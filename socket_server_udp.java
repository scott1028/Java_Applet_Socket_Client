import netscape.javascript.JSObject;
import java.applet.Applet;
import java.awt.*;
import java.net.*;
import java.io.*;

/*
	編譯：javac -encoding utf-8 -cp "C:\Program Files (x86)\Java\jre7\lib\plugin.jar" socket_server_udp.java
*/

public class socket_server_udp extends Applet {
	public void init(){
		try{
			this.runServer();
		}
		catch(Exception e){
			// 因為該 Function 有 throws Exception
		}
	}

	public void stop(){ }

	public void paint(Graphics g) { }

	public void runServer() throws Exception {		// 有下 throw error 該執行敘述就要實做 Try-Catch
		// JavaScript 物件
		JSObject win = JSObject.getWindow(this);
		JSObject doc = (JSObject)win.getMember("document");

		// 設定最大的訊息大小為 8192.
		final int SIZE = 8192;

		// 設定訊息暫存區
		byte buffer[] = new byte[SIZE];

		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

		// 設定接收的 UDP Socket.
		DatagramSocket socket = new DatagramSocket(5001);

		// 持續接收
		while(true){
			// 接收封包。
			socket.receive(packet);
			// 將接收訊息轉換為字串。
			String msg = new String(buffer, 0, packet.getLength());
			// 印出接收到的訊息。

			// 當有長度大於 0 才印, 避免洗畫面
			if(msg.length()>0){
				System.out.println( "[*] receive = "+msg.replaceAll("\u0000.*", "") );
				win.eval("dosome('"+msg.replaceAll("\u0000.*", "")+"')");
			}
		}

		// 關閉 UDP Socket.
		// socket.close();		// 無法執行的不能寫
	}
}
