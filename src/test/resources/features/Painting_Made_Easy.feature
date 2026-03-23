Feature: To Validate Painting Made Easy Form-Get Free Quote functionality

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When User clicks on Painting made easy
    Then User clicks on Get free quote

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify Painting made easy Get Free Quote flow including questionnaire, date & time selection, and confirmation for logged-in user
    Then User clicks on the Next button on the Book a Free Survey form
    And verify the lead API parameters for Painting Made Easy form: iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
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
      | requirementType | carpetArea | bhkType | address | pincode | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource      | time         |
      | Exteriors       |       1200 | 2 BHK   | pune    |  400066 | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Painting made easy | 12 PM - 3 PM |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify thank you pop-up appears when user selects I’ll do it later option
    Then User clicks on the Next button on the Book a Free Survey form
    Then User clicks on the I’ll do it later option
    Then A survey booking confirmation message should be displayed successfully

  #---------------------------------- Scenario 3 ----------------------------------#
  Scenario Outline: To verify thank you pop-up is displayed when user skips all questions
    Then User clicks on the Next button on the Book a Free Survey form
    And User clicks on the Next button on the Share Few Details section
    And User clicks on the Skip for now option in the painting requirements question
    And User clicks on the Skip for now option in the home configuration question
    Then A survey booking confirmation message should be displayed successfully

  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify pop-up is displayed when an unserviceable PIN code is entered
    And User enters unserviceable Pin code "<pincode>"
    Then User clicks on the Next button on the Book a Free Survey form
    Then the unserviceable pin code message should be displayed

    Examples: 
      | pincode |
      |  111222 |
