package ServerTools;

public class JudgeMsgTypeAndDeal {
	
	public JudgeMsgTypeAndDeal(Massager msger,ControlDB controldb) {
		
		//接收信息
		msger.MsgReceiver();
		//判断头类型并且处理->发送信息
		if(msger.getMsgType()==1){
			//登录
			System.out.println("s:收到头为登录的信息字段!");//测试信息
			//判断账号密码是否合法
			boolean b=false;
			b=controldb.CheckLogin(msger.getID(),msger.getPwd());
			//判断并返回结果
			if(b) {
				//添加当前用户,更新在线用户列表
				controldb.UserOnline(msger.getID(),msger.getClientIPaddress());
			}
			//返回结果
			msger.MsgSender().SendLoginResult(b);
		}
		else if(msger.getMsgType()==2) {
			System.out.println("s:收到头为注册的信息字段!");//测试信息
			boolean b=false;
			//向数据库存储
			b=controldb.addUser(msger.getID(), msger.getName(), 
					msger.getPwd(),msger.getSex(),msger.getBirday());
			//返回信息
			msger.MsgSender().SendRegisteredResult(b);
		}
		else if(msger.getMsgType()==3) {
			//下线
			controldb.UserOffline(msger.getID());
		}
		else if(msger.getMsgType()==4) {
			//更新个人信息
			String[] str=null;
			str=controldb.UpDateInfoById(msger.getID()) .split(" ");//name,status,FStr
			if(str.length==5)//好友不为空
				msger.MsgSender().UpDateInfo(str[0], str[1], str[2], str[3], str[4]);//昵称-状态-性别-生日-好友
			else//好友为空
				msger.MsgSender().UpDateInfo(str[0], str[1], str[2], str[3]);//昵称-状态-性别-生日
		}
		else if(msger.getMsgType()==5) {
			//添加好友
			boolean b=false;
			//双方都添加
			String fid=msger.getFID(),mid=msger.getID();
			
			b=controldb.addFriend(mid,fid);
			b=controldb.addFriend(fid,mid);
			if(b) {
				//更新
				String MyHostAddr=controldb.getHostAddrById(mid),
						strMyFID=controldb.getAllFriendIdById(mid);//参数是自己的id
				//返回信息，更新客户端好友显示
				msger.MsgSender().SendNewFIDMsg(strMyFID,MyHostAddr);
				//更新在线好友客户端的当前列表
				if(controldb.isOnlineNow(fid)) {
					String strFID=null,FHostAddr=null;
					//得到新的好友列表
					strFID=controldb.getAllFriendIdById(fid);//参数是好友的id
					//得到在线好友客户端地址
					FHostAddr=controldb.getHostAddrById(fid);
					if(strFID!=null) {
						//发送新的好友列表
						msger.MsgSender().SendNewFIDMsg(strFID, FHostAddr);
					}
				}
			}
			else {
				System.out.println("Judge:添加好友失败！");
			}
				
		}
		else if(msger.getMsgType()==6) {
			//一般聊天信息
			//某id发给fid的信息msgtext,判断fid是否在线,在线就转发,否则储存到数据库待好友上线时查看
			String myid,frid,msgtext,fhostaddr;
			myid=msger.getID();
			frid=msger.getFID();
			msgtext=msger.getMsgText();
			if(controldb.isOnlineNow(frid) ){
				//好友在线
				fhostaddr=controldb.getHostAddrById(frid);
				msger.MsgSender().SendCommonMsg(myid, frid, fhostaddr, msgtext);
			}else {
				//好友不在线
				msger.MsgSender().SendCommonMsgFail(myid);
			}
		}
		else if(msger.getMsgType()==7) {
			//修改个人信息
		
			//修改数据库中的信息
			controldb.ChangeInfoById(msger.getID(), msger.getName(), msger.getSex(), msger.getBirday());
			//更新个人信息
			String[] str=null;
			str=controldb.UpDateInfoById(msger.getID()) .split(" ");//name,status,FStr
			if(str.length==5)//好友不为空
				msger.MsgSender().UpDateInfo(str[0], str[1], str[2], str[3], str[4]);//昵称-状态-性别-生日-好友
			else//好友为空
				msger.MsgSender().UpDateInfo(str[0], str[1], str[2], str[3]);//昵称-状态-性别-生日
			
		/*	//更新好友的当前列表
			String strFID=null;
			strFID=controldb.getAllFriendIdById(msger.getFID());//参数是好友的id
			if(strFID!=null) {
				msger.MsgSender().UpDateFID(strFID);
			} */
		}
		else if(msger.getMsgType()==8) {
			//删除好友
			boolean b=false;
			//双方都添加
			String fid=msger.getFID(),mid=msger.getID();
			
			//双方都删除
			b=controldb.delFriend(msger.getID(),msger.getFID());
			b=controldb.delFriend(msger.getFID(),msger.getID());
			if(b) {
				//更新
				String MyHostAddr=controldb.getHostAddrById(mid),
						strMyFID=controldb.getAllFriendIdById(mid);//参数是自己的id
				//返回信息，更新客户端好友显示
				msger.MsgSender().SendNewFIDMsg(strMyFID,MyHostAddr);
				//更新好友的当前列表
				if(controldb.isOnlineNow(fid)) {
					String strFID=null,FHostAddr=null;
					//得到新的好友列表
					strFID=controldb.getAllFriendIdById(fid);//参数是好友的id
					//得到在线好友客户端地址
					FHostAddr=controldb.getHostAddrById(fid);
					if(strFID!=null) {
						//发送新的好友列表
						msger.MsgSender().SendNewFIDMsg(strFID, FHostAddr);
					}
				}
			}
			else {
				System.out.println("Judge:删除好友失败！！");
			}
//			if(controldb.isOnlineNow(msger.getFID())) {
//				String strFID=null;
//				strFID=controldb.getAllFriendIdById(msger.getFID());//参数是好友的id
//				if(strFID!=null) {
//					msger.MsgSender().UpDateFID(strFID);
//				}
//			}
		}
		
		else if(msger.getMsgType()==9) {
			//得到好友ID
			String strFID=null;
			strFID=controldb.getAllFriendIdById(msger.getID());
			if(strFID!=null) {
				msger.MsgSender().UpDateFID(strFID);
			}
		}
		
	}
}
