import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class NilsinApplicationHelper {

    public static final String FILE_PROPERTIES = "nilsin.properties";
	public static final String BASE_PORT_PROPERTIES = "base.port";
	public static final int DEFAULT_PORT = 8080;
	
	public static Properties getPropertiesInDefaultFile() throws IOException, FileNotFoundException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("nilsin.properties"));
		return properties;
	}
}
