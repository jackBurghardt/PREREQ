import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class Blob {
	
	public File file;
	public String sha; 
	public String fileDesc;

	public Blob (String filePath) {
		file = new File(filePath); //this does not work
		makeFile(); //this declares sha
		fileDesc = filePath + " : " + sha;
	}
	
	public void makeFile () {
		
		//gets content of file
		String fileContents = "";
		try { fileContents = fileToString(); }
		catch (IOException e) { System.out.println(e); return; }
		
		
		//get sha1 of file contents
		byte[] fileContent = fileContents.getBytes();
		String shaName = byteToHexSha(fileContent);
		sha = shaName;
		
		//make file with sha1 name
		try { Files.write( Paths.get("./objects/" + sha + ".txt"), fileContents.getBytes() ); }
		catch (Exception e) { System.out.println(e); return; }
		
		//bonus: read and write the data as zip compressed file
		
		
	}

	//throws a bomb at the index folder and hopes it works
	public boolean pop() {
		try {
			//kills the satan span of a file
			File fileToDel = new File("./objects/" + sha + ".txt");
			fileToDel.delete();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
//---------------------------------------- Stolen Code -----------------------------------------------	

	public String fileToString() throws IOException {
		Path fPath = Path.of(file.getPath());
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
