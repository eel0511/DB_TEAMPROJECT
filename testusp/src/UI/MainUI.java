package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.*;

import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.JButton;
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

import Database.*;

public class MainUI extends JFrame implements MouseListener {

	JButton bookMngButton;
	JButton rankButton;
	JButton userButton;
	JButton signUpButton;

	JButton loginButton;
	JButton ISBNButton;
	JButton bookNameButton;

	JButton updateButton;
	JButton mybookButton;
	JButton secessionButton;
	JButton returnBookButton;
	JButton logOutButton;

	JButton reserveButton;
	JButton borrowButton;

	JButton searchBookButton;

	JButton searchReserveBookButton;

	JTextField idWrite;
	JTextField pwWrite;
	JTextField ISBNWrite;
	JTextField bookWrite;

	static JTable table;
	static JTable usertable;

	JPanel bookListPanel;
	JPanel contentPanel;

	static int grade;

	String colName[] = { "������", "ISBN", "���� �Ǽ�" };
//    Vector<String> colName = new Vector<String>();
	private DefaultTableModel model = new DefaultTableModel(colName, 0);

	public MainUI() {

		setTitle("DB TeamProject");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 700, 800);

		contentPanel = new JPanel();
		((JComponent) contentPanel).setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�α���", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		loginPanel.setBounds(10, 5, 400, 100);
		loginPanel.setLayout(null);

		contentPanel.add(loginPanel);

		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(10, 20, 170, 20);
		loginPanel.add(idLabel);

		idWrite = new JTextField();
		idWrite.setBounds(40, 20, 250, 20);
		loginPanel.add(idWrite);
		idWrite.setColumns(10);

		JLabel pwLabel = new JLabel("PW");
		pwLabel.setBounds(10, 60, 170, 20);
		loginPanel.add(pwLabel);

		pwWrite = new JTextField();
		pwWrite.setBounds(40, 60, 250, 20);
		loginPanel.add(pwWrite);
		pwWrite.setColumns(10);

		loginButton = new JButton("�α���");
		loginButton.setBounds(300, 20, 90, 20);
		loginButton.addActionListener(new setAddressListener());
		loginPanel.add(loginButton);

		signUpButton = new JButton("ȸ������");
		signUpButton.setBounds(300, 60, 90, 20);
		signUpButton.addActionListener(new setAddressListener());
		loginPanel.add(signUpButton);

		JPanel adminPanel = new JPanel();
		adminPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�����ڱ��", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		adminPanel.setBounds(420, 5, 110, 100);
		adminPanel.setLayout(null);

		contentPanel.add(adminPanel);

		bookMngButton = new JButton("��������");
		bookMngButton.setBounds(10, 15, 90, 20);
		bookMngButton.addActionListener(new setAddressListener());
		bookMngButton.setEnabled(false);
		adminPanel.add(bookMngButton);

		rankButton = new JButton("���� TOP");
		rankButton.setBounds(10, 40, 90, 20);
		rankButton.addActionListener(new setAddressListener());
		rankButton.setEnabled(false);
		adminPanel.add(rankButton);

		userButton = new JButton("�ݳ�����");
		userButton.setBounds(10, 65, 90, 20);
		userButton.addActionListener(new setAddressListener());
		userButton.setEnabled(false);
		adminPanel.add(userButton);

		JPanel userPanel = new JPanel();
		userPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "����ڱ��", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		userPanel.setBounds(530, 5, 110, 100);
		userPanel.setLayout(null);

		contentPanel.add(userPanel);

		updateButton = new JButton("��������");
		updateButton.setBounds(10, 15, 90, 20);
		updateButton.addActionListener(new setAddressListener());
		updateButton.setEnabled(false);
		userPanel.add(updateButton);

		secessionButton = new JButton("Ż��");
		secessionButton.setBounds(10, 65, 90, 20);
		secessionButton.addActionListener(new setAddressListener());
		secessionButton.setEnabled(false);
		userPanel.add(secessionButton);

