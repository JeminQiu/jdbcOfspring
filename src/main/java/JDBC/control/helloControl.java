package JDBC.control;
import jdk.internal.util.xml.impl.Input;
import sun.security.krb5.Config;

import javax.lang.model.element.NestingKind;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class helloControl {
    public static void main(String[]args) throws java.sql.SQLException ,ClassNotFoundException{
        /*Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/ddemo?useSSL=FALSE&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root","1120");
        String sql="select * from dept";
        Statement stmt=connection.createStatement();
        ResultSet resultSet=stmt.executeQuery(sql);
        while (resultSet.next()){
            int a=resultSet.getInt("DEPTNO");
            String b=resultSet.getString("DNAME");
            String c=resultSet.getString("LOC");
            System.out.println(a+" "+b+" "+c);
        }*/
        ConfigManager configManager=new ConfigManager();
        configManager.getNewsList();
    }
}
class ConfigManager{
    private static ConfigManager configManager;
    private static Properties properties;
    //在构造工具类时，进行配置文件的读取
    public ConfigManager() {
        String configFile = "application.properties";
        properties = new Properties();
        InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
        try {
            //读取配置文件
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        //通过单例模式配置实例化的个数
     public static  ConfigManager getInstance(){
        if (configManager==null){
            configManager=new ConfigManager();
        }
        return configManager;
    }
    //通过key获取对应的value
    public String getString(String key){
        return properties.getProperty(key);
    }
    //使用配置文件访问数据库
    public void getNewsList()throws  java.sql.SQLException ,ClassNotFoundException {
        Connection connection = null;
        String driver = ConfigManager.getInstance().getString("spring.datasource.driver-class-name");
        String url = ConfigManager.getInstance().getString("spring.datasource.url");
        String username = ConfigManager.getInstance().getString("spring.datasource.username");
        String password = ConfigManager.getInstance().getString("spring.datasource.password");
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
        String sql = "select * from dept";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next()) {
            int a = resultSet.getInt("DEPTNO");
            String b = resultSet.getString("DNAME");
            String c = resultSet.getString("LOC");
            System.out.println(a + " " + b + " " + c);
        }
        stmt.execute("insert  into  dept(deptno,dname,loc)values (60,'Accounting','qiuzz')");
    }

}
