package mangerSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
//定义客户端读数据线程类
class ClientReadThread extends Thread{
	Socket socket;  //套接字
	BufferedReader br;  //读数据流
	MessageDialog dialog; //消息窗体
	public ClientReadThread(Socket socket,MessageDialog dialog){
		this.socket = socket;
		this.dialog = dialog;
		try {
			//创建读数据流对象（接受服务器端发送的数据）
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run(){
		try{
		  while(true){//接受数据，并显示
				String s=br.readLine();
				dialog.textArea.append(s+"\n");
		    }
		  }catch(Exception e){e.printStackTrace();}
		  finally{
			  try {
				socket.close();br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
//客户端类，
public class MessageClient {
   int port = 8001; //服务器端口号
   MessageDialog dialog; //消息窗口
   BufferedReader br;//读数据流
   PrintWriter pw;//写数据流（发送数据给服务器）
   Socket socket;//套接字
   public MessageClient(MessageDialog dialog){
	   this.dialog = dialog;
	   try {//创建套接字
			socket = new Socket(InetAddress.getLocalHost(),port);
			//创建并启动读数据线程
			new ClientReadThread(socket,dialog).start();
		} catch (Exception e) {
			e.printStackTrace();
	 } 	
   }
  //点击发送按钮，完成发送数据的操作
   public void send(){
	  try {
		if(!dialog.textArea_1.getText().equals("")){
		  // socket = new Socket(InetAddress.getLocalHost(),port);
		   pw = new PrintWriter(socket.getOutputStream());
		   String s=MainFrame.userName+":"+dialog.textArea_1.getText();				   ;
		   pw.println(s);
		   pw.flush();
		   dialog.textArea.append(s+"\n");
		   dialog.textArea_1.setText("");
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	   
   }
}
