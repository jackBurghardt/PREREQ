import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Tree {
	private String bigString;
	private String shawed;
	private File index = new File ("index.txt");
	public Tree(ArrayList<String> listOfString) throws NoSuchAlgorithmException, IOException {
		bigString = listOfString.get(0);
		for(int i=1; i<listOfString.size(); i++) {
			bigString = bigString + "\n" +listOfString.get(i);
		}
		shawed = GenerateHash(bigString);
		makeAndWriteToFile();
	
	}
	
	public String getBigString() {
		return bigString;
	}
	public void writeToIndexFile (ArrayList<String> strs) throws FileNotFoundException {
		Scanner scanny = new Scanner(index);
		while (scanny.hasNextLine()) {
			String fileName = scanny.next();
			scanny.next();
			String sha = scanny.next();
			strs.add("blob : " + sha + " " + fileName);
		}
		scanny.close();
	}
	public String getShawed() {
		return shawed;
	}
	
	public void makeAndWriteToFile() throws IOException {
		File fil = new File("./objects/" + shawed);
		if(!fil.exists()) { //only makes new file if file does not already exist
			fil.createNewFile();
			FileWriter fw = new FileWriter(fil, true);
			fw.write(bigString);
			fw.close();
			
		}
		
	}
	public void clear () throws IOException {
		FileWriter file = new FileWriter(index);
		PrintWriter print = new PrintWriter(file);
		String s = "";
		print.write(s);
		print.close();
		file.close();
	}
	public void createIndexFile () {
		
	}
	
	public String GenerateHash(String input) throws NoSuchAlgorithmException {
        MessageDigest objSHA = MessageDigest.getInstance("SHA-1");
        byte[] bytSHA = objSHA.digest(input.getBytes());
        BigInteger intNumber = new BigInteger(1, bytSHA);
        String strHashCode = intNumber.toString(16);
		
        // pad with 0 if the hexa digits are less then 40.
        while (strHashCode.length() < 40) {
            strHashCode = "0" + strHashCode;
        }
        return strHashCode;
    }
	
	
	public static void main (String [] args) throws NoSuchAlgorithmException, IOException {
	ArrayList<String> test = new ArrayList<String>();
	test.add("blob : addf120b430021c36c232c99ef8d926aea2acd6b");
	test.add("tree : c09f382894b42abb22deaef2b26ca5b008334cf7");
	test.add("blob : 2aae6c35c94fcfb415dbe95f408b9ce91ee846ed");
	Tree testTree = new Tree(test);
}
}
