import netscape.javascript.JSObject;
import java.applet.Applet;
import java.awt.*;
import java.net.*;
import java.io.*;

/*
	編譯：javac -cp "C:\Program Files (x86)\Java\jre7\lib\plugin.jar" socket_server_udp.java
*/

public class socket_server_udp extends Applet {
	public void init(){
		this.runServer();
	}

	public void stop(){ }

	public void paint(Graphics g) { }

	public void runServer(){
		final int SIZE = 8192;                    // 設定最大的訊息大小為 8192.
		byte buffer[] = new byte[SIZE];            // 設定訊息暫存區
		for (int count = 0; ; count++) {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			// 設定接收的 UDP Socket.
			DatagramSocket socket = new DatagramSocket(5000);
			// 接收封包。
			socket.receive(packet);                                
			// 將接收訊息轉換為字串。
			String msg = new String(buffer, 0, packet.getLength());
			// 印出接收到的訊息。
			System.out.println(count+" : receive = "+msg);
			// 關閉 UDP Socket.
			socket.close();
		}
	}

	// public void runServer(String msg){
	// 	JSObject win = JSObject.getWindow(this);
	// 	JSObject doc = (JSObject)win.getMember("document");

	// 	// from Java call JavaScript Method
	// 	// in JavaScript: window.eval("dosome()") 等於 eval("dosome()") 也能執行
	// 	win.eval("dosome('"+msg+"')");
	// }
}
