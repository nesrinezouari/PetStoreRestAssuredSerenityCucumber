package adidas.communs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ApplicationProperties;

public class BaseAPI {

	ApplicationProperties appProps = new ApplicationProperties();
	String Apipropfilename = "TestApi.json";

	public BaseAPI(String env) {

		RestAssured.baseURI = (String) appProps.GetDataByKeyFromJsonArrayFile(env, "endpointURL", Apipropfilename);

	}
	/**
	 * Common specification for request
	 * 
	 * @return
	 */
	public RequestSpecification getCommonSpec() {
		RequestSpecification rSpec = RestAssured.given();
		rSpec.header("Accept", "application/json");
		rSpec.contentType(ContentType.JSON);
		return rSpec;
	}
	/**
	 * 
	 * @param rspec
	 * @param path
	 * @return
	 */
	public Response getApiResponse(RequestSpecification rspec, String path) {
		Response response = rspec.basePath(path).get();
		return response;
	}

	/**
	 * 
	 * @param path
	 * @param key
	 * @param value
	 * @return
	 */

	public Response getApiResponseWithQueryParams(String path, String key, String value) {
		RequestSpecification rspec = getCommonSpec();

		System.out.println(key + " = " + value);
		rspec.param(key, value);

		Response response = rspec.basePath(path).get();

		return response;
	}

	/**
	 * 
	 * @param response
	 * @return
	 */

	public int getResponseSize(Response response) {

		JSONArray jsonArr = new JSONArray(response.getBody().asString());
		int size = jsonArr.length();

		return size;
	}

	/**
	 *
	 * @param response
	 * @return response status
	 */
	public int getResponseStatus(Response response) {

		return response.statusCode();
	}

	/**
	 *
	 * @param response
	 * @return desc
	 */
	public String getResponseDescription(Response response) {
		String desc = response.getBody().prettyPrint();

		return desc;
	}

	/**
	 *
	 * @param token
	 * @param path
	 * @return
	 */
	public Response postApiResponse(String path, String fileJsonDataName) {

		JSONObject jsonBody = generateStringFromResource(fileJsonDataName);

		Response response = getCommonSpec().body(jsonBody).when().post(path);

		return response;

	}
	/**
	 * 
	 * @param path
	 * @param jsonobj
	 * @return
	 */
	public Response putApiResponse(String path, String jsonobj) {

		RequestSpecification rspec = getCommonSpec();

		Response response = rspec.body(jsonobj).when().put(path);

		return response;
	}
	
	/**
	 * 
	 * @param path_params we consider that delete can be with id or any other combination in the future;  path param should be separated with , on feature file sample id=22333,status=sold,
	 *  means we have Map of two parameter we should parse with getMapFromString method to separe them 
	 * @param path
	 * @return
	 */
	public Response deleteApiResponseWithPathParams(String path_params, String path) {
		RequestSpecification rspec = getCommonSpec();
		rspec.header("api_key", "special-key");
		Map<String, Object> path_params_mapped = getMapFromString(path_params);

		Iterator it = path_params_mapped.entrySet().iterator();

		String pathParams = "";
		while (it.hasNext()) {

			Map.Entry<String, String> entry = (Map.Entry) it.next();

			System.out.println(entry.getKey() + " = " + entry.getValue());
			rspec.pathParam(entry.getKey(), entry.getValue());

			pathParams = "/{".concat(entry.getKey()).concat("}");
		}

		path = path.concat(pathParams);
		Response response = rspec.basePath(path).delete();

		return response;
	}

	/**
	 * this method dedicated to Read Json File and Return JsonObject 
	 * @param filename
	 * @return
	 * @return
	 */
	public JSONObject generateStringFromResource(String filename) {
		return appProps.ReadJsonFile(filename);
	}

	public JSONObject UpdateDatafromJsonFile(String jsonDataFileName, String key, String value) {
		JSONObject petdata = appProps.ReadJsonFile(jsonDataFileName);
		petdata.put(key, value);
		return petdata;
	}

	public JSONObject UpdateDatafromJsonObject(JSONObject petObj, String key,
			String value) {

		petObj.put(key, value);
		return petObj;
	}

	

	public Object getElementFromResponse(Response response, String key) {

		JSONParser parser = new JSONParser();
		Object jsonData = null;
		try {
			jsonData = parser.parse(response.getBody().asString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		org.json.simple.JSONObject jsonDataObj = (org.json.simple.JSONObject) jsonData;
		Object value = jsonDataObj.get(key);

		return value;

	}

	/**
	 * 
	 * @param path_params
	 * @return
	 */

	private Map<String, Object> getMapFromString(String path_params) {

		Map<String, Object> MapParams = new HashMap<String, Object>();

		String[] params = path_params.split(",");

		for (String param : params) {
			String[] paramKeyValue = param.split("=");
			String key = paramKeyValue[0];

			MapParams.put(key, (paramKeyValue[1]));
		}

		return MapParams;
	}

}
