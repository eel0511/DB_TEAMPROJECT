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

public class user {
	public static String[] login(int ID, int password) {
		// 로그인
		Statement stmt;
		ResultSet res;
		Connection conn = null;
		String[] user = { "0", "0" };

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

					user[0] = Integer.toString(res.getInt("구분"));
					user[1] = res.getString("성명");
					return user;
				} else {
					System.out.println("로그인실패");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public static void userdata_update(int ID, String name,String email,int phonenum,int type,int password) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// 회원수정

		PreparedStatement stmt;
		int res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "update 회원 set 회원.ID= "+ID+", 회원.성명='"+name+"',회원.이메일='"+email+"',회원.전화번호="+phonenum+",회원.구분="+type+",회원.password="+password+"";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	

	public static void sign_up(int ID, String name,String email,int phonenum,int type,int password) {
		// 회원가입
		PreparedStatement  stmt;
		int res;
		Connection conn = null;
		String[] user = {"0","0","0","0","0","0"};
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";

		String query = "INSERT INTO 회원  values ("+ID+",'"+name+"','"+email+"',"+phonenum+","+type+","+password+")";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "회원가입완료");
	}
	public static void secession(int ID,int password) {
		// 회원탈퇴
		PreparedStatement  stmt;
		int res;
		Connection conn = null;
		
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";

		String query = "delete from 회원 where ID like '"+ID+"'";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

		

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "회원탈퇴완료");
		
	}
	public static String[][] bookinfoname(String str) {
		// 책이름으로 검색
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[9][3];
		int count = 0;
		String query = "select * from 도서  where 도서.제목  like \"%" + str + "%\";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

				//if(res.getString(columnIndex))//
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
	public static String[][] bookinfoISBN(int ISBN) {
		// ISBN으로 책검색
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		int count =0;
		String[][] bookarray = new String[9][3];

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
					bookarray[count][2] = Integer.toString(res.getInt("수량"));
					count++;
					return bookarray;
				} else {
					JOptionPane.showMessageDialog(null, "해당 번호의 책은 없습니다");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

}
