import java.math.BigInteger;
import java.security.MessageDigest;

public interface GitUtils {
	
	public static String StringToSha (String inp) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1"); //generates sha1
			byte[] messageDigest = md.digest(inp.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 40) { hashtext = "0" + hashtext; }
			return hashtext;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	
}
