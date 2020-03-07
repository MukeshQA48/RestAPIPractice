package com.qa.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.TestBase;
import com.qa.Client.RestClient;
import com.qa.Data.Users;

public class PostApiTest extends TestBase
{
	TestBase testBase;
	   String serviceURL;
	   String apiURL;
	   String url;
	   RestClient restClient;
	   CloseableHttpResponse closeableHttpResponse;
	   String responseString;
	   JSONObject responseJson;
	   
		
		@BeforeMethod
		public void	setUp() 
		{
		  testBase=new TestBase();
		  serviceURL=props.getProperty("URL");
		  apiURL=props.getProperty("serviceURL");
		 
		  url=serviceURL+apiURL;
		 
		}
		
		@Test
		public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException
		{
			restClient=new RestClient();
			HashMap<String, String> headerMap=new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
			//Jacksonapi
			ObjectMapper mapper=new ObjectMapper();
			Users users=new Users("morpheus","leader");//expected user object
			
			//object to json file
			mapper.writeValue(new File("D:\\RestApi\\RestAutomation\\src\\main\\java\\com\\qa\\Data\\Users.json"),users);
			
			//java object to json in string (Marshalling)
			String usersJsonString=mapper.writeValueAsString(users);
			System.out.println(usersJsonString);
			
			closeableHttpResponse=restClient.post(url, usersJsonString, headerMap);//call the api
			
			//validate the response from api
			//1.status code
			int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
			Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
			
			//2.json string
			String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
			
			JSONObject responseJson=new JSONObject(responseString);
			System.out.println("The response from api is"+responseJson);
			
			//3.json to javaObjects(unMarshalling)
			Users usersResObj = mapper.readValue(responseString, Users.class); //actual users object
			System.out.println(usersResObj);
			
			Assert.assertTrue(users.getName().equals(usersResObj.getName()));
			
			Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
			
			System.out.println(usersResObj.getId());
			System.out.println(usersResObj.getCreatedAt());
			
			
			
		}
}
