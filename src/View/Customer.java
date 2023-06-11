package View;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ConnectDB.Connect;


public class Customer extends JFrame{
	public Connect connect;
	public String AccountNumber;
	public JLabel STK;
	public JLabel so;
	public JLabel hoten;
	public JLabel queQuan;
	public JTextField textField;
	public JLabel lblNewLabel_1_2;
	public JLabel lblNewLabel_1_2_1;
	public JLabel stkR;
	public JLabel soDuR;
	public JTextField textfR;
	private JLabel stkC;
	private JLabel soDuC;
	private JTextField textfC;
	private JTextField MKtextField;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textFieldC;
	
	
public Customer (String AccountNumber) {
	this.setTitle("VT-BANK");
	this.setSize(640,450);
	this.setLocationRelativeTo(null);
	this.AccountNumber = AccountNumber;
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
    JTabbedPane tab = new JTabbedPane();
    
    JPanel pntab1 = new JPanel(new BorderLayout());
    JPanel thongtin = new JPanel();
    thongtin.setLayout(new GridLayout(3,4));
    JLabel stk = new JLabel("STK:");
    STK = new JLabel();
    JLabel soDu = new JLabel("Số dư:");
    so = new JLabel();
    JLabel ten = new JLabel("Họ Tên:");
    hoten = new JLabel();
    JLabel que = new JLabel("Quê Quán:");
    queQuan = new JLabel();
    JButton re = new JButton("refresh");
    re.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			thongtin(AccountNumber);
			int rowCount = model.getRowCount(); 
			for (int i = rowCount - 1; i >= 0; i--) {
			    model.removeRow(i);
			}
			lichSu(AccountNumber);
		}
	});
    JButton out = new JButton("Đăng xuất");
    out.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog( null , 
                    "Do you want to sign out?",
                    "",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				new login();
				connect.close();
				dispose();
			}
			else if ( result == JOptionPane.NO_OPTION) {
				
			}
			
		}
	});
    thongtin.add(stk);
    thongtin.add(STK);
    thongtin.add(soDu);
    thongtin.add(so);
    thongtin.add(ten);
    thongtin.add(hoten);
    thongtin.add(que);
    thongtin.add(queQuan);
    thongtin.add(re);
    thongtin.add(out);
    JLabel label = new JLabel();
	label.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Customer.class.getResource("R2UxRNc.gif"))));
	pntab1.add(thongtin, BorderLayout.NORTH);
    pntab1.add(label, BorderLayout.CENTER);
    
    JPanel pntab2 = new JPanel();
    pntab2.setLayout(null);
    JLabel lblNewLabel = new JLabel("NẠP TIỀN");
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
	lblNewLabel.setBounds(0, 10, 626, 54);
	pntab2.add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("STK:");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_1.setBounds(189, 101, 37, 23);
	pntab2.add(lblNewLabel_1);
	
	JLabel lblNewLabel_1_1 = new JLabel("Số dư:");
	lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_1_1.setBounds(189, 159, 52, 23);
	pntab2.add(lblNewLabel_1_1);
	
	JLabel lblNewLabel_1_1_1 = new JLabel("Số tiền:");
	lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_1_1_1.setBounds(189, 216, 52, 23);
	pntab2.add(lblNewLabel_1_1_1);
	
	lblNewLabel_1_2 = new JLabel();
	lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_1_2.setBounds(292, 101, 172, 23);
	pntab2.add(lblNewLabel_1_2);
	
	lblNewLabel_1_2_1 = new JLabel();
	lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_1_2_1.setBounds(292, 159, 195, 23);
	pntab2.add(lblNewLabel_1_2_1);
	
	textField = new JTextField();
	textField.setBounds(292, 218, 195, 23);
	pntab2.add(textField);
	textField.setColumns(10);
	
	JButton ButtonNap = new JButton("Nạp");
	ButtonNap.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền muốn nạp");
				}
			else if (textField.getText()!=null) {
					connect = new Connect();
					int tien = Integer.parseInt(textField.getText());
					int nap = connect.giaoDich(AccountNumber, "Nạp", tien );
					if (nap == 1) {
						JOptionPane.showMessageDialog(null, "Giao dịch thành công");
						thongtin(AccountNumber);
					}
					else if (nap == 0) {
						JOptionPane.showMessageDialog(null, "Giao dịch thất bại");
					}
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
	ButtonNap.setFont(new Font("Tahoma", Font.PLAIN, 15));
	ButtonNap.setBounds(276, 291, 85, 21);
	pntab2.add(ButtonNap);
    
    JPanel pntab3 = new JPanel();
    pntab3.setLayout(null);
    JLabel titleChuyen = new JLabel("CHUYỂN TIỀN");
    titleChuyen.setHorizontalAlignment(SwingConstants.CENTER);
    titleChuyen.setFont(new Font("Tahoma", Font.PLAIN, 25));
    titleChuyen.setBounds(0, 10, 626, 54);
    pntab3.add(titleChuyen);
	
	JLabel TstkC = new JLabel("STK:");
	TstkC.setFont(new Font("Tahoma", Font.PLAIN, 15));
	TstkC.setBounds(189, 101, 37, 23);
	pntab3.add(TstkC);
	
	JLabel labelSODUc = new JLabel("Số dư:");
	labelSODUc.setFont(new Font("Tahoma", Font.PLAIN, 15));
	labelSODUc.setBounds(189, 159, 52, 23);
	pntab3.add(labelSODUc);
	
	JLabel labelSoTienc = new JLabel("STK nhận:");
	labelSoTienc.setFont(new Font("Tahoma", Font.PLAIN, 15));
	labelSoTienc.setBounds(189, 216, 77, 23);
	pntab3.add(labelSoTienc);
	
	stkC = new JLabel();
	stkC.setFont(new Font("Tahoma", Font.PLAIN, 15));
	stkC.setBounds(292, 101, 172, 23);
	pntab3.add(stkC);
	
	soDuC = new JLabel();
	soDuC.setFont(new Font("Tahoma", Font.PLAIN, 15));
	soDuC.setBounds(292, 159, 195, 23);
	pntab3.add(soDuC);
	
	textfC = new JTextField();
	textfC.setBounds(292, 218, 195, 23);
	pntab3.add(textfC);
	textfC.setColumns(10);
	
	JButton ButtonChuyen = new JButton("Chuyển");
	
	ButtonChuyen.setFont(new Font("Tahoma", Font.PLAIN, 15));
	ButtonChuyen.setBounds(277, 331, 85, 21);
	pntab3.add(ButtonChuyen);
	
	JLabel labelSoTienR_1 = new JLabel("Số tiền:");
	labelSoTienR_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	labelSoTienR_1.setBounds(189, 265, 52, 23);
	pntab3.add(labelSoTienR_1);
	
	textFieldC = new JTextField();
	textFieldC.setColumns(10);
	textFieldC.setBounds(292, 267, 195, 23);
ButtonChuyen.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (textFieldC.getText().equals("")||textfC.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
			}
		else if (textfC.getText()!=null||textFieldC.getText()!=null) {
			int ktra = connect.ktraTonTai(textfC.getText());
			System.out.println(ktra);
			
			if (ktra==1) {
				int soduC = Integer.parseInt(soDuC.getText());
				
				int sotienN = Integer.parseInt(textFieldC.getText());
				int sotienn = soduC-sotienN;
				
				
				
				if (sotienn<=50000) {
					System.out.println(soduC);
					int thaytest = soduC-50000;
					System.out.println(thaytest);
					JOptionPane.showMessageDialog(null, "Bạn chỉ được chuyển tối đa "+thaytest);
				}else {
					int tienC = Integer.parseInt(textFieldC.getText());
					new pinC(AccountNumber,tienC, textfC.getText());
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Số tài khoản nhận không tồn tại");
			}
			
				}
			
		}
	});
	pntab3.add(textFieldC);
	
    JPanel pntab4 = new JPanel();
    pntab4.setLayout(null);
    JLabel titleRut = new JLabel("RÚT TIỀN");
    titleRut.setHorizontalAlignment(SwingConstants.CENTER);
    titleRut.setFont(new Font("Tahoma", Font.PLAIN, 25));
    titleRut.setBounds(0, 10, 626, 54);
	pntab4.add(titleRut);
	
	JLabel TstkR = new JLabel("STK:");
	TstkR.setFont(new Font("Tahoma", Font.PLAIN, 15));
	TstkR.setBounds(189, 101, 37, 23);
	pntab4.add(TstkR);
	
	JLabel labelSODUR = new JLabel("Số dư:");
	labelSODUR.setFont(new Font("Tahoma", Font.PLAIN, 15));
	labelSODUR.setBounds(189, 159, 52, 23);
	pntab4.add(labelSODUR);
	
	JLabel labelSoTienR = new JLabel("Số tiền:");
	labelSoTienR.setFont(new Font("Tahoma", Font.PLAIN, 15));
	labelSoTienR.setBounds(189, 216, 52, 23);
	pntab4.add(labelSoTienR);
	
	stkR = new JLabel();
	stkR.setFont(new Font("Tahoma", Font.PLAIN, 15));
	stkR.setBounds(292, 101, 172, 23);
	pntab4.add(stkR);
	
	soDuR = new JLabel();
	soDuR.setFont(new Font("Tahoma", Font.PLAIN, 15));
	soDuR.setBounds(292, 159, 195, 23);
	pntab4.add(soDuR);
	
	textfR = new JTextField();
	textfR.setBounds(292, 218, 195, 23);
	pntab4.add(textfR);
	textfR.setColumns(10);
	
	JButton ButtonRut = new JButton("Rút");
	ButtonRut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				if (textfR.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền muốn rút");
				}
			else if (textfR.getText()!=null) {
					connect = new Connect();
					
					int tien = Integer.parseInt(textfR.getText());
					new pin(AccountNumber,tien, "Rút");
					
					}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
	ButtonRut.setFont(new Font("Tahoma", Font.PLAIN, 15));
	ButtonRut.setBounds(276, 291, 85, 21);
	pntab4.add(ButtonRut);
    JPanel pntab5 = new JPanel();
    pntab5.setLayout(null);
    JLabel titleDoi = new JLabel("ĐỔI MẬT KHẤU");
    titleDoi.setHorizontalAlignment(SwingConstants.CENTER);
    titleDoi.setFont(new Font("Tahoma", Font.PLAIN, 25));
    titleDoi.setBounds(0, 10, 626, 54);
    pntab5.add(titleDoi);
	
	JButton buttonDoi = new JButton("Đổi");
	
	
	buttonDoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
	buttonDoi.setBounds(265, 173, 85, 21);

	pntab5.add(buttonDoi);
	
	JLabel doimk = new JLabel("Mật khẩu mới:");
	doimk.setFont(new Font("Tahoma", Font.PLAIN, 15));
	doimk.setBounds(169, 124, 113, 27);
	pntab5.add(doimk);
	
	MKtextField = new JTextField();
	MKtextField.setBounds(311, 124, 175, 27);
	pntab5.add(MKtextField);
	MKtextField.setColumns(10);
	
	buttonDoi.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (MKtextField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Nhập mật khẩu mới trước");
			}else {
			new pinD(AccountNumber, MKtextField.getText());
			}
		}
	});
	JLabel anh = new JLabel();
	anh.setBounds(220, 204, 192,192);
	anh.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Customer.class.getResource("settings.gif"))));
	pntab5.add(anh);

    
    JPanel pntab6 = new JPanel();
    pntab6.setLayout(null);
	table = new JTable();
	table.setFont(new Font("Tahoma", Font.PLAIN, 13));
	table.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Thời gian giao dịch", "Loại giao dịch", "Số tiền"
		}
	));
	
	JScrollPane scrollPane = new JScrollPane(table);
	scrollPane.setBounds(10, 10, 606, 393);
	pntab6.add(scrollPane);
   
    tab.add(pntab1,"Home");
    tab.add(pntab2,"Nạp tiền");
    tab.add(pntab3,"Chuyển tiền");
    tab.add(pntab4,"Rút tiền");
    tab.add(pntab5,"Đổi mật khẩu");
    tab.add(pntab6,"Lịch sử giao dịch");
    thongtin(AccountNumber);
    lichSu(AccountNumber);
    this.setResizable(false);
	this.setLayout(new BorderLayout());
	this.add(tab);
	this.setVisible(true);
}
public static void main(String[] args) {
	new Customer("040106330118");
}
public  void thongtin(String acc) {
	
	try {
		connect = new Connect();
		ResultSet rs = connect.thongTinKhachHang(acc);
		
		while (rs.next()) {
			STK.setText(rs.getString(1));
			stkC.setText(rs.getString(1));
			lblNewLabel_1_2.setText(rs.getString(1));
			stkR.setText(rs.getString(1));
			hoten.setText(rs.getString(2));	
			queQuan.setText(rs.getString(3));
			
		}
		String s = connect.soDu(acc);
		so.setText(s+" VND");
		lblNewLabel_1_2_1.setText(s+" VND");
		soDuR.setText(s+" VND");
		soDuC.setText(s);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
public void lichSu(String AccountNumber) {
	
		try {
			connect = new Connect();
			ResultSet rs = connect.lichSuGiaoDich(AccountNumber);
			model = (DefaultTableModel) table.getModel();
			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getTimestamp(1).toString(),rs.getString(2), rs.getInt(3)+" VNĐ", 
				});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}
}
