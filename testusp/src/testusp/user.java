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
		// �α���
		Statement stmt;
		ResultSet res;
		Connection conn = null;
		String[] user = { "0", "0" };

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";

		String query = "select * from ȸ�� where ȸ��.ID =" + ID + ";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {
				if (res.getInt("ID") == ID && res.getInt("password") == password) {

					user[0] = Integer.toString(res.getInt("����"));
					user[1] = res.getString("����");
					return user;
				} else {
					System.out.println("�α��ν���");

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

		// ȸ������

		PreparedStatement stmt;
		int res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String query = "";

		query = "update ȸ�� set ȸ��.ID= "+ID+", ȸ��.����='"+name+"',ȸ��.�̸���='"+email+"',ȸ��.��ȭ��ȣ="+phonenum+",ȸ��.����="+type+",ȸ��.password="+password+"";

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
		// ȸ������
		PreparedStatement  stmt;
		int res;
		Connection conn = null;
		String[] user = {"0","0","0","0","0","0"};
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";

		String query = "INSERT INTO ȸ��  values ("+ID+",'"+name+"','"+email+"',"+phonenum+","+type+","+password+")";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "ȸ�����ԿϷ�");
	}
	public static void secession(int ID,int password) {
		// ȸ��Ż��
		PreparedStatement  stmt;
		int res;
		Connection conn = null;
		
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";

		String query = "delete from ȸ�� where ID like '"+ID+"'";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.prepareStatement(query);
			res = stmt.executeUpdate();

		

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "ȸ��Ż��Ϸ�");
		
	}
	public static String[][] bookinfoname(String str) {
		// å�̸����� �˻�
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		String[][] bookarray = new String[9][3];
		int count = 0;
		String query = "select * from ����  where ����.����  like \"%" + str + "%\";";
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

			stmt = conn.createStatement();
			res = stmt.executeQuery(query);

			while (res.next()) {

				//if(res.getString(columnIndex))//
				bookarray[count][0] = Integer.toString(res.getInt("ISBN"));
				bookarray[count][1] = res.getString("����");
				bookarray[count][2] = Integer.toString(res.getInt("����"));
				count++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}
	public static String[][] bookinfoISBN(int ISBN) {
		// ISBN���� å�˻�
		Statement stmt;
		ResultSet res;
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_teamproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1234";
		int count =0;
		String[][] bookarray = new String[9][3];

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
					bookarray[count][2] = Integer.toString(res.getInt("����"));
					count++;
					return bookarray;
				} else {
					JOptionPane.showMessageDialog(null, "�ش� ��ȣ�� å�� �����ϴ�");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookarray;
	}

}
