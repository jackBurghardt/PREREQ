import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class treeTester {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub
//		blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f
//		blob : 01d82591292494afd1602d175e165f94992f6f5f
//		blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83
//		tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b
//		tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976
		Index b = new Index();
		b.addBlob("perplexing.txt");
		b.addBlob("crankyKong.txt");
		ArrayList<String> alisted = new ArrayList<String>();
		alisted.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		alisted.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		alisted.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		alisted.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		alisted.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		Tree branch = new Tree(alisted);
		System.out.println(branch.getBigString());
		System.out.println("SHA1 String: " + branch.getShawed());
		
	}

}
