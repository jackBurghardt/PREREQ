import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class Index {
	
	HashMap<String, Blob> blobMap = new HashMap<String, Blob>();
	LinkedList<String> blobs = new LinkedList<String>();
	File indexFile;
	
	public Index () {
		//init a project that has empty file named index 
		try { 
			Files.write( Paths.get("index.txt"), "".getBytes() );
			indexFile = new File("index.txt");
			
		}
		catch (Exception e) { e.printStackTrace(); return; }
		
		//creates Objects folder
		new File("./objects").mkdirs();
	}
	
	public boolean addBlob(String fileName) {
		//need to check for bad input
		Blob temp = new Blob (fileName);
		blobMap.putIfAbsent(fileName, temp); 
		//need to check if already there
		blobs.add(fileName);
		
		//add the thing to the index in form of
		//fileName : sha1
		
		
		try {
			//adding true should mean data is not overwritten
			FileWriter author = new FileWriter(indexFile, true);
			author.write(temp.fileDesc + "\n");
			author.close();
		} catch (Exception e) { e.printStackTrace(); return false; }
		
		return true;
	}
	
	public boolean removeBlob(String fileName) {
		//need to check for bad input
		Blob blob = blobMap.get(fileName);
		blobMap.remove(fileName);
		blobs.remove(fileName);
		
		//remove the thing from the list
		try {
			//janky way to clear file by overwriting it as blank
			FileWriter author = new FileWriter(indexFile);
			author.write("");
			
			//rewrites all the list items
			//this is terribly inefficient but if you say anything I will strangle a cat
			for (String cur : blobs) {
				author.write(blobMap.get(cur).fileDesc + "\n");
			}
			
			author.close();
			
		} catch (Exception e) {
			 e.printStackTrace();
			 return false;
		}
		
		//deletes the file in the objects folder
		//everything has been deleted from all lists so file should de-reference and get swept
		return blob.pop();		
	}

}
