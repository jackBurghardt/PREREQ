import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;


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
		if (summary.length() > 150) {
			summary = summary.substring(0, 150);
		}
	    this.author = author;
	    date = getDate();
	    writeToFile();
	}
	
	
	public String generateSha1() {
		String toSha = pTree.toString() + summary;
		return GitUtils.StringToSha(toSha);
	}
	
	public String getDate () {
		//starts with unix time
		//count from march 3rd 2021 instead of 1970
		//comprised of various compenents that sum to xxxx/xx/x/xx/xxx.xxx
		//seconds are converted to pi seconds
		//1.567 pi seconds are a one flip can be decimal (limited to 8 characters)
		//7 flips are a flop
		//104 flops are a flopper
		//23 floppers are a bingus
		//converted to bingus/flopper/flop/flip.remander
		//first 3 is replaced by E
		//all 0 are replaced with !
		//2 becomes S
		//every other / is L or 1
		//adds ( to end and moves last 10 digits to front
		//append "_SAT"
		

		long allahWillGuideMe = Instant.now().getEpochSecond();
		int smallInt = (int)(allahWillGuideMe - (long)1614775733); //this gives me around 80 years of working code
		double piSeconds = (double)smallInt / Math.PI;
		
		double dRemainder = (piSeconds / 1.567);
		
		double flip = (dRemainder % 7.0); int remainder = (int)Math.floor(dRemainder / 7);
		int flop = remainder % 104; remainder = Math.floorDiv(remainder, 104);
		int flopper = remainder % 23; remainder = Math.floorDiv(remainder, 23);
		int bingus = remainder;
		
		String cutFlip = flip + "";
		cutFlip = cutFlip.substring(0, 8);
		
		String returnValue = bingus+"/"+flopper+"/"+flop+"/"+cutFlip;
		
		returnValue = returnValue.replaceFirst("3", "E");
		returnValue = returnValue.replaceAll("0", "!");
		returnValue = returnValue.replaceAll("2", "S");
		int switchFlip = 1;
		while (returnValue.contains("/")) {
			if (switchFlip % 2 == 1) { returnValue = returnValue.replaceFirst("/", "L"); }
			else { returnValue = returnValue.replaceFirst("/", "l"); }
		}
		
		if (returnValue.length() > 9) {
			String temp = returnValue.substring(5, returnValue.length());
			returnValue = temp + "(" + returnValue.substring(0, 9);
		}
		
		//return String.valueOf(allahWillGuideMe); //gets the time in the most gangster way
		return returnValue + "_SAT";
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
