package utilities;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import userPackage.PersonModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class SeatPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private FlightModel flt;
	private JLabel[][] labels = new JLabel[10][4];
	private seatChooserMouseAdapter[][] mAdapter = new seatChooserMouseAdapter[10][4];
	private JLabel lblNewLabel_1;
	private Runnable rn;
	private JButton payButton;
	private JButton applyTrophie;
	private JButton btnPrintFlightDetatils;
	private static PersonModel ps;
	private double totalPrice = 0;
	private int appliedTrophy = 0;
	private int totalSeatForUser = 0;

	public static void run(FlightModel flt, PersonModel pss, Runnable rn) {

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.getContentPane().add(new SeatPanel(flt, pss, rn));
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.pack();
				frame.setSize(350, 650);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

			}

		});

	}

	public SeatPanel(FlightModel flt, PersonModel pss, Runnable rn) {
		this.flt = flt;
		this.ps = pss;
		this.rn = rn;

		setBounds(0, 0, 350, 650);
		setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"Bussines class ( $ " + new DecimalFormat("#.##").format(flt.priceBussiness) + " )");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(83, 10, 180, 13);
		add(lblNewLabel);

		JLabel IDlabel = new JLabel("ID : " + flt.ID);
		IDlabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		IDlabel.setBounds(10, 10, 63, 13);
		add(IDlabel);

		JLabel modelName = new JLabel("" + flt.model);
		modelName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		modelName.setHorizontalAlignment(SwingConstants.RIGHT);
		modelName.setBounds(255, 10, 71, 13);
		add(modelName);

		lblNewLabel_1 = new JLabel("Buy");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(145, 542, 45, 13);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		;

		payButton = new JButton("$ 6876");
		payButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 10));
		payButton.setBounds(28, 563, 100, 25);

		applyTrophie = new JButton("Apply Trophy");
		applyTrophie.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 10));
		applyTrophie.setBounds(218, 563, 100, 25);

		btnPrintFlightDetatils = new JButton("Print Flight Detatils");
		btnPrintFlightDetatils.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 10));
		btnPrintFlightDetatils.setBounds(88, 563, 168, 30);

		renderSeats();

		if (ps == null)
			bindDataForManager();
		else
			bindDataForUser();

	}

	private void bindDataForUser() {

		add(lblNewLabel_1);
		add(payButton);
		add(applyTrophie);
		applyTrophie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (applyTrophie.getText().equals("Apply Trophy")) {
					int maxTrophy = (int) (totalPrice / (.5 * 2));
					appliedTrophy = Math.min(maxTrophy, ps.trophies);
					applyTrophie.setText("Remove");
					totalPrice -= appliedTrophy * .5;
					payButton.setText("$ " + new DecimalFormat("#.##").format(totalPrice));

				} else {
					totalPrice += appliedTrophy * .5;
					payButton.setText("$ " + new DecimalFormat("#.##").format(totalPrice));
					appliedTrophy = 0;
					applyTrophie.setText("Apply Trophy");
				}
			}

		});

		payButton.setText("$ 0");
		payButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Math.abs(totalPrice) < 1e-8) {
					JOptionPane.showMessageDialog(null, "Please choose a seat fisrt!", "No Seat Chossen",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					int res = JOptionPane.showConfirmDialog(null,
							"Pay $   " + new DecimalFormat("#.##").format(totalPrice) + "   ?", "Payment",
							JOptionPane.YES_NO_OPTION);
					if (res == 0) {
						// set selected seats for user and close

						for (int i = 0; i < 9; i++) {
							for (int j = 0; j < 4; j++) {
								if (labels[i][j].getBackground().equals(Color.GREEN)) {
									flt.seatMap[i][j] = new String(ps.username);
									labels[i][j].setBackground(Color.YELLOW);
									labels[i][j].setToolTipText("Reserved -Click to cancel");
									labels[i][j].removeMouseListener(mAdapter[i][j]);
									labels[i][j].addMouseListener(new seatCancelMouseAdapter());
									flt.seatCount--;
									totalSeatForUser++;
								}
							}
						}

						ps.bookedFlightIDSet.add(flt.ID);
						ps.trophiesToGetFrom.add(flt.ID);
						JOptionPane.showMessageDialog(null, "Success! Thank you for choosing us.");
						payButton.setText("$ 0");
						totalPrice = 0;
						ps.trophies -= appliedTrophy;
						rn.run();

						// TODO: comment these
						// ps.writeData();
						// FlightModel.saveFlightList();
					}
				}

			}
		});

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 4; j++) {
				if (flt.seatMap[i][j] != null) {
					// seat chosen by someone else
					if (flt.seatMap[i][j].equals(ps.username)) {
						labels[i][j].setBackground(Color.YELLOW);
						labels[i][j].setToolTipText("Reserved for you\n click to cancel");
						labels[i][j].addMouseListener(new seatCancelMouseAdapter());
						totalSeatForUser++;
					} else
						labels[i][j].setBackground(Color.RED);
				}

				else {
					mAdapter[i][j]=new seatChooserMouseAdapter();
					labels[i][j].addMouseListener(mAdapter[i][j]);
				}
			}

		}

	}

	class seatChooserMouseAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			JLabel lb = (JLabel) arg0.getSource();
			if (lb.getBackground().equals(Color.GREEN)) {
				lb.setBackground(Color.WHITE);
				if (lb.getText().charAt(0) > 'C')
					totalPrice -= flt.priceEconomy;
				else
					totalPrice -= flt.priceBussiness;
				payButton.setText("$ " + new DecimalFormat("#.##").format(totalPrice));

			} else {
				lb.setBackground(Color.GREEN);
				if (lb.getText().charAt(0) > 'C')
					totalPrice += flt.priceEconomy;
				else
					totalPrice += flt.priceBussiness;
				payButton.setText("$ " + new DecimalFormat("#.##").format(totalPrice));
			}
		}

	}

	class seatCancelMouseAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			JLabel lb = (JLabel) arg0.getSource();
			int res = JOptionPane.showConfirmDialog(null,
					"Cancel seat " + lb.getText() + "?\nYou will be refunded with only 70% of the price.",
					"Cancel reservation", JOptionPane.YES_NO_OPTION);

			if (res == 0) {
				lb.removeMouseListener(this);
				lb.setToolTipText(null);
				lb.setBackground(Color.WHITE);
				lb.addMouseListener(mAdapter[lb.getText().charAt(0) - 'A'][lb.getText().charAt(1) - '1']);
				double pd = 0;
				if (lb.getText().charAt(0) > 'C')
					pd = flt.priceEconomy;
				else
					pd = flt.priceBussiness;
				flt.seatMap[lb.getText().charAt(0) - 'A'][lb.getText().charAt(1) - '1'] = null;
				pd *= .7;
				flt.seatCount++;
				totalSeatForUser--;
				if (totalSeatForUser == 0) {
					ps.bookedFlightIDSet.remove(flt.ID);
					ps.trophiesToGetFrom.remove(flt.ID);
				}
				JOptionPane.showMessageDialog(null,
						"You have Recieved $ " + new DecimalFormat("#.##").format(pd) + " !");
				rn.run();
			}
		}

	}

	private void bindDataForManager() {
		add(btnPrintFlightDetatils);
		btnPrintFlightDetatils.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
                    //TODO print details
				    String s=System.getProperty("user.home")+"\\Desktop\\FlightDeatails_"+flt.ID+".txt";
				    File f=new File(s);
				    try {
				    	PrintWriter pw=new PrintWriter(f);
				    	pw.println("Flight ID : "+flt.ID);
				    	pw.println("From      : "+flt.source);
				    	pw.println("To        : "+flt.destination);
				    	pw.println("Time      : "+new SimpleDateFormat().format(flt.dateInMilli+flt.timeInMilli));
				    	pw.println("\n   Seat   |    Booked By");
				    	
				    	for(int i=0;i<9;i++)
				    		for(int j=0;j<4;j++) {
				    			for(int k=0;k<30;k++)pw.print("-");
				    			
				    			String st=(char)('A'+i)+""+(char)('1'+j);
				    			if(flt.seatMap[i][j]==null)pw.println("\n    "+st+"    |       N/A");
				    			else pw.println("\n    "+st+"    |   "+flt.seatMap[i][j]);
				    			
				    		}
				    pw.close();
				    JOptionPane.showMessageDialog(null, "Flight details is saved to your desktop.\n("+f.getAbsolutePath()+")");
				   
				    }
				    catch(Exception e) {
				    	e.printStackTrace();
				    	JOptionPane.showMessageDialog(null, "Couldn't create file");
				    }
				    

			}

		});

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 4; j++) {
				if (flt.seatMap[i][j] != null) {
					labels[i][j].setBackground(Color.RED);
					labels[i][j].setToolTipText("Booked by " + flt.seatMap[i][j]);
				}
			}
		}

	}

	private void renderSeats() {
		int x_offset = 55;
		int y_offset = 35;
		int middlegap = 25, seatGap = 6, h = 45, w = 45;
		int x = 0, y = 0;
		int begap = 15;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 4; j++) {
				JLabel lb = labels[i][j] = new JLabel(((char) ('A' + i)) + "" + (j + 1));
				lb.setBackground(Color.WHITE);
				lb.setBounds(x + x_offset, y + y_offset, w, h);
				lb.setBorder(BorderFactory.createRaisedBevelBorder());
				lb.setHorizontalAlignment(SwingConstants.CENTER);
				add(lb);
				if (j == 1)
					x += middlegap;
				x += w + seatGap;
			}
			y += h + seatGap;
			x = 0;
			if (i == 2) {
				JLabel lb = new JLabel("Economy Class( $ " + new DecimalFormat("#.##").format(flt.priceEconomy) + " )");
				lb.setBounds(83, y + y_offset + begap / 2, 180, 13);
				lb.setHorizontalAlignment(SwingConstants.CENTER);
				add(lb);
				y += 13 + seatGap + begap;
				lb.setFont(new Font("Tahoma", Font.PLAIN, 10));
			}
		}

	}
}
