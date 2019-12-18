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
		// 책이름으로 검색
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[20][5];
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
				
				bookarray[count][0] = res.getString("제목");
				bookarray[count][1] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][2] = res.getString("저자");
				bookarray[count][3] = res.getString("출판사");
				bookarray[count][4] = Integer.toString(res.getInt("수량"));
				count++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

	public static String[][] managerbookinfoISBN(int ISBN) {
		// ISBN으로 책검색
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[20][5];
		int count = 0;

		String query = "select * from 도서 where 도서.ISBN =" + ISBN + ";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {
				if (res.getInt("ISBN") == ISBN) {
					System.out.println("");
					bookarray[count][0] = Integer.toString(res.getInt("ISBN"));
					bookarray[count][1] = res.getString("제목");
					bookarray[count][2] = res.getString("저자");
					bookarray[count][3] = res.getString("출판사");
					bookarray[count][4] = Integer.toString(res.getInt("수량"));
					count++;

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

	public static void book_delete(int ISBN) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// 관리자 도서 삭제

		PreparedStatement stmt;
		int res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "delete from 도서  where ISBN = " + ISBN + "";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "도서 삭제완료 ");
	}

	public static void book_update(int ISBN, String bookname, String author, String publisher, int num) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// 관리자 도서정보 수정

		PreparedStatement stmt;
		int res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "update 도서  set 도서.제목 ='" + bookname + "', 도서.저자 = '" + author + "', 도서.출판사 ='" + publisher
				+ "', 도서.수량 =" + num + " where ISBN = " + ISBN + "";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "도서 " + bookname + " 추가완료");
	}

	public static void add_book(int ISBN, String bookname, String author, String publisher, int num) {
		// 도서추가 - 관리자용
		PreparedStatement stmt;
		int res;
		Connection conn = null;
		String[] user = { "0", "0" };

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";

		String query = "INSERT INTO 도서  values (" + ISBN + ",'" + bookname + "','" + author + "','" + publisher + "',"
				+ num + ")";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

			System.out.println("변경된 row : " + res);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String[][] managerreturnsearch() {
		// 책이름으로 검색
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[20][4];
		int count = 0;
		String query = "select * from 대여 where (대여.반납일자 <> 0 && 대여.반납승인 = 0);";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

//				System.out.println(Integer.toString(res.getInt("ISBN")) + " " + res.getString("제목")
//						+ " " +Integer.toString(res.getInt("수량")));
				bookarray[count][0] = Integer.toString(res.getInt("ID"));
				bookarray[count][1] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][2] = res.getString("반납일자");
				bookarray[count][3] = res.getString("반납만료일자");
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

		// 반납승인

		PreparedStatement stmt;
		int res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "update 대여 set 대여.반납승인 = 1 where 대여.ISBN = " + ISBN + "&& 대여.반납일자<>'0'";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

			System.out.println("대여 완료");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void plusbooknum(int ISBN) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// 반납승인후 책권수 늘리기

		PreparedStatement stmt;

		int res;

		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "update 도서 set 도서.수량 = 도서.수량+1 where 도서.ISBN = " + ISBN + "";

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
