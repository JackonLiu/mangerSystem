package mangerSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public class SalaryInforDialog extends JDialog {
	
	JTree tree;
	JTextField textField;//工号
	JTextField textField_1;//年
	JTextField textField_2;//月
	JTextField textField_3;//缺勤天数，销售额，项目奖
	JTextField textField_5;//基本工资
	JTextField textField_6;//月工资
	JButton btnNewButton;
	JButton btnNewButton_1;
	JButton btnNewButton_2;
	JTable table;
	DefaultTableModel model;
	DefaultTreeModel tmodel;
	JComboBox comboBox,combobox,comboBox1;
	DefaultMutableTreeNode node_1,node_2,node_3;
	String staffNo;
	protected static int year=2010;
	String Year;
	Object[][] salaryList = new Object[20][9];
	JFrame jframe;
	Icon icon;
	String month;
	double b=0.0,p=0.00;
	String sa=null;
	double ProjectReward=0.00,totalSalary=0.00,saleNum=0.00,saleReward=0.00;
	String LeftDutyDays="0";
	double DutyReward=0.00,staffBasicSalary=0.00;
	public SalaryInforDialog(JFrame f,String title,boolean modal) {
		this.setTitle(title);
		getContentPane().setLayout(new BorderLayout());
		this.setAlwaysOnTop(true);
		this.setModal(modal);
		getContentPane().setFont(new Font("华文仿宋", Font.BOLD, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(150, 160, 600, 450);
			
		JPanel panel0 = new JPanel();
		panel0.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel0.setLayout(new BorderLayout());
			
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel0.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lbll = new JLabel("员工信息");
		lbll.setHorizontalAlignment(SwingConstants.CENTER);
		lbll.setFont(new Font("华文仿宋", Font.BOLD, 14));
		lbll.setVerticalAlignment(SwingConstants.TOP);
		panel_1.add(lbll, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		tree = new JTree();
		
		refreshtreeModel();

		scrollPane.setViewportView(tree);
		
		JPanel panel_2 = new JPanel();
		panel0.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 30, 0, 30};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JLabel label_2 = new JLabel("员工编号：");
		label_2.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 0;
		panel.add(label_2, gbc_label_2);
		
		textField = new JTextField();
		textField.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(5);
		
	
		//滚动下拉轮子显示基本工资 
		textField.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				UserDB move = new UserDB();
				staffNo=textField.getText().trim();
				System.out.println("mouseWheelMoved");
				ArrayList<String> BasicSalary=move.selectInfo(staffNo, null);
				textField_5.setText(BasicSalary.get(4).trim());
				staffBasicSalary = Double.valueOf(BasicSalary.get(4).trim()).doubleValue();
				textField_5.setEditable(false);
			}
		});
		
		//创建日历对象，获取当前日期
		 
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);//获取年
		JLabel label = new JLabel("年：");
		label.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		textField_1 = new JTextField();
		textField_1.setText(String.valueOf(year));
		textField_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_1.setColumns(5);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
		  public void keyTyped(KeyEvent e) {
			 int code = e.getKeyChar();
			 if (code>=KeyEvent.VK_0 &&code<=KeyEvent.VK_9){
				 //只能输入数字
			  }else e.consume();
			}
		});
		
		JLabel label_1 = new JLabel("月：");
		label_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 1;
		panel.add(label_1, gbc_label_1);
		
		comboBox1 = new JComboBox();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		java.util.Date dd  = Calendar.getInstance().getTime();
		month = sdf.format(dd);
		comboBox1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"01", "02","03","04", "05","06","07", "08","09","10", "11","12"}));
		comboBox1.setSelectedItem(month);
		GridBagConstraints gbc_comboBox1 = new GridBagConstraints();
		gbc_comboBox1.anchor = GridBagConstraints.EAST;
		gbc_comboBox1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox1.gridx = 3;
		gbc_comboBox1.gridy = 1;
		panel.add(comboBox1, gbc_comboBox1);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("华文仿宋", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"缺勤天数", "销售额","项目奖"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.EAST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.EAST;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 2;
		panel.add(comboBox, gbc_comboBox);

		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_3.setColumns(5);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 2;
		panel.add(textField_3, gbc_textField_3);


		JLabel label_5 = new JLabel("基本工资：");
		label_5.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 3;
		panel.add(label_5, gbc_label_5);
		
		textField_5 = new JTextField();
		textField_5.setText("6000.00");
		textField_5.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_5.setColumns(5);
		
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 3;
		panel.add(textField_5, gbc_textField_5);
		
		
		combobox = new JComboBox();
		combobox.setFont(new Font("华文仿宋", Font.BOLD, 14));
		combobox.setModel(new DefaultComboBoxModel(new String[] {"月工资", "年工资"}));
		GridBagConstraints gbc_comboBox2 = new GridBagConstraints();
		gbc_comboBox2.anchor = GridBagConstraints.EAST;
		gbc_comboBox2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox2.fill = GridBagConstraints.EAST;
		gbc_comboBox2.gridx = 2;
		gbc_comboBox2.gridy = 3;
		panel.add(combobox, gbc_comboBox2);
		
		textField_6 = new JTextField();
		textField_6.setText("0");
		textField_6.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_6.setColumns(5);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 3;
		gbc_textField_6.gridy = 3;
		panel.add(textField_6, gbc_textField_6);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 4;
		gbc_panel_3.insets = new Insets(0, 3, 0, 3);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 4;
		panel.add(panel_3, gbc_panel_3);
		
		model=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"编号", "年", "月", "月工资"
				}
			);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if(node.isLeaf()){
					
					totalSalary=0.00;
				if(node.getParent().toString()=="行政人员"){
					
					comboBox.setSelectedItem("缺勤天数");
					combobox.setSelectedItem("年工资");
					String text=node.toString();
					System.out.println("员工姓名："+text);
					if(text!=null){
						UserDB sel = new UserDB();
						salaryList= sel.selectsalaryinfo(null,text,null,null);
						textField.setText((String) salaryList[0][0]);
						textField_1.setText((String) salaryList[0][1]);
						comboBox1.setSelectedItem((String) salaryList[0][2]);
						textField_3.setText((String) salaryList[0][3]);
						textField_5.setText((String) salaryList[0][7]);
						
						//对应月工资，重新生成表
						for(int i=0;i<salaryList.length;i++){
							Object[] tableList = new Object[4];
							tableList[0]=(String) salaryList[i][0];
							tableList[1]=(String) salaryList[i][1];
							tableList[2]=(String) salaryList[i][2];
							tableList[3]=String.valueOf(salaryList[i][8]);
							if(tableList[0]!=null){
								totalSalary=Double.valueOf(tableList[3].toString()).doubleValue()+totalSalary;
							model.addRow(tableList);}
					} 
						textField_6.setText(String.valueOf(totalSalary));
				}
				}
				if(node.getParent().toString()=="销售人员"){
					comboBox.setSelectedItem("销售额");//销售额
					combobox.setSelectedItem("年工资");
					String text=node.toString();
					System.out.println("员工姓名："+text);
					if(text!=null){
						UserDB sel = new UserDB();
						salaryList= sel.selectsalaryinfo(null,text,null,null);
						textField.setText((String) salaryList[0][0]);
						//显示最进一个月的工资
						textField_1.setText((String) salaryList[0][1]);
						comboBox1.setSelectedItem((String) salaryList[0][2]);
						textField_3.setText((String) salaryList[0][6]);
						textField_5.setText((String) salaryList[0][7]);
						
						//对应月工资，重新生成表
						for(int i=0;i<salaryList.length;i++){
							Object[] tableList = new Object[4];
							tableList[0]=(String) salaryList[i][0];
							tableList[1]=(String) salaryList[i][1];
							tableList[2]=(String) salaryList[i][2];
							
							tableList[3]=String.valueOf(salaryList[i][8]);
							if(tableList[0]!=null){
								totalSalary=Double.valueOf(tableList[3].toString()).doubleValue()+totalSalary;
							
							model.addRow(tableList);}
					} 
						textField_6.setText(String.valueOf(totalSalary));
				}
				}	
				if(node.getParent().toString()=="技术人员"){
				comboBox.setSelectedItem("项目奖");//项目奖
				combobox.setSelectedItem("年工资");
				String text=node.toString();
				System.out.println("员工姓名："+text);
				if(text!=null){
					UserDB sel = new UserDB();
					salaryList= sel.selectsalaryinfo(null,text,null,null);
					textField.setText((String) salaryList[0][0]);
					textField_1.setText((String) salaryList[0][1]);
					comboBox1.setSelectedItem((String) salaryList[0][2]);
					textField_3.setText((String) salaryList[0][5]);
					textField_5.setText((String) salaryList[0][7]);
					
					//对应月工资，重新生成表
					
					for(int i=0;i<salaryList.length;i++){
						Object[] tableList = new Object[4];
						tableList[0]=(String) salaryList[i][0];
						tableList[1]=(String) salaryList[i][1];
						tableList[2]=(String) salaryList[i][2];
						tableList[3]=(String)salaryList[i][8];
							
						if(tableList[0]!=null){
							totalSalary=Double.valueOf(tableList[3].toString()).doubleValue()+totalSalary;
						
						model.addRow(tableList);}
					}
					textField_6.setText(String.valueOf(totalSalary));
				}
				}
				}
			}
			
		});
		
		
		textField_3.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				staffBasicSalary = Double.valueOf(textField_5.getText().trim()).doubleValue();
				 if(comboBox.getSelectedItem().toString()=="缺勤天数"){
					LeftDutyDays = textField_3.getText();
					if(Integer.valueOf(LeftDutyDays).intValue()==0)
						DutyReward=0.20*staffBasicSalary;
					if(Integer.valueOf(LeftDutyDays).intValue()>0||Integer.valueOf(LeftDutyDays).intValue()<4)
						DutyReward=0.10*staffBasicSalary;
					if(Integer.valueOf(LeftDutyDays).intValue()==4||Integer.valueOf(LeftDutyDays).intValue()==5)
						DutyReward=0.05*staffBasicSalary;
					if(Integer.valueOf(LeftDutyDays).intValue()>5)
						DutyReward=0.0;
					
					b = DutyReward+staffBasicSalary;
					System.out.println("行政人员");
						sa =String.valueOf(b);
						textField_6.setText(sa);
						totalSalary = b;
				
				}
				if(comboBox.getSelectedItem().toString()=="销售额"){
					saleNum = Double.valueOf(textField_3.getText().trim()).doubleValue();
					b=saleNum*0.05+staffBasicSalary;
					saleReward=saleNum*0.05;
					sa =String.valueOf(b);
					System.out.println("销售人员");
					textField_6.setText(sa);
					totalSalary = b;
					}
				if(comboBox.getSelectedItem().toString()=="项目奖"){
								ProjectReward =Double.valueOf(textField_3.getText().trim()).doubleValue();
							b = ProjectReward+staffBasicSalary;
							sa =String.valueOf(b);
							System.out.println("技术人员");
							textField_6.setText(sa);
							totalSalary =b;
				 }
				textField_6.setEditable(false);
			}
		});
		
		
		JButton btnNewButton = new JButton("新  增");
		btnNewButton.addActionListener(new btnNewButton_ActionAdapter());
		btnNewButton.setFont(new Font("华文仿宋", Font.BOLD, 14));
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("修  改");
		btnNewButton_1.addActionListener(new btnNewButton_2ActionAdapter());
		btnNewButton_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		panel_3.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("删  除");
		btnNewButton_2.addActionListener(new btnNewButton_3ActionAdapter());
		btnNewButton_2.setFont(new Font("华文仿宋", Font.BOLD, 14));
		panel_3.add(btnNewButton_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		table = new JTable(model);
		table.setFont(new Font("华文仿宋", Font.PLAIN, 12));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setViewportView(table);
		this.add(panel0);
		this.setVisible(true);

	}
	
		//可修改指定月份的行政人员的缺勤情况，
		//研发人员的项目奖励，销售人员的销售奖励。然后自动生成总工资。
		 class btnNewButton_2ActionAdapter implements ActionListener{
			 int v=0;
			public void actionPerformed(ActionEvent e) {
			
				UserDB UserDB = new UserDB();
				staffNo = textField.getText().trim();          
				if(!isstaffNo()){
					JOptionPane.showMessageDialog(jframe, "本公司无此员工", 
							"错误", JOptionPane.ERROR_MESSAGE);
				}
				else{
				String year = textField_1.getText().trim();        
				String month = (String) comboBox1.getSelectedItem(); 
				
				if(comboBox.getSelectedItem().toString()=="缺勤天数"){
					v=UserDB.updateSalary(staffNo,year,month,LeftDutyDays,DutyReward ,
							saleNum ,saleReward,ProjectReward,staffBasicSalary,totalSalary);
					}
				if(comboBox.getSelectedItem().toString()=="销售额"){
					
					v=UserDB.updateSalary(staffNo,year,month,LeftDutyDays,DutyReward ,
							saleNum ,saleReward,ProjectReward,staffBasicSalary,totalSalary);
					}
				if(comboBox.getSelectedItem().toString()=="项目奖"){
					v=UserDB.updateSalary(staffNo,year,month,LeftDutyDays,DutyReward ,
							saleNum ,saleReward,ProjectReward,staffBasicSalary,totalSalary);
					}
				System.out.println("影响了"+v+"行数据");
				refreshtreeModel();
				}}
			 
		 }
		
		 
		 class btnNewButton_3ActionAdapter implements ActionListener{
			
			 public void actionPerformed(ActionEvent e) {
				UserDB UserDB = new UserDB();
				staffNo = textField.getText().trim();  
				Year=textField_1.getText().trim();
				month=comboBox1.getSelectedItem().toString();
				if(!isstaffNo()){
					JOptionPane.showMessageDialog(jframe, "该编号不存在!", 
							"错误", JOptionPane.ERROR_MESSAGE);
				}
				else{
					JOptionPane.showOptionDialog(null,"请确认是否删除？", "请确认", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, icon, null,null);
					option();
						
					}
		 }
		
		 }
		 
		 
		 
		 //确认用户选择
		 public void option() {
			 JFrame jf = new JFrame("请选择");
			 jf.setSize(300, 120);
			 jf.setVisible(true);
			 jf.setAlwaysOnTop(true);
			 jf.setLocation(600, 440);
			 jf.setLayout(new BorderLayout());
			 JLabel jl= new JLabel("是：删除该员工所有信息，否:删除该月工资信息");
			 jf.add(jl, BorderLayout.NORTH);
			 JPanel jp = new JPanel();
			 JButton jb=new JButton("是");
			 jp.add(jb);
			 JButton jn= new JButton("否");
			 jp.add(jn);
			 jf.add(jp,BorderLayout.SOUTH);
			 jn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					UserDB UserDB = new UserDB();
					int y=UserDB.deletesalaryInfo(staffNo,Year,month);
					if(y!=0){
					System.out.println("删除该月信息成功" +"影响了"+y+"行数据");}
					else{
						System.out.println("删除该月信息失败！");
					}
				}
				
			});
			 jb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					UserDB UserDB = new UserDB();
					int v=UserDB.deletesalaryInfo(staffNo,null,null);
					System.out.println("影响了"+v+"行数据");
				}
			});

		}
		 
	 class 	btnNewButton_ActionAdapter implements ActionListener{

			//新增工资信息的按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UserDB UserDB = new UserDB();
				staffNo = textField.getText().trim();
				if(!isstaffNo()){
					JOptionPane.showMessageDialog(jframe, "本公司无此员工", 
							"错误", JOptionPane.ERROR_MESSAGE);
				}
				else{
				Year = textField_1.getText().trim();
				month =comboBox1.getSelectedItem().toString();
				if(UserDB.selectsalaryinfo(staffNo,null,Year,month)[0][0]!=null){
					JOptionPane.showMessageDialog(jframe, "本月工资已存在", 
							"错误", JOptionPane.ERROR_MESSAGE);
				}
				else{
					staffBasicSalary = Double.valueOf(textField_5.getText().trim()).doubleValue();
				if(comboBox.getSelectedItem().toString()=="缺勤天数"){
					UserDB.insertSalary(staffNo, Year, month,LeftDutyDays,DutyReward,p,p,p,staffBasicSalary,totalSalary);
				}
				if(comboBox.getSelectedItem().toString()=="销售额"){
					UserDB.insertSalary(staffNo, Year, month,"0",p,saleNum ,saleReward,p,staffBasicSalary,totalSalary);
					}
				if(comboBox.getSelectedItem().toString()=="项目奖"){
					UserDB.insertSalary(staffNo, Year, month,"0",p,p,p,ProjectReward,staffBasicSalary,totalSalary);
				}
				refreshtreeModel();
				refreshTable();
				}}
			}
	 }
	 
	
	 
	//初始化和更新数据表格
	   public void refreshtreeModel() {
		   tmodel = new DefaultTreeModel(new DefaultMutableTreeNode("新科技有限公司"){
				{
							UserDB data = new UserDB();
							 List<String>  Name=null;
							 List<String>  Name1=null;
							 List<String>  Name2=null;
							node_1 = new DefaultMutableTreeNode("行政人员");
								Name = data.selectName("xingzheng");
								for(int i=0;i<Name.size();i++){
								node_1.add(new DefaultMutableTreeNode(Name.get(i)));
								}
							add(node_1);
							node_2 = new DefaultMutableTreeNode("销售人员");
								Name1 = data.selectName("sale");
								for(int i=0;i<Name1.size();i++){
							    node_2.add(new DefaultMutableTreeNode(Name1.get(i)));
							  
						    add(node_2);
								}
							node_3 = new DefaultMutableTreeNode("技术人员");
								Name2 = data.selectName("jishu");
								for(int i=0;i<Name2.size();i++){
								node_3.add(new DefaultMutableTreeNode(Name2.get(i)));
								
							add(node_3);
							
					}}
						}
			);
		   
		   if (tree==null)tree = new JTree(tmodel);
		   else {
			   tree.removeAll();
			   tree=new JTree(tmodel);
		   }
		   tree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		   tree.setFont(new Font("华文仿宋", Font.BOLD, 14));
		   
	   }
	 
	 public boolean isstaffNo() {
		 Object []list = new Object[20];
		   UserDB UserDB = new UserDB();
		   int flag=0;
			list=UserDB.selectallStaffNo("staffbasicinfo");
			for(int i=0;i<list.length;i++){
				
				if(staffNo.equals(list[i])){
					flag=1;
					break;
				}
				}
			
			if(flag==1){
				return true;
			}
			return false;
	   }
	 
	        //获取输入框中的信息然后更新数据表格
	 		public void refreshTable() {
				Object[] salaryList = new Object[4];
				salaryList[0]=staffNo;
				salaryList[1]=Year;
				salaryList[2]=month;
				salaryList[3]=totalSalary;
				model.addRow(salaryList);
			}
}

