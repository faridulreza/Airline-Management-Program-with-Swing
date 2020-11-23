package managerPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import net.miginfocom.swing.MigLayout;
import userPackage.UserLogin;
import utilities.FlightModel;


import java.awt.Font;
import java.awt.Color;

import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ManagerMainFrame {

	private JFrame frame;
	private CreateFlightPanel mCreateFlightPanel;
    private ArrayList<FlightModel>flightList;
	/**
	 * Launch the application.
	 */
	public static void run() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMainFrame window = new ManagerMainFrame();
					window.frame.pack();
					window.frame.setSize(950, 650);
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagerMainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		flightList=FlightModel.getFlightList();
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 650);
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent evt) {
				FlightModel.saveCityList();
				FlightModel.saveFlightList();

			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JPanel tabPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, tabPanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tabPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, tabPanel, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabPanel, -750, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(tabPanel);

		JPanel contentPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, contentPanel, 55, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, contentPanel, 6, SpringLayout.EAST, tabPanel);
		tabPanel.setLayout(new MigLayout("", "[200px]", "[][][45px][][45px][][45px][][40px][][]"));
		springLayout.putConstraint(SpringLayout.SOUTH, contentPanel, -6, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, contentPanel, -6, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(contentPanel);

		JPanel headingPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, headingPanel, 6, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, headingPanel, 6, SpringLayout.EAST, tabPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, headingPanel, -6, SpringLayout.NORTH, contentPanel);
		springLayout.putConstraint(SpringLayout.EAST, headingPanel, -8, SpringLayout.EAST, frame.getContentPane());
		contentPanel.setLayout(null);
		frame.getContentPane().add(headingPanel);
		headingPanel.setLayout(new MigLayout("", "[][][200px][grow]", "[29px]"));

		JLabel headingLb = new JLabel("Cheap flight tickets, Find yourself in travelling!");
		headingLb.setForeground(new Color(165, 42, 42));
		headingLb.setFont(new Font("Lucida Console", Font.BOLD, 18));
		headingPanel.add(headingLb, "cell 1 0,alignx center,aligny center");
		try {
			BufferedImage img = ImageIO.read(new File("images\\trophy.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		headingPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		tabPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
        
		contentPanel.setLayout(new BorderLayout());
		
		JButton bookNowButton = new JButton("Create Flight");
		bookNowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (mCreateFlightPanel == null)
					mCreateFlightPanel = new CreateFlightPanel();
				contentPanel.removeAll();
				headingLb.setText("Create a flight in seconds");
				contentPanel.add(mCreateFlightPanel);
				contentPanel.revalidate();
				contentPanel.repaint();
			}
		});
		bookNowButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		tabPanel.add(bookNowButton, "cell 0 2,grow");

		JButton myBookingsButton = new JButton("All Flight List");
		myBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPanel.removeAll();
				JScrollPane js=new JScrollPane(new AllFlightsViewJpanel(FlightModel.getFlightList(),1));
				js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				headingLb.setText("List of Flights created so far");
				contentPanel.add(js);
				contentPanel.revalidate();
				contentPanel.repaint();
			}
		});
		myBookingsButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		tabPanel.add(myBookingsButton, "cell 0 4,grow");

		JButton searchFlightButton = new JButton("Find a Flight");
		searchFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPanel.removeAll();
				
				contentPanel.add(new SearchPanelForManager());
				headingLb.setText("Quickly search and manage a flight");
				contentPanel.revalidate();
				contentPanel.repaint();
			}
		});
		searchFlightButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		tabPanel.add(searchFlightButton, "cell 0 6,grow");

		JButton logoutButton = new JButton("LogOut");
		logoutButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = new File("data\\managerSession.txt");
				if (f.isFile())
					f.delete();
				FlightModel.saveCityList();
				FlightModel.saveFlightList();
				frame.setVisible(false);
				frame.dispose();
				UserLogin.reRun();
			}
		});
		

		tabPanel.add(logoutButton, "cell 0 8,grow");

		myBookingsButton.doClick();

	}
}
