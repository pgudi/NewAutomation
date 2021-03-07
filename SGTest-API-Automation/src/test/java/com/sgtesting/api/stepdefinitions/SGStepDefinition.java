package com.sgtesting.api.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SGStepDefinition {
	public static String auth_token=null;
	public static RequestSpecification httpRequest=null;
	public static Response response=null;
	public static JsonPath jpath=null;
	public static int user_id=0;
	public static Logger log=Logger.getLogger("Api Automation Logs...");
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@Given("^I execute SignIn EndPoint$")
	public void I_execute_SignIn_EndPoint(DataTable endpoint)
	{
		try
		{
			log.info("I execute SignIn EndPoint");
			List<Map<String,String>> data=endpoint.asMaps(String.class, String.class);
			log.info("I execute SignIn EndPoint "+data.get(0).get("EndPointURL"));
			RestAssured.baseURI=data.get(0).get("EndPointURL");
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@When("^I submit the Post request for SignIn$")
	public void I_submit_the_Post_request_for_SignIn()
	{
		try
		{
			log.info("I submit the Post request for SignIn");
			httpRequest=RestAssured.given();
			JSONObject params=new JSONObject();
			params.put("email", "prabhakargudi@gmail.com");
			params.put("password", "India!@#");
					
			httpRequest.header("Content-Type", "application/json");
			String str=params.toJSONString();
			System.out.println(str);
			httpRequest.body(params.toJSONString());
			response=httpRequest.post();
			jpath=response.jsonPath();
			auth_token=jpath.getString("auth_token");
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@Then("^I should get 200 success status code$")
	public void I_should_get_200_success_status_code()
	{
		try
		{
			log.info("I should get 200 success status code");
			Assert.assertEquals(response.getStatusCode(), 200);
			Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@Given("^I execute CreateUser EndPoint$")
	public void I_execute_CreateUser_EndPoint(DataTable endpoint)
	{
		try
		{
			List<Map<String,String>> data=endpoint.asMaps(String.class, String.class);
			log.info("I execute CreateUser EndPoint "+data.get(0).get("EndPointURL"));
			RestAssured.baseURI=data.get(0).get("EndPointURL");
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@When("^I submit the Post request for CreateUser$")
	public void I_submit_the_Post_request_for_CreateUser()
	{
		try
		{
			log.info("I submit the Post request for CreateUser");
			httpRequest=RestAssured.given();
			
			JSONObject params=new JSONObject();
			params.put("email", "welcomeuser@gmail.com");
			params.put("first_name", "Welcome");
			params.put("last_name", "User1");
			params.put("phone_number", "9886012345");
			params.put("address", "RPC Layout");
			params.put("state", "Karnatka");
			params.put("zipcode", "560010");
					
			httpRequest.header("Content-Type", "application/json");
			String str=params.toJSONString();
			System.out.println(str);
			httpRequest.body(params.toJSONString());
			httpRequest.header("Authorization", auth_token);
			response=httpRequest.post();
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}

	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@Then("^I should get 201 success status code along with response body$")
	public void I_should_get_201_success_status_code_along_with_response_body()
	{
		try
		{
			log.info("I should get 201 success status code along with response body");
			Assert.assertEquals(response.getStatusCode(), 201);
			Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
			Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
			jpath=response.jsonPath();
			Assert.assertEquals(jpath.getString("first_name"), "Welcome");
			Assert.assertEquals(jpath.getString("last_name"), "User1");
			user_id=Integer.parseInt(jpath.getString("id"));
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@Given("^I execute DisplayUser EndPoint$")
	public void I_execute_DisplayUser_EndPoint(DataTable endpoint)
	{
		try
		{
			List<Map<String,String>> data=endpoint.asMaps(String.class, String.class);
			log.info("I execute DisplayUser EndPoint "+data.get(0).get("EndPointURL"));
			RestAssured.baseURI=data.get(0).get("EndPointURL");
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@When("^I submit the Get request for DisplayUser$")
	public void I_submit_the_Get_request_for_DisplayUser()
	{
		try
		{
			log.info("I submit the Get request for DisplayUser");
			httpRequest=RestAssured.given();
			httpRequest.header("Authorization", auth_token);
			response=httpRequest.get("/"+user_id);
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@Then("^I should get 200 success status code along with response body$")
	public void I_should_get_200_success_status_code_along_with_response_body()
	{
		try
		{
			log.info("I should get 200 success status code along with response body");
			Assert.assertEquals(response.getStatusCode(), 200);
			jpath=response.jsonPath();
			Assert.assertEquals(jpath.getString("first_name"), "Welcome");
			Assert.assertEquals(jpath.getString("last_name"), "User1");
			Assert.assertEquals(jpath.getString("email"), "welcomeuser@gmail.com");
			Assert.assertEquals(jpath.getString("phone_number"), "9886012345");
			Assert.assertEquals(jpath.getString("address"), "RPC Layout");
			Assert.assertEquals(jpath.getString("state"), "Karnatka");
			Assert.assertEquals(jpath.getString("zipcode"), "560010");
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@Given("^I execute ModifyUser EndPoint$")
	public void I_execute_ModifyUser_EndPoint(DataTable endpoint)
	{
		try
		{
			
			List<Map<String,String>> data=endpoint.asMaps(String.class, String.class);
			log.info("I execute ModifyUser EndPoint "+data.get(0).get("EndPointURL"));
			RestAssured.baseURI=data.get(0).get("EndPointURL");
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@When("^I submit the Put request for ModifyUser$")
	public void I_submit_the_Put_request_for_ModifyUser()
	{
		try
		{
			log.info("I submit the Put request for ModifyUser");
			httpRequest=RestAssured.given();
			
			JSONObject modifyUserParams=new JSONObject();
			modifyUserParams.put("email", "welcomeusernew@gmail.com");
			modifyUserParams.put("first_name", "WelcomeNew");
			modifyUserParams.put("last_name", "UserNew1");
			modifyUserParams.put("phone_number", "9886012345");
			modifyUserParams.put("address", "RPC Layout New");
			modifyUserParams.put("state", "Karnatka");
			modifyUserParams.put("zipcode", "560010");
					
			httpRequest.header("Content-Type", "application/json");
			String strupdated=modifyUserParams.toJSONString();
			System.out.println(strupdated);
			httpRequest.body(modifyUserParams.toJSONString());
			httpRequest.header("Content-Type", "application/json");
			httpRequest.header("Authorization", auth_token);
			response=httpRequest.put("/"+user_id);
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@Then("^I should get 200 success status code along with response body for ModifyUser$")
	public void I_should_get_200_success_status_code_along_with_response_body_for_ModifyUser()
	{
		try
		{
			log.info("I should get 200 success status code along with response body for ModifyUser");
			Assert.assertEquals(response.getStatusCode(), 202);
			jpath=response.jsonPath();
			Assert.assertEquals(jpath.getString("first_name"), "WelcomeNew");
			Assert.assertEquals(jpath.getString("last_name"), "UserNew1");
			Assert.assertEquals(jpath.getString("email"), "welcomeusernew@gmail.com");
			Assert.assertEquals(jpath.getString("phone_number"), "9886012345");
			Assert.assertEquals(jpath.getString("address"), "RPC Layout New");
			Assert.assertEquals(jpath.getString("state"), "Karnatka");
			Assert.assertEquals(jpath.getString("zipcode"), "560010");
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@Given("^I execute DeleteUser EndPoint$")
	public void I_execute_DeleteUser_EndPoint(DataTable endpoint)
	{
		try
		{
			
			List<Map<String,String>> data=endpoint.asMaps(String.class, String.class);
			log.info("I execute DeleteUser EndPoint "+data.get(0).get("EndPointURL"));
			RestAssured.baseURI=data.get(0).get("EndPointURL");
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@When("^I submit the delete request for DeleteUser$")
	public void I_submit_the_delete_request_for_DeleteUser()
	{
		try
		{
			log.info("I submit the delete request for DeleteUser");
			httpRequest=RestAssured.given();
			httpRequest.header("Content-Type", "application/json");
			httpRequest.header("Authorization", auth_token);
			Response response=httpRequest.delete("/"+user_id);
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
	
	/**
	 * Author:
	 * Reviewed By:
	 * Parameters:
	 * Purpose:
	 * Description:
	 */
	@Then("^I should get 202 success status code for delete$")
	public void I_should_get_202_success_status_code_for_delete()
	{
		try
		{
			log.info("I should get 202 success status code for delete");
			Assert.assertEquals(response.getStatusCode(), 202);
			Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		}catch(Exception e)
		{
			log.error("There is an exception has arised during execution :"+e);
		}
	}
}
