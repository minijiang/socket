package test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Handler;

import entity.User;

public class ChatServer {
	private Socket socket;
	private Handler handler;
	private Message messageBean;
   Scanner  in = new Scanner(System.in);
	// 由Socket对象得到输出流，并构造PrintWriter对象
	PrintWriter printWriter;
	InputStream input;
	OutputStream output;
	DataOutputStream dataOutputStream;
	
	    public static void main(String[] args) //4
	        {
	      new ChatServer(8888);
	    }
	   
	public ChatServer(int status) {
		initMessage(status);
		initChatServer();
	while(true){
	  sendMessage(in.next());
	}		
	}

	/**
	 * 消息队列，用于传递消息
	 * 
	 * @param handler
	 */
	public void setChatHandler(Handler handler) {
		this.handler = handler;
	}

	private void initChatServer() {
		// 开个线程接收消息
		receiveMessage();
	}

	/**
	 * 初始化用户信息
	 */
	private void initMessage(int status) {
		messageBean = new Message();
		User userInfoBean = new User();
		messageBean.setMessageId(1);
		messageBean.setChatType(1);
		userInfoBean.setUserName("admin");
		userInfoBean.setupwd("123123123a");
		// 以下操作模仿当用户点击了某个好友展开的聊天界面，将保存用户id和聊天目标用户id
		if (status == 1) {// 如果是用户1，那么他就指向用户2聊天
			messageBean.setUserId(1);
			messageBean.setFriendId(2);
		} else if (status == 2) {// 如果是用户2，那么他就指向用户1聊天
			messageBean.setUserId(2);
			messageBean.setFriendId(1);
		}
	}

	/**
	 * 发送消息
	 * 
	 * @param contentMsg
	 */
	public void sendMessage(String contentMsg) {
		try {
			if (socket == null) {
		
				System.out.println("服务器已经关闭");
				return;
			}
			byte[] str = contentMsg.getBytes("utf-8");// 将内容转utf-8
			String aaa = new String(str);
			messageBean.setContent(aaa);
			String messageJson = messageBean.toString();
			/**
			 * 因为服务器那边的readLine()为阻塞读取 如果它读取不到换行符或者输出流结束就会一直阻塞在那里
			 * 所以在json消息最后加上换行符，用于告诉服务器，消息已经发送完毕了
			 * */
			messageJson += "\n";
			output.write(messageJson.getBytes("utf-8"));// 换行打印
			output.flush(); // 刷新输出流，使Server马上收到该字符串
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "错误：" + e.toString());
		}
	}

	/**
	 * 接收消息，在子线程中
	 */
	private void receiveMessage() {
		new Thread(new Runnable() {
			public void run() {
				try {
					// 向本机的8080端口发出客户请求
					socket = new Socket("localhost", 8888);
					// 由Socket对象得到输入流，并构造相应的BufferedReader对象
					printWriter = new PrintWriter(socket.getOutputStream());
					input = socket.getInputStream();
					output = socket.getOutputStream();
					dataOutputStream = new DataOutputStream(
							socket.getOutputStream());
					// 从客户端获取信息
					BufferedReader bff = new BufferedReader(
							new InputStreamReader(input));
					// 读取发来服务器信息
					String line;
					while (true) {
						Thread.sleep(500);
						// 获取客户端的信息
						while ((line = bff.readLine()) != null) {
							System.out.println("内容 : " + line);
						}
						if (socket == null)
							break;
					}
					output.close();// 关闭Socket输出流
					input.close();// 关闭Socket输入流
					socket.close();// 关闭Socket
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("错误：" + e.toString());
				}
			}
		}).start();
	}

	public Socket getSocekt() {
		if (socket == null)
			return null;
		return socket;
	}
}