
Feature: To Validate Compare Product Functionality(Two Product)

  #----------------------------------1---------------------------------------------------->
  Scenario Outline: To verify that user can add a product to the comparison list
    Given User is on first product url "CompareFirstProductUrl" and adds first product "<FirstProduct>" to the comparison list
    And User is on second product url "CompareSecondProductUrl" and adds second product "<SecondProduct>" to the comparison list
    Then the compare counter should display "<ExpectedCount>"
    And User can view the comparison page with the selected products

    Examples: 
      | FirstProduct      | SecondProduct      | ExpectedCount |
      | Style Color Smart | Style Super Bright |             2 |

  #----------------------------------2---------------------------------------------------->
  Scenario Outline: To verify that user can remove a product from the comparison list
    Given User is on first product url "CompareFirstProductUrl" and adds first product "<FirstProduct>" to the comparison list
    And User is on second product url "CompareSecondProductUrl" and adds second product "<SecondProduct>" to the comparison list
    And User can view the comparison page with the selected products
    When User removes "<ProductToRemove>" from the comparison list
    Then the compare counter should display "<ExpectedCountAfterRemove>"
    And The comparison list should contain "<RemainingProduct>"

    Examples: 
      | FirstProduct      | SecondProduct      | ProductToRemove   | ExpectedCountAfterRemove | RemainingProduct   |
      | Style Color Smart | Style Super Bright | Style Color Smart |                        1 | Style Super Bright |

  #----------------------------------3---------------------------------------------------->
  Scenario Outline: To verify Compare Page opens correctly
    Given User is on first product url "CompareFirstProductUrl" and adds first product "<FirstProduct>" to the comparison list
    And User is on second product url "CompareSecondProductUrl" and adds second product "<SecondProduct>" to the comparison list
    And User can view the comparison page with the selected products
    When User clicks on the Compare Now button
    Then Compare Page should be displayed
    And All selected products should be visible with their details

    Examples: 
      | FirstProduct      | SecondProduct      |
      | Style Color Smart | Style Super Bright |

  #----------------------------------4---------------------------------------------------->
  Scenario Outline: To verify product titles and count on the Compare page
    Given User is on first product url "CompareFirstProductUrl" and adds first product "<FirstProduct>" to the comparison list
    And User is on second product url "CompareSecondProductUrl" and adds second product "<SecondProduct>" to the comparison list
    And User can view the comparison page with the selected products
    When User clicks on the Compare Now button
    Then User should see the correct product titles on the Compare page.
    Then User should see and verify that the product count is "<ExpectedCount>"

    Examples: 
      | FirstProduct      | SecondProduct      | ExpectedCount |
      | Style Color Smart | Style Super Bright |             2 |
