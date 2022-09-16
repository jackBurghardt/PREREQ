import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import java.io.File;
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

public class BlobTester {
	
	@BeforeAll
	//makes test files and objects folder required for just testing the blob class
	public static void setUpBeforeClass() throws Exception {
		File f =new File("objects");
		f.mkdir();
		Path p = Paths.get("textTESTER.txt");
	       try {
	           Files.writeString(p, "line 1\nline 2\nline 3", StandardCharsets.ISO_8859_1);
	       } catch (IOException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	       }
	}

	@AfterAll
	//deletes test files and objects folder
	public static void tearDownAfterClass() throws Exception {
		Path p = Paths.get("textTESTER.txt");
		Files.delete(p);
		File f = new File ("objects");
		f.delete();
	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		//only tests blob so it isnt 80%, indexTester does it all
		setUpBeforeClass();
		
		Blob blolkob = new Blob("textTESTER.txt");
		File f = new File("./objects/2846a43bef88668a42e41c2842f76a74e828432d.zip");
		assertTrue(f.exists());	//checks if the file was created
		assertTrue(blolkob.pop()); //checks if the file was deleted
		tearDownAfterClass();
		
	}

}
