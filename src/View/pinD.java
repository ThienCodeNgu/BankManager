package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import ConnectDB.Connect;

public class pinD extends JFrame {
	private String stk, mk;
	private JPasswordField pin;
public pinD(String stk, String mk) {
	 
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setSize(333, 230);
    this.setLayout(null);
    
    this.stk = stk;
   this.mk = mk;
    JLabel lblNewLabel = new JLabel("NHẬP MÃ PIN");
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
	lblNewLabel.setBounds(0, 10, 309, 31);
	this.add(lblNewLabel);
	
	pin = new JPasswordField();
	pin.setBounds(29, 62, 267, 31);
	this.add(pin);
	
	JButton ok = new JButton("Ok");
	ok.addActionListener(new ActionListener() {
		
		private Connect connect;

		public void actionPerformed(ActionEvent e) {
			try {
				if (pin.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã PIN để tiếp tục");
				}else if (pin.getText()!="") {
					System.out.println(pin.getText());
				connect = new Connect();
				int checkPin = connect.checkPIN(stk, pin.getText());
				if (checkPin == 1 || checkPin == 0) {
				int doi = connect.doiMK(stk,mk);
				if (doi == 1) {
					JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
					dispose();
				}
				else if (doi == 0) {
					JOptionPane.showMessageDialog(null, "Đổi mật khẩu không thành công");
				}
				} else  {
					JOptionPane.showMessageDialog(null, "Mã PIN không chính xác");
				}
				}
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	ok.setBounds(66, 117, 85, 21);
	this.add(ok);
	
	JButton thoat = new JButton("Thoát");
	thoat.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();		
		}
	});
	thoat.setBounds(161, 117, 85, 21);
	this.add(thoat);
    
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setVisible(true);
}
}
