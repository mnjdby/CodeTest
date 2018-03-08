import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

	
	public static void main(String[] args) throws FileNotFoundException {    
	    File file=new File("/Users/nitish/.m2/repository/com/sun/xml/bind/jaxb-core/2.3.0/jaxb-api.jar");
	    System.out.println(file.exists());
	    Scanner scan=new Scanner(file);
	}
	
}
