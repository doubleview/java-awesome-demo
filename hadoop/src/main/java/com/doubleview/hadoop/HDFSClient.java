package com.doubleview.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author huchengchao
 */
public class HDFSClient {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        //1 获取文件系统
        Configuration configuration = new Configuration();
        //configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
        //FileSystem fs = FileSystem.get(configuration);
        FileSystem fs = FileSystem.get(new URI("hdfs://ecs01:9000"), configuration, "huchengchao");
        //2 创建目录
        fs.mkdirs(new Path("/user/doubleview"));
        //3 关闭资源
        fs.close();
    }

    @Test
    public void testCopyFromLocalFile() throws IOException, InterruptedException, URISyntaxException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication", "2");
        FileSystem fs = FileSystem.get(new URI("hdfs://ecs01:9000"), configuration, "huchengchao");
        // 2 上传文件
        fs.copyFromLocalFile(new Path("/Users/huchengchao/hack/cmdb.json"), new Path("/user/doubleview/cmdb.json"));
        fs.close();
    }

    @Test
    public void testListFiles() throws IOException, URISyntaxException, InterruptedException {
        // 1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://ecs01:9000"), configuration, "huchengchao");
        // 2 获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus status = listFiles.next();
            // 输出详情
            // 文件名称
            System.out.println(status.getPath().getName());
            // 长度
            System.out.println(status.getLen());
            // 权限
            System.out.println(status.getPermission());
            // 分组
            System.out.println(status.getGroup());
            // 获取存储的块信息
            BlockLocation[] blockLocations = status.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
        }
        fs.close();
    }

    @Test
    public void testListStatus() throws IOException, URISyntaxException, InterruptedException {
        // 1 获取文件配置信息
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://ecs01:9000"), configuration, "huchengchao");
        // 2 判断是文件还是文件夹
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {
            // 如果是文件
            if (fileStatus.isFile()) {
                System.out.println("f:" + fileStatus.getPath().getName());
            } else {
                System.out.println("d:" + fileStatus.getPath().getName());
            }
        }
        // 3 关闭资源
        fs.close();
    }

    @Test
    public void putFileToHDFS() throws IOException, URISyntaxException, InterruptedException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://ecs01:9000"), configuration, "huchengchao");
        // 2 创建输入流
        FileInputStream fis = new FileInputStream(new File("/Users/huchengchao/hack/cmdb.json"));
        // 3 获取输出流
        FSDataOutputStream fos = fs.create(new Path("/user/doubleview/cmdb_copy.json"));
        // 4 流对拷
        IOUtils.copyBytes(fis, fos, configuration);
        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }


    @Test
    public void getFileFromHDFS() throws IOException, URISyntaxException, InterruptedException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://ecs01:9000"), configuration, "huchengchao");

        FSDataInputStream fis = fs.open(new Path("/user/doubleview/cmdb.json"));
        FileOutputStream fos = new FileOutputStream(new File("/Users/huchengchao/hack/cmdb_copy.json"));
        // 4 流对拷
        IOUtils.copyBytes(fis, fos, configuration);
        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }
}