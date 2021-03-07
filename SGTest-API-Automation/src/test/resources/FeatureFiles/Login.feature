#Author: your.email@your.domain.com
# Created Date:
# Reviewed By:
#Purpose:
# Description :
Feature: Login functionality
Description: Verify the login functionality
Scenario: Login with valid credentials Using - Post Method
Given I execute SignIn EndPoint
|EndPointURL|
|https://enigmatic-meadow-39517.herokuapp.com/api/v1/students/sign_in|
When I submit the Post request for SignIn
Then I should get 200 success status code