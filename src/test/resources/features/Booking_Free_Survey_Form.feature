@Report
Feature: To Validate Survey Booking Form

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify successful submission of the survey booking form for a logged-in user
    And User clicks on the Book a Free Survey button
    Then User clicks on the Next button on the Book a Free Survey form
    And User clicks on the Next button on the Share Few Details section
    And User selects the painting requirement type "<requirementType>"
    And User clicks on the project details next button
    Then User enters the carpet area "<carpetArea>"
    #Then User select bhk type "<bhkType>" and enter the carpet area "<carpetArea>"
    #And User clicks on the project details next button
    And User clicks on the Skip for now option in the carpet area section
    And User clicks on the Skip for now option in the Project Requirements section
    Then A survey booking confirmation message should be displayed successfully

    Examples: 
      | requirementType | carpetArea | bhkType |
      | Exteriors       |       1200 | 2 BHK   |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify that the user navigates to the previous step using the Back button on the survey booking form
    When User clicks on the Book a Free Survey button
    Then User clicks on the Next button on the Book a Free Survey form
    And User clicks on the Back button in the Book a Free Survey form
    Then User should be redirected to the previous step of the Book a Free Survey form
