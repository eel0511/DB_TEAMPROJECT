package testusp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class manager {
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
}
