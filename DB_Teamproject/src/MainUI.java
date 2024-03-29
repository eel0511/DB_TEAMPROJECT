//package testusp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
//import testusp.test;

public class MainUI extends JFrame {

	
	JButton bookMngButton;
	JButton rankButton;
	JButton userButton;
	JButton signUpButton;
	JButton loginButton;
	JButton ISBNButton;
	JButton bookNameButton;
	
	JTextField idWrite;
	JTextField pwWrite;
	JTextField ISBNWrite;
	JTextField bookWrite;
	
	static JTable table;
	
	JPanel bookListPanel;
	JPanel contentPanel;
	
	
    String colName[] = { "도서명", "ISBN", "남은 권수" };
//    Vector<String> colName = new Vector<String>();
	private DefaultTableModel model = new DefaultTableModel(colName, 0);
	
	public static void main(String[] args) {
		// TODO 자동 생성된 메소드 스텁
		MainUI obj = new MainUI();
	}

	public MainUI() {

		setTitle("DB TeamProject");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 700, 800);

		contentPanel = new JPanel();
		((JComponent) contentPanel).setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "로그인", TitledBorder.LEADING,
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

		loginButton = new JButton("로그인");
		loginButton.setBounds(300, 20, 90, 20);
		loginButton.addActionListener(new setAddressListener());
		loginPanel.add(loginButton);

		signUpButton = new JButton("회원가입");
		signUpButton.setBounds(300, 60, 90, 20);
		signUpButton.addActionListener(new setAddressListener());
		loginPanel.add(signUpButton);

		JPanel adminPanel = new JPanel();
		adminPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "관리자기능", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		adminPanel.setBounds(420, 5, 250, 100);
		adminPanel.setLayout(null);

		contentPanel.add(adminPanel);

		bookMngButton = new JButton("도서관리");
		bookMngButton.setBounds(20, 15, 90, 20);
		bookMngButton.addActionListener(new setAddressListener());
		bookMngButton.setEnabled(false);
		adminPanel.add(bookMngButton);

		rankButton = new JButton("대출 TOP");
		rankButton.setBounds(20, 40, 90, 20);
		rankButton.addActionListener(new setAddressListener());
		rankButton.setEnabled(false);
		adminPanel.add(rankButton);

		userButton = new JButton("회원관리");
		userButton.setBounds(20, 65, 90, 20);
		userButton.addActionListener(new setAddressListener());
		userButton.setEnabled(false);
		adminPanel.add(userButton);

		JPanel bookPanel = new JPanel();
		bookPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "도서 기능", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		bookPanel.setBounds(10, 120, 400, 100);
		bookPanel.setLayout(null);

		JLabel bookname = new JLabel("도서명");
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

		bookNameButton = new JButton("검색");
		bookNameButton.setBounds(300, 20, 90, 20);
		bookNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String isbn = ISBNWrite.getText();
				System.out.println("갱신");
				String bookData[][] = { { "1", "2", "3" }, { "4", "5", "6" } };
				
				
				model = new DefaultTableModel();
				 
				model.setDataVector(bookData, colName);
				
