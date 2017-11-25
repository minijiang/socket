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

	// socket服务
	private static ServerSocket server;
	// 使用ArrayList存储所有的Socket
	public List<Socket> socketList = new ArrayList();
	// 模仿保存在内存中的socket
	public Map<Integer, Socket> socketMap = new HashMap();
	// 模仿保存在数据库中的用户信息
	public Map<Integer, User> userMap = new HashMap<Integer, User>();

	/**
	 * 初始化socket服务
	 */
	
	public static void main(String[] args) {
		new Servers();
	}

	public Servers() {

		try {
			ServerSocket serversocket = new ServerSocket(8888);
			Socket socket = null;
			System.out.println("服务端即将监听8888端口，等待客户端接入...");
			// 循环监听
			while (true) {
				socket = serversocket.accept();
				if(!conut(socket)){
					socketList.add(socket);// 存入list集合中
					socketMap.put(1, socket); // 存入map集合中
				}
				
			
				System.out.println("服务器端：监听到客户端，分配输出流线程");
				ServerOutputThread serverOutputThread = new ServerOutputThread(socket);
				serverOutputThread.start();		
				System.out.println("服务器端：分配输入流线程");
		
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