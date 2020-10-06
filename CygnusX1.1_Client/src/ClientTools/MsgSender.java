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
	private BufferedWriter bw=null;//new BufferedWriter(new OutputStreamWriter(os1));//��socket1��
	
	public MsgSender() {
		init();
	}
	private void init() {
		host="192.168.10.5";//��������ַ
		port=8091;
	}
	public boolean sendLogin(String id,String pwd){
		boolean b=false;
		//����ǿ�
		if(id.equals("")||pwd.equals(""))return b;
		request="1";//��¼�������"1"
		b=SendMsg(request+"#"+id+"#"+pwd+"#");
		System.out.println(request+"#"+id+"#"+pwd+"#");//������Ϣ
		System.out.println("���ϵ�½��Ϣ�ѷ��ͣ��ȴ�����");//������Ϣ
		return b;
	}
	public boolean sendRegistered(String id,String name,String pwd,int sex,String birDay) {
		boolean b=false;
		request="2";//ע���������"2"
		b=SendMsg(request+"#"+id+"#"+name+"#"+pwd+"#"+sex+"#"+birDay+"#");
		System.out.println(request+"#"+id+"#"+name+"#"+pwd+"#"+sex+"#"+birDay+"#");//������Ϣ
		if(b)System.out.println("����ע����Ϣ�ѷ��ͣ��ȴ��ظ�");//������Ϣ
		else System.out.println("����ע����Ϣ����ʧ��");//������Ϣ
		return b;
	}
	public boolean SendOffline(String id) {
		boolean b=false;
		request="3";//�����������"3"
		b=SendMsg(request+"#"+id+"#");
		return b;
	}
	
	
	
	
	
	/*
	 * ����µķ���д��SendMsg֮ǰ
	 * */
	
	private boolean SendMsg(String Msg) {
		boolean b=false;
		try {
			//��Ҫ����
			s=new Socket(host,port);//����Ҫ���ӵķ�������ַ�Ͷ˿�8091
			os=s.getOutputStream();
			bw=new BufferedWriter(new OutputStreamWriter(os));//��socket1��
			bw.write(Msg);//���巢�͵�����
			bw.flush();
			b=true;
			bw.close();
			os.close();
			s.close();
		} catch (UnknownHostException e) {
			System.out.println("sender:*�޷�ʶ�����������ֻ�IP��ַ��-UnknownHostException");
		} catch (IOException e) {
			System.out.println("sender:*������δ���ߣ�"+new TIMEer().getTIME());
		}
		return b;
	}
	
}
