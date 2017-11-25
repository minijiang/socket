package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
//����һ����������д�벢��������
public class MyClientWriter extends Thread {
	private DataOutputStream dos;
	public MyClientWriter(DataOutputStream dos) {
		this.dos = dos;
	}

	@Override
	public void run() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String msg;
		try {
			while (true) {
				msg = br.readLine();
				dos.writeUTF(msg);
				dos.flush();
				if (msg.equals("bye")) {
					System.out.println("�ͻ�������,�����˳�");
					System.exit(0);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	}  