package mangerSystem;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame {
    UserManagerDialog userDialog;
    QueryDialog queryDialog;
    MessageDialog messageDialog;
    SalaryInforDialog salaryInforDialog;
    BasicInforDialog basicInforDialog;
	//private JPanel contentPane ;
    JFrame jframe = this;
    static String userName;
  	public MainFrame(String userName){//创建窗体
  		MainFrame.userName=userName;
		setTitle("工资信息管理系统" + userName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(80, 80, 800, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		//getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//getContentPane().setLayout(new BorderLayout(0, 0));
		//添加菜单、菜单项
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("用户管理");
		menuBar.add(menu);
		//菜单项点击事件，显示用户管理面板
		JMenuItem menuItem = new JMenuItem("用户管理");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDialog = new UserManagerDialog(jframe,"用户管理",true); 
			}
		});
		
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("员工信息管理");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("基本信息管理");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basicInforDialog = new BasicInforDialog(
						jframe,"基本信息管理",true);
			}
		});
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_4 = new JMenuItem("工资信息管理");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salaryInforDialog = new SalaryInforDialog(
						jframe,"工资信息管理",true);
			}
		});
		menu_1.add(menuItem_4);
		
		JMenu menu_2 = new JMenu("工资信息查询");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_2 = new JMenuItem("工资信息查询");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryDialog = new QueryDialog(jframe,"工资信息查询",true);
			}
		});
		menu_2.add(menuItem_2);
		
		JMenu menu_3 = new JMenu("在线交流");
		menuBar.add(menu_3);
		
		JMenuItem menuItem_3 = new JMenuItem("在线交流");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messageDialog = new MessageDialog(jframe,"留言管理",true);
			}
		});
		menu_3.add(menuItem_3);
		
		this.setVisible(true);

	}

  	public static void main(String[] args) {
		 new MainFrame(" Java ");
	}
  	
}
