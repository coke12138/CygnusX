package CX1Client;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ClientTools.Massager;

public class Registered extends JFrame{

	/**
	 * �û�ע�����
	 */
	private static final long serialVersionUID = 1L;//�Զ����
	private JFrame theRegisterWindows;
	private JPanel contentPane;
	private JTextField accNum,nicName;
	private JPasswordField pwdText,confPwd;
	private JDialog Tframe,Rframe;
	private JButton jb_1,jb_2;
	private String pwdTexttext,accNumtext,nicNametext,confPwdtext;
	private JLabel tip_1,tip_2,tip_3,tip_4,tip_5;
	private chooseDate Cd;
	private JComboBox<String> birYear,birMonth,birDay;
	private JRadioButton manChoose,womanChoose;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		//������
		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public Registered() throws ParseException {
		theRegisterWindows=this;
		setTitle("\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//ɶҲ����������ʾ
		setBounds(100, 100, 450, 377);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);this.getContentPane();
		
		JLabel accountNumber = new JLabel("\u8D26\u53F7*");//�˺�
		accountNumber.setBounds(75, 60, 54, 15);
		contentPane.add(accountNumber);

		JLabel nickname = new JLabel("\u6635\u79F0*");//�ǳ�
		nickname.setBounds(75, 90, 54, 15);
		contentPane.add(nickname);

		JLabel pwd = new JLabel("\u5BC6\u7801*");//����
		pwd.setBounds(75, 120, 54, 15);
		contentPane.add(pwd);

		JLabel confirmpwd = new JLabel("\u786E\u8BA4\u5BC6\u7801*");//����ȷ��
		confirmpwd.setBounds(75, 150, 54, 15);
		contentPane.add(confirmpwd);

		JLabel gender = new JLabel("\u6027\u522B");//�Ա�
		gender.setBounds(75, 180, 54, 15);
		contentPane.add(gender);

		JLabel birthday = new JLabel("\u51FA\u751F\u65E5\u671F");//��������
		birthday.setBounds(75, 210, 54, 15);
		contentPane.add(birthday);

		JButton registered = new JButton("\u6CE8\u518C");//ע��
		registered.setBounds(75, 279, 93, 23);
		contentPane.add(registered);
		tip_5 = new JLabel("");//��ʾ
		tip_5.setBounds(75, 305, 93, 15);
		tip_5.setForeground(Color.pink);
		contentPane.add(tip_5);

		JButton cancel = new JButton("\u53D6\u6D88");//ȡ��
		jb_1=new JButton("ȷ��");//ѯ�ʡ��˳��������еİ�ť
        jb_2=new JButton("ȡ��");
		cancel.setBounds(252, 279, 93, 23);
		contentPane.add(cancel);

		JLabel tip1 = new JLabel("\u8BF7\u8F93\u5165\u4F60\u7684\u6CE8\u518C\u4FE1\u606F\uFF1A");
		tip1.setFont(new Font("΢ܛ�����w", Font.PLAIN, 20));
		tip1.setBounds(1, 1, 226, 39);
		contentPane.add(tip1);

		accNum = new JTextField();//�˺�
		accNum.setBounds(191, 57, 110, 21);
		contentPane.add(accNum);
		accNum.setColumns(10);
		tip_1 = new JLabel("");
		tip_1.setBounds(300, 60, 54, 15);
		tip_1.setForeground(Color.pink);
		contentPane.add(tip_1);
		accNum.addFocusListener((FocusListener) new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				accNumtext=accNum.getText();
				if(accNumtext.equals("")) {
					tip_1.setText("����Ϊ��");
				}
				else {
					tip_1.setText("");
				}
			}
		});

		nicName = new JTextField();//�ǳ�
		nicName.setColumns(10);
		nicName.setBounds(191, 87, 110, 21);
		contentPane.add(nicName);
		tip_2 = new JLabel("");
		tip_2.setBounds(300, 90, 54, 15);
		tip_2.setForeground(Color.pink);
		
		contentPane.add(tip_2);
		nicName.addFocusListener((FocusListener) new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				nicNametext=nicName.getText();
				if(nicNametext.equals("")) {
					tip_2.setText("����Ϊ��");
				}
				else {
					tip_2.setText("");
				}
			}
		});

		pwdText = new JPasswordField();//����
		pwdText.setBounds(191, 117, 110, 21);
		contentPane.add(pwdText);
		tip_3 = new JLabel("");
		tip_3.setBounds(300, 120, 140, 15);
		tip_3.setForeground(Color.pink);
		contentPane.add(tip_3);
		pwdText.addFocusListener((FocusListener) new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				pwdTexttext=String.valueOf(pwdText.getPassword());
				if(pwdTexttext.equals("")) {
					tip_3.setText("����Ϊ��");
				}
				else if(pwdTexttext.length()<6) {
					tip_3.setText("���볤������Ϊ6λ");
				}
				else {
					tip_3.setText("");
				}
			}
		});

		confPwd = new JPasswordField();//ȷ������
		confPwd.setBounds(191, 147, 110, 21);
		contentPane.add(confPwd);
		tip_4 = new JLabel("");
		tip_4.setBounds(300, 150, 140, 15);
		tip_4.setForeground(Color.pink);
		contentPane.add(tip_4);
		confPwd.addFocusListener((FocusListener) new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				confPwdtext=String.valueOf(confPwd.getPassword());
				if(confPwdtext.equals("")) {
					tip_4.setText("����Ϊ��");
				}
				else if(!confPwdtext.equals(pwdTexttext)) {
					tip_4.setText("������������벻һ��");
				}
				else {
					tip_4.setText("");
				}
			}
		});

		manChoose = new JRadioButton("\u7537");//ѡ����
		manChoose.setBounds(201, 176, 37, 23);
		contentPane.add(manChoose);

		womanChoose = new JRadioButton("\u5973");//ѡ��Ů
		womanChoose.setBounds(252, 176, 37, 23);
		contentPane.add(womanChoose);

		ButtonGroup buttongroup = new ButtonGroup();
		buttongroup.add(manChoose);
		buttongroup.add(womanChoose);

		birYear = new JComboBox<String>();//���ѡ��
		Cd = new chooseDate();// ������¼�ͼ���
		birYear.setBounds(191, 206, 66, 23);
		contentPane.add(birYear);
		birYear.addItem("ѡ����");
		for (int i = 2010; i >= 1997; i--) {//����"��"�б�
			birYear.addItem(i + "");
		}
		birYear.addActionListener(new ActionListener() {//����"��"

			public void actionPerformed(ActionEvent e) {
				Cd.setConfirmYear(2011 - birYear.getSelectedIndex());
				System.out.println("��:" + Cd.getConfirmYear());
			}
			
		});

		birDay = new JComboBox<String>();//�յ�ѡ��
		birDay.setBounds(191, 239, 66, 23);
		birDay.addItem("ѡ����");
		contentPane.add(birDay);
		
		birMonth = new JComboBox<String>();//�µ�ѡ��
		birMonth.setBounds(279, 206, 66, 23);
		contentPane.add(birMonth);
		birMonth.addItem("ѡ����");
		for (int i = 1; i <= 12; i++) {//����"��"�б�
			birMonth.addItem(i + "");
		}
		birMonth.addActionListener(new ActionListener() {//����"��"
			public void actionPerformed(ActionEvent e) {
				Cd.setConfirmMonth(birMonth.getSelectedIndex());
				int DaysNumber = Cd.getDaysNumber();
				//������б�
				birDay.removeAllItems();
				for (int i = 1; i <= DaysNumber; i++) {//ѡ����֮�������"ѡ����"�����˵�
					birDay.addItem(i + "");
				}
				System.out.println("��:" + Cd.getConfirmMonth());
			}
		});
		
		birDay.addActionListener(new ActionListener() {//����"��"
			public void actionPerformed(ActionEvent e) {
				Cd.setConfirmDay(birDay.getSelectedIndex()+1);
				System.out.println("��:" + Cd.getConfirmDay());
			}
		});

		JLabel month = new JLabel("\u6708");
		month.setBounds(351, 210, 20, 15);
		contentPane.add(month);

		JLabel years = new JLabel("\u5E74");
		years.setBounds(262, 210, 26, 15);
		contentPane.add(years);

		JLabel day = new JLabel("\u65E5");
		day.setBounds(262, 243, 26, 15);
		contentPane.add(day);

		registered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���ע�ᰴť�������Ϸ���
				if(!accNumtext.equals("")&&!nicNametext.equals("")&&!pwdTexttext.equals("")&&
						pwdTexttext.length()>=6&&!confPwdtext.equals("")&&confPwdtext.equals(pwdTexttext))
				{
					//�������ͨ��
					//�����������ע����Ϣ
					Massager massager=new Massager();
					//��ʼ��send
					massager.MsgSender();
					boolean isRegistered=false;
					String birdayStr;
					//"����"�ֶ�
					birdayStr=BirthdaySend();
					//�����Ա�
					int sex=-10;
					if(manChoose.isSelected()) sex=0;else if(womanChoose.isSelected()) sex=1;else sex=-10;
					//����
					isRegistered=massager.sendRegistered(accNumtext,nicNametext,pwdTexttext,sex,birdayStr);
					//���ս��
					massager.getMassage();
					//�ղ�����Ϣ-�������
					//�յ�ע��ʧ��-ID�ظ�
					//�յ�ע��ɹ�-��ʾ���
					if(!isRegistered) {
						//�������ʧ��
						tip_5.setText("��������");
					}
					else if(massager.getMsgType()==-2) {
						tip_5.setText("ID�ѱ�ע��");
					}
					else {
						//���ͳɹ�
						//���շ���������ֵ,�������ʧ�ܻ��޷�������ʾ��������
						
						//������ʾ����ʾע����Ϣ
						Rframe = new JDialog();//����һ���µ�JFrame����Ϊ�´���
						Rframe.setBounds(200,200,450,300);
						Rframe.setLayout(null);
						Rframe.setResizable(false);
						Rframe.setTitle("ע��ɹ�");
						
						JLabel Rjl = new JLabel();Rjl.setForeground(Color.blue);Rjl.setBounds(165, 2, 109, 43);
						Rjl.setText("ע��ɹ���");Rjl.setFont(new Font("΢ܛ�����w", Font.PLAIN, 21));
						JButton Rjb1=new JButton("ȷ��");Rjb1.setBounds(168, 228, 93, 23);
						Rframe.add(Rjl);
						Rframe.add(Rjb1);
						
						//��ʾע����Ϣ //<html>1<br>2</html>//ȫ�ǿո�\u3000
						String DisplayString="<html>ע����Ϣ:<br>\u3000\u3000�˺�:"+accNumtext+"<br>\u3000\u3000�ǳ�:"+
								nicNametext+"<br>\u3000\u3000����"+pwdTexttext+"<br>";
						if(manChoose.isSelected())DisplayString+="\u3000\u3000�Ա�:��<br>";//�Ա�
						else if(womanChoose.isSelected())DisplayString+="\u3000\u3000�Ա�:Ů<br>";
						//��������
						if(BirthdaySend()!=null)
							DisplayString+="\u3000\u3000��������(��/��/��):"+BirthdaySend();
							
						JLabel tipSucceed = new JLabel(DisplayString+"<html>");tipSucceed.setBounds(103, 45, 349, 173);
						tipSucceed.setFont(new Font("΢ܛ�����w", Font.PLAIN, 15));
						Rframe.add(tipSucceed);
						
						Rjb1.addActionListener(new ActionListener() {//���ȷ���˳�����
							public void actionPerformed(ActionEvent e) {
								Rframe.dispose();
								theRegisterWindows.dispose();
							}
						});
						Rframe.addWindowListener(new WindowAdapter() {//������Ͻǹرյ�������
							public void windowClosing(WindowEvent e) {
								Rframe.dispose();
								theRegisterWindows.dispose();
							}
						});
						Rframe.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);    // ����ģʽ����������������
						Rframe.setVisible(true);
						
					}
						
				}
				
				else {
					//������鲻ͨ��
					tip_5.setText("����ע����Ϣ");
				}
			}
		});
		cancel.addActionListener(new ActionListener() {//���ȡ����ť�ᵯ��һ��ѯ�ʴ���
			public void actionPerformed(ActionEvent e) {
				ConfirmExit();
			}
		});
		
		jb_1.addActionListener(new ActionListener() {//�˳�ע�ᣬ�رմ���
			public void actionPerformed(ActionEvent e) {
				Tframe.dispose();
				theRegisterWindows.dispose();
			}
        });
        jb_2.addActionListener(new ActionListener() {//ȡ���˳�ע�ᣬ����ע��
			public void actionPerformed(ActionEvent e) {
				Tframe.dispose();
			}
        });
        addWindowListener(new WindowAdapter() {//������Ͻǹرյ�������
			public void windowClosing(WindowEvent e) {
				ConfirmExit();
			}
		});
	}
	private void ConfirmExit() {//ȷ���˳���
		Tframe = new JDialog();//����һ���µ�JFrame����Ϊ�´��ڡ�
        Tframe.setBounds(200,200,260,210);
        Tframe.setResizable(false);
        Tframe.setTitle("�˳�");
        Tframe.getContentPane().setLayout(null);
        JLabel Tjl = new JLabel();
        Tframe.getContentPane().add(Tjl);Tjl.setBounds(85, 40, 100, 20);
        Tframe.getContentPane().add(jb_1);jb_1.setBounds(40, 90, 70, 25);
        Tframe.getContentPane().add(jb_2);jb_2.setBounds(130, 90, 70, 25);
        Tjl.setText("�˳�ע����");
        Tframe.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);//������������
        Tframe.setVisible(true);
	}
	private String BirthdaySend() {
		//�����ڷ���ע����Ϣʱ�жϳ��������ֶε�����
		String str="";
		if(birYear.getSelectedIndex()!=0) {//��������
			str+=Cd.getConfirmYear()+"#";
			if(birMonth.getSelectedIndex()!=0) {
				str+=Cd.getConfirmMonth()+"#";
				if(birDay.getSelectedIndex()!=0)
					str+=Cd.getConfirmDay();
				else
					str+="1";
			}
			else
				str+="1#1#";
		}
		else str=null;
		return str;
	}
}

