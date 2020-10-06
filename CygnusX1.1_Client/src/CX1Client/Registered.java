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
	 * 用户注册界面
	 */
	private static final long serialVersionUID = 1L;//自动添加
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
		//测试用
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//啥也不做弹出提示
		setBounds(100, 100, 450, 377);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);this.getContentPane();
		
		JLabel accountNumber = new JLabel("\u8D26\u53F7*");//账号
		accountNumber.setBounds(75, 60, 54, 15);
		contentPane.add(accountNumber);

		JLabel nickname = new JLabel("\u6635\u79F0*");//昵称
		nickname.setBounds(75, 90, 54, 15);
		contentPane.add(nickname);

		JLabel pwd = new JLabel("\u5BC6\u7801*");//密码
		pwd.setBounds(75, 120, 54, 15);
		contentPane.add(pwd);

		JLabel confirmpwd = new JLabel("\u786E\u8BA4\u5BC6\u7801*");//密码确认
		confirmpwd.setBounds(75, 150, 54, 15);
		contentPane.add(confirmpwd);

		JLabel gender = new JLabel("\u6027\u522B");//性别
		gender.setBounds(75, 180, 54, 15);
		contentPane.add(gender);

		JLabel birthday = new JLabel("\u51FA\u751F\u65E5\u671F");//出生日期
		birthday.setBounds(75, 210, 54, 15);
		contentPane.add(birthday);

		JButton registered = new JButton("\u6CE8\u518C");//注册
		registered.setBounds(75, 279, 93, 23);
		contentPane.add(registered);
		tip_5 = new JLabel("");//提示
		tip_5.setBounds(75, 305, 93, 15);
		tip_5.setForeground(Color.pink);
		contentPane.add(tip_5);

		JButton cancel = new JButton("\u53D6\u6D88");//取消
		jb_1=new JButton("确认");//询问‘退出弹出框中的按钮
        jb_2=new JButton("取消");
		cancel.setBounds(252, 279, 93, 23);
		contentPane.add(cancel);

		JLabel tip1 = new JLabel("\u8BF7\u8F93\u5165\u4F60\u7684\u6CE8\u518C\u4FE1\u606F\uFF1A");
		tip1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		tip1.setBounds(1, 1, 226, 39);
		contentPane.add(tip1);

		accNum = new JTextField();//账号
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
					tip_1.setText("不能为空");
				}
				else {
					tip_1.setText("");
				}
			}
		});

		nicName = new JTextField();//昵称
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
					tip_2.setText("不能为空");
				}
				else {
					tip_2.setText("");
				}
			}
		});

		pwdText = new JPasswordField();//密码
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
					tip_3.setText("不能为空");
				}
				else if(pwdTexttext.length()<6) {
					tip_3.setText("密码长度至少为6位");
				}
				else {
					tip_3.setText("");
				}
			}
		});

		confPwd = new JPasswordField();//确认密码
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
					tip_4.setText("不能为空");
				}
				else if(!confPwdtext.equals(pwdTexttext)) {
					tip_4.setText("两次输入的密码不一致");
				}
				else {
					tip_4.setText("");
				}
			}
		});

		manChoose = new JRadioButton("\u7537");//选择男
		manChoose.setBounds(201, 176, 37, 23);
		contentPane.add(manChoose);

		womanChoose = new JRadioButton("\u5973");//选择女
		womanChoose.setBounds(252, 176, 37, 23);
		contentPane.add(womanChoose);

		ButtonGroup buttongroup = new ButtonGroup();
		buttongroup.add(manChoose);
		buttongroup.add(womanChoose);

		birYear = new JComboBox<String>();//年的选择
		Cd = new chooseDate();// 用来记录和计算
		birYear.setBounds(191, 206, 66, 23);
		contentPane.add(birYear);
		birYear.addItem("选择年");
		for (int i = 2010; i >= 1997; i--) {//生成"年"列表
			birYear.addItem(i + "");
		}
		birYear.addActionListener(new ActionListener() {//监听"年"

			public void actionPerformed(ActionEvent e) {
				Cd.setConfirmYear(2011 - birYear.getSelectedIndex());
				System.out.println("年:" + Cd.getConfirmYear());
			}
			
		});

		birDay = new JComboBox<String>();//日的选择
		birDay.setBounds(191, 239, 66, 23);
		birDay.addItem("选择天");
		contentPane.add(birDay);
		
		birMonth = new JComboBox<String>();//月的选择
		birMonth.setBounds(279, 206, 66, 23);
		contentPane.add(birMonth);
		birMonth.addItem("选择月");
		for (int i = 1; i <= 12; i++) {//生成"月"列表
			birMonth.addItem(i + "");
		}
		birMonth.addActionListener(new ActionListener() {//监听"月"
			public void actionPerformed(ActionEvent e) {
				Cd.setConfirmMonth(birMonth.getSelectedIndex());
				int DaysNumber = Cd.getDaysNumber();
				//先清空列表
				birDay.removeAllItems();
				for (int i = 1; i <= DaysNumber; i++) {//选择月之后就设置"选择天"下拉菜单
					birDay.addItem(i + "");
				}
				System.out.println("月:" + Cd.getConfirmMonth());
			}
		});
		
		birDay.addActionListener(new ActionListener() {//监听"日"
			public void actionPerformed(ActionEvent e) {
				Cd.setConfirmDay(birDay.getSelectedIndex()+1);
				System.out.println("日:" + Cd.getConfirmDay());
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
				//点击注册按钮检查参数合法性
				if(!accNumtext.equals("")&&!nicNametext.equals("")&&!pwdTexttext.equals("")&&
						pwdTexttext.length()>=6&&!confPwdtext.equals("")&&confPwdtext.equals(pwdTexttext))
				{
					//参数检查通过
					//向服务器发送注册信息
					Massager massager=new Massager();
					//初始化send
					massager.MsgSender();
					boolean isRegistered=false;
					String birdayStr;
					//"生日"字段
					birdayStr=BirthdaySend();
					//设置性别
					int sex=-10;
					if(manChoose.isSelected()) sex=0;else if(womanChoose.isSelected()) sex=1;else sex=-10;
					//发送
					isRegistered=massager.sendRegistered(accNumtext,nicNametext,pwdTexttext,sex,birdayStr);
					//接收结果
					massager.getMassage();
					//收不到信息-检查网络
					//收到注册失败-ID重复
					//收到注册成功-显示结果
					if(!isRegistered) {
						//如果发送失败
						tip_5.setText("请检查网络");
					}
					else if(massager.getMsgType()==-2) {
						tip_5.setText("ID已被注册");
					}
					else {
						//发送成功
						//接收服务器返回值,如果返回失败或无返回则提示请检查网络
						
						//弹出提示框，显示注册信息
						Rframe = new JDialog();//构造一个新的JFrame，作为新窗口
						Rframe.setBounds(200,200,450,300);
						Rframe.setLayout(null);
						Rframe.setResizable(false);
						Rframe.setTitle("注册成功");
						
						JLabel Rjl = new JLabel();Rjl.setForeground(Color.blue);Rjl.setBounds(165, 2, 109, 43);
						Rjl.setText("注册成功！");Rjl.setFont(new Font("微軟正黑體", Font.PLAIN, 21));
						JButton Rjb1=new JButton("确定");Rjb1.setBounds(168, 228, 93, 23);
						Rframe.add(Rjl);
						Rframe.add(Rjb1);
						
						//显示注册信息 //<html>1<br>2</html>//全角空格\u3000
						String DisplayString="<html>注册信息:<br>\u3000\u3000账号:"+accNumtext+"<br>\u3000\u3000昵称:"+
								nicNametext+"<br>\u3000\u3000密码"+pwdTexttext+"<br>";
						if(manChoose.isSelected())DisplayString+="\u3000\u3000性别:男<br>";//性别
						else if(womanChoose.isSelected())DisplayString+="\u3000\u3000性别:女<br>";
						//出生日期
						if(BirthdaySend()!=null)
							DisplayString+="\u3000\u3000出生日期(年/月/日):"+BirthdaySend();
							
						JLabel tipSucceed = new JLabel(DisplayString+"<html>");tipSucceed.setBounds(103, 45, 349, 173);
						tipSucceed.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
						Rframe.add(tipSucceed);
						
						Rjb1.addActionListener(new ActionListener() {//点击确定退出窗口
							public void actionPerformed(ActionEvent e) {
								Rframe.dispose();
								theRegisterWindows.dispose();
							}
						});
						Rframe.addWindowListener(new WindowAdapter() {//点击右上角关闭弹出窗口
							public void windowClosing(WindowEvent e) {
								Rframe.dispose();
								theRegisterWindows.dispose();
							}
						});
						Rframe.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);    // 设置模式类型阻塞其他窗口
						Rframe.setVisible(true);
						
					}
						
				}
				
				else {
					//参数检查不通过
					tip_5.setText("请检查注册信息");
				}
			}
		});
		cancel.addActionListener(new ActionListener() {//点击取消按钮会弹出一个询问窗口
			public void actionPerformed(ActionEvent e) {
				ConfirmExit();
			}
		});
		
		jb_1.addActionListener(new ActionListener() {//退出注册，关闭窗口
			public void actionPerformed(ActionEvent e) {
				Tframe.dispose();
				theRegisterWindows.dispose();
			}
        });
        jb_2.addActionListener(new ActionListener() {//取消退出注册，继续注册
			public void actionPerformed(ActionEvent e) {
				Tframe.dispose();
			}
        });
        addWindowListener(new WindowAdapter() {//点击右上角关闭弹出窗口
			public void windowClosing(WindowEvent e) {
				ConfirmExit();
			}
		});
	}
	private void ConfirmExit() {//确认退出吗
		Tframe = new JDialog();//构造一个新的JFrame，作为新窗口。
        Tframe.setBounds(200,200,260,210);
        Tframe.setResizable(false);
        Tframe.setTitle("退出");
        Tframe.getContentPane().setLayout(null);
        JLabel Tjl = new JLabel();
        Tframe.getContentPane().add(Tjl);Tjl.setBounds(85, 40, 100, 20);
        Tframe.getContentPane().add(jb_1);jb_1.setBounds(40, 90, 70, 25);
        Tframe.getContentPane().add(jb_2);jb_2.setBounds(130, 90, 70, 25);
        Tjl.setText("退出注册吗？");
        Tframe.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);//阻塞其他窗口
        Tframe.setVisible(true);
	}
	private String BirthdaySend() {
		//用于在发送注册信息时判断出生日期字段的内容
		String str="";
		if(birYear.getSelectedIndex()!=0) {//出生日期
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