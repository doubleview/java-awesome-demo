package com.doubleview.hbase;

import cn.hutool.core.util.StrUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;


public class Demo {

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


    private Table getTable(String tableName) throws IOException {
        Connection connection = ConnectionFactory.createConnection(getConf());
        return connection.getTable(TableName.valueOf(tableName));
    }


    @Test
    public void isTableExist() throws IOException {
        Connection connection = ConnectionFactory.createConnection(getConf());
        Admin admin = connection.getAdmin();
        System.out.println(admin.tableExists(TableName.valueOf("student")));
    }

    @Test
    public void createTable() throws IOException {
        Admin admin = getAdmin();
        String tableName = "t_user_ride_card";
        if (admin.tableExists(TableName.valueOf(tableName))) {
            System.out.println(StrUtil.format("表 {} 已经存在", tableName));
        } else {
            TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName))
                    .setColumnFamily(ColumnFamilyDescriptorBuilder.of("cf"))
                    .build();
            admin.createTable(tableDescriptor);
            System.out.println(StrUtil.format("表 {} 创建成功", tableName));
        }
    }

    @Test
    public void dropTable() throws IOException {
        Admin admin = getAdmin();
        String tableName = "t_user_ride_card";
        if (admin.tableExists(TableName.valueOf(tableName))) {
            admin.deleteTable(TableName.valueOf(tableName));
            System.out.println(StrUtil.format("表 {} 删除成功", tableName));
        } else {
            System.out.println(StrUtil.format("表 {} 不存在", tableName));
        }
    }

    @Test
    public void addRowData() throws IOException {
        Table table = getTable("t_user_ride_card");
        Put put = new Put(Bytes.toBytes("1"));
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("days"), Bytes.toBytes("30"));
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("status"), Bytes.toBytes("1"));
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("name"), Bytes.toBytes("单车不限次卡"));
        table.put(put);
        table.close();
        System.out.println("插入数据成功");
    }

    @Test
    public void deleteMultiRow() throws IOException {
        Table table = getTable("t_user_ride_card");
        table.delete(new Delete(Bytes.toBytes("111111")));
        table.close();
        System.out.println("删除数据成功");
    }

    @Test
    public void getAllRows() throws IOException {
        Table table = getTable("t_user_ride_card");
        Scan scan = new Scan();
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println("行键: " + Bytes.toString(CellUtil.cloneRow(cell)));
                System.out.println("列族: " + Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.println("值: " + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
    }

    @Test
    public void getRow() throws IOException {
        Table table = getTable("t_user_ride_card");

        Get get = new Get(Bytes.toBytes("1"));
        Result result = table.get(get);
        for (Cell cell : result.rawCells()) {
            System.out.println("行键: " + Bytes.toString(CellUtil.cloneRow(cell)));
            System.out.println("列族: " + Bytes.toString(CellUtil.cloneFamily(cell)));
            System.out.println("值: " + Bytes.toString(CellUtil.cloneValue(cell)));
            System.out.println("时间戳: " + cell.getTimestamp());
            System.out.println("--------");
        }
    }

    @Test
    public void getRowQualifier() throws IOException {
        Table table = getTable("t_user_ride_card");

        Get get = new Get(Bytes.toBytes("1"));
        get.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("name"));
        Result result = table.get(get);
        for (Cell cell : result.rawCells()) {
            System.out.println("行键: " + Bytes.toString(CellUtil.cloneRow(cell)));
            System.out.println("列族: " + Bytes.toString(CellUtil.cloneFamily(cell)));
            System.out.println("健: " + Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println("值: " + Bytes.toString(CellUtil.cloneValue(cell)));
            System.out.println("时间戳: " + cell.getTimestamp());
            System.out.println("--------");
        }
    }
}