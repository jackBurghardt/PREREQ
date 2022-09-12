import java.io.IOException;

public class Main {

	public static void main (String [] hehe) {
		
		/*
		Blob a = new Blob ("perplexing.txt");
		try {
			System.out.println(a.fileToString());
		} catch (IOException e) {
			System.out.println("Cringe");
			System.out.println(e);
		}
		
		a.makeFile();
		*/
		
		Index b = new Index ();
		
		b.addBlob("perplexing.txt");
		b.addBlob("crankyKong.txt");
		
		//b.removeBlob("crankyKong.txt");
	}	
	
}
