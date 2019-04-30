package networkThird;

import ExectorSocket.SocketText;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;

public class experiment_3_3 {
    ServerSocket socketServer;
    Socket socket;
    public experiment_3_3() throws IOException ,InterruptedException{
        socketServer=new ServerSocket(8888);
        socket=socketServer.accept();
        System.out.println("服务器启动，与客户端一连接成功");
        System.out.println("要传输的文件：滕王阁序.txt");
        sendTxt();
    }
    void sendTxt() throws  IOException,InterruptedException{
        OutputStream outputStream=socket.getOutputStream();
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("滕王阁序.txt\n");
        bufferedWriter.flush();
        InputStream inputStream=socket.getInputStream();
        InputStream fileRead=new FileInputStream(new File("src/main/滕王阁序.txt"));
        System.out.println("开始传输文件");
        int len=0;
        byte []bytes=new byte[1024];

        while((len=fileRead.read(bytes))!=-1){
            outputStream.write(bytes);
            outputStream.flush();
        }
        System.out.println("传输完成");
        Thread.sleep(1000);
    }
    public static void main(String args[]) throws IOException,InterruptedException{
        new experiment_3_3();
    }
}
