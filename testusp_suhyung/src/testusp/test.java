package testusp;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import testusp.MainUI;

public class test {

	public static boolean login(int ID, int password) {
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";

		String query = "select * from 회원 where 회원.ID =" + ID + ";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {
				if (res.getInt("ID") == ID && res.getInt("password") == password) {
					System.out.println("로그인완료");

					return true;
				} else {
					System.out.println("로그인실패");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String[][] bookinfoISBN(int ISBN) {
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[100][100];

		String query = "select * from 도서 where 도서.ISBN =" + ISBN + ";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {
				if (res.getInt("ISBN") == ISBN) {
					System.out.println("");
					bookarray[0][0] = Integer.toString(res.getInt("ISBN"));
					bookarray[0][1] = res.getString("제목");
					bookarray[0][2] = Integer.toString(res.getInt("수량"));
					return bookarray;
				} else {
					System.out.println("그런책 없음");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

	public static String[][] bookinfoname(String str) {
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[10][3];
		int count = 0;
		String query = "select * from 도서  where 도서.제목  like \"%" + str + "%\";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

//				System.out.println(Integer.toString(res.getInt("ISBN")) + " " + res.getString("제목")
//						+ " " +Integer.toString(res.getInt("수량")));
				bookarray[count][0] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][1] = res.getString("제목");
				bookarray[count][2] = Integer.toString(res.getInt("수량"));
				count++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		

	}

}
