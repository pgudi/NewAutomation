package com.sgtesting.api.stepdefinitions;

import org.hamcrest.Matchers;
import com.sgtesting.api.utility.Constants;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;

public class Initialize implements Constants{
	
	/**
	 * Created By:
	 * Created Date:
	 * Reviewed By:
	 * Test case ID:
	 * parameters:
	 * return type:
	 * Purpose:
	 * Description:
	 */
	@Before
	public void setUp()
	{
		try
		{
			ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		    resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
		    RestAssured.responseSpecification = resBuilder.build();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
