package crazy.chapter7;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class SystemTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> envMap = System.getenv();
		for (String name : envMap.keySet()){
			System.out.println(name + " -->" + envMap.get(name) );
		}
		System.out.println(System.getenv("JAVA_HOME"));
		Properties properties = System.getProperties();
		properties.store(new FileOutputStream("props.txt"), "System Properties");
		System.out.println(System.getProperty("os.name"));
	
	}

}
