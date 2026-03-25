Feature: Validate the Budget Calculator homepage for a logged-in user(single

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: Validate Budget Calculator functionality using valid mandatory data
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
    Then User clicks on the Next button on the Book a Free Survey form
    And verify the lead API parameters for Budget Calculator form: iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
    And User clicks on the Next button on the Share Few Details section
    And User selects the painting requirement type "<requirementType>"
    And User clicks on the project details next button
    Then User select home configuration type "<bhkType>"
    Then User enters the carpet area "<carpetArea>"
    And User clicks on the project details next button
    And User selects a schedule visit date and timeslot "<time>"
    And User clicks on the Schedule button
    Then A survey booking confirmation message should be displayed successfully

    Examples: 
      | spaceName         | carpetArea | pincode | requirementType | bhkType | address | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource     | time         |
      | Villa or Bungalow |       1000 |  400066 | Exteriors       | 2 BHK   | pune    | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Budget Calculator | 12 PM - 3 PM |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: Verify other categories remain locked when Premium category is selected without login
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
  Scenario Outline: Verify Book a Free Survey button visibility on other tabs after booking a survey from the Premium tab
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
    Then User clicks on the Next button on the Book a Free Survey form
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
    Then User clicks on the Next button on the Book a Free Survey form
    Then User clicks on the I’ll do it later option
    Then A survey booking confirmation message should be displayed successfully

    Examples: 
      | spaceName         | carpetArea | pincode | requirementType | bhkType | address | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource     | time         |
      | Villa or Bungalow |       1000 |  400066 | Exteriors       | 2 BHK   | pune    | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Budget Calculator | 12 PM - 3 PM |

  #---------------------------------- Scenario 5----------------------------------#
  Scenario Outline: To verify thank you pop-up is displayed when user skips all questions
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
    Then User clicks on the Next button on the Book a Free Survey form
    And User clicks on the Next button on the Share Few Details section
    And User clicks on the Skip for now option in the painting requirements question
    And User clicks on the Skip for now option in the home configuration question
    Then A survey booking confirmation message should be displayed successfully

    Examples: 
      | spaceName         | carpetArea | pincode | requirementType | bhkType | address | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource     | time         |
      | Villa or Bungalow |       1000 |  400066 | Exteriors       | 2 BHK   | pune    | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Budget Calculator | 12 PM - 3 PM |

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
    Then User clicks on the Next button on the Book a Free Survey form
    And User enters unserviceable Pin code "<Pincode_Unserviceable>"
    Then User clicks on the Next button on the Book a Free Survey form
    Then the unserviceable pin code message should be displayed

    Examples: 
      | spaceName         | carpetArea | pincode | requirementType | bhkType | address | Pincode_Unserviceable |
      | Villa or Bungalow |       1000 |  400066 | Exteriors       | 2 BHK   | pune    |                111222 |

      
     
  #---------------------------------- Scenario 7----------------------------------# 
  Scenario Outline: To verify Edit Carpet Area and Verify Price Update

  