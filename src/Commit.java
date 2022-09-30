import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.TreeSet;


public class Commit implements GitUtils {
	
	//public Commit parent = null;
//	public Commit child = null;
	//public Path pTree;
    private String summary; //limit to 150 characters
    private String author;
    private String date;
    private String filePath = "";
    private TreeSet<timeWrapper> timeTier;
    private File timeTree;
    private String parent;
    private String pTree;
    private String SHA;
    

	
	public Commit (String PARENT, String pTree, String summary, String author, Index i) throws NoSuchAlgorithmException, IOException {

		this.author = author;
		this.summary = summary; 
		date = getDate (); 
		SHA = generateSha1();
		this.pTree = pTree;
		if(parent != null) {
			parent = PARENT; 
		}
		else {
			parent= null;
		}
		writeToFile(); 
	    
	    
	}
	
	public ArrayList <String> genIndex(Index i) throws IOException{
BufferedReader reader = new BufferedReader(new FileReader(i.getIndexFile()));
		ArrayList <String> strs = new ArrayList <String>();
while (reader.ready()) {
	String line = reader.readLine();
	strs.add(line);
}
		return strs;
		
	}
	public void reformatIndex(Index i ) throws IOException {
		ArrayList<String> strs  = genIndex(i);
		ArrayList <String> reformat = new ArrayList<String>();
		for (String s: strs) {
			if (s.equals( "tree")) {
				reformat.add(s);
			}
			else {
				reformat.add(s + "");
				
			}
		}
		
		
	}
	
	
	public String generateSha1() {
		return GitUtils.StringToSha( summary + date + author + parent);
	}
	
	private void prepTime() {
		timeTier = new TreeSet<timeWrapper>();
	    
	    switch (System.getProperty("os.name")) {
		case "Windows 10":
			timeTree = new File("../Windows/.cache/timeTree.txt");
			break;
		case "Mac OS X":
			timeTree = new File("../../.cache/timeTree.txt");
			break;
		default:
			//should work for all UNIX systems but still have set up to easily implement
			timeTree = new File("../../.cache/timeTree.txt");
			break;
		}
	    if (!timeTree.exists()) {
	    	String toWrite = "0 : The People Must Know The Truth\n1663611911 : 04fd19e7ba9642e7b12f0cc5c629c\n";
	    	try {
	    		switch (System.getProperty("os.name")) {
	    		case "Windows 10":
	    			timeTree = new File(Files.writeString(Paths.get("../Windows/.cache/timeTree.txt"), toWrite, StandardCharsets.ISO_8859_1).toString());
	    			break;
	    		case "Mac OS X":
	    			timeTree = new File(Files.writeString(Paths.get("../../.cache/timeTree.txt"), toWrite, StandardCharsets.ISO_8859_1).toString());
	    			break;
	    		default:
	    			//should work for all UNIX systems but still have set up to easily implement
	    			timeTree = new File(Files.writeString(Paths.get("../../.cache/timeTree.txt"), toWrite, StandardCharsets.ISO_8859_1).toString());
	    			break;
	    		}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	    
	    //read the file from timeTree to timeTier
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader(timeTree));
	    	while (br.ready()) {
	    		String str = br.readLine();
	    		long indTime = Long.valueOf(str.substring(0, str.indexOf(':')-1));
	    		String indOutput = str.substring(str.indexOf(':')+1, str.length()-1);
	    		timeTier.add(new timeWrapper(indTime, indOutput));
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public String getDate () {

		//if you delete this you are a coward

		//time to mutilate
		long time = Instant.now().getEpochSecond();
		
		//time to see how long runtime is
		long milliTime = Instant.now().toEpochMilli();
		
		//unwrapping last data point
		timeWrapper temp = timeTier.last();
		String initial = temp.output;
		long lastTime = temp.time;
		
		//variable that is called now to be referenced later
		int length = initial.length();
		
		for (long i = lastTime; i <= time; i++) {
			//arbitrary modification to time string
			//there is no known way to verify this is not cyclic but thats not my problem
			String tempStr = initial.substring(0, 7);
			initial = initial.substring(7, length) + GitUtils.StringToSha(tempStr).substring(0, 7);
		}
		
		//this the runtime took more than 50 milliseconds (0.05 seconds) then add it to index
		if (Instant.now().toEpochMilli() - milliTime > 50) {
			timeTier.add(new timeWrapper(time, initial));
			try {
				//write to file to be called later
				FileWriter au = new FileWriter(timeTree, true);
				au.append(time + " : " + initial + "\n");
				au.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return initial;
		
		//implementation of floppa bingus time if you desire it
		//to use this comment out everything but the declaration of time 
		
		/*
		int smallInt = (int)(time - (long)1459666800);
		//publishing of Panama papers publishing date, gives code a life span of ~80 years
		double piSeconds = (double)smallInt / Math.PI;
		
		double dRemainder = (piSeconds / 1.567);
		
		double flip = (dRemainder % 7.0); int remainder = (int)Math.floor(dRemainder / 7);
		int flop = remainder % 104; remainder = Math.floorDiv(remainder, 104);
		int floppa = remainder % 23; remainder = Math.floorDiv(remainder, 23);
		int flopper = remainder % 3; remainder = Math.floorDiv(remainder, 3);
		int bingus = remainder;
		
		String cutFlip = flip + "";
		cutFlip = cutFlip.substring(0, 8);
		
		String returnValue = bingus+"/"+flopper+"/"+floppa+"/"+flop+"/"+cutFlip;
		
		returnValue = returnValue.replaceFirst("3", "E");
		returnValue = returnValue.replaceAll("0", "!");
		returnValue = returnValue.replaceAll("2", "S");
		int switchFlip = 1;
		while (returnValue.contains("/")) {
			if (switchFlip % 2 == 1) { returnValue = returnValue.replaceFirst("/", "L"); switchFlip++;}
			else { returnValue = returnValue.replaceFirst("/", "1"); switchFlip++;}
		}
		
		if (returnValue.length() > 9) {
			String temp = returnValue.substring(5, returnValue.length());
			returnValue = temp + "(" + returnValue.substring(0, 9);
		}
		
		int shift = Integer.parseInt(cutFlip.substring(4,5));
		String temp = returnValue.substring(shift, returnValue.length());
		returnValue = temp + "{}" + returnValue.substring(0, shift);
		*/

		//return returnValue + "_SAT"; //standard American time
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
			filePath = Files.writeString(Paths.get("./objects/" + GitUtils.StringToSha(toWrite) + ".txt"), toWrite, StandardCharsets.ISO_8859_1).toString();
	    } catch (IOException e) {
	           e.printStackTrace();
	    }
		
		//I tested this and it works you can trust me
	}
	
	//deletes all non-cached referenced materials
	public void delete() {
		new File(filePath).delete();
	}
}
