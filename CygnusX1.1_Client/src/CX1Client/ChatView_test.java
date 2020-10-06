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
 * �����µĽ�����2020/6/18
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
	
	private Massager msger=null, m=null;//msger��������,m��������
	
	private String myname,mysex,mybir_day,mybir_month,mybir_year;
	
	private JPopupMenu popupMenu;
	private JMenuItem menuItem;
	private int delLabel_x,delLabel_y;
	private String delFriendLabel,chatwithID="-1",offlineContent=null;
	private TIMEer timer=new TIMEer();
	private int hasContent=-1;
	private String[] theFIDstr;//��ǰ���еĺ���id

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//������
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
		System.out.println("CV:���������������");
		init(id);//��ʼ��
		Update(id);//����
	}
	private void init(String id) {
		msger=new Massager();//��Ϣ�շ�
		
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
				//������Ϣ��ť
			//	msger.MsgSender();
				String msgText=MsgSendtextArea.getText();
				if(chatwithID==null) {
					ChatTextField.setText("û���������");
					System.out.println("CV:û���������");
				}
				else if(msgText.equals("")) {
					ChatTextField.setText("��������Ϊ�գ�");
					System.out.println("CV:��������Ϊ�գ�");
				}
				else {
					//��������������ʾ�����촰��
					ChatTextField.setText(ChatTextField.getText()+"->"+id+" "+timer.getTIME()+"\n"+msgText+"\n");
					//��������
					MsgSendtextArea.setText("");
					msger.MsgSender();
					msger.SendCommonMsg(id, chatwithID, msgText);
				}
			}
		});
		SendMsgButton.setBounds(730, 525, 69, 23);
		panel_2.add(SendMsgButton);
		
		ChatWithName=new JLabel("����");
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
				//��Ӻ���
				//��������
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
				//�޸����ϰ�ť
				new ChangeMyInfoTanChuang(id,myname,mysex,mybir_year,mybir_month,mybir_day).setVisible(true);
			}
		});
		RevisedInfoButton.setBounds(158, 0, 93, 23);
		panel.add(RevisedInfoButton);
		
		MyStatusLabel = new JLabel("MyStatus");
		MyStatusLabel.setBounds(0, 40, 54, 15);
		panel.add(MyStatusLabel);
		
		MyBirDayLabel = new JLabel("���� -��-��-��");
		MyBirDayLabel.setBounds(0, 60, 130, 15);
		panel.add(MyBirDayLabel);
		
		MySexLabel =new JLabel("�Ա� ");
		MySexLabel.setBounds(0, 80, 54, 15);
		panel.add(MySexLabel);
		
		contentPane.setLayout(gl_contentPane);
		
		addWindowListener(new WindowAdapter() {//������Ͻǹرյ�������
			public void windowClosing(WindowEvent e) {
				//ֹͣ������Ϣ�߳�
				if(acceptMsg!=null)
					acceptMsg.interrupt();
				//����������Ϣ
				OffLineMassage(id);
				System.out.println("ChatView�������.");//������Ϣ
			}
		});
	}
	private void OffLineMassage(String id) {//����
		msger.MsgSender();
		msger.SendOffline(id);
	}
	private void Update(String id) {
		//���ͻ�ȡ��Ϣ������
		
		msger.MsgSender();
		msger.SendGetNameStatus(id);//Ҫ���ȡ�ǳơ�״̬�����ա������б����Ϣ
		
		//�����̲߳��Ͻ�����Ϣ������
		acceptMsg=new Thread(new Runnable() {
			public void run() {
				while(true) {
					System.out.println("\nCV:������Ϣ");
					m=new Massager();
					m.getMassage();//����-������Ϣ
					int type=m.getMsgType();
					if(type==4) {
						//�õ��ǳƺ�״̬�����ա������б�
						//�����ǳ�
						myname=m.getName();
						MyNameLabel.setText(myname);
						//����״̬
						String temp=null;
						temp=m.getStatus();
						if(temp.equals("1"))
							temp="����";
						else
							temp="����";
						MyStatusLabel.setText(temp);
						//�����Ա�
						String sexTemp=m.getSex();
						if(sexTemp.equals("9990")) {
							sexTemp="-";
						}
						mysex=sexTemp;
						MySexLabel.setText(sexTemp);
						//��������
						String[] birStr=m.getBirDay().split("-");
						if(!birStr[0].equals("9990")) {
							mybir_year=birStr[0];
							mybir_month=birStr[1];
							mybir_day=birStr[2];
							MyBirDayLabel.setText("����:"+birStr[0]+"��"+birStr[1]+"��"+birStr[2]+"��");
						}
						else {
							MyBirDayLabel.setText("����:-��-��-��");
						}
						//���ú����б�
						setFLabel(id,m);
						System.out.println("CV:��������б�1,msg.getFStr()="+m.getFStr());
					}
//					else if(type==125) {//����
//						//��Ӻ��ѳɹ�
//						//���º����б�
//						//�������󣬵õ������б�
//				//		msger.MsgSender();
//						//Ҫ���ȡ�ҵ�ȫ������id,�������յ�type127���������Լ��ĺ���ID
//				//		msger.SendGetAllFriendId(id);
//						System.out.println("CV:������µĺ��ѱ�ǩid:"+m.getFID()+".");//������Ϣ
//					}
//					else if(type==126) {//����
//						//ɾ�����ѳɹ�
//						//���º����б�
//						//�������󣬵õ������б�
//				//		msger.MsgSender();
//						//Ҫ���ȡ�ҵ�ȫ������id,�������յ�type127���������Լ��ĺ���ID
//				//		msger.SendGetAllFriendId(id);
//						System.out.println("CV:ɾ���˺��ѱ�ǩid:"+delFriendLabel+".");//������Ϣ
//					}
					else if(type==127) {
						//���ú����б�
						setFLabel(id,m);
						System.out.println("CV:��������б�2,msg.getFStr()="+m.getFStr());
					}
					else if(type==128) {
						//���������ú����б�
						setFLabel(id,m);
						System.out.println("CV:������������б�3,msg.getFStr()="+m.getFStr());
					}
					else if(type==997) {
						//��Ӻ���ʧ��
						
					}
					else if(type==995) {
						//��Ϣ����ʧ��
						ChatTextField.setText("��ǰ����δ���ߣ���Ϣ����ʧ��.");
					}
					else if(type==6) {
						//������Ϣ
						//�޸�hasContentֵΪID
						hasContent=Integer.parseInt(m.getID());
						if(Integer.parseInt(chatwithID)!=hasContent) {
							//���ǵ�ǰ������Ѿ�ˢ�º����б�,��ʾ���
							setFLabel(id,theFIDstr);
							if(offlineContent==null) {
								offlineContent="";
							}
							offlineContent+="->"+m.getID()+" "+timer.getTIME()+"\n"+m.getMsgText()+"\n";
						}else {
							//����Ϣ��ʾ������Ի���
							ChatTextField.setText(ChatTextField.getText()+"->"+m.getID()+" "+timer.getTIME()+"\n"+m.getMsgText()+"\n");
						}
							
						System.out.println("CV:������Ϣ:"+m.getID()+"����˵�ˣ�"+timer.getTIME()+"\n"+m.getMsgText()+"\n�޸���hasContent="+hasContent);
					}
					else {
						System.out.println("CV:�յ�����δʶ������ͣ�type="+type);
					}
					
				}
					
			}
			
		});
		acceptMsg.start();
		System.out.println("acceptMsg-> ok");//������Ϣ
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
		//���ݷ���������ֵ ���ú����б�
		friendLabel=new ArrayList<JLabel>();
		friendlist.removeAll();
		System.out.println("CV:�����б�"+msg.getFStr());
		if(msg.getFStr()!=null) {//��������б�Ϊ��
			if(!msg.getFStr().equals("")) {
				
			}
			String[] FIDstr=msg.getFStr().split("\\*");//����id
			//���±���ֵ
			theFIDstr=FIDstr;System.out.println("һ���к��ѣ�"+FIDstr.length+"����");
			for(int i=0;i<FIDstr.length;i++) {
				//��Ӻ��ѱ�ǩ
				JLabel jl=new JLabel(FIDstr[i]);System.out.println("��"+i+"�����ѣ�"+FIDstr[i]+";");//������Ϣ
				friendLabel.add(jl);
				friendlist.add(friendLabel.get(i));
				//���ͼƬ-������Ϣ��ʾ
				if(hasContent!=-1&&!chatwithID.equals("-1")) {
					if(Integer.parseInt(chatwithID)!=hasContent&&hasContent==Integer.parseInt(FIDstr[i])) {
						//�ж�����������������Ϣid�Ƿ�һ��
						//������Ϣ����Ϊ���-��һ��
						friendLabel.get(i).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/���.png")));
					}else {
						//û������Ϣ���ǻҵ�-һ��
						friendLabel.get(i).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/�ҵ�.png")));
					}
				}
				else {
					//û������Ϣ���ǻҵ�-һ��
					friendLabel.get(i).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/�ҵ�.png")));
				}
				//�����ѱ�ǩ��Ӽ���
				friendLabel.get(i).addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount()==2) {
							//˫������
							String fid=((JLabel)e.getSource()).getText();
							chatwithID=fid;
							ChatWithName.setText("��"+fid+"������");
							//�ж��Ƿ�ȡ�������ʾ����ʾ��������
							if(Integer.parseInt(chatwithID)==hasContent) {
								//��������������Ϣidһ��
								hasContent=-9;
								//��Ϊ�ҵ�
								((JLabel)e.getSource()).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/�ҵ�.png")));
								//����Ϣ��ʾ������Ի���
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
							//����Ҽ�
							//����Ҽ��˵�
							popupMenu=new JPopupMenu();
							addPopup(jl,popupMenu);
							menuItem=new JMenuItem("ɾ���ú���");
							String fid=((JLabel)e.getSource()).getText();
							delFriendLabel=fid;
							menuItem.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									msger.MsgSender();
									msger.AddDelFriend(id, fid, "8");//ɾ������-8
									System.out.println("ɾ���˺���->"+fid);
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
			JLabel jl=new JLabel("��ĺ����б�Ϊ��.");
			friendlist.add(jl);
		}
		friendlist.repaint();
		friendlist.validate();
	}
	private void setFLabel(String id,String[] FIDstr) {
		//���ݱ��غ���id�б� ���ú����б�
		friendLabel=new ArrayList<JLabel>();
		friendlist.removeAll();
		
		System.out.println("CV:�����б�-FIDstr��"+FIDstr);
		if(FIDstr!=null) {//��������б�Ϊ��
			for(int i=0;i<FIDstr.length;i++) {
				//��Ӻ��ѱ�ǩ
				JLabel jl=new JLabel(FIDstr[i]);
				friendLabel.add(jl);
				//���ͼƬ-������Ϣ��ʾ
				if(hasContent==Integer.parseInt(FIDstr[i])) {
					//������Ϣ����Ϊ���
					friendLabel.get(i).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/���.png")));
				}else {
					//û������Ϣ���ǻҵ�
					friendLabel.get(i).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/�ҵ�.png")));
				}
				friendlist.add(friendLabel.get(i));
				
				//�����ѱ�ǩ��Ӽ���
				friendLabel.get(i).addMouseListener(new MouseAdapter() {
					//���ﲻ��i��������//����������iҪ��((JLabel)e.getSource())
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount()==2) {
							//˫������
							String fid=((JLabel)e.getSource()).getText();
							chatwithID=fid;
							ChatWithName.setText("��"+fid+"������");
							//�ж��Ƿ�ȡ�������ʾ����ʾ������������
							if(Integer.parseInt(chatwithID)==hasContent) {
								//��������������Ϣidһ��
								hasContent=-9;
								//��Ϊ�ҵ�
								((JLabel)e.getSource()).setIcon(new ImageIcon(ChatView_test.class.getResource("/icon/�ҵ�.png")));
								//����Ϣ��ʾ������Ի���
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
							//����Ҽ�
							//����Ҽ��˵�
							popupMenu=new JPopupMenu();
							addPopup(jl,popupMenu);
							menuItem=new JMenuItem("ɾ���ú���");
							String fid=((JLabel)e.getSource()).getText();
							delFriendLabel=fid;
							menuItem.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									msger.MsgSender();
									msger.AddDelFriend(id, fid, "8");//ɾ������-8
									System.out.println("ɾ���˺���->"+fid);
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
			JLabel jl=new JLabel("��ĺ����б�Ϊ��.");
			friendlist.add(jl);
		}
		friendlist.repaint();
		friendlist.validate();
	}
}//ccss
