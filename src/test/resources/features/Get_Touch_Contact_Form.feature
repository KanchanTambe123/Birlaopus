@SCOPE1
Feature: To Validate Get in Touch Contact Form Subscription

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"

  #----------------------------------1---------------------------------------------------->
  Scenario Outline: To verify successful submission of the Get in Touch form with updated mandatory data for a logged-in user
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When User hovers over the Get in Touch popup
    And User clicks on the Get in Touch option
    And User updates the mandatory field Name
    And User updates the mandatory field Email
    And User updates the mandatory field Phone Number
    And User updates the mandatory field Pincode "<Pincode>"
    And User clicks submits the Get in Touch form
    And User should see the acknowledgment message after successful submission

    Examples: 
      | Pincode |
      |  400001 |

  #----------------------------------2---------------------------------------------------->
  Scenario Outline: To verify that a validation message is displayed when the user enters an invalid pincode on the Get in Touch form
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When User hovers over the Get in Touch popup
    And User clicks on the Get in Touch option
    And User updates the mandatory field Pincode "<Pincode>"
    And User clicks submits the Get in Touch form
    Then validation message for invalid pincode input field should get displayed "Please enter a valid Pincode"

    Examples: 
      | Pincode |
      |   40000 |

  #----------------------------------3---------------------------------------------------->
  Scenario Outline: To verify mandatory field validation messages on the Get in Touch form without login
    When User hovers over the Get in Touch popup
    And User clicks on the Get in Touch option
    And User clicks submits the Get in Touch form
    Then validation message for all input field should get displayed "This field is required"
