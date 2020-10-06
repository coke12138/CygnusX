/*
 * ChatFriend.java
 *
 * Created on __DATE__, __TIME__
 */

package CX1Client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ClientTools.Massager;

/**
 *
 * @author  __USER__
 */
public class ChatFriend extends javax.swing.JFrame {

	/** Creates new form ChatFriend */
	
	//更新信息的部分
	private int MyStatus;//1在线,0下线
	private String MyID, MyName;
	private Massager msger=null, m=null;
	private Thread acceptMsg;

	public ChatFriend(String id) {
		this.MyID = id;
		this.MyStatus=1;
		msger=new Massager();
		msger.MsgSender();
		msger.SendGetNameStatus(id);
//		initComponents();
		init();
		
		addWindowListener(new WindowAdapter() {//点击右上角关闭弹出窗口
			public void windowClosing(WindowEvent e) {
				//停止接收信息线程
				if(acceptMsg!=null)
					acceptMsg.interrupt();
				//发送下线
				OffLineMassage();
				System.out.println("ChatFriend程序结束.");//测试信息
			}
		});
	}
	
	private void init() {
		
	//	Account.setText(MyID);
		
		//建立线程不断接收信息
		acceptMsg=new Thread(new Runnable() {
			public void run() {
				while(true) {
					m=new Massager();
					m.getMassage();//阻塞
					int type=m.getMsgType();
					if(type==4) {
						//得到昵称
						MyName=m.getName();
		//				Name.setText(MyName);
						String temp=null;
						temp=m.getStatus();
						if(temp.equals("1"))
							temp="在线";
						else
							temp="离线";
		//				Status.setText(temp);
						
					}
				}
					
			}
		});
		acceptMsg.start();
		System.out.println("ok");
	}
	private void OffLineMassage() {
		msger.MsgSender();
		msger.SendOffline(MyID);
	}
//
//	//GEN-BEGIN:initComponents
//	// <editor-fold defaultstate="collapsed" desc="Generated Code">
//	private void initComponents() {
//
//		jPanel1 = new javax.swing.JPanel();
//		Name = new javax.swing.JTextField();
//		Account = new javax.swing.JTextField();
//		Status = new javax.swing.JTextField();
//		ModifyInfo = new javax.swing.JButton();
//		jPanel2 = new javax.swing.JPanel();
//		jScrollPane1 = new javax.swing.JScrollPane();
//		FriendList = new javax.swing.JList();
//		jPanel3 = new javax.swing.JPanel();
//		SendButton = new javax.swing.JButton();
//		jScrollPane2 = new javax.swing.JScrollPane();
//		ChatArea = new javax.swing.JTextArea();
//		jScrollPane3 = new javax.swing.JScrollPane();
//		MsgSendBox = new javax.swing.JTextArea();
//
//		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//		jPanel1.setBackground(new java.awt.Color(255, 255, 246));
//
//		Name.setEditable(false);
//		Name.setText("myName");
//
//		Account.setEditable(false);
//		Account.setText("1230612306");
//
//		Status.setEditable(false);
//		Status.setText("Status");
//
//		ModifyInfo.setText("\u4fee\u6539\u8d44\u6599");
//		ModifyInfo.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				ModifyInfoActionPerformed(evt);
//			}
//		});
//
//		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
//				jPanel1);
//		jPanel1.setLayout(jPanel1Layout);
//		jPanel1Layout
//				.setHorizontalGroup(jPanel1Layout
//						.createParallelGroup(
//								javax.swing.GroupLayout.Alignment.LEADING)
//						.addGroup(
//								jPanel1Layout
//										.createSequentialGroup()
//										.addContainerGap()
//										.addGroup(
//												jPanel1Layout
//														.createParallelGroup(
//																javax.swing.GroupLayout.Alignment.LEADING)
//														.addComponent(
//																Status,
//																javax.swing.GroupLayout.PREFERRED_SIZE,
//																40,
//																javax.swing.GroupLayout.PREFERRED_SIZE)
//														.addGroup(
//																jPanel1Layout
//																		.createSequentialGroup()
//																		.addGroup(
//																				jPanel1Layout
//																						.createParallelGroup(
//																								javax.swing.GroupLayout.Alignment.TRAILING)
//																						.addComponent(
//																								Account,
//																								javax.swing.GroupLayout.Alignment.LEADING,
//																								javax.swing.GroupLayout.DEFAULT_SIZE,
//																								120,
//																								Short.MAX_VALUE)
//																						.addComponent(
//																								Name,
//																								javax.swing.GroupLayout.DEFAULT_SIZE,
//																								120,
//																								Short.MAX_VALUE))
//																		.addPreferredGap(
//																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//																		.addComponent(
//																				ModifyInfo)))
//										.addContainerGap()));
//		jPanel1Layout
//				.setVerticalGroup(jPanel1Layout
//						.createParallelGroup(
//								javax.swing.GroupLayout.Alignment.LEADING)
//						.addGroup(
//								jPanel1Layout
//										.createSequentialGroup()
//										.addGroup(
//												jPanel1Layout
//														.createParallelGroup(
//																javax.swing.GroupLayout.Alignment.BASELINE)
//														.addComponent(
//																ModifyInfo)
//														.addComponent(
//																Name,
//																javax.swing.GroupLayout.PREFERRED_SIZE,
//																javax.swing.GroupLayout.DEFAULT_SIZE,
//																javax.swing.GroupLayout.PREFERRED_SIZE))
//										.addPreferredGap(
//												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//										.addComponent(
//												Account,
//												javax.swing.GroupLayout.PREFERRED_SIZE,
//												javax.swing.GroupLayout.DEFAULT_SIZE,
//												javax.swing.GroupLayout.PREFERRED_SIZE)
//										.addPreferredGap(
//												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//										.addComponent(
//												Status,
//												javax.swing.GroupLayout.PREFERRED_SIZE,
//												javax.swing.GroupLayout.DEFAULT_SIZE,
//												javax.swing.GroupLayout.PREFERRED_SIZE)
//										.addContainerGap()));
//
//		jPanel2.setBackground(new java.awt.Color(227, 250, 238));
//
//		FriendList.setModel(new javax.swing.AbstractListModel() {
//			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
//					"Item 5", "Item 6" };
//
//			public int getSize() {
//				return strings.length;
//			}
//
//			public Object getElementAt(int i) {
//				return strings[i];
//			}
//		});
//		jScrollPane1.setViewportView(FriendList);
//
//		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
//				jPanel2);
//		jPanel2.setLayout(jPanel2Layout);
//		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
//				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
//				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237,
//				Short.MAX_VALUE));
//		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
//				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
//				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428,
//				Short.MAX_VALUE));
//
//		jPanel3.setBackground(new java.awt.Color(204, 255, 204));
//
//		SendButton.setText("\u53d1\u9001");
//		SendButton.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				SendButtonActionPerformed(evt);
//			}
//		});
//
//		ChatArea.setColumns(20);
//		ChatArea.setEditable(false);
//		ChatArea.setLineWrap(true);
//		ChatArea.setRows(5);
//		jScrollPane2.setViewportView(ChatArea);
//
//		MsgSendBox.setColumns(20);
//		MsgSendBox.setRows(5);
//		jScrollPane3.setViewportView(MsgSendBox);
//
//		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
//				jPanel3);
//		jPanel3.setLayout(jPanel3Layout);
//		jPanel3Layout.setHorizontalGroup(jPanel3Layout
//				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//				.addGroup(
//						javax.swing.GroupLayout.Alignment.TRAILING,
//						jPanel3Layout.createSequentialGroup()
//								.addContainerGap(671, Short.MAX_VALUE)
//								.addComponent(SendButton).addContainerGap())
//				.addComponent(jScrollPane2,
//						javax.swing.GroupLayout.DEFAULT_SIZE, 740,
//						Short.MAX_VALUE)
//				.addComponent(jScrollPane3,
//						javax.swing.GroupLayout.DEFAULT_SIZE, 740,
//						Short.MAX_VALUE));
//		jPanel3Layout
//				.setVerticalGroup(jPanel3Layout
//						.createParallelGroup(
//								javax.swing.GroupLayout.Alignment.LEADING)
//						.addGroup(
//								javax.swing.GroupLayout.Alignment.TRAILING,
//								jPanel3Layout
//										.createSequentialGroup()
//										.addComponent(
//												jScrollPane2,
//												javax.swing.GroupLayout.DEFAULT_SIZE,
//												345, Short.MAX_VALUE)
//										.addPreferredGap(
//												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//										.addComponent(
//												jScrollPane3,
//												javax.swing.GroupLayout.PREFERRED_SIZE,
//												151,
//												javax.swing.GroupLayout.PREFERRED_SIZE)
//										.addPreferredGap(
//												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//										.addComponent(SendButton)));
//
//		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
//				getContentPane());
//		getContentPane().setLayout(layout);
//		layout.setHorizontalGroup(layout
//				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//				.addGroup(
//						layout.createSequentialGroup()
//								.addGroup(
//										layout.createParallelGroup(
//												javax.swing.GroupLayout.Alignment.LEADING)
//												.addComponent(
//														jPanel1,
//														javax.swing.GroupLayout.DEFAULT_SIZE,
//														javax.swing.GroupLayout.DEFAULT_SIZE,
//														Short.MAX_VALUE)
//												.addComponent(
//														jPanel2,
//														javax.swing.GroupLayout.PREFERRED_SIZE,
//														javax.swing.GroupLayout.DEFAULT_SIZE,
//														javax.swing.GroupLayout.PREFERRED_SIZE))
//								.addPreferredGap(
//										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//								.addComponent(jPanel3,
//										javax.swing.GroupLayout.DEFAULT_SIZE,
//										javax.swing.GroupLayout.DEFAULT_SIZE,
//										Short.MAX_VALUE)));
//		layout.setVerticalGroup(layout
//				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//				.addGroup(
//						layout.createSequentialGroup()
//								.addComponent(jPanel1,
//										javax.swing.GroupLayout.PREFERRED_SIZE,
//										javax.swing.GroupLayout.DEFAULT_SIZE,
//										javax.swing.GroupLayout.PREFERRED_SIZE)
//								.addPreferredGap(
//										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//								.addComponent(jPanel2,
//										javax.swing.GroupLayout.DEFAULT_SIZE,
//										javax.swing.GroupLayout.DEFAULT_SIZE,
//										Short.MAX_VALUE))
//				.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE,
//						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
//
//		pack();
//	}// </editor-fold>
//	//GEN-END:initComponents
//
//	private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {
//		// 
//
//	}
//
//	private void ModifyInfoActionPerformed(java.awt.event.ActionEvent evt) {
//		//
//
//	}
//
//	
//	/**
//	 * @param args the command line arguments
//	 */
//	public static void main(String args[]) {
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				new ChatFriend("0000000000").setVisible(true);
//			}
//		});
//	}
//
//	//GEN-BEGIN:variables
//	// Variables declaration - do not modify
//	private javax.swing.JTextField Account;
//	private javax.swing.JTextArea ChatArea;
//	private javax.swing.JList FriendList;
//	private javax.swing.JButton ModifyInfo;
//	private javax.swing.JTextArea MsgSendBox;
//	private javax.swing.JTextField Name;
//	private javax.swing.JButton SendButton;
//	private javax.swing.JTextField Status;
//	private javax.swing.JPanel jPanel1;
//	private javax.swing.JPanel jPanel2;
//	private javax.swing.JPanel jPanel3;
//	private javax.swing.JScrollPane jScrollPane1;
//	private javax.swing.JScrollPane jScrollPane2;
//	private javax.swing.JScrollPane jScrollPane3;
//	// End of variables declaration//GEN-END:variables

}