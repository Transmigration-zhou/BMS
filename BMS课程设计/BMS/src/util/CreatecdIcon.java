package util;

import java.net.URL;

import javax.swing.ImageIcon;

import view.LoginWindow;

/**
 * <p>Title: CreatecdIcon</p>
 * <p>Description: ָ��·������ͼƬ</p>
 * @author Transmirgration_zhou
 * @date 2020��6��8��
 */
public class CreatecdIcon {
	public static ImageIcon add(String ImageName) {
		URL IconUrl = LoginWindow.class.getResource("/"+ImageName);
		ImageIcon icon= new ImageIcon(IconUrl);	
		return icon;
	}
}
