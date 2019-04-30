package networkThird;

import javax.xml.bind.Element;
import java.io.*;
import java.net.*;

public class inetAddress {
    static void testURL() throws MalformedURLException , IOException {
        URL url=new URL("http://images.sohu.com/uiue/sohu_logo/beijing2008/sohu.gif");
        InputStream in=url.openStream();
        FileOutputStream fout=new FileOutputStream(new File("sohu.gif"));
        int a=0;
        while(a>-1){
            a=in.read();
            fout.write(a);
        }
    }
    static void  getSzuWeb() throws IOException,MalformedURLException{
        URL url=new  URL("https://www.szu.edu.cn");
       InputStream in=url.openStream();
       InputStreamReader  inputStreamReader=new InputStreamReader(in);
       BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
       String str;
       File file=new File("szu.html");
       BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true));
       while ((str=bufferedReader.readLine())!=null){
           bufferedWriter.write(str+"\n");
           bufferedWriter.flush();
       }
        HttpURLConnection urlcon=(HttpURLConnection)url.openConnection();
        //获取相应的文件长度
        System.out.println("页面文件大小  "+urlcon.getContentLength()/1024+"KB");
    }
    static void getAddress() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostName()+ " "+address.getHostAddress());
        InetAddress[] addresses=InetAddress.getAllByName("www.csdn.net");
        System.out.println("www.csdn.net上的所有ip地址");
        for(int i=0;i<addresses.length;i++)
            System.out.println(addresses[i]);
    }
    static  void getElement(){
       // Element element =doc.getElementsByTag("a");
    }
    public static void main(String[] args) throws IOException,UnknownHostException, MalformedURLException {
         getSzuWeb();
    }
}
