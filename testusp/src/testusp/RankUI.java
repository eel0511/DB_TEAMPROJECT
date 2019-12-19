package testusp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class RankUI extends JFrame  {
	
	JButton searchButtion;
	JButton exitButton;
	
	JPanel contentPanel;
	JPanel RankPanel;
	JTextField srcField;
	JTextField dstField;
	
	static JTable table;
	JLabel booklistLabel;
	
	private DefaultTableModel model = new DefaultTableModel();

	RankUI() {
		setTitle("DB TeamProject");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 400, 400);

		contentPanel = new JPanel();
		((JComponent) contentPanel).setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		RankPanel = new JPanel();
		RankPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "대출 TOP 10", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		RankPanel.setBounds(10, 10, 360, 330);
		RankPanel.setLayout(null);

		booklistLabel = new JLabel("아이디                                             대출 수");
		booklistLabel.setBounds(10, 20, 350, 20);
		RankPanel.add(booklistLabel);
		
		table = new JTable(model);
		table.setBounds(10, 40, 340, 160);
		
		String[] colName = {"ID","대출수"};
		String bookData[][] = new String[10][2];
		model.setDataVector(bookData, colName);
		
		RankPanel.add(table);
		
		srcField = new JTextField();
		srcField.setBounds(10, 270, 120, 20);
		srcField.setColumns(10);
		RankPanel.add(srcField);
		
		dstField = new JTextField();
		dstField.setBounds(160, 270, 120, 20);
		dstField.setColumns(10);
		RankPanel.add(dstField);
		
		
		
		searchButtion = new JButton("조회");
		searchButtion.setBounds(10, 300, 90, 20);
		searchButtion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				int src = Integer.parseInt(srcField.getText());
				int dst = Integer.parseInt(dstField.getText());
				
				for (int i = 0; i < bookData.length; i++) {
					table.setValueAt(bookData[i][0], i, 0);
					table.setValueAt(bookData[i][1], i, 1);
				}
			}});
		
		exitButton = new JButton("나가기");
		exitButton.setBounds(190, 300, 90, 20);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}});
		
		
		RankPanel.add(searchButtion);
		RankPanel.add(exitButton);
		contentPanel.add(RankPanel);
		
		setVisible(true);
	}
}
