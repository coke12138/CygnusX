package CX1Client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import ClientTools.Massager;
import ClientTools.TIMEer;
/*
 * 这是新的界面类2020/6/18
 * */
public class ChatView_test extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel,panel_1,panel_2;
	private JTextArea MsgSendtextArea;
	private JPanel contentPane;
	private JTextArea ChatTextField;//,addFriendTextField;
	private JButton SendMsgButton,RevisedInfoButton;
	private JLabel FriendListLabel,MyStatusLabel,MyNameLabel,MyIDLabel,MyBirDayLabel,MySexLabel,ChatWithName;
	private JScrollPane scrollPane;
	private JPanel friendlist;
	private ArrayList<JLabel> friendLabel;//=new ArrayList<JLabel>();
	
	private Thread acceptMsg;
	
	private Massager msger=null, m=null;//msger用来发送,m用来接收
	
	private String myname,mysex,mybir_day,mybir_month,mybir_year;
	
	private JPopupMenu popupMenu;
	private JMenuItem menuItem;
	private int delLabel_x,delLabel_y;
	private String delFriendLabel,chatwithID="-1",offlineContent=null;
	private TIMEer timer=new TIMEer();
	private int hasContent=-1;
	private String[] theFIDstr;//当前所有的好友id

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//测试用
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatView_test frame = new ChatView_test("MyID???");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 */
	public ChatView_test(String id) {
		System.out.println("CV:聊天界面启动！！");
		init(id);//初始化
		Update(id);//更新
	}
	private void init(String id) {
		msger=new Massager();//信息收发
		
		setTitle("CX1.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1092, 601);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setMaximumSize(new Dimension(1092, 601));
		
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 204));
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
		);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 20, 809, 315);
		panel_2.add(scrollPane_1);
		
		ChatTextField = new JTextArea();
		ChatTextField.setEditable(false);
		scrollPane_1.setViewportView(ChatTextField);
		ChatTextField.setColumns(10);
		
		MsgSendtextArea = new JTextArea();
		MsgSendtextArea.setBounds(5, 344, 799, 178);
		panel_2.add(MsgSendtextArea);
		
		SendMsgButton = new JButton("\u53D1\u9001");
		SendMsgButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//发送信息按钮
			//	msger.MsgSender();
				String msgText=MsgSendtextArea.getText();
				if(chatwithID==null) {
					ChatTextField.setText("没有聊天对象！");
					System.out.println("CV:没有聊天对象！");
				}
				else if(msgText.equals("")) {
					ChatTextField.setText("发送内容为空！");
					System.out.println("CV:所发内容为空！");
				}
				else {
					//把输入框的内容显示到聊天窗口
					ChatTextField.setText(ChatTextField.getText()+"->"+id+" "+timer.getTIME()+"\n"+msgText+"\n");
					//清空输入框
					MsgSendtextArea.setText("");
					msger.MsgSender();
					msger.SendCommonMsg(id, chatwithID, msgText);
				}
			}
		});
		SendMsgButton.setBounds(730, 525, 69, 23);
		panel_2.add(SendMsgButton);
		
		ChatWithName=new JLabel("聊天");
		ChatWithName.setBounds(10, 4, 242, 15);
		panel_2.add(ChatWithName);
		panel_1.setLayout(null);
		
		FriendListLabel = new JLabel("\u597D\u53CB\u5217\u8868");
		FriendListLabel.setBounds(0, 0, 77, 15);
		panel_1.add(FriendListLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 23, 251, 421);
		panel_1.add(scrollPane);
		
		friendlist = new JPanel();
		scrollPane.setViewportView(friendlist);
		friendlist.setLayout(new GridLayout(25, 1, 5, 0));
		
		JButton AddFriendButton = new JButton("\u6DFB\u52A0\u597D\u53CB");
		AddFriendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加好友
				//弹出窗口
				new AddFriendTanChuang(id,msger).setVisible(true);
			}
		});
		AddFriendButton.setBounds(158, 0, 93, 23);
		panel_1.add(AddFriendButton);
		panel.setLayout(null);
		
		MyNameLabel = new JLabel("MyName");
		MyNameLabel.setBounds(0, 0, 100, 15);
		panel.add(MyNameLabel);
		
		MyIDLabel = new JLabel(id);
		MyIDLabel.setBounds(0, 20, 100, 15);
		panel.add(MyIDLabel);
		
		RevisedInfoButton = new JButton("\u4FEE\u6539\u8D44\u6599");
		RevisedInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//修改资料按钮
				new ChangeMyInfoTanChuang(id,myname,mysex,mybir_year,mybir_month,mybir_day).setVisible(true);
			}
		});
		RevisedInfoButton.setBounds(158, 0, 93, 23);
		panel.add(RevisedInfoButton);
		
		MyStatusLabel = new JLabel("MyStatus");
		MyStatusLabel.setBounds(0, 40, 54, 15);
		panel.add(MyStatusLabel);
		
		MyBirDayLabel = new JLabel("生日 -年-月-日");
		MyBirDayLabel.setBounds(0, 60, 130, 15);
		panel.add(MyBirDayLabel);
		
		MySexLabel =new JLabel("性别 ");
		MySexLabel.setBounds(0, 80, 54, 15);
		panel.add(MySexLabel);
		
		contentPane.setLayout(gl_contentPane);
		
		addWindowListener(new WindowAdapter() {//点击右上角关闭弹出窗口
			public void windowClosing(WindowEvent e) {
				//停止接收信息线程
				if(acceptMsg!=null)
					acceptMsg.interrupt();
				//发送下线信息
				OffLineMassage(id);
				System.out.println("ChatView程序结束.");//测试信息
			}
		});
	}
	private void OffLineMassage(String id) {//下线
		msger.MsgSender();
		msger.SendOffline(id);
	}
	private void Update(String id) {
		//发送获取信息的请求
		
		msger.MsgSender();
		msger.SendGetNameStatus(id);//要求获取昵称、状态、生日、好友列表等信息
		
		//建立线程不断接收信息并处理
		acceptMsg=new Thread(new Runnable() {
			public void run() {
				while(true) {
					System.out.println("\nCV:接收消息");
					m=new Massager();
					m.getMassage();//阻塞-接收信息
					int type=m.getMsgType();
					if(type==4) {
						//得到昵称和状态、生日、好友列表
						//设置昵称
						myname=m.getName();
						MyNameLabel.setText(myname);
						//设置状态
						String temp=null;
						temp=m.getStatus();
						if(temp.equals("1"))
							temp="在线";
						else
							temp="离线";
						MyStatusLabel.setText(temp);
						//设置性别
						String sexTemp=m.getSex();
						if(sexTemp.equals("9990")) {
							sexTemp="-";
						}
						mysex=sexTemp;
						MySexLabel.setText(sexTemp);
						//设置生日
						String[] birStr=m.getBirDay().split("-");
						if(!birStr[0].equals("9990")) {
							mybir_year=birStr[0];
							mybir_month=birStr[1];
							mybir_day=birStr[2];
							MyBirDayLabel.setText("生日:"+birStr[0]+"年"+birStr[1]+"月"+birStr[2]+"日");
						}
						else {
							MyBirDayLabel.setText("生日:-年-月-日");
						}
						//设置好友列表
						setFLabel(id,m);
						System.out.println("CV:重设好友列表1,msg.getFStr()="+m.getFStr());
					}
//					else if(type==125) {//弃用
//						//添加好友成功
//						//更新好友列表
//						//发出请求，得到好友列表
//				//		msger.MsgSender();
//						//要求获取我的全部好友id,并导致收到type127被动更新自己的好友ID
//				//		msger.SendGetAllFriendId(id);
//						System.out.println("CV:添加了新的好友标签id:"+m.getFID()+".");//测试信息
//					}
//					else if(type==126) {//弃用
//						//删除好友成功
//						//更新好友列表
//						//发出请求，得到好友列表
//				//		msger.MsgSender();
//						//要求获取我的全部好友id,并导致收到type127被动更新自己的好友ID
//				//		msger.SendGetAllFriendId(id);
//						System.out.println("CV:删除了好友标签id:"+delFriendLabel+".");//测试信息
//					}
					else if(type==127) {
						//设置好友列表
						setFLabel(id,m);
						System.out.println("CV:重设好友列表2,msg.getFStr()="+m.getFStr());
					}
					else if(type==128) {
						//被动的设置好友列表
						setFLabel(id,m);
						System.out.println("CV:被动重设好友列表3,msg.getFStr()="+m.getFStr());
					}
					else if(type==997) {
						//添加好友失败
						
					}
					else if(type==995) {
						//消息发送失败
						ChatTextField.setText("当前好友未在线！消息发送失败.");
					}
					else if(type==6) {
						//聊天消息
						//修改hasContent值为ID
						hasContent=Integer.parseInt(m.getID());
						if(Integer.parseInt(chatwithID)!=hasContent) {
							//不是当前聊天好友就刷新好友列表,显示红点
							setFLabel(id,theFIDstr);
							if(offlineContent==null) {
								offlineContent="";
							}
							offlineContent+="->"+m.getID()+" "+timer.getTIME()+"\n"+m.getMsgText()+"\n";
						}else {
							//把消息显示到聊天对话框
							ChatTextField.setText(ChatTextField.getText()+"->"+m.getID()+" "+timer.getTIME()+"\n"+m.getMsgText()+"\n");
						}
							
						System.out.println("CV:聊天消息:"+m.getID()+"对你说了："+timer.getTIME()+"\n"+m.getMsgText()+"\n修改了hasContent="+hasContent);
					}
					else {
						System.out.println("CV:收到其他未识别的类型：type="+type);
					}
					
				}
					
			}
			
		});
		acceptMsg.start();
		System.out.println("acceptMsg-> ok");//测试信息
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private void setFLabel(String id,Massager msg) {
		//根据服务器返回值 设置好友列表
		friendLabel=new ArrayList<JLabel>();
		friendlist.removeAll();
		System.out.println("CV:好友列表："+msg.getFStr());
		if(msg.getFStr()!=null) {//如果好友列表不为空
			if(!msg.getFStr().equals("")) {
				
			}
			String[] FIDstr=msg.getFStr().split("\\*");//好友id
			//更新本地值
			theFIDstr=FIDstr;System.out.println("一共有好友："+FIDstr.length+"个。");
			for(int i=0;i<FIDstr.length;i++) {
				//添加好友标签
				JLabel jl=new JLabel(FIDstr[i]);System.out.println("第"+i+"个好友："+FIDstr[i]+";");//测试信息
				friendLabel.add(jl);
				friendlist.add(friendLabel.get(i));
				//添加图片-用于信息提示
				if(hasContent!=-1&&!chatwithID.equals("-1")) {
					if(Integer.parseInt(chatwithID)!=hasContent&&hasContent==Integer.parseInt(FIDstr[i])) {
						//判断了聊天对象和离线信息id是否一致
						//有新消息就设为红点-不一致
						friendLabel.get(i).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/红点.png")));
					}else {
						//没有新消息就是灰点-一致
						friendLabel.get(i).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/灰点.png")));
					}
				}
				else {
					//没有新消息就是灰点-一致
					friendLabel.get(i).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/灰点.png")));
				}
				//给好友标签添加监听
				friendLabel.get(i).addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount()==2) {
							//双击聊天
							String fid=((JLabel)e.getSource()).getText();
							chatwithID=fid;
							ChatWithName.setText("和"+fid+"的聊天");
							//判断是否取消红点提示和显示聊天内容
							if(Integer.parseInt(chatwithID)==hasContent) {
								//聊天对象和离线信息id一致
								hasContent=-9;
								//设为灰点
								((JLabel)e.getSource()).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/灰点.png")));
								//把消息显示到聊天对话框
								if(offlineContent!=null){
									ChatTextField.setText(offlineContent);
									offlineContent=null;
								}
							}else {
								ChatTextField.setText("");
							}
							System.out.println("CV:chatwithID="+chatwithID);
						}
						if(e.isMetaDown()) {
							//点击右键
							//添加右键菜单
							popupMenu=new JPopupMenu();
							addPopup(jl,popupMenu);
							menuItem=new JMenuItem("删除该好友");
							String fid=((JLabel)e.getSource()).getText();
							delFriendLabel=fid;
							menuItem.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									msger.MsgSender();
									msger.AddDelFriend(id, fid, "8");//删除好友-8
									System.out.println("删除了好友->"+fid);
								}
							});
							popupMenu.add(menuItem);
							popupMenu.show(e.getComponent(), e.getX(), e.getY());
						}
					}
				});
			}
		}
		else {
			JLabel jl=new JLabel("你的好友列表为空.");
			friendlist.add(jl);
		}
		friendlist.repaint();
		friendlist.validate();
	}
	private void setFLabel(String id,String[] FIDstr) {
		//根据本地好友id列表 设置好友列表
		friendLabel=new ArrayList<JLabel>();
		friendlist.removeAll();
		
		System.out.println("CV:好友列表-FIDstr："+FIDstr);
		if(FIDstr!=null) {//如果好友列表不为空
			for(int i=0;i<FIDstr.length;i++) {
				//添加好友标签
				JLabel jl=new JLabel(FIDstr[i]);
				friendLabel.add(jl);
				//添加图片-用于信息提示
				if(hasContent==Integer.parseInt(FIDstr[i])) {
					//有新消息就设为红点
					friendLabel.get(i).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/红点.png")));
				}else {
					//没有新消息就是灰点
					friendLabel.get(i).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/灰点.png")));
				}
				friendlist.add(friendLabel.get(i));
				
				//给好友标签添加监听
				friendLabel.get(i).addMouseListener(new MouseAdapter() {
					//这里不是i的作用域//把这里代码的i要用((JLabel)e.getSource())
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount()==2) {
							//双击聊天
							String fid=((JLabel)e.getSource()).getText();
							chatwithID=fid;
							ChatWithName.setText("和"+fid+"的聊天");
							//判断是否取消红点提示和显示离线聊天内容
							if(Integer.parseInt(chatwithID)==hasContent) {
								//聊天对象和离线信息id一致
								hasContent=-9;
								//设为灰点
								((JLabel)e.getSource()).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/灰点.png")));
								//把消息显示到聊天对话框
								if(offlineContent!=null) {
									ChatTextField.setText(offlineContent);
									offlineContent=null;
								}
									
							}else {
								ChatTextField.setText("");
							}
							System.out.println("CV:chatwithID="+chatwithID);
						}
						if(e.isMetaDown()) {
							//点击右键
							//添加右键菜单
							popupMenu=new JPopupMenu();
							addPopup(jl,popupMenu);
							menuItem=new JMenuItem("删除该好友");
							String fid=((JLabel)e.getSource()).getText();
							delFriendLabel=fid;
							menuItem.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									msger.MsgSender();
									msger.AddDelFriend(id, fid, "8");//删除好友-8
									System.out.println("删除了好友->"+fid);
								}
							});
							popupMenu.add(menuItem);
							popupMenu.show(e.getComponent(), e.getX(), e.getY());
						}
					}
				});
			}
		}
		else {
			JLabel jl=new JLabel("你的好友列表为空.");
			friendlist.add(jl);
		}
		friendlist.repaint();
		friendlist.validate();
	}
}//ccss
