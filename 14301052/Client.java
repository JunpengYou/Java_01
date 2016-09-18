import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args){
		Socket cSocket=null;
		PrintWriter out=null;
		BufferedReader in=null;
		String userinput;
		try{
			cSocket=new Socket("127.0.0.1",3333);
			out=new PrintWriter(cSocket.getOutputStream(),true);
			in=new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
			while((userinput=stdIn.readLine())!=null){
				System.out.println("发送信息："+userinput);
				out.println(userinput);
				System.out.println("收到信息："+in.readLine());
				if(userinput.equals("bye")){
					break;
				}
			}
			System.out.println("程序退出...");
			out.close();
			in.close();
			stdIn.close();
			cSocket.close();
		}catch(Exception e){
			System.exit(1);
		}
	}
	
}
