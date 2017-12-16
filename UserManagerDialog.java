package mangerSystem;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;

public class UserManagerDialog extends JDialog {
	//private final JPanel contentPane ;
	
	JList<Object> list;
	DefaultListModel<String> userList;
	JButton btnNewButton;
	JButton btnNewButton_1;
	JButton btnNewButton_2;
	JTextField textField;
	JPasswordField password;
	JPasswordField password_1;
	JPasswordField password_2;
	JComboBox comboBox;
	JLabel label; 
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_3; 
	UserDB userDB;
	UserManagerDialog thisDialog = this;
	public UserManagerDialog(JFrame f,String title,boolean modal) {
		this.setTitle(title);
		userDB = new UserDB();//创建对象
		getContentPane().setLayout(new BorderLayout());
		this.setAlwaysOnTop(true);
		this.setModal(modal);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 450, 300);
			
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 255), null, null));
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lbll = new JLabel("用户列表");
		lbll.setHorizontalAlignment(SwingConstants.CENTER);
		lbll.setFont(new Font("华文仿宋", Font.BOLD, 14));
		lbll.setVerticalAlignment(SwingConstants.TOP);
		panel_1.add(lbll, BorderLayout.NORTH);
		
		//获取用户名，并加入list表
		Object[] userList1 = userDB.getusrName().toArray();
		userList =new DefaultListModel<String>();
		for(int i=0;i<userList1.length;i++){
			userList.addElement(userList1[i].toString());
		}
		list = new JList(userList);
		list.setFont(new Font("华文仿宋", Font.PLAIN, 14));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.add(list, BorderLayout.CENTER);
		//选择更新监听
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String userName = (String)list.getSelectedValue();	
				ArrayList<String> userInfo = userDB.getusrInfo(userName);
				if (!userInfo.isEmpty()){//不空
					textField.setText(userInfo.get(0));
					password.setText(userInfo.get(1));
					comboBox.setSelectedItem(userInfo.get(2));
					btnNewButton_1.setEnabled(true);
					btnNewButton_2.setEnabled(true);
		    	}
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, Color.BLUE, null, null));
		panel.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(8, 1, 0, 0));
		
		btnNewButton = new JButton("增  加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=0;
				if(btnNewButton.getText().equals("增  加")){
					setComponentEnabled(true);
				    setComponentEmpty();
					btnNewButton.setText("保  存");
					btnNewButton_1.setEnabled(false);  //设置为不可控制
					btnNewButton_2.setEnabled(false);
				}else{
				String userName = textField.getText();
				String password1 = String.valueOf(password.getPassword());
				String role = (String)comboBox.getSelectedItem();
				try {
					result = userDB.insertUserInfo(userName, password1, role);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				if (result==-1){
					JOptionPane.showMessageDialog(thisDialog, "用户名存在!", 
							"错误", JOptionPane.ERROR_MESSAGE);
					}
				else if(result==0){
					JOptionPane.showMessageDialog(thisDialog, "用户增加不成功!", 
							"错误", JOptionPane.ERROR_MESSAGE);
	
					
				}else  {//添加成功
					JOptionPane.showMessageDialog(thisDialog, "用户增加成功!", 
							"消息", JOptionPane.INFORMATION_MESSAGE);
					userList.addElement(userName);
					}
				btnNewButton.setText("增  加");
				btnNewButton_1.setEnabled(true);
				btnNewButton_2.setEnabled(true);
				setComponentEnabled(false);
				}
			}
			});
		btnNewButton.setFont(new Font("华文仿宋", Font.BOLD, 14));
		panel_2.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		panel_2.add(separator);
		
		btnNewButton_1 = new JButton("修改密码");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = textField.getText();
				if(userName.equals("")){
					JOptionPane.showMessageDialog(thisDialog, "请选择要修改的用户!", 
							"错误", JOptionPane.ERROR_MESSAGE);
				}else{
				if(btnNewButton_1.getText().equals("修改密码")){
					textField.setEditable(false);
					password.setText("");
					password.setEditable(true);
					label.setVisible(false);
					comboBox.setVisible(false);
					setComponentVisiable(true);
					btnNewButton_1.setText("保  存");
					btnNewButton.setEnabled(false);
					btnNewButton_2.setEnabled(false);
				}else{
					String username = textField.getText();
					String password1 =String.valueOf(password.getPassword());
					String newPassword1 =String.valueOf(password_1.getPassword());
					String newPassword2 =String.valueOf(password_2.getPassword());
					if(newPassword1.equals(newPassword2)){
						int result = userDB.updateUserInfo(userName, 
								password1,newPassword1);
						if(result==-1){
							JOptionPane.showMessageDialog(thisDialog, "原密码不对!", 
									"错误", JOptionPane.ERROR_MESSAGE);
						}else if(result==0){
							JOptionPane.showMessageDialog(thisDialog, "修改不成功!", 
									"错误", JOptionPane.ERROR_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(thisDialog, "修改密码成功!", 
									"错误", JOptionPane.ERROR_MESSAGE);
						}
						setComponentEmpty();
						setComponentEnabled(false);
						label.setVisible(true);
						comboBox.setVisible(true);
						setComponentVisiable(false);
						btnNewButton_1.setText("修改密码");
						btnNewButton.setEnabled(true);
						btnNewButton_2.setEnabled(true);
					}
					else{
					  int confirm = JOptionPane.showConfirmDialog(thisDialog, 
							  "新密码与确认密码不一致，确认要重新输入密码？", 
								"确认",JOptionPane.YES_NO_OPTION);
					  if (confirm==1){
						    //setComponentEmpty();
							setComponentEnabled(false);
							label.setVisible(true);
							comboBox.setVisible(true);
							setComponentVisiable(false);
							btnNewButton_1.setText("修改密码");
							btnNewButton.setEnabled(true);
							btnNewButton_2.setEnabled(true);
					  }
					}
			   }
			}
			}		
		});
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		panel_2.add(btnNewButton_1);
		
		JSeparator separator_1 = new JSeparator();
		panel_2.add(separator_1);
		
		btnNewButton_2 = new JButton("删  除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = textField.getText();
				if(userName.equals("admin")){
					JOptionPane.showMessageDialog(thisDialog, "admin用户不能删除!", 
							"错误", JOptionPane.ERROR_MESSAGE);
				}else
				if(userName.equals("")){
					JOptionPane.showMessageDialog(thisDialog, "请选择要删除的用户!", 
							"错误", JOptionPane.ERROR_MESSAGE);
				 }
				else{
				int confirm = JOptionPane.showConfirmDialog(thisDialog, "确认删除用户"+userName+"?", 
						"确认",JOptionPane.YES_NO_OPTION);
				if (confirm==0){
				  int result = userDB.deleteUserInfo(userName);
				  if (result==0){//删除失败
					JOptionPane.showMessageDialog(thisDialog, "删除失败!", 
							"错误", JOptionPane.ERROR_MESSAGE);
				 }else{
					JOptionPane.showMessageDialog(thisDialog, "用户删除成功!", 
							"消息", JOptionPane.INFORMATION_MESSAGE);
					userList.removeElement(userName);//将该用户名从用户列表中删除
				    setComponentEmpty(); 
				  }
				}	
			 }
			}
		});
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setFont(new Font("华文仿宋", Font.BOLD, 14));
		panel_2.add(btnNewButton_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 255), null, null));
		panel.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel = new JLabel("用  户：");
		lblNewLabel.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(30, 30, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(30, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		panel_3.add(textField, gbc_textField);
				
		JLabel lblNewLabel_1 = new JLabel("密  码：");
		lblNewLabel_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 30, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		panel_3.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		password = new JPasswordField();
		password.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 2;
		password.setEchoChar('*');
		panel_3.add(password, gbc_textField_1);
			
		label = new JLabel("角  色：");
		label.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 30, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 3;
		panel_3.add(label, gbc_label);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("华文仿宋", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"admin", "user"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 3;
		panel_3.add(comboBox, gbc_comboBox);
		
		JPanel panel_4 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 255), null, null));
		panel.add(panel_3, BorderLayout.CENTER);
	
		lblNewLabel_2 = new JLabel("新密码：");
		lblNewLabel_2.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 30, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 4;
		lblNewLabel_2.setVisible(false);
		panel_3.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		
		password_1 = new JPasswordField();
		password_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 4;
		password_1.setEchoChar('*');
		password_1.setVisible(false);
		panel_3.add(password_1, gbc_textField_2);
		
		lblNewLabel_3 = new JLabel("确认密码：");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 30, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 5;
		lblNewLabel_3.setVisible(false);
		panel_3.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		password_2 = new JPasswordField();
		password_2.setHorizontalAlignment(SwingConstants.LEFT);
		password_2.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.NORTH;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 5;
		password_2.setEchoChar('*');
		password_2.setVisible(false);
		panel_3.add(password_2, gbc_textField_3);
		getContentPane().add(panel); //将panel加入到当前容器（面板）
		//独占模式
		setComponentEnabled(false);
	    this.setVisible(true);
	}
	public void setComponentEnabled(boolean flag){
		//输入框可编辑或不可编辑
		textField.setEditable(flag);
		password.setEditable(flag);
		comboBox.setEditable(flag);
	}

	public void setComponentVisiable(boolean flag){
		//新密码、确认密码可见
		lblNewLabel_2.setVisible(flag);
		password_1.setVisible(flag);
		lblNewLabel_3.setVisible(flag);
		password_2.setVisible(flag);
	}
	public void setComponentEmpty(){
		textField.setText("");
		password.setText("");
	}
	
}
