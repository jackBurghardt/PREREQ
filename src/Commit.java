import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

/**
 * my reaction to that information
 */

/**
 * @author evanstokdyk
 *
 */
public class Commit implements GitUtils {
	
	public Commit parent = null;
	public Commit child = null;
	public Path pTree;
    private String summary; //limit to 150 characters
    private String author;
    private String date;

	
	public Commit (Commit parent, String pTree, String summary, String author) {
		this.parent = parent;
		this.pTree = Paths.get(pTree);
		this.summary = summary;
	    this.author = author;
	    date = getDate();
	    writeToFile();
	}
	
	
	public String generateSha1() {
		String toSha = pTree.toString() + summary;
		return GitUtils.StringToSha(toSha);
	}
	
	public String getDate () {
		long allahWillGuideMe = Instant.now().getEpochSecond();
		return String.valueOf(allahWillGuideMe); //gets the time in the most gangster way
	}
	
	public void writeToFile() {
		//I will not test this code you cannot control me
		
		String toWrite = "";
		
		toWrite += pTree.toString() + "\n";
		
		if (parent != null) { toWrite += parent.pTree.toString() + "\n"; }
		else { toWrite += "\n"; } 
		
		if (child != null) { toWrite += child.pTree.toString() + "\n"; }
		else { toWrite += "\n"; } 
		
		toWrite += author + "\n";
		toWrite += date + "\n";
		toWrite += summary + "\n";
		
		try {
	           Files.writeString(Paths.get("./objects/" + GitUtils.StringToSha(toWrite) + ".txt"), toWrite, StandardCharsets.ISO_8859_1);
	    } catch (IOException e) {
	           e.printStackTrace();
	    }
		
		//I tested this and it works you can trust me
	}
	
}
