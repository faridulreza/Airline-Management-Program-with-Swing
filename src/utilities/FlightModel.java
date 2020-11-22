package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;



public class FlightModel implements Serializable {
	transient private static ArrayList<FlightModel> flightList;
	transient private static  ArrayList<String> cityList;
    transient public static final int FLIGHT_CANCELLED=0;
    transient public static final int FLIGHT_DELAYED=1;
    transient public static final int FLIGHT_NORMAL=2;
    
	
	
	public String source, destination,model;
	public Long dateInMilli=0l;
	public Long timeInMilli=0l;
	public Long  durationInMilli=0l,delayedByInMilli=0l;
	public double  priceBussiness,priceEconomy;
	public int status=FLIGHT_NORMAL,ID,seatCount=36;
	public String[][] seatMap = new String[10][4];

	
	public static ArrayList<FlightModel> getFlightList() {
		if(flightList!=null)return flightList;
		try {
			// Creating stream to read the object
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("data\\flightList.txt"));
			flightList = (ArrayList<FlightModel>) in.readObject();
			in.close();
		} catch (Exception e) {
			// System.out.println(e);
			flightList = new ArrayList<FlightModel>();
		}
		return flightList;

	}
	
	public static void saveFlightList() {
		if(flightList==null)return; 
		try{    
			  //Creating stream and writing the object  
			 File f=new File("data\\flightList.txt");
			 if(f.exists())f.delete();
			 f.createNewFile();
			 
			  FileOutputStream fout=new FileOutputStream("data\\flightList.txt");  
			  ObjectOutputStream out=new ObjectOutputStream(fout);  
			  out.writeObject(flightList);  
			  out.flush();   
			  out.close();  
			  //System.out.println("flightList saved");  
			  }
		 catch(Exception e){
			 e.printStackTrace();
			 }  
		 
	}
	
	public static void saveCityList() {
        
		if(cityList==null)return; 
		try{    
			  //Creating stream and writing the object  
			 File f=new File("data\\CityList.txt");
			 if(f.exists())f.delete();
			 f.createNewFile();
			 
			  FileOutputStream fout=new FileOutputStream("data\\CityList.txt");  
			  ObjectOutputStream out=new ObjectOutputStream(fout);  
			  out.writeObject(cityList);  
			  out.flush();   
			  out.close();  
			 // System.out.println("success");  
			  }
		 catch(Exception e){
			 
			 }  		
		
	}
	
	public static ArrayList<String> getCityList() {
		if(cityList!=null)return cityList;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("data\\CityList.txt"));
			cityList = (ArrayList<String>) in.readObject();
			in.close();
			
		} catch (Exception e) {
			
			cityList=new ArrayList<String>();
			cityList.add("~ add new ~");
		}
		
		return cityList;
	}
	

}
