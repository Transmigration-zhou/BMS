package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import dao.BookDao;
import turn.Add;
import turn.Borrow;
import turn.FindBook;
import turn.FindUser;
import turn.Modify;
import turn.Return;
import util.CreatecdIcon;
import util.DBUtil;

/**
 * <p>Title: DeleteWindow</p>
 * <p>Description: ɾ��ͼ��</p>
 * @author Transmirgration_zhou
 * @date 2020��6��26��
 */
@SuppressWarnings("serial")
public class DeleteWindow extends JFrame {
	JMenuBar menubar;
	JMenu menu, Borrow, Information, Find;
	JMenuItem Borrow2, back, add1, delete, modify, find_Book, find_User;
	JLabel ID1;
	JTextField ID2;
	JButton delete2;

	public DeleteWindow() {
		init();
		setVisible(true);
		setBounds(400, 150, 300, 200);
		setTitle("ͼ�����ϵͳ-ɾ��ͼ����Ϣ");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@SuppressWarnings("deprecation")
	void init() {
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

		JPanel panel = new JPanel();
		panel.setLayout(null);
		ID1 = new JLabel("ID:");
		ID2 = new JTextField();
		delete2 = new JButton("ɾ��");
		this.getRootPane().setDefaultButton(delete2);
		panel.add(ID1);
		panel.add(ID2);
		panel.add(delete2);
		ID1.setBounds(50, 30, 50, 50);
		ID2.setBounds(100, 40, 100, 25);
		delete2.setBounds(90, 80, 70, 35);
		getContentPane().add(panel);

		Delete Delete2 = new Delete();
		Delete2.setView(this);
		delete2.addActionListener(Delete2);
		
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
	private class Delete implements ActionListener {
		DeleteWindow view;

		public void setView(DeleteWindow view) {
			this.view = view;
		}

		public void actionPerformed(ActionEvent e) {
			String ID = view.ID2.getText();
			BookDao bookDao = new BookDao();
			try {
				if (ID.equals("")) {
					JOptionPane.showMessageDialog(null, "����ͼ��IDΪ�գ�");
				} else {
					Connection con = DBUtil.getCon();
					bookDao.delete(con, Integer.parseInt(ID));
					JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
				e1.printStackTrace();
			}
		}
	}
	
}