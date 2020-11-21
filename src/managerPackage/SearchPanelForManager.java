package managerPackage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import utilities.FlightModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class SearchPanelForManager extends JPanel {

	/**
	 * Create the panel.
	 */

	private ArrayList<FlightModel> flightList;
	private JComboBox<String> departFromCombo, flyToCombo;
	private JLabel datePickerLabel;
	private long dateInMilli = 0;
	private long timeInMilli = 0;
	private String choosedSource = " any ", choosedDest = " any ";
	private JLabel departTimeLabel;
	private JPanel searchHolderJpanel;

	public SearchPanelForManager() {
		flightList = FlightModel.getFlightList();
		setBackground(Color.WHITE);
		setBounds(0, 0, 735, 552);
		setLayout(null);
		DatePicker d1 = new DatePicker();
		SearchPanelForManager.this.add(d1);
		d1.setBounds(400, 54, 0, 0);
		d1.addDateChangeListener(new DateChangeListener() {

			@Override
			public void dateChanged(DateChangeEvent event) {
				Date date = Date.from(event.getNewDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
				datePickerLabel.setText("     " + event.getNewDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
				dateInMilli = date.getTime();
				search(false);
			}

		});

	

		ArrayList<String> cityList = FlightModel.getCityList();
		String[] arrForCombo = new String[cityList.size()];

		for (int i = 0; i < cityList.size(); i++)
			arrForCombo[i] = cityList.get(i);
		arrForCombo[cityList.size() - 1] = " any ";

		departFromCombo = new JComboBox<String>();
		departFromCombo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		departFromCombo.setForeground(Color.BLACK);
		departFromCombo.setBackground(Color.WHITE);
		departFromCombo.setModel(new DefaultComboBoxModel<String>(arrForCombo));
		departFromCombo.setSelectedItem(" any ");
		departFromCombo.setBounds(29, 24, 135, 25);
		departFromCombo.addActionListener(new ComboItemListener());
		add(departFromCombo);

		JLabel lblNewLabel = new JLabel("Depart From");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(29, 8, 68, 13);
		add(lblNewLabel);

		flyToCombo = new JComboBox<>();
		flyToCombo.setForeground(Color.BLACK);
		flyToCombo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		flyToCombo.setBackground(Color.WHITE);
		flyToCombo.setModel(new DefaultComboBoxModel<String>(arrForCombo));
		flyToCombo.setSelectedItem(" any ");
		flyToCombo.setBounds(174, 24, 135, 25);
		flyToCombo.addActionListener(new ComboItemListener());
		add(flyToCombo);

		JLabel lblFlyTo = new JLabel("Fly to");
		lblFlyTo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFlyTo.setBackground(Color.WHITE);
		lblFlyTo.setBounds(174, 7, 68, 13);
		add(lblFlyTo);

		datePickerLabel = new JLabel("     yyyy-mm-dd");
		datePickerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		datePickerLabel.setIcon(new ImageIcon("images\\calender-icon.png"));
		datePickerLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		datePickerLabel.setVerticalTextPosition(SwingConstants.CENTER);
		datePickerLabel.setIconTextGap(20);
		datePickerLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		datePickerLabel.setBounds(319, 24, 135, 25);

		datePickerLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				d1.openPopup();
			}
		});
		add(datePickerLabel);

		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(319, 7, 45, 13);
		add(lblNewLabel_1);

		departTimeLabel = new JLabel("          hh:mm");
		departTimeLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				JComboBox<Integer> hr = new JComboBox<>();
				JComboBox<Integer> mn = new JComboBox<>();
				Integer[] hrArr = new Integer[24];
				Integer[] mnArr = new Integer[60];
				for (int i = 0; i < 24; i++)
					hrArr[i] = i;
				for (int i = 0; i < 60; i++)
					mnArr[i] = i;

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
					int h = (int) hr.getSelectedItem();
					int m = (int) mn.getSelectedItem();
					timeInMilli = (h * 60 + m) * 60 * 1000l;
					String s = h < 10 ? "0" + h : "" + h;
					s += " : ";
					s += m < 10 ? "0" + m : "" + m;
					departTimeLabel.setText("          " + s);
					search(false);
				}
			}
		});
		departTimeLabel.setVerticalTextPosition(SwingConstants.CENTER);
		departTimeLabel.setIcon(new ImageIcon("images\\clock-icon.png"));
		departTimeLabel.setIconTextGap(35);
		departTimeLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		departTimeLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		departTimeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		departTimeLabel.setBounds(469, 24, 135, 25);
		add(departTimeLabel);

		JLabel lblNewLabel_1_1 = new JLabel("Departure Time");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1_1.setBounds(469, 7, 85, 13);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Search by ID");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1_2.setBounds(615, 8, 85, 13);
		add(lblNewLabel_1_2);

		JButton btnNewButton = new JButton("Flight ID");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search(true);
			}
		});
		btnNewButton.setIcon(null);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setToolTipText("");
		btnNewButton.setBounds(614, 24, 85, 25);
		add(btnNewButton);

		searchHolderJpanel = new JPanel();
		searchHolderJpanel.setBackground(Color.WHITE);
		searchHolderJpanel.setBounds(0, 54, 735, 498);
		searchHolderJpanel.setLayout(new BorderLayout(0, 0));
		add(searchHolderJpanel);
		

	}

	class ComboItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			JComboBox b = (JComboBox) arg0.getSource();
			String item = (String) b.getSelectedItem();

			if (b.equals(departFromCombo))
				choosedSource = item;
			else
				choosedDest = item;

			// System.out.println(choosedSource +" "+choosedDest);
			search(false);

		}

	}

	ArrayList<FlightModel> tmpList = new ArrayList<FlightModel>();

	synchronized private void search(boolean searchByID) {
		tmpList.clear();

		if (searchByID) {
			String ID=JOptionPane.showInputDialog(null, "Enter Flight ID");
			if(ID!=null && !ID.isEmpty()){
				int id=-1;
				try{
				   id=Integer.parseInt(ID);
				}
				catch(Exception e) {}
				if(id==-1)return;
				
				
				for (FlightModel flt : flightList) {
				     if(flt.ID==id) {
				    	tmpList.add(flt);
				    	break;
				     }
				    	 
				}	
				
				
			}
			else return;
				
		} else {
			for (FlightModel flt : flightList) {
				if (flt.timeInMilli + flt.dateInMilli >= dateInMilli + timeInMilli) {
					if (choosedSource.equals(" any ")) {
						if (choosedDest.equals(" any ") || choosedDest.equals(flt.destination))
							tmpList.add(flt);
					} else if (flt.source.equals(choosedSource)) {
						if (choosedDest.equals(" any ") || choosedDest.equals(flt.destination))
							tmpList.add(flt);
					}
				}
			}
		}
		
		

		if (tmpList.size() == 0) {

			searchHolderJpanel.removeAll();
			JLabel js = new JLabel("No match found :(");
			js.setHorizontalAlignment(SwingConstants.CENTER);
			js.setPreferredSize(new Dimension(120, 35));
			searchHolderJpanel.add(js,BorderLayout.CENTER);
			searchHolderJpanel.revalidate();
			searchHolderJpanel.repaint();

		} else {
			
			searchHolderJpanel.removeAll();
			JScrollPane js = new JScrollPane(new AllFlightsViewJpanel(tmpList,-1));
			js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			searchHolderJpanel.add(js);
			searchHolderJpanel.revalidate();
			searchHolderJpanel.repaint();
		}

	}
}
