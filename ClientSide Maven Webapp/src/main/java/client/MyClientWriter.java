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
//创建一个进程用来写入并发送数据
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
					System.out.println("客户端下线,程序退出");
					System.exit(0);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	}  