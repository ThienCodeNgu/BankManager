package View;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ConnectDB.Connect;

public class login extends JFrame{
	public JTextField stk;
	public JPasswordField mk;
	public login() {
		this.setTitle("Quản lí ngân hàng");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(550, 230);
		JPanel bac = new JPanel();
		JLabel title = new JLabel("ĐĂNG NHẬP");
		title.setFont(new Font("Arial",Font.BOLD,25));
		title.setForeground(Color.red);
		bac.add(title);
		
		JLabel STK = new JLabel("Số tài khoản:");
		stk = new JTextField(20);
		JLabel MK = new JLabel("Mật khẩu:");
		mk = new JPasswordField(20);
		
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(2, 2));
		pn.add(STK);
		pn.add(stk);
		pn.add(MK);
		pn.add(mk);
		JButton login = new JButton("Đăng Nhập");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangnhap();
			}
		});
		JLabel anh = new JLabel();
		anh.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(login.class.getResource("login.png"))));
		
		this.setLayout(new BorderLayout());
		JPanel chung = new JPanel();
		chung.setLayout(new FlowLayout());
		chung.add(pn);
		chung.add(login);
		
		this.add(bac, BorderLayout.NORTH);
		this.add(chung, BorderLayout.CENTER);
		this.add(anh, BorderLayout.WEST);
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
public static void main(String[] args) {
	new login();
}
public void dangnhap () {
	Connect con;
	try {
		con = new Connect();
		int kq = con.login(stk.getText(), mk.getText());
		int lad = con.loginAD(stk.getText(), mk.getText());
		if (kq == 1) {
			JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
			new Customer(stk.getText());
			dispose();
		} else if (lad ==1) {
			JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
			new ADMIN(stk.getText());
			dispose();
			}
		else if (kq == 0 ) {
			JOptionPane.showMessageDialog(null, "Thông tin đăng nhập không chính xác");
		}
		
		else if (lad==0) {
			JOptionPane.showMessageDialog(null, "Thông tin đăng nhập không chính xác");
		}
		System.out.println(lad);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}

}
