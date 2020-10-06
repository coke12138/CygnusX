package CX1Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ClientTools.Massager;

//������Ӻ��ѵĵ���
public class AddFriendTanChuang extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField IDField;
	private JDialog thisDialog;
	private JLabel TipLabel1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddFriendTanChuang dialog = new AddFriendTanChuang();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddFriendTanChuang(String Myid,Massager m) {
		thisDialog=this;
		setTitle("\u6DFB\u52A0\u597D\u53CB");
		setBounds(100, 100, 345, 188);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		IDField = new JTextField();
		IDField.setText("");
		IDField.setBounds(164, 43, 84, 21);
		contentPanel.add(IDField);
		IDField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u5F85\u6DFB\u52A0\u8D26\u53F7");
		lblNewLabel.setFont(new Font("΢ܛ�����w", Font.PLAIN, 14));
		lblNewLabel.setBounds(70, 40, 84, 25);
		contentPanel.add(lblNewLabel);
		{
			TipLabel1=new JLabel("");
			TipLabel1.setBounds(70, 75, 178, 21);
			contentPanel.add(TipLabel1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u641C\u7D22\u5E76\u6DFB\u52A0");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//��Ӱ�ť�¼�
						String friendId=IDField.getText();
						if(!Myid.equals(friendId)) {//��������Լ�
							//��������
							m.AddDelFriend(Myid, friendId,"5");//��Ӻ���-5
							TipLabel1.setForeground(Color.green);
							TipLabel1.setText("�� �� �� �� ��...");
						}
						else {
							TipLabel1.setForeground(Color.pink);
							TipLabel1.setText("��������Լ���");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("�ر�");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//ȡ����ť�¼�
						thisDialog.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
