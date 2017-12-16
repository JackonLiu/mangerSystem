package mangerSystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class LoginFrame extends JFrame {
	JPanel contentPane;
	JTextField textField;
	JLabel lblNewLabel_1;
	JTextField textField_1;
	JPasswordField password;
	JButton btnNewButton;
	JButton btnNewButton_1;
	JFrame jframe = this;

	public LoginFrame() {
		this.setTitle("登录");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 377, 264);
		this.setSize(new Dimension(400, 300));
		this.setLocation(450,240);
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("ScrollPane.border"));
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("用  户： ");
		lblNewLabel.setFont(new Font("华文仿宋", Font.BOLD, 14));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 2;
		gbc_textField.gridwidth=2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		//回车，光标跳至密码输入框
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					textField.setFocusable(false);
					password.setFocusable(true);
				}
			}
		});
		
		lblNewLabel_1 = new JLabel("密  码：  ");
		lblNewLabel_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 3;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		password = new JPasswordField();
		password.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 3;
		gbc_textField_1.gridwidth=2;
		panel.add(password, gbc_textField_1);
		password.setColumns(10);
		password.setEchoChar('*');
		
		password.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					
					UserDB userLogin = new UserDB();
					String userName = textField.getText();
					String password1 = String.valueOf(password.getPassword());		
					if(userLogin.isLoginSuccess(userName,password1)){//登录验证  userLogin.isLoginSuccess(userName,password1)
						new MainFrame(userName);//创建主窗体，并显示
						jframe.dispose();//关闭登录窗口
					}
					else{//显示错误信息
						JOptionPane.showMessageDialog(jframe, "用户名或密码错误!", 
								"错误", JOptionPane.ERROR_MESSAGE);
						textField.setFocusable(true);
						}
				}
			}
		});
		
		btnNewButton = new JButton("登  录");
		//点击登录按钮，进行登录验证
		
		/**
		 *匹配不正确，可重试三次
		 */
		btnNewButton.addActionListener(new ActionListener() {
			int i=1;
			public void actionPerformed(ActionEvent e) {
				UserDB userLogin = new UserDB();
				String userName = textField.getText();
				String password1 = String.valueOf(password.getPassword());		
				if(userLogin.isLoginSuccess(userName,password1)){//登录验证
								new MainFrame(userName);//创建主窗体，并显示
					jframe.dispose();//关闭登录窗口
				}
				else{//显示错误信息,匹配不正确，可重试三次
					if(i<3){
						
					JOptionPane.showMessageDialog(jframe, "用户名或密码错误，您还有"+(3-i)+"次机会", 
							"错误", JOptionPane.ERROR_MESSAGE);
					  textField.setFocusable(true);
					  i++;
					}
					else{
						jframe.dispose();//关闭登录窗口
					}
					}
				
			}
		});
		btnNewButton.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(20, 2, 0, 20);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 5;
		gbc_btnNewButton.gridheight = 2;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton_1 = new JButton("取  消");
		btnNewButton_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(20, 20, 0, 5);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 5;
		gbc_btnNewButton_1.gridheight = 2;
		//gbc_btnNewButton_1.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		//点击取消按钮，系统退出
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		
		//contentPane.add(panel, BorderLayout.CENTER);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	 public static void main(String []args){
		   new LoginFrame();
	   }
}
