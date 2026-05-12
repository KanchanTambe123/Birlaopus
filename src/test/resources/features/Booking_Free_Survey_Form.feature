@SCOPE
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
    Then User selects the painting requirement type "<requirementType>"
    And User clicks on the project details next button
    Then User select home configuration type "<bhkType>"
    Then User enters the carpet area "<carpetArea>"
    And User clicks on the project details next button
    And User selects a schedule visit date and timeslot "<time>"
    And User clicks on the Schedule button
    Then A survey booking confirmation message should be displayed successfully "Thank you for sharing your details & scheduling a survey with us!"

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
    And User clicks on the Book a Free Survey button
    Then User clicks on the Next button on the Book a Free Survey form
    Then User clicks on the I’ll do it later option
    Then A survey booking confirmation message should be displayed successfully "Thank you for sharing your details & scheduling a survey with us!"

  #---------------------------------- Scenario 4 ----------------------------------#
  # Scenario Outline: To verify thank you pop-up is displayed when user skips all questions
  # And User clicks on the Book a Free Survey button
  # Then User clicks on the Next button on the Book a Free Survey form
  #And User clicks on the Next button on the Share Few Details section
  # Then User selects the painting requirement type "<requirementType>"
  #And User clicks on the Skip for now option in the painting requirements question
  # And User clicks on the Skip for now option in the home configuration question
  #Then A survey booking confirmation message should be displayed successfully
  #---------------------------------- Scenario 5 ----------------------------------#
  Scenario Outline: To verify pop-up is displayed when an unserviceable PIN code is entered
    And User clicks on the Book a Free Survey button
    And User enters unserviceable Pin code "<pincode>"
    Then User clicks on the Next button on the Book a Free Survey form
    Then the unserviceable pin code message should be displayed "Coming soon Painting services are currently not available in your area"

    Examples: 
      | pincode |
      |  111222 |
@test
  #---------------------------------- Scenario 5 ----------------------------------#
  Scenario Outline: To Verify address section with valid and invalid inputs
    And User clicks on the Book a Free Survey button
    When User enters "<FlatNo>" in Flat no / building name field
    And User enters "<AddressLine1>" in Property name / Address line 1 field
    And User selects location using "<MapOption>"
    And User enters "<City>" in City field
    And User enters "<State>" in State field
    And User enters "<Pincode>" in Pincode field
    Then User clicks on the Next button on the Book a Free Survey form
    Then "<ExpectedResult>" should be displayed

    Examples: 
      | FlatNo | AddressLine1                 | MapOption      | City   | State       | Pincode | ExpectedResult                |
      | test   | Testtex Laboratories Pvt Ltd | Locate on maps | Mumbai | Maharashtra |  500001 | Address saved successfully    |
      |        | Testtex Laboratories Pvt Ltd | Locate on maps | Mumbai | Maharashtra |  500001 | This field is required.       |
      | test   |                              | Locate on maps | Mumbai | Maharashtra |  500001 | This field is required.       |
      | test   | Testtex Laboratories Pvt Ltd | Locate on maps | Mumbai | Maharashtra |         | This field is required.       |
      | test   | Testtex Laboratories Pvt Ltd | Locate on maps | Mumbai | Maharashtra |     123 | Invalid Input.                |
      | test   | Testtex Laboratories Pvt Ltd | Locate on maps | Mumbai | Maharashtra |  500001 | Next page should be displayed |
