package networkThird;

import java.io.*;
import java.net.Socket;

public class exprriment_3_3_Client {
    Socket socket;
    public exprriment_3_3_Client()throws IOException {
        socket=new Socket("localhost",8888);
        System.out.println("客户端连接成功");
        acceptTxt();
    }
    public void acceptTxt() throws  IOException {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("接受的文件为:" + bufferedReader.readLine());
            System.out.print("保存为:1text.txt");
            OutputStream FileStream = new FileOutputStream(new File("1text.txt"));
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                FileStream.write(bytes);
                FileStream.flush();

            }
        }
        catch (IOException e){
            System.out.println();
            System.out.println(e.getMessage());
        }

    }
    public static void main(String args[])throws  IOException{
        new exprriment_3_3_Client();
    }
}
