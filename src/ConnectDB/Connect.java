package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

public class Connect {
	private PreparedStatement ps;
	private static Connection c;
	public static Connection getConnection () throws SQLException {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://LAPTOP-PB07JP04\\MSSQLSERVER22:1433; ;databaseName=NganHang;integratedSecurity=true";
			c = DriverManager.getConnection(connectionURL, "sa", "10102004");
			System.out.println("Kết nối thành công");
		} catch (ClassNotFoundException e) {
			System.out.println("kết nối thất bại");
			System.err.print(e.getMessage()+"/n"+e.getClass()+"/n"+e.getCause());
		} 
		return c;
		
	}
	public Connect() throws SQLException {
		Connection c = Connect.getConnection();
	}
	public static void close () {
		if (c!=null) {
			try {
				c.close();
				System.out.println("ngắt kết nối thành công");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public int ktraTonTai (String AccountNumber) {
		try {
			ps = c.prepareStatement("select count(*) as result\r\n"
					+ "from TaiKhoan\r\n"
					+ "where STK = ?\r\n");
			ps.setString(1, AccountNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("result");
			}
			return 3;
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;
		}
		
	}
	public int checkPIN(String stk, String PIN) {
		try {
			ps = c.prepareStatement("select @@rowcount \r\n  "
					+ "from TaiKhoan \r\n"
					+ "where STK = (?) \r\n"
					+ "and pin = (?) ");
			ps.setString(1, stk);
			ps.setString(2, PIN);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;
		}
		return 3;
	}
	public int chuyenkhoan(String AccountNumberA, String AccountNumberB, int amount) {
		if (ktraTonTai(AccountNumberB)==0) {
			return 0;
		}
		else if (ktraTonTai(AccountNumberB)==1) {
			int chuyen = giaoDich(AccountNumberA, "Chuyển", amount);
			int nhan = giaoDich(AccountNumberB, "Nhận", amount);
			if (chuyen==1&&nhan==1) {
				return 1;  
			}
		}
		return 10;
	}
	public int giaoDich (String AccountNumber ,  String trade_type, int soTien) {
	try {
		ps = c.prepareStatement("insert into GiaoDich (STK, LoaiGD, SoTien)\r\n"
				+ "values (?, ?, ?)\r\n"
				+ "select @@rowcount as result;\r\n"
				+ "");
		ps.setString(1, AccountNumber);
		ps.setString(2, trade_type);
		ps.setInt(3, soTien);
		int rs = ps.executeUpdate();
		return rs;
	} catch (SQLException e) {
		e.printStackTrace();
		return -1;
	}
	}
	public String soDu (String AccountNumber) {
		try {
			ps = c.prepareStatement("select \r\n"
					+ "(sum(case when LoaiGD = N'Nạp'then SoTien else 0 end)+sum(case when LoaiGD = N'Nhận'then SoTien else 0 end)\r\n"
					+ "-sum(case when LoaiGD = N'Chuyển'then SoTien else 0 end)-sum(case when LoaiGD = N'Rút'then SoTien else 0 end))\r\n"
					+ "as SoDu\r\n"
					+ "from GiaoDich \r\n"
					+ "where STK = ? ");
			ps.setString(1, AccountNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("SoDu")!= null) {
				return rs.getString("SoDu");
			}else {
				return "0";
			}
				}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
		
	}
	
	public String ThongKeNap (String AccountNumber, String bd, String kt) {
		try {
			ps = c.prepareStatement("select sum(case when LoaiGD = N'Nạp' then SoTien else 0 end) as giaTri\r\n"
					+ "from GiaoDich\r\n"
					+ "where STK = ?\r\n"
					+ "and NgayGD between ? and ?");
			ps.setString(1, AccountNumber);
			ps.setString(2, bd);
			ps.setString(3, kt);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("giaTri")!= null) {
				return rs.getString("giaTri");
			}else {
				return "0";
			}
				}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
		
	}
	public String ThongKeRut (String AccountNumber, String bd, String kt) {
		try {
			ps = c.prepareStatement("select sum(case when LoaiGD = N'Rút' then SoTien else 0 end) as giaTri\r\n"
					+ "from GiaoDich \r\n"
					+ "where STK = ? \r\n"
					+ "and NgayGD between ? and ?");
			ps.setString(1, AccountNumber);
			ps.setString(2, bd);
			ps.setString(3, kt);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("giaTri")!= null) {
				return rs.getString("giaTri");
			}else {
				return "0";
			}
				}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
		
	}
	public String ThongKeChuyen (String AccountNumber, String bd, String kt) {
		try {
			ps = c.prepareStatement("select sum(case when LoaiGD = N'Chuyển' then SoTien else 0 end) as giaTri\r\n"
					+ "from GiaoDich\r\n"
					+ "where STK = ?\r\n"
					+ "and NgayGD between ? and ?");
			ps.setString(1, AccountNumber);
			ps.setString(2, bd);
			ps.setString(3, kt);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("giaTri")!= null) {
				return rs.getString("giaTri");
			}else {
				return "0";
			}
				}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
		
	}
	public ResultSet  lichSuGiaoDich(String AccountNumber) {
		try {
			ps = c.prepareStatement("select NgayGD, LoaiGD, SoTien\r\n"
					+ "from GiaoDich\r\n"
					+ "where STK = ?");
			ps.setString(1, AccountNumber);
			ResultSet rs = ps.executeQuery();
			if (rs!=null) {
				return rs;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int xoaTaiKhoan (String AccountNumber) {
		try {
			ps= c.prepareStatement("delete from TaiKhoan where STK = ? select @@rowcount as result");
			ps.setString(1, AccountNumber);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;
		}
	}
	public ResultSet timKiem (String AccountNumber) {
		try {
			ps = c.prepareStatement("select * from TaiKhoan\r\n"
					+ "where STK = ?");
			ps.setString(1, AccountNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs;
			}
		return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int insertAccount (String stk, String ten, String sdt, String qq, String pin, String mk, int trangThai, String maNV) {
		try {
			ps = c.prepareStatement(""
					+ "insert into TaiKhoan  \r\n"
					+ "values ((?), (?), (?), (?), (?),(?), (?), (?))"
					+ "select @@rowcount as result");
			ps.setString(1, stk);
			ps.setString(2, ten);
			ps.setString(3, sdt);
			ps.setString(4, qq);
			ps.setString(5, pin);
			ps.setString(6, mk);
			ps.setInt(7, trangThai);
			ps.setString(8, maNV);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return 3 ;
		}
	}
	public int updateAccount (String stk, String ten, String sdt, String qq, String pin, String mk, int trangThai) {
		try {
			ps = c.prepareStatement("update TaiKhoan \r\n"
					+ "set STK = ?,\r\n"
					+ " TenKH = ?,\r\n"
					+ " SDT = ?,\r\n"
					+ "	QueQuan = ?,\r\n"
					+ "	PIN = ?,\r\n"
					+ "	MK = ?,\r\n"
					+ "	TrangThai = ?\r\n"
					+ "where STK = '040106330117'\r\n"
					+ "select @@rowcount as result;");
			ps.setString(1, stk);
			ps.setString(2, ten);
			ps.setString(3, sdt);
			ps.setString(4, qq);
			ps.setString(5, pin);
			ps.setString(6, mk);
			ps.setInt(7, trangThai);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;
		}
	}
	public int login (String AccountNumber, String AccountPassword) {
		try {
			ps = c.prepareStatement("select count(*) as result \r\n"
					+ "from TaiKhoan\r\n"
					+ "where STK = (?)\r\n"
					+ "and MK = (?)\r\n"
					+ "and TrangThai = 1;");
			ps.setString(1, AccountNumber);
			ps.setString(2, AccountPassword);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("result");
			}
			return -6;
		} catch (SQLException e) {
			e.printStackTrace();
			return -6;
		}
	}
	public int loginAD (String AccountNumber, String AccountPassword) {
		try {
			ps = c.prepareStatement("select count(*) as result \r\n"
					+ "from NhanVien\r\n"
					+ "where MaNV = ?\r\n"
					+ "and MK = ?");
			ps.setString(1, AccountNumber);
			ps.setString(2, AccountPassword);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("result");
			}
			return -6;
		} catch (SQLException e) {
			e.printStackTrace();
			return -6;
		}
		
	}
	public ResultSet thongTinKhachHang(String STK) {
		try {
			ps = c.prepareStatement("select STK, TenKH, QueQuan\r\n"
					+ "from TaiKhoan\r\n"
					+ "where STK = (?)");
			ps.setString(1, STK);
			ResultSet rs = ps.executeQuery();
			if (rs!=null) {
				return rs;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet danhSach () {
		try {
			ps = c.prepareStatement("select STK, TenKH, SDT, QueQuan, PIN, MK, TrangThai\r\n"
					+ "from TaiKhoan");
			ResultSet rs = ps.executeQuery();
			
			if (rs != null) {
				return rs;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int doiMK (String AccountNumber, String AccountPassword) {
		try {
			ps = c.prepareStatement("update TaiKhoan\r\n"
					+ "set MK = ?\r\n"
					+ "where STK = ?\r\n"
					+ "select @@ROWCOUNT as result");
			ps.setString(1, AccountPassword);
			ps.setString(2, AccountNumber);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;
		}
		
	}
	
	
public static void main(String[] args) throws SQLException {
	
	Connect con = new Connect();

	
	int kq = con.checkPIN("040106330117", "101024");
	
	System.out.println(kq);

	int ktra = con.ktraTonTai("040106330117");
	System.out.println(ktra);
	
	String nghich = con.ThongKeNap("040106330117", "2023-02-10", "2023-02-20");
	System.out.println(nghich);
	
	
//	
//	con.close(c);
	
	
}
}
