package mangerSystem;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MessageDialog extends JDialog {
	/**
	 * Create the dialog.
	 */
	JList list;
	JTextArea textArea_1;
	JTextArea textArea;
	JButton btnNewButton_1;
	JButton btnNewButton;
	JCheckBox isServer;
	MessageServer server;
	MessageClient client =null;
	DefaultListModel<String> userList;
	MessageDialog dialog =this;
	
	public MessageDialog(JFrame f,String title,boolean modal) {
		this.setTitle("在线交流");
		getContentPane().setLayout(new BorderLayout());
		this.setAlwaysOnTop(true);
		this.setModal(modal);
		getContentPane().setFont(new Font("华文仿宋", Font.BOLD, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 160, 600, 450);
			
		JPanel panel0 = new JPanel();
		panel0.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel0.setLayout(new BorderLayout());
				
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel0.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("\u5728\u7EBF\u7528\u6237\u5217\u8868");
		lblNewLabel.setFont(new Font("华文仿宋", Font.BOLD, 14));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		panel_4.add(separator, BorderLayout.NORTH);
		
		userList =new DefaultListModel<String>();
		list = new JList(userList);
		list.setFont(new Font("华文仿宋", Font.PLAIN, 14));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.add(list, BorderLayout.CENTER);
	
		panel_4.add(list, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel0.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {30, 10, 0, 10};
		gbl_panel_2.rowHeights = new int[]{24, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE, 0.0};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.gridwidth = 2;
		gbc_textArea_1.insets = new Insets(0, 0, 0, 5);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 0;
		panel_2.add(textArea_1, gbc_textArea_1);
		
		btnNewButton_1 = new JButton("发  送");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (client==null){
					client = new MessageClient(dialog);
				}
				client.send();
			}
		});
		btnNewButton_1.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		panel_2.add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton = new JButton("清  屏");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    textArea.setText("");
			}
		});
		btnNewButton.setFont(new Font("华文仿宋", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		panel_2.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setRows(20);
		JScrollPane sp = new JScrollPane(textArea);
		panel_3.add(sp,BorderLayout.CENTER);
		
		
		JPanel panel_5 = new JPanel();
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{136, 133, 84, 6, 0};
		gbl_panel_5.rowHeights = new int[]{23, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		isServer = new JCheckBox("是否兼做服务器端？");
		isServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isServer.isSelected()){//创建服务器端
				   	server = new MessageServer(dialog);	
				   	server.start();
			   }
			}
		});
		isServer.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_isServer = new GridBagConstraints();
		gbc_isServer.anchor = GridBagConstraints.NORTHWEST;
		gbc_isServer.insets = new Insets(0, 0, 0, 5);
		gbc_isServer.gridx = 0;
		gbc_isServer.gridy = 0;
		panel_5.add(isServer, gbc_isServer);
		
		panel_3.add(panel_5,BorderLayout.NORTH);
		getContentPane().add(panel0); //将panel加入到当前容器（面板）
	    
		this.setVisible(true);
	  
	}
}
