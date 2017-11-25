import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client01 {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostExcept
	 */
	private static String host = "localhost";
	private static int port = 8888;
	static Socket server;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		server = new Socket(host, port);
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		PrintWriter out = new PrintWriter(server.getOutputStream());
		BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
		
		while (true){
			String s = wt.readLine();
			out.println(s);
			out.flush();
			if (s.equals("end"))
			{
				break;
			
			}
			System.out.println(in.readLine());
		}
		server.close();
	}

}
