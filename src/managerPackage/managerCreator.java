package managerPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import userPackage.PersonModel;

public class managerCreator {

	public static void main(String[] args) {
		ArrayList<String> lst;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("data\\managerList.txt"));
			lst = (ArrayList<String>) in.readObject();
			in.close();

		} catch (Exception e) {
			lst = new ArrayList<String>();
		}

		String name = null, pass = null;
		name=(String)JOptionPane.showInputDialog(null,"Enter Manager ID","Create Manager",JOptionPane.PLAIN_MESSAGE);
		pass=(String)JOptionPane.showInputDialog(null,"Enter Manger Password","Create Manager",JOptionPane.PLAIN_MESSAGE);
		if(name ==null || name.isEmpty()|| pass==null || pass.isEmpty()) {
			return;
		}
		
		lst.add(name.trim()+"#"+pass.trim());

		try {
			FileOutputStream fout = new FileOutputStream("data\\managerList.txt");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(lst);
			out.flush();
			JOptionPane.showMessageDialog(null, "Manger Added.\nID  : "+name.trim()+"\nPass: "+pass.trim(), "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some error occured. Manager was not added","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}
