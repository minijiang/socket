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
			// ��ȡ�ֽ����������ת�����ַ������
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out=new PrintWriter(socket.getOutputStream());
			// �Ӽ��������ַ���
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("�������������������Ҫ���͵���Ϣ��");
				String s=in.readLine();

				System.out.println(s);

				out.println("has received����");

				out.flush();
			}

		} catch (IOException e) {
			  System.out.println("�������Ͽ�����...");
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
