package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.swing.JOptionPane;

import java.text.*;

public class borrow {

	private static int borrownum = 10000;

	public static void borrow(int ISBN, int ID, int count, int grade) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// �뿩
		if (count > 0) {
			PreparedStatement stmt;
			PreparedStatement stmt2;
			int res;
			int res2;
			Connection conn = null;
			Connection conn2 = null;

			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String id = "root";
			String pw = "1234";
			String query = "";
			String query2 = "";
			if (grade == 1) {
				query = "INSERT INTO �뿩 values (" + (ID + borrownum) + "," + ISBN + "," + ID + ",'" + dTime + "','" + "0"
						+ "','" + getDate(10) + "','" + "0" + "')";
				query2 ="update ���� set ����.���� ="+(count-1)+" where ����.ISBN = "+ISBN+"";
			
			} else if (grade == 2) {
				query = "INSERT INTO �뿩 values (" + (ID + borrownum) + 1 + "," + ISBN + "," + ID + ",'" + dTime + "','" + "0"
						+ "','" + getDate(30) + "','" + "0" + "')";
				query2 ="update ���� set ����.���� ="+(count-1)+" where ����.ISBN = "+ISBN+"";
				
			} else if (grade == 3) {
				query = "INSERT INTO �뿩 values (" + (ID + borrownum) + 1 + "," + ISBN + "," + ID + ",'" + dTime + "','" + "0"
						+ "','" + getDate(60) + "','" + "0" + "')";
				query2 ="update ���� set ����.���� ="+(count-1)+" where ����.ISBN = "+ISBN+"";

			}else {
				JOptionPane.showMessageDialog(null, "������ �����Դϴ�.");
			}

			try {
				Class.forName(driver);

				conn = DriverManager.getConnection(url, id, pw);
				conn2 = DriverManager.getConnection(url, id, pw);

				stmt = conn.prepareStatement(query);
				stmt2 = conn2.prepareStatement(query2);
				
				
				res = stmt.executeUpdate();
				res2 = stmt2.executeUpdate();
				borrownum++;

				JOptionPane.showMessageDialog(null, "�뿩 �Ϸ�");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "����å�̾��� �������� �ؾ��մϴ�.");
		}
	}

	public static void userbookreturnrequset(int ID, int ISBN) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// �ݳ���û

		PreparedStatement stmt;
		int res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "update �뿩 set �뿩.�ݳ�����='" + dTime + "' where �뿩.ISBN = " + ISBN + "&& �뿩.�ݳ�����='0'";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();
			borrownum++;

			System.out.println("�뿩 �Ϸ�");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static String getDate(int iDay) {
		Calendar temp = Calendar.getInstance();
		StringBuffer sbDate = new StringBuffer();

		temp.add(Calendar.DAY_OF_MONTH, iDay);

		int nYear = temp.get(Calendar.YEAR);
		int nMonth = temp.get(Calendar.MONTH) + 1;
		int nDay = temp.get(Calendar.DAY_OF_MONTH);

		sbDate.append(nYear);
		if (nMonth < 10)
			sbDate.append("0");
		sbDate.append(nMonth);
		if (nDay < 10)
			sbDate.append("0");
		sbDate.append(nDay);

		return sbDate.toString();
	}

	public static String[][] userbooklist(int ID) {
		// ȸ���뿩���
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[9][4];
		int count = 0;
		String query = "select * from (�뿩 natural join ����)  where �뿩.ID  = " + ID + "&& �뿩.�ݳ�����=0 && �뿩.�ݳ�����=0";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

				bookarray[count][0] = res.getString("����");
				bookarray[count][1] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][2] = res.getString("��������");
				bookarray[count][3] = res.getString("�ݳ���������");

				count++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

}
