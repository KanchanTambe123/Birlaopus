Feature: To Validate the Brand section Form and Book a Free Consultation form on the homepage.

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #----------------------------------1---------------------------------------------------->
  Scenario Outline: To verify mandatory fields with valid data on the Let’s Connect form in the Brands section for a logged-in user
    Given User is on the Birla Opus Brands page  "BrandUrl"
    And User navigates to the Let’s Connect form section
    #And User enters a valid Name on the Let’s Connect form Brands
    When User selects "<HelpOption>" from the How can we help you? dropdown
    And User enters a valid Pincode on the Let’s Connect form Brands "<Pincode>"
    And User clicks on the Submit button
    And User captures and validates API request and response for "lead/shortForm"
    #And verify the lead API parameters for Brands Enquiry form  : iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
    And User should see the acknowledgment message after successful submission "Thank You For Reaching out to us !"

    Examples: 
      | HelpOption      | Pincode |
      | Product Enquiry |  400703 |

  #----------------------------------2---------------------------------------------------->
  Scenario Outline: To verify validation messages when mandatory fields are empty in Let’s Connect form (Brands)
    Given User is on the Birla Opus Brands page  "BrandUrl"
    And User clicks on the Submit button
    #And validation message for Name input fields should get displayed "This field is required"
    And validation message for HelpOption input fields should get displayed "This field is required"
    And validation message for Pincode input fields should get displayed "This field is required"

  #----------------------------------3---------------------------------------------------->
  #Scenario Outline: To verify mandatory fields with valid data on the Let’s Connect form (Become a dealer)
  #Given User is on the Birla Opus Become a dealer page  "BecomedealerUrl"
  # When User selects Become a dealer Let’s Connect form "<UserType>" from the Who are you ? dropdown
  # And User selects Become a dealer Let’s Connect form "<HelpOption>" from the How can we help you? dropdown
  # And User enters a valid Pincode on the Become a dealer Let’s Connect form  "<Pincode>"
  # And User clicks on the Submit button
  #And User should see the acknowledgment message after successful submission
  #Examples:
  # | UserType             | Pincode | HelpOption                 |
  # | Dealer / Retailer    |  411033 | Become a Dealer            |
  # | Painter / Contractor |  400703 | Complaints / Feedback      |
  #| Designer / AID       |  411033 | General Enquiries / Others |
  #----------------------------------4---------------------------------------------------->
  #Scenario Outline: To verify mandatory fields with valid data on the Let’s Connect form (Become a contractor)
  # Given User is on the Birla Opus Become a dealer page  "BecontractorUrl"
  # When User selects Become a dealer Let’s Connect form "<UserType>" from the Who are you ? dropdown
  # And User selects Become a dealer Let’s Connect form "<HelpOption>" from the How can we help you? dropdown
  # And User enters a valid Pincode on the Become a dealer Let’s Connect form  "<Pincode>"
  # And User clicks on the Submit button
  # And User should see the acknowledgment message after successful submission
  #Examples:
  #| UserType             | Pincode | HelpOption                 |
  #| Painter / Contractor |  400703 | Crew / Grasim relationship |
  #| Painter / Contractor |  411033 | General Enquiries / Others |
  #----------------------------------3---------------------------------------------------->
  Scenario Outline: To verify that the Back button works after entering form data in the Book a Free Consultation form on the homepage
    And User navigates to the Book a free consultation form section
    Then User clicks on Book a Free Consultation button
    And the user clicks on the Back button on the Book a Free Consultation form
    Then the user should be navigated to the previous page of the Book a Free Consultation form

  #----------------------------------4---------------------------------------------------->
  Scenario Outline: To verify validation messages when mandatory fields are empty in the Book a Free Consultation form on the homepage
    And User navigates to the Book a free consultation form section
    Then User clicks on Book a Free Consultation button
    Then the user clicks on the Continue button on the Book a Free Consultation form
    And validation message for Pincode input fields should get displayed "This field is required"

  #----------------------------------5---------------------------------------------------->
  Scenario Outline: To verify that an error message is displayed for an invalid pincode on the Book a Free Consultation form for a logged-in user
    And User navigates to the Book a free consultation form section
    Then User clicks on Book a Free Consultation button
    And User enters a invalid pincode on the Book a Free Consultation form  "<PinCode>"
    Then the user clicks on the Continue button on the Book a Free Consultation form
    Then error message should be displayed for invalid pin code "Please enter a valid Pincode"

    Examples: 
      | PinCode |
      |     123 |
