package org.catapulthealth.junitrestweb;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ModifykeypairvalueTest {
	
	/* Input - header, url, authentication key (API KEY) - key, value, 
	 body : {"value": "VALUE"}
	 Expected output - status code 201 (created), header - content-type as 
	 application/json */
	
	    String Key = "Authorization";
		String Value = "Key to be entered here when testing";
		String id = "449ae9b7-c14b-41de-a63a-0629ec4aa6ff";
		//URL : https://dictionary.iachieved.it/dictionary/id/keys/key
		String RequestBodyInput = "{\"value\" : \"Value\" + }";
	
		/* Test to check the status code in the response of the POST request in order to 
		create and modify the key value pair. Check if it returns 200 or 201?*/
		@Test
		public void testStatusCodeofModifyingKeyPairValue()
		{
			given().
				header("Content-Type","application/json").
				header(Key, Value).
				body(RequestBodyInput).
			when().
				post("https://dictionary.iachieved.it/dictionary/" + id + "/keys/Testkey").
			then().
				assertThat().
				statusCode(200);	
		}
		
		/* Test to check the status code in the response of the POST request in order to 
		create and modify the key value pair - Check if it returns 200 or 201?*/ 			
		@Test
		public void testStatusCodeVerifying()
		{
			given().
				header("Content-Type","application/json").
				header(Key, Value).
				body(RequestBodyInput).
			when().
				post("https://dictionary.iachieved.it/dictionary/"+ id +"/keys/Testkey").
			then().
				assertThat().
				statusCode(201);	
		}		
		
		// Test to check if the content-type changes in the response of the POST request.		
		@Test
		public void testVerifyContentType()
		{
			given().
				header("Content-Type","application/json").
				header(Key, Value).
				body(RequestBodyInput).
			when().
				post("https://dictionary.iachieved.it/dictionary/" + id +"/keys/Testkey").
			then().
				assertThat().
				statusCode(200).and().header("Content-Type",equalTo("application/json"));	
		}
		
		
		// Test to check the response on the invalid input of Authentication key on the POST request. 
		@Test
		public void testVerifyAuthorization()
		{
			given().
				header("Content-Type","application/json").
				header("unkownuname", "unkownpwd").
				body(RequestBodyInput).
			when().
				post("https://dictionary.iachieved.it/dictionary/"+ id +"/keys/Testkey").
			then().
				assertThat().
				statusCode(401).and().header("Content-Type",equalTo("text/plain"));	
		}
	}
		