		JPanel bookPanel = new JPanel();
		bookPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "���� ���", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		bookPanel.setBounds(10, 120, 400, 100);
		bookPanel.setLayout(null);

		JLabel bookname = new JLabel("������");
		bookname.setBounds(10, 20, 170, 20);
		bookPanel.add(bookname);

		bookWrite = new JTextField();
		bookWrite.setBounds(50, 20, 240, 20);
		bookWrite.setColumns(10);
		bookPanel.add(bookWrite);

		JLabel ISBN = new JLabel("ISBN");
		ISBN.setBounds(10, 60, 170, 20);
		bookPanel.add(ISBN);

		ISBNWrite = new JTextField();
		ISBNWrite.setBounds(50, 60, 240, 20);
		ISBNWrite.setColumns(10);
		bookPanel.add(ISBNWrite);

		bookNameButton = new JButton("�˻�");
		bookNameButton.setBounds(300, 20, 90, 20);
		bookNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String bookname = bookWrite.getText();
				System.out.println("����");
				String bookData[][] = user.bookinfoname(bookname);

				model = new DefaultTableModel();

				model.setDataVector(bookData, colName);

				for (int i = 0; i < bookData.length; i++) {
					table.setValueAt(bookData[i][0], i, 0);
					table.setValueAt(bookData[i][1], i, 1);
					table.setValueAt(bookData[i][2], i, 2);
				}
			}
		});
		bookPanel.add(bookNameButton);

		ISBNButton = new JButton("�˻�");
		ISBNButton.setBounds(300, 60, 90, 20);
		ISBNButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String isbn = ISBNWrite.getText();
				System.out.println("����");
				String bookData[][] = user.bookinfoISBN(Integer.valueOf(isbn));

				model = new DefaultTableModel();

				model.setDataVector(bookData, colName);

				for (int i = 0; i < bookData.length; i++) {
					table.setValueAt(bookData[i][0], i, 0);
					table.setValueAt(bookData[i][1], i, 1);
					table.setValueAt(bookData[i][2], i, 2);
				}
			}
		});

		bookPanel.add(ISBNButton);

		contentPanel.add(bookPanel);

		JPanel myReservePanel = new JPanel();
		myReservePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�� ���� ��Ȳ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		myReservePanel.setBounds(10, 220, 400, 160);

		contentPanel.add(myReservePanel);

		bookListPanel = new JPanel();
		bookListPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�˻� ���",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bookListPanel.setBounds(420, 120, 250, 250);
		bookListPanel.setLayout(null);

		contentPanel.add(bookListPanel);

		JLabel listLabel = new JLabel("������              ISBN                ����  �Ǽ�");
		listLabel.setBounds(10, 30, 240, 20);
		bookListPanel.add(listLabel);

		String colName[] = { "������", "ISBN", "���� �Ǽ�" };
		String[][] bookData = new String[9][3];

		model = new DefaultTableModel();
		model.setDataVector(bookData, colName);

		table = new JTable(model);
		table.setBounds(10, 50, 230, 150);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(this);
		bookListPanel.add(table);

		borrowButton = new JButton("����");
		borrowButton.setBounds(10, 220, 90, 20);
		borrowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				System.out.println(row);
				int valISBN = Integer.parseInt((String) table.getValueAt(row, 0));
				int valID = Integer.parseInt(idWrite.getText());
				int valcount = Integer.parseInt((String) table.getValueAt(row, 2));
				borrow b = new borrow();
				b.borrow(valISBN, valID, valcount, grade);

				String bookname = bookWrite.getText();
				System.out.println("����");
				String bookData[][] = user.bookinfoname(bookname);

				model = new DefaultTableModel();

				model.setDataVector(bookData, colName);

				for (int i = 0; i < bookData.length; i++) {
					table.setValueAt(bookData[i][0], i, 0);
					table.setValueAt(bookData[i][1], i, 1);
					table.setValueAt(bookData[i][2], i, 2);
				}
			}
		});
		bookListPanel.add(borrowButton);

		reserveButton = new JButton("����");
		reserveButton.setBounds(120, 220, 90, 20);
		reserveButton.addActionListener(new setAddressListener());
		bookListPanel.add(reserveButton);

		JPanel userbookListPanel = new JPanel();
		userbookListPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "ȸ�� ���� ���",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		userbookListPanel.setBounds(390, 380, 280, 250);
		userbookListPanel.setLayout(null);

		JLabel booklistLabel = new JLabel("������       ISBN          ���⳯¥  �ݳ�����");
		booklistLabel.setBounds(10, 30, 240, 20);
		userbookListPanel.add(booklistLabel);

		String user_colName[] = { "������", "ISBN", "����Ⱓ", "�ݳ�" };
		String user_bookData[][] = new String[15][4];

		usertable = new JTable(user_bookData, user_colName);
		usertable.setBounds(10, 50, 260, 150);
		userbookListPanel.add(usertable);

		searchBookButton = new JButton("��ȸ");
		searchBookButton.setBounds(10, 220, 90, 20);
		searchBookButton.addActionListener(new setAddressListener());
		userbookListPanel.add(searchBookButton);

		contentPanel.add(userbookListPanel);

		returnBookButton = new JButton("�ݳ�");
		returnBookButton.setBounds(120, 220, 90, 20);
		returnBookButton.addActionListener(new setAddressListener());
		userbookListPanel.add(returnBookButton);

		contentPanel.add(userbookListPanel);

		JPanel reservebookListPanel = new JPanel();
		reservebookListPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "���� ��Ȳ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		reservebookListPanel.setBounds(10, 380, 370, 250);
		reservebookListPanel.setLayout(null);

		JLabel reservebooklistLabel = new JLabel("������                 ISBN                    �������ο�   ���Ⱑ�ɱⰣ");
		reservebooklistLabel.setBounds(10, 30, 350, 20);
		reservebookListPanel.add(reservebooklistLabel);

		String reserve_colName[] = { "������", "ISBN", "�������ο�", "���Ⱑ�ɱⰣ" };
		String reserve_bookData[][] = new String[14][4];

		JTable reservetable = new JTable(reserve_bookData, reserve_colName);
		reservetable.setBounds(10, 50, 350, 150);
		reservebookListPanel.add(reservetable);

		searchReserveBookButton = new JButton("����");
		searchReserveBookButton.setBounds(10, 220, 90, 20);
		searchReserveBookButton.addActionListener(new setAddressListener());
		reservebookListPanel.add(searchReserveBookButton);

		contentPanel.add(reservebookListPanel);


		logOutButton = new JButton("�α׾ƿ�");
		logOutButton.setBounds(15, 640, 100, 20);
		logOutButton.setEnabled(false);
		logOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setLogOut();
			}
		});
		contentPanel.add(logOutButton);

		setLogOut();

		setVisible(true);

	}

	class setAddressListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == loginButton) {
				int ID = Integer.parseInt(idWrite.getText());
				int pw = Integer.parseInt(pwWrite.getText());

				String[] temp = user.login(ID, pw);

				if (temp[0].equals("0")) {
					JOptionPane.showMessageDialog(null, "��ϵ��� ���� ȸ���Դϴ�.", "��� �޽��� ����", JOptionPane.WARNING_MESSAGE);
				} else if (temp[0].equals("1")) { // �л�
					setUser();
					grade = 1;
				} else if (temp[0].equals("2")) {// ���п���
					setUser();
					grade = 2;
				} else if (temp[0].equals("3")) {// ������
					setUser();
					grade = 3;
				} else if (temp[0].equals("4")) {// ������
					setAdmin();
					grade = 4;
				}

//            test.login(ID, pw);
			} else if (e.getSource() == bookNameButton) {
				String bookname = bookWrite.getText();
			} else if (e.getSource() == signUpButton) {
				SignUpUI obj = new SignUpUI();
			} else if (e.getSource() == updateButton) {
				UpdateUI update = new UpdateUI(idWrite.getText());
			} else if (e.getSource() == secessionButton) {
				int valId = Integer.parseInt(idWrite.getText());
				int valpw = Integer.parseInt(pwWrite.getText());
				user u = new user();
				u.secession(valId, valpw);
				JOptionPane.showMessageDialog(null, "Ż��Ǿ����ϴ�.");
				idWrite.setText("");
				pwWrite.setText("");

				idWrite.setEditable(true);
				pwWrite.setEditable(true);

			} else if (e.getSource() == searchBookButton) {
				int ID = Integer.parseInt(idWrite.getText());
				String bookData[][] = borrow.userbooklist(ID);

				model = new DefaultTableModel();

				model.setDataVector(bookData, colName);

				for (int i = 0; i < bookData.length; i++) {
					usertable.setValueAt(bookData[i][0], i, 0);
					usertable.setValueAt(bookData[i][1], i, 1);
					usertable.setValueAt(bookData[i][2], i, 2);
					usertable.setValueAt(bookData[i][3], i, 3);
				}
			} else if (e.getSource() == bookMngButton) {
				ManageBookUI managebook = new ManageBookUI();

			} else if (e.getSource() == returnBookButton) {

				int row = usertable.getSelectedRow();
				System.out.println(row);
				int valISBN = Integer.parseInt((String) usertable.getValueAt(row, 1));
				int ID = Integer.parseInt(idWrite.getText());
				borrow.userbookreturnrequset(ID, valISBN);

				String bookData[][] = borrow.userbooklist(ID);

				model = new DefaultTableModel();

				model.setDataVector(bookData, colName);

				for (int i = 0; i < bookData.length; i++) {
					usertable.setValueAt(bookData[i][0], i, 0);
					usertable.setValueAt(bookData[i][1], i, 1);
					usertable.setValueAt(bookData[i][2], i, 2);
					usertable.setValueAt(bookData[i][3], i, 3);
				}
			} else if (e.getSource() == userButton) {
				ApproveUI update = new ApproveUI();
			}
			else if(e.getSource() == rankButton) {
				RankUI rank = new RankUI();
	         }
		}

	}

	public void setLogOut() {
		loginButton.setEnabled(true);
		idWrite.setEditable(true);
		idWrite.setText("");
		pwWrite.setEditable(true);
		pwWrite.setText("");
		signUpButton.setEnabled(true);

		ISBNButton.setEnabled(false);
		bookNameButton.setEnabled(false);

		bookMngButton.setEnabled(false);
		rankButton.setEnabled(false);
		userButton.setEnabled(false);

		updateButton.setEnabled(false);
		secessionButton.setEnabled(false);
		logOutButton.setEnabled(false);

		reserveButton.setEnabled(false);
		borrowButton.setEnabled(false);

		searchBookButton.setEnabled(false);
		returnBookButton.setEnabled(false);

		searchReserveBookButton.setEnabled(false);
	}

	public void setUser() {

		JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");

		loginButton.setEnabled(false);
		idWrite.setEditable(false);
		pwWrite.setEditable(false);
		signUpButton.setEnabled(false);

		ISBNButton.setEnabled(true);
		bookNameButton.setEnabled(true);

		updateButton.setEnabled(true);
		secessionButton.setEnabled(true);

		reserveButton.setEnabled(false);
		borrowButton.setEnabled(true);

		searchBookButton.setEnabled(true);
		returnBookButton.setEnabled(true);

		searchReserveBookButton.setEnabled(true);

		logOutButton.setEnabled(true);
	}

	public void setAdmin() {
		JOptionPane.showMessageDialog(null, "������ �α��� �Ǿ����ϴ�.");

		bookMngButton.setEnabled(true);
		rankButton.setEnabled(true);
		userButton.setEnabled(true);
		idWrite.setEditable(false);
		pwWrite.setEditable(false);
		loginButton.setEnabled(false);
		signUpButton.setEnabled(false);

		logOutButton.setEnabled(true);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
		int row = table.getSelectedRow();
		if(Integer.parseInt((String) table.getValueAt(row, 2)) == 0) {
			reserveButton.setEnabled(true);
		}else {
			reserveButton.setEnabled(false);
		}}catch(NumberFormatException e1) {
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}
}