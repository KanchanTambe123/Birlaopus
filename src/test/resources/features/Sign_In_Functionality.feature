@Report
Feature: To Validate Sign-In (User Login)Functionality

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To Verify sign-in functionality with valid mobile number and OTP
    And User enters valid mobile number mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    And the User should successfully sign in

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To Verify sign-in form with invalid mobile number
    And User enters invalid mobile number "<mobile number>" on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    Then Validation message mobile number should get displayed to user "Please enter valid mobile number"

    Examples: 
      | mobile number |
      |         12345 |

  #---------------------------------- Scenario 3 ----------------------------------#
  Scenario Outline: To Verify sign-in form with empty mobile number field
    And User enters invalid mobile number "<mobile number>" on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    Then Validation message mobile number filed empty should get displayed to user "This field is required"

    Examples: 
      | mobile number |
      |               |

  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify user is able to log out successfully
    And User enters valid mobile number mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    And User clicks on the Go to my profile button
    When User clicks on the Sign Out button
    Then User should see a popup with the message Are you sure you want to logout? and selects "<answer>"
    Then User should be logged out successfully

    Examples: 
      | answer |
      | Yes    |
