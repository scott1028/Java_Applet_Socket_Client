import netscape.javascript.JSObject;
import java.applet.Applet;
import java.awt.*;
import java.net.*;
import java.io.*;

/*
	編譯：javac -encoding utf-8 -cp "C:\Program Files (x86)\Java\jre7\lib\plugin.jar" socket_client.java
*/

public class socket_client extends Applet {
	public void init(){
		try{
			Socket client = new Socket("127.0.0.1", 5000);
			System.out.println("Just connected to "+ client.getRemoteSocketAddress());

			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);

			byte[] b = new byte[512];	// [注意] 大小要算好如果送出包含 nil Byte 的 String, JavaScript 將不會執行
			while(true){
				in.read(b);
				String str=new String(b,"utf-8");					// Byte 轉 String
				str=str.replaceAll("\u0000.*", "");					// 一併移除 nil 字元

				// 當有長度大於 0 才印, 避免洗畫面
				if(str.length()>0){
					System.out.println( "[*]Console:"+str );		// 在終端機印出來
					runServer( str );								// 送一份給 JavaScript 端執行
				}
			}
			// client.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void stop(){ }

	public void paint(Graphics g) { }

	public void runServer(String msg){
		JSObject win = JSObject.getWindow(this);
		JSObject doc = (JSObject)win.getMember("document");

		// from Java call JavaScript Method
		// in JavaScript: window.eval("dosome()") 等於 eval("dosome()") 也能執行
		win.eval("dosome('"+msg+"')");
	}
}