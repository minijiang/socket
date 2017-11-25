package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

import entity.User;

public class Client {

	private int port = 8888;
	private String loca = "localhost";
	public Socket socket = null;
	Scanner in = new Scanner(System.in);
	String name = ""; //发送人
	String pass = ""; //内容或密码
	String rname =""; //指向人
    boolean bm = true;
	
	public static void main(String[] args) {
		new Client();
	}
	public Client() {
		try {

			// 链接sever端
			Socket s1 = new Socket("127.0.0.1", 8888);
			// 实例化输入流
			DataInputStream dis = new DataInputStream(s1.getInputStream()); // 用dataInputStream包装一下然后就不会产生线程阻塞
			// 实例化输出流
			DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
		
			System.out.println("请选择： 1-登陆   2-注册  输入其他退出....");
			switch (in.nextInt()) {
			case 1:
				login(dos,dis);
				
					System.out.println("请选择： 1.指定人聊天    2.向服务器聊天  输入其他退出....");
					int xz =in.nextInt();
					if (xz == 1) {
						chatroom(name, dos,dis);
					}else if(xz==2){
					dos.writeUTF(in.next());
					}
					chatroom(name, dos,dis);		
				break;
			case 2:
				add(dos,dis);
				String ext = dis.readUTF();
				if (ext.equals("用户名已存在")) {
					System.out.println(ext + "请重新输入注册信息...");
					add(dos,dis);
				}else{
					
				}
				break;
			default:
				System.out.println("已退出程序...");
				break;
			
			}

			// 实例化并启动两个进程
			new MyClientReader(dis,s1).start();
			new MyClientWriter(dos).start();
			// 捕获异常
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void login(DataOutputStream dos,DataInputStream dis) {
		System.out.println("请输入用户名：");
		name = in.next().trim();
		System.out.println("请输入密码：");
		String pwd = in.next().trim();
		try {
			dos.writeUTF("0-" + name + "-" + pwd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void add(DataOutputStream dos,DataInputStream dis) {
		System.out.println("请输入用户名：");
		name = in.next().trim();
		System.out.println("请输入密码：");
		String pwd = in.next().trim();
		try {
			dos.writeUTF("1-" + name + "-" + pwd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void chatroom(String name, DataOutputStream dos,DataInputStream dis) {
		// name - text - refname
		System.out.println("输入接收人：");
		String refname = in.next().trim();
		System.out.println("输入要发送的信息：");
		String text = in.next();
		String con = "2-"+ text + "-" + name +"-"+refname;
		try {
			dos.writeUTF(con);
			dos.flush();
			System.out.println(dis.readUTF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}