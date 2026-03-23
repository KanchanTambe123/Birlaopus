@SCOPE1
Feature: To Validate Survey Booking Form

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify successful submission of the survey booking form for a logged-in user
    And User clicks on the Book a Free Survey button
    #And User select property name and address "<address>"
    #Then User click on address confirm button
    #And User enters the survey Pincode "<pincode>"
    Then User clicks on the Next button on the Book a Free Survey form
    And verify the lead API parameters for booking a free survey: iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
    And User clicks on the Next button on the Share Few Details section
    And User clicks on the project details next button
    Then User select home configuration type "<bhkType>"
    Then User enters the carpet area "<carpetArea>"
    And User clicks on the project details next button
    And User selects a schedule visit date and timeslot "<time>"
    And User clicks on the Schedule button
    Then A survey booking confirmation message should be displayed successfully

    Examples: 
      | requirementType | carpetArea | bhkType | address | pincode | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource      | time         |
      | Exteriors       |       1200 | 2 BHK   | pune    |  411033 | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Book a free survey | 12 PM - 3 PM |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify that the user navigates to the previous step using the Back button on the survey booking form
    When User clicks on the Book a Free Survey button
    Then User clicks on the Next button on the Book a Free Survey form
    And User clicks on the Back button in the Book a Free Survey form
    Then User should be redirected to the previous step of the Book a Free Survey form

  #---------------------------------- Scenario 3 ----------------------------------#
  Scenario Outline: To verify thank you pop-up appears when user selects I’ll do it later option
    Then User clicks on the Next button on the Book a Free Survey form
    Then User clicks on the I’ll do it later option
    Then A survey booking confirmation message should be displayed successfully

  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify thank you pop-up is displayed when user skips all questions
    Then User clicks on the Next button on the Book a Free Survey form
    And User clicks on the Next button on the Share Few Details section
    And User clicks on the Skip for now option in the painting requirements question
    And User clicks on the Skip for now option in the home configuration question
    Then A survey booking confirmation message should be displayed successfully

  #---------------------------------- Scenario 5 ----------------------------------#
  Scenario Outline: To verify pop-up is displayed when an unserviceable PIN code is entered
    And User enters unserviceable Pin code "<pincode>"
    Then User clicks on the Next button on the Book a Free Survey form
    Then the unserviceable pin code message should be displayed

    Examples: 
      | pincode |
      |  111222 |

