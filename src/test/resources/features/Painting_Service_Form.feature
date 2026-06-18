@Scope1
Feature: To Validate Short Lead Form (Painting Service)

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify Short Lead Form (Painting Service) Request is submitted successfully by a logged-in user with valid data
    And User clicks on the Sign up for free button
    And User enters a valid pin code "<PinCode>" in the Painting Service Form
    And User clicks on the Submit button in the Painting Service Form
    And User captures and validates API request and response for "lead/shortForm"

    Examples: 
      | PinCode |
      |  400703 |

  #---------------------------------- Scenario 1 ----------------------------------#pending
 Scenario Outline: To verify pop-up is displayed when an unserviceable PIN code is entered
 
    And User clicks on the Sign up for free button
    And User enters a unservicable pin code "<PinCode>" in the Painting Service Form
    And User clicks on the Submit button in the Painting Service Form
    And User captures and validates API request and response for "lead/shortForm"
   Then the unserviceable pin code message should be displayed "Coming soon Painting services are currently not available in your area"
    Examples: 
      | PinCode |
      |  111222 |
 
  #----------------------------------Scenario 2----------------------------------#
  Scenario Outline: To verify error message is displayed for an invalid pin code for a logged-in user
    And User clicks on the Sign up for free button
    And User enters an invalid pin code "<PinCode>" in the Painting Service Form
    And User clicks on the Submit button in the Painting Service Form
    Then An error message should be displayed for invalid pin code "Please enter a invalid Pincode"

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

    
    