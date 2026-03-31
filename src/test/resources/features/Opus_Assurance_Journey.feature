#@SCOPE1
Feature: To Validate Sign-In (User Login)Functionality

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify create account functionality with valid mobile number and OTP(new user)
    When User clicks on Register Now Cta on home page
    And User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    Then User clicks on the Create an account option
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    And User enters bypass OTP and clicks on the Verify OTP button
    Then User enter valid first name
    And User enter valid last name
    And User enter valid email id
    And User enter valid pin code "<pin_code>"
    Then User click on the Save Details button
    Then User should see an message We re coming soon! "We're coming soon!"

    Examples: 
      | paintable_area | pin_code |
      | 2500 sqft      | 400 060  |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify whether user is able to see error message for email id field when user enter invalid email id
    When User clicks on Register Now Cta on home page
    And User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    Then User clicks on the Create an account option
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    And User enters bypass OTP and clicks on the Verify OTP button
    Then User enter valid first name
    And User enter valid last name
    And User enter invalid email id "<invalid_email_id>"
    And User should see an error message for email id "Invalid Input"
    And User enter valid pin code "<pin_code>"
    Then User click on the Save Details button

    Examples: 
      | paintable_area | invalid_email_id | pin_code |
      | 2500 sqft      | testestgmail.com | 400 060  |

  #---------------------------------- Scenario 3 ----------------------------------#
  Scenario Outline: To verify whether user is able to see error message for last name when user enter invalid last name
    When User clicks on Register Now Cta on home page
    And User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    Then User clicks on the Create an account option
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    And User enters bypass OTP and clicks on the Verify OTP button
    Then User enter valid first name
    And User enter invalid last name "<invalid_last_name>"
    And User enter valid email id
    And User enter valid pin code "<pin_code>"
    Then User click on the Save Details button
    And User should see an error message for last name "This field is required."

    Examples: 
      | paintable_area | invalid_last_name | invalid_email_id | pin_code |
      | 2500 sqft      | @@@@@@@@@@@       | testestgmail.com | 400 060  |

  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify whether user is able to see error message for first name when user enter invalid first name
    When User clicks on Register Now Cta on home page
    And User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    Then User clicks on the Create an account option
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    And User enters bypass OTP and clicks on the Verify OTP button
    Then User enter invalid first name "<invalid_first_name>"
    And User enter valid last name
    And User enter valid email id
    And User enter valid pin code "<pin_code>"
    Then User click on the Save Details button
    And User should see an error message for first name "This field is required."

    Examples: 
      | paintable_area | invalid_first_name | pin_code |
      | 2500 sqft      | @@@@@@@@@@@        | 400 060  |

  #---------------------------------- Scenario 5 ----------------------------------#
  Scenario Outline: To verify whether user is able to see error message for first name when user enter invalid first name
    When User clicks on Register Now Cta on home page
    And User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    Then User clicks on the Create an account option
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    And User enter invalid otp "<invalid_otp>"
    And User click on verify otp cta
    Then User should see an error message for otp "OTP does not match."

    Examples: 
      | paintable_area | invalid_otp |
      | 2500 sqft      |        1111 |

  #---------------------------------- Scenario 6 ----------------------------------#
  Scenario Outline: To verify that the user is shown an error message when the paintable area is less than 2000 sq ft
    When User clicks on Register Now Cta on home page
    And User enter paintable area "<paintable_area>"
    Then User should see an error message for paintable area  "Paintable area should be more than or equal to 2000 sqft to avail Opus Assurance"

    Examples: 
      | paintable_area | pin_code |
      | 1000 sqft      | 400 060  |
