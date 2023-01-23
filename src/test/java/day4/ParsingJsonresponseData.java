package day4;

import org.testng.Assert;
import org.testng.annotations.Test;
import  io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import static io.restassured.RestAssured.*;


public class ParsingJsonresponseData {
	
	@Test(priority=1)
	void testJsonResponse()
	{
		
		/*
		//Aproach1
		
		given()
			.contentType("ContentType.JSON")
		
		.when()
			.get("http://localhost:3000/store")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("book[5].title", equalTo("sell my pen here"));
		*/
		
		//Aproach2
		
		Response res= given()
			.contentType("ContentType.JSON")
			
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
		
		String bookName=res.jsonPath().get("book[5].title").toString();
		Assert.assertEquals(bookName, "sell my pen here");
	}
	
	@Test(priority=2)
	void testJsonResponseData()
	{
	
		//Aproach2
		
		Response res= given()
			.contentType("ContentType.JSON")
			
		.when()
			.get("http://localhost:3000/store");
		
		/*
		 Assert.assertEquals(res.getStatusCode(),200);
		 Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
		
		String bookName=res.jsonPath().get("book[5].title").toString();
		Assert.assertEquals(bookName, "sell my pen here");
		*/
		
		//using JSONObject class
		
		JSONObject jo= new JSONObject(res.asString()); //converting Response to Json object Type
		
		//print all titles of books
		/*
		for(int i=0;i<jo.getJSONArray("book").length();i++) 
		{
			String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(bookTitle);
		}
		*/
		
		
		//search of title of thr book in json validation
		boolean status= false;
		for(int i=0;i<jo.getJSONArray("book").length();i++) 
		{
			String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(bookTitle.equals("sell my pen here")) 
			{
				status=true;
				break;
			}
		}
	
		Assert.assertEquals(status, true);
		
		//validate total price of books
		double totalprice =0;
		for(int i=0;i<jo.getJSONArray("book").length();i++) 
		{
			String price=jo.getJSONArray("books").getJSONObject(i).get("price").toString();
			totalprice =totalprice + Double.parseDouble(price);

		}
		System.out.println("Total Price of books is : " +totalprice);
		
	}

}
