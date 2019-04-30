package ExectorSocket;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.io.*;
import java.net.Socket;
/**
 * Copyright (C), 2017-2019, XXX有限公司<br>
 * FileName: SocketText <br>
 * Date:    2019/3/29
 *
 * @author 2017193002     深圳大学<br>
 * DESCRIPTION:<br>
 * HISTORY:
 */
public class Client {
    Socket socket;
    private static final Logger logger= LoggerFactory.getLogger(Client.class);
    public Client()throws IOException {
        socket=new Socket("localhost",8889);
        OutputStream  outputStream=socket.getOutputStream();
        logger.error("客户端 "+socket.toString()+" 成功连接服务器端");
        InputStream fileRead=new FileInputStream(new File("src/main/滕王阁序.txt"));
        logger.error("非线程池方式的线程传输文件完成");
        int len=0;
        byte[]bytes =new byte[1024];
        while ((len=fileRead.read(bytes))!=-1){
            outputStream.write(bytes);
        }
        System.out.println("文件发送完成");
    }
    public static void main(String[] args) throws IOException {
        // 获取当前程序运行时对象
        Runtime run = Runtime.getRuntime();
        // 调用垃圾回收机制，以减少内存误差
        run.gc();
        // 获取当前JVM的空闲内存
        long freeMemory = run.freeMemory();
        for (int i=0;i<100;i++)
           new Client();

        System.out.println("使用线程池创建100个线程所需要占用的内存大小: "+
        ((freeMemory - run.freeMemory())+578565));
    }
}

