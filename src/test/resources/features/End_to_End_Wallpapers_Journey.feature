@Scope2
Feature: To Validate the happy end-to-end Wallpapers journey flow for a logged-in user, including adding and removing Wallpapers and managing the wishlist.

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify the end-to-end Wallpapers journey through the checkout process with valid data for a logged-in user through navigation
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    Then User clicks the cart icon on the header and removes the product from the cart if available
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 wallpaper name "<productname>" through navigation bar
    Then the User selects a wallpaper shade "<shades_code>"
    Then User enters valid pincode "<pincode>" and check product availability
    And User click on add to cart button on wallpaper section
    Then User click on View cart & Checkout button on wallpaper section
    And User increase the product quantity "<desiredQuantity>"
    Then User clicks on the Proceed to Enter Address button
    And User clicks on the Apply button, verifies the availability of coupon vouchers, and applies a coupon if available
    And User verifies the product quantity in the final order summary "<expectedQuantity>"
    Then the total payable amount should be correctly calculated and displayed
    And User clicks on the Proceed to Shipment button
    Then User clicks on the Proceed to payment button

    Examples: 
      | navmenu | navtab     | productname          | shades_code | quantity | pack | pincode | desiredQuantity | address | expectedQuantity |
      | Shop    | Wallpapers | Textilia Tabit 32001 |       32102 |        1 |    1 |  400703 |               2 | Mumbai  |                2 |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify that a logged-in user can successfully add a Wallpapers to the cart through navigation
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    Then User clicks the cart icon on the header and removes the product from the cart if available
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 wallpaper name "<productname>" through navigation bar
    Then the User selects a wallpaper shade "<shades_code>"
    Then User enters a valid pincode "<pincode>" on wallpaper
    And User click on pincode serviceability check button on wallpaper
    And User click on add to cart button on wallpaper section
    Then User click on View cart & Checkout button on wallpaper section
    Then Product should be added to the cart successfully
    Then User clicks the cart icon on the header and removes the product from the cart if available

    Examples: 
      | navmenu | navtab     | productname          | shades_code | quantity | pack | pincode | desiredQuantity | address | expectedQuantity |
      | Shop    | Wallpapers | Textilia Tabit 32001 |       32102 |        1 |    1 |  500001 |               2 | Mumbai  |                2 |

  #---------------------------------- Scenario 3 ----------------------------------#
  Scenario Outline: To verify that a logged-in user can remove a Wallpapers product from the header cart(cart page)
    Given User is on wallpaper Product page "WallpaperProductUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    Then the User selects a wallpaper shade "<shades_code>"
    Then User enters a valid pincode "<pincode>" on wallpaper
    And User click on pincode serviceability check button on wallpaper
    And User click on add to cart button on wallpaper section
    Then User clicks the cart icon on the header and removes the product from the cart if available
    And Cart count should be updated to "<expectedCount>"

    Examples: 
      | navmenu | navtab     | productname               | shades_code | quantity | pack | pincode | desiredQuantity | address | expectedCount |
      | Shop    | Wallpapers | Chromatic Geometric 45124 |       45128 |        1 |    1 |  500001 |               2 | Mumbai  |             0 |

  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify successful Wallpapers add-to-wishlist functionality through navigation for a logged-in user
    Given User is on wallpaper Product page "WallpaperProductUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    #Then the User selects a wallpaper shade "<shades_code>"
    And User clicks the Wishlist icon for Wallpapers on the Product Details page
    Then Product should be added to the wishlist successfully

    Examples: 
      | navmenu | navtab     | productname               | optionText          | shades_code |
      | Shop    | Wallpapers | Chromatic Geometric 45124 | Delete colour story |       45128 |

  #---------------------------------- Scenario 5 ----------------------------------#
  Scenario Outline: To verify successful Wallpapers remove-to-wishlist functionality through navigation for a logged-in user.(profile page)
    Given User is on wallpaper Product page "WallpaperProductUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    Then the User selects a wallpaper shade "<shades_code>"
    And User clicks the Wishlist icon for Wallpapers on the Product Details page
    Then Product should be added to the wishlist successfully
    Then User click whishlist icon on top page
    And User clicks on "<tabName>" tab
    Then the User clicks the product option button in favourites
    And the User selects "<optionText>" from the product options in the wishlist
    Then Product should be removed from the wishlist successfully

    Examples: 
      | navmenu | navtab     | productname               | tabName    | optionText       | shades_code |
      | Shop    | Wallpapers | Chromatic Geometric 45124 | Favourites | Delete Wallpaper |       45128 |

  #---------------------------------- Scenario 6 ----------------------------------#
  Scenario Outline: To verify error message when user enters invalid pincode for wallpaper
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 wallpaper name "<productname>" through navigation bar
    Then the User selects a wallpaper shade "<shades_code>"
    Then User enters a valid pincode "<pincode>" on wallpaper
    And User click on pincode serviceability check button on wallpaper
    Then User should see an error message for invalid pincode for Wallpapers "<errorMessage>"

    Examples: 
      | navmenu | navtab     | productname          | shades_code | quantity | pack | pincode | desiredQuantity | address | expectedQuantity | errorMessage                 |
      | Shop    | Wallpapers | Textilia Tabit 32001 |       32102 |        1 |    1 |     400 |               2 | Mumbai  |                2 | Please enter a valid pincode |

  #---------------------------------- Scenario 7 ----------------------------------#
  Scenario Outline: To verify error message when pincode field is empty for wallpaper
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon
    When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 wallpaper name "<productname>" through navigation bar
    Then the User selects a wallpaper shade "<shades_code>"
    And User click on pincode serviceability check button on wallpaper
    Then User should see the error message when pincode field is empty "This field is required"

    Examples: 
      | navmenu | navtab     | productname          | shades_code | quantity | pack | pincode | desiredQuantity | address | expectedQuantity |
      | Shop    | Wallpapers | Textilia Tabit 32001 |       32102 |        1 |    1 |  400703 |               2 | Mumbai  |                2 |
  #---------------------------------- Scenario 8 ----------------------------------#
  #Scenario Outline: To verify user cannot proceed when invalid quantity is selected
    #When User select product main navigation L1 "<navmenu>", sub navigation L2 "<navtab>" and L3 wallpaper name "<productname>" through navigation bar
    #Then the User selects a wallpaper shade "<shades_code>"
    #Then Add to cart button should be disabled
#
    #Examples: 
      #| navmenu | navtab     | productname          | shades_code | quantity | pack | pincode | desiredQuantity | address | expectedQuantity |
      #| Shop    | Wallpapers | Textilia Tabit 32001 |           32102 |        1 |    1 |  400703 |               2 | Mumbai  |                2 |
