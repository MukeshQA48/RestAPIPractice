package com.qa.Client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient 
{
   //1.Get Method without Headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{
		
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse=httpClient.execute(httpget);
		return closeableHttpResponse;
	}
		
		/*//a.Status Code
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("StatusCode is ------>"+statusCode);
		
		//b.Json String
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson=new JSONObject(responseString);
		System.out.println("Response Json is"+responseJson);
		
		//c.allHeaders
		Header[] headersArray=closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders=new HashMap<String, String>();
		
		for(Header header:headersArray)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array--->"+allHeaders);*/
		
	//get method with headers
	public CloseableHttpResponse get(String url,HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
		
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);
		
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse=httpClient.execute(httpget);
		return closeableHttpResponse;
	}
	
	//post method with headers
	public CloseableHttpResponse post(String url,String entityString,HashMap<String, String> headerMap) throws IOException
	{
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url);
		
		httpPost.setEntity(new StringEntity(entityString));
		
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse=httpClient.execute(httpPost);
		return closeableHttpResponse;
		
	}
	
}	
	

