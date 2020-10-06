package ClientTools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

//用户端接收端口为8092
public class Massager {
	
	//receive
	private int MsgType;
	private Socket recSocket=null;
	private ServerSocket ss=null;
	private BufferedReader br=null;
	private InputStream is=null;
	private String[] msg=null;
	private String BirDay,Sex;
	

	//send
	private int SendPort;
	private String SendHost=null,request=null;
	private OutputStream os=null;//socket1.getOutputStream();
	private BufferedWriter bw=null;//new BufferedWriter(new OutputStreamWriter(os1));//和socket1绑定
	private Socket sendSocket=null;
	private String name=null,status,FStr,MsgText;


	private String ID,FID;
	
	//receive
	public void getMassage(){
		//accept阻塞
		try {
			System.out.println("m:recSocket等待获取结果...");
			ss=new ServerSocket(8092);
			recSocket=ss.accept();
			is=recSocket.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			//用#分割
			msg=br.readLine().split("#");
			System.out.println("m:收到原始信息:");//测试信息
			for(int i=0;i<msg.length;i++) {
				System.out.println("->msg["+i+"]:"+msg[i]);
			}
			System.out.println(msg[0]);//测试信息
			if(msg[0].equals("123")){
				//"登陆成功"类型
				setMsgType(1);
				System.out.println("m:收到登陆成功信息");//测试信息
			}
			else if(msg[0].equals("124")) {
				//"注册成功"类型
				setMsgType(2);
				System.out.println("m:收到注册成功信息");//测试信息
			}
			else if(msg[0].equals("998")) {
				//"注册失败"类型
				setMsgType(-2);
				System.out.println("m:收到注册失败信息");//测试信息
			}
			else if(msg[0].equals("4")) {
				//"得到昵称状态生日好友列表"类型
				//头0-昵称1-状态2-生日3-好友4
				setMsgType(4);
				setName(msg[1]);
				setStatus(msg[2]);
				setSex(msg[3]);
				setBirDay(msg[4]);
				if(msg.length>=6) {
					setFStr(msg[5]);
				}
				else
					setFStr(null);
				System.out.println("m:收到我的个人信息");//测试信息
			}
//			else if(msg[0].equals("125")) {//弃用
//				//"添加好友成功"类型
//				setMsgType(125);
//				setFID(msg[1]);
//				System.out.println("m:收到\"添加好友成功\"类型");//测试信息
//			}
//			else if(msg[0].equals("126")) {//弃用
//				//"删除好友成功"类型
//				setMsgType(126);
//				setFID(msg[1]);
//				System.out.println("m:收到\"删除好友成功\"类型");//测试信息
//			}
			else if(msg[0].equals("997")) {
				//"添加好友失败"类型
				setMsgType(997);
				System.out.println("m:收到\"添加好友失败\"类型");//测试信息
			}
			else if(msg[0].equals("127")) {
				//"更新好友ID"类型
				setMsgType(127);
				setFStr(msg[1]);
				System.out.println("m:收到更新好友ID的类型");//测试信息
			}
			else if(msg[0].equals("128")) {
				//"被动更新好友ID"类型
				setMsgType(128);
				if(msg[1].equals(" ")) {
					setFStr(null);
					System.out.println("m:好友列表为空setFStr改为null?="+getFStr());
				}else {
					setFStr(msg[1]);
				}//css
				System.out.println("m:收到被动更新好友ID的类型");//测试信息
			}
			else if(msg[0].equals("995")) {
				setMsgType(995);
			}
			else if(msg[0].equals("6")) {
				//一般消息
				setMsgType(6);
				setID(msg[1]);
				setMsgText(msg[2]);
			}
			else{
				System.out.println("m:收到未设置的类型：msg[0]="+msg[0]+"!!");
			}
			br.close();
			is.close();
			recSocket.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void setMsgType(int type){
		this.MsgType=type;
	}
	public int getMsgType() {
		return this.MsgType;
	}
	private void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	private void setStatus(String i) {
		this.status=i;
	}
	public String getStatus() {
		return this.status;
	}
	public String getFStr() {
		return FStr;
	}
	public void setFStr(String fStr) {
		FStr = fStr;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFID() {
		return FID;
	}
	public void setFID(String fID) {
		FID = fID;
	}
	public String getBirDay() {
		return BirDay;
	}
	public void setBirDay(String birDay) {
		BirDay = birDay;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getMsgText() {
		return MsgText;
	}
	public void setMsgText(String msgText) {
		MsgText = msgText;
	}
	
	//send
	public void MsgSender() {
		init();
	}
	private void init() {
		SendHost="192.168.10.5";//服务器地址
		SendPort=8091;
	}
	public boolean sendLogin(String id,String pwd){
		//登录
		boolean b=false;
		InetAddress addr;
		String myIPAddress=null;
		try {
			//自己的IP地址
			addr = InetAddress.getLocalHost();
			myIPAddress=addr.getHostAddress();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		//如果id非空
		if(id.equals("")||pwd.equals(""))return b;
		request="1";//登录请求代号"1"
		b=SendMsg(request+"#"+id+"#"+pwd+"#"+myIPAddress+"#");
		System.out.println(request+"#"+id+"#"+pwd+"#");//测试信息
		System.out.println("m:以上登陆信息已发送，等待检验");//测试信息
		return b;
	}
	public boolean sendRegistered(String id,String name,String pwd,int sex,String birDay) {
		boolean b=false;
		request="2";//注册请求代号"2"
		b=SendMsg(request+"#"+id+"#"+name+"#"+pwd+"#"+sex+"#"+birDay+"#");
		System.out.println(request+"#"+id+"#"+name+"#"+pwd+"#"+sex+"#"+birDay+"#");//测试信息
		if(b)System.out.println("m:以上注册信息已发送，等待回复");//测试信息
		else System.out.println("m:以上注册信息发送失败");//测试信息
		return b;
	}
	public boolean SendOffline(String id) {
		boolean b=false;
		request="3";//下线请求代号"3"
		b=SendMsg(request+"#"+id+"#");
		return b;
	}
	public void SendGetNameStatus(String id) {
		request="4";//获取昵称请求代号"4"
		SendMsg(request+"#"+id+"#");
	}
	public void AddDelFriend(String Id,String friendId,String request_) {
	//	request="5";
		//"添加好友"请求代号"5"//"删除好友"请求代号"8"
		SendMsg(request_+"#"+Id+"#"+friendId+"#");
	}
	public void SendCommonMsg(String Id, String friendId,String msgStr) {
		request="6";//"一般信息"代号"6"
		SendMsg(request+"#"+Id+"#"+friendId+"#"+msgStr+"#");
	}
	public boolean sendChangeInfo(String id,String name,int sex,String birDay) {
		//修改个人信息
		boolean b=false;
		request="7";//修改请求代号"7"
		b=SendMsg(request+"#"+id+"#"+name+"#"+sex+"#"+birDay+"#");
		System.out.println(request+"#"+id+"#"+name+"#"+sex+"#"+birDay+"#");//测试信息
		if(b)System.out.println("m:以上修改信息已发送！");//测试信息
		else System.out.println("m:以上修改信息发送失败");//测试信息
		return b;
	}
	public void SendGetAllFriendId(String id) {
		//"获取好友ID"-9
		request="9";
		SendMsg(request+"#"+id+"#");
	}
	
	
	/*
	 * 请把新的方法写在SendMsg之前
	 * */
	
	private boolean SendMsg(String Msg) {
		boolean b=false;
		try {
			//主要代码
			sendSocket=new Socket(SendHost,SendPort);//设置要连接的服务器地址和端口8091
			os=sendSocket.getOutputStream();
			bw=new BufferedWriter(new OutputStreamWriter(os));//和socket1绑定
			bw.write(Msg);//具体发送的内容
			bw.flush();
			b=true;
			bw.close();
			os.close();
			sendSocket.close();
		} catch (UnknownHostException e) {
			System.out.println("m:*无法识别主机的名字或IP地址！-UnknownHostException");
		} catch (IOException e) {
			System.out.println("m:*服务器未在线！"+new TIMEer().getTIME());
		}
		return b;
	}
	
}
