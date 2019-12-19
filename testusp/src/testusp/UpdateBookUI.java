package testusp;

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
import testusp.*;

public class UpdateBookUI extends JFrame {
   
   JButton changeButton;
 
   JTextField booknameWrite;
   JTextField isbnWrite;
   JTextField authorWrite;
   JTextField publisherWrite;
   JTextField bookNumWrite; 
   
   JPanel contentPanel;
   JPanel manageBookPanel;
   
   public UpdateBookUI() {
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
      
      
      
      changeButton = new JButton("수정");
      changeButton.setBounds(370, 70, 120, 30);
      
      changeButton.addActionListener(new ActionListener() {
          @Override
          
          public void actionPerformed(ActionEvent arg0) {
             String bookname = booknameWrite.getText();
              String isbn = isbnWrite.getText();
              int ISBN = Integer.parseInt(isbn);
              String author = authorWrite.getText();
              String publisher = publisherWrite.getText();
              String booknum = bookNumWrite.getText();
              int BookNum = Integer.parseInt(booknum);
             
              manager.book_update(ISBN, bookname, author, publisher, BookNum);
             
              dispose();
          }});
    
      
      manageBookPanel.add(changeButton);
      
      
      setVisible(true);
   }
}