package mangerSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public class QueryDialog extends JDialog {
	JTree tree;
	JComboBox comboBox;
	JTextField textField;
	JTextField textField_4;
	JTextField textField_7;
	JTextField textField_8;
	JTextField textField_9;
	JTextField textField_10;
	JTextField textField_11;
	JTextField textField_12;
	JTextField textField_13;
	JButton btnNewButton_3;
	JTable table;
	JPanel panel0;
	Container panel_2;
	JScrollPane scrollPane_1;
	DefaultTableModel model;
	DefaultTreeModel tmodel;
	DefaultMutableTreeNode node_1,node_2,node_3;
	Object[][] salaryList ;//= new Object[20][9]
	
	public QueryDialog(JFrame f,String title,boolean modal) {
		this.setTitle(title);
		getContentPane().setLayout(new BorderLayout());
		this.setAlwaysOnTop(true);
		this.setModal(modal);
		getContentPane().setFont(new Font("华文仿宋", Font.BOLD, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 160, 600, 450);
			
		JPanel panel0 = new JPanel();
		panel0.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel0.setLayout(new BorderLayout());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 255), null, null));
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
		tree.setFont(new Font("华文仿宋", Font.BOLD, 14));
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("新科技有限公司") {
				{
					UserDB data = new UserDB();
					 List<String>  Name=null;
					 List<String>  Name1=null;
					 List<String>  Name2=null;
					node_1 = new DefaultMutableTreeNode("行政人员");
						Name = data.selectName("xingzheng");
						for(int i=0;i<Name.size();i++){
						node_1.add(new DefaultMutableTreeNode(Name.get(i)));
						
					add(node_1);
						}
					
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
			}}}
	));
		scrollPane.setViewportView(tree);
		
		JPanel panel_2 = new JPanel();
		panel0.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.rowHeights = new int[] {0, 0, 0};
		gbl_panel.columnWidths = new int[] {0, 30, 0, 30};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 4;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] {0, 0, 0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[] {0, 0, 0, 0, 30};
		gbl_panel_4.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("华文仿宋", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"姓名", "编号"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.EAST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panel_4.add(comboBox, gbc_comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField.setColumns(5);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_4.add(textField, gbc_textField);
		
		JLabel label_7 = new JLabel("起始时间   年：");
		label_7.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.EAST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 1;
		panel_4.add(label_7, gbc_label_7);
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_10.setColumns(5);
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 1;
		gbc_textField_10.gridy = 1;
		panel_4.add(textField_10, gbc_textField_10);
		
		
		
		JLabel label_4 = new JLabel("月：");
		label_4.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.EAST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 2;
		gbc_label_4.gridy = 1;
		panel_4.add(label_4, gbc_label_4);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_7.setColumns(5);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 3;
		gbc_textField_7.gridy = 1;
		panel_4.add(textField_7, gbc_textField_7);
		
		
		
		JLabel label_8 = new JLabel("终止时间   年：");
		label_8.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.EAST;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 2;
		panel_4.add(label_8, gbc_label_8);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_4.setColumns(5);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 2;
		panel_4.add(textField_4, gbc_textField_4);
		
		JLabel label_9 = new JLabel("月：");
		label_9.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.EAST;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 2;
		gbc_label_9.gridy = 2;
		panel_4.add(label_9, gbc_label_9);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_9.setColumns(5);
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 3;
		gbc_textField_9.gridy = 2;
		panel_4.add(textField_9, gbc_textField_9);
		
		model=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"编号", "年", "月", "出勤奖", "销售奖", "项目奖", "基本工资", "月工资"}
					
				);
		
		btnNewButton_3 = new JButton("查    询");
		btnNewButton_3.addActionListener(new btnNewButton_3_ActionAdapter(model));
		btnNewButton_3.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.gridwidth = 2;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 4;
		panel_4.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JLabel label = new JLabel("编  号：");
		label.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_8.setColumns(5);
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 1;
		panel.add(textField_8, gbc_textField_8);
		
		JLabel label_10 = new JLabel("姓  名：");
		label_10.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.anchor = GridBagConstraints.EAST;
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 2;
		gbc_label_10.gridy = 1;
		panel.add(label_10, gbc_label_10);
		
		textField_12 = new JTextField();
		textField_12.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_12.setColumns(5);
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.insets = new Insets(0, 0, 5, 0);
		gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_12.gridx = 3;
		gbc_textField_12.gridy = 1;
		panel.add(textField_12, gbc_textField_12);
		
		JLabel label_11 = new JLabel("类  别：");
		label_11.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.anchor = GridBagConstraints.EAST;
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 0;
		gbc_label_11.gridy = 2;
		panel.add(label_11, gbc_label_11);
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_11.setColumns(5);
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 5, 5);
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 1;
		gbc_textField_11.gridy = 2;
		panel.add(textField_11, gbc_textField_11);
		
		JLabel label_12 = new JLabel("职  位：");
		label_12.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.anchor = GridBagConstraints.EAST;
		gbc_label_12.insets = new Insets(0, 0, 5, 5);
		gbc_label_12.gridx = 2;
		gbc_label_12.gridy = 2;
		panel.add(label_12, gbc_label_12);
		
		textField_13 = new JTextField();
		textField_13.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_13.setColumns(5);
		GridBagConstraints gbc_textField_13 = new GridBagConstraints();
		gbc_textField_13.insets = new Insets(0, 0, 5, 0);
		gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_13.gridx = 3;
		gbc_textField_13.gridy = 2;
		panel.add(textField_13, gbc_textField_13);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		table = new JTable(model);
		table.setFont(new Font("华文仿宋", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.getColumnModel().getColumn(1).setPreferredWidth(48);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		scrollPane_1.setViewportView(table);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			String text;
			Object[][] salaryList = new Object[20][9];
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if(node.isLeaf()){
					text=node.toString().trim();
					System.out.println("员工姓名："+text);
					if(text!=null){
						UserDB sel = new UserDB();
						ArrayList<String> list1 = sel.selectInfo(null,text);
						textField_8.setText(list1.get(0));
						textField_12.setText(text);
						textField_11.setText(list1.get(1));
						textField_13.setText(list1.get(2));
					} 
				if(node.getParent().toString()=="行政人员"){
					
					
					text=node.toString().trim();
					System.out.println("员工类别：行政人员");
					if(text!=null){
						UserDB sel = new UserDB();
						salaryList = sel.selectsalaryinfo(null,text,null,null);
						for(int i=0;i<salaryList.length;i++){
							if(salaryList[i][0]!=null){
								model.addRow(salaryList[i]);
							}}
						} 
				}
				
				if(node.getParent().toString()=="销售人员"){
					text=node.toString().trim();
					System.out.println("员工类别：销售人员");
					if(text!=null){
						UserDB sel = new UserDB();
						salaryList = sel.selectsalaryinfo(null,text,null,null);
						for(int i=0;i<salaryList.length;i++){
							if(salaryList[i][0]!=null){
							model.addRow(salaryList[i]);
						}}
				}
				}
				if(node.getParent().toString()=="技术人员"){
				text=node.toString();
				System.out.println("员工类别：技术人员");
				if(text!=null){
					System.out.println("员工类别：技术人员");
					UserDB sel = new UserDB();
					salaryList = sel.selectsalaryinfo(null,text,null,null);
					for(int i=0;i<salaryList.length;i++){
						if(salaryList[i][1]!=null){
							model.addRow(salaryList[i]);
						}}
				}
				
			}
				}	}
		});
	
		this.add(panel0); //将panel加入到当前容器（面板）
	    this.setVisible(true);
	}
	
	//按时间更新数据表格
	public void refreshdatatable(DefaultTableModel model) {
		//获取员工工资信息，并加入salaryList表
			UserDB UserDB = new UserDB();
			String startyear=textField_10.getText();
			String startmonth=textField_7.getText();
			if(startmonth.length()==1){
				startmonth = "0"+startmonth;
			}
			String endyear=textField_4.getText();
			String endmonth=textField_9.getText();
			if(endmonth.length()==1){
				endmonth = "0"+endmonth;
			}
			salaryList = UserDB.selectall
					(startyear,startmonth,endyear,endmonth);
			for(int i=0;i<salaryList.length;i++){
				if(salaryList[i][0]!=null){
				model.addRow(salaryList[i]);}
			}
	}
	
	
	//按编号更新数据表格
	public void refreshTable(DefaultTableModel model) {

		//获取员工工资信息，并加入salaryList表
		UserDB UserDB = new UserDB();
		String staffNo=textField.getText();
		salaryList = UserDB.selectsalaryinfo(staffNo,null,null,null);
		for(int i=0;i<salaryList.length;i++){
			if(salaryList[i][1]!=null){
			model.addRow(salaryList[i]);
		}
		}
	}
	
		//按姓名更新数据表格
		public void NameTable(DefaultTableModel model) {

			//获取员工工资信息，并加入salaryList表
			UserDB UserDB = new UserDB();
			String staffName=textField.getText();
			salaryList = UserDB.selectsalaryinfo(null,staffName,null,null);
			for(int i=0;i<salaryList.length;i++){
				if(salaryList[i][1]!=null){
				model.addRow(salaryList[i]);
			}
		}}
	
	class  btnNewButton_3_ActionAdapter implements ActionListener{
		DefaultTableModel model;
		
		public btnNewButton_3_ActionAdapter(DefaultTableModel model){
			this.model=model;
		}@Override
		public void actionPerformed(ActionEvent e) {
			UserDB UserDB = new UserDB();
			//获取员工基本信息，并加入list1表
			//equals本意为确定两个对象的引用是否相同
			if(comboBox.getSelectedItem().equals("编号"))
			{
				if(!textField_10.getText().isEmpty()){
				refreshdatatable(model);}
				else{
				String staffNo=textField.getText();
				ArrayList<String> list1 = UserDB.selectInfo(staffNo,null);
				textField_8.setText(list1.get(0));
				textField_12.setText(list1.get(1));
				textField_11.setText(list1.get(2));
				textField_13.setText(list1.get(3));
				// 更新数据表格
				refreshTable(model);
				}
			}
			
			else {
				if(!textField_10.getText().isEmpty()){
					refreshdatatable(model);}
				else{
				String staffName =textField.getText();
				//获取员工基本信息，并加入list1表
				ArrayList<String> list1 = UserDB.selectInfo(null,staffName);
				textField_8.setText(list1.get(0));
				textField_12.setText(staffName);
				textField_11.setText(list1.get(1));
				textField_13.setText(list1.get(2));
				
				NameTable(model);
				}
				
				}
		}
		}

}
