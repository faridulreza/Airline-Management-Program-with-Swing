package managerPackage;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.FlightModel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FlightItemPanelForManager extends JPanel {

	private JLabel departTimeLabel;
	private JLabel arrivalTimeLabel;
	private JLabel dateLabel;
	private JButton delayButton;
	private JButton cancelFlightButton;
	private JLabel statusLabel;
	private FlightModel flt;

	/**
	 * Create the panel.
	 */

	public FlightItemPanelForManager(FlightModel flt) {
		this.flt = flt;
		this.setBounds(0,0,714,100);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(null);

		JLabel IDLabel = new JLabel("ID : 0");
		IDLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		IDLabel.setBounds(10, 4, 58, 13);
		add(IDLabel);

		JLabel modelLabel = new JLabel("Boing 770");
		modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		modelLabel.setBounds(74, 4, 142, 13);
		add(modelLabel);

		JLabel departureCityLabel = new JLabel("Dhaka");
		departureCityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		departureCityLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		departureCityLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		departureCityLabel.setBounds(39, 20, 100, 25);
		add(departureCityLabel);

		departTimeLabel = new JLabel("14 : 00");
		departTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		departTimeLabel.setVerticalAlignment(SwingConstants.TOP);
		departTimeLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		departTimeLabel.setBounds(49, 44, 80, 30);
		add(departTimeLabel);

		JLabel arrivalCityLabel = new JLabel("Dhaka");
		arrivalCityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		arrivalCityLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		arrivalCityLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		arrivalCityLabel.setBounds(224, 20, 100, 25);
		add(arrivalCityLabel);

		JLabel betweenLabel = new JLabel();
		betweenLabel.setIcon(new ImageIcon("images//arrow-icon.png"));
		betweenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		betweenLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		betweenLabel.setBounds(147, 36, 70, 25);
		add(betweenLabel);

		arrivalTimeLabel = new JLabel("14 : 00");
		arrivalTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		arrivalTimeLabel.setVerticalAlignment(SwingConstants.TOP);
		arrivalTimeLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		arrivalTimeLabel.setBounds(234, 44, 80, 30);
		add(arrivalTimeLabel);

		dateLabel = new JLabel("27th November");
		dateLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateLabel.setBounds(354, 11, 128, 25);
		add(dateLabel);

		JLabel durationLabel = new JLabel("6 hours");
		durationLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		durationLabel.setBounds(396, 35, 70, 13);
		add(durationLabel);

		JLabel economyClassPriceLabel = new JLabel("$ 334.00");
		economyClassPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		economyClassPriceLabel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 10));
		economyClassPriceLabel.setBounds(360, 58, 45, 13);
		add(economyClassPriceLabel);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(415, 54, 1, 20);
		add(separator);

		JLabel bussinessClassLabel = new JLabel("$ 334.00");
		bussinessClassLabel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 10));
		bussinessClassLabel.setBounds(426, 58, 56, 13);
		add(bussinessClassLabel);

		delayButton = new JButton("Delay");
		delayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = JOptionPane.showInputDialog(null,"Delay time for flight "+flt.ID +" :(by hours)");
				if (s != null && !s.isEmpty()) {
					try {
						long d = (long) (Double.valueOf(s) * 1000 * 60 * 60);
						flt.delayedByInMilli += d;
						flt.status = flt.FLIGHT_DELAYED;
						Calendar cd = Calendar.getInstance();
						cd.setTimeInMillis(flt.dateInMilli + flt.timeInMilli + d);

						flt.timeInMilli = cd.get(Calendar.HOUR) * 60 * 60 * 1000l + cd.get(Calendar.MINUTE) * 60 * 1000l
								+ cd.get(Calendar.SECOND) * 1000l + cd.get(Calendar.MILLISECOND);
						cd.add(Calendar.MILLISECOND, (int) -flt.timeInMilli);
						flt.dateInMilli = cd.getTimeInMillis();
						bindTimeData();
						setStatus();

					} catch (Exception e) {
					}
				}
			}
		});
		delayButton.setForeground(Color.WHITE);
		delayButton.setBackground(Color.BLUE);
		delayButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		delayButton.setBounds(592, 20, 100, 25);
		add(delayButton);

		cancelFlightButton = new JButton("Cancel");
		cancelFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Cancel the flight "+flt.ID+ "?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
				     flt.status=flt.FLIGHT_CANCELLED;
				     setStatus();
				}
			}
		});
		cancelFlightButton.setBackground(Color.BLUE);
		cancelFlightButton.setForeground(Color.WHITE);
		cancelFlightButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		cancelFlightButton.setBounds(592, 58, 100, 25);
		add(cancelFlightButton);

		statusLabel = new JLabel("on schedule");
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(576, 4, 128, 13);
		add(statusLabel);

		JLabel availSeatsLabel = new JLabel("36");
		availSeatsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		availSeatsLabel.setBounds(514, 57, 45, 13);
		add(availSeatsLabel);

		JLabel lblNewLabel_1 = new JLabel("Available Seats");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(504, 44, 70, 13);
		add(lblNewLabel_1);

		// bind Constants data
		IDLabel.setText("ID : " + flt.ID);
		modelLabel.setText(flt.model);
		availSeatsLabel.setText("" + flt.seatCount);
		DecimalFormat df = new DecimalFormat("#.##");
		durationLabel.setText(df.format(flt.durationInMilli / (1000 * 60 * 60.0)) + " hours");
		bussinessClassLabel.setText("$ "+df.format(flt.priceBussiness));
		economyClassPriceLabel.setText("$ "+df.format(flt.priceBussiness));
		departureCityLabel.setText(flt.source);
		arrivalCityLabel.setText(flt.destination);

		bindTimeData();
		setStatus();

	}

	private void setStatus() {
		if (flt.status == flt.FLIGHT_CANCELLED) {
			statusLabel.setText("Cancelled");
			statusLabel.setForeground(Color.RED);
			this.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else if (flt.status == flt.FLIGHT_DELAYED) {
			statusLabel.setText("Delayed by "
					+ new DecimalFormat("#.##").format((flt.delayedByInMilli / (1000 * 60 * 60.0))) + " hours");
			statusLabel.setForeground(Color.ORANGE);
			this.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		} else {
			long d = flt.timeInMilli + flt.dateInMilli;
			long e = Calendar.getInstance().getTimeInMillis();
			if (e <= d) {
				statusLabel.setText("On Schedule");
				statusLabel.setForeground(Color.BLUE);
				this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			} else if (e <= d + flt.durationInMilli) {
				statusLabel.setText("ongoing");
				statusLabel.setForeground(Color.GREEN);
				this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			} else {
				statusLabel.setText("Finished");
				statusLabel.setForeground(Color.BLACK);
				this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			}

		}

	}

	private void bindTimeData() {
		Calendar cd = Calendar.getInstance();
		cd.setTimeInMillis(flt.dateInMilli);
		dateLabel.setText(cd.get(Calendar.DAY_OF_MONTH) + " " + new SimpleDateFormat("MMMM").format(cd.getTime()));
		cd.add(Calendar.MILLISECOND, (int) (flt.timeInMilli + flt.durationInMilli));
		int a = cd.get(Calendar.HOUR_OF_DAY);
		int b = cd.get(Calendar.MINUTE);
		arrivalTimeLabel.setText((a < 10 ? "0" + a : a) + ":" + (b < 10 ? "0" + b : b));
		a = (int) (flt.timeInMilli / (1000 * 60 * 60));
		b = (int) ((flt.timeInMilli / (1000 * 60)) % 60);
		departTimeLabel.setText((a < 10 ? "0" + a : a) + ":" + (b < 10 ? "0" + b : b));
	}
	
	

}
