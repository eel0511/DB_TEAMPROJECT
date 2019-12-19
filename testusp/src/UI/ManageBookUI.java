package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import Database.*;

public class ManageBookUI extends JFrame {
   
   JButton deleteButton;
   JButton updateButton;
   JButton changeButton;
   JButton searchButton;
   
   JTextField booknameWrite;
   JTextField isbnWrite;
   JTextField authorWrite;
   JTextField publisherWrite;
   JTextField bookNumWrite; 
   
   JPanel contentPanel;
   JPanel manageBookPanel;
   
   JTable table;
   
   String colName[] = { "도서명", "ISBN", "저자", "출판사", "수량" };
// Vector<String> colName = new Vector<String>();
private DefaultTableModel model = new DefaultTableModel(colName, 0);
   
   public ManageBookUI() {
      setTitle("DB TeamProject");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(250, 250, 600, 600);
      
      contentPanel = new JPanel();
      ((JComponent) contentPanel).setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPanel);
      contentPanel.setLayout(null);
      
      manageBookPanel = new JPanel();
      manageBookPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "도서관리", TitledBorder.LEADING,
            TitledBorder.TOP, null, new Color(0, 0, 0)));
      manageBookPanel.setBounds(10, 10, 555, 530);
      manageBookPanel.setLayout(null);
      
      contentPanel.add(manageBookPanel);
      
      JLabel booknameLabel = new JLabel("도서명");
      booknameLabel.setBounds(10, 20, 170, 20);
      manageBookPanel.add(booknameLabel);

      booknameWrite = new JTextField();
      booknameWrite.setBounds(50, 20, 250, 20);
      manageBookPanel.add(booknameWrite);
      booknameWrite.setColumns(10);

      JLabel pwLabel = new JLabel("ISBN");
      pwLabel.setBounds(15, 50, 170, 20);
      manageBookPanel.add(pwLabel);

      isbnWrite = new JTextField();
      isbnWrite.setBounds(50, 50, 250, 20);
      manageBookPanel.add(isbnWrite);
      isbnWrite.setColumns(10);
      
      JLabel authorLabel = new JLabel("저자");
      authorLabel.setBounds(15, 80, 170, 20);
      manageBookPanel.add(authorLabel);

      authorWrite = new JTextField();
      authorWrite.setBounds(50, 80, 250, 20);
      manageBookPanel.add(authorWrite);
      authorWrite.setColumns(10);
      
      JLabel publisherLabel = new JLabel("출판사");
      publisherLabel.setBounds(10, 110, 170, 20);
      manageBookPanel.add(publisherLabel);

      publisherWrite = new JTextField();
      publisherWrite.setBounds(50, 110, 250, 20);
      manageBookPanel.add(publisherWrite);
      publisherWrite.setColumns(10);
      
      JLabel bookNumLabel = new JLabel("수량");
      bookNumLabel.setBounds(10, 140, 170, 20);
      manageBookPanel.add(bookNumLabel);

      bookNumWrite = new JTextField();
      bookNumWrite.setBounds(50, 140, 250, 20);
      manageBookPanel.add(bookNumWrite);
      bookNumWrite.setColumns(10);
      
      
      
      updateButton = new JButton("등록");
      updateButton.setBounds(370, 70, 120, 30);
      
      updateButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent arg0) {
             String bookname = booknameWrite.getText();
              String isbn = isbnWrite.getText();
              int ISBN = Integer.parseInt(isbn);
              String author = authorWrite.getText();
              String publisher = publisherWrite.getText();
              String booknum = bookNumWrite.getText();
              int BookNum = Integer.parseInt(booknum);
             
              manager.add_book(ISBN, bookname, author, publisher, BookNum);
             
              dispose();
          }});
    
      
      searchButton = new JButton("검색");
      searchButton.setBounds(10, 425, 60, 20);
      searchButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent arg0) {
             String bookname = booknameWrite.getText();
             System.out.println("갱신");
             String bookData[][] = manager.managerbookinfoname(bookname);

             model = new DefaultTableModel();

             model.setDataVector(bookData, colName);

             for (int i = 0; i < bookData.length; i++) {
                table.setValueAt(bookData[i][0], i, 0);
                table.setValueAt(bookData[i][1], i, 1);
                table.setValueAt(bookData[i][2], i, 2);
                table.setValueAt(bookData[i][3], i, 3);
                table.setValueAt(bookData[i][4], i, 4);
             }
          }
       });
      changeButton = new JButton("수정");
      changeButton.setBounds(75, 425, 60, 20);
      changeButton.addActionListener(new setAddressListener());
      
      deleteButton = new JButton("삭제");
      deleteButton.setBounds(140, 425, 60, 20);
      deleteButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent arg0) {
             int row = table.getSelectedRow();
             int valISBN = Integer.parseInt((String) table.getValueAt(row, 1));
             manager.book_delete(valISBN);
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
      manageBookPanel.add(updateButton);
      manageBookPanel.add(changeButton);
      manageBookPanel.add(deleteButton);
      
      JLabel listLabel = new JLabel("          도서명                     ISBN                         저자                       출판사                     수량");
      listLabel.setBounds(10, 200, 500, 20);
      manageBookPanel.add(listLabel);

      String colName[] = { "도서명", "ISBN", "저자", "출판사", "수량" };
      String[][] bookData = new String[12][5];

      model = new DefaultTableModel();
      model.setDataVector(bookData, colName);
      
      table = new JTable(model);
      table.setBounds(10, 220, 500, 195);
      manageBookPanel.add(table);
      
      setVisible(true);
   }
   
   class setAddressListener implements ActionListener {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	         if (e.getSource() == changeButton) {
	        	 int row = table.getSelectedRow();
				int ISBN = Integer.parseInt((String) table.getValueAt(row, 1));
	            UpdateBookUI updateBook = new UpdateBookUI(ISBN);
	            
	         }

	   }
	   }
}