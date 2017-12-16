package mangerSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
class ServerReadThread extends Thread{
	Socket socket; //套接字
	BufferedReader br; //读数据流对象
	MessageDialog dialog; //消息窗口
	ArrayList<Socket> clientList;//存储建立的连接套接字
	PrintWriter pw;//写数据流对象
	public ServerReadThread(Socket socket,
			ArrayList<Socket> clientList,MessageDialog dialog){
		this.dialog = dialog;
		this.socket = socket;
		this.clientList = clientList;
		try {
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {e.printStackTrace();}
	}
	public void run(){
		String s=null;
		try {//读客户端发送的数据
			s = br.readLine();
		} catch (IOException e1) {e1.printStackTrace();	}
		String userName = s.substring(0,s.indexOf(':'));
		//将上线用户加入到在线用户列表
		if(!dialog.userList.contains("userName")){
			dialog.userList.addElement(userName);
		}
		try{
			while(true){////往所有客户（除发送客户）发送信息
				for(Socket socket1:clientList){
					if(!socket.equals(socket1)){
					  pw = new PrintWriter(socket1.getOutputStream()); 
	                  pw.println(s);
	                  pw.flush();
					}
				}
				s = br.readLine();
			}
		}catch(Exception e){e.printStackTrace();}
		    finally{
			try {
				socket.close();br.close();pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
//服务器端类
class MessageServer extends Thread {
	private int port=8001;//绑定端口
	ServerSocket serverSocket; //ServerSocket对象
	ArrayList<Socket> clientList = null;//存储建立连接的套接字
	Socket clientSocket;  //套接字
	MessageDialog dialog;
		
	public MessageServer(MessageDialog dialog){
		try {
			this.dialog = dialog;
			serverSocket = new ServerSocket(port);//创建对象
			clientList = new ArrayList<Socket>();
			dialog.userList.addElement("监听端口："+port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run(){
		 try {
			 while(true){
			  clientSocket = serverSocket.accept();//建立连接
			  //将新的连接套接字加入套接字链表
			  if (!clientList.contains(clientSocket)){
					clientList.add(clientSocket);	
				}
			  //创建并启动服务器端读数据线程
			   new ServerReadThread(clientSocket,
					clientList,dialog).start();
			  } 
		    }catch (IOException e1) {
				e1.printStackTrace();
			}finally{
				try {
					serverSocket.close();
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}	
	 }
 
}
