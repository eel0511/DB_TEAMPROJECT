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

public class ApproveUI extends JFrame {

	JButton searchButtion;
	JButton apporveButton;
	JButton exitButton;

	JPanel contentPanel;
	JPanel signUpPanel;
	
	JLabel booklistLabel;

	static JTable table;

	private DefaultTableModel model = new DefaultTableModel();
	String bookData[][] = new String[19][4];

	ApproveUI() {
		setTitle("DB TeamProject");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 400, 400);

		contentPanel = new JPanel();
		((JComponent) contentPanel).setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		signUpPanel = new JPanel();
		signUpPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "반납신청 목록",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		signUpPanel.setBounds(10, 10, 360, 330);
		signUpPanel.setLayout(null);

		booklistLabel = new JLabel("도서명                ISBN                 반납일자            반납만료일자");
		booklistLabel.setBounds(10, 20, 350, 20);
		signUpPanel.add(booklistLabel);

		table = new JTable(model);
		table.setBounds(10, 40, 340, 255);

		String[] colName = { "ID", "도서명", "ISBN", "반납 신청일자" };

		model.setDataVector(bookData, colName);

		signUpPanel.add(table);

		searchButtion = new JButton("조회");
		searchButtion.setBounds(10, 300, 90, 20);
		searchButtion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				bookData = manager.managerreturnsearch();

				for (int i = 0; i < bookData.length; i++) {
					table.setValueAt(bookData[i][0], i, 0);
					table.setValueAt(bookData[i][1], i, 1);
					table.setValueAt(bookData[i][2], i, 2);
					table.setValueAt(bookData[i][3], i, 3);
				}
			}
		});

		apporveButton = new JButton("승인");
		apporveButton.setBounds(100, 300, 90, 20);
		apporveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				int valISBN = Integer.parseInt((String) table.getValueAt(row, 1));
				int id = Integer.parseInt((String) table.getValueAt(row, 1));

				manager.managerbookreturnrequset(id, valISBN);
				manager.plusbooknum(valISBN);
				bookData = manager.managerreturnsearch();
				for (int i = 0; i < bookData.length; i++) {
					table.setValueAt(bookData[i][0], i, 0);
					table.setValueAt(bookData[i][1], i, 1);
					table.setValueAt(bookData[i][2], i, 2);
					table.setValueAt(bookData[i][3], i, 3);
				}
			}
		});

		exitButton = new JButton("나가기");
		exitButton.setBounds(190, 300, 90, 20);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		signUpPanel.add(apporveButton);
		signUpPanel.add(searchButtion);
		signUpPanel.add(exitButton);
		contentPanel.add(signUpPanel);

		setVisible(true);
	}
}
