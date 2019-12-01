package testusp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class select {
	Statement stmt;
	ResultSet res;
	Connection conn = null;

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String id = "root";
	String pw = "1234";
	Scanner sc = new Scanner(System.in);

	String table = sc.nextLine();
	String query = "select *from " + table + ";";

	{

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {
				System.out.println(res.getString("ISBN") + " " + res.getString("ÀúÀÚ"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
