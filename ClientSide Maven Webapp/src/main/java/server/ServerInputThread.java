package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerInputThread extends Thread{
	 Socket socket = null;  
	    public ServerInputThread(Socket insocket) {  
	        socket = insocket;  
	        
	    }  
	  
	    public void run() {  
	        try {  
	            while(true){  
	                // ���������  
	            	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                String infor = null;  
	                while((infor=in.readLine())!=null){  
	                    System.out.println("��������������յ�һ����Ϣ--"+infor);  
	                }  
	            }  
	        } catch (IOException e) {  
	           System.out.println("�������Ͽ�����...");
	        } finally {  
	            try {  
	               /* socket.shutdownInput();  
	                if (br != null) br.close();  
	                if (isr != null) isr.close();  
	                if (is != null) is.close();  
	                if (socket != null) socket.close();  */
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	}  
