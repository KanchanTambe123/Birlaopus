
Feature: To Validate Newsletter Subscription Sign-Up Functionality

  Background: 
    Given User is on the Colour Letter Page "colourletterUrl"

  #----------------------------------1---------------------------------------------------->
  Scenario Outline: To verify successful submission of the newsletter subscription form with a valid email address
    When User enters a valid email ID in the newsletter subscription field
    And User clicks on the Sign up for colour letter button
    Then the User should see a success message confirming the newsletter subscription "<Title>" "<Message>"


    Examples: 
      | Title     | Message                         |
      | Thank You | For Subscribing Colour Letter ! |

  #----------------------------------2---------------------------------------------------->
  Scenario Outline: To verify that the newsletter subscription fails with an invalid email address on the newsletter subscription form
    When User enters an invalid email ID "asd@gmail" in the newsletter subscription field
    And User clicks on the Sign up for colour letter button
    Then An error message should be displayed for invalid email ID "Please enter valid email ID"

  #----------------------------------3---------------------------------------------------->
  Scenario Outline: To verify that a validation message appears when the email field is left empty on the newsletter subscription form
    When User leaves the email field empty
    And User clicks on the Sign up for colour letter button
    Then An error message should be displayed for the empty email field "This field is required"
