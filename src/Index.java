import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Index {
	


	
	public Index () {
		
		//init a project that has empty file named index 
		try { Files.write( Paths.get("./index.txt"), "".getBytes() ); }
		catch (Exception e) { System.out.println(e); return; }
		
		//create Objects folder
		new File("./objects").mkdirs();
	}
	
	public void addBlob() {
		return;
	}
	
	
	//when given project name can add blobs to given objects folder

	//store filename and sha1 value 
	//adds it to 'index' file
	
	//can also remove blobs,  removes the mapping and object

	


}
