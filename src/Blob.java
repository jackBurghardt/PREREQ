import java.io.*;

//all of these were auto imported by eclipse and I dont want to touch them
//likely radio active
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//code is best understood while descending into madness
//there are many optimizations to be made here if I was not lazy
//I unfortunately have much bad news for me
public class Blob {
	
	public File file;
	public String sha; 
	public String fileDesc;
	
	public Blob (String filePath) {
		file = new File(filePath); //this does not work
		makeFile(); //this declares sha to be used in next line
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
		
		//bonus: read and write the data as zip compressed file
		//I stole this code and hopefully changed the right stuff so it could do what I wanted
		try {
			//this is all stolen code
			FileOutputStream fos = new FileOutputStream("./objects/" + sha + ".zip");
			ZipOutputStream zipOut = new ZipOutputStream(fos);
	        FileInputStream fis = new FileInputStream(file);
	        ZipEntry zipEntry = new ZipEntry(file.getName());
	        zipOut.putNextEntry(zipEntry);
	        byte[] bytes = new byte[1024];
	        int length;
	        while((length = fis.read(bytes)) >= 0) {
	            zipOut.write(bytes, 0, length);
	        }
	        zipOut.close();
	        fis.close();
	        fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//make file with sha1 name
		//this is now old code now that I changed to useing zips, can always go back if things get spicy
		//try { Files.write( Paths.get("./objects/" + sha + ".txt"), fileContents.getBytes() ); }
		//catch (Exception e) { System.out.println(e); return; }

		
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
