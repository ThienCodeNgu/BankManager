package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import ConnectDB.Connect;

public class testForm extends JFrame {
	private String stk;
	public Connect con;
	private JTextField bd;
	private JTextField kt;
public testForm(String stk) {
	this.setTitle("Quản lí ngân hàng");
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setSize(300, 100);
	this.setLayout(new GridLayout(3,2));
	this.stk = stk;
	
	JLabel ngauBD = new JLabel("Ngày bắt đầu:");
	JLabel ngayKT = new JLabel("Ngày kết thúc:");
	bd = new JTextField(10);
	kt = new JTextField(10);
	this.add(ngauBD);
	this.add(bd);
	this.add(ngayKT);
	this.add(kt);
	JButton button = new JButton("Thống kê");
	this.add(button);
	System.out.println(stk);

	button.addActionListener(new ActionListener() {
		
	
		public void actionPerformed(ActionEvent e) {
			try {
				con = new Connect();
				String t1 = con.ThongKeNap(stk,bd.getText(),kt.getText());
				String t2 = con.ThongKeRut(stk,bd.getText(),kt.getText());
				String t3 = con.ThongKeChuyen(stk,bd.getText(),kt.getText());
				System.out.println(t1);
				
				
				int s1 = Integer.parseInt(t1);
				int s2 = Integer.parseInt(t2);
				int s3 = Integer.parseInt(t3);
				
				DefaultCategoryDataset dcd = new DefaultCategoryDataset();
				dcd.setValue(s1, "Nạp", "Nạp tiền");
				dcd.setValue(s2, "Rút", "Rút tiền");
				dcd.setValue(s3, "Chuyển", "Chuyển khoản");
				
				JFreeChart jChart = ChartFactory.createBarChart("THỐNG KÊ GIAO DỊCH", "LOẠI GIAO DỊCH", "SỐ TIỀN", dcd, PlotOrientation.VERTICAL,true, true, false);
				CategoryPlot plot = jChart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				
				ChartFrame chartFrm = new ChartFrame("Thống kê giao dịch", jChart, true);
				chartFrm.setVisible(true);
				chartFrm.setSize(900,600);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
	});
	
	
	
	
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setVisible(true);
}

}
