
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

	public class AHHHHTEST {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
			Index index = new Index();
			
			//index.addBlob("index.txt");
			index.addBlob("hello.txt");
			index.addBlob("blobby.txt");
			
			Commit c1 = new Commit (null, "com one", "your mother");
			index.addBlob("two.txt");
			index.addBlob("five.txt");
			Commit c2 = new Commit (c1, "com  two", "yourself");
			index.addBlob("one.txt");
			index.addBlob("four.txt");
			Commit c3 = new Commit (c2, "com 3", "Dr.Who himself");
			index.addBlob("bye.txt");
			index.addBlob("six.txt");
			Commit c4 = new Commit(c3, "shamamamamamamamlama", "moon Night");
		
			
		}
	}


