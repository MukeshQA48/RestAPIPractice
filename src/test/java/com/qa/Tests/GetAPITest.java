package com.qa.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.Client.RestClient;
import com.qa.Util.TestUtil;

public class GetAPITest extends TestBase
{
   TestBase testBase;
   String serviceURL;
   String apiURL;
   String url;
   RestClient restClient;
   CloseableHttpResponse closeableHttpResponse;
   String responseString;
   JSONObject responseJson;
   Header[] headersArray;
	
	@BeforeMethod
	public void	setUp() 
	{
	  testBase=new TestBase();
	  serviceURL=props.getProperty("URL");
	  apiURL=props.getProperty("serviceURL");
	 
	  url=serviceURL+apiURL;
	 
	}
	
	@Test(priority = 1)
	public void getApiTestWithoutHeaders() throws ClientProtocolException, IOException
	{
		 restClient=new RestClient();
		 closeableHttpResponse=restClient.get(url);
		 
		 //a.Status Code
			int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
			System.out.println("StatusCode is ------>"+statusCode);
			
			Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
			
			//b.Json String
			responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
			
			responseJson=new JSONObject(responseString);
			System.out.println("Response Json is"+responseJson);
			
			//checking json attributes using Assert Statement
			//1.single statement
			String perPageValue=TestUtil.getValueByJPath(responseJson, "/per_page");
			System.out.println("Value of perpage is ------>"+perPageValue);
			Assert.assertEquals(Integer.parseInt(perPageValue), per_Page);
			
			String totalPages=TestUtil.getValueByJPath(responseJson, "/total_pages");
			System.out.println("Value of Total Pages----->"+totalPages);
			Assert.assertEquals(Integer.parseInt(totalPages), total_Pages);
			
			//2.Multiple Statements
			String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
			String email = TestUtil.getValueByJPath(responseJson, "/data[1]/email");
			String firstName = TestUtil.getValueByJPath(responseJson, "/data[2]/first_name");
			String lastname = TestUtil.getValueByJPath(responseJson, "/data[3]/first_name");
			
			System.out.println(id);
			System.out.println(email);
			System.out.println(firstName);
			System.out.println(lastname);

			
			//c.allHeaders
			Header[] headersArray=closeableHttpResponse.getAllHeaders();
			HashMap<String, String> allHeaders=new HashMap<String, String>();
			
			for(Header header:headersArray)
			{
				allHeaders.put(header.getName(), header.getValue());
			}
			System.out.println("Headers Array--->"+allHeaders);
		 
		 
	}
	/*@Test(priority = 2)
	public void getApiTestWithHeaders() throws ClientProtocolException, IOException
	{
		 restClient=new RestClient();
		 closeableHttpResponse=restClient.get(url);
		 
		 HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("User-Agent", "PostmanRuntime/7.20.1");
		 
		 //a.Status Code
			int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
			System.out.println("StatusCode is ------>"+statusCode);
			
			Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
			
			//b.Json String
			responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
			
			responseJson=new JSONObject(responseString);
			System.out.println("Response Json is"+responseJson);
			
			//checking json attributes using Assert Statement
			//1.single statement
			String perPageValue=TestUtil.getValueByJPath(responseJson, "/per_page");
			System.out.println("Value of perpage is ------>"+perPageValue);
			Assert.assertEquals(Integer.parseInt(perPageValue), per_Page);
			
			String totalPages=TestUtil.getValueByJPath(responseJson, "/total_pages");
			System.out.println("Value of Total Pages----->"+totalPages);
			Assert.assertEquals(Integer.parseInt(totalPages), total_Pages);
			
			//2.Multiple Statements
			String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
			String email = TestUtil.getValueByJPath(responseJson, "/data[1]/email");
			String firstName = TestUtil.getValueByJPath(responseJson, "/data[2]/first_name");
			String lastname = TestUtil.getValueByJPath(responseJson, "/data[3]/first_name");
			
			System.out.println(id);
			System.out.println(email);
			System.out.println(firstName);
			System.out.println(lastname);

			
			//c.allHeaders
			Header[] headersArray=closeableHttpResponse.getAllHeaders();
			HashMap<String, String> allHeaders=new HashMap<String, String>();
			
			for(Header header:headersArray)
			{
				allHeaders.put(header.getName(), header.getValue());
			}
			System.out.println("Headers Array--->"+allHeaders);
		 
		 
	}*/
	
	
	
}
