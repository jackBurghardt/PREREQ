import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class Blob {
	
	public File file;

	public Blob (String filePath) {
		file = new File(filePath); //this does not work
		makeFile();
	}
	
	public void makeFile () {
		
		//gets content of file
		String fileContents = "";
		try { fileContents = fileToString(); }
		catch (IOException e) { System.out.println(e); return; }
		
		
		//this does not work
		byte[] fileContent = fileContents.getBytes();
		
		
		
		String sha = byteToHexSha(fileContent);
		
		//renameFile(sha);
		
		//making file 
		try { Files.write( Paths.get("./objects/" + sha + ".txt"), fileContents.getBytes() ); }
		catch (Exception e) { System.out.println(e); return; }
		
		
		
		//copyFile(filePath); //assume Objects folder exists
		
		
		//convert the file to sha string
		
		
		
	
		
		//./file.txt
		
		
		//go to objects folder
		
		
		
		
		
		//make new file with same contents and file name of sha1-code
		
		
		
		
		//bonus: read and write the data as zip compressed file
		
		
		
		
		
		
	}
	
	
	//make getter and setter for sha1 to use later
	public String getSha (String filePath) {
		return "";
	}
	
// ======================================= Tester ===================================================
	
	public static void main (String [] hehe) {
		Blob a = new Blob ("./perplexing.txt");
		
		/*
		try {
			System.out.println(a.fileToString());
		} catch (IOException e) {
			System.out.println("Cringe");
			System.out.println(e);
		}
		*/
		a.makeFile();
	}	
	
//---------------------------------------- Stolen Code -----------------------------------------------	

	public String fileToString() throws IOException {
		Path fPath = Path.of(file.getPath());
		//Path fPath = Path.of("c:/temp/demo.txt");

		return Files.readString(fPath);
	}
	
	
	
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