class chooseDate {
	Calendar calendar = Calendar.getInstance();
	int ConfirmYear, ConfirmMonth, ConfirmDay, daysnumber;
	public int getDaysNumber() {
		int key = 0;
		if (ConfirmMonth == 1 || ConfirmMonth == 3 || ConfirmMonth == 5 || ConfirmMonth == 7 || ConfirmMonth == 8
				|| ConfirmMonth == 10 || ConfirmMonth == 12)
			key = 31;
		else if (ConfirmMonth != 2)
			key = 30;
		else if (ConfirmYear % 4 == 0)
			key = 29;
		else
			key = 28;
		return key;
	}
	public void setDaysnumber(int daysnumber) {
		this.daysnumber = daysnumber;
	}
	public void setDate(int year, int month) {
		calendar.set(year, month, 0);
	}
	public int getConfirmMonth() {
		return ConfirmMonth;
	}
	public void setConfirmMonth(int confirmMonth) {
		ConfirmMonth = confirmMonth;
	}
	public int getConfirmDay() {
		return ConfirmDay;
	}
	public void setConfirmDay(int confirmDay) {
		ConfirmDay = confirmDay;
	}
	public int getConfirmYear() {
		return ConfirmYear;
	}
	public void setConfirmYear(int year) {
		ConfirmYear = year;
	}
}