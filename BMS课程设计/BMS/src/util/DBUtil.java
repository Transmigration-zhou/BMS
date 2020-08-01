package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <p>Title: DBUtil</p>
 * <p>Description: �������ݿ�</p>
 * @author Transmirgration_zhou
 * @date 2020��6��25��
 */
public class DBUtil {
	private static final String url = "jdbc:mysql://localhost:3306/bms?"
			+ "useUnicode = true & serverTimezone = GMT"
			// MySQL�ڸ߰汾��Ҫָ���Ƿ����SSL����
			+ "& characterEncoding = utf8 & useSSL = false";
	private static String user = "root";
	private static String password = "zhou0322";
	private static String jdbcName = "com.mysql.cj.jdbc.Driver"; // ��������
	private static Connection con = null;

	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	public static Connection getCon() throws Exception {
		Class.forName(jdbcName);
		con = DriverManager.getConnection(url, user, password);
		return con;
	}

	/**
	 * �ر����ݿ�����
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

//	������
//	public static void main(String[] args) {
//		new DBUtil();
//		try {
//			DBUtil.getCon();
//			System.out.println("���ݿ����ӳɹ�");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("���ݿ�����ʧ��");
//		}
//	}
}
