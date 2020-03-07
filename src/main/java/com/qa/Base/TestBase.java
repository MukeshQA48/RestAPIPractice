package com.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase 
{
	
	public static int RESPONSE_STATUS_CODE_200=200;
	public static int RESPONSE_STATUS_CODE_500=500;
	public static int RESPONSE_STATUS_CODE_400=400;
	public static int RESPONSE_STATUS_CODE_401=200;
	public static int RESPONSE_STATUS_CODE_201=201;
	
	public static int per_Page=6;
	public static int total_Pages=2;

	public Properties props;
	public TestBase()
	{
		
		try {
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\Config\\Config.properties");
			props=new Properties();
			props.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
