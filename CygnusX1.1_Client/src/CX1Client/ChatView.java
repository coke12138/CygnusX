package CX1Client;

import javax.swing.JFrame;

public class ChatView extends JFrame{

	private javax.swing.JTextField Account;
	private javax.swing.JTextArea ChatArea;
	private javax.swing.JList FriendList;
	private javax.swing.JButton ModifyInfo;
	private javax.swing.JTextArea MsgSendBox;
	private javax.swing.JTextField Name;
	private javax.swing.JButton SendButton;
	private javax.swing.JTextField Status;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ChatView().setVisible(true);
			}
		});
	}
	
	public ChatView() {
		init();
		
	}
	
	private void init() {
		//初始化界面
		//实例
		jPanel1 = new javax.swing.JPanel();
		Name = new javax.swing.JTextField();
		Account = new javax.swing.JTextField();
		Status = new javax.swing.JTextField();
		ModifyInfo = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		FriendList = new javax.swing.JList();
		jPanel3 = new javax.swing.JPanel();
		SendButton = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		ChatArea = new javax.swing.JTextArea();
		jScrollPane3 = new javax.swing.JScrollPane();
		MsgSendBox = new javax.swing.JTextArea();
		
		setBounds(10, 10, 850, 500);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);

		jPanel1.setBackground(new java.awt.Color(255, 255, 246));
		jPanel1.setLayout(null);
		jPanel1.setBounds(0, 0, 200, 90);
		add(jPanel1);
		
		jPanel2.setBackground(new java.awt.Color(204, 255, 204));
		jPanel2.setLayout(null);
		jPanel2.setBounds(0, 90, 200, 500-90);
		add(jPanel2);
		
		jPanel3.setBackground(new java.awt.Color(255, 231, 231));
		jPanel3.setLayout(null);
		jPanel3.setBounds(200, 0, 850-200, 500);
		add(jPanel3);
		
		Name.setEditable(false);
		Name.setText("myName");

		Account.setEditable(false);
		Account.setText("1230612306");

		Status.setEditable(false);
		Status.setText("Status");
		
	//	jPanel2
	}

}
