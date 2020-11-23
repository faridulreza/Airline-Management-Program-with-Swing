package userPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PersonModel implements Serializable {
	public String name, username;
	public String passWordHash;
	public Integer trophies =0;
	public Set<Integer> bookedFlightIDSet = new HashSet<>();
	public Set<Integer> trophiesToGetFrom = new HashSet<>();

	PersonModel() {
	}

	PersonModel(String n, String u, String i) {
		name = n;
		username = u;
		passWordHash = i;
	}

	public static PersonModel getPersonModel(String userId) {
		try {
			// Creating stream to read the object
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("data\\userData\\" + userId + ".txt"));
			PersonModel s = (PersonModel) in.readObject();
			in.close();
			return s;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void writeData() {

		try {
			// Creating stream and writing the object
			File f = new File("data\\userData\\" + username + ".txt");
			if (f.exists())
				f.delete();
			f.createNewFile();
			FileOutputStream fout = new FileOutputStream("data\\userData\\" + username + ".txt");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(this);
			out.flush();
			// closing the stream
			//System.out.println("person saved");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
