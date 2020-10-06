package ServerTools;

public class JudgeMsgTypeAndDeal {
	
	public JudgeMsgTypeAndDeal(Massager msger,ControlDB controldb) {
		
		//������Ϣ
		msger.MsgReceiver();
		//�ж�ͷ���Ͳ��Ҵ���->������Ϣ
		if(msger.getMsgType()==1){
			//��¼
			System.out.println("s:�յ�ͷΪ��¼����Ϣ�ֶ�!");//������Ϣ
			//�ж��˺������Ƿ�Ϸ�
			boolean b=false;
			b=controldb.CheckLogin(msger.getID(),msger.getPwd());
			//�жϲ����ؽ��
			if(b) {
				//��ӵ�ǰ�û�,���������û��б�
				controldb.UserOnline(msger.getID(),msger.getClientIPaddress());
			}
			//���ؽ��
			msger.MsgSender().SendLoginResult(b);
		}
		else if(msger.getMsgType()==2) {
			System.out.println("s:�յ�ͷΪע�����Ϣ�ֶ�!");//������Ϣ
			boolean b=false;
			//�����ݿ�洢
			b=controldb.addUser(msger.getID(), msger.getName(), 
					msger.getPwd(),msger.getSex(),msger.getBirday());
			//������Ϣ
			msger.MsgSender().SendRegisteredResult(b);
		}
		else if(msger.getMsgType()==3) {
			//����
			controldb.UserOffline(msger.getID());
		}
		else if(msger.getMsgType()==4) {
			//���¸�����Ϣ
			String[] str=null;
			str=controldb.UpDateInfoById(msger.getID()) .split(" ");//name,status,FStr
			if(str.length==5)//���Ѳ�Ϊ��
				msger.MsgSender().UpDateInfo(str[0], str[1], str[2], str[3], str[4]);//�ǳ�-״̬-�Ա�-����-����
			else//����Ϊ��
				msger.MsgSender().UpDateInfo(str[0], str[1], str[2], str[3]);//�ǳ�-״̬-�Ա�-����
		}
		else if(msger.getMsgType()==5) {
			//��Ӻ���
			boolean b=false;
			//˫�������
			String fid=msger.getFID(),mid=msger.getID();
			
			b=controldb.addFriend(mid,fid);
			b=controldb.addFriend(fid,mid);
			if(b) {
				//����
				String MyHostAddr=controldb.getHostAddrById(mid),
						strMyFID=controldb.getAllFriendIdById(mid);//�������Լ���id
				//������Ϣ�����¿ͻ��˺�����ʾ
				msger.MsgSender().SendNewFIDMsg(strMyFID,MyHostAddr);
				//�������ߺ��ѿͻ��˵ĵ�ǰ�б�
				if(controldb.isOnlineNow(fid)) {
					String strFID=null,FHostAddr=null;
					//�õ��µĺ����б�
					strFID=controldb.getAllFriendIdById(fid);//�����Ǻ��ѵ�id
					//�õ����ߺ��ѿͻ��˵�ַ
					FHostAddr=controldb.getHostAddrById(fid);
					if(strFID!=null) {
						//�����µĺ����б�
						msger.MsgSender().SendNewFIDMsg(strFID, FHostAddr);
					}
				}
			}
			else {
				System.out.println("Judge:��Ӻ���ʧ�ܣ�");
			}
				
		}
		else if(msger.getMsgType()==6) {
			//һ��������Ϣ
			//ĳid����fid����Ϣmsgtext,�ж�fid�Ƿ�����,���߾�ת��,���򴢴浽���ݿ����������ʱ�鿴
			String myid,frid,msgtext,fhostaddr;
			myid=msger.getID();
			frid=msger.getFID();
			msgtext=msger.getMsgText();
			if(controldb.isOnlineNow(frid) ){
				//��������
				fhostaddr=controldb.getHostAddrById(frid);
				msger.MsgSender().SendCommonMsg(myid, frid, fhostaddr, msgtext);
			}else {
				//���Ѳ�����
				msger.MsgSender().SendCommonMsgFail(myid);
			}
		}
		else if(msger.getMsgType()==7) {
			//�޸ĸ�����Ϣ
		
			//�޸����ݿ��е���Ϣ
			controldb.ChangeInfoById(msger.getID(), msger.getName(), msger.getSex(), msger.getBirday());
			//���¸�����Ϣ
			String[] str=null;
			str=controldb.UpDateInfoById(msger.getID()) .split(" ");//name,status,FStr
			if(str.length==5)//���Ѳ�Ϊ��
				msger.MsgSender().UpDateInfo(str[0], str[1], str[2], str[3], str[4]);//�ǳ�-״̬-�Ա�-����-����
			else//����Ϊ��
				msger.MsgSender().UpDateInfo(str[0], str[1], str[2], str[3]);//�ǳ�-״̬-�Ա�-����
			
		/*	//���º��ѵĵ�ǰ�б�
			String strFID=null;
			strFID=controldb.getAllFriendIdById(msger.getFID());//�����Ǻ��ѵ�id
			if(strFID!=null) {
				msger.MsgSender().UpDateFID(strFID);
			} */
		}
		else if(msger.getMsgType()==8) {
			//ɾ������
			boolean b=false;
			//˫�������
			String fid=msger.getFID(),mid=msger.getID();
			
			//˫����ɾ��
			b=controldb.delFriend(msger.getID(),msger.getFID());
			b=controldb.delFriend(msger.getFID(),msger.getID());
			if(b) {
				//����
				String MyHostAddr=controldb.getHostAddrById(mid),
						strMyFID=controldb.getAllFriendIdById(mid);//�������Լ���id
				//������Ϣ�����¿ͻ��˺�����ʾ
				msger.MsgSender().SendNewFIDMsg(strMyFID,MyHostAddr);
				//���º��ѵĵ�ǰ�б�
				if(controldb.isOnlineNow(fid)) {
					String strFID=null,FHostAddr=null;
					//�õ��µĺ����б�
					strFID=controldb.getAllFriendIdById(fid);//�����Ǻ��ѵ�id
					//�õ����ߺ��ѿͻ��˵�ַ
					FHostAddr=controldb.getHostAddrById(fid);
					if(strFID!=null) {
						//�����µĺ����б�
						msger.MsgSender().SendNewFIDMsg(strFID, FHostAddr);
					}
				}
			}
			else {
				System.out.println("Judge:ɾ������ʧ�ܣ���");
			}
//			if(controldb.isOnlineNow(msger.getFID())) {
//				String strFID=null;
//				strFID=controldb.getAllFriendIdById(msger.getFID());//�����Ǻ��ѵ�id
//				if(strFID!=null) {
//					msger.MsgSender().UpDateFID(strFID);
//				}
//			}
		}
		
		else if(msger.getMsgType()==9) {
			//�õ�����ID
			String strFID=null;
			strFID=controldb.getAllFriendIdById(msger.getID());
			if(strFID!=null) {
				msger.MsgSender().UpDateFID(strFID);
			}
		}
		
	}
}
