package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraBug {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 RestAssured.baseURI = "https://rahulshettyacademy-team0.atlassian.net";
		 String createIssueResponse = given()
		.header("Content-type","application/json")
		.header("Authorization","Basic dGVzdGZucDMyMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwNUh3eFNrYm1keGpFSzJmY20xZ0xOOTRRTjU0bk5pWXJhMURnSXJtM0I0eHU5RGtkSDdSTUhiNW5ZV1VkT29PVS1yZjVUVVJIcmYyTjU1bEY0THJGejUzRXhnSzc1alpuWXp5ZVZTMHR6SzFUVE5MQ1cxNV9xV0otbzJlUjF5NU4zclVqNTFOdUJrUXpKbnQ3emdiU3JiQmhvZUNqQ3dTbTljMXRVRHVRZEowPTM4ODRFNjJG")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Pages are not working - automation2\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}").log().all()
		.post("rest/api/3/issue")
		.then().log().all().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.get("id");
		System.out.println(issueId);
		
		//Add Attachment
		given()
		.pathParam("key", issueId)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic dGVzdGZucDMyMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwNUh3eFNrYm1keGpFSzJmY20xZ0xOOTRRTjU0bk5pWXJhMURnSXJtM0I0eHU5RGtkSDdSTUhiNW5ZV1VkT29PVS1yZjVUVVJIcmYyTjU1bEY0THJGejUzRXhnSzc1alpuWXp5ZVZTMHR6SzFUVE5MQ1cxNV9xV0otbzJlUjF5NU4zclVqNTFOdUJrUXpKbnQ3emdiU3JiQmhvZUNqQ3dTbTljMXRVRHVRZEowPTM4ODRFNjJG")
		.multiPart("file", new File("/Users/FNP_FIZA/Pictures/bug - nps2.PNG")).log().all()
		.post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		
		
		
	}

}
