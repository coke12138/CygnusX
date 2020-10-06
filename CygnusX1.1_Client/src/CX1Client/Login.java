package CX1Client;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import ClientTools.Massager;

public class Login extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame thisLoginJframe;

	/** Creates new form Login */
	public Login() {
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		thisLoginJframe=this;
		this.setVisible(true);
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		account = new javax.swing.JTextField();
		jPasswordField1 = new javax.swing.JPasswordField();
		jLabel3 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		LoginTip = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("CygnusX1");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14));
		jLabel1.setText("\u8d26\u53f7");

		jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14));
		jLabel2.setText("\u5bc6\u7801");

		jLabel3.setFont(new java.awt.Font("等线 Light", 0, 12));
		jLabel3.setText("\u6ce8\u518c\u65b0\u8d26\u53f7");
		jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel3MouseClicked(evt);
			}
		});

		jButton1.setFont(new java.awt.Font("微软雅黑 Light", 0, 24));
		jButton1.setText("\u767b\u5f55");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabel3)
																		.addGap(67,
																				67,
																				67)
																		.addComponent(
																				LoginTip,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				169,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(65,
																				65,
																				65)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jButton1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								266,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel1Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel1)
																														.addComponent(
																																jLabel2))
																										.addGap(42,
																												42,
																												42)
																										.addGroup(
																												jPanel1Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																account,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																196,
																																Short.MAX_VALUE)
																														.addComponent(
																																jPasswordField1,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																196,
																																Short.MAX_VALUE))))))
										.addGap(67, 67, 67)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(48, 48, 48)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																account,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																jPasswordField1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(20, 20, 20)
										.addComponent(
												jButton1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												45, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																LoginTip,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {
		// 点击注册新用户
		System.out.println("L:点击了注册标签");
		EventQueue.invokeLater(new Runnable() {//稍后调用
			public void run() {
				try {
					Registered frame = new Registered();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		//点击登录按钮后
		Massager massager=new Massager();
		boolean isLoginSend=false;
		//初始化sender
		massager.MsgSender();
		isLoginSend=massager.sendLogin(account.getText(), String.valueOf(jPasswordField1.getPassword()));
		//若发送成功才接着获取结果
		if(isLoginSend) {
			//获得返回结果，确定能否成功登录，如果成功就打开主界面
			massager.getMassage();
			int result=massager.getMsgType();
			if(result==1) {
				System.out.println("L:OK!");
				//打开主界面//打开ChatFriend界面
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						//传参ID
						String id=account.getText();
						new ChatView_test(id).setVisible(true);//新界面
					//	new ChatFriend().setVisible(true);
					}
				});
				//关闭登录窗口
				thisLoginJframe.dispose();
			}
			else {
				System.out.println("L:返回代号为:"+result);
				//提示登陆失败
				LoginTip.setForeground(Color.red);
				LoginTip.setText("登陆失败,请检查用户名或密码");
			}
		}
		else {
			//提示登陆失败
			LoginTip.setForeground(Color.red);
			LoginTip.setText("L:登陆失败,请检查用户名或密码");
		}
	}

	/**
	 * @param args the command line arguments
	 */

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel LoginTip;
	private javax.swing.JTextField account;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPasswordField jPasswordField1;
	// End of variables declaration//GEN-END:variables

}