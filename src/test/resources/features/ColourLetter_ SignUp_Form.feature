@Report
Feature: To Validate Newsletter Subscription Sign-Up Functionality

  Background: 
    Given User is on the Colour Letter Page "colourletterUrl"

  #----------------------------------1---------------------------------------------------->
  Scenario Outline: To verify successful newsletter subscription with a valid email
    When User enters a valid email ID in the newsletter subscription field
    And User clicks on the Sign up for colour letter button
    Then the User should see a success message confirming the newsletter subscription "<Title>" "<Message>"
    And User closes the newsletter success popup

    Examples: 
      | Title     | Message                         |
      | Thank You | For Subscribing Colour Letter ! |

  #----------------------------------2---------------------------------------------------->
  Scenario Outline: To verify newsletter subscription fails with an invalid email
    When User enters an invalid email ID "asd@gmail" in the newsletter subscription field
    And User clicks on the Sign up for colour letter button
    Then An error message should be displayed for invalid email Please enter a valid email ID "Please enter a valid email ID"

  #----------------------------------3---------------------------------------------------->
  Scenario Outline: To verify that validation appears when the email field is left empty
    When User leaves the email field empty
    And User clicks on the Sign up for colour letter button
    Then An error message should be displayed empty filed Email is required "Email is required"
