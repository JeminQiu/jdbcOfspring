package networkThird;

import ExectorSocket.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class experiment_3_2_1_server {
    Socket socket;
    ServerSocket serverSocket;
    BufferedReader br;
    PrintWriter pw;
    public experiment_3_2_1_server() throws IOException {
        serverSocket=new ServerSocket(8888);
        socket=serverSocket.accept();
        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw=new PrintWriter(socket.getOutputStream());
        String str;
        while(true){
            str=br.readLine();
            System.out.println("Server receive:" +str);
            if ("Time".equals(str)){
                String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                        format(Calendar.getInstance().getTime());
                pw.println("Current time:" +time);
                System.out.println("Output:Current time:"+time);
                pw.flush();
            }
            else if ("exit".equals(str)){
                pw.println("bye");
                System.out.println("Server output:bye");
                pw.flush();
                pw.close();
                br.close();
                socket.close();
                break;
            }
            else {
                pw.println("Sorry,No a command");
                System.out.println("server output : sorry ,order_no a command");
                pw.flush();
            }
        }
    }
    public static void  main(String args[]) throws IOException {
        new experiment_3_2_1_server();
    }
}
