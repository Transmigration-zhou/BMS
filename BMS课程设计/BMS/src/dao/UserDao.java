package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * <p>Title: UserDao</p>
 * <p>Description: </p>
 * @author Transmirgration_zhou
 * @date 2020��6��29��
 */
public class UserDao {
	/**
	 * 	������Ϊ�ؼ��ֲ�ѯ
	 * @param con
	 * @param book
	 * @throws Exception
	 */
	public List<User> selectUserName(Connection con, String name) throws Exception {
		Statement stmt = con.createStatement();
		String sql = "select ID,����,�绰,���֤,�ѽ�������,�鼮ID from ������Ϣ where ����='" + name + "'";
		ResultSet rs = stmt.executeQuery(sql);
		List<User> userList = new ArrayList<User>();
		User user = null;
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("ID"));
			user.setUserName(rs.getString("����"));
			user.setPhone(rs.getString("�绰"));
			user.setIdentity(rs.getString("���֤"));
			user.setNumber(rs.getInt("�ѽ�������"));
			user.setBook(rs.getString("�鼮ID"));
			userList.add(user);
		}
		return userList;
	}

	/**
	 * ��IDΪ�ؼ��ֲ�ѯ
	 * 
	 * @param con
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<User> selectUserID(Connection con, int id) throws Exception {
		Statement stmt = con.createStatement();
		String sql = "select ID,����,�绰,���֤,�ѽ�������,�鼮ID from ������Ϣ where ID=" + id;
		ResultSet rs = stmt.executeQuery(sql);
		List<User> userList = new ArrayList<User>();
		User user = null;
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("ID"));
			user.setUserName(rs.getString("����"));
			user.setPhone(rs.getString("�绰"));
			user.setIdentity(rs.getString("���֤"));
			user.setNumber(rs.getInt("�ѽ�������"));
			user.setBook(rs.getString("�鼮ID"));
			userList.add(user);
		}
		return userList;
	}

	public List<User> selectUser(Connection con, String name, int id) throws Exception {
		Statement stmt = con.createStatement();
		String sql = "select ID,����,�绰,���֤,�ѽ�������,�鼮ID from ������Ϣ where ID=" + id + " and ����='" + name + "'";
		ResultSet rs = stmt.executeQuery(sql);
		List<User> userList = new ArrayList<User>();
		User user = null;
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("ID"));
			user.setUserName(rs.getString("����"));
			user.setPhone(rs.getString("�绰"));
			user.setIdentity(rs.getString("���֤"));
			user.setNumber(rs.getInt("�ѽ�������"));
			user.setBook(rs.getString("�鼮ID"));
			userList.add(user);
		}
		return userList;
	}

}
