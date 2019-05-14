import java.net.*;
import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.rmi.UnknownHostException;
import java.util.RandomAccess;
import java.util.Scanner;

public class anaRealName  {
    File file;
    String url;
    long position;
    public anaRealName(){

        url=new String();
         url="ftp://ftp.inrialpes.fr/pub/lear/douze/data/INRIAPerson.tar";
        file=new File("断点续传.tar");
        position=file.length();
        doal();
       // con(file,url,position);
    }
    public void getByname() {
        String name;
        Scanner sc=new Scanner(System.in);
        try {
            for (int i=0;i<5;i++) {
                name=sc.nextLine();
                InetAddress inetAddress = InetAddress.getByName(name);
                System.out.println(inetAddress);
            }
        }
        catch (java.net.UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public void con(File fileWrite,String url,long position)  {
       try {
           System.out.println("文件已经下载："+position+"B");
           RandomAccessFile Fwrite = new RandomAccessFile(fileWrite, "rw");
           Fwrite.seek(position);
           URL url1=new URL(url);
           HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
           conn.setRequestProperty("Range","bytes="+position+"-");
           conn.connect();
           System.out.println("待下载文件大小为："+conn.getContentLength()+"B");
           InputStream ins = conn.getInputStream();
           byte[] buf=new byte[2048];
           while ((ins.read(buf))>=0){
               Fwrite.write(buf);
               //System.out.println(fileWrite.length());
           }
       }
       catch (FileNotFoundException e){
           System.out.println(e.getMessage());
           System.out.println(e.getCause());
       }
       catch (IOException h){
           System.out.println(h.getMessage());
           System.out.println(h.getCause());
        }
    }
    public void useUrl2(File fileWrite,String string,long position)  {
        try{
            URL url=new URL(string);
            FileOutputStream fout=new FileOutputStream(fileWrite);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            System.out.println("待下载文件大小为:"+conn.getContentLength());
            System.out.println("开始下载文件");
            InputStream ins = conn.getInputStream();
            byte[] buf=new byte[100];
            while ((ins.read(buf))>=0){
                fout.write(buf);
            }
        }
        catch (IOException e){
            System.out.println("文件已经下载："+fileWrite.length());
            position=fileWrite.length();
        }
    }
    public void doal(){
        try{
            URL url=new URL("ftp://ftp.inrialpes.fr/pub/lear/douze/data/INRIAPerson.tar");
            InputStream in=url.openStream();
            FileOutputStream fout=new FileOutputStream(new File("szu.tar"));
            int a=0;
            while (a>-1){
                a=in.read();
                fout.write(a);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static  void main(String[] args){
       new anaRealName();
    }
}
