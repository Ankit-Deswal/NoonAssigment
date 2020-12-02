/*
 * Author: Hemant Choudhari
 * summary: Used Test case for logging the status using Log4j
 * Date: 09/14/2019
 */

package com.noonapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;



public class TestBase {
	

	
	
	public Logger logger;
	
	@BeforeClass
	public void setup(){
		
		logger=Logger.getLogger(" Fetch token API");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
		
	}
	


}
