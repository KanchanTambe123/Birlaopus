Feature: To Validate Painting Made Easy Form-Get Free Quote functionality

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify Painting made easy Get Free Quote flow including questionnaire, date & time selection, and confirmation for logged-in user
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When User clicks on Painting made easy
    Then User clicks on Get free quote
    Then User clicks on the Next button on Address section page
    And User captures and validates API request and response for "lead/shortForm"
    And verify the lead API parameters for Painting Made Easy form: iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
    And User clicks on the Next button on the Share Few Details section
    And User selects the painting requirement type "<requirementType>"
    And User clicks on the project details next button
    Then User select home configuration type "<bhkType>"
    Then User enters the carpet area "<carpetArea>"
    And User clicks on the project details next button
    And User selects a schedule visit date and timeslot "<time>"
    And User clicks on the Schedule button
    Then A survey booking confirmation message should be displayed successfully "Thank you for sharing your details & scheduling a survey with us!"

    Examples: 
      | requirementType | carpetArea | bhkType | address | pincode | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource      | time         |
      | Exteriors       |       1200 | 2 BHK   | pune    |  400066 | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Painting made easy | 12 PM - 3 PM |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify thank you pop-up appears when user selects I’ll do it later option
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When User clicks on Painting made easy
    Then User clicks on Get free quote
    Then User clicks on the Next button on Address section page
    Then User clicks on the I’ll do it later option
    Then A survey booking confirmation message should be displayed successfully "Thank you for sharing your details & scheduling a survey with us!"

  #---------------------------------- Scenario 3 ----------------------------------#
  #Scenario Outline: To verify thank you pop-up is displayed when user skips all questions
  #  When User clicks on the profile icon
  # And User clicks on the Sign In button
  # And User enters valid mobile number on the Sign In page
  # And the User clicks on the Sign In button after entering the mobile number
  # And User enters valid OTP and clicks on the Verify OTP button
  #Then User clicks on the close icon
  # When User clicks on Painting made easy
  #Then User clicks on Get free quote
  #Then User clicks on the Next button on Address section page
  # And User clicks on the Next button on the Share Few Details section
  #And User clicks on the Skip for now option in the painting requirements question
  # And User clicks on the Skip for now option in the home configuration question
  #Then A survey booking confirmation message should be displayed successfully
  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify pop-up is displayed when an unserviceable PIN code is entered
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When User clicks on Painting made easy
    Then User clicks on Get free quote
    And User enters unserviceable Pin code "<pincode>"
    Then User clicks on the Next button on Address section page
    Then the unserviceable pin code message should be displayed "Coming soon Painting services are currently not available in your area"

    Examples: 
      | pincode |
      |  111222 |

  #---------------------------------- Scenario 5----------------------------------#
  Scenario Outline: To verify error message is displayed to the user when entered incorrect mobile number on create an account option
    When User clicks on Painting made easy
    Then User clicks on Get free quote
    And User clicks on the Create an account option
    And User enter invalid mobile number "<invalid_mobile_no>"
    Then User should see an message Please enter valid mobile number "Please enter valid mobile number"

    Examples: 
      | invalid_mobile_no |
      |              4444 |

  #---------------------------------- Scenario 6----------------------------------#
  Scenario Outline: To verify error message is displayed to the user when entered incorrect first name
    When User clicks on Painting made easy
    Then User clicks on Get free quote
    And User clicks on the Create an account option
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    And User enters bypass OTP and clicks on the Verify OTP button
    Then User enter invalid first name on painting made easy "<invalid_first_name>"
    And User enter valid last name on painting made easy
    And User enter valid email id on painting made easy
    And User clicks on save details button
    And User should see an error message for first name "This field is required"

    Examples: 
      | invalid_first_name |
      | @@@@@@@@@@@        |

  #---------------------------------- Scenario 7 ----------------------------------#
  Scenario Outline: To verify error message is displayed to the user when entered incorrect last name
    When User clicks on Painting made easy
    Then User clicks on Get free quote
    And User clicks on the Create an account option
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    And User enters bypass OTP and clicks on the Verify OTP button
    Then User enter valid first name on painting made easy
    And User enter invalid last name on painting made easy "<invalid_last_name>"
    And User enter valid email id on painting made easy
    And User clicks on save details button
    And User should see an error message for last name "This field is required"

    Examples: 
      | invalid_last_name |
      | @@@@@@@@@@@       |

  #---------------------------------- Scenario 8 ----------------------------------#
  Scenario Outline: To verify error message is displayed to the user when entered incorrect email id
    When User clicks on Painting made easy
    Then User clicks on Get free quote
    And User clicks on the Create an account option
    And User enters valid mobile number on the Create an Account
    And User clicks on the Create an Account button
    And User enters bypass OTP and clicks on the Verify OTP button
    Then User enter valid first name on painting made easy
    And User enter valid last name on painting made easy
    And User enter invalid email id on painting made easy "<invalid_email_id>"
    And User clicks on save details button
    And User should see an error message for last name "Please enter valid email address"

    Examples: 
      | invalid_email_id |
      | testtestgmai.com |

  #---------------------------------- Scenario 9 ----------------------------------#
  Scenario Outline: To verify error message is displayed to the user when entered incorrect mobile number on sign-in
    When User clicks on Painting made easy
    Then User clicks on Get free quote
    And User enter invalid mobile number on sign in "<invalid_mobile_no>"
    Then User should see an message Please enter valid mobile number "Please enter valid mobile number"

    Examples: 
      | invalid_mobile_no |
      |              4444 |

  #---------------------------------- Scenario 10----------------------------------#
  Scenario Outline: To verify that an error message is displayed when a required field is left empty.
    When User clicks on Painting made easy
    Then User clicks on Get free quote
    And User enter valid mobile number on sign in "<valid_mobile_no>"
    And User click on sign in button
    And User enter valid otp "<valid_otp>"
    And User click on verify button
    And User empty flat no field.
    And User empty property name field.
    And User should see an error message for flat no. "This field is required."
    And User should see an error message for property name "This field is required."

    Examples: 
      | valid_mobile_no | valid_otp |
      |      7019144066 |      1111 |
       #---------------------------------- Scenario 11----------------------------------#
 #Scenario Outline: To verify error message is displayed when user does not select any option for Tell us about your project	
    #When User clicks on Painting made easy
    #Then User clicks on Get free quote
    #And User enter valid mobile number on sign in "<valid_mobile_no>"
    #And User click on sign in button
    #And User enter valid otp "<valid_otp>"
    #And User click on verify button
    #And User click on next button on Site Details
#  	 And User click next button on just a few more details
#  	 And User click next button on tell us about your project
#  	 And User should see an error message as please select an option "Please select an option"
#  	   	
#  	Examples:
#  	|valid_mobile_no|valid_otp|
#  	| 7019144066    | 1111    |
