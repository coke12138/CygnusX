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
//���������ն˿�8091
public class Massager {
	/*
	 * ����һ�������շ���Ϣ����
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
		//������Ϣ��ʼ��
		try {
			while(true){
				ss=new ServerSocket(8091);
				System.out.println("\nm:Server���ڼ���");
				s=ss.accept();
				//�õ��ͻ���ip��ַ
		//		ClientIPaddress=s.getInetAddress().getHostAddress();
				System.out.println("m:�ͻ��˵�IP��ַ-"+ClientIPaddress);
				is=s.getInputStream();
				br=new BufferedReader(new InputStreamReader(is));
				//��#�ָ�
				msg=br.readLine().split("#");
				
				System.out.println("m:�յ�ԭʼ��Ϣ:");//������Ϣ
				for(int i=0;i<msg.length;i++) {
					System.out.println("->msg["+i+"]:"+msg[i]);
				}
				
				//����ͷ�趨��Ϣ����
				if(msg[0].equals("1")){
					//"��¼"����
					setMsgType(1);
					//�洢�ָ���ֶ�
					setIDPwd(msg[1],msg[2]);
					ClientIPaddress=msg[3];
					System.out.println("m:�ͻ��˵�IP��ַ-"+ClientIPaddress);
					System.out.println("m:�յ�1��½����");//������Ϣ
				}
				else if(msg[0].equals("2")) {
					//"ע��"����
					setMsgType(2);
					//�洢�ָ���ֶ�
					setIDPwd(msg[1],msg[3]);
					setName(msg[2]);
					setSex(Integer.valueOf(msg[4]));
					String str=null;
					//msg���������ֶ��±���5��6��7.
					int i=1;
					//�洢��������
					if(!msg[5].equals("null")) {
						//����ǿ�
						while(msg.length-5>=i) {
							str+=msg[i+4]+"-";
							i++;
						}
						for(int j=0;j<4-i;j++) {
							//��ȫ�����ֶ�
							str+="1-";
						}
						setbirDay(str);
					}
					else {
						//����ǿյ�
						setbirDay(null);
					}
					System.out.println("m:�յ�2ע������");//������Ϣ
				}
				else if(msg[0].equals("3")) {
					//"����"����
					setMsgType(3);
					setID(msg[1]);
				}
				else if(msg[0].equals("4")) {
					//"�õ�������Ϣ"����
					setMsgType(4);
					setID(msg[1]);
				}
				else if(msg[0].equals("5")) {
					//"��Ӻ���"����
					setMsgType(5);
					setID(msg[1]);
					setFID(msg[2]);
				}
				else if(msg[0].equals("6")) {
					//"����һ����Ϣ"����
					setMsgType(6);
					setID(msg[1]);
					setFID(msg[2]);
					setMsgText(msg[3]);
				}
				else if(msg[0].equals("7")) {
					//"�޸ĸ�����Ϣ"����
					//id-�ǳ�-�Ա�-����
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
					//����
					String str="";
					//msg���������ֶ��±���456.
					int i=1;
					//�洢��������
					if(!msg[4].equals("null")) {
						//����ǿ�
						while(msg.length-4>=i) {
							str+=msg[i+3]+"-";
							i++;
						}
						for(int j=0;j<3-i;j++) {
							//��ȫ�����ֶ�
							str+="1-";
						}
						setbirDay(str);
					}
					else {
						//����ǿյ�
						setbirDay(null);
					}
				}
				else if(msg[0].equals("8")) {
					//"ɾ������"����
					setMsgType(8);
					setID(msg[1]);
					setFID(msg[2]);
				}
				else if(msg[0].equals("9")) {
					//"�õ�����ID"����
					setMsgType(9);
					setID(msg[1]);
				}
				else{
					System.out.println("m:�յ�����δʶ������:msg[0]="+msg[0]);
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
		//��ʼ���ӿ�
		sendPort=8092;
		return this;
	}
	public void SendLoginResult(boolean b) {
		if(b) {
			SendResult("123#");//�ɹ���¼����123
			System.out.println("m:��¼*�ɹ�*");//������Ϣ
		}else {
			SendResult("999#");//ʧ�ܵ�¼����999
			System.out.println("m:��½ʧ��");//������Ϣ
		}
		
	}
	public void SendRegisteredResult(boolean b) {
		if(b) {
			SendResult("124#");//ע��ɹ�����124
			System.out.println("m:ע��*�ɹ�*");//������Ϣ
		}else {
			SendResult("998#");//ע��ʧ�ܴ���998
			System.out.println("m:ע��ʧ��");//������Ϣ
		}
		
	}
	public void UpDateInfo(String name,String Status,String Sex,String BirDay,String FStr) {
		//�����б�Ϊ��
		SendResult("4#"+name+"#"+Status+"#"+Sex+"#"+BirDay+"#"+FStr+"#");//��ȡ���ֺ�״̬����4
		System.out.println("m:������name-status-�����б�\n ->"+"4#"+name+"#"+Status+"#"+Sex+"#"+BirDay+"#"+FStr+"#");//������Ϣ
	}
	public void UpDateInfo(String name,String Status,String Sex,String BirDay) {
		//�����б�Ϊ��
		SendResult("4#"+name+"#"+Status+"#"+Sex+"#"+BirDay+"#");//��ȡ���ֺ�״̬����4
		System.out.println("m:������name-status\n ->"+"4#"+name+"#"+BirDay+"#"+Status+"#"+Sex+"#");//������Ϣ
	}
	public void UpDateFID(String FStr) {
		//�����б�Ϊ�ղŵ��ô˷���//�����޸ĸ�����Ϣ�ĵ���
		SendResult("127#"+FStr+"#");//127-���º����б�
		System.out.println("m:�����˸��º����б�:\n ->"+FStr);
	}
	
//	public void SendAddFriendResult(boolean b, String FID) {//����
//		//��Ӻ��Ѹ��º����б�
//		if(b) {
//			SendResult("125#"+FID+"#");//��Ӻ��ѳɹ�
//		}
//		else
//			SendResult("997#");//��Ӻ���ʧ��
//	}
//	public void SendDelFriendResult(boolean b, String FID) {//����
//		//
//		if(b) {
//			SendResult("126#"+FID+"#");//ɾ�����ѳɹ�
//		}
//		else
//			SendResult("996#");//ɾ������ʧ��
//	}
	public void SendCommonMsg(String id,String Fid,String FHostAddress,String MsgText) {
		//����һ����Ϣ//ֻ�к������߲ŵ��ô˷���
		//�ҵ�id-����id-�������ߵ�ַ-��Ϣ����
		//id��Fid˵...
		SendMsg("6#"+id+"#"+MsgText+"#",FHostAddress);
	}
	public void SendCommonMsgFail(String id) {
		//��֪����ʧ�ܵĽ��
		SendResult("995#");
	}
	public void SendNewFIDMsg(String FIDStr,String FHostAddress) {
		//����Ӻ��ѣ��������º����б�//�����ߵĺ��ѿͻ��˷����µĺ����б�
		SendMsg("128#"+FIDStr+"#",FHostAddress);
	}
	
	//����������д����֮ǰ
	private void SendResult(String Result) {
		//���ؽ��
		try {
			s=new Socket(ClientIPaddress,sendPort);
			os=s.getOutputStream();
			bw=new BufferedWriter(new OutputStreamWriter(os));
			bw.write(Result);//����
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
		//ת����Ϣ��Ҫ���ݡ���ַ
		try {
			s=new Socket(ipaddress,sendPort);
			os=s.getOutputStream();
			bw=new BufferedWriter(new OutputStreamWriter(os));
			bw.write(Content);//����
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
