import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client1 {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostExcept
	 */
	static Scanner input = new Scanner(System.in);
	private static String host = "localhost";
	private static int port = 8888;


	

	public static void main(String[] args) throws Exception {
		System.out.println("请输入用户名：");
		String key = input.next().trim();
		System.out.println("请输入密码：");
		String value = input.next();
		if(key!= null && value != null){
			String login = "login-"+key+"-"+value;
			Socket socket  = new Socket(host,port);
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter out=new PrintWriter(socket.getOutputStream());
			BufferedReader wt=new BufferedReader(new InputStreamReader(System.in));

			while(true){
			String s=wt.readLine();
			out.println(s);
			out.flush();
			System.out.println(in.readLine());
			}
		}

	}
}
