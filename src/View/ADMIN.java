package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import ConnectDB.Connect;

public class ADMIN extends JFrame {
	private String luachon;
private JTextField fieldTimKiem;
private JTable table;
private JTextField stkT;
private JTextField hoTenT;
private JTextField sdtT;
private JTextField pinT;
private JTextField mkT;
private Connect con;
private DefaultTableModel model;
private String maNV;
private JComboBox comboBox;
private ButtonGroup bg;
public String getLuaChon() {
	return luachon;
}
public void setLuaChon(String luaChon) {
	this.luachon = luaChon;
}

public ADMIN(String maNV) {
	this.setTitle("Quản lí ngân hàng");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setSize(1351, 763);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setLayout(null);
	this.luachon = "";
	this.maNV = maNV;
	
	
	JLabel bank = new JLabel();
	bank.setBounds(10, 10, 135, 139);
	this.add(bank);
	bank.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ADMIN.class.getResource("bank.png"))));
	
	JLabel title = new JLabel("VT-BANK");
	title.setFont(new Font("Tahoma", Font.PLAIN, 25));
	title.setForeground(Color.green);
	title.setHorizontalAlignment(SwingConstants.CENTER);
	title.setBounds(145, 37, 1172, 43);
	this.add(title);
	
	fieldTimKiem = new JTextField();
	fieldTimKiem.setBounds(200, 90, 851, 30);
	this.add(fieldTimKiem);
	fieldTimKiem.setColumns(10);
	
	
	
	JButton logout = new JButton("");
	logout.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ADMIN.class.getResource("User-Interface-Logout-icon.png"))));
	logout.setBounds(1280, 0, 48, 43);
	logout.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog( null , 
                    "Bạn có muốn đăng xuất?",
                    "",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				new login();
				con.close();
				dispose();
			}
			else if ( result == JOptionPane.NO_OPTION) {
				
			}
		}
	});
	this.add(logout);
	
	table = new JTable();
	table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "Số tài khoản", "Tên", "Số điện thoại", "Quê Quán", "PIN","Mật khẩu", "Trạng thái"
			}
		));
	JScrollPane sc = new JScrollPane(table);
	table.setBackground(new Color(176,196,222));
	sc.setBounds(10, 165, 1317, 238);
	this.add(sc);
	
	JPanel panelNhap = new JPanel();
	panelNhap.setBackground(new Color(176,196,222));
	panelNhap.setBounds(10, 425, 1317, 211);
	this.add(panelNhap);
	panelNhap.setLayout(null);
	
	JLabel labelSTK = new JLabel("STK:");
	labelSTK.setFont(new Font("Tahoma", Font.PLAIN, 15));
	labelSTK.setBounds(10, 10, 82, 30);
	panelNhap.add(labelSTK);
	
	stkT = new JTextField();
	stkT.setBounds(102, 10, 233, 30);
	panelNhap.add(stkT);
	stkT.setColumns(10);
	
	JLabel hoTenLabel = new JLabel("Họ Tên:");
	hoTenLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
	hoTenLabel.setBounds(10, 64, 82, 30);
	panelNhap.add(hoTenLabel);
	
	hoTenT = new JTextField();
	hoTenT.setColumns(10);
	hoTenT.setBounds(102, 64, 233, 30);
	panelNhap.add(hoTenT);
	
	JLabel labelQue = new JLabel("Quê quán:");
	labelQue.setFont(new Font("Tahoma", Font.PLAIN, 15));
	labelQue.setBounds(413, 10, 82, 30);
	panelNhap.add(labelQue);
	
	String[] arr_tinh = {
			"",
			"An Giang",
			"Bà Rịa – Vũng Tàu",
			"Bạc Liêu",
			"Bắc Giang",
			"Bắc Kạn",
			"Bắc Ninh",
			"Bến Tre",
			"Bình Dương",
			"Bình Định",
			"Bình Phước",
			"Bình Thuận",
			"Cà Mau",
			"Cao Bằng",
			"Cần Thơ",
			"Đà Nẵng",
			"Đắk Lắk",
			"Đắk Nông",
			"Điện Biên",
			"Đồng Nai",
			"Đồng Tháp",
			"Gia Lai",
			"Hà Giang",
			"Hà Nam",
			"Hà Nội",
			"Hà Tĩnh",
			"Hải Dương",
			"Hải Phòng",
			"Hậu Giang",
			"Hòa Bình",
			"Thành phố Hồ Chí Minh",
			"Hưng Yên",
			"Khánh Hòa",
			"Kiên Giang",
			"Kon Tum",
			"Lai Châu",
			"Lạng Sơn",
			"Lào Cai",
			"Lâm Đồng",
			"Long An",
			"Nam Định",
			"Nghệ An",
			"Ninh Bình",
			"Ninh Thuận",
			"Phú Thọ",
			"Phú Yên",
			"Quảng Bình",
			"Quảng Nam",
			"Quảng Ngãi",
			"Quảng Ninh",
			"Quảng Trị",
			"Sóc Trăng",
			"Sơn La",
			"Tây Ninh",
			"Thái Bình",
			"Thái Nguyên",
			"Thanh Hóa",
			"Thừa Thiên Huế",
			"Tiền Giang",
			"Trà Vinh",
			"Tuyên Quang",
			"Vĩnh Long",
			"Vĩnh Phúc",
			"Yên Bái"};
	
	
	comboBox = new JComboBox(arr_tinh);
	comboBox.setBounds(505, 10, 234, 30);
	panelNhap.add(comboBox);
	
	JLabel labelSDT = new JLabel("SĐT:");
	labelSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
	labelSDT.setBounds(413, 64, 82, 30);
	panelNhap.add(labelSDT);
	
	sdtT = new JTextField();
	sdtT.setColumns(10);
	sdtT.setBounds(506, 64, 233, 30);
	panelNhap.add(sdtT);
	
	JLabel pinLabel = new JLabel("PIN:");
	pinLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
	pinLabel.setBounds(10, 120, 82, 30);
	panelNhap.add(pinLabel);
	
	pinT = new JTextField();
	pinT.setColumns(10);
	pinT.setBounds(102, 120, 233, 30);
	panelNhap.add(pinT);
	
	JLabel labelMK = new JLabel("Mật Khẩu:");
	labelMK.setFont(new Font("Tahoma", Font.PLAIN, 15));
	labelMK.setBounds(413, 120, 82, 30);
	panelNhap.add(labelMK);
	
	mkT = new JTextField();
	mkT.setColumns(10);
	mkT.setBounds(506, 120, 233, 30);
	panelNhap.add(mkT);
	
	JLabel labelTrangThai = new JLabel("Trạng Thái:");
	labelTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 15));
	labelTrangThai.setBounds(10, 171, 82, 30);
	panelNhap.add(labelTrangThai);
	
	JRadioButton co = new JRadioButton("Đang hoạt động");
	co.setBounds(117, 178, 120, 21);
	panelNhap.add(co);
	
	JRadioButton khong = new JRadioButton("Không hoạt động");
	khong.setBounds(240, 178, 125, 21);
	panelNhap.add(khong);
	
	bg = new ButtonGroup();
	bg.add(co);
	bg.add(khong);
	JButton timKiemButton = new JButton("Tìm kiếm");
	timKiemButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ADMIN.class.getResource("Search-icon.png"))));
	timKiemButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				con = new Connect();
				int ktraTimKiem = con.ktraTonTai(fieldTimKiem.getText());
				System.out.println(ktraTimKiem);
				//gọi hàm kiểm tra tồn tài khoản 
				if (fieldTimKiem.getText().equals("")) {
					//nếu textfield rỗng thì xuất ra thông báo chú ý, và yêu cầu nhập stk để tìm kiếm
					JOptionPane.showMessageDialog(null, "Nhập số tài khoản để tìm kiếm", "Chú ý", JOptionPane.WARNING_MESSAGE);	
					
				}else if (ktraTimKiem == 1) {
					//nếu tài khoản đó tồn tại thì hiển thị thông tin lên panel (có thể xóa đc)
					ResultSet rs = con.timKiem(fieldTimKiem.getText());
						stkT.setText(rs.getString(1));
						hoTenT.setText(rs.getString(2));
						sdtT.setText(rs.getString(3));
						comboBox.setSelectedItem(rs.getString(4));
						pinT.setText(rs.getString(5));
						mkT.setText(rs.getString(6));
						//nếu trạng thái hoạt động là 1 thì click có
						//ngược lại nếu trạng thái là 0 thì click không
						if (rs.getInt(7) == 1) {
							co.doClick();
						}else if (rs.getInt(7) == 0) {
							khong.doClick();
					}
				}
				else  {
					//nếu tài khoản không tồn tại hoặc ô tìm kiếm rỗng thì thông báo rồi reset
					reset();
					JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại", "Lỗi", JOptionPane.WARNING_MESSAGE);	
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
			
		
	});
	timKiemButton.setBounds(1150, 89, 132, 30);
	this.add(timKiemButton);
	
	JLabel icon = new JLabel();
	icon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ADMIN.class.getResource("credit-card-icon.png"))));
	icon.setBounds(850, 10, 520, 191);
	panelNhap.add(icon);
	
	JButton Them = new JButton("Thêm");
	
	Them.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setLuaChon("Thêm");
			reset();
		}
	});
	Them.setBounds(10, 658, 174, 58);
	this.add(Them);
	Them.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ADMIN.class.getResource("user-add-icon.png"))));
	
	JButton Sua = new JButton("Sửa");
	Sua.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ADMIN.class.getResource("Text-Edit-icon.png"))));
	Sua.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setLuaChon("Sửa");
			
		}
	});
	Sua.setBounds(261, 658, 174, 58);
	this.add(Sua);
	
	JButton Xoa = new JButton("Xóa");
	Xoa.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ADMIN.class.getResource("remove.png"))));
	Xoa.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (stkT.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 tài khoản để xóa", "Chú ý", JOptionPane.WARNING_MESSAGE);		
				}
				else {
				con = new Connect();
				int result = JOptionPane.showConfirmDialog( null , 
	                    "Bạn có chắc muốn xóa tài khoản này?",
	                    "",
	                    JOptionPane.YES_NO_OPTION,
	                    JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					int check = con.ktraTonTai(fieldTimKiem.getText());
					if (check ==1) {
					    int xoaTaiKhoan = con.xoaTaiKhoan(fieldTimKiem.getText());
					    if (xoaTaiKhoan == 1) {
						// xuất ra thông báo xóa tài khoản thành công
					    	JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công");
					    	int rowCount = model.getRowCount();
							//Remove rows one by one from the end of the table
							for (int i = rowCount - 1; i >= 0; i--) {
							    model.removeRow(i);
							}
							danhsach();
					    }else if (xoaTaiKhoan == 0) {
						//xuất ra thông báo xóa tài khoản không thành công
					    	JOptionPane.showMessageDialog(null, "Xóa tài khoản thất bại");
					     }
				}
				}
				else if ( result == JOptionPane.NO_OPTION) {
					//nếu chọn không thì bỏ qua và trở về trang chính
				}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
		}
	});
	Xoa.setBounds(531, 658, 174, 58);
	this.add(Xoa);
	
	JButton save = new JButton("Lưu");
	save.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ADMIN.class.getResource("save.png"))));
	save.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (getLuaChon().equals("Thêm")||getLuaChon().equals("")) {
				//gọi hàm insert bên class connect
			 try {
				con = new Connect();
				
				int ktratontai = con.ktraTonTai(stkT.getText());
				if (stkT.getText().equals("")||hoTenT.getText().equals("")||sdtT.getText().equals("")||comboBox.getSelectedItem().equals("")||pinT.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin "); 
				}else {
				if (ktratontai==0) {
					//nếu tài khoản đã tồn tại thì xuất ra thông báo lỗi
					JOptionPane.showMessageDialog(null, "Số tài khoản này đã tồn tại", "Lỗi", JOptionPane.WARNING_MESSAGE);		
				}
				else {
					int kq = 1 ;
					//nếu tài khoản chưa tồn tại thì tiếp tục thực hiện lệnh chèn thêm tài khoản vào danh sách
				if (co.isSelected()) {
					kq = 1;
				}
				else if (khong.isSelected()) {
					kq =0;
				}
				 String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
				 String regP = "^\\d{6}$";

			        // Kiem tra
			        boolean ktP = pinT.getText().matches(regP);
				    // Kiem tra dinh dang
				    boolean kt = sdtT.getText().matches(reg);
				    if (kt==true&&ktP==true) {
				int kqc = con.insertAccount(stkT.getText(),  hoTenT.getText(), sdtT.getText(), comboBox.getSelectedItem()+"", pinT.getText(), mkT.getText(), kq, maNV);
				
				if (kqc == 0) {
					JOptionPane.showMessageDialog(null, "Thêm tài khoản không thàng công", "Lỗi", JOptionPane.WARNING_MESSAGE);			
				}
				else if (kqc == 1) {
					JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công!");
					int rowCount = model.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    model.removeRow(i);
					}
					danhsach();
				}
				}else if (kt == false||ktP==false) {
					JOptionPane.showMessageDialog(null, "Định dạng pin hoặc số điện thoại không chính xác!");
				}
				    }}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			else if (getLuaChon().equals("Sửa")){
				int ktratontai = con.ktraTonTai(fieldTimKiem.getText());
				if (stkT.getText().equals("")||hoTenT.getText().equals("")||sdtT.getText().equals("")||comboBox.getSelectedItem().equals("")||pinT.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
				}else {
				if (ktratontai==0) {
					//nếu tài khoản không tồn tại thì xuất ra thông báo lỗi
					JOptionPane.showMessageDialog(null, "Tài khoản này không tồn tại", "Lỗi", JOptionPane.WARNING_MESSAGE);		
				}
				else {
					//nếu tài khoản này tồn tại thì cho phép chỉnh sửa
					int kq = 1;
					if (co.isSelected()) {
						kq = 1;
					}else if (khong.isSelected()){
						kq = 0;
					}
					String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
					 String regP = "^\\d{6}$";

				        // Kiem tra
				        boolean ktP = pinT.getText().matches(regP);
					    // Kiem tra dinh dang
					    boolean kt = sdtT.getText().matches(reg);
					    if (kt==true&&ktP==true) {
					int result = JOptionPane.showConfirmDialog( null , 
		                    "Bạn có chắc muốn sửa tài khoản này?",
		                    "",
		                    JOptionPane.YES_NO_OPTION,
		                    JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
					int update = con.updateAccount(stkT.getText(),  hoTenT.getText(), sdtT.getText(), comboBox.getSelectedItem()+"", pinT.getText(), mkT.getText(), kq);
					if (update == 1) {
						JOptionPane.showMessageDialog(null, "Sửa tài khoản thành công", "Thông báo", JOptionPane.WARNING_MESSAGE);
						int rowCount = model.getRowCount();
						//Remove rows one by one from the end of the table
						for (int i = rowCount - 1; i >= 0; i--) {
						    model.removeRow(i);
						}
						danhsach();
					}
					}}else if (kt == false||ktP==false) {
						JOptionPane.showMessageDialog(null, "Định dạng pin hoặc số điện thoại không chính xác!");
					}
					}
				}
			}
		}
	});
	save.setBounds(826, 658, 174, 58);
	this.add(save);
	
	
	JButton ThongKe = new JButton("Thống kê");
	ThongKe.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			if (stkT.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Tìm kiếm tài khoản để thực hiện chức năng");
			}else {
					new testForm(stkT.getText());
			}	
		}
	});
	ThongKe.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ADMIN.class.getResource("statistics-icon.png"))));
	ThongKe.setBounds(1127, 658, 174, 58);
	this.add(ThongKe);
	
	JLabel back = new JLabel();
	back.setBounds(0,0,1351, 763);
	back.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ADMIN.class.getResource("back.jpg"))));
	this.add(back);
	
	URL url = ADMIN.class.getResource("bank-icon.png");
	Image img = Toolkit.getDefaultToolkit().createImage(url);
	this.setIconImage(img);
	danhsach();
	
	this.setVisible(true);
}

public void danhsach() {
	//ham do du lieu vao table
	try {
		con = new Connect();
		ResultSet rs = con.danhSach();
		model = (DefaultTableModel) table.getModel();
		while (rs.next()) {
			model.addRow(new Object[] {
					rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),
			});
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}


public void reset() {
	bg.clearSelection();
	stkT.setText("");
	sdtT.setText("");
	mkT.setText("");
	hoTenT.setText("");
	comboBox.setSelectedIndex(0);
	pinT.setText("");
}
public void checkPhone(String str) {
    // Bieu thuc chinh quy mo ta dinh dang so dien thoai
    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

    // Kiem tra dinh dang
    boolean kt = str.matches(reg);

    if (kt == false) {
        
    } else {
        System.out.println("Dung dinh dang so dien thoai!");
    }
}
public static void main(String[] args) {
	new ADMIN("NV001");
}
}
