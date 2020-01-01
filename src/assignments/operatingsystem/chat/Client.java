package assignments.operatingsystem.chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.net.UnknownHostException;

public class Client {
	private static Socket socket;

	public static void main(String[] args) throws IOException{
		try{
			String ip = args[0];
			int port = 1123;
			socket = new Socket(ip,port);
			Client c = new Client();
			
			Thread t1 = new Thread(new Runnable(){
				@Override
				public void run(){
					c.sendMsg();
				}
			});
			
			Thread t2 = new Thread(new Runnable(){
				@Override
				public void run(){
					c.receiveMsg();
				}
			});
		
			t1.start();
			t2.start();
		}catch(ConnectException conErr){			
			System.out.println("Connection Refused from the Server");
		}catch(UnknownHostException ex){
			System.out.println("Please Enter a Correct IP Address");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Please Enter a IP Address eg. Client 192.168.0.7");
		}
		
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
