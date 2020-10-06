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

//�û��˽��ն˿�Ϊ8092
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
	private BufferedWriter bw=null;//new BufferedWriter(new OutputStreamWriter(os1));//��socket1��
	private Socket sendSocket=null;
	private String name=null,status,FStr,MsgText;


	private String ID,FID;
	
	//receive
	public void getMassage(){
		//accept����
		try {
			System.out.println("m:recSocket�ȴ���ȡ���...");
			ss=new ServerSocket(8092);
			recSocket=ss.accept();
			is=recSocket.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			//��#�ָ�
			msg=br.readLine().split("#");
			System.out.println("m:�յ�ԭʼ��Ϣ:");//������Ϣ
			for(int i=0;i<msg.length;i++) {
				System.out.println("->msg["+i+"]:"+msg[i]);
			}
			System.out.println(msg[0]);//������Ϣ
			if(msg[0].equals("123")){
				//"��½�ɹ�"����
				setMsgType(1);
				System.out.println("m:�յ���½�ɹ���Ϣ");//������Ϣ
			}
			else if(msg[0].equals("124")) {
				//"ע��ɹ�"����
				setMsgType(2);
				System.out.println("m:�յ�ע��ɹ���Ϣ");//������Ϣ
			}
			else if(msg[0].equals("998")) {
				//"ע��ʧ��"����
				setMsgType(-2);
				System.out.println("m:�յ�ע��ʧ����Ϣ");//������Ϣ
			}
			else if(msg[0].equals("4")) {
				//"�õ��ǳ�״̬���պ����б�"����
				//ͷ0-�ǳ�1-״̬2-����3-����4
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
				System.out.println("m:�յ��ҵĸ�����Ϣ");//������Ϣ
			}
//			else if(msg[0].equals("125")) {//����
//				//"��Ӻ��ѳɹ�"����
//				setMsgType(125);
//				setFID(msg[1]);
//				System.out.println("m:�յ�\"��Ӻ��ѳɹ�\"����");//������Ϣ
//			}
//			else if(msg[0].equals("126")) {//����
//				//"ɾ�����ѳɹ�"����
//				setMsgType(126);
//				setFID(msg[1]);
//				System.out.println("m:�յ�\"ɾ�����ѳɹ�\"����");//������Ϣ
//			}
			else if(msg[0].equals("997")) {
				//"��Ӻ���ʧ��"����
				setMsgType(997);
				System.out.println("m:�յ�\"��Ӻ���ʧ��\"����");//������Ϣ
			}
			else if(msg[0].equals("127")) {
				//"���º���ID"����
				setMsgType(127);
				setFStr(msg[1]);
				System.out.println("m:�յ����º���ID������");//������Ϣ
			}
			else if(msg[0].equals("128")) {
				//"�������º���ID"����
				setMsgType(128);
				if(msg[1].equals(" ")) {
					setFStr(null);
					System.out.println("m:�����б�Ϊ��setFStr��Ϊnull?="+getFStr());
				}else {
					setFStr(msg[1]);
				}//css
				System.out.println("m:�յ��������º���ID������");//������Ϣ
			}
			else if(msg[0].equals("995")) {
				setMsgType(995);
			}
			else if(msg[0].equals("6")) {
				//һ����Ϣ
				setMsgType(6);
				setID(msg[1]);
				setMsgText(msg[2]);
			}
			else{
				System.out.println("m:�յ�δ���õ����ͣ�msg[0]="+msg[0]+"!!");
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
		SendHost="192.168.10.5";//��������ַ
		SendPort=8091;
	}
	public boolean sendLogin(String id,String pwd){
		//��¼
		boolean b=false;
		InetAddress addr;
		String myIPAddress=null;
		try {
			//�Լ���IP��ַ
			addr = InetAddress.getLocalHost();
			myIPAddress=addr.getHostAddress();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		//���id�ǿ�
		if(id.equals("")||pwd.equals(""))return b;
		request="1";//��¼�������"1"
		b=SendMsg(request+"#"+id+"#"+pwd+"#"+myIPAddress+"#");
		System.out.println(request+"#"+id+"#"+pwd+"#");//������Ϣ
		System.out.println("m:���ϵ�½��Ϣ�ѷ��ͣ��ȴ�����");//������Ϣ
		return b;
	}
	public boolean sendRegistered(String id,String name,String pwd,int sex,String birDay) {
		boolean b=false;
		request="2";//ע���������"2"
		b=SendMsg(request+"#"+id+"#"+name+"#"+pwd+"#"+sex+"#"+birDay+"#");
		System.out.println(request+"#"+id+"#"+name+"#"+pwd+"#"+sex+"#"+birDay+"#");//������Ϣ
		if(b)System.out.println("m:����ע����Ϣ�ѷ��ͣ��ȴ��ظ�");//������Ϣ
		else System.out.println("m:����ע����Ϣ����ʧ��");//������Ϣ
		return b;
	}
	public boolean SendOffline(String id) {
		boolean b=false;
		request="3";//�����������"3"
		b=SendMsg(request+"#"+id+"#");
		return b;
	}
	public void SendGetNameStatus(String id) {
		request="4";//��ȡ�ǳ��������"4"
		SendMsg(request+"#"+id+"#");
	}
	public void AddDelFriend(String Id,String friendId,String request_) {
	//	request="5";
		//"��Ӻ���"�������"5"//"ɾ������"�������"8"
		SendMsg(request_+"#"+Id+"#"+friendId+"#");
	}
	public void SendCommonMsg(String Id, String friendId,String msgStr) {
		request="6";//"һ����Ϣ"����"6"
		SendMsg(request+"#"+Id+"#"+friendId+"#"+msgStr+"#");
	}
	public boolean sendChangeInfo(String id,String name,int sex,String birDay) {
		//�޸ĸ�����Ϣ
		boolean b=false;
		request="7";//�޸��������"7"
		b=SendMsg(request+"#"+id+"#"+name+"#"+sex+"#"+birDay+"#");
		System.out.println(request+"#"+id+"#"+name+"#"+sex+"#"+birDay+"#");//������Ϣ
		if(b)System.out.println("m:�����޸���Ϣ�ѷ��ͣ�");//������Ϣ
		else System.out.println("m:�����޸���Ϣ����ʧ��");//������Ϣ
		return b;
	}
	public void SendGetAllFriendId(String id) {
		//"��ȡ����ID"-9
		request="9";
		SendMsg(request+"#"+id+"#");
	}
	
	
	/*
	 * ����µķ���д��SendMsg֮ǰ
	 * */
	
	private boolean SendMsg(String Msg) {
		boolean b=false;
		try {
			//��Ҫ����
			sendSocket=new Socket(SendHost,SendPort);//����Ҫ���ӵķ�������ַ�Ͷ˿�8091
			os=sendSocket.getOutputStream();
			bw=new BufferedWriter(new OutputStreamWriter(os));//��socket1��
			bw.write(Msg);//���巢�͵�����
			bw.flush();
			b=true;
			bw.close();
			os.close();
			sendSocket.close();
		} catch (UnknownHostException e) {
			System.out.println("m:*�޷�ʶ�����������ֻ�IP��ַ��-UnknownHostException");
		} catch (IOException e) {
			System.out.println("m:*������δ���ߣ�"+new TIMEer().getTIME());
		}
		return b;
	}
	
}
