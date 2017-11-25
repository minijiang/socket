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
	// ��Socket����õ��������������PrintWriter����
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
	 * ��Ϣ���У����ڴ�����Ϣ
	 * 
	 * @param handler
	 */
	public void setChatHandler(Handler handler) {
		this.handler = handler;
	}

	private void initChatServer() {
		// �����߳̽�����Ϣ
		receiveMessage();
	}

	/**
	 * ��ʼ���û���Ϣ
	 */
	private void initMessage(int status) {
		messageBean = new Message();
		User userInfoBean = new User();
		messageBean.setMessageId(1);
		messageBean.setChatType(1);
		userInfoBean.setUserName("admin");
		userInfoBean.setupwd("123123123a");
		// ���²���ģ�µ��û������ĳ������չ����������棬�������û�id������Ŀ���û�id
		if (status == 1) {// ������û�1����ô����ָ���û�2����
			messageBean.setUserId(1);
			messageBean.setFriendId(2);
		} else if (status == 2) {// ������û�2����ô����ָ���û�1����
			messageBean.setUserId(2);
			messageBean.setFriendId(1);
		}
	}

	/**
	 * ������Ϣ
	 * 
	 * @param contentMsg
	 */
	public void sendMessage(String contentMsg) {
		try {
			if (socket == null) {
		
				System.out.println("�������Ѿ��ر�");
				return;
			}
			byte[] str = contentMsg.getBytes("utf-8");// ������תutf-8
			String aaa = new String(str);
			messageBean.setContent(aaa);
			String messageJson = messageBean.toString();
			/**
			 * ��Ϊ�������Ǳߵ�readLine()Ϊ������ȡ �������ȡ�������з���������������ͻ�һֱ����������
			 * ������json��Ϣ�����ϻ��з������ڸ��߷���������Ϣ�Ѿ����������
			 * */
			messageJson += "\n";
			output.write(messageJson.getBytes("utf-8"));// ���д�ӡ
			output.flush(); // ˢ���������ʹServer�����յ����ַ���
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "����" + e.toString());
		}
	}

	/**
	 * ������Ϣ�������߳���
	 */
	private void receiveMessage() {
		new Thread(new Runnable() {
			public void run() {
				try {
					// �򱾻���8080�˿ڷ����ͻ�����
					socket = new Socket("localhost", 8888);
					// ��Socket����õ�����������������Ӧ��BufferedReader����
					printWriter = new PrintWriter(socket.getOutputStream());
					input = socket.getInputStream();
					output = socket.getOutputStream();
					dataOutputStream = new DataOutputStream(
							socket.getOutputStream());
					// �ӿͻ��˻�ȡ��Ϣ
					BufferedReader bff = new BufferedReader(
							new InputStreamReader(input));
					// ��ȡ������������Ϣ
					String line;
					while (true) {
						Thread.sleep(500);
						// ��ȡ�ͻ��˵���Ϣ
						while ((line = bff.readLine()) != null) {
							System.out.println("���� : " + line);
						}
						if (socket == null)
							break;
					}
					output.close();// �ر�Socket�����
					input.close();// �ر�Socket������
					socket.close();// �ر�Socket
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("����" + e.toString());
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