package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;

import test.Message;

import com.google.gson.JsonObject;

import entity.User;
import feign.gson.GsonDecoder;

public class Servers {

	// socket����
	private static ServerSocket server;
	// ʹ��ArrayList�洢���е�Socket
	public List<Socket> socketList = new ArrayList();
	// ģ�±������ڴ��е�socket
	public Map<Integer, Socket> socketMap = new HashMap();
	// ģ�±��������ݿ��е��û���Ϣ
	public Map<Integer, User> userMap = new HashMap<Integer, User>();

	/**
	 * ��ʼ��socket����
	 */
	
	public static void main(String[] args) {
		new Servers();
	}

	public Servers() {

		try {
			ServerSocket serversocket = new ServerSocket(8888);
			Socket socket = null;
			System.out.println("����˼�������8888�˿ڣ��ȴ��ͻ��˽���...");
			// ѭ������
			while (true) {
				socket = serversocket.accept();
				if(!conut(socket)){
					socketList.add(socket);// ����list������
					socketMap.put(1, socket); // ����map������
				}
				
			
				System.out.println("�������ˣ��������ͻ��ˣ�����������߳�");
				ServerOutputThread serverOutputThread = new ServerOutputThread(socket);
				serverOutputThread.start();		
				System.out.println("�������ˣ������������߳�");
		
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean conut(Socket s) {
		if (socketList.contains(s)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean delsocket(Socket s) {
		if (socketList.contains(s)) {
			socketList.remove(s);
			return true;
		} else {
			return false;
		}
	}
}