package networkThird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MyUDPChat extends JFrame implements ActionListener,Runnable {
    JFrame frame;
    JTextArea textArea;
    JTextField ip;
    JTextField desPort;
    JTextField myPort,name, message;
    JButton ensure;
    JButton send,clear,exit;

    public MyUDPChat(){
        GUI();
    }

    void GUI(){
        frame = new JFrame("UDPChatRoom");
        frame.setSize(550,600);

        //窗口上方
        JLabel label = new JLabel("DesIP:") ; JPanel panel1 = new JPanel();
        panel1.add(label);

        //目的IP
        ip = new JTextField(10);
        panel1.add(ip);
        ip.setText("127.0.0.1");

        //目的端口
        label = new JLabel("DesPort");
        panel1.add(label);
        desPort = new JTextField(4);
        panel1.add(desPort);

        //本地端口
        label = new JLabel("MyPort:");
        panel1.add(label);
        myPort = new JTextField(4);
        panel1.add(myPort);

        ensure = new JButton("ensure"); ensure.addActionListener(this);
        panel1.add(ensure);
        frame.getContentPane().add(panel1,BorderLayout.NORTH);
        //聊天文本
        textArea = new JTextArea(); textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        //textArea.setEnabled(false);
        textArea.setFont(new Font("宋体",Font.PLAIN,12));
        textArea.setForeground(Color.BLACK);
        JScrollPane js = new JScrollPane(textArea);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(js);
        //窗口下方
        name = new JTextField(6);
        message = new JTextField(10);
        send = new JButton("send");
        send.addActionListener(this);
        clear = new JButton("clear");
        clear.addActionListener(this);
        exit = new JButton("exit");
        exit.addActionListener(this);

        label = new JLabel("name:");
        JPanel panel2 = new JPanel();
        panel2.add(label);panel2.add(name);

        label = new JLabel("message:");
        panel2.add(label);
        panel2.add(message);

        panel2.add(send); panel2.add(clear); panel2.add(exit);
        frame.getContentPane().add(panel2,BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==send) {
            try {
                byte[] host = {127,0,(byte)0,(byte)1};
                InetAddress address = InetAddress.getByAddress(host);
                String send_message = name.getText() + ":\n" + message.getText();
                byte[] data = send_message.getBytes();
                textArea.append("you:"+"\n"+message.getText()+"\n\n");

                DatagramPacket packet = new DatagramPacket(data,data.length,address,Integer.parseInt(desPort.getText()));
                DatagramSocket socket = new DatagramSocket();
                socket.send(packet);
            } catch(IOException e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getSource()==ensure){
            Thread t = new Thread(this);
            t.start();
        }
        else if(e.getSource()==clear){
            textArea.setText("");
        }
        else if(e.getSource()==exit){
            System.exit(0);
        }
    }

    public void run(){
        myReceive();
    }

    void myReceive(){
        try {
            DatagramSocket receive;
            receive = new DatagramSocket(Integer.parseInt(myPort.getText()));

            while (true) {
                byte[] data = new byte[60];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                receive.receive(packet);
                textArea.append(new String(data)+"\n");
                Thread.sleep(1000);
            }
        }catch(InterruptedException e2) {
            e2.printStackTrace();
        }catch (IOException e1){
            e1.printStackTrace();
        }
    }

    public static void main(String []args){
         new MyUDPChat();
    }
}
