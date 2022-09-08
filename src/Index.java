import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class Index {
	
	HashMap<String, Blob> blobMap = new HashMap<String, Blob>();
	FileWriter author = null;
	File indexFile = null;
	
	public Index () {
		
		//init a project that has empty file named index 
		try { 
			Files.write( Paths.get("index.txt"), "".getBytes() ) ;
			indexFile = new File("index.txt");
			author = new FileWriter(indexFile);
		}
		catch (Exception e) { e.printStackTrace(); return; }
		
		
		//create Objects folder
		new File("./objects").mkdirs();
	}
	
	public boolean addBlob(String fileName) {
		//need to check for bad input
		Blob temp = new Blob (fileName);
		blobMap.putIfAbsent(fileName, temp); 
		
		//add the thing to the list
		try { author.write(temp.fileDesc + "\n"); }
		catch (Exception e) { e.printStackTrace(); }
		
		return true;
	}
	
	public boolean removeBlob(String fileName) {
		//need to check for bad input
		Blob blob = blobMap.get(fileName);
		blobMap.remove(fileName);
		
		//add the thing to the list
		try {
			PrintWriter writer = new PrintWriter(indexFile);
			writer.print("");
		} catch (Exception e) {
			
		}

		
		
		return blob.pop();		
	}

	//store filename and sha1 value 
	//adds it to 'index' file
	

	


}
