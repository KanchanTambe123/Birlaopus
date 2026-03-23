@SCOPE1
Feature: To Validate Painting Service Request Form(Homepage Search Form)

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify Painting Service Request is submitted successfully by a logged-in user with valid data
    And User clicks on the Sign up for free button
    And User enters a valid pin code "<PinCode>" in the Painting Service Form
    And User clicks on the Submit button in the Painting Service Form
    And User should see the acknowledgment message after successful submission

    Examples: 
      | PinCode |
      |  400703 |

  #----------------------------------Scenario 2----------------------------------#
  Scenario Outline: To verify error message is displayed for an invalid pin code for a logged-in user
    And User clicks on the Sign up for free button
    And User enters an invalid pin code "<PinCode>" in the Painting Service Form
    And User clicks on the Submit button in the Painting Service Form
    Then 
    An error message should be displayed for invalid pin code "Please enter a valid Pincode"

    Examples: 
      | PinCode |
      |     123 |

  #----------------------------------Scenario 3---------------------------------------------------->
  Scenario Outline: To verify validation messages when mandatory fields are empty in Painting Service Request Form
    And User clicks on the Sign up for free button
    And User clicks on the Submit button in the Painting Service Form
    And validation message for Pincode empty input fields should get displayed "This field is required"

  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify that the Back button works after entering form data in the Painting Service Request form
    And User clicks on the Sign up for free button
    And User clicks on the Back button
    Then User should be navigated to the previous page

  