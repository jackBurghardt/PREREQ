import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.*;

public class IndexTester {

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		Path p = Paths.get("textTESTER.txt");
	       try {
	           Files.writeString(p, "line 1\nline 2\nline 3", StandardCharsets.ISO_8859_1);
	       } catch (IOException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	       }
			File d = new File ("index.txt");
			d.delete();
			File k = new File("./objects/2846a43bef88668a42e41c2842f76a74e828432d.zip");
			k.delete();
			File f =new File("./objects");
			f.delete();
			Path h = Paths.get("testing2.txt");
		       try {
		           Files.writeString(h, "oihughgcghjv\nuytfugiyt\nhfxghcyuigyftdhfg\nhugyfhtfyugijhcfh", StandardCharsets.ISO_8859_1);
		       } catch (IOException e) {
		           // TODO Auto-generated catch block
		           e.printStackTrace();
		       }
		       
		       Path l = Paths.get("testing3.txt");
		       try {
		           Files.writeString(l, "dtfuygyfhfyuigychggu", StandardCharsets.ISO_8859_1);
		       } catch (IOException e) {
		           // TODO Auto-generated catch block
		           e.printStackTrace();
		       }
		       
		       Path q = Paths.get("testing4.txt");
		       try {
		           Files.writeString(q, "utdutiutdcutfutycyfcyycycycycycycycycycycyy", StandardCharsets.ISO_8859_1);
		       } catch (IOException e) {
		           // TODO Auto-generated catch block
		           e.printStackTrace();
		       }
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		Path p = Paths.get("textTESTER.txt");
		Files.delete(p);
		Path h = Paths.get("testing2.txt");
		Files.delete(h);
		Path l = Paths.get("testing3.txt");
		Files.delete(l);
		Path po = Paths.get("testing4.txt");
		Files.delete(po);
		Path p2o = Paths.get("index.txt");
		Files.delete(p2o);
		Path objb = Paths.get("objects");
		Files.delete(objb);
		
	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		setUpBeforeClass();
		Index i = new Index();
		File obj = new File("objects");
		File indxe = new File("index.txt");
		assertTrue(obj.exists()); //checks if objects folder was made
		assertTrue(indxe.exists()); //checks if index file was made
		assertTrue(i.addBlob("textTESTER.txt")); //checks if the adding a blob works (BlobTester.java already tests if blob works)
		FileReader fr = new FileReader(indxe);
		BufferedReader br = new BufferedReader(fr);
		String str="";
		while(br.ready()) {
			str = br.readLine();
		}
		br.close();
		fr.close();
		assertEquals(str, "textTESTER.txt : 2846a43bef88668a42e41c2842f76a74e828432d");//checks if index is updated
		assertTrue(i.addBlob("testing2.txt"));//checks adding a second blob
		
		FileReader fr2 = new FileReader(indxe);
		BufferedReader br2 = new BufferedReader(fr2);
		String str2="";
		while(br2.ready()) {
			str2 = str2 +br2.readLine();
		}
		br2.close();
		fr2.close();
		assertEquals(str2, "textTESTER.txt : 2846a43bef88668a42e41c2842f76a74e828432dtesting2.txt : 68f57a0fd01deb21ca8d4afbcd64897f181ef59f");//checks if index is updated with the second blob
		assertTrue(i.addBlob("testing3.txt"));
		assertTrue(i.addBlob("testing4.txt"));//two more blobs added
		assertFalse(i.addBlob("testing2.txt"));//checks adding a blob which already exists
		
		FileReader fr3 = new FileReader(indxe);
		BufferedReader br3 = new BufferedReader(fr3);
		String str3="";
		while(br3.ready()) {
			str3 = str3 +br3.readLine();
		}
		br3.close();
		fr3.close();
		assertEquals(str3, "textTESTER.txt : 2846a43bef88668a42e41c2842f76a74e828432dtesting2.txt : 68f57a0fd01deb21ca8d4afbcd64897f181ef59ftesting3.txt : ccdc9061f33609fe6ad66c18f37e88a6020dbd4btesting4.txt : d239c4b51a74c4fd66c8acda7c2be9f446a44f38");//checks index with multiple added
		assertTrue(i.removeBlob("testing3.txt"));//checks removing the blob
		File k = new File("./objects/ccdc9061f33609fe6ad66c18f37e88a6020dbd4btesting4.zip");
		assertFalse(k.exists());//checks that blob was deleted in objects
		
		FileReader fr4 = new FileReader(indxe);
		BufferedReader br4 = new BufferedReader(fr4);
		String str4="";
		while(br4.ready()) {
			str4 = str4 +br4.readLine();
		}
		br4.close();
		fr4.close();
		assertEquals(str4, "textTESTER.txt : 2846a43bef88668a42e41c2842f76a74e828432dtesting2.txt : 68f57a0fd01deb21ca8d4afbcd64897f181ef59ftesting4.txt : d239c4b51a74c4fd66c8acda7c2be9f446a44f38");//checks index with a blob removed
		assertFalse(i.removeBlob("testing3.txt"));//checks removing a blob that doesn't exist
		assertTrue(i.removeBlob("testing2.txt"));
		assertTrue(i.removeBlob("textTESTER.txt"));
		assertTrue(i.removeBlob("testing4.txt"));//removes the rest of the blobs to make deleting the objects folder easier in tearDown
		tearDownAfterClass();
	}

}
