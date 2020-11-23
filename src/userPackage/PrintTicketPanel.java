package userPackage;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.FlightModel;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PrintTicketPanel extends JPanel {
     
	/**
	 * Create the panel.
	 */
	public PrintTicketPanel(FlightModel flt,PersonModel pss) {
		setBounds(0, 0, 350, 650);
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Californian FB", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 40, 45, 18);
		add(lblNewLabel);
		
		JLabel name = new JLabel(pss.name);
		name.setForeground(Color.BLUE);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Arial", Font.ITALIC, 12));
		name.setBounds(58, 40, 114, 18);
		add(name);
		
		JLabel lblUserid = new JLabel("UserID");
		lblUserid.setFont(new Font("Californian FB", Font.PLAIN, 12));
		lblUserid.setBounds(190, 40, 45, 18);
		add(lblUserid);
		
		JLabel userID = new JLabel(pss.username);
		userID.setForeground(Color.BLUE);
		userID.setHorizontalAlignment(SwingConstants.CENTER);
		userID.setFont(new Font("Arial", Font.ITALIC, 12));
		userID.setBounds(245, 40, 95, 18);
		add(userID);
		
		JLabel lblNewLabel_1 = new JLabel("Emirates");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_1.setBounds(120, 4, 100, 20);
		add(lblNewLabel_1);
		
		JLabel lblDate = new JLabel("Time");
		lblDate.setFont(new Font("Californian FB", Font.PLAIN, 12));
		lblDate.setBounds(10, 68, 45, 18);
		add(lblDate);
		
		SimpleDateFormat df=new SimpleDateFormat();
		Date d=new Date();
		d.setTime(flt.dateInMilli+flt.timeInMilli);
		
		JLabel lblpmNov = new JLabel(df.format(d));
		lblpmNov.setForeground(Color.BLUE);
		lblpmNov.setHorizontalAlignment(SwingConstants.CENTER);
		lblpmNov.setFont(new Font("Arial", Font.ITALIC, 12));
		lblpmNov.setBounds(58, 68, 114, 18);
		add(lblpmNov);
		
		JLabel lblFlightId = new JLabel("Flight ID");
		lblFlightId.setFont(new Font("Californian FB", Font.PLAIN, 12));
		lblFlightId.setBounds(190, 68, 45, 18);
		add(lblFlightId);
		
		JLabel flightID = new JLabel(flt.ID+"");
		flightID.setForeground(Color.BLUE);
		flightID.setHorizontalAlignment(SwingConstants.CENTER);
		flightID.setFont(new Font("Arial", Font.ITALIC, 12));
		flightID.setBounds(245, 68, 95, 18);
		add(flightID);
		
		JLabel printingTime = new JLabel(df.format(Calendar.getInstance().getTime()));
		printingTime.setFont(new Font("Tahoma", Font.PLAIN, 8));
		printingTime.setHorizontalAlignment(SwingConstants.RIGHT);
		printingTime.setBounds(245, 627, 95, 13);
		add(printingTime);
		
		renderSeats(flt,pss);
		
	}
	

	private void renderSeats(FlightModel flt,PersonModel pss) {
		int x_offset = 65;
		int y_offset = 105;
		int middlegap = 25, seatGap = 6, h = 45, w = 45;
		int x = 0, y = 0;
		int begap = 15;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 4; j++) {
				JLabel lb = new JLabel(((char) ('A' + i)) + "" + (j + 1));
				lb.setBackground(Color.WHITE);
				lb.setBounds(x + x_offset, y + y_offset, w, h);
				lb.setBorder(BorderFactory.createRaisedBevelBorder());
				lb.setHorizontalAlignment(SwingConstants.CENTER);
				if(flt.seatMap[i][j]!=null && flt.seatMap[i][j].equals(pss.username)) {
					lb.setBackground(Color.GREEN);
				}
				add(lb);
				if (j == 1)
					x += middlegap;
				x += w + seatGap;
			}
			y += h + seatGap;
			x = 0;
			if (i == 2) {
				y +=seatGap + begap;
			}
		}

	}
	
}
