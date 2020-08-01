package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import dao.BookDao;
import dao.BorrowReturnDao;
import model.Book;
import model.User;
import turn.Add;
import turn.Delete;
import turn.FindBook;
import turn.FindUser;
import turn.Modify;
import turn.Return;
import util.CreatecdIcon;
import util.DBUtil;

/**
 * <p>Title: BorrowWindow</p>
 * <p>Description: ���鴰��</p>
 * @author Transmirgration_zhou
 * @date 2020��6��26��
 */
@SuppressWarnings("serial")
public class BorrowWindow extends JFrame {
	JMenuBar menubar;
	JMenu menu, Borrow, Information, Find;
	JMenuItem Borrow2, back, add1, delete, modify, find_Book, find_User;
	JLabel Book1, User1;
	JTextField Book2, User2;
	JButton sure;
	Box baseBox, boxv1, boxv2;

	public BorrowWindow() {
		init();
		setVisible(true);
		setBounds(600, 300, 500, 150);
		setTitle("ͼ�����ϵͳ-ͼ�����");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@SuppressWarnings({ "deprecation" })
	void init() {
		setLayout(new FlowLayout());

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

		/**
		 * �������������ݿ⣬��ʾ�鼮�����
		 */
		Book1 = new JLabel("ͼ��ID  ");
		Book2 = new JTextField(10);
		add(Book1);
		add(Book2);

		User1 = new JLabel("������ID  ");
		User2 = new JTextField(10);
		add(User1);
		add(User2);

		sure = new JButton("ȷ��");
		Borrow borrow = new Borrow();
		borrow.setView(this);
		sure.addActionListener(borrow);
		this.getRootPane().setDefaultButton(sure);
		add(sure);

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
	
	private class Borrow implements ActionListener {
		BorrowWindow view;

		public void setView(BorrowWindow view) {
			this.view = view;
		}

		public void actionPerformed(ActionEvent e) {
			String book = view.Book2.getText();
			String user = view.User2.getText();
			BookDao bookDao = new BookDao();
			BorrowReturnDao Dao = new BorrowReturnDao();
			try {
				if (book.equals("") || user.equals("") ) {
					JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ���");
				} else {
					Connection con = DBUtil.getCon();
					List<Book> booklist = bookDao.selectID(con, book);
					Book book0 = booklist.get(0);
					if(book0.getBcheck()==true) {
						JOptionPane.showMessageDialog(null, "�����ѱ����ģ�");
					}
					else {
						Book book1 = new Book(Integer.parseInt(book), true);
						User user1 = new User(Integer.parseInt(user), 1 , book);
						Dao.borrow(con, book1, user1);
						JOptionPane.showMessageDialog(null, "����ɹ���");
					}
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
				e1.printStackTrace();
			}
		}
	}
}