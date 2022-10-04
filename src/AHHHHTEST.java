

	

	import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

	public class AHHHHTEST {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
			Index index = new Index();
			
			index.addBlob("index.txt");
			index.addBlob("hello.txt");
			File x = new File ("popo");
	
			Commit c1 = new Commit (null, "com one", "your mother");
			Commit c2 = new Commit (c1, "com  two", "yourself");
			System.out.println("prim commit child" + c1.Hash());
			Commit c3 = new Commit (null, "com 3", "Dr.Who himself");
			Commit c4 = new Commit(null, "shamamamamamamamlama", "moon Night");
			
			
		}
	}


