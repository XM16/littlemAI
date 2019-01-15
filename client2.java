package TCPSocket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CORBA.portable.OutputStream;

public class client2 extends JFrame implements ActionListener{
	public static String path;
	public static JLabel ja=new JLabel("接受的文件");
	public static JLabel jb=new JLabel("已接受字节数");
	public static JLabel jc=new JLabel("BYTES");
	public static JLabel jd=new JLabel("文件服务器地址");
	public static JButton js=new JButton("建立TCP连接");
	public static JButton jz=new JButton("中断接收");
	public static JTextField jna=new JTextField();
	public static JTextField jmb=new JTextField();
	public static JTextField jvd=new JTextField();
	public static Long len;
	JPanel jf=new JPanel();
	public client2(){
		jf.setLayout(null);
		ja.setBounds(10,10,100,20);
		jf.add(ja);
		jna.setBounds(10, 40, 300, 25);
		jna.addActionListener(this);
		jf.add(jna);
		jb.setBounds(10, 70, 150, 25);
		jf.add(jb);
		jmb.setBounds(100,70,100,25);
		jmb.addActionListener(this);
		jf.add(jmb);
		jc.setBounds(200, 70, 40, 25);
		jf.add(jc);
		jd.setBounds(10, 100, 150, 25);
		jf.add(jd);
		jvd.setBounds(100, 100, 100, 25);
		jvd.addActionListener(this);
		jf.add(jvd);
		js.setBounds(10, 130, 120, 25);
		js.addActionListener(this);
		jf.add(js);
		jz.setBounds(220, 130, 90, 25);
		jz.addActionListener(this);
		jf.add(jz);
		this.add(jf);
		this.setTitle("文件客户端2");
		this.setBounds(300, 170, 350, 250);
		this.setVisible(true);
	}
	   public static void main(String[] args) {
		   client1 de=new client1();
		   
		   
		}
	@Override
	public void actionPerformed(ActionEvent t) {
		// TODO Auto-generated method stub
		if(t.getSource()==js) 
		try
		{
			Socket client=new Socket("172.19.98.113",9076);
			//输入流用于网路传输
			DataInputStream is=new DataInputStream(client.getInputStream());
			path="E:\\";
			path+=is.readUTF();
			jna.setText(path);
			jvd.setText(client.getInetAddress().toString());
			//输出流，把内存中内容写到硬盘中
			FileOutputStream os =new FileOutputStream(path);
			int bufferSize=9087;
			byte[] buf=new byte[bufferSize];
			long passedlen=0;
			int read=-1;
			while((read=is.read(buf))!=-1){
				   os.write(buf,0,read);
				passedlen+=read;
			}
			System.out.println("文件传输完成");
 			jmb.setText(Long.toString(passedlen));
 			if(t.getSource()==jz)
 			{
			os.close();
			is.close();
			client.close();
 			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

