package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
//����һ�������������н��ն�ȡ����
public class MyClientReader  extends  Thread {
	private DataInputStream dis;
	private Socket s=null;
	public MyClientReader(DataInputStream dis,Socket s1) {
		this.dis = dis;
		this.s=s1;
	}

	@Override
	public void run() {
		String msg;
		try {
			while (true) {
				msg = dis.readUTF();
				
				System.out.println("��"+s.getLocalPort()+") ��" + msg);
				if (msg.equals("bye")) {
					System.out.println("����������,�����˳�");
					System.exit(0);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	}  
