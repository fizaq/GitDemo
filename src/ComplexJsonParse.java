import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

@Test
public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload.CoursePrice());
		
		//Counting the total number of courses
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//print purchsase amount
		
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmt);
		
		// To print title of first course
		//get method by default will pull up the string 
		// we can use either get or getString.
		
		String titleone = js.get("courses[0].title");
		System.out.println(titleone);
		System.out.println("-----------------------------------------------------------");
		//print all course titles and prices
		
		for(int i = 0 ; i< count ; i ++)
		{
			String coursetitles = js.getString("courses["+i+"].title");
			
			System.out.println(coursetitles);
			int coursesprice = js.getInt("courses["+i+"].price");
			System.out.println(coursesprice);
		
			
		}
		
		//Print no of copies sold by RPA course
		for(int i = 0 ; i< count ; i ++)
		{
		String coursetitles = js.getString("courses["+i+"].title");
		if(coursetitles.equals("RPA")) 
		{
			System.out.println("Copies sold by RPA " + js.getInt("courses["+i+"].copies"));
			break;
		}
		}
		
		//Verify that if sum of all course prices matchs with the Purchase prices.
		System.out.println("--------------------------------------------");
		int sum=0;
		int totalprice = 0;
		for(int i = 0 ; i< count ; i ++) 
		{
		
			int cprices = js.getInt("courses["+i+"].price");
			int ccopies = js.getInt("courses["+i+"].copies");
		    totalprice  = cprices * ccopies;
			
		}
		sum += totalprice;
		System.out.println(sum);
		int pAmt = js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(sum, pAmt);
		
		
	}

}
