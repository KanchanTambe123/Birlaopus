@SCOPE
Feature: To Validate search Functionality

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the search icon

  #----------------------------------1---------------------------------------------------->
  Scenario Outline: To verify that clicking on the search icon displays the search panel
    Then The search panel should appear

  #----------------------------------2---------------------------------------------------->
  Scenario Outline: To verify User enters a valid keyword for search bar
    When User enters the product name "<searchfor>" in the search box
    Then User should be redirected to the search results page, and the results count should be displayed as greater than 0

    Examples: 
      | searchfor       |
      | Exterior paints |

  #----------------------------------3---------------------------------------------------->
  Scenario Outline: To verify User enters an invalid keyword for search bar
    When User enters the product name "<searchfor>" in the search box
    Then User should see a message for the invalid keyword containing "Showing 0 results"

    Examples: 
      | searchfor         |
      | xyz123            |
      | asdfghjkl         |
      |          00000000 |
      | unknownproduct999 |

  #----------------------------------4---------------------------------------------------->
  Scenario Outline: To verify that User sees trending searches when clicking the search box
    Then User should see the trending search "<TrendingSearch>"

    Examples: 
      | TrendingSearch   |
      | Colour Catalogue |

  #----------------------------------5---------------------------------------------------->
  Scenario Outline: To verify User clicks a suggestion from trending searches
    When User click on serch box then selects the suggestion "<suggestion>"
    Then User should be redirected to the respective results page for "<suggestion>"

    Examples: 
      | suggestion       |
      | Colour Catalogue |
