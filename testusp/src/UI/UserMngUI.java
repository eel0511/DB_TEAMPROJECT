package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Database.manager;
import Database.user;
import UI.ManageBookUI.setAddressListener;

public class UserMngUI extends JFrame implements MouseListener{

	JButton deleteButton;
	JButton updateButton;
	JButton searchButton;
	JButton bookMngButton;

	JTextField IDWrite;
	JTextField nameWrite;
	JTextField emailWrite;
	JTextField numberWrite;
	JTextField permissionWrite;
	JTextField pwWrite;

	JPanel contentPanel;
	JPanel manageBookPanel;

	JTable table;

	String colName[] = { "ID", "이름", "이메일", "전화번호", "구분" , "패스워드"};

	private DefaultTableModel model = new DefaultTableModel(colName, 0);
	UserMngUI() {
		setTitle("DB TeamProject");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 600, 600);

		contentPanel = new JPanel();
		((JComponent) contentPanel).setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		manageBookPanel = new JPanel();
		manageBookPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "도서관리",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		manageBookPanel.setBounds(10, 10, 555, 530);
		manageBookPanel.setLayout(null);

		contentPanel.add(manageBookPanel);

		JLabel booknameLabel = new JLabel("ID");
		booknameLabel.setBounds(10, 20, 170, 20);
		manageBookPanel.add(booknameLabel);

		IDWrite = new JTextField();
		IDWrite.setBounds(70, 20, 250, 20);
		IDWrite.setEditable(false);
		manageBookPanel.add(IDWrite);
		IDWrite.setColumns(10);

		JLabel pwLabel = new JLabel("성명");
		pwLabel.setBounds(10, 50, 170, 20);
		manageBookPanel.add(pwLabel);

		nameWrite = new JTextField();
		nameWrite.setBounds(70, 50, 250, 20);
		manageBookPanel.add(nameWrite);
		nameWrite.setColumns(10);

		JLabel authorLabel = new JLabel("이메일");
		authorLabel.setBounds(10, 80, 170, 20);
		manageBookPanel.add(authorLabel);

		emailWrite = new JTextField();
		emailWrite.setBounds(70, 80, 250, 20);
		manageBookPanel.add(emailWrite);
		emailWrite.setColumns(10);

		JLabel publisherLabel = new JLabel("전화번호");
		publisherLabel.setBounds(10, 110, 170, 20);
		manageBookPanel.add(publisherLabel);

		numberWrite = new JTextField();
		numberWrite.setBounds(70, 110, 250, 20);
		manageBookPanel.add(numberWrite);
		numberWrite.setColumns(10);

		JLabel bookNumLabel = new JLabel("구분");
		bookNumLabel.setBounds(10, 140, 170, 20);
		manageBookPanel.add(bookNumLabel);

		permissionWrite = new JTextField();
		permissionWrite.setBounds(70, 140, 250, 20);
		manageBookPanel.add(permissionWrite);
		permissionWrite.setColumns(10);
		
		JLabel passwordLabel = new JLabel("비밀번호");
		passwordLabel.setBounds(10, 170, 170, 20);
		manageBookPanel.add(passwordLabel);

		pwWrite = new JTextField();
		pwWrite.setBounds(70, 170, 250, 20);
		manageBookPanel.add(pwWrite);
		pwWrite.setColumns(10);

		updateButton = new JButton("수정");
		updateButton.setBounds(370, 70, 120, 30);

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int ID = Integer.parseInt((String)IDWrite.getText());
				String name = nameWrite.getText();
				String email = emailWrite.getText();
				int number = Integer.parseInt((String)numberWrite.getText());
				int permission = Integer.parseInt((String)permissionWrite.getText());
				int pw = Integer.parseInt((String)pwWrite.getText());

				manager.userupdate_for_manager(ID, name, email, number, permission, pw);
				
				String bookname = IDWrite.getText();
				System.out.println("갱신");
				String userData[][] = manager.userlist();

