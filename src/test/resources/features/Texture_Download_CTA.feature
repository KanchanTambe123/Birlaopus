
Feature: To Validate Texture Page Download CTA Validation

  Background: 
    Given User is on BirlaOpus TexturePage "textureUrl"
  #----------------------------------1---------------------------------------------------->
  Scenario Outline: To verify navigation to Texture page and verify Download CTA triggers file download
    And User clicks on the Download now CTA for the Latest Patterns 
    Then The Texture PDF should open successfully 
   # And The Texture PDF file should be downloaded successfully 
    #And The downloaded PDF file should be deleted after verification
