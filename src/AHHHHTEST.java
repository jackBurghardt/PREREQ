

	

	import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

	public class AHHHHTEST {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
			
			Commit c1 = new Commit (null, "com one", "your mother");
			Commit c2 = new Commit (c1, "com  two", "yourself");
			System.out.println("prim commit child" + c1.Hash());
			Commit c3 = new Commit ( c2, "com 3", "Dr.Who himself");
			Commit c4 = new Commit(null, "shamamamamamamamlama", "moon Night");
			
		}
	}


