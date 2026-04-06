Feature: To validate the happy end-to-end colour swatch journey flow for a logged-in user, including adding and removing colour swatches and managing the wishlist.

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify the end-to-end  colour swatch journey through the checkout process with valid data for a logged-in user through navigation
    Then User clicks the cart icon on the header and removes the product from the cart if available
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User selects Colour for colour swatch "<Colour>"
    Then User selects colour swatch quantity <quantity>
    Then User enters a valid pincode "<pincode>" and check product availability
    And User click on add to cart button
    Then User click on View cart & Checkout button
    #And User click on whishlist icon
    And User increase the product quantity "<desiredQuantity>"
    Then User clicks on the Proceed to Enter Address button
    And User clicks on the Apply button, verifies the availability of coupon vouchers, and applies a coupon if available
    And User verifies the product quantity in the final order summary "<expectedQuantity>"
    Then the total payable amount should be correctly calculated and displayed
    And User clicks on the Proceed to Shipment button
    Then User clicks on the Proceed to payment button

    #Then User click on Shop now button
    Examples: 
      | navmenu | navtab       | productname   | Colour    | quantity | pack | pincode | desiredQuantity | address | expectedQuantity |
      | Shop    | Colour Tools | Colour Swatch | Rose dust |        1 |    1 |  400703 |               2 | Mumbai  |                2 |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify that a logged-in user can successfully add a Colour Swatch to the cart through navigation
    Then User clicks the cart icon on the header and removes the product from the cart if available
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User selects Colour for colour swatch "<Colour>"
    Then User selects colour swatch quantity <quantity>
    Then User enters a valid pincode "<pincode>" and check product availability
    And User click on add to cart button
    Then User click on View cart & Checkout button
    Then Product should be added to the cart successfully
    Then User clicks the cart icon on the header and removes the product from the cart if available

    Examples: 
      | navmenu | navtab       | productname   | Colour    | quantity | pack | pincode | desiredQuantity | address | expectedQuantity |
      | Shop    | Colour Tools | Colour Swatch | Rose dust |        1 |    1 |  400703 |               2 | Mumbai  |                2 |

  #---------------------------------- Scenario 3 ----------------------------------#
  Scenario Outline: To verify that a logged-in user can remove a Colour Swatch product from the header cart
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User selects Colour for colour swatch "<Colour>"
    Then User selects colour swatch quantity <quantity>
    Then User enters a valid pincode "<pincode>" and check product availability
    And User click on add to cart button
    Then User click on View cart & Checkout button
    Then User clicks the cart icon on the header and removes the product from the cart if available
    And Cart count should be updated to "<expectedCount>"

    Examples: 
      | navmenu | navtab       | productname   | Colour    | quantity | pack | pincode | expectedCount |
      | Shop    | Colour Tools | Colour Swatch | Rose dust |        1 |    1 |  400703 |             0 |

  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify successful colour swatch add-to-wishlist functionality through navigation for a logged-in user
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    And User click whishlist icon on colour swatch
    Then Product should be added to the wishlist successfully

    Examples: 
      | navmenu | navtab       | productname   | optionText          |
      | Shop    | Colour Tools | Colour Swatch | Delete colour story |

  #---------------------------------- Scenario 5 ----------------------------------#
  Scenario Outline: To verify successful colour swatch remove-to-wishlist functionality through navigation for a logged-in user.
    Then User click whishlist icon on top page
    And User clicks on "<tabName>" tab

    #Then Product should be removed from the wishlist successfully
    Examples: 
      | navmenu | navtab       | productname   | tabName    |
      | Shop    | Colour Tools | Colour Swatch | Favourites |

  #---------------------------------- Scenario 6 ----------------------------------#
  Scenario Outline: To verify error message when user enters invalid pincode for colour swatch
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User selects Colour for colour swatch "<Colour>"
    Then User selects colour swatch quantity <quantity>
    Then User enters an invalid pincode "<pincode>" and check product availability
    Then User should see an error message for invalid pincode for colour swatch "<errorMessage>"

    Examples: 
      | navmenu | navtab       | productname   | Colour    | quantity | pincode | errorMessage                 |
      | Shop    | Colour Tools | Colour Swatch | Rose dust |        1 |     123 | Please enter a valid pincode |

  #---------------------------------- Scenario 7 ----------------------------------#
  Scenario Outline: To verify error message when pincode field is empty
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User selects Colour for colour swatch "<Colour>"
    Then User selects colour swatch quantity <quantity>
    And User click on pincode serviceability check button
    Then User should see the error message when pincode field is empty "This field is required"

    Examples: 
      | navmenu | navtab       | productname   | Colour    | quantity |
      | Shop    | Colour Tools | Colour Swatch | Rose dust |        1 |

  #---------------------------------- Scenario 8 ----------------------------------#
  Scenario Outline: To verify user cannot proceed when invalid quantity is selected
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User selects Colour for colour swatch "<Colour>"
    Then User selects colour swatch quantity <quantity>
    Then Add to cart button should be disabled

    Examples: 
      | navmenu | navtab       | productname   | Colour    | quantity |
      | Shop    | Colour Tools | Colour Swatch | Rose dust |        0 |
