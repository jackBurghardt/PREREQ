import java.io.IOException;

public class Main {

	public static void main (String [] hehe) {
		Blob a = new Blob ("./perplexing.txt");
		Index b = new Index ();
		
		
		
		
		try {
			System.out.println(a.fileToString());
		} catch (IOException e) {
			System.out.println("Cringe");
			System.out.println(e);
		}
		
		a.makeFile();
		
		b.addBlob();
	}	
	
}
