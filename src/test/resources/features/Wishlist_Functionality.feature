
Feature: To Validate Wishlist add and remove functionality(Single Product)

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify successful add-to-wishlist functionality through navigation for a logged-in user.
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    And User click whishlist icon on product
    Then Product should be added to the wishlist successfully
    Then User click whishlist icon on top page
    And User removes the product from the wishlist if it is already added "<optionText>"

    Examples: 
      | navmenu  | navtab        | productname       | optionText       |
      | Products | Waterproofing | Alldry Wall Fix 4 | Delete a Product |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify successful remove-to-wishlist functionality through navigation for a logged-in user.
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    And User click whishlist icon on product
    Then User click whishlist icon on top page
    And User removes the product from the wishlist if it is already added "<optionText>"
    Then Product should be removed from the wishlist successfully

    Examples: 
      | navmenu  | navtab        | productname       | optionText       |
      | Products | Waterproofing | Alldry Wall Fix 4 | Delete a Product |

  #---------------------------------- Scenario 3 ----------------------------------#
  Scenario Outline: To verify wishlist add and remove functionality through search bar for a logged-in user
    When User clicks on the search icon
    Then User enters the product name "<searchfor>" in the search box
    And User selects and clicks on the product
    And User click whishlist icon on product
    Then Product should be added to the wishlist successfully
    Then User click whishlist icon on top page
    And User removes the product from the wishlist if it is already added "<optionText>"
    Then Product should be removed from the wishlist successfully

    Examples: 
      | navmenu  | navtab        | productname       | optionText       | searchfor       |
      | Products | Waterproofing | Alldry Wall Fix 4 | Delete a Product | Interior paints |
