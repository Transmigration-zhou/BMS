package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import dao.BookDao;
import model.Book;
import turn.Add;
import turn.Borrow;
import turn.Delete;
import turn.FindUser;
import turn.Modify;
import turn.Return;
import util.CreatecdIcon;
import util.DBUtil;

/**
 * <p>Title: FindBookWindow</p>
 * <p>Description: �����鼮����</p>
 * @author Transmirgration_zhou
 * @date 2020��6��29��
 */
@SuppressWarnings("serial")
public class FindBookWindow extends JFrame {
	JMenuBar menubar;
	JMenu menu, Borrow, Information, Find;
	JMenuItem Borrow2, back, add1, delete, modify, find_Book, find_User;
	JLabel find, name1, author1;
	JTextField name2, author2;
	JButton find2;
	JTable table;
	Object[][] a = new Object[15][7];
	Object name[] = { "ID", "ͼ������", "����", "������", "����", "ISBN", "�Ƿ񱻽���" };

	public FindBookWindow() {
		init();
		setVisible(true);
		setBounds(200, 100, 1200, 700);
		setTitle("ͼ�����ϵͳ-�����鼮");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@SuppressWarnings("deprecation")
	void init() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		menubar = new JMenuBar();// �˵���

		Borrow = new JMenu("ͼ�����");
		Information = new JMenu();
		Find = new JMenu("����");

		ImageIcon logo1 = CreatecdIcon.add("res/borrow.png");
		Borrow.setIcon(logo1);
		Borrow.setPreferredSize(new Dimension(90, 25));
		Borrow.setText("ͼ�����");

		ImageIcon logo2 = CreatecdIcon.add("res/BookInformation.png");
		Information.setIcon(logo2);
		Information.setPreferredSize(new Dimension(90, 25));
		Information.setText("ͼ����Ϣ");

		ImageIcon logo3 = CreatecdIcon.add("res/find.png");
		Find.setIcon(logo3);
		Find.setPreferredSize(new Dimension(90, 25));
		Find.setText("����");

		Borrow2 = new JMenuItem("������Ϣ");
		back = new JMenuItem("�黹ͼ��");

		add1 = new JMenuItem("����ͼ��");
		delete = new JMenuItem("ɾ��ͼ��");
		modify = new JMenuItem("�޸�ͼ��");

		find_Book = new JMenuItem("�����鼮");
		find_User = new JMenuItem("�����û�");

		Borrow2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
		back.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK));
		add1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_MASK));
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		modify.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_MASK));
		find_Book.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, InputEvent.CTRL_MASK));
		find_User.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, InputEvent.CTRL_MASK));

		Borrow.add(Borrow2);
		Borrow.addSeparator();
		Borrow.add(back);

		Information.add(add1);
		Information.add(delete);
		Information.add(modify);

		Find.add(find_Book);
		Find.add(find_User);

		menubar.add(Borrow);
		menubar.add(Information);
		menubar.add(Find);
		setJMenuBar(menubar);

		table = new JTable(a, name);
		table.setForeground(Color.BLACK); // ������ɫ
		table.setFont(new Font(null, Font.PLAIN, 14));
		table.setSelectionForeground(Color.DARK_GRAY); // ѡ�к�������ɫ
		table.setSelectionBackground(Color.LIGHT_GRAY); // ѡ�к����屳��
		table.setGridColor(Color.GRAY); // ������ɫ
		table.getTableHeader().setFont(new Font(null, Font.BOLD, 15)); // ��ͷ����������ʽ
		table.getTableHeader().setForeground(Color.BLUE); // ��ͷ����������ɫ
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.setPreferredScrollableViewportSize(new Dimension(450, 300));
		table.setBounds(50, 50, 1100, 400);
		JScrollPane JSP = new JScrollPane(table);
		panel.add(JSP);
		JSP.setBounds(100, 10, 1000, 400);

		find = new JLabel("�����鼮:");
		name1 = new JLabel("����:");
		name2 = new JTextField();
		author1 = new JLabel("����:");
		author2 = new JTextField();
		find2 = new JButton("��ѯ");
		this.getRootPane().setDefaultButton(find2);
		panel.add(find);
		panel.add(name1);
		panel.add(author1);
		panel.add(name2);
		panel.add(author2);
		panel.add(find2);

		find.setBounds(50, 500, 70, 50);
		name1.setBounds(340, 500, 50, 50);
		author1.setBounds(780, 500, 50, 50);
		name2.setBounds(375, 510, 100, 30);
		author2.setBounds(815, 510, 100, 30);
		find2.setBounds(550, 560, 110, 40);
		FindBook FindBook = new FindBook();
		FindBook.setView(this);
		find2.addActionListener(FindBook);
		getContentPane().add(panel);

		Borrow turn1 = new Borrow();
		turn1.setView(this);
		Borrow2.addActionListener(turn1);
		Return turn2 = new Return();
		turn2.setView(this);
		back.addActionListener(turn2);
		Add turn3 = new Add();
		turn3.setView(this);
		add1.addActionListener(turn3);
		Delete turn4 = new Delete();
		turn4.setView(this);
		delete.addActionListener(turn4);
		Modify turn5 = new Modify();
		turn5.setView(this);
		modify.addActionListener(turn5);
		FindBook turn6 = new FindBook();
		turn6.setView(this);
		find_Book.addActionListener(turn6);
		FindUser turn7 = new FindUser();
		turn7.setView(this);
		find_User.addActionListener(turn7);

	}

	private class FindBook implements ActionListener {
		FindBookWindow view;

		public void setView(FindBookWindow view) {
			this.view = view;
		}

		public void actionPerformed(ActionEvent e) {
			String BookName = view.name2.getText();
			String Author = view.author2.getText();
			BookDao bookDao = new BookDao();
			/**
			 * 	����б�
			 */
			for (int i = 0; i < table.getRowCount(); i++) {
				a[i][0] = "";
				a[i][1] = "";
				a[i][2] = "";
				a[i][3] = "";
				a[i][4] = "";
				a[i][5] = "";
				a[i][6] = "";
			}
			try {
				if (BookName.equals("") && Author.equals("")) {
					JOptionPane.showMessageDialog(null, "�����������Ϣ��");
				} else if (BookName.equals("")) {
					Connection con = DBUtil.getCon();
					List<Book> booklist = bookDao.selectAuthor(con, Author);
					if (booklist.size() == 0) {
						JOptionPane.showMessageDialog(null, "���Ҳ��������Ϣ��");
					} else {
						for (int i = 0; i < booklist.size(); i++) {
							Book book = booklist.get(i);
							a[i][0] = book.getBID();
							a[i][1] = book.getBname();
							a[i][2] = book.getBauthor();
							a[i][3] = book.getBpublisher();
							a[i][4] = book.getBprice();
							a[i][5] = book.getBISBN();
							a[i][6] = book.getBcheck();
						}
					}
				} else if (Author.equals("")) {
					Connection con = DBUtil.getCon();
					List<Book> booklist = bookDao.selectBookName(con, BookName);
					if (booklist.size() == 0) {
						JOptionPane.showMessageDialog(null, "���Ҳ��������Ϣ��");
					} else {
						for (int i = 0; i < booklist.size(); i++) {
							Book book = booklist.get(i);
							a[i][0] = book.getBID();
							a[i][1] = book.getBname();
							a[i][2] = book.getBauthor();
							a[i][3] = book.getBpublisher();
							a[i][4] = book.getBprice();
							a[i][5] = book.getBISBN();
							a[i][6] = book.getBcheck();
						}
					}
				} else {
					Connection con = DBUtil.getCon();
					List<Book> booklist = bookDao.select(con, BookName, Author);
					if (booklist.size() == 0) {
						JOptionPane.showMessageDialog(null, "���Ҳ��������Ϣ��");
					} else {
						for (int i = 0; i < booklist.size(); i++) {
							Book book = booklist.get(i);
							a[i][0] = book.getBID();
							a[i][1] = book.getBname();
							a[i][2] = book.getBauthor();
							a[i][3] = book.getBpublisher();
							a[i][4] = book.getBprice();
							a[i][5] = book.getBISBN();
							a[i][6] = book.getBcheck();
						}
					}
				}
				view.repaint();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
				e1.printStackTrace();
			}
		}
	}
}