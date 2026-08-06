@module
Feature: To Validate the Budget Calculator homepage for a logged-in user(single product)

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
@test
  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To validate Budget Calculator functionality using valid mandatory data
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When the User scrolls to the Budget Calculator
    Then the User selects the space name to paint "<spaceName>"
    And the User enters a valid carpet area "<carpetArea>"
    And the User enters a valid serviceable pincode "<pincode>"
    Then the User clicks on the Calculate Now button
    # And the calculation result correctly reflects the entered carpet area and pincode
    Then click on Budget Calculator book free survey button
    Then User clicks on the Next button on Address section page
    And User captures and validates API request and response for "lead/shortForm"
    And verify the lead API parameters for Budget Calculator form: iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
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
      | spaceName         | carpetArea | pincode | requirementType | bhkType | address | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource     | time         |
      | Villa or Bungalow |       1000 |  400066 | Exteriors       | 2 BHK   | pune    | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Budget Calculator | 12 PM - 3 PM |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify other categories remain locked when Premium category is selected without login
    When the User scrolls to the Budget Calculator
    Then the User selects the space name to paint "<spaceName>"
    And the User enters a valid carpet area "<carpetArea>"
    And the User enters a valid serviceable pincode "<pincode>"
    Then the User clicks on the Calculate Now button
    Then the Economy and Luxury categories should be displayed as locked

    Examples: 
      | spaceName | carpetArea | pincode |
      | Studio    |       1000 |  400066 |

  #---------------------------------- Scenario 3 ----------------------------------#
  Scenario Outline: To verify Book a Free Survey button visibility on other tabs after booking a survey from the Premium tab
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When the User scrolls to the Budget Calculator
    Then the User selects the space name to paint "<spaceName>"
    And the User enters a valid carpet area "<carpetArea>"
    And the User enters a valid serviceable pincode "<pincode>"
    Then the User clicks on the Calculate Now button
    Then click on Budget Calculator book free survey button
    Then User clicks on the Next button on Address section page
    And User captures and validates API request and response for "lead/shortForm"
    Then User clicks on the I’ll do it later option
    Then the User clicks on the Thank You window close icon
    Then the Book a free survey button should not be visible on the Premium tab
    And the Book a free survey button should be visible on the Economy tab
    And the Book a free survey button should be visible on the Luxury tab

    Examples: 
      | spaceName         | carpetArea | pincode | requirementType | bhkType | address | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource     | time         |
      | Villa or Bungalow |       1000 |  400066 | Exteriors       | 2 BHK   | pune    | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Budget Calculator | 12 PM - 3 PM |

  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify thank you pop-up appears when user selects I’ll do it later option
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When the User scrolls to the Budget Calculator
    Then the User selects the space name to paint "<spaceName>"
    And the User enters a valid carpet area "<carpetArea>"
    And the User enters a valid serviceable pincode "<pincode>"
    Then the User clicks on the Calculate Now button
    # And the calculation result correctly reflects the entered carpet area and pincode
    Then click on Budget Calculator book free survey button
    Then User clicks on the Next button on Address section page
    And User captures and validates API request and response for "lead/shortForm"
    Then User clicks on the I’ll do it later option
    Then A survey booking confirmation message should be displayed successfully "Thank you for sharing your details & scheduling a survey with us!"

    Examples: 
      | spaceName         | carpetArea | pincode | requirementType | bhkType | address | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource     | time         |
      | Villa or Bungalow |       1000 |  400066 | Exteriors       | 2 BHK   | pune    | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Budget Calculator | 12 PM - 3 PM |

  #---------------------------------- Scenario 5----------------------------------#
  #Scenario Outline: To verify thank you pop-up is displayed when user skips all questions
  # When the User scrolls to the Budget Calculator
  #Then the User selects the space name to paint "<spaceName>"
  #And the User enters a valid carpet area "<carpetArea>"
  #And the User enters a valid serviceable pincode "<pincode>"
  #Then the User clicks on the Calculate Now button
  #Then click on Budget Calculator book free survey button
  #Then User clicks on the Next button on the Book a Free Survey form
  # And User clicks on the Next button on the Share Few Details section
  #And User clicks on the Skip for now option in the painting requirements question
  #And User clicks on the Skip for now option in the home configuration question
  # Then A survey booking confirmation message should be displayed successfully
  #Examples:
  # | spaceName         | carpetArea | pincode | requirementType | bhkType | address | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource     | time         |
  #| Villa or Bungalow |       1000 |  400066 | Exteriors       | 2 BHK   | pune    | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Budget Calculator | 12 PM - 3 PM |
  #---------------------------------- Scenario 6----------------------------------#
  Scenario Outline: To verify pop-up is displayed when an unserviceable PIN code is entered
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When the User scrolls to the Budget Calculator
    Then the User selects the space name to paint "<spaceName>"
    And the User enters a valid carpet area "<carpetArea>"
    And the User enters a valid serviceable pincode "<pincode>"
    Then the User clicks on the Calculate Now button
    Then click on Budget Calculator book free survey button
    #Then User clicks on the Next button on the Book a Free Survey form
    And User enters unserviceable Pin code on budget calculator form "<Pincode_Unserviceable>"
    Then User clicks on the Next button on Address section page
    And User captures and validates API request and response for "lead/shortForm"
    Then the unserviceable pin code message should be displayed "Coming soon Painting services are currently not available in your area"

    Examples: 
      | spaceName         | carpetArea | pincode | requirementType | bhkType | address | Pincode_Unserviceable |
      | Villa or Bungalow |       1000 |  400066 | Exteriors       | 2 BHK   | pune    |                111222 |

  #---------------------------------- Scenario 7----------------------------------#
  Scenario Outline: To verify whether the carpet area can be edited and if the price updates correctly.
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When the User scrolls to the Budget Calculator
    Then the User selects the space name to paint "<spaceName>"
    And the User enters a valid carpet area "<carpetArea>"
    And the User enters a valid serviceable pincode "<pincode>"
    Then the User clicks on the Calculate Now button
    And the User verifies the edit button is clickable and clicks on the carpet area edit button
    And the User enters a valid Recalculate estimate carpet area "<UpdatecarpetArea>"
    Then the User clicks on the Recalculate Estimate button and verifies the price is updated

    Examples: 
      | spaceName         | carpetArea | pincode | requirementType | bhkType | address | UpdatecarpetArea |
      | Villa or Bungalow |       2000 |  411033 | Exteriors       | 2 BHK   | pune    |             2500 |

  #---------------------------------- Scenario 8----------------------------------#
  Scenario Outline: To verify that an error message is displayed when an invalid pincode is entered.
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When the User scrolls to the Budget Calculator
    And the User enters a invalid pincode "<pincode>"
    Then the User should see the error message "Invalid Input."

    Examples: 
      | pincode |
      |    4110 |
      |       0 |

  #---------------------------------- Scenario 9----------------------------------#
  Scenario Outline: To verify error message when alphabet is entered in pincode field
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When the User scrolls to the Budget Calculator
    And the User enters a invalid pincode "<pincode>"
    Then the user should see the error message "This field is required." when an alphabet is entered in the pincode field.

    Examples: 
      | pincode |
      | a       |

  #---------------------------------- Scenario 10----------------------------------#
  Scenario Outline: To verify that clicking on "View Products" displays products based on carpet area and a particular pincode(Premium category)
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When the User scrolls to the Budget Calculator
    Then the User selects the space name to paint "<spaceName>"
    And the User enters a valid carpet area "<carpetArea>"
    And the User enters a valid serviceable pincode "<pincode>"
    Then the User clicks on the Calculate Now button
    And the User clicks on view products

    #Then the User should see the list of products matching the carpet area "<carpetArea>" and pincode "<pincode>"
    Examples: 
      | spaceName         | carpetArea | pincode |
      | Villa or Bungalow |       2000 |  411033 |

  #---------------------------------- Scenario 11----------------------------------#
  Scenario Outline: To verify Download Estimate button is clickable
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When the User scrolls to the Budget Calculator
    Then the User selects the space name to paint "<spaceName>"
    And the User enters a valid carpet area "<carpetArea>"
    And the User enters a valid serviceable pincode "<pincode>"
    Then the User clicks on the Calculate Now button
    When the User scrolls to the Download Estimate button
    Then the Download Estimate should be visible
    And the Download Estimate should be clickable

    Examples: 
      | spaceName         | carpetArea | pincode |
      | Villa or Bungalow |       2000 |  411033 |
