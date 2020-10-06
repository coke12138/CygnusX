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
		//连接数据库
		System.out.println("\nDB:数据库连接:"+ConnectDB());
		//连接成功发送操作指令
		
		//得到返回结果关闭接口
		
	}

	private boolean ConnectDB(){
		//连接数据库
		boolean b=false;
		try {
			//数据库驱动、名、密码
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
		//检查账号密码
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
		
		//注册新增id,昵称,密码,性别,生日
		boolean b=false;
		//处理性别
		String theSex;
		if(sex==0)
			theSex="男";
		else if(sex==1)
			theSex="女";
		else 
			theSex=null;
		if(CheckID(Cid)) {
			//ID已存在
			System.out.println("DB:id已被注册");
			return b;
		}
		String sql1="insert into userinfo(Cid,Cname,Cpwd,Csex,Cbirthday) values(?,?,?,?,?)",
				sql2="insert into userinfo(Cid,Cname,Cpwd,Csex) values(?,?,?,?)";
		System.out.println("DB:bir字段:"+birthday);
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
		//检查ID是否存在
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
		//检查ID是否存在于上线表
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
		//用户上线
		if(!isOnlined(ID)) {//第一次上线要新增ID,状态1
			String sql1="insert into onlineid(Oid,Ostatus,Oipaddress) values(?,?,?)";
			try {
				PreparedStatement state=conn.prepareStatement(sql1);
				state.setString(1, ID);
				state.setString(2, "1");//在线1,不在线0
				state.setString(3, ipAddress);
				state.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {//ID已存在则修改状态和ip
			//把status改成1
			String sql2="UPDATE onlineid SET Ostatus='1',Oipaddress=? WHERE Oid = ?";
			try {
				PreparedStatement state=conn.prepareStatement(sql2);
				state.setString(1, ipAddress);
				state.setString(2, ID);//在线1,不在线0
				state.executeUpdate();
			} catch (SQLException e) {
			//	e.printStackTrace();
			}
		}
		System.out.println("DB:新增上线用户ID\n ->"+ID);
	}
	public void UserOffline(String ID) {
		//用户下线
		//把status改成0
		String sql1="UPDATE onlineid SET Ostatus='0',Oipaddress=null WHERE Oid = ?";
		try {
			PreparedStatement state=conn.prepareStatement(sql1);
			state.setString(1, ID);//在线1,不在线0
			state.executeUpdate();
		} catch (SQLException e) {
		//	e.printStackTrace();
		}
		System.out.println("DB:下线用户ID\n ->"+ID);
	}
	public boolean isOnlineNow(String ID) {
		//检查ID此时是否在线
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
		//更新个人信息
		String sql1="select Cname,Csex,Cbirthday from userinfo where Cid=?",
				sql2="select Ostatus from onlineid where Oid=?",
				sql3="SELECT Mfid FROM myfriend where Mid=?";
		String NAME=null,STATUS=null,BirDay=null,SEX=null;
		ArrayList<String> FList=new ArrayList<String>();//好友列表
		try {
			//NAME
			PreparedStatement state=conn.prepareStatement(sql1);
			state.setString(1, ID);
			ResultSet rs=state.executeQuery();
			if(rs.next()) {
				NAME=rs.getString("Cname");
				String birday=rs.getString("Cbirthday");
				//如果数据库字段是null,那么此处的birday指向空
				if(birday==null) {
					BirDay="9990-9990-9990";
				}else {
					String[]temp=birday.split(" ");
					BirDay=temp[0];
				}
				String sex=rs.getString("Csex");
				//如果性别字段为空
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
			//好友列表id
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
			FStr+=FList.get(i)+"*";//用*分隔
		}
		System.out.println("DB:个人信息return:\n ->"+NAME+" "+STATUS+" "+SEX+" "+BirDay+" "+FStr+" .");
		//添加空格(仅这一处)为了供服务端下一步的msg进行分割，不涉及客户端
		return NAME+" "+STATUS+" "+SEX+" "+BirDay+" "+FStr+" ";
	}
	private boolean CheckFriend(String ID,String FID) {
		//检查此ID是否已经有了FID
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
		//添加好友
		boolean b=false;
		System.out.println("DB:cheakID-"+ID+"->"+CheckID(ID)+" cheakID-"+FID+"->"+CheckID(FID));
		if(!CheckID(ID) || !CheckID(FID)){
			//如果任意一个ID不存在则无法添加好友
			System.out.println("DB:添加好友失败:ID<"+ID+">或<"+FID+">不存在！");//测试信息
		}else if(CheckFriend(ID,FID)) {
			//如果已经是好友了也无法再次添加好友
			System.out.println("DB:添加好友失败:ID<"+ID+">和<"+FID+">已经是好友了！");//测试信息
		}
		else{
			//FID存在，ID和FID不存在好友关系
			//向表里添加包含自己的ID和好友的FID的一条数据
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
			System.out.println("DB:添加好友成功！ID<"+ID+"> FID<"+FID+">成为好友");//测试信息
		}
		return b;
	}
	public boolean delFriend(String ID, String FID) {
		//删除好友
		boolean b=false;
		if(!CheckID(ID)&&!CheckID(FID)){
			System.out.println("DB:删除好友失败:好友FID<"+FID+">不存在！");//测试信息
		}else if(!CheckFriend(ID,FID)) {
			System.out.println("DB:删除好友失败:ID<"+ID+">和<"+FID+">不是好友！");//测试信息
		}
		else{
			//FID存在，ID和FID存在好友关系
			//从表里删除包含自己的ID和好友的FID的一条数据
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
			System.out.println("DB:删除好友成功！ID<"+ID+"> FID<"+FID+">不再是好友");//测试信息
		}
		return b;
	}
	
	public boolean ChangeInfoById(String Cid,String name, int sex, String birthday) {
		//修改个人信息
		boolean b=false;
		//处理性别
		String theSex;
		if(sex==0)
			theSex="男";
		else if(sex==1)
			theSex="女";
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
		ArrayList<String> FList=new ArrayList<String>();//好友列表
		try {
			PreparedStatement state=conn.prepareStatement(sql1);
			//好友列表id
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
			FStr+=FList.get(i)+"*";//用*分隔
		}
		System.out.println("DB:好友IDreturn:\n ->"+FStr+" .");
		return FStr;
	}
	public String getHostAddrById(String ID) {
		String sql="select Oipaddress from onlineid where Oid=?";
		String hostaddr=null;
		try {
			PreparedStatement state=conn.prepareStatement(sql);
			//好友列表id
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
