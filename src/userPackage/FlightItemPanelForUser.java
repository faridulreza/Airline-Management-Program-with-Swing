package userPackage;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.FlightModel;
import utilities.SeatPanel;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class FlightItemPanelForUser extends JPanel {

	private JLabel departTimeLabel;
	private JLabel arrivalTimeLabel;
	private JLabel dateLabel;
	private JButton printNrefundNbookButton;
	private JButton cancelFlightButton;
	private JLabel statusLabel;
	private FlightModel flt;
	private static PersonModel ps;
	private boolean isBooked, isOngoingOrFinished, isCancelled;
	private JLabel availSeatsLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 */

	public FlightItemPanelForUser(FlightModel flt, PersonModel pss) {
		this.flt = flt;
		this.ps = pss;
		this.setBounds(0, 0, 714, 100);
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

		printNrefundNbookButton = new JButton("Book Now");
		printNrefundNbookButton.setForeground(Color.WHITE);
		printNrefundNbookButton.setBackground(Color.BLUE);
		printNrefundNbookButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		printNrefundNbookButton.setBounds(592, 30, 100, 44);

		cancelFlightButton = new JButton("Manage");
		cancelFlightButton.setBackground(Color.BLUE);
		cancelFlightButton.setForeground(Color.WHITE);
		cancelFlightButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		cancelFlightButton.setBounds(592, 58, 100, 25);

		statusLabel = new JLabel("on schedule");
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(576, 4, 128, 13);
		add(statusLabel);

		availSeatsLabel = new JLabel("" + flt.seatCount);
		availSeatsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		availSeatsLabel.setBounds(514, 57, 45, 13);
		add(availSeatsLabel);

		lblNewLabel_1 = new JLabel("Available Seats");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(504, 44, 70, 13);
		add(lblNewLabel_1);

		// bind Constants data
		IDLabel.setText("ID : " + flt.ID);
		modelLabel.setText(flt.model);
		availSeatsLabel.setText("" + flt.seatCount);
		DecimalFormat df = new DecimalFormat("#.##");
		durationLabel.setText(df.format(flt.durationInMilli / (1000 * 60 * 60.0)) + " hours");
		bussinessClassLabel.setText("$ " + df.format(flt.priceBussiness));
		economyClassPriceLabel.setText("$ " + df.format(flt.priceEconomy));
		departureCityLabel.setText(flt.source);
		arrivalCityLabel.setText(flt.destination);

		setUp();
	}

	public void setUp() {
		bindTimeData();
		setStatus();
		if (ps.bookedFlightIDSet.contains(flt.ID))
			isBooked = true;
		setButtons();
	}

	private ActionListener listenerForPrint = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {

		}

	};
	private ActionListener listenerForCancel = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

		}

	};

	private void setButtons() {
		this.remove(cancelFlightButton);
		this.remove(printNrefundNbookButton);

		if (isOngoingOrFinished) {

		} else if (isBooked) {
			if (isCancelled) {
				remove(availSeatsLabel);
				remove(lblNewLabel_1);
				double refundVal = 0;
				for (int i = 0; i < 9; i++)
					for (int j = 0; j < 4; j++) {
						if (flt.seatMap[i][j] != null && flt.seatMap[i][j].equals(ps.username)) {
							if (i < 3)
								refundVal += flt.priceBussiness;
							else
								refundVal += flt.priceEconomy;
							flt.seatMap[i][j] = null;
						}
					}

				if (Math.abs(refundVal) < 1e-8)
					return;

				final String refund = new DecimalFormat("#.##").format(refundVal);
				printNrefundNbookButton.removeActionListener(listenerForPrint);
				listenerForPrint = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null, "You have recieved 100% refund : $ " + refund
								+ ". We are extremely sorry for your inconvinience");
						setUp();
					}
				};

				printNrefundNbookButton.addActionListener(listenerForPrint);
				printNrefundNbookButton.setBounds(592, 30, 100, 44);
				printNrefundNbookButton.setText("Get Refund");
				add(printNrefundNbookButton);

			} else {

				printNrefundNbookButton.removeActionListener(listenerForPrint);
				listenerForPrint = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO: print Ticket
						PrintTicketPanel tc = new PrintTicketPanel(flt, ps);
						BufferedImage image = new BufferedImage(350, 650, BufferedImage.TYPE_INT_ARGB);
						Graphics g = image.getGraphics();
						tc.paint(g);
						try {
							String userHomeFolder = System.getProperty("user.home");
							File f = new File(userHomeFolder+"\\Desktop", "YourTicket.png");

							ImageIO.write(image, "png", f);
							JOptionPane.showMessageDialog(null, "Your ticket is saved to Desktop.\n("+f.getAbsolutePath()+")");
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null, "Error Saving Ticket");
						}
					}
				};
				printNrefundNbookButton.addActionListener(listenerForPrint);

				cancelFlightButton.removeActionListener(listenerForCancel);
				listenerForCancel = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO open seatPanel for seat cancel or management
						SeatPanel.run(flt, ps, new Runnable() {

							@Override
							public void run() {
								setUp();
								availSeatsLabel.setText(flt.seatCount + "");
							}
						});
					}

				};
				cancelFlightButton.addActionListener(listenerForCancel);

				cancelFlightButton.setText("Manage");
				printNrefundNbookButton.setText("Print Ticket");
				cancelFlightButton.setBounds(592, 58, 100, 25);
				printNrefundNbookButton.setBounds(592, 20, 100, 25);
				add(printNrefundNbookButton);
				add(cancelFlightButton);
			}

		} else if (!isCancelled) {
			printNrefundNbookButton.removeActionListener(listenerForPrint);
			listenerForPrint = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO: open seatPanel for booking
					SeatPanel.run(flt, ps, new Runnable() {

						@Override
						public void run() {
							setUp();
							availSeatsLabel.setText(flt.seatCount + "");

						}
					});
				}
			};
			printNrefundNbookButton.addActionListener(listenerForPrint);

			printNrefundNbookButton.setBounds(592, 30, 100, 44);
			printNrefundNbookButton.setText("Book Now");
			add(printNrefundNbookButton);
		}

	}

	private void setStatus() {
		if (flt.status == flt.FLIGHT_CANCELLED) {
			statusLabel.setText("Cancelled");
			statusLabel.setForeground(Color.RED);
			this.setBorder(BorderFactory.createLineBorder(Color.RED));
			isCancelled = true;
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
				isOngoingOrFinished = true;
			} else {
				statusLabel.setText("Finished");
				statusLabel.setForeground(Color.BLACK);
				this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				isOngoingOrFinished = true;
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
