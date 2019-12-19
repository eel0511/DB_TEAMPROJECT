package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class reservation {

	public static String[][] reservationinfo(int ID) {
		// 예약현황 검색 ID로
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[9][4];
		int count = 0;
		String query = "select * from (도서 natural join 대여)  join 예약 on (예약.대여번호 = 대여.대여번호) where 예약.ID =" + ID + ";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

				// if(res.getString(columnIndex))//
				bookarray[count][0] = res.getString("제목");
				bookarray[count][1] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][2] = Integer.toString(res.getInt("반납만료일자"));
				bookarray[count][3] = Integer.toString(res.getInt("예약순번"));

				count++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

	public static String[][] reservationinforank(int ISBN) {
		// 예약현황 검색 ISBN 으로 (몇명인지)
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[9][4];
		int count = 0;
		String query = "select * from (도서 natural join 대여)  join 예약 on (예약.대여번호 = 대여.대여번호) where 도서.isbn = " + ISBN
				+ ";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

				// if(res.getString(columnIndex))//
				bookarray[count][0] = res.getString("제목");
				bookarray[count][1] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][2] = Integer.toString(res.getInt("반납만료일자")); // 대출가능일
				bookarray[count][2] = Integer.toString(res.getInt("예약순번"));

				count++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

	public static void delete_reservation(int ID, int ISBN) {
		// 예약취소
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

		String query2 = "select * from 예약 join 대여  on (예약.대여번호 = 대여.대여번호) where (ISBN = " + ISBN + " and 예약.ID = " + ID
				+ ");";

		try {
			Class.forName(driver);

			conn2 = DriverManager.getConnection(url, id, pw);

			stmt2 = conn2.createStatement();
			res2 = stmt2.executeQuery(query2);
			num = res2.getInt("대여번호");
			System.out.println(num);
			System.out.println(ID);
			System.out.println(ISBN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = "delete from 예약 where (예약.ID = " + ID + " and 예약.대여번호 = " + num + ")";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "예약취소");

		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}

	public static void reservation_make(int ID, int ISBN) {
		// 예약
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

		String query2 = "select 대여번호 from 대여 where (대여.ISBN = " + ISBN + " ) order by 반납만료일자 limit 1;";

		try {
			Class.forName(driver);

			conn2 = DriverManager.getConnection(url, id, pw);

			stmt2 = conn2.createStatement();
			res2 = stmt2.executeQuery(query2);
			while (res2.next()) {
				
			num = res2.getInt("대여번호");
			System.out.println(num);
			
			}
			System.out.println("a");

		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = "INSERT INTO 예약  values (" + num + ", " + ID + "," + (++rank) + " );";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "예약");
	}
}
