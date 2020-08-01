package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Book;

/**
 * 
 * <p>Title: BookDao</p>
 * <p>Description: </p>
 * @author Transmirgration_zhou
 * @date 2020��6��29��
 */
public class BookDao {
	/**
	 * ͼ�����
	 * 
	 * @param con
	 * @param book
	 * @throws Exception
	 */
	public void insert(Connection con, Book book) throws Exception {
		String sql = "insert into ͼ����Ϣ values(?,?,?,?,?,?,?)";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setInt(1, book.getBID());
		psmt.setString(2, book.getBname());
		psmt.setString(3, book.getBauthor());
		psmt.setString(4, book.getBpublisher());
		psmt.setDouble(5, book.getBprice());
		psmt.setString(6, book.getBISBN());
		psmt.setBoolean(7, book.getBcheck());
		psmt.execute();
	}

	/**
	 * ͼ��ɾ��
	 * 
	 * @param con
	 * @param ID
	 * @throws Exception
	 */
	public void delete(Connection con, int ID) throws Exception {
		String sql = "delete from ͼ����Ϣ where ID=?";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setInt(1, ID);
		psmt.execute();
	}

	/**
	 * ͼ���޸�
	 * 
	 * @param con
	 * @param book
	 * @throws Exception
	 */
	public void update(Connection con, Book book) throws Exception {
		String sql = "update ͼ����Ϣ set ͼ������=?,����=?,������=?,����=?,ISBN=? where ID=?";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1, book.getBname());
		psmt.setString(2, book.getBauthor());
		psmt.setString(3, book.getBpublisher());
		psmt.setDouble(4, book.getBprice());
		psmt.setString(5, book.getBISBN());
		psmt.setInt(6, book.getBID());
		psmt.execute();
	}

	/**
	 * ��ͼ������Ϊ�ؼ��ֲ�ѯ
	 * 
	 * @param con
	 * @param book
	 * @throws Exception
	 */
	public List<Book> selectBookName(Connection con, String name) throws Exception {
		Statement stmt = con.createStatement();
		String sql = "select ID,ͼ������,����,������,����,ISBN,�Ƿ񱻽���  from ͼ����Ϣ where ͼ������='" + name + "'";
		ResultSet rs = stmt.executeQuery(sql);
		List<Book> bookList = new ArrayList<Book>();
		Book book = null;
		while (rs.next()) {
			book = new Book();
			book.setBID(rs.getInt("ID"));
			book.setBname(rs.getString("ͼ������"));
			book.setBauthor(rs.getString("����"));
			book.setBpublisher(rs.getString("������"));
			book.setBprice(rs.getDouble("����"));
			book.setBISBN(rs.getString("ISBN"));
			book.setBcheck(rs.getBoolean("�Ƿ񱻽���"));
			bookList.add(book);
		}
		return bookList;
	}

	/**
	 * ������Ϊ�ؼ��ֲ�ѯ
	 * 
	 * @param con
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Book> selectAuthor(Connection con, String name) throws Exception {
		Statement stmt = con.createStatement();
		String sql = "select ID, ͼ������, ����, ������, ����, ISBN,�Ƿ񱻽���  from ͼ����Ϣ where ����='" + name + "'";
		ResultSet rs = stmt.executeQuery(sql);
		List<Book> bookList = new ArrayList<Book>();
		Book book = null;
		while (rs.next()) {
			book = new Book();
			book.setBID(rs.getInt("ID"));
			book.setBname(rs.getString("ͼ������"));
			book.setBauthor(rs.getString("����"));
			book.setBpublisher(rs.getString("������"));
			book.setBprice(rs.getDouble("����"));
			book.setBISBN(rs.getString("ISBN"));
			book.setBcheck(rs.getBoolean("�Ƿ񱻽���"));
			bookList.add(book);
		}
		return bookList;
	}

	public List<Book> select(Connection con, String bookname, String author) throws Exception {
		Statement stmt = con.createStatement();
		String sql = "select ID, ͼ������, ����, ������, ����, ISBN,�Ƿ񱻽���  from ͼ����Ϣ where ͼ������='" + bookname + "'and ����=" + author
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		List<Book> bookList = new ArrayList<Book>();
		Book book = null;
		while (rs.next()) {
			book = new Book();
			book.setBID(rs.getInt("ID"));
			book.setBname(rs.getString("ͼ������"));
			book.setBauthor(rs.getString("����"));
			book.setBpublisher(rs.getString("������"));
			book.setBprice(rs.getDouble("����"));
			book.setBISBN(rs.getString("ISBN"));
			book.setBcheck(rs.getBoolean("�Ƿ񱻽���"));
			bookList.add(book);
		}
		return bookList;
	}


	public List<Book> selectID(Connection con, String id) throws Exception {
		Statement stmt = con.createStatement();
		String sql = "select ID, ͼ������, ����, ������, ����, ISBN,�Ƿ񱻽���  from ͼ����Ϣ where ID='" + id + "'";
		ResultSet rs = stmt.executeQuery(sql);
		List<Book> bookList = new ArrayList<Book>();
		Book book = null;
		while (rs.next()) {
			book = new Book();
			book.setBID(rs.getInt("ID"));
			book.setBname(rs.getString("ͼ������"));
			book.setBauthor(rs.getString("����"));
			book.setBpublisher(rs.getString("������"));
			book.setBprice(rs.getDouble("����"));
			book.setBISBN(rs.getString("ISBN"));
			book.setBcheck(rs.getBoolean("�Ƿ񱻽���"));
			bookList.add(book);
		}
		return bookList;
	}
}
