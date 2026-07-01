@Scope1
Feature: To Validate the Shop Happy End-to-End Product Journey Flow for an Existing User

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify the end-to-end product journey through the checkout process with valid data for a logged-in user through navigation
    Then User clicks the cart icon on the header and removes the product from the cart if available
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User click on Shop now button
    Then User selects Colour "<Colour>"
    Then User selects quantity <quantity> of "<pack>" Ltr pack
    Then User enters a valid pincode "<pincode>" and check product availability
    And User click on add to cart button
    Then User click on View cart & Checkout button
    #And User click on whishlist icon
    And User increase the product quantity "<desiredQuantity>"
    Then User clicks on the Proceed to Enter Address button
    And User clicks on the Apply button, verifies the availability of coupon vouchers, and applies a coupon if available
    And User verifies the product quantity in the final order summary "<expectedQuantity>"
    Then the total payable amount should be correctly calculated and displayed
    Then User clicks on the Edit button to update the shipping details.
    And User enters a valid first name for the updated shipping address
    And User enters a valid last name for the updated shipping address
    And User enters a valid mobile number for the updated shipping address
    And User enters a valid shipping address "<address>"
    And User clicks on the Submit button to update the details.
    And User clicks on the Proceed to Shipment button
    Then User clicks on the Proceed to payment button

    Examples: 
      | navmenu  | navtab          | productname      | Colour     | quantity | pack | pincode | desiredQuantity | address | expectedQuantity |
      | Products | Exterior paints | Calista Neo Star | Jaipur red |        1 |    1 |  400703 |               5 | Mumbai  |                5 |

  #---------------------------------- Scenario 2 ----------------------------------#
  #Scenario Outline: To verify that the logged-in user continues shopping and completes checkout successfully via the Continue Shopping button
  #And User clicks the cart icon on the header and removes the product from the cart if available
  #Then User clicks on continue shopping button
  #And User select product category "<productcategory>"
  #Then User select product and click shop now button
  #Then User selects Colour "<Colour>"
  #Then User selects quantity <quantity> of "<pack>" Ltr pack
  #Then User enters a valid pincode "<pincode>" and check product availability
  #And User click on add to cart button
  #Then User click on View cart & Checkout button
  #And User increase the product quantity "<desiredQuantity>"
  #Then User clicks on the Proceed to Enter Address button
  #And User clicks on the Apply button, verifies the availability of coupon vouchers, and applies a coupon if available
  #And User verifies the product quantity in the final order summary "<expectedQuantity>"
  #Then the total payable amount should be correctly calculated and displayed
  #And User clicks on the Proceed to Shipment button
  #Then User clicks on the Proceed to payment button
  # Examples:
  # | Colour    | quantity | pack | pincode | desiredQuantity | address | expectedQuantity | productcategory |
  #| Rose dust |        1 |    1 |  400703 |               2 | Mumbai  |                2 | Interior Paints |
  #---------------------------------- Scenario 3 ----------------------------------#
  Scenario Outline: To verify the end-to-end product journey through the checkout process with valid data for a logged-in user using the search bar
    And User clicks the cart icon on the header and removes the product from the cart if available
    When User clicks on the search icon
    Then User enters the product name "<searchfor>" in the search box
    #And User select product category "<category>"
    And User selects and clicks on the product
    Then User click on Shop now button
    Then User selects Colour "<Colour>"
    Then User selects quantity <quantity> of "<pack>" Ltr pack
    Then User enters a valid pincode "<pincode>" and check product availability
    And User click on add to cart button
    Then User click on View cart & Checkout button
    And User increase the product quantity "<desiredQuantity>"
    Then User clicks on the Proceed to Enter Address button
    And User clicks on the Apply button, verifies the availability of coupon vouchers, and applies a coupon if available
    And User verifies the product quantity in the final order summary "<expectedQuantity>"
    Then the total payable amount should be correctly calculated and displayed
    And User clicks on the Proceed to Shipment button
    Then User clicks on the Proceed to payment button

    Examples: 
      | searchfor       | Colour    | quantity | pack | pincode | desiredQuantity | expectedQuantity |
      | Interior paints | Rose dust |        1 |    1 |  411033 |               2 |                2 |

  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify the validation message when the cart value is less than INR 999 for a logged-in user
    Then User clicks the cart icon on the header and removes the product from the cart if available
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 product name "<productname>" through navigation bar
    Then User click on Shop now button
    Then User selects Colour "<Colour>"
    Then User selects quantity <quantity> of "<pack>" Ltr pack
    Then User enters a valid pincode "<pincode>" and check product availability
    And User click on add to cart button
    Then User click on View cart & Checkout button
    #And User click on whishlist icon
    #And User increase the product quantity "<desiredQuantity>"
    Then User clicks on the Proceed to Enter Address button
    And the validation message should be displayed as "To proceed to checkout, we request a minimum cart value of INR 999."
    Then User clicks on the popup Close button
    And User clicks the cart icon on the header and removes the product from the cart if available

    Examples: 
      | navmenu  | navtab          | productname      | Colour     | quantity | pack | pincode | desiredQuantity |
      | Products | Exterior paints | Calista Neo Star | Jaipur red |        1 |    1 |  400703 |               2 |
