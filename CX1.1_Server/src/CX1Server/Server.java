package CX1Server;

import ServerTools.ControlDB;
import ServerTools.JudgeMsgTypeAndDeal;
import ServerTools.Massager;

public class Server {

	public static void main(String[] args) {
		new Server();
	}
	
	private Massager msger=null;
	private ControlDB controldb=null;
	public Server(){
		//��ʼ��
		msger=new Massager();
		controldb=new ControlDB();
		while(true) {
			//������Ҫ�ڿ���̨�ֶ��ر�
			
			//�շ���Ϣ,�������ݿ�
			new JudgeMsgTypeAndDeal(msger,controldb);
			
		}
	//	controldb.CloseDB();
	}
}