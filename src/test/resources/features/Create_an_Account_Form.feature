@module
Feature: To Validate Create an Account(Account Registration) Functionality(Form)

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    Then User clicks on the Create an Account option

  #----------------------------------1---------------------------------------------------->
  Scenario Outline: To verify Create an Account functionality with mandatory fields and valid data
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    And User enters valid OTP and clicks on the Verify OTP button
    #And User enters bypass OTP and clicks on the Verify OTP button
    Then User enters valid first name
    And User enters valid last name
    Then User clicks on the Save Details button
    #And User should see the welcome message with name
    And the user should be welcomed with their name and see the Go to my profile button.
    #When User clicks on the profile icon
    #And User clicks on the delete account option
    #Then User should see a popup with the message Are you sure you want to delete your account? "<answer>"
    #And User enters valid OTP on profile page and clicks on the Verify OTP button
    
    Examples: 
      | answer |
      | Yes    |
    
  #----------------------------------2---------------------------------------------------->
  Scenario Outline: To verify Create an Account functionality with duplicate mobile number
    And User enters an already registered mobile number on the Create an Account page
    And User clicks on the Create an Account button
    Then User should see an error message indicating the User already exists "User already exists!!!"
@test
  #----------------------------------3---------------------------------------------------->
  Scenario Outline: To verify Create an Account functionality with all mandatory fields empty
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    #And User enters valid OTP and clicks on the Verify OTP button
    And User enters bypass OTP and clicks on the Verify OTP button
    Then User clicks on the Save Details button
    Then User should see "This field is required" error message for all mandatory fields
