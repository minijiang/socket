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
	String name = ""; //������
	String pass = ""; //���ݻ�����
	String rname =""; //ָ����
    boolean bm = true;
	
	public static void main(String[] args) {
		new Client();
	}
	public Client() {
		try {

			// ����sever��
			Socket s1 = new Socket("127.0.0.1", 8888);
			// ʵ����������
			DataInputStream dis = new DataInputStream(s1.getInputStream()); // ��dataInputStream��װһ��Ȼ��Ͳ�������߳�����
			// ʵ���������
			DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
		
			System.out.println("��ѡ�� 1-��½   2-ע��  ���������˳�....");
			switch (in.nextInt()) {
			case 1:
				login(dos,dis);
				
					System.out.println("��ѡ�� 1.ָ��������    2.�����������  ���������˳�....");
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
				if (ext.equals("�û����Ѵ���")) {
					System.out.println(ext + "����������ע����Ϣ...");
					add(dos,dis);
				}else{
					
				}
				break;
			default:
				System.out.println("���˳�����...");
				break;
			
			}

			// ʵ������������������
			new MyClientReader(dis,s1).start();
			new MyClientWriter(dos).start();
			// �����쳣
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void login(DataOutputStream dos,DataInputStream dis) {
		System.out.println("�������û�����");
		name = in.next().trim();
		System.out.println("���������룺");
		String pwd = in.next().trim();
		try {
			dos.writeUTF("0-" + name + "-" + pwd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void add(DataOutputStream dos,DataInputStream dis) {
		System.out.println("�������û�����");
		name = in.next().trim();
		System.out.println("���������룺");
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
		System.out.println("��������ˣ�");
		String refname = in.next().trim();
		System.out.println("����Ҫ���͵���Ϣ��");
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