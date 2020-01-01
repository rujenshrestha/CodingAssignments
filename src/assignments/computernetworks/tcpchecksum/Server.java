package assignments.computernetworks.tcpchecksum;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	private static ServerSocket servSock;
	private static Socket socket;
	private TCPData tcp = new TCPData();

	public static void main(String[] args) throws IOException{
		
		int port = 1154;
		servSock = new ServerSocket(port);
		socket = servSock.accept();
		Server s = new Server();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run(){
				s.sendData();
			}
		}
		);
		
		t1.start();
		
	}
	
	public void sendData(){
		Scanner sc = new Scanner(System.in);
		String msg="";
		try{
			ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
			while(tcp.isKeepAlive()){
				msg = sc.nextLine();
				tcp.setData(msg);
				tcp.setChecksum(tcp.computeChecksum(msg));
				System.out.println("server side data:"+tcp.getData()+" checksum:"+tcp.getChecksum());
				objOut.writeObject(tcp);
				objOut.flush();					
			}
			sc.close();
			objOut.close();
			socket.close();
			System.out.println("CONNECTION TERMINATED WITH "+socket.getInetAddress().toString());
			
		}catch(Exception e){
			System.out.println("Exception:"+e);
		}
	}
}