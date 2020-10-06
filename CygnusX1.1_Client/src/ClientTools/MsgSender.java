package ClientTools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MsgSender {
	private Socket s=null;
	private int port;
	private String host=null,request=null;
	private OutputStream os=null;//socket1.getOutputStream();
	private BufferedWriter bw=null;//new BufferedWriter(new OutputStreamWriter(os1));//和socket1绑定
	
	public MsgSender() {
		init();
	}
	private void init() {
		host="192.168.10.5";//服务器地址
		port=8091;
	}
	public boolean sendLogin(String id,String pwd){
		boolean b=false;
		//如果非空
		if(id.equals("")||pwd.equals(""))return b;
		request="1";//登录请求代号"1"
		b=SendMsg(request+"#"+id+"#"+pwd+"#");
		System.out.println(request+"#"+id+"#"+pwd+"#");//测试信息
		System.out.println("以上登陆信息已发送，等待检验");//测试信息
		return b;
	}
	public boolean sendRegistered(String id,String name,String pwd,int sex,String birDay) {
		boolean b=false;
		request="2";//注册请求代号"2"
		b=SendMsg(request+"#"+id+"#"+name+"#"+pwd+"#"+sex+"#"+birDay+"#");
		System.out.println(request+"#"+id+"#"+name+"#"+pwd+"#"+sex+"#"+birDay+"#");//测试信息
		if(b)System.out.println("以上注册信息已发送，等待回复");//测试信息
		else System.out.println("以上注册信息发送失败");//测试信息
		return b;
	}
	public boolean SendOffline(String id) {
		boolean b=false;
		request="3";//下线请求代号"3"
		b=SendMsg(request+"#"+id+"#");
		return b;
	}
	
	
	
	
	
	/*
	 * 请把新的方法写在SendMsg之前
	 * */
	
	private boolean SendMsg(String Msg) {
		boolean b=false;
		try {
			//主要代码
			s=new Socket(host,port);//设置要连接的服务器地址和端口8091
			os=s.getOutputStream();
			bw=new BufferedWriter(new OutputStreamWriter(os));//和socket1绑定
			bw.write(Msg);//具体发送的内容
			bw.flush();
			b=true;
			bw.close();
			os.close();
			s.close();
		} catch (UnknownHostException e) {
			System.out.println("sender:*无法识别主机的名字或IP地址！-UnknownHostException");
		} catch (IOException e) {
			System.out.println("sender:*服务器未在线！"+new TIMEer().getTIME());
		}
		return b;
	}
	
}
