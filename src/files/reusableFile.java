package files;

import io.restassured.path.json.JsonPath;

public class reusableFile {

	public static JsonPath rawToJson(String reponse) {
		
		JsonPath jas = new JsonPath(reponse);
		return jas;
	}
	
}
