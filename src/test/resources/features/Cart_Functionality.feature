@SCOPE1
Feature: To Validate Cart Functionality – Add, Update, Remove and Price Validation (Single Product)

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #----------------------------------1---------------------------------------------------->
  Scenario Outline: To verify that a logged-in user can successfully add a product to the cart through navigation
    #Then User clicks the cart icon on the header and removes the product from the cart if available
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User click on Shop now button
    Then User selects Colour "<Colour>"
    Then User selects quantity <quantity> of "<pack>" Ltr pack
    Then User enters a valid pincode "<pincode>" and check product availability
    And User click on add to cart button
    Then User click on View cart & Checkout button
    Then Product should be added to the cart successfully
    Then User clicks the cart icon on the header and removes the product from the cart if available

    Examples: 
      | navmenu  | navtab          | productname      | Colour     | quantity | pack | pincode | desiredQuantity | address | expectedQuantity |
      | Products | Exterior paints | Calista Neo Star | Jaipur red |        1 |    1 |  400703 |               2 | Mumbai  |                2 |

  #----------------------------------2---------------------------------------------------->
  Scenario Outline: To verify that a logged-in user can successfully add a product to the cart through searchbar
    When User clicks on the search icon
    Then User enters the product name "<searchfor>" in the search box
    And User selects and clicks on the product
    Then User click on Shop now button
    Then User selects Colour "<Colour>"
    Then User selects quantity <quantity> of "<pack>" Ltr pack
    Then User enters a valid pincode "<pincode>" and check product availability
    And User click on add to cart button
    Then User click on View cart & Checkout button
    Then Product should be added to the cart successfully
    Then User clicks the cart icon on the header and removes the product from the cart if available

    Examples: 
      | searchfor       | Colour    | quantity | pack | pincode |
      | Interior paints | Rose dust |        1 |    1 |  411033 |

  #----------------------------------3---------------------------------------------------->
  Scenario Outline: To verify that the total price is updated correctly when the product quantity is changed through navigation for a logged-in user
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User click on Shop now button
    Then User selects Colour "<Colour>"
    Then User selects quantity <quantity> of "<pack>" Ltr pack
    Then User enters a valid pincode "<pincode>" and check product availability
    And User click on add to cart button
    Then User click on View cart & Checkout button
    And User increase the product quantity "<desiredQuantity>"
    #Then User clicks on the Proceed to Enter Address button
    #And User verifies the product quantity in the final order summary "<expectedQuantity>"
    Then the total payable amount should be correctly calculated and displayed
    Then User clicks the cart icon on the header and removes the product from the cart if available

    Examples: 
      | navmenu  | navtab          | productname      | Colour     | quantity | pack | pincode | desiredQuantity | address | expectedQuantity |
      | Products | Exterior paints | Calista Neo Star | Jaipur red |        1 |    1 |  400703 |               5 | Mumbai  |                5 |

  #----------------------------------4---------------------------------------------------->
  Scenario Outline: To verify that a logged-in user can remove a product from the header cart
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User click on Shop now button
    Then User selects Colour "<Colour>"
    Then User selects quantity <quantity> of "<pack>" Ltr pack
    Then User enters a valid pincode "<pincode>" and check product availability
    And User click on add to cart button
    Then User click on View cart & Checkout button
    Then User clicks the cart icon on the header and removes the product from the cart if available
    And Cart count should be updated to "<expectedCount>"

    Examples: 
      | navmenu  | navtab          | productname      | Colour     | quantity | pack | pincode | desiredQuantity | address | expectedQuantity | expectedCount |
      | Products | Exterior paints | Calista Neo Star | Jaipur red |        1 |    1 |  400703 |               5 | Mumbai  |                5 |             0 |
