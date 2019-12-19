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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class UpdateUI extends JFrame {
   
   JButton submitButton;
   
   JTextField idWrite;
   JTextField pwWrite;
   JTextField nameWrite;
   JTextField emailWrite;
   JTextField codeWrite;
   JTextField numberWrite;
   
   JPanel contentPanel;
   JPanel signUpPanel;
   
   JComboBox Combo;

   UpdateUI(String id){
      setTitle("DB TeamProject");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(250, 250, 400, 400);
      
      contentPanel = new JPanel();
      ((JComponent) contentPanel).setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPanel);
      contentPanel.setLayout(null);
      
      signUpPanel = new JPanel();
      signUpPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "정보수정", TitledBorder.LEADING,
            TitledBorder.TOP, null, new Color(0, 0, 0)));
      signUpPanel.setBounds(10, 5, 360, 330);
      signUpPanel.setLayout(null);
      
      contentPanel.add(signUpPanel);
      
      JLabel idLabel = new JLabel("ID");
      idLabel.setBounds(10, 20, 170, 20);
      signUpPanel.add(idLabel);

      idWrite = new JTextField();
      idWrite.setBounds(40, 20, 250, 20);
      signUpPanel.add(idWrite);
      idWrite.setColumns(10);
      idWrite.setText(id);
      idWrite.setEditable(false);

      JLabel pwLabel = new JLabel("PW");
      pwLabel.setBounds(10, 60, 170, 20);
      signUpPanel.add(pwLabel);

      pwWrite = new JTextField();
      pwWrite.setBounds(40, 60, 250, 20);
      signUpPanel.add(pwWrite);
      pwWrite.setColumns(10);
      
      JLabel nameLabel = new JLabel("성명");
      nameLabel.setBounds(10, 100, 170, 20);
      signUpPanel.add(nameLabel);

      nameWrite = new JTextField();
      nameWrite.setBounds(40, 100, 250, 20);
      signUpPanel.add(nameWrite);
      nameWrite.setColumns(10);
      
      JLabel emailLabel = new JLabel("메일");
      emailLabel.setBounds(10, 140, 170, 20);
      signUpPanel.add(emailLabel);

      emailWrite = new JTextField();
      emailWrite.setBounds(40, 140, 250, 20);
      signUpPanel.add(emailWrite);
      emailWrite.setColumns(10);
      
      JLabel numberLabel = new JLabel("번호");
      numberLabel.setBounds(10, 180, 170, 20);
      signUpPanel.add(numberLabel);

      numberWrite = new JTextField();
      numberWrite.setBounds(40, 180, 250, 20);
      signUpPanel.add(numberWrite);
      numberWrite.setColumns(10);
      
      String[] temp = {"학부","대학원","교직원"};
      
      JLabel comboLabel = new JLabel("직책");
      comboLabel.setBounds(10, 220, 170, 20);
      signUpPanel.add(comboLabel);
      
      Combo = new JComboBox(temp);
      Combo.setBounds(40, 220, 250, 20);
      signUpPanel.add(Combo);
      
      submitButton = new JButton("제출");
      submitButton.setBounds(40, 260, 90, 20);
      submitButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
            String id = idWrite.getText();
            String pw = pwWrite.getText();
            String name = nameWrite.getText();
            String email = emailWrite.getText();
            String code = codeWrite.getText();
            String number = numberWrite.getText();
            String select = Combo.getSelectedItem().toString();
            
         }});
      signUpPanel.add(submitButton);
      
      setVisible(true);
   }

}