@Report
Feature: To Validate Store Locator Functionality

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"

  #----------------------------------1---------------------------------------------------->
  Scenario Outline: To verify store locator displays results for valid pincode
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When User hovers over the Get in Touch popup
    Then User clicks on the Find a Store option
    Then User enters a valid pincode "<pincode>"
    And User clicks on the submit button
    Then store locator results, including the store count text and filter dropdown, should be displayed successfully

    Examples: 
      | pincode |
      |  411033 |

  #----------------------------------2---------------------------------------------------->
  Scenario Outline: To verify Store Locator lead details submission with valid data
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When User hovers over the Get in Touch popup
    Then User clicks on the Find a Store option
    Then User enters a valid pincode "<pincode>"
    And User clicks on the submit button
    And User selects the first store and clicks the Get Number button
    Then User clicks on the continue button
    And User enters a valid email ID on the Store Locator lead details form
    Then User clicks on the submit button on lead details form
    And The confirmation message should be displayed successfully

    Examples: 
      | pincode |
      |  411033 |

  #----------------------------------3---------------------------------------------------->
  Scenario Outline: To verify validation message is displayed when user enters an invalid pincode(find store-without login)
    When User hovers over the Get in Touch popup
    Then User clicks on the Find a Store option
    Then User enters a valid pincode "<pincode>"
    And User clicks on the submit button
    Then validation message for invalid pincode input field should get displayed "Please enter a valid Pincode"

    Examples: 
      | pincode |
      |   41103 |

  #----------------------------------4---------------------------------------------------->
  Scenario Outline: To verify validation message is displayed when pincode empty field (find store-without login)
    When User hovers over the Get in Touch popup
    Then User clicks on the Find a Store option
    And User clicks on the submit button
    Then validation message for empty pincode input field should get displayed "This field is required"
