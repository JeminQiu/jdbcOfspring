package ExectorSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;


public class Server {
    private static final Logger logger=LoggerFactory.getLogger(Server.class);
    ServerSocket socketServer;
    int sumSocket=0;
    public Server() throws IOException {
        socketServer=new ServerSocket(8889);
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
                        logger.error("非线程池式客户端 "+socket.toString()+"接入，启动线程");
                      new Thread(new socketThread(socket)).start();
                    }
                    catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }.start();
    }
    class socketThread implements Runnable{
        Socket socket;
        public void run(){
            try {
                AtomicReference<InputStream> inputStream = new AtomicReference<InputStream>(socket.getInputStream());
                OutputStream FileStream = new FileOutputStream(new File("text.txt"));
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = inputStream.get().read(bytes)) != -1) {
                    FileStream.write(bytes);
                    FileStream.flush();
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            logger.error("非线程池的方式：线程"+socket.toString()+" 文件发送完成");
        }
        public socketThread(Socket socket){
            this.socket=socket;
        }
    }
    public static void main(String[] args) throws IOException{
        logger.error("非线城池式的服务器启动 ，准备接受客户端");
        new Server();
    }
}
