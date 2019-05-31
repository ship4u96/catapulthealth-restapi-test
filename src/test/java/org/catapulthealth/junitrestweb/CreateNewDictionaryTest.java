package org.catapulthealth.junitrestweb;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

public class CreateNewDictionaryTest {
	
	String Key = "Authorization";
	String Value = "Key to be entered here when testing";
	String RequestBody = "{ }";
	
	// Input - header, url, authentication key (API KEY) - key, value, empty body.
	// Expected output - status code 201 (created), header - content-type as application/json and body - {"id" : "dictionary-id"}
	
	// Test to check if given a header, url, and authentication key, check if it returns status code 201.
	@Test
	public void testStatusCodeofSuccessfulCreationOfNewDictionary()
	{
		given().
		header(Key, Value).
			header("Content-Type","application/json").
			body(RequestBody).
		when().
			post("https://dictionary.iachieved.it/dictionary").
		then().
			assertThat().
			statusCode(201);	
	}
	
		//Test to check if given header, url but invalid authorization key,
		// it should return status code 401 - Unauthorized.
		@Test
		public void testStatusCodeOfUnauthorizedKey()
		{
			given().
			header("unkownuname", "unkownpwd").
				header("Content-Type","application/json").
				body(RequestBody).
			when().
				post("https://dictionary.iachieved.it/dictionary").
			then().
				assertThat().
				statusCode(401);	
		}
	
	// Test to check if it returns the status code 201 with the selected content-type under header. 
	// Here,the content-type is application/json.
	@Test
	public void testHeaders()
	{
		given().
		header("Content-Type","application/json").
		header(Key, Value).
		body(RequestBody).
			when().
			post("https://dictionary.iachieved.it/dictionary").
		then().
			statusCode(201).
		and().
		 assertThat().
			header("Content-Type",equalTo("application/json"));
	}
	
	/* Test to check if it returns status code 201 on comparing the content-type as text/plain , 
	which was different from the input requested in the POST request. */
	
	@Test
	public void testHeader()
	{
		given().
		header("Content-Type","application/json").
		header(Key, Value).
		body(RequestBody).
			when().
			post("https://dictionary.iachieved.it/dictionary").
		then().
			statusCode(201).
		and().
		 assertThat().
			header("Content-Type",equalTo("text/plain"));
	}
	
	// observation : Header - Content-type is used to mention the type of response required i.e. in json or text. 
	// It does not fail the test.
}
