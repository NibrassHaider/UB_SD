import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	
	ServerSocket server;
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	BufferedReader br;

	String user;
	
	Thread thread;
	
	Server(){
		try {
			this.server = new ServerSocket(3003);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.socket = this.server.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.out = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.in = new DataInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.br = new BufferedReader(new InputStreamReader(System.in));
		thread = new Thread(this);
		thread.start();
		
		while(true) {
			try {
				System.out.println(in.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	public static void main(String[] args) {
		new Server();

	}


	@Override
	public void run() {
		while(true) {
			try {
				user = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.writeUTF(user);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
