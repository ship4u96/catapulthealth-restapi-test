package org.catapulthealth.junitrestweb;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class DeleteDictionaryTest {

	String Key = "Authorization";
	String Value = "Key to be entered here when testing";
	String id = "449ae9b7-c14b-41de-a63a-0629ec4aa6ff";
	
	/*Input - header, url, authentication key (API KEY) - key, value.
	 Expected output - status code 200 (OK), header - content-type as application/json.
	 OR Expected Output : On Invalid API KEY - Response : Status Code : 401 & Content-Type : Plain
	 OR Expected Output : On Wrong Input ID - Response : Status Code : 404 & Content-Type : Application/json */
	
	
	/* Test to check the response on performing POST request to delete the dictionary of particular ID.
	 The below test is to check if the key and id still returns in the response or not after deletion, 
	 It should return No Content as no ID should exist after deleting, but the test fails. 
	 It returns status code - 200 Ok, which explains that it still returns the ID and key after deletion. 
	 That is the defect */
	
	@Test
	public void testStatusCodeToCheckOnDeleteDictionary()
	{
		given().
			header("Content-Type","application/json").
			header(Key, Value).
		when().
			delete("https://dictionary.iachieved.it/dictionary/"+id).
		then().
			assertThat().
			statusCode(204);	
		// test fails on asserting statusCode(200) as expected statusCode. 
	}
	
	@Test
	public void testCheckIfKeyExistsAfterDeletionDictionary()
	{
		given().
			header("Content-Type","application/json").
			header(Key, Value).
		when().
			get("https://dictionary.iachieved.it/dictionary/" + id +"/keys").
		then().
			assertThat().
			statusCode(200);	
	}
	
	//Test to check the response on performing POST request & verifying if it accepts the request on invalid authentication (API key).
	//Check Authorization. 
	@Test
	public void testStatusCodeofUnauthorizedKey()
	{
		given().
			header("Content-Type","application/json").
			header("unkownuname", "unkownpwd").
		when().
			delete("https://dictionary.iachieved.it/dictionary/"+id).
		then().
			assertThat().
			statusCode(401);	
	}
	
	//Test to check the response on performing POST request with invalid input ID and valid API key.
	@Test
	public void testStatusCodeResourceNotFound()
	{
		given().
			header("Content-Type","application/json").
			header(Key, Value).
		when().
			delete("https://dictionary.iachieved.it/dictionary/449ae9b7-c14b-41de-a63a-0629ec4aa6f5").
		then().
			assertThat().
			statusCode(404);	
	}
	
	/* Test to check the content-type in the response of the POST request. 
	To validate if the content-type changes in the response based on the changes in the request.*/ 
		@Test
		public void testHeaders_WithContentTypeJson_AuthorizedKey()
		{
			given().
			header("Content-Type","application/json").
			header(Key, Value).
				when().
				delete("https://dictionary.iachieved.it/dictionary/"+id).
			then().
				statusCode(204).
			and().
			 assertThat().
				header("Content-Type",equalTo(null));
			
		}
		
		// Test to check the content-type in the response of the POST request when invalid API key in entered. 
		// To validate if the content-type changes in the response based on the changes in the request. 
		@Test
		public void testHeaders_WithContentTypeText_UnauthorizedKey()
		{
			given().
			header("Content-Type","application/json").
			header("unkownuname", "unkownpwd").
				when().
				delete("https://dictionary.iachieved.it/dictionary/"+id).
			then().
				statusCode(401).
			and().
			 assertThat().
				header("Content-Type",equalTo("text/plain"));
			
		}
		
		// Test to check the content-type in the response of the POST request when Resource is Not Found. 
		// To validate if the content-type changes in the response based on the changes in the request. 
		@Test
		public void testHeaders_WithContentTypeJson_AuthorizedKey_ResourceNotFound()
		{
			given().
			header("Content-Type","application/json").
			header(Key, Value).
				when().
				delete("https://dictionary.iachieved.it/dictionary/449ae9b7-c14b-41de-a63a-0629ec4aa6f5").
			then().
				statusCode(404).
			and().
			 assertThat().
				header("Content-Type",equalTo("application/json"));
			
		}
		
}
