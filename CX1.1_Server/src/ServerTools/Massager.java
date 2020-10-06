package ServerTools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
//服务器接收端口8091
public class Massager {
	/*
	 * 这是一个用来收发信息的类
	 * */

	//receive
	private int MsgType;
	private Socket s=null;
	private ServerSocket ss=null;
	private BufferedReader br=null;
	private InputStream is=null;
	private String[] msg=null;
	private String ID=null,Pwd=null,birDay=null,name=null,FID;
	
	private String ClientIPaddress=null;
	private int sex;
	
	//send
	private BufferedWriter bw=null;
	private OutputStream os=null;
	private int sendPort;
	private String msgText=null;
	
	//receive	
	public Massager MsgReceiver() {
		//接收信息初始化
		try {
			while(true){
				ss=new ServerSocket(8091);
				System.out.println("\nm:Server正在监听");
				s=ss.accept();
				//得到客户端ip地址
		//		ClientIPaddress=s.getInetAddress().getHostAddress();
				System.out.println("m:客户端的IP地址-"+ClientIPaddress);
				is=s.getInputStream();
				br=new BufferedReader(new InputStreamReader(is));
				//用#分割
				msg=br.readLine().split("#");
				
				System.out.println("m:收到原始信息:");//测试信息
				for(int i=0;i<msg.length;i++) {
					System.out.println("->msg["+i+"]:"+msg[i]);
				}
				
				//根据头设定信息种类
				if(msg[0].equals("1")){
					//"登录"类型
					setMsgType(1);
					//存储分割的字段
					setIDPwd(msg[1],msg[2]);
					ClientIPaddress=msg[3];
					System.out.println("m:客户端的IP地址-"+ClientIPaddress);
					System.out.println("m:收到1登陆类型");//测试信息
				}
				else if(msg[0].equals("2")) {
					//"注册"类型
					setMsgType(2);
					//存储分割的字段
					setIDPwd(msg[1],msg[3]);
					setName(msg[2]);
					setSex(Integer.valueOf(msg[4]));
					String str=null;
					//msg数组生日字段下标是5、6、7.
					int i=1;
					//存储出生日期
					if(!msg[5].equals("null")) {
						//如果非空
						while(msg.length-5>=i) {
							str+=msg[i+4]+"-";
							i++;
						}
						for(int j=0;j<4-i;j++) {
							//补全生日字段
							str+="1-";
						}
						setbirDay(str);
					}
					else {
						//如果是空的
						setbirDay(null);
					}
					System.out.println("m:收到2注册类型");//测试信息
				}
				else if(msg[0].equals("3")) {
					//"下线"类型
					setMsgType(3);
					setID(msg[1]);
				}
				else if(msg[0].equals("4")) {
					//"得到个人信息"类型
					setMsgType(4);
					setID(msg[1]);
				}
				else if(msg[0].equals("5")) {
					//"添加好友"类型
					setMsgType(5);
					setID(msg[1]);
					setFID(msg[2]);
				}
				else if(msg[0].equals("6")) {
					//"发送一般信息"类型
					setMsgType(6);
					setID(msg[1]);
					setFID(msg[2]);
					setMsgText(msg[3]);
				}
				else if(msg[0].equals("7")) {
					//"修改个人信息"类型
					//id-昵称-性别-生日
					setMsgType(7);
					setID(msg[1]);
					setName(msg[2]);
					int sexTemp;
					if(msg[3].equals("0")) {
						sexTemp=0;
					}
					else if(msg[3].equals("1")){
						sexTemp=1;
					}
					else
						sexTemp=-10;
					System.out.println("MSG:msg[3]="+msg[3]+" sexTemp="+sexTemp);
					setSex(sexTemp);
					//生日
					String str="";
					//msg数组生日字段下标是456.
					int i=1;
					//存储出生日期
					if(!msg[4].equals("null")) {
						//如果非空
						while(msg.length-4>=i) {
							str+=msg[i+3]+"-";
							i++;
						}
						for(int j=0;j<3-i;j++) {
							//补全生日字段
							str+="1-";
						}
						setbirDay(str);
					}
					else {
						//如果是空的
						setbirDay(null);
					}
				}
				else if(msg[0].equals("8")) {
					//"删除好友"类型
					setMsgType(8);
					setID(msg[1]);
					setFID(msg[2]);
				}
				else if(msg[0].equals("9")) {
					//"得到好友ID"类型
					setMsgType(9);
					setID(msg[1]);
				}
				else{
					System.out.println("m:收到其他未识别类型:msg[0]="+msg[0]);
				}
				
				br.close();
				is.close();
				s.close();
				ss.close();
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	public int getMsgType(){
		return this.MsgType;
	}
	public void setMsgType(int type){
		this.MsgType=type;
	}
	private void setIDPwd(String id,String pwd) {
		this.ID=id;
		this.Pwd=pwd;
	}
	private void setID(String id) {
		this.ID=id;
	}
	public String getID() {
		return this.ID;
	}
	public String getPwd() {
		return this.Pwd;
	}
	public String getClientIPaddress() {
		return this.ClientIPaddress;
	}
	private void setbirDay(String str) {
		this.birDay=str;
	}
	public String getBirday() {
		return this.birDay;
	}
	private void setName(String str) {
		this.name=str;
	}
	public String getName() {
		return this.name;
	}
	private void setSex(int sex) {
		this.sex=sex;
	}
	public int getSex() {
		return this.sex;
	}
	public String getFID() {
		return FID;
	}
	public void setFID(String fID) {
		FID = fID;
	}
	public String getMsgText() {
		return msgText;
	}
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	
	
	//send
	public Massager MsgSender() {
		//初始化接口
		sendPort=8092;
		return this;
	}
	public void SendLoginResult(boolean b) {
		if(b) {
			SendResult("123#");//成功登录代号123
			System.out.println("m:登录*成功*");//测试信息
		}else {
			SendResult("999#");//失败登录代号999
			System.out.println("m:登陆失败");//测试信息
		}
		
	}
	public void SendRegisteredResult(boolean b) {
		if(b) {
			SendResult("124#");//注册成功代号124
			System.out.println("m:注册*成功*");//测试信息
		}else {
			SendResult("998#");//注册失败代号998
			System.out.println("m:注册失败");//测试信息
		}
		
	}
	public void UpDateInfo(String name,String Status,String Sex,String BirDay,String FStr) {
		//好友列表不为空
		SendResult("4#"+name+"#"+Status+"#"+Sex+"#"+BirDay+"#"+FStr+"#");//获取名字和状态代号4
		System.out.println("m:发送了name-status-好友列表\n ->"+"4#"+name+"#"+Status+"#"+Sex+"#"+BirDay+"#"+FStr+"#");//测试信息
	}
	public void UpDateInfo(String name,String Status,String Sex,String BirDay) {
		//好友列表为空
		SendResult("4#"+name+"#"+Status+"#"+Sex+"#"+BirDay+"#");//获取名字和状态代号4
		System.out.println("m:发送了name-status\n ->"+"4#"+name+"#"+BirDay+"#"+Status+"#"+Sex+"#");//测试信息
	}
	public void UpDateFID(String FStr) {
		//好友列表不为空才调用此方法//用于修改个人信息的调用
		SendResult("127#"+FStr+"#");//127-更新好友列表
		System.out.println("m:发送了更新好友列表:\n ->"+FStr);
	}
	
//	public void SendAddFriendResult(boolean b, String FID) {//弃用
//		//添加好友更新好友列表
//		if(b) {
//			SendResult("125#"+FID+"#");//添加好友成功
//		}
//		else
//			SendResult("997#");//添加好友失败
//	}
//	public void SendDelFriendResult(boolean b, String FID) {//弃用
//		//
//		if(b) {
//			SendResult("126#"+FID+"#");//删除好友成功
//		}
//		else
//			SendResult("996#");//删除好友失败
//	}
	public void SendCommonMsg(String id,String Fid,String FHostAddress,String MsgText) {
		//发送一般信息//只有好友在线才调用此方法
		//我的id-好友id-好友在线地址-信息内容
		//id对Fid说...
		SendMsg("6#"+id+"#"+MsgText+"#",FHostAddress);
	}
	public void SendCommonMsgFail(String id) {
		//告知发送失败的结果
		SendResult("995#");
	}
	public void SendNewFIDMsg(String FIDStr,String FHostAddress) {
		//被添加好友，被动更新好友列表//给在线的好友客户端发送新的好友列表
		SendMsg("128#"+FIDStr+"#",FHostAddress);
	}
	
	//把新增发送写在这之前
	private void SendResult(String Result) {
		//返回结果
		try {
			s=new Socket(ClientIPaddress,sendPort);
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
	private void SendMsg(String Content,String ipaddress) {
		//转发信息需要内容、地址
		try {
			s=new Socket(ipaddress,sendPort);
			os=s.getOutputStream();
			bw=new BufferedWriter(new OutputStreamWriter(os));
			bw.write(Content);//代号
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
