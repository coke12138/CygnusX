package ServerTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControlDB {
	
	private Connection conn=null;

	public static void main(String[] args) {
		new ControlDB();
	}

	public ControlDB(){
		//�������ݿ�
		System.out.println("\nDB:���ݿ�����:"+ConnectDB());
		//���ӳɹ����Ͳ���ָ��
		
		//�õ����ؽ���رսӿ�
		
	}

	private boolean ConnectDB(){
		//�������ݿ�
		boolean b=false;
		try {
			//���ݿ���������������
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cygnusx1db?useSSL=FALSE&serverTimezone=UTC","root","W1odeshujukumima");
			b=true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public void CloseDB() {
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public boolean CheckLogin(String ID,String Pwd) {
		//����˺�����
		boolean b=false;
		String sql="select Cid from userinfo where Cid=? and Cpwd=?";
		try {
			PreparedStatement state=conn.prepareStatement(sql);
			state.setString(1, ID);
			state.setString(2, Pwd);
			ResultSet rs=state.executeQuery();
			b=rs.next();
		} catch (SQLException e) {
		//	e.printStackTrace();
		}
		return b;
	}
	public boolean addUser(String Cid,String name,String pwd, int sex, String birthday){
		
		//ע������id,�ǳ�,����,�Ա�,����
		boolean b=false;
		//�����Ա�
		String theSex;
		if(sex==0)
			theSex="��";
		else if(sex==1)
			theSex="Ů";
		else 
			theSex=null;
		if(CheckID(Cid)) {
			//ID�Ѵ���
			System.out.println("DB:id�ѱ�ע��");
			return b;
		}
		String sql1="insert into userinfo(Cid,Cname,Cpwd,Csex,Cbirthday) values(?,?,?,?,?)",
				sql2="insert into userinfo(Cid,Cname,Cpwd,Csex) values(?,?,?,?)";
		System.out.println("DB:bir�ֶ�:"+birthday);
		try {
			PreparedStatement state;
			if(birthday!=null) {
				state=conn.prepareStatement(sql1);
				state.setString(5, birthday);
			}
			else
				state=conn.prepareStatement(sql2);
			state.setString(1, Cid);
			state.setString(2, name);
			state.setString(3, pwd);
			state.setString(4, theSex);
			state.executeUpdate();
			b=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	private boolean CheckID(String ID) {
		//���ID�Ƿ����
		boolean b=false;
		String sql="select Cid from userinfo where Cid=?";
		try {
			PreparedStatement state=conn.prepareStatement(sql);
			state.setString(1, ID);
			ResultSet rs=state.executeQuery();
			b=rs.next();
		} catch (SQLException e) {
		//	e.printStackTrace();
		}
		return b;
	}
	private boolean isOnlined(String ID) {
		//���ID�Ƿ���������߱�
		boolean b=false;
		String sql="select Oid from onlineid where Oid=?";
		try {
			PreparedStatement state=conn.prepareStatement(sql);
			state.setString(1, ID);
			ResultSet rs=state.executeQuery(); 
			b=rs.next();
		} catch (SQLException e) {
		//	e.printStackTrace();
		}
		return b;
	}
	public void UserOnline(String ID,String ipAddress) {
		//�û�����
		if(!isOnlined(ID)) {//��һ������Ҫ����ID,״̬1
			String sql1="insert into onlineid(Oid,Ostatus,Oipaddress) values(?,?,?)";
			try {
				PreparedStatement state=conn.prepareStatement(sql1);
				state.setString(1, ID);
				state.setString(2, "1");//����1,������0
				state.setString(3, ipAddress);
				state.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {//ID�Ѵ������޸�״̬��ip
			//��status�ĳ�1
			String sql2="UPDATE onlineid SET Ostatus='1',Oipaddress=? WHERE Oid = ?";
			try {
				PreparedStatement state=conn.prepareStatement(sql2);
				state.setString(1, ipAddress);
				state.setString(2, ID);//����1,������0
				state.executeUpdate();
			} catch (SQLException e) {
			//	e.printStackTrace();
			}
		}
		System.out.println("DB:���������û�ID\n ->"+ID);
	}
	public void UserOffline(String ID) {
		//�û�����
		//��status�ĳ�0
		String sql1="UPDATE onlineid SET Ostatus='0',Oipaddress=null WHERE Oid = ?";
		try {
			PreparedStatement state=conn.prepareStatement(sql1);
			state.setString(1, ID);//����1,������0
			state.executeUpdate();
		} catch (SQLException e) {
		//	e.printStackTrace();
		}
		System.out.println("DB:�����û�ID\n ->"+ID);
	}
	public boolean isOnlineNow(String ID) {
		//���ID��ʱ�Ƿ�����
		boolean b=false;
		String sql="select Ostatus from onlineid where Oid=?";
		int status=-1;
		try {
			PreparedStatement state=conn.prepareStatement(sql);
			state.setString(1, ID);
			ResultSet rs=state.executeQuery(); 
			if(rs.next()) {
				status=rs.getInt("Ostatus");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(status==1) {
			b=true;
		}
		return b;
	}
	public String UpDateInfoById(String ID) {
		//���¸�����Ϣ
		String sql1="select Cname,Csex,Cbirthday from userinfo where Cid=?",
				sql2="select Ostatus from onlineid where Oid=?",
				sql3="SELECT Mfid FROM myfriend where Mid=?";
		String NAME=null,STATUS=null,BirDay=null,SEX=null;
		ArrayList<String> FList=new ArrayList<String>();//�����б�
		try {
			//NAME
			PreparedStatement state=conn.prepareStatement(sql1);
			state.setString(1, ID);
			ResultSet rs=state.executeQuery();
			if(rs.next()) {
				NAME=rs.getString("Cname");
				String birday=rs.getString("Cbirthday");
				//������ݿ��ֶ���null,��ô�˴���birdayָ���
				if(birday==null) {
					BirDay="9990-9990-9990";
				}else {
					String[]temp=birday.split(" ");
					BirDay=temp[0];
				}
				String sex=rs.getString("Csex");
				//����Ա��ֶ�Ϊ��
				if(sex==null) {
					SEX="9990";
				}else {
					SEX=sex;
				}
			}
			//STATUS
			state=conn.prepareStatement(sql2);
			state.setString(1, ID);
			rs=state.executeQuery();
			if(rs.next()) {
				STATUS=rs.getString("Ostatus");
			}
			//�����б�id
			state=conn.prepareStatement(sql3);
			state.setString(1, ID);
			rs=state.executeQuery();
			while(rs.next()) {
				FList.add(rs.getString("Mfid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String FStr="";
		for(int i=0;i<FList.size();i++) {
			FStr+=FList.get(i)+"*";//��*�ָ�
		}
		System.out.println("DB:������Ϣreturn:\n ->"+NAME+" "+STATUS+" "+SEX+" "+BirDay+" "+FStr+" .");
		//��ӿո�(����һ��)Ϊ�˹��������һ����msg���зָ���漰�ͻ���
		return NAME+" "+STATUS+" "+SEX+" "+BirDay+" "+FStr+" ";
	}
	private boolean CheckFriend(String ID,String FID) {
		//����ID�Ƿ��Ѿ�����FID
		boolean b=false;
		String sql="select Mfid from myfriend where Mid=? and Mfid=?";
		try {
			PreparedStatement state=conn.prepareStatement(sql);
			state.setString(1, ID);
			state.setString(2, FID);
			ResultSet rs=state.executeQuery();
			b=rs.next();
		} catch (SQLException e) {
		//	e.printStackTrace();
		}
		return b;
	}
	public boolean addFriend(String ID, String FID) {
		//��Ӻ���
		boolean b=false;
		System.out.println("DB:cheakID-"+ID+"->"+CheckID(ID)+" cheakID-"+FID+"->"+CheckID(FID));
		if(!CheckID(ID) || !CheckID(FID)){
			//�������һ��ID���������޷���Ӻ���
			System.out.println("DB:��Ӻ���ʧ��:ID<"+ID+">��<"+FID+">�����ڣ�");//������Ϣ
		}else if(CheckFriend(ID,FID)) {
			//����Ѿ��Ǻ�����Ҳ�޷��ٴ���Ӻ���
			System.out.println("DB:��Ӻ���ʧ��:ID<"+ID+">��<"+FID+">�Ѿ��Ǻ����ˣ�");//������Ϣ
		}
		else{
			//FID���ڣ�ID��FID�����ں��ѹ�ϵ
			//�������Ӱ����Լ���ID�ͺ��ѵ�FID��һ������
			String sql1="insert into myfriend(Mid,Mfid) values(?,?)";
			try {
				PreparedStatement state=conn.prepareStatement(sql1);
				state.setString(1, ID);
				state.setString(2, FID);
				state.executeUpdate();
				b=true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("DB:��Ӻ��ѳɹ���ID<"+ID+"> FID<"+FID+">��Ϊ����");//������Ϣ
		}
		return b;
	}
	public boolean delFriend(String ID, String FID) {
		//ɾ������
		boolean b=false;
		if(!CheckID(ID)&&!CheckID(FID)){
			System.out.println("DB:ɾ������ʧ��:����FID<"+FID+">�����ڣ�");//������Ϣ
		}else if(!CheckFriend(ID,FID)) {
			System.out.println("DB:ɾ������ʧ��:ID<"+ID+">��<"+FID+">���Ǻ��ѣ�");//������Ϣ
		}
		else{
			//FID���ڣ�ID��FID���ں��ѹ�ϵ
			//�ӱ���ɾ�������Լ���ID�ͺ��ѵ�FID��һ������
			String sql1="DELETE FROM myfriend WHERE Mid =? and Mfid=?";
			try {
				PreparedStatement state=conn.prepareStatement(sql1);
				state.setString(1, ID);
				state.setString(2, FID);
				state.executeUpdate();
				b=true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("DB:ɾ�����ѳɹ���ID<"+ID+"> FID<"+FID+">�����Ǻ���");//������Ϣ
		}
		return b;
	}
	
	public boolean ChangeInfoById(String Cid,String name, int sex, String birthday) {
		//�޸ĸ�����Ϣ
		boolean b=false;
		//�����Ա�
		String theSex;
		if(sex==0)
			theSex="��";
		else if(sex==1)
			theSex="Ů";
		else 
			theSex=null;
		
		String sql1="UPDATE userinfo SET Cname=?,Csex=?,Cbirthday=? WHERE Cid =?",
				sql2="UPDATE userinfo SET Cname=?,Csex=? WHERE Cid =?";
		try {
			PreparedStatement state;
			if(birthday!=null) {
				state=conn.prepareStatement(sql1);
				state.setString(3, birthday);
				state.setString(4, Cid);
			}
			else {
				state=conn.prepareStatement(sql2);
				state.setString(3, Cid);
			}
				
			state.setString(1, name);
			state.setString(2, theSex);
			
			state.executeUpdate();
			b=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public String getAllFriendIdById(String ID) {
		String sql1="SELECT Mfid FROM myfriend where Mid=?";
		ArrayList<String> FList=new ArrayList<String>();//�����б�
		try {
			PreparedStatement state=conn.prepareStatement(sql1);
			//�����б�id
			state=conn.prepareStatement(sql1);
			state.setString(1, ID);
			ResultSet rs=state.executeQuery();
			while(rs.next()) {
				FList.add(rs.getString("Mfid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String FStr="";
		for(int i=0;i<FList.size();i++) {
			FStr+=FList.get(i)+"*";//��*�ָ�
		}
		System.out.println("DB:����IDreturn:\n ->"+FStr+" .");
		return FStr;
	}
	public String getHostAddrById(String ID) {
		String sql="select Oipaddress from onlineid where Oid=?";
		String hostaddr=null;
		try {
			PreparedStatement state=conn.prepareStatement(sql);
			//�����б�id
			state=conn.prepareStatement(sql);
			state.setString(1, ID);
			ResultSet rs=state.executeQuery();
			if(rs.next()) {
				hostaddr=rs.getString("Oipaddress");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hostaddr;
	}
}
