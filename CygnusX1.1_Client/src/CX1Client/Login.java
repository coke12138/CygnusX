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

		jLabel3.setFont(new java.awt.Font("���� Light", 0, 12));
		jLabel3.setText("\u6ce8\u518c\u65b0\u8d26\u53f7");
		jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel3MouseClicked(evt);
			}
		});

		jButton1.setFont(new java.awt.Font("΢���ź� Light", 0, 24));
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
		// ���ע�����û�
		System.out.println("L:�����ע���ǩ");
		EventQueue.invokeLater(new Runnable() {//�Ժ����
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
		//�����¼��ť��
		Massager massager=new Massager();
		boolean isLoginSend=false;
		//��ʼ��sender
		massager.MsgSender();
		isLoginSend=massager.sendLogin(account.getText(), String.valueOf(jPasswordField1.getPassword()));
		//�����ͳɹ��Ž��Ż�ȡ���
		if(isLoginSend) {
			//��÷��ؽ����ȷ���ܷ�ɹ���¼������ɹ��ʹ�������
			massager.getMassage();
			int result=massager.getMsgType();
			if(result==1) {
				System.out.println("L:OK!");
				//��������//��ChatFriend����
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						//����ID
						String id=account.getText();
						new ChatView_test(id).setVisible(true);//�½���
					//	new ChatFriend().setVisible(true);
					}
				});
				//�رյ�¼����
				thisLoginJframe.dispose();
			}
			else {
				System.out.println("L:���ش���Ϊ:"+result);
				//��ʾ��½ʧ��
				LoginTip.setForeground(Color.red);
				LoginTip.setText("��½ʧ��,�����û���������");
			}
		}
		else {
			//��ʾ��½ʧ��
			LoginTip.setForeground(Color.red);
			LoginTip.setText("L:��½ʧ��,�����û���������");
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