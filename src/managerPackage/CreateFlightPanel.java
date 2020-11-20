package managerPackage;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.optionalusertools.TimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.TimeChangeEvent;
import com.sun.jdi.event.EventQueue;

import utilities.FlightModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;

public class CreateFlightPanel extends JPanel {

	/**
	 * Create the panel.
	 */

	private ArrayList<String> cityList;
	private ArrayList<FlightModel> flightList;
	private JComboBox<String> departFromCombo, flyToCombo;
	private JLabel datePickerLabel;
	private JTextField bussinessClassPriceTx;
	private JTextField economyClassPriceTx;
	private JTextField airbusModelNameTx;
	private long dateInMilli = -1;
	private long timeInMilli = -1, duraInMilli=-1;
	private double priceb,pricee;
	private JTextField duration;
	private JLabel flightIDLabel;
	private JLabel departTimeLabel;

	public CreateFlightPanel() {
		flightList=FlightModel.getFlightList();
		setBackground(Color.WHITE);
		setBounds(0, 0, 735, 552);
		setLayout(null);
		DatePicker d1 = new DatePicker();
		CreateFlightPanel.this.add(d1);
		d1.setBounds(450, 200, 0, 0);
		d1.addDateChangeListener(new DateChangeListener() {

			@Override
			public void dateChanged(DateChangeEvent event) {
				Date date = Date.from(event.getNewDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
				datePickerLabel.setText("     " + event.getNewDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
				dateInMilli = date.getTime();
			}

		});

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel lbl = new JLabel("Flight ID :");
		lbl.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 14));
		lbl.setBounds(10, 10, 47, 22);
		add(lbl);

		flightIDLabel = new JLabel("0");
		flightIDLabel.setText(""+flightList.size());
		flightIDLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 14));
		flightIDLabel.setBounds(67, 10, 45, 22);
		add(flightIDLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 35, 740, 1);
		add(separator);

		cityList = FlightModel.getCityList();
		departFromCombo = new JComboBox<String>();
		departFromCombo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		departFromCombo.setForeground(Color.BLACK);
		departFromCombo.setBackground(Color.WHITE);
		departFromCombo.setModel(new DefaultComboBoxModel<String>(cityList.toArray(new String[cityList.size()])));
		departFromCombo.setBounds(147, 86, 135, 30);
		departFromCombo.addActionListener(new ComboItemListener());
		add(departFromCombo);

		JLabel lblNewLabel = new JLabel("Depart From");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(147, 70, 68, 13);
		add(lblNewLabel);

		flyToCombo = new JComboBox<>();
		flyToCombo.setForeground(Color.BLACK);
		flyToCombo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		flyToCombo.setBackground(Color.WHITE);
		flyToCombo.setModel(new DefaultComboBoxModel<String>(cityList.toArray(new String[cityList.size()])));
		flyToCombo.setBounds(387, 87, 135, 26);
		flyToCombo.addActionListener(new ComboItemListener());
		add(flyToCombo);

		JLabel lblFlyTo = new JLabel("Fly to");
		lblFlyTo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFlyTo.setBackground(Color.WHITE);
		lblFlyTo.setBounds(387, 70, 68, 13);
		add(lblFlyTo);

		datePickerLabel = new JLabel("     yyyy-mm-dd");
		datePickerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		datePickerLabel.setIcon(new ImageIcon("images\\calender-icon.png"));
		datePickerLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		datePickerLabel.setVerticalTextPosition(SwingConstants.CENTER);
		datePickerLabel.setIconTextGap(20);
		datePickerLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		datePickerLabel.setBounds(266, 181, 135, 30);

		datePickerLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				d1.openPopup();
			}
		});
		add(datePickerLabel);

		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(266, 167, 45, 13);
		add(lblNewLabel_1);

		departTimeLabel = new JLabel("          hh:mm");
		departTimeLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				JComboBox<Integer> hr=new JComboBox<>();
                JComboBox<Integer> mn=new JComboBox<>();
				Integer[]hrArr=new Integer[24];
				Integer[]mnArr=new Integer[60];
				for(int i=0;i<24;i++)hrArr[i]=i;
				for(int i=0;i<60;i++)mnArr[i]=i;
				
				hr.setModel(new DefaultComboBoxModel<Integer>(hrArr));
				mn.setModel(new DefaultComboBoxModel<Integer>(mnArr));
				
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Hour:"));
				myPanel.add(hr);
				myPanel.add(new JLabel("Minute:"));
				myPanel.add(mn);
				

				int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Departure time",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					int h=(int) hr.getSelectedItem();
					int m=(int) mn.getSelectedItem();
					timeInMilli=(h*60+m)*60*1000l;
					String s=h<10?"0"+h:""+h;
					s+=" : ";
					s+= m<10?"0"+m:""+m;
					departTimeLabel.setText("          "+s);
					
				}
			}
		});
		departTimeLabel.setVerticalTextPosition(SwingConstants.CENTER);
		departTimeLabel.setIcon(new ImageIcon("images\\clock-icon.png"));
		departTimeLabel.setIconTextGap(35);
		departTimeLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		departTimeLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		departTimeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		departTimeLabel.setBounds(147, 238, 135, 30);
		add(departTimeLabel);

		JLabel lblNewLabel_1_1 = new JLabel("Departure Time");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1_1.setBounds(147, 224, 85, 13);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Flight Duration (hour)");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1_2.setBounds(387, 224, 111, 13);
		add(lblNewLabel_1_2);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(120, 318, 440, 2);
		add(separator_1);

		JLabel lblNewLabel_2 = new JLabel("Ticket Price");
		lblNewLabel_2.setBounds(120, 301, 68, 13);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Bussiness Class");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(147, 350, 85, 26);
		add(lblNewLabel_3);

		bussinessClassPriceTx = new JTextField();
		bussinessClassPriceTx.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		bussinessClassPriceTx.setBounds(237, 350, 96, 26);
		add(bussinessClassPriceTx);
		bussinessClassPriceTx.setColumns(10);

		JLabel lblNewLabel_3_1 = new JLabel("Economy Class");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(357, 350, 85, 26);
		add(lblNewLabel_3_1);

		economyClassPriceTx = new JTextField();
		economyClassPriceTx.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		economyClassPriceTx.setColumns(10);
		economyClassPriceTx.setBounds(449, 350, 96, 26);
		add(economyClassPriceTx);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(343, 317, 1, 80);
		add(separator_2);

		JLabel lblNewLabel_3_2 = new JLabel("Airbus Model");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_2.setBounds(209, 430, 85, 26);
		add(lblNewLabel_3_2);

		airbusModelNameTx = new JTextField();
		airbusModelNameTx.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		airbusModelNameTx.setColumns(10);
		airbusModelNameTx.setBounds(319, 430, 179, 26);
		add(airbusModelNameTx);

		JButton addFlightButton = new JButton("Add Flight");
		addFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validateForm()) {
					addFlight();
				}
			}
		});
		addFlightButton.setForeground(SystemColor.text);
		addFlightButton.setBackground(SystemColor.textHighlight);
		addFlightButton.setIcon(null);
		addFlightButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		addFlightButton.setBounds(610, 494, 95, 30);
		add(addFlightButton);

		duration = new JTextField();
		duration.setBounds(387, 238, 111, 30);
		add(duration);
		duration.setColumns(10);

	}
	
	protected void addFlight() {
	  FlightModel flt=new FlightModel();
	  flt.ID=flightList.size();
	  flt.dateInMilli=dateInMilli;
	  flt.timeInMilli=timeInMilli;
	  flt.durationInMilli=duraInMilli;
	  flt.model=airbusModelNameTx.getText().trim();
	  flt.source=(String) departFromCombo.getSelectedItem();
	  flt.destination=(String) flyToCombo.getSelectedItem();
	  flt.priceBussiness=priceb;
	  flt.priceEconomy=pricee;
	  flightList.add(flt);
	  
	  
	  //Clear form
	  flightIDLabel.setText(""+flightList.size());
	  dateInMilli=-1;
	  datePickerLabel.setText("     yyyy-mm-dd");
	  timeInMilli=-1;
	  departTimeLabel.setText("          hh:mm");
	  duraInMilli=-1;
	  duration.setText("");
	  bussinessClassPriceTx.setText("");
	  economyClassPriceTx.setText("");
	  airbusModelNameTx.setText("");
	  
	  JOptionPane.showMessageDialog(null, "Flight added successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
	}

	

	private boolean validateForm() {
		
		String tm="~ add new ~";
		
		if(tm.equals(departFromCombo.getSelectedItem()) || tm.equals(flyToCombo.getSelectedItem())) {
			JOptionPane.showMessageDialog(null,"Please add some place.\nSelect \"~ add new ~\" from the drop down box.","Invalid Place",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(dateInMilli==-1) {
			JOptionPane.showMessageDialog(null,"Please Select a Date first!","Date not selected",JOptionPane.ERROR_MESSAGE);
			return false;
			
		}
		if(timeInMilli==-1) {
			JOptionPane.showMessageDialog(null,"Please Set the departure time.","No Departure time",JOptionPane.ERROR_MESSAGE);
			return false;			
		}
		
		if(duration.getText().isBlank()) {
			JOptionPane.showMessageDialog(null,"Set the Flight duration","Error",JOptionPane.ERROR_MESSAGE);
			return false;						
		}
		else {
			String s=duration.getText().trim();
			try {
			   double d=Double.parseDouble(s);
			   duraInMilli=(long)d*60*60*1000;
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Please enter vlaid flight time in hour .(eg. 6.50 for 6 hours 30 min)","Invalid Duration time",JOptionPane.ERROR_MESSAGE);
				return false;							
			}
			
		}
		
		if(bussinessClassPriceTx.getText().isBlank()) {
			JOptionPane.showMessageDialog(null,"Set price for Bussiness Class seats.","Error",JOptionPane.ERROR_MESSAGE);
			return false;						
		}
		else {
			String s=bussinessClassPriceTx.getText().trim();
			try {
			   priceb=Double.parseDouble(s);
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Please enter vlaid price for Bussiness class seats!","Invalid price",JOptionPane.ERROR_MESSAGE);
				return false;							
			}
			
		}
		

		if(economyClassPriceTx.getText().isBlank()) {
			JOptionPane.showMessageDialog(null,"Set price for Economy Class seats.","Error",JOptionPane.ERROR_MESSAGE);
			return false;						
		}
		else {
			String s=economyClassPriceTx.getText().trim();
			try {
			   pricee=Double.parseDouble(s);
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Please enter vlaid price for Economy class seats!","Invalid price",JOptionPane.ERROR_MESSAGE);
				return false;							
			}
			
		}
		
		if(airbusModelNameTx.getText().isEmpty()) {
		
			JOptionPane.showMessageDialog(null,"Please enter the airbus model.","Error",JOptionPane.ERROR_MESSAGE);
			return false;						
			
		}
		
		
		return true;
	}

	class ComboItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			JComboBox b = (JComboBox) arg0.getSource();
			String item = (String) b.getSelectedItem();
			if (item.equals("~ add new ~")) {
				String name = JOptionPane.showInputDialog(null, "Enter City Name");
				if (name == null || name.isEmpty())
					return;
				name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
				cityList.add(name.trim());
				cityList.sort(new Comparator<String>() {

					@Override
					public int compare(String arg0, String arg1) {
						// TODO Auto-generated method stub
						return arg0.compareTo(arg1);
					}

				});

				String a = (String) departFromCombo.getSelectedItem();
				String c = (String) flyToCombo.getSelectedItem();

				departFromCombo
						.setModel(new DefaultComboBoxModel<String>(cityList.toArray(new String[cityList.size()])));
				flyToCombo.setModel(new DefaultComboBoxModel<String>(cityList.toArray(new String[cityList.size()])));
				if (!a.equals("~ add new ~"))
					departFromCombo.setSelectedItem(a);
				if (!c.equals("~ add new ~"))
					flyToCombo.setSelectedItem(c);
				b.setSelectedItem(name.trim());

			}

		}

	}
}
