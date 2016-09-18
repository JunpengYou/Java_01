import java.net.*;
import java.io.*;

public class Server extends Thread {
	static ServerSocket serverSocket=null;
	Socket clientSocket=null;
	static int count=1;
	private int clientID;
	
	private Server(Socket clientSoc){
		clientSocket=clientSoc;
		clientID=count++;
		start();
	}
	
	public void run(){
		BufferedReader in;
		PrintWriter out;
		String inputLine;
		try{
			out=new PrintWriter(clientSocket.getOutputStream(),true);
			in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			while((inputLine=in.readLine())!=null){
				System.out.println("从client "+clientID+"处收到信息:"+inputLine);
				String s="";
				for(int i=inputLine.length()-1;i>=0;i--){
					s=s+inputLine.charAt(i);
				}
				out.println(s);
				if(inputLine.equals("bye")){
					break;
				}
			}
			System.out.println("再见！client "+clientID+"!");
			out.close();
			in.close();
			clientSocket.close();
		}catch(Exception e){
			System.exit(1);
		}
	}
	
	public static void main(String[] args){
		System.out.println("服务器启动...");
		try{
			serverSocket=new ServerSocket(3333);
			while(true){
				new Server(serverSocket.accept());
			}
		}catch(Exception e){
			System.out.println("服务终止...");
			System.exit(1);
		}
	}
	
}
