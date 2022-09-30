

import java.io.FileNotFoundException;

public class TESTERFORB {
public static void main(String[] args) throws FileNotFoundException {
		
		Commit c1 = new Commit ("first commit", "one", null);
		Commit c2 = new Commit ("WEEEEE commit", "two", c1);
		System.out.println("prim commit child" + c1.Hash());
		Commit c3 = new Commit ("good mesure", "thirdCommit", c2);
		Commit c4 = new Commit("I ran out of stuff", "ooo", null);
		
	}
}
