package userPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.FlightModel;

import java.awt.Dimension;

public class AllFlightsViewForUser extends JPanel{

	private ArrayList<FlightModel> flightList;
    private int height;
	/**
	 * Create the panel.
	 */
	
	public AllFlightsViewForUser(ArrayList<FlightModel> flightList,int val,PersonModel pss) {
		this.flightList = flightList;
		height=flightList.size()*(3+100)+25;
		setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(735, height));
		this.setMinimumSize(new Dimension(735, height));
        setLayout(null);	
		flightList.sort(new Comparator<FlightModel>() {

			@Override
			public int compare(FlightModel arg0, FlightModel arg1) {
				return Long.compare(arg1.dateInMilli+arg1.timeInMilli, arg0.dateInMilli+arg0.timeInMilli)*val;
			}
			
		});
		int y=3;
		for(FlightModel flt:flightList) {
			FlightItemPanelForUser tm=new FlightItemPanelForUser(flt,pss);
			tm.setBounds(3,y,714,100);
			add(tm);
			y+=100+3;
		}
		
		if(flightList.size()==0) {
			JLabel js=new JLabel("No Bookings for you!");
			js.setBounds(755/2-90,0,200,20);
			add(js);
		}
	}
	
	@Override
    public Dimension getMaximumSize() {
        
		return new Dimension(755, height);
        
    }
	
}
