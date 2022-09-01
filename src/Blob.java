import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Blob {
	
	public File file;

	public Blob (String filePath) {
		file = new File(filePath); //this does not work
	}

	


	
	public void makeFile (String filePath) {
		//get the file into a string
		
		
		
		//convert the file to sha string
		
		
		
		//this does not work
		byte[] fileContent = filePath.getBytes();
		String sha = byteToHexSha(fileContent);
		
		//go to objects folder
		
		
		
		
		
		//make new file with same contents and file name of sha1-code
		
		
		
		
		//bonus: read and write the data as zip compressed file
		
		
		
		
		
		
	}
	
	
	//make getter and setter for sha1 to use later
	public String getSha (String filePath) {
		return "";
	}
	
	
	
//-------------------------------------------------------------------------------------------------	

	public String byteToHexSha (byte[] fileBytes) {
		MessageDigest md = null;
	    try { md = MessageDigest.getInstance("SHA-1");}
	    catch(Exception e) { e.printStackTrace(); } 
	    
	    byte[] b = md.digest(fileBytes);
		
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			ret += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring(1);
		}
		return ret;
	}
	
}
