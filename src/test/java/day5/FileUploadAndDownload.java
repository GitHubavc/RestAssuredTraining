package day5;

import org.testng.Assert;
import org.testng.annotations.Test;


import  io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

public class FileUploadAndDownload {
	
	//@Test
	void singleFileUpload()
	
	{
		File myfile= new File("/Users/amit/Downloads/Virtual Q.pdf");
				
		given()
		
		.multiPart("file",myfile)
		.contentType("multipart/form-data")
		 
		.when()
		 .post("http://localhost:8080/uploadFile")
		 
		 .then()
		  .statusCode(200)
		  .body("fileName", equalTo("Test1.txt"))
		  .log().all();
	}
	
	//@Test
	void multipleFileUpload()
	
	{
		File myfile= new File("/Users/amit/Downloads/Virtual Q.pdf");
		File myfile2= new File("/Users/amit/Downloads/Virtual Q2.pdf");
				
		given()
		
		.multiPart("files",myfile)
		.multiPart("files",myfile2)
		.contentType("multipart/form-data")
		 
		.when()
		 .post("http://localhost:8080/uploadFile")
		 
		 .then()
		  .statusCode(200)
		  .body("[0].fileName", equalTo("Test1.txt"))
		  .body("[1].fileName", equalTo("Test1.txt"))
		  .log().all();
	}
	
	@Test
	void fileDownload()
	{
		
		given()
		
		.when()
			.get("http://localhost:8080/downloadFile/test1.txt")
			
		.then()
			.statusCode(200)
			.log().all();
	}
	
	

}
