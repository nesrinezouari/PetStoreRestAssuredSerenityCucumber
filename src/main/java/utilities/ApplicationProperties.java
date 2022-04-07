package utilities;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 
 * This class is the implementation of application properties of AUT
 */
public class ApplicationProperties {

	private String pathToProperties;
	private String pathToJson;
	private JSONObject jsonData;

	/* Path to env file and json file */
	private String pathToFilePropertiesEnv = getResourcePath() + File.separatorChar +"properties" + File.separatorChar ; 
	private String pathToJsonFile = getJsonPath() + File.separatorChar + "json" + File.separatorChar;

	public String getResourcePath() {
		if (pathToProperties == null) {
			String userDir = System.getProperty("user.dir");
			String projectResourcesPath = userDir.concat("/src/test/resources");
			File f = new File(projectResourcesPath);
			if (f.exists()) {
				pathToProperties = projectResourcesPath;
			} else {
				pathToProperties = userDir;
			}
		}

		return pathToProperties;
	}

	public String getJsonPath() {
		if (pathToJson == null) {
			String userDirectory = System.getProperty("user.dir");
			String projectJsonPath = userDirectory.concat("/src/main/java/models");
			File f = new File(projectJsonPath);
			if (f.exists()) {
				pathToJson = projectJsonPath;
			} else {
				pathToJson = userDirectory;
			}
		}

		return pathToJson;
	}

	/**	public String ReadJsonFile(String fileName) {
			try {
				jsonData = new String(Files.readAllBytes(Paths.get(pathToJsonFile + fileName)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return jsonData;
		}
	**/

	public JSONObject ReadJsonFile(String fileName) {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(pathToJsonFile + fileName));

			jsonData = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}

	public JSONObject ReadJsonFileEnv(String Filename) {
		JSONParser parser = new JSONParser();
     String pathToFilePropertiesEnv2= pathToFilePropertiesEnv.concat(Filename);
		try {
			Object obj = parser.parse(new FileReader(pathToFilePropertiesEnv2));

			jsonData = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}

	/**
	 *
	 * @param env
	 * @param key
	 * @return
	 */
	public Object GetDataByKeyFromJsonArrayFile(String env, String key,String filename) {
		JSONObject contFile = ReadJsonFileEnv(filename);
		JSONArray listEnv = (JSONArray) contFile.get(env);
		JSONObject params = (JSONObject) listEnv.get(0);
		Object value = params.get(key);

		return value;
	}

	/************
	 * This method will return value of key data
	 * @param jsonDataFileName
	 * @param Key
	 * @return
	 */
	public Object GetDataByKeyFromJsonFile(String jsonDataFileName, String Key) {
        Object value = ReadJsonFile(jsonDataFileName).get(Key);

		return value; 
	}
	
	
}