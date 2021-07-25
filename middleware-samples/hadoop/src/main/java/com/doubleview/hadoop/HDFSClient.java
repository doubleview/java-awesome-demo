package com.doubleview.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
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
        FsPermission fsPermission = new FsPermission(FsAction.ALL, FsAction.ALL, FsAction.ALL);
//        fs.delete(new Path("/user/huchengchao"), true);
        fs.mkdirs(new Path("/user/doubleview"), fsPermission);
        //3 关闭资源
        fs.close();
    }

    private FileSystem getFileSystem() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        return FileSystem.get(new URI("hdfs://ecs01:9000"), configuration, "huchengchao");
    }

    @Test
    public void testCopyFromLocalFile() throws IOException, InterruptedException, URISyntaxException {
        // 1 获取文件系统
        FileSystem fs = getFileSystem();
        // 2 上传文件
        fs.copyFromLocalFile(new Path("/Users/huchengchao/hack/cmdb.json"), new Path("/user/doubleview/cmdb.json"));
        fs.close();
    }

    @Test
    public void testCopyToLocalFile() throws Exception {
        FileSystem fs = getFileSystem();
        fs.copyToLocalFile(false, new Path("/user/doubleview/cmdb.json"), new Path("local.json"), true);
        fs.close();
    }

    @Test
    public void testRename() throws Exception {
        FileSystem fs = getFileSystem();
        fs.rename(new Path("/user/doubleview/cmdb.json"), new Path("/user/doubleview/cmdb1.json"));
        fs.close();
    }

    @Test
    public void testListFiles() throws IOException, URISyntaxException, InterruptedException {
        // 1获取文件系统
        FileSystem fs = getFileSystem();
        // 2 获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/user"), true);
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
        FileSystem fs = getFileSystem();
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

        FSDataInputStream fis = fs.open(new Path("/user/doubleview/cmdb1.json"));
        FileOutputStream fos = new FileOutputStream(new File("cmdb_copy.json"));
        // 4 流对拷
        IOUtils.copyBytes(fis, fos, configuration);
        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    public void readFileSeek1() throws Exception {
        FileSystem fs = getFileSystem();

        // 2 获取输入流
        FSDataInputStream fis = fs.open(new Path("/hadoop- 2.7.2.tar.gz"));
        // 3 创建输出流
        FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-2.7.2.tar.gz.part1"));
        // 4 流的拷贝
//        fis.seek(1024 * 1024 * 128);
        byte[] buf = new byte[1024];
        for(int i =0 ; i < 1024 * 128; i++){
            fis.read(buf);
            fos.write(buf);
        }
        // 5关闭资源
        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos); fs.close();
        fs.close();;
    }
}