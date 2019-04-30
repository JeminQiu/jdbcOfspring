package ExectorSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;


public class SocketServer {
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);
     ExecutorService executorService=Executors.newCachedThreadPool();
     ServerSocket socketServer;
     int sumSocket=0;
     public SocketServer() throws IOException {
         socketServer=new ServerSocket(8887);
         logger.error("端口号8887： 等待客户端连接");
         acceptSocket();
     }
     void acceptSocket() {
        new Thread(){
            @Override
            public void run(){
                while(true){
                    try {
                        Socket socket = socketServer.accept();
                        sumSocket++;
                        System.out.println("客户端接入"+socket.toString());
                        logger.error("客户端"+socket.toString()+" 连接");
                        Callable thread=new ServerThread(socket);
                        Future future=executorService.submit(thread);
                        logger.error("线程加入线程池");
                        if (!future.isDone()){
                            future.cancel(true);
                        }
                    }
                    catch (IOException e){
                        logger.error(e.getMessage());
                    }
                }
            }
        }.start();
     }
     class ServerThread implements Callable{
         Socket socket;
         public Object call() throws Exception {
             InputStream inputStream=socket.getInputStream();
             OutputStream FileStream=new FileOutputStream(new File("text.txt"));
             byte[] bytes=new byte[1024];
             int len=0;
             while((len=inputStream.read(bytes))!=-1){
                 FileStream.write(bytes);
                 FileStream.flush();
             }
             System.out.println("文件接收完成");
             logger.error("接受客户端信息完成");
             return null;
         }
         public ServerThread(Socket socket){
             this.socket=socket;
         }
     }
     public static void main(String[] args) throws IOException{
         logger.error("线程池形式启动服务器");
         new SocketServer();
    }
}
