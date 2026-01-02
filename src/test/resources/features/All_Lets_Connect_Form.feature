@Report
Feature: To Validate Brand Inquiry Form

  Background: 
    Given User is on BirlaOpus HomePage "birlaopusHomeUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #----------------------------------1---------------------------------------------------->
Scenario Outline: To verify mandatory fields on the Let’s Connect form (Brands) with valid data for a logged-in user

    Given User is on the Birla Opus Brands page  "BrandUrl"
    And User navigates to the Let’s Connect form section
    And User enters a valid Name on the Let’s Connect form Brands
    When User selects "<HelpOption>" from the How can we help you? dropdown
    And User enters a valid Pincode on the Let’s Connect form Brands "<Pincode>"
    And User clicks on the Submit button
    And User should see the acknowledgment message after successful submission

    Examples: 
      | HelpOption      | Pincode |
      | Product Enquiry |  400703 |

  #----------------------------------2---------------------------------------------------->
  Scenario Outline: To verify validation messages when mandatory fields are empty in Let’s Connect form (Brands)
    Given User is on the Birla Opus Brands page  "BrandUrl"
    And User clicks on the Submit button
    And validation message for Name input fields should get displayed "This field is required"
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
