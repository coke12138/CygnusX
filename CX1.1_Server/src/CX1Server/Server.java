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
		//初始化
		msger=new Massager();
		controldb=new ControlDB();
		while(true) {
			//服务器要在控制台手动关闭
			
			//收发信息,操作数据库
			new JudgeMsgTypeAndDeal(msger,controldb);
			
		}
	//	controldb.CloseDB();
	}
}