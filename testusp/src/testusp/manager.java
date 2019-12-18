package testusp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class manager {
	public static String[][] managerbookinfoname(String str) {
		// å�̸����� �˻�
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[20][5];
		int count = 0;
		String query = "select * from ����  where ����.����  like \"%" + str + "%\";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

//				System.out.println(Integer.toString(res.getInt("ISBN")) + " " + res.getString("����")
//						+ " " +Integer.toString(res.getInt("����")));
				
				bookarray[count][0] = res.getString("����");
				bookarray[count][1] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][2] = res.getString("����");
				bookarray[count][3] = res.getString("���ǻ�");
				bookarray[count][4] = Integer.toString(res.getInt("����"));
				count++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

	public static String[][] managerbookinfoISBN(int ISBN) {
		// ISBN���� å�˻�
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[20][5];
		int count = 0;

		String query = "select * from ���� where ����.ISBN =" + ISBN + ";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {
				if (res.getInt("ISBN") == ISBN) {
					System.out.println("");
					bookarray[count][0] = Integer.toString(res.getInt("ISBN"));
					bookarray[count][1] = res.getString("����");
					bookarray[count][2] = res.getString("����");
					bookarray[count][3] = res.getString("���ǻ�");
					bookarray[count][4] = Integer.toString(res.getInt("����"));
					count++;

					return bookarray;
				} else {
					System.out.println("�׷�å ����");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

	public static void book_delete(int ISBN) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// ������ ���� ����

		PreparedStatement stmt;
		int res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "delete from ����  where ISBN = " + ISBN + "";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "���� �����Ϸ� ");
	}

	public static void book_update(int ISBN, String bookname, String author, String publisher, int num) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// ������ �������� ����

		PreparedStatement stmt;
		int res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "update ����  set ����.���� ='" + bookname + "', ����.���� = '" + author + "', ����.���ǻ� ='" + publisher
				+ "', ����.���� =" + num + " where ISBN = " + ISBN + "";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "���� " + bookname + " �߰��Ϸ�");
	}

	public static void add_book(int ISBN, String bookname, String author, String publisher, int num) {
		// �����߰� - �����ڿ�
		PreparedStatement stmt;
		int res;
		Connection conn = null;
		String[] user = { "0", "0" };

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";

		String query = "INSERT INTO ����  values (" + ISBN + ",'" + bookname + "','" + author + "','" + publisher + "',"
				+ num + ")";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

			System.out.println("����� row : " + res);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String[][] managerreturnsearch() {
		// å�̸����� �˻�
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[20][4];
		int count = 0;
		String query = "select * from �뿩 where (�뿩.�ݳ����� <> 0 && �뿩.�ݳ����� = 0);";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

//				System.out.println(Integer.toString(res.getInt("ISBN")) + " " + res.getString("����")
//						+ " " +Integer.toString(res.getInt("����")));
				bookarray[count][0] = Integer.toString(res.getInt("ID"));
				bookarray[count][1] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][2] = res.getString("�ݳ�����");
				bookarray[count][3] = res.getString("�ݳ���������");
				count++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

	public static void managerbookreturnrequset(int ID, int ISBN) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// �ݳ�����

		PreparedStatement stmt;
		int res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "update �뿩 set �뿩.�ݳ����� = 1 where �뿩.ISBN = " + ISBN + "&& �뿩.�ݳ�����<>'0'";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

			System.out.println("�뿩 �Ϸ�");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void plusbooknum(int ISBN) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// �ݳ������� å�Ǽ� �ø���

		PreparedStatement stmt;

		int res;

		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "update ���� set ����.���� = ����.����+1 where ����.ISBN = " + ISBN + "";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);

			res = stmt.executeUpdate();

		

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
