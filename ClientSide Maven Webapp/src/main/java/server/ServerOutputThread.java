package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerOutputThread extends Thread {
	Socket socket = null;
	public ServerOutputThread(Socket outsocket) {
		socket = outsocket;
	}

	public void run() {
		try {
			// 获取字节输出流，并转换成字符输出流
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out=new PrintWriter(socket.getOutputStream());
			// 从键盘输入字符串
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("服务器输出流：请输入要发送的信息：");
				String s=in.readLine();

				System.out.println(s);

				out.println("has received……");

				out.flush();
			}

		} catch (IOException e) {
			  System.out.println("服务器断开连接...");
		} finally {
			try {
				//socket.shutdownOutput();
				/*if (bw != null)
					bw.close();
				if (osw != null)
					osw.close();
				if (os != null)
					os.close();
				if (socket != null)
					socket.close();*/
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
