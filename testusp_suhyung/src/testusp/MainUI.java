package testusp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

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
import testusp.test;

public class MainUI extends JFrame {

	JButton loginButton;
	JTextField idWrite;
	JTextField pwWrite;

	public static void main(String[] args) {
		// TODO �ڵ� ������ �޼ҵ� ����
		MainUI obj = new MainUI();
	}

	public MainUI() {

		setTitle("DB TeamProject");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 700, 800);

		JPanel contentPanel = new JPanel();
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

		JButton signUpButton = new JButton("ȸ������");
		signUpButton.setBounds(300, 60, 90, 20);
		loginButton.addActionListener(new setAddressListener());
		loginPanel.add(signUpButton);

		JPanel adminPanel = new JPanel();
		adminPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�����ڱ��", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		adminPanel.setBounds(420, 5, 250, 100);
		adminPanel.setLayout(null);

		contentPanel.add(adminPanel);

		JButton bookMngButton = new JButton("��������");
		bookMngButton.setBounds(20, 15, 90, 20);
		loginButton.addActionListener(new setAddressListener());
		bookMngButton.setEnabled(false);
		adminPanel.add(bookMngButton);

		JButton rankButton = new JButton("���� TOP");
		rankButton.setBounds(20, 40, 90, 20);
		loginButton.addActionListener(new setAddressListener());
		rankButton.setEnabled(false);
		adminPanel.add(rankButton);

		JButton userButton = new JButton("ȸ������");
		userButton.setBounds(20, 65, 90, 20);
		loginButton.addActionListener(new setAddressListener());
		userButton.setEnabled(false);
		adminPanel.add(userButton);

		JPanel bookPanel = new JPanel();
		bookPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "���� ���", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		bookPanel.setBounds(10, 120, 400, 100);
		bookPanel.setLayout(null);

		JLabel bookname = new JLabel("������");
		bookname.setBounds(10, 20, 170, 20);
		bookPanel.add(bookname);

		JTextField bookWrite = new JTextField();
		bookWrite.setBounds(50, 20, 240, 20);
		bookWrite.setColumns(10);
		bookPanel.add(bookWrite);

		JLabel ISBN = new JLabel("ISBN");
		ISBN.setBounds(10, 60, 170, 20);
		bookPanel.add(ISBN);

		JTextField ISBNWrite = new JTextField();
		ISBNWrite.setBounds(50, 60, 240, 20);
		ISBNWrite.setColumns(10);
		bookPanel.add(ISBNWrite);

		JButton bookNameButton = new JButton("�˻�");
		bookNameButton.setBounds(300, 60, 90, 20);
		loginButton.addActionListener(new setAddressListener());
		bookPanel.add(bookNameButton);

		JButton ISBNButton = new JButton("�˻�");
		ISBNButton.setBounds(300, 20, 90, 20);
		loginButton.addActionListener(new setAddressListener());
		bookPanel.add(ISBNButton);

		contentPanel.add(bookPanel);

		JPanel bookListPanel = new JPanel();
		bookListPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�˻� ���",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bookListPanel.setBounds(420, 120, 250, 250);
		bookListPanel.setLayout(null);

		contentPanel.add(bookListPanel);

		JLabel listLabel = new JLabel("������              ISBN                ����  �Ǽ�");
		listLabel.setBounds(10, 30, 240, 20);
		bookListPanel.add(listLabel);

		String colName[] = { "������", "ISBN", "���� �Ǽ�" };
		String bookData[][] = { { "1", "2", "3" }, { "4", "5", "6" } };

		JTable table = new JTable(bookData, colName);
		table.setBounds(10, 50, 230, 150);
		bookListPanel.add(table);

		JButton borrowButton = new JButton("����");
		borrowButton.setBounds(10, 220, 90, 20);
		borrowButton.addActionListener(new setAddressListener());
		bookListPanel.add(borrowButton);

		JButton reserveButton = new JButton("����");
		reserveButton.setBounds(120, 220, 90, 20);
		reserveButton.addActionListener(new setAddressListener());
		bookListPanel.add(reserveButton);

		setVisible(true);

	}

	class setAddressListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == loginButton) {
				int ID = Integer.parseInt(idWrite.getText());
				int pw = Integer.parseInt(pwWrite.getText());
				if(test.login(ID, pw)) {
					System.out.println("a");
				}
					
			}
			
		}
	}

}
