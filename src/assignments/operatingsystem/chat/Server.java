package assignments.operatingsystem.chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	private static ServerSocket servSock;
	private static Socket socket;

	public static void main(String[] args) throws IOException{
		
		int port = 1123;
		servSock = new ServerSocket(port);
		socket = servSock.accept();
		Server s = new Server();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run(){
				s.sendMsg();
			}
		}
		);
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run(){
				s.receiveMsg();
			}
		}
		);
		
		t1.start();
		t2.start();
		
	}
	
	public void receiveMsg(){
		
		try{
			Scanner sc = new Scanner(socket.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = "";
			while(!msg.equals("exit")){ 
					msg = br.readLine();
					System.out.println(msg);	
			}
			System.out.println("CONNECTION TERMINATED WITH "+socket.getInetAddress().toString());
			br.close();
			sc.close();
			socket.close();
		}catch(IOException ioErr){
			System.out.println("IOException:"+ioErr);
		}
	}
	
	public void sendMsg(){
		String msg = "";
		Scanner sc = new Scanner(System.in);
		try{
			PrintStream print = new PrintStream(socket.getOutputStream());
			while(!msg.equals("exit")){
				msg = sc.nextLine();		
				print.println(msg);
				print.flush();
			}
			System.out.println("CONNECTION TERMINATED WITH "+socket.getInetAddress().toString());
			sc.close();
			print.close();
			socket.close();
		}catch(IOException ioErr){
			System.out.println("IOException:"+ioErr);
		}
	}
}