				model = new DefaultTableModel();

				model.setDataVector(userData, colName);

				for (int i = 0; i < userData.length; i++) {
					table.setValueAt(userData[i][0], i, 0);
					table.setValueAt(userData[i][1], i, 1);
					table.setValueAt(userData[i][2], i, 2);
					table.setValueAt(userData[i][3], i, 3);
					table.setValueAt(userData[i][4], i, 4);
					table.setValueAt(userData[i][5], i, 5);
				}
			}
		});

		searchButton = new JButton("검색");
		searchButton.setBounds(10, 425, 60, 20);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String bookname = IDWrite.getText();
				System.out.println("갱신");
				String userData[][] = manager.userlist();

				model = new DefaultTableModel();

				model.setDataVector(userData, colName);

				for (int i = 0; i < userData.length; i++) {
					table.setValueAt(userData[i][0], i, 0);
					table.setValueAt(userData[i][1], i, 1);
					table.setValueAt(userData[i][2], i, 2);
					table.setValueAt(userData[i][3], i, 3);
					table.setValueAt(userData[i][4], i, 4);
					table.setValueAt(userData[i][5], i, 5);
				}
			}
		});
		
		bookMngButton = new JButton("대여관리");
	    bookMngButton.setBounds(75, 425, 60, 20);
	    bookMngButton.addActionListener(new setAddressListener());

		deleteButton = new JButton("삭제");
		deleteButton.setBounds(140, 425, 60, 20);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				int ID = Integer.parseInt((String) table.getValueAt(row, 0));
				manager.secession_for_manager(ID);
				
				String bookname = IDWrite.getText();
				System.out.println("갱신");
				String userData[][] = manager.userlist();

				model = new DefaultTableModel();

				model.setDataVector(userData, colName);

				for (int i = 0; i < userData.length; i++) {
					table.setValueAt(userData[i][0], i, 0);
					table.setValueAt(userData[i][1], i, 1);
					table.setValueAt(userData[i][2], i, 2);
					table.setValueAt(userData[i][3], i, 3);
					table.setValueAt(userData[i][4], i, 4);
					table.setValueAt(userData[i][5], i, 5);
				}
			}
		});

		JButton disposeButton = new JButton("종료");
		disposeButton.setBounds(205, 425, 60, 20);
		disposeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		manageBookPanel.add(disposeButton);
		manageBookPanel.add(searchButton);
		manageBookPanel.add(bookMngButton);
		manageBookPanel.add(updateButton);
		manageBookPanel.add(deleteButton);

		JLabel listLabel = new JLabel(
				"    ID                     이름                   이메일                전화번호                  구분               패스워드");
		listLabel.setBounds(10, 200, 500, 20);
		manageBookPanel.add(listLabel);

		String colName[] = { "ID", "이름", "이메일", "전화번호", "구분" ,"패스워드"};
		String[][] bookData = new String[12][6];

		model = new DefaultTableModel();
		model.setDataVector(bookData, colName);

		table = new JTable(model);
		table.setBounds(10, 220, 500, 195);
		table.addMouseListener(this);
		manageBookPanel.add(table);

		setVisible(true);
	}

	class setAddressListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		   int row = table.getSelectedRow();
		   int ID = Integer.parseInt((String) table.getValueAt(row, 0));
           UserBookMngUI mng = new UserBookMngUI(ID);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			int row = table.getSelectedRow();
			IDWrite.setText((String) table.getValueAt(row, 0));
			nameWrite.setText((String) table.getValueAt(row, 1));
			emailWrite.setText((String) table.getValueAt(row, 2));
			numberWrite.setText((String) table.getValueAt(row, 3));
			permissionWrite.setText((String) table.getValueAt(row, 4));
			pwWrite.setText((String) table.getValueAt(row, 5));
		} catch (NumberFormatException e1) {
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}

}
