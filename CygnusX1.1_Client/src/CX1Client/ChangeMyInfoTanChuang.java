package CX1Client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientTools.Massager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class ChangeMyInfoTanChuang extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JDialog thisDialog;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<String>birYear,birMonth,birDay;
	private chooseDate Cd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new ChangeMyInfoTanChuang("0000","Name","1","2009","10","10").setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChangeMyInfoTanChuang(String id,String thename,String thesex,String theyear,String themonth,String theday) {
		setResizable(false);
		thisDialog=this;
		setTitle("\u4FEE\u6539\u4FE1\u606F");
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u4F60\u7684\u8D26\u53F7");
			lblNewLabel.setBounds(87, 10, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField(id);
			textField.setEditable(false);
			textField.setBounds(151, 7, 100, 21);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u6635\u79F0");
			lblNewLabel_1.setBounds(87, 40, 54, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("\u6027\u522B");
			lblNewLabel_2.setBounds(87, 70, 54, 15);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("\u51FA\u751F\u65E5\u671F");
			lblNewLabel_3.setBounds(87, 100, 54, 15);
			contentPanel.add(lblNewLabel_3);
		}
		{
			textField_1 = new JTextField(thename);
			textField_1.setBounds(151, 37, 100, 21);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u7537");
		rdbtnNewRadioButton.setBounds(155, 66, 37, 23);
		contentPanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u5973");
		rdbtnNewRadioButton_1.setBounds(210, 66, 37, 23);
		
		ButtonGroup buttongroup = new ButtonGroup();
		buttongroup.add(rdbtnNewRadioButton);
		buttongroup.add(rdbtnNewRadioButton_1);
		
		//设置性别的默认选项
		if(thesex.equals("男")) {
			rdbtnNewRadioButton.setSelected(true);
		}else if(thesex.equals("女")) {
			rdbtnNewRadioButton_1.setSelected(true);
		}
		
		contentPanel.add(rdbtnNewRadioButton_1);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//确认修改
						//向服务器发送信息
						Massager massager=new Massager();
						//初始化send
						massager.MsgSender();
						
						//昵称
						String nameTemp=textField_1.getText();
						//设置性别
						int sex=-10;
						if(rdbtnNewRadioButton.isSelected()) sex=0;
						else if(rdbtnNewRadioButton_1.isSelected()) sex=1;
						else sex=-10;
						//"生日"字段
						String birdayTemp=BirthdaySend(Cd);
						
						massager.sendChangeInfo(id, nameTemp, sex, birdayTemp);
						System.out.println("CMTC:修改信息:"+id+" "+nameTemp+" "+sex+" "+birdayTemp+".");
					}
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u5173\u95ED");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//关闭按钮
						thisDialog.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		{
			birYear = new JComboBox<String>();//年的选择
			Cd = new chooseDate();// 用来记录和计算
			birYear.setBounds(151, 95, 66, 23);
			contentPanel.add(birYear);
			birYear.addItem("选择年");
			for (int i = 2010; i >= 1997; i--) {//生成"年"列表
				birYear.addItem(i + "");
				//设置默认年份
				if(Integer.parseInt(theyear)==i) {
					birYear.setSelectedIndex(birYear.getItemCount()-1);
					Cd.setConfirmYear(2011 - birYear.getSelectedIndex());
				}
			}
			birYear.addActionListener(new ActionListener() {//监听"年"

				public void actionPerformed(ActionEvent e) {
					Cd.setConfirmYear(2011 - birYear.getSelectedIndex());
					System.out.println("年:" + Cd.getConfirmYear());
				}
				
			});

			birDay = new JComboBox<String>();//日的选择
			birDay.setBounds(151, 125, 66, 23);
			birDay.addItem("选择天");
			contentPanel.add(birDay);
			
			birMonth = new JComboBox<String>();//月的选择
			birMonth.setBounds(151+90, 95, 66, 23);
			contentPanel.add(birMonth);
			birMonth.addItem("选择月");
			for (int i = 1; i <= 12; i++) {//生成"月"列表
				birMonth.addItem(i + "");
				//设置默认月份
				if(Integer.parseInt(themonth)==i) {
					birMonth.setSelectedIndex(birMonth.getItemCount()-1);
					Cd.setConfirmMonth(birMonth.getSelectedIndex());
					for (int j = 1; j <= Cd.getDaysNumber(); j++) {
						//这个循环是为了设置默认天数,不是初始化
						birDay.addItem(j + "");
						//设置默认天数
						if(Integer.parseInt(theday)==j) {
							birDay.setSelectedIndex(birDay.getItemCount()-1);
							Cd.setConfirmDay(birDay.getSelectedIndex());
							break;
						}
					}
					
				}
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
			month.setBounds(310, 100, 20, 15);
			contentPanel.add(month);

			JLabel years = new JLabel("\u5E74");
			years.setBounds(151+70, 100, 26, 15);
			contentPanel.add(years);

			JLabel day = new JLabel("\u65E5");
			day.setBounds(151+70, 130, 26, 15);
			contentPanel.add(day);
		}
		
	}
	private String BirthdaySend(chooseDate Cd) {
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
