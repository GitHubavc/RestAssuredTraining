package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.Pojo_Postrequest;

//Pojo --serialization-> Json object--deserialiazation-> POJO

public class SeriaizationDeserialization {

	//POJO ----->JSON (Serialization)  
	//@Test
	void convertPojoToJson() throws JsonProcessingException {
		

		//created ajava object using pojo class
		Student stypojo = new Student ();
		
stypojo.setName("Scott");
stypojo.setLocation("France");
stypojo.setPhone("123456");
		
		String courseArr[]= {"C","C++"};
		stypojo.setCourses(courseArr);
		
		
		//Convert Java Object to Json Object
		
		ObjectMapper objMapper= new ObjectMapper();
		
		
		String jsondata= objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stypojo);

		System.out.println(jsondata);
	}
	
	
	//JSON ----->POJO (De-Serialization)  
		@Test
		void convertJsonToPojo() throws JsonProcessingException {
			
			String jsondata= "\n"
					+ "  \"name\" : \"Scott\",\n"
					+ "  \"location\" : \"France\",\n"
					+ "  \"phone\" : \"123456\",\n"
					+ "  \"courses\" : [ \"C\", \"C++\" ]\n"
					+ "}";
			
			//Convert Json  Object to java Object
			
			ObjectMapper objMapper= new ObjectMapper();
			
			Student stupojo=objMapper.readValue(jsondata, Student.class);// json--> pojo
			
			System.out.println("Name " +stupojo.getName());
			System.out.println("Location " +stupojo.getLocation());
			System.out.println("Phone Number " +stupojo.getPhone());
			System.out.println("Course 1 "  +stupojo.getCourses()[0]);
			System.out.println("Course 2 " +stupojo.getCourses()[1]);
			
			

		
		}
}
