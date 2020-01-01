package assignments.computernetworks.tcpchecksum;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static Socket socket;
	private TCPData tcp;
	
	public static void main(String[] args) throws IOException{
		try{
			String ip = "127.0.0.1";
			int port = 1154;
			socket = new Socket(ip,port);
			Client c = new Client();
			
			
			Thread t2 = new Thread(new Runnable(){
				@Override
				public void run(){
					c.receiveData();
				}
			});
		
			t2.start();
		}catch(UnknownHostException ex){
			System.out.println("Please Enter a Correct IP Address");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Please Enter a IP Address eg. Client 192.168.0.7");
		}
		
	}
	
	public void receiveData(){
		try{
			ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream());
			tcp = (TCPData) objIn.readObject();
			while(tcp.isKeepAlive()){ 			
				System.out.println("client side data:"+tcp.getData()+" checksum:"+tcp.getChecksum());
				tcp.verifyChecksum();
				tcp.setKeepAlive(false);					
			}
				
			System.out.println("CONNECTION TERMINATED WITH "+socket.getInetAddress().toString());
			socket.close();
		}catch(Exception e){
			System.out.println("Exception:"+e);
		}
	}
	
}
