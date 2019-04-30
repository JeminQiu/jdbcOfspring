package networkThird;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class expriment_3_2_1_Client {
    Socket socket;
    BufferedReader br;
    PrintWriter pw;
    public expriment_3_2_1_Client()throws IOException {
        try{
        socket = new Socket("localhost", 8888);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(socket.getOutputStream());
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        while (!"exit".equals(str)) {
            pw.println(str);
            pw.flush();
            System.out.println("Output:" + str);
            str = br.readLine();
            System.out.println("receive:" + str);
            str = in.nextLine();
        }
        pw.println("exit");
        pw.flush();
        System.out.println("Output exit");
        str = br.readLine();
        System.out.println("receive:" + str);
        pw.close();
        br.close();
        socket.close();
    }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static  void main(String args[]) throws  IOException{
        new expriment_3_2_1_Client();
    }
}
