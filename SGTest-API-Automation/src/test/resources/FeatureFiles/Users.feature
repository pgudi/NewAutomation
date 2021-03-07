#Author: your.email@your.domain.com
# Created Date:
# Reviewed By:
#Purpose:
# Description :
Feature: Users functionality
Description This feaure describes create/update/display/delete user functionalities.

Scenario: Login with valid credentials Using - Post Method
Given I execute SignIn EndPoint
|EndPointURL|
|https://enigmatic-meadow-39517.herokuapp.com/api/v1/students/sign_in|
When I submit the Post request for SignIn
Then I should get 200 success status code

Scenario: To verify Rest Service for Create User endpoint - Post Method
Given I execute CreateUser EndPoint
|EndPointURL|
|https://enigmatic-meadow-39517.herokuapp.com/api/v1/users|
When I submit the Post request for CreateUser
Then I should get 201 success status code along with response body

Scenario: To verify Rest Service for display User endpoint - Get Method
Given I execute DisplayUser EndPoint
|EndPointURL|
|https://enigmatic-meadow-39517.herokuapp.com/api/v1/users|
When I submit the Get request for DisplayUser
Then I should get 200 success status code along with response body

Scenario: To verify Rest Service for modify User endpoint - Put Method
Given I execute ModifyUser EndPoint
|EndPointURL|
|https://enigmatic-meadow-39517.herokuapp.com/api/v1/users|
When I submit the Put request for ModifyUser
Then I should get 200 success status code along with response body for ModifyUser

Scenario: To verify Rest Service for delete User endpoint - Delete Method
Given I execute DeleteUser EndPoint
|EndPointURL|
|https://enigmatic-meadow-39517.herokuapp.com/api/v1/users|
When I submit the delete request for DeleteUser
Then I should get 202 success status code for delete
