package mangerSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;

public class BasicInforDialog<staffPosition> extends JDialog {
	//增加修改删除相应的树形结构和表结构（填充）要进行修改。
	JTree tree=null;
	JTextField textField;
	JTextField textField_1;
	JTextField textField_3;
	JTextField textField_5;
	JButton btnNewButton;
	JButton btnNewButton_1;
	JButton btnNewButton_2;
	JTable table;
	DefaultTableModel model;
	DefaultTreeModel tmodel;
	DefaultMutableTreeNode node_1,node_2,node_3;
    JFrame jframe;
    JScrollPane scrollPane= null;
    JComboBox comboBox;
    String staffNo, staffBasicSalary,staffName,staffClass,staffPosition; 
	public BasicInforDialog(JFrame f,String title,boolean modal) {
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
		
		 scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		
		refreshtreemodel();

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
		
		JLabel label = new JLabel("员工姓名：");
		label.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_1.setColumns(5);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		
		JLabel label_1 = new JLabel("类  别：");
		label_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 30, 5, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 3;
		panel.add(label_1, gbc_label_1);
		
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("华文仿宋", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"xingzheng", "sale","jishu"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 30, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		
		
		JLabel label_3 = new JLabel("职  位：");
		label_3.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 3;
		panel.add(label_3, gbc_label_3);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_3.setColumns(5);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 3;
		panel.add(textField_3, gbc_textField_3);
		
		JLabel label_5 = new JLabel("基本工资：");
		label_5.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 4;
		panel.add(label_5, gbc_label_5);
		
		textField_5 = new JTextField();
		textField_5.setText("0");
		textField_5.setFont(new Font("华文仿宋", Font.BOLD, 14));
		textField_5.setColumns(5);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 4;
		panel.add(textField_5, gbc_textField_5);
		//基本工资输入框只能输入数字和小数点
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
		  public void keyTyped(KeyEvent e) {
			 int code = e.getKeyChar();
			 if ((code>=KeyEvent.VK_0 &&code<=KeyEvent.VK_9)
	    	      ||(code==KeyEvent.VK_PERIOD)){//为数字或小数点
				  
			  }else e.consume();
			}
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 4;
		gbc_panel_3.insets = new Insets(0, 3, 0, 3);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 5;
		panel.add(panel_3, gbc_panel_3);
		
		model=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"编号", "姓名", "类别", "职位","基本工资"
				}
			);
		
		btnNewButton = new JButton("新  增");
		btnNewButton.addActionListener(new btnNewButton_ActionAdapter(tmodel));
		btnNewButton.setFont(new Font("华文仿宋", Font.BOLD, 14));
		panel_3.add(btnNewButton);
		
		btnNewButton_1 = new JButton("修  改");
		btnNewButton_1.addActionListener(new btnNewButton_1ActionAdapter());
		btnNewButton_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		panel_3.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("删  除");
		btnNewButton_2.addActionListener(new btnNewButton_2ActionAdapter());
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

    class 	btnNewButton_ActionAdapter implements ActionListener{
    	DefaultTreeModel tmodel;

    	public btnNewButton_ActionAdapter(DefaultTreeModel tmodel) {
    		super();
			this.tmodel = tmodel;
		}

		//新增员工信息的按钮
		@Override
		public void actionPerformed(ActionEvent e) {
			UserDB UserDB = new UserDB();
			staffNo = textField.getText();
			if(isstaffNo()){
				JOptionPane.showMessageDialog(jframe, "该编号已存在!", 
						"错误", JOptionPane.ERROR_MESSAGE);
			}
			else{
			staffName = textField_1.getText();
			staffClass =(String) comboBox.getSelectedItem();
			staffPosition = textField_3.getText();
			staffBasicSalary = textField_5.getText();
			UserDB.insertInfo(staffNo, staffName, staffClass,staffPosition,staffBasicSalary);
			refreshtreemodel();
			refreshTable();
			}
		}
 }
        //获取输入框中的信息然后更新数据表格
		public void refreshTable() {
			
			Object[] salaryList = new Object[5];
			salaryList[0]=staffNo;
			salaryList[1]=staffName;
			salaryList[2]=staffClass;
			salaryList[3]=staffPosition;
			salaryList[4]=staffBasicSalary;
			model.addRow(salaryList);
		}
		
		class btnNewButton_1ActionAdapter implements ActionListener{

		//修改员工基本信息
		public void actionPerformed(ActionEvent e) {
			UserDB UserDB = new UserDB();
			staffNo = textField.getText();   
			
			if(!isstaffNo()){
				JOptionPane.showMessageDialog(jframe, "该编号不存在!", 
						"错误", JOptionPane.ERROR_MESSAGE);
			}
			else{
			String Name = textField_1.getText();           //获得文本框
			String staffClass =(String) comboBox.getSelectedItem();		
			String Position = textField_3.getText();           //获得文本框
			String BasicSalary=textField_5.getText();            //获得文本框
			
		    int v=	UserDB.updateInfo(staffNo,Name,staffClass,Position,BasicSalary);
			System.out.println("修改成功"+"影响了"+v+"行数据");
			Object[] salaryList = new Object[5];
			salaryList[0]=staffNo;
			salaryList[1]=Name;
			salaryList[2]=staffClass;
			salaryList[3]=Position;
			salaryList[4]=BasicSalary;
			model.addRow(salaryList);
			refreshtreemodel();
			}
			
		}
		}
		class btnNewButton_2ActionAdapter implements ActionListener{
			//删除员工基本信息的按钮
			int v=0;
			public void actionPerformed(ActionEvent e) {
				UserDB UserDB = new UserDB();
				staffNo = textField.getText(); 
					if(!isstaffNo()){
						JOptionPane.showMessageDialog(jframe, "该编号不存在!", 
								"错误", JOptionPane.ERROR_MESSAGE);
					}
					if(UserDB.selectsalaryinfo(staffNo, null,null,null)[0][1]!=null){
						JOptionPane.showMessageDialog(jframe, "已包含工资信息，不能删除!", 
								"错误", JOptionPane.ERROR_MESSAGE);
					}
					else{
					 v=UserDB.deleteInfo(staffNo);
					System.out.println("删除成功"+"影响了"+v+"行数据");
					}
			
				refreshtreemodel();
				
			}
		}
		//初始化和更新数据表格
   public void refreshtreemodel() {
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
	
	
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if(node==null)
					return;
				if(node.isLeaf()){
				String text=node.toString();
				if(text!=null){//判断是否为名字节点
					UserDB sel = new UserDB();
					ArrayList<String> list1 = sel.selectInfo(null,text);
					textField.setText(list1.get(0));
					textField_1.setText(text);
					comboBox.setSelectedItem(list1.get(2));
					textField_3.setText(list1.get(3));
					textField_5.setText(list1.get(4));
					
					Object[] salaryList = new Object[5];
					salaryList[0]=list1.get(0);
					salaryList[1]=text;
					salaryList[2]=list1.get(2);
					salaryList[3]=list1.get(3);
					salaryList[4]=list1.get(4);
					model.addRow(salaryList);
				} 
			}
			}
		});
		scrollPane.setViewportView(tree);
	
   }
   //判断数据库中是否有该编号
   public boolean isstaffNo() {
	   Object []list = new Object[30];
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
}
