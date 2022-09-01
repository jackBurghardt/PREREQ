
public class Main {

	public static void main (String [] args) {
		Blob a = new Blob ("/Users/evanstokdyk/eclipse-workspace/Prerequisites/src/perplexing.txt");
	
		byte[] inp = new byte[] {'b','c','g'};
		System.out.println(a.byteToHexSha(inp));
	}
}
