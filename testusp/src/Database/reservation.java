package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class reservation {

	public static String[][] reservationinfo(int ID) {
		// ������Ȳ �˻� ID��
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[9][4];
		int count = 0;
		String query = "select * from (���� natural join �뿩)  join ���� on (����.�뿩��ȣ = �뿩.�뿩��ȣ) where ����.ID =" + ID + ";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

				// if(res.getString(columnIndex))//
				bookarray[count][0] = res.getString("����");
				bookarray[count][1] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][2] = Integer.toString(res.getInt("�ݳ���������"));
				bookarray[count][3] = Integer.toString(res.getInt("�������"));

				count++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

	public static String[][] reservationinforank(int ISBN) {
		// ������Ȳ �˻� ISBN ���� (�������)
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[9][4];
		int count = 0;
		String query = "select * from (���� natural join �뿩)  join ���� on (����.�뿩��ȣ = �뿩.�뿩��ȣ) where ����.isbn = " + ISBN
				+ ";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

				// if(res.getString(columnIndex))//
				bookarray[count][0] = res.getString("����");
				bookarray[count][1] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][2] = Integer.toString(res.getInt("�ݳ���������")); // ���Ⱑ����
				bookarray[count][2] = Integer.toString(res.getInt("�������"));

				count++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

	public static void delete_reservation(int ID, int ISBN) {
		// �������
		PreparedStatement stmt;
		int res;
		Connection conn = null;
		Statement stmt2;
		ResultSet res2;
		Connection conn2 = null;
		int num = 0;

		int rank = 0;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";

		String query2 = "select * from ���� join �뿩  on (����.�뿩��ȣ = �뿩.�뿩��ȣ) where (ISBN = " + ISBN + " and ����.ID = " + ID
				+ ");";

		try {
			Class.forName(driver);

			conn2 = DriverManager.getConnection(url, id, pw);

			stmt2 = conn2.createStatement();
			res2 = stmt2.executeQuery(query2);
			num = res2.getInt("�뿩��ȣ");
			System.out.println(num);
			System.out.println(ID);
			System.out.println(ISBN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = "delete from ���� where (����.ID = " + ID + " and ����.�뿩��ȣ = " + num + ")";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "�������");

		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}

	public static void reservation_make(int ID, int ISBN) {
		// ����
		PreparedStatement stmt;
		int res;
		Connection conn = null;
		Statement stmt2;
		ResultSet res2;
		Connection conn2 = null;
		int num=0;
		int count =0;
		int rank = 0;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";

		String query2 = "select �뿩��ȣ from �뿩 where (�뿩.ISBN = " + ISBN + " ) order by �ݳ��������� limit 1;";

		try {
			Class.forName(driver);

			conn2 = DriverManager.getConnection(url, id, pw);

			stmt2 = conn2.createStatement();
			res2 = stmt2.executeQuery(query2);
			while (res2.next()) {
				
			num = res2.getInt("�뿩��ȣ");
			System.out.println(num);
			
			}
			System.out.println("a");

		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = "INSERT INTO ����  values (" + num + ", " + ID + "," + (++rank) + " );";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "����");
	}
}
