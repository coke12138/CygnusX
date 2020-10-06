package ServerTools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MsgSender {

	private Socket s=null;
	private BufferedWriter bw=null;
	private OutputStream os=null;
	private String host=null;
	private int port;
	
	public MsgSender() {
		init();
	}
	public MsgSender(String hosT) {
		host=hosT;
		port=8092;
	}
	private void init() {
		host="192.168.10.22";//默认IP
		port=8092;//客户端接收端口
	}
	public void SenderLoginSucceedResult() {
		SendResult("123#");//成功登录代号123
	}
	public void SenderLoginFailedResult() {
		SendResult("999#");//失败登录代号999
	}
	public void SenderRegisteredSucceedResult() {
		SendResult("124#");//注册成功代号124
	}
	public void SenderRegisteredFailedResult() {
		SendResult("998#");//注册失败代号998
	}
	public void setServerHost(String host) {
		this.host=host;
	}
	private void SendResult(String Result) {
		try {
			s=new Socket(host,port);
			os=s.getOutputStream();
			bw=new BufferedWriter(new OutputStreamWriter(os));
			bw.write(Result);//代号
			bw.flush();
			bw.close();
			os.close();
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