				for(int i = 0; i < bookData.length; i++) {
					table.setValueAt(bookData[i][0], i, 0);
					table.setValueAt(bookData[i][1], i, 1);
					table.setValueAt(bookData[i][2], i, 2);
				}
			}});
		bookPanel.add(bookNameButton);

		ISBNButton = new JButton("검색");
		ISBNButton.setBounds(300, 60, 90, 20);
		ISBNButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String isbn = ISBNWrite.getText();
				System.out.println("갱신");
				String bookData[][] = { { "7", "8", "9" }, { "10", "11", "12" } };
				
				
				model = new DefaultTableModel();
				 
				model.setDataVector(bookData, colName);
				
				for(int i = 0; i < bookData.length; i++) {
					table.setValueAt(bookData[i][0], i, 0);
					table.setValueAt(bookData[i][1], i, 1);
					table.setValueAt(bookData[i][2], i, 2);
				}
			}});
		
		bookPanel.add(ISBNButton);

		contentPanel.add(bookPanel);

		bookListPanel = new JPanel();
		bookListPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "검색 결과",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bookListPanel.setBounds(420, 120, 250, 250);
		bookListPanel.setLayout(null);

		contentPanel.add(bookListPanel);

		JLabel listLabel = new JLabel("ISBN              도서명                남은  권수");
		listLabel.setBounds(10, 30, 240, 20);
		bookListPanel.add(listLabel);

		String colName[] = { "도서명", "ISBN", "남은 권수" };
		String[][] bookData = new String[9][3];

		model = new DefaultTableModel();
		model.setDataVector(bookData, colName);
		
		table = new JTable(model);
		table.setBounds(10, 50, 230, 150);
		bookListPanel.add(table);

		JButton borrowButton = new JButton("대출");
		borrowButton.setBounds(10, 220, 90, 20);
		borrowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				System.out.println(row);
				int valISBN = Integer.parseInt((String) table.getValueAt(row, 0));
				String valID = idWrite.getText();
				System.out.println(valISBN +", "+valID);
			}});
		bookListPanel.add(borrowButton);

		JButton reserveButton = new JButton("예약");
		reserveButton.setBounds(120, 220, 90, 20);
		reserveButton.addActionListener(new setAddressListener());
		bookListPanel.add(reserveButton);
		
		JPanel userbookListPanel = new JPanel();
	      userbookListPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "회원 도서 목록",
	            TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	      userbookListPanel.setBounds(420, 380, 250, 250);
	      userbookListPanel.setLayout(null);
	      
	      JLabel booklistLabel = new JLabel("도서명              ISBN                  대출기간");
	      booklistLabel.setBounds(10, 30, 240, 20);
	      userbookListPanel.add(booklistLabel);

	      String user_colName[] = { "도서명", "ISBN", "대출기간" };
	      String user_bookData[][] = { { "1", "2", "3" }, { "4", "5", "6" } };

	      JTable usertable = new JTable(user_bookData, user_colName);
	      usertable.setBounds(10, 50, 230, 150);
	      userbookListPanel.add(usertable);
	      
	      JButton searchBookButton = new JButton("조회");
	      searchBookButton.setBounds(10, 220, 90, 20);
	      searchBookButton.addActionListener(new setAddressListener());
	      userbookListPanel.add(searchBookButton); 
	      
	      contentPanel.add(userbookListPanel);
	      
	      JButton returnBookButton = new JButton("반납");
	      returnBookButton.setBounds(120, 220, 90, 20);
	      returnBookButton.addActionListener(new setAddressListener());
	      userbookListPanel.add(returnBookButton);
	      
	      contentPanel.add(userbookListPanel);
	   
	      JPanel reservebookListPanel = new JPanel();
	      reservebookListPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "예약 현황",
	            TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	      reservebookListPanel.setBounds(10, 380, 400, 250);
	      reservebookListPanel.setLayout(null);
	      
	      JLabel reservebooklistLabel = new JLabel("도서명                 ISBN                    예약대기인원   대출가능기간");
	      reservebooklistLabel.setBounds(10, 30, 350, 20);
	      reservebookListPanel.add(reservebooklistLabel);

	      String reserve_colName[] = { "도서명", "ISBN", "예약대기인원", "대출가능기간" };
	      String reserve_bookData[][] = { { "1", "2", "3", "4" }, { "4", "5", "6", "8" } };

	      JTable reservetable = new JTable(reserve_bookData, reserve_colName);
	      reservetable.setBounds(10, 50, 350, 150);
	      reservebookListPanel.add(reservetable);
	      
	      JButton searchReserveBookButton  = new JButton("조회");
	      searchReserveBookButton .setBounds(10, 220, 90, 20);
	      searchReserveBookButton .addActionListener(new setAddressListener());
	      reservebookListPanel.add(searchReserveBookButton);
	      
	      contentPanel.add(reservebookListPanel);
	      
	      JButton cancleReserveBookButton = new JButton("취소");
	      cancleReserveBookButton.setBounds(120, 220, 90, 20);
	      cancleReserveBookButton.addActionListener(new setAddressListener());
	      reservebookListPanel.add(cancleReserveBookButton);
	      
	      contentPanel.add(reservebookListPanel);

		setVisible(true);

	}

	class setAddressListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == loginButton) {
				try {
				int ID = Integer.parseInt(idWrite.getText());
				int pw = Integer.parseInt(pwWrite.getText());
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "숫자를 입력해주세요.", "경고 메시지 제목", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String[] temp = {"3", "송현우"};
		
				if(temp[0].equals("0")) {
					JOptionPane.showMessageDialog(null, "등록되지 않은 회원입니다.", "경고 메시지 제목", JOptionPane.WARNING_MESSAGE);
				}
				else if(temp[0].equals("1")) {
					JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
					idWrite.setEditable(false);
					pwWrite.setEditable(false);
				}
				else if(temp[0].equals("2")) {
					JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
					idWrite.setEditable(false);
					pwWrite.setEditable(false);
				}
				else if(temp[0].equals("3")) {
					JOptionPane.showMessageDialog(null, "관리자 로그인 되었습니다.");
					idWrite.setEditable(false);
					pwWrite.setEditable(false);
					bookMngButton.setEnabled(true);
					rankButton.setEnabled(true);
					userButton.setEnabled(true);
				}
				
//				test.login(ID, pw);
			}
			else if(e.getSource() == bookNameButton) {
				String bookname = bookWrite.getText();
			}
			else if(e.getSource() == signUpButton) {
				SignUpUI signUp = new SignUpUI();
			}
			
		}
	}

}
