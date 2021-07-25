package com.doubleview.hbase.weibo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class Weibo {

    //获取配置 conf
    private Configuration conf = HBaseConfiguration.create();
    //微博内容表的表名
    private static final byte[]  TABLE_CONTENT = Bytes.toBytes("weibo:content");
    //用户关系表的表名
    private static final byte[] TABLE_RELATIONS = Bytes.toBytes("weibo:relations");
    //微博收件箱表的表名
    private static final byte[] TABLE_RECEIVE_CONTENT_EMAIL = Bytes.toBytes("weibo:receive_content_email");


    private Configuration getConf() {
        //使用 HBaseConfiguration 的单例方法实例化
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "ecs01,ecs02,ecs03");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        return conf;
    }

    private Admin getAdmin() throws IOException {
        Connection connection = ConnectionFactory.createConnection(getConf());
        return connection.getAdmin();
    }

    private Table getTable(byte[] tableName) throws IOException {
        Connection connection = ConnectionFactory.createConnection(getConf());
        return connection.getTable(TableName.valueOf(tableName));
    }

    @Test
    public void initNamespace() {
        Admin admin = null;
        try {
            admin = getAdmin();
            //命名空间类似于关系型数据库中的 schema，可以想象成文件夹
            NamespaceDescriptor weibo = NamespaceDescriptor.create("weibo")
                    .addConfiguration("creator", "doubleview")
                    .addConfiguration("create_time", System.currentTimeMillis() + "").build();
            admin.createNamespace(weibo);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != admin) {
                try {
                    admin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createTable(byte[] tableName, Collection<String> columnFamilies) {
        Admin admin = null;
        try {
            admin = getAdmin();
            Collection<ColumnFamilyDescriptor> columnFamilyDescriptors = new ArrayList<>();
            for (String name : columnFamilies) {
                ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(name))
                        .setBlockCacheEnabled(true)
                        .setBlocksize(2097152)
                        .setCompressionType(Compression.Algorithm.SNAPPY)
                        .setMaxVersions(1)
                        .setMinVersions(1)
                        .build();
                columnFamilyDescriptors.add(columnFamilyDescriptor);
            }

            TableDescriptor content = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName))
                    .setColumnFamilies(columnFamilyDescriptors)
                    .build();
            admin.createTable(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != admin) {
                try {
                    admin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建微博内容表
     */
    @Test
    public void createTableContent() {
        createTable(TABLE_CONTENT, Collections.singletonList("info"));
    }

    /**
     * 创建微博内容表
     */
    @Test
    public void createTableRelations() {
        createTable(TABLE_RELATIONS, Arrays.asList("fans", "attends"));
    }


    /**
     * 创建微博内容表
     */
    @Test
    public void createTableReceiveContentEmail() {
        createTable(TABLE_RECEIVE_CONTENT_EMAIL, Collections.singletonList("info"));
    }

    @Test
    public void testPublishContent() throws IOException {
        publishContent("1001", "今天买了一包空气，送了点薯片，非常开心!!");
        publishContent("1001", "今天天气不错");

        publishContent("1002", "准备下课");
        publishContent("1002", "准备关机");

        publishContent("1003", "我爱Java");
        publishContent("1003", "我爱ElasticSearch");
    }


    @Test
    public void testAddAttends() throws IOException {
        addAttends("1001", "1002", "1003");
    }

    public void publishContent(String uid, String content) throws IOException {
        //组装 Rowkey
        Table contentTable = getTable(TABLE_CONTENT);
        long timestamp = System.currentTimeMillis();
        String rowKey = uid + "_" + timestamp;
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("content"), timestamp, Bytes.toBytes(content));
        contentTable.put(put);

        Table relationTable = getTable(TABLE_RELATIONS);
        Get get = new Get(Bytes.toBytes(uid));
        get.addFamily(Bytes.toBytes("fans"));
        Result result = relationTable.get(get);
        List<byte[]> fans = new ArrayList<>();
        //遍历取出当前发布微博的用户的所有粉丝数据
        for (Cell cell : result.rawCells()) {
            fans.add(CellUtil.cloneValue(cell));
        }

        //开始操作收件箱表
        Table emailTable = getTable(TABLE_RECEIVE_CONTENT_EMAIL);
        List<Put> puts = new ArrayList<>();
        for (byte[] fan : fans) {
            Put fanPut = new Put(fan);
            fanPut.addColumn(Bytes.toBytes("info"), Bytes.toBytes(uid), timestamp, Bytes.toBytes(rowKey));
            puts.add(fanPut);
        }
        emailTable.put(puts);
    }

    public void addAttends(String uid, String... attends) throws IOException {
        Table relationTable = getTable(TABLE_RELATIONS);
        List<Put> puts = new ArrayList<>();
        //a、在微博用户关系表中，添加新关注的好友
        Put attendPut = new Put(Bytes.toBytes(uid));
        for (String attend : attends) {
            // 为当前用户添加关注的人
            attendPut.addColumn(Bytes.toBytes("attends"), Bytes.toBytes(attend), Bytes.toBytes(attend));
            //b、为被关注的人，添加粉丝
            Put fansPut = new Put(Bytes.toBytes(attend));
            fansPut.addColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid), Bytes.toBytes(uid));
            //将所有关注的人一个一个的添加到 puts(List)集合中
            puts.add(fansPut);
        }
        puts.add(attendPut);
        relationTable.put(puts);

        //c.1、微博收件箱添加关注的用户发布的微博内容(content)的 rowkey
        Table contentTable = getTable(TABLE_CONTENT);
        Scan scan = new Scan();
        //用于存放取出来的关注的人所发布的微博的 rowkey
        List<byte[]> rowkeys = new ArrayList<>();

        for (String attend : attends) {
            //过滤扫描 rowkey，即:前置位匹配被关注的人的 uid_
            RowFilter filter = new RowFilter(CompareOperator.EQUAL, new SubstringComparator(attend + "_"));
            //为扫描对象指定过滤规则
            scan.setFilter(filter);
            //通过扫描对象得到 scanner
            ResultScanner result = contentTable.getScanner(scan);
            //迭代器遍历扫描出来的结果集
            for (Result r : result) {
                //取出每一个符合扫描结果的那一行数据
                for (Cell cell : r.rawCells()) {
                    // 将得到的 rowkey 放置于集合容器中
                    rowkeys.add(CellUtil.cloneRow(cell));
                }
            }
        }

        //c.2、将取出的微博 rowkey 放置于当前操作用户的收件箱中
        if (rowkeys.size() <= 0) {
            return;
        }
        //得到微博收件箱表的操作对象
        Table emailTable = getTable(TABLE_RECEIVE_CONTENT_EMAIL);
        //用于存放多个关注的用户的发布的多条微博 rowkey 信息
        List<Put> recPuts = new ArrayList<>();
        for (byte[] rk : rowkeys) {
            Put put = new Put(Bytes.toBytes(uid)); //uid_timestamp
            String rowKey = Bytes.toString(rk); //借取 uid
            String attendUID = rowKey.substring(0, rowKey.indexOf("_"));
            long timestamp = Long.parseLong(rowKey.substring(rowKey.indexOf("_") + 1));
            //将微博 rowkey 添加到指定单元格中
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(attendUID), timestamp, rk);
            recPuts.add(put);
        }
        emailTable.put(recPuts);
    }
}