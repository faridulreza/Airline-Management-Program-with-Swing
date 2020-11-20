package managerPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JPanel;

import utilities.FlightModel;

import java.awt.Dimension;

public class AllFlightsViewJpanel extends JPanel{

	private ArrayList<FlightModel> flightList;
    private int height;
	/**
	 * Create the panel.
	 */
	
	public AllFlightsViewJpanel(ArrayList<FlightModel> flightList) {
		this.flightList = flightList;
		height=flightList.size()*(3+100)+10;
		setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(735, height));
		this.setMinimumSize(new Dimension(735, height));
        setLayout(null);	
		flightList.sort(new Comparator<FlightModel>() {

			@Override
			public int compare(FlightModel arg0, FlightModel arg1) {
				// TODO Auto-generated method stub
				return Long.compare(arg1.dateInMilli+arg1.timeInMilli, arg0.dateInMilli+arg0.timeInMilli);
			}
			
		});
		int y=3;
		for(FlightModel flt:flightList) {
			FlightItemPanelForManager tm=new FlightItemPanelForManager(flt);
			tm.setBounds(3,y,714,100);
			add(tm);
			y+=100+3;
			
		}
	}
	
	@Override
    public Dimension getMaximumSize() {
        
		return new Dimension(755, height);
        
    }
	
}
