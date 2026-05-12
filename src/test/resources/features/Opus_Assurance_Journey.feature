@SCOPE
Feature: To Validate Opus Assurance Journey Functionality

  Background: 
    Given User is on Opus Assurance Journey "AssuranceUrl"

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify the account creation functionality for a new user using a valid mobile number and OTP, with a non-serviceable pincode.
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
    Then User should see the heading "We're coming soon!"
    And User should see the message "Birla Opus Assurance is currently not available in your location. We've noted your interest and will notify you as soon as we launch in your area."

    Examples: 
      | paintable_area | pin_code |
      | 2500 sqft      |   111222 |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify whether user is able to see error message for email id field when user enter invalid email id
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
    And User enter paintable area "<paintable_area>"
    Then User should see an error message for paintable area  "Paintable area should be more than or equal to 2000 sqft to avail Opus Assurance"

    Examples: 
      | paintable_area | pin_code |
      | 1000 sqft      | 400 060  |

  #---------------------------------- Scenario 7 ----------------------------------#
  Scenario Outline: To verify that an existing user can complete the end-to-end flow by submitting all mandatory details (new project-Sign Up for PaintCraft button)
    Given User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    And User enter valid mobile number on sign in "<valid_mobile_no>"
    And User click on sign in button
    And User enter valid otp "<valid_otp>"
    And User click on verify otp button
    Then User click on start new project
    And User enter valid pin code on enter details "<pin_code>"
    Then User click on submit button on enter details
    And User enter Site Details project name
    And User enter update pin code on site details "<Update_pin_code>"
    Then User click submit button on Site Details
    And verify the lead API parameters for opus assurance journey: iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
    And User click next button on Just a Few More Details
    Then User clcik on Sign Up for PaintCraft button
    Then User selects the painting requirement type "<requirementType>"
    And User clicks on the project details next button
    Then User select home configuration type "<bhkType>"
    Then User enters the carpet area "<carpetArea>"
    And User clicks on the project details next button
    And User selects a schedule visit date and timeslot "<time>"
    And User clicks on the Schedule button
    Then Birla Opus Assurance confirmation message should be displayed successfully "Thank you for signing up for Birla Opus Assurance!"

    Examples: 
      | paintable_area | pin_code | valid_mobile_no | valid_otp | Update_pin_code | requirementType | carpetArea | bhkType | time         | iclLeadContextC      | iclLeadTypeC             | iclSubType         | leadSubSource  |
      | 2500 sqft      |   500002 |      7019144066 |      1111 |          500002 | Exteriors       |       1200 | 2 BHK   | 12 PM - 3 PM | Birla Opus Assurance | Painting Service Enquiry | Paintcraft Service | Opus Assurance |

  #---------------------------------- Scenario 8 ----------------------------------#
  # Scenario Outline: To verify that an existing user can complete the end-to-end flow by submitting all mandatory details (new project-Find Contractor button)
  # Given User enter valid paintable area "<paintable_area>"
  # And User click on Next button
  #Then User should click on Yet to Start Cta
  # And User click on Pre-register now Cta
  #And User enter valid mobile number on sign in "<valid_mobile_no>"
  # And User click on sign in button
  # And User enter valid otp "<valid_otp>"
  # And User click on verify otp button
  # Then User click on start new project
  # And User enter valid pin code on enter details "<pin_code>"
  # Then User click on submit button on enter details
  # And User enter Site Details project name
  #And User enter update pin code on site details "<Update_pin_code>"
  #Then User click submit button on Site Details
  #And verify the lead API parameters for opus assurance journey: iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
  # And User click next button on Just a Few More Details
  #Then User clcik on Find Contractor button
  # And User selects a contractor as needed
  #Then User click on Next button in Find Contractor section
  #Then User selects the painting requirement type "<requirementType>"
  # And User clicks on the project details next button
  # Then User select home configuration type "<bhkType>"
  #  Then User enters the carpet area "<carpetArea>"
  # And User clicks on the project details next button
  # And User selects a schedule visit date and timeslot "<time>"
  # And User clicks on the Schedule button
  # Then Birla Opus Assurance confirmation message should be displayed successfully
  #Examples:
  #  | paintable_area | pin_code | valid_mobile_no | valid_otp | Update_pin_code | requirementType | carpetArea | bhkType | time         | iclLeadContextC      | iclLeadTypeC             | iclSubType         | leadSubSource  |
  #  | 2500 sqft      |   500002 |      7019144066 |      1111 |          500002 | Exteriors       |       1200 | 2 BHK   | 12 PM - 3 PM | Birla Opus Assurance | Painting Service Enquiry | Paintcraft Service | Opus Assurance |
  #---------------------------------- Scenario 9 ----------------------------------#
  Scenario Outline: To verify that a new user can complete the end-to-end flow by submitting all mandatory details (create an account – Sign Up for PaintCraft button).    Given User enter valid paintable area "<paintable_area>"
    Given User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    Then User clicks create an Account option
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    And User enters bypass OTP and clicks on the Verify OTP button
    Then User enter valid first name
    And User enter valid last name
    And User enter valid email id
    And User enter valid pin code "<pin_code>"
    And User clicks on submit button on the Create an Account
    Then User clicks on tell us more about your site button
    And User enter Site Details project name
    And User enter flat number or bulding name "<flat number>"
    And User enter proerty name "<property_name>"
    Then User clicks on confirm and add address details
    And User enter update pin code on site details "<Update_pin_code>"
    Then User click submit button on Site Details
    And User click next button on Just a Few More Details
    Then User clcik on Sign Up for PaintCraft button
    Then User selects the painting requirement type "<requirementType>"
    And User clicks on the project details next button
    Then User select home configuration type "<bhkType>"
    Then User enters the carpet area "<carpetArea>"
    And User clicks on the project details next button
    And User selects a schedule visit date and timeslot "<time>"
    And User clicks on the Schedule button
    Then Birla Opus Assurance confirmation message should be displayed successfully "Thank you for signing up for Birla Opus Assurance!"

    Examples: 
      | paintable_area | pin_code | valid_mobile_no | valid_otp | Update_pin_code | requirementType | carpetArea | bhkType | time         | iclLeadContextC      | iclLeadTypeC             | iclSubType         | leadSubSource  | flat number | property_name |
      | 2500 sqft      |   500002 |      7019144066 |      1111 |          500002 | Exteriors       |       1200 | 2 BHK   | 12 PM - 3 PM | Birla Opus Assurance | Painting Service Enquiry | Paintcraft Service | Opus Assurance | B-10        | mumbai        |

  #---------------------------------- Scenario 10----------------------------------#
  Scenario Outline: To verify Clicking Pre-Register Now without login should open to login or register page
    And User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    And User Login page should be displayed successfully

    Examples: 
      | paintable_area |
      | 2500 sqft      |

  #---------------------------------- Scenario 11----------------------------------#
  Scenario Outline: To verify that selecting more than 5 contractors is restricted
    Given User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    And User enter valid mobile number on sign in "<valid_mobile_no>"
    And User click on sign in button
    And User enter valid otp "<valid_otp>"
    And User click on verify otp button
    Then User click on start new project
    And User enter valid pin code on enter details "<pin_code>"
    Then User click on submit button on enter details
    And User enter Site Details project name
    And User enter update pin code on site details "<Update_pin_code>"
    Then User click submit button on Site Details
    And verify the lead API parameters for opus assurance journey: iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
    And User click next button on Just a Few More Details
    Then User clcik on Find Contractor button
    And User selects 5 contractors
    Then User should not be able to select more than 5 contractors and validation message should be displayed

    Examples: 
      | paintable_area | pin_code | valid_mobile_no | valid_otp | Update_pin_code | requirementType | carpetArea | bhkType | time         | iclLeadContextC      | iclLeadTypeC             | iclSubType         | leadSubSource  | flat number | property_name |
      | 2500 sqft      |   500002 |      7019144066 |      1111 |          500002 | Exteriors       |       1200 | 2 BHK   | 12 PM - 3 PM | Birla Opus Assurance | Painting Service Enquiry | Paintcraft Service | Opus Assurance | B-10        | mumbai        |

  #---------------------------------- Scenario 12----------------------------------#
  Scenario Outline: To verify that a validation message is displayed when proceeding without selecting a contractor
    Given User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    And User enter valid mobile number on sign in "<valid_mobile_no>"
    And User click on sign in button
    And User enter valid otp "<valid_otp>"
    And User click on verify otp button
    Then User click on start new project
    And User enter valid pin code on enter details "<pin_code>"
    Then User click on submit button on enter details
    And User enter Site Details project name
    And User enter update pin code on site details "<Update_pin_code>"
    Then User click submit button on Site Details
    And verify the lead API parameters for opus assurance journey: iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
    And User click next button on Just a Few More Details
    Then User clcik on Find Contractor button
    Then User click on Next button in Find Contractor section
    And User should be displayed validation message Contractor section "Please select a contractor"

    Examples: 
      | paintable_area | pin_code | valid_mobile_no | valid_otp | Update_pin_code | requirementType | carpetArea | bhkType | time         | iclLeadContextC      | iclLeadTypeC             | iclSubType         | leadSubSource  | flat number | property_name |
      | 2500 sqft      |   500002 |      7019144066 |      1111 |          500002 | Exteriors       |       1200 | 2 BHK   | 12 PM - 3 PM | Birla Opus Assurance | Painting Service Enquiry | Paintcraft Service | Opus Assurance | B-10        | mumbai        |

  #---------------------------------- Scenario 13----------------------------------#
  Scenario Outline: To verify that for unserviceable pincode, the API returns serviceable = false and the Coming Soon message is displayed
    Given User enter valid paintable area "<paintable_area>"
    And User click on Next button
    Then User should click on Yet to Start Cta
    And User click on Pre-register now Cta
    And User enter valid mobile number on sign in "<valid_mobile_no>"
    And User click on sign in button
    And User enter valid otp "<valid_otp>"
    And User click on verify otp button
    Then User click on start new project
    And User enter valid pin code on enter details "<pin_code>"
    Then User click on submit button on enter details
    And User enter Site Details project name
    And User enter update pin code on site details "<Update_pin_code>"
    Then User click submit button on Site Details
    And verify the lead API parameters for opus assurance journey: isAreaServiceable against value "<status>"
    Then User should see the heading "We're coming soon!"
    And User should see the message "Birla Opus Assurance is currently not available in your location. We've noted your interest and will notify you as soon as we launch in your area."

    Examples: 
      | paintable_area | pin_code | status | Update_pin_code | valid_mobile_no | valid_otp |
      | 2500 sqft      | 400 060  | false  |          111222 |      7019144066 |      1111 |
