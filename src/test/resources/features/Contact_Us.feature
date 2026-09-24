Feature: To Validate Contact Us Form

  Background: 
    Given User is on the Contact us form "ContactUsUrl"
    When User clicks on the profile icon
    And User clicks on the Sign In button
    And User enters valid mobile number on the Sign In page
    And the User clicks on the Sign In button after entering the mobile number
    And User enters valid OTP and clicks on the Verify OTP button
    Then User clicks on the close icon

  #---------------------------------- Scenario 1 ----------------------------------#
  Scenario Outline: To verify successful submission of contact us form for a logged-in user( Select-option 1.I am a customer-Painting Service Enquiry and option 2.Complaints / Feedback )
    When User selects Who Are You as "<Option>"
    Then User selects How can we help you ? as "<Option1>"
    And User enters a valid serviceable pincode in the Contact Us form "<pincode>"
    Then User enters a valid email ID in the Contact Us form
    And User enter valid Concern and Queries as "<Concern_Query>"
    Then User clicks on the Submit button in the Contact Us form
    And User captures and validates API request and response for "lead/shortForm"
    And verify the lead API parameters for contact us form : iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
    And User enter Site Details project name
    Then User click next button on Address section page for contact us form
    And User clicks on the Next button on the Share Few Details section
    Then User selects the painting requirement type "<requirementType>"
    And User clicks on the project details next button
    Then User select home configuration type "<bhkType>"
    Then User enters the carpet area "<carpetArea>"
    And User clicks on the project details next button
    And User selects a schedule visit date and timeslot "<time>"
    And User clicks on the Schedule button
    Then contact us form confirmation message should be displayed successfully "Thank you for sharing your details & scheduling a survey with us!"

    Examples: 
      | Option          | Option1                  | pincode | Concern_Query | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource | requirementType | carpetArea | bhkType | address | time         |
      | I am a customer | Painting Service Enquiry |  500001 | no            | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Contact Us    | Exteriors       |       1200 | 2 BHK   | pune    | 12 PM - 3 PM |
      | I am a customer | Complaints / Feedback    |  400703 | no            | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Contact Us    | Exteriors       |       1200 | 2 BHK   | pune    | 12 PM - 3 PM |

  #---------------------------------- Scenario 2 ----------------------------------#
  Scenario Outline: To verify successful submission of contact us form for a logged-in user( Select-option 1.Painter / Contractor-Become a painter / Contractor and option 2.General Enquiries / Others)
    When User selects Who Are You as "<Option>"
    Then User selects How can we help you ? as "<Option1>"
    And User enters a valid serviceable pincode in the Contact Us form "<pincode>"
    Then User enters a valid email ID in the Contact Us form
    And User enter valid Concern and Queries as "<Concern_Query>"
    Then User clicks on the Submit button in the Contact Us form
    And User captures and validates API request and response for "lead/shortForm"
    And verify the lead API parameters for contact us form : iclLeadContextC against value "<iclLeadContextC>",  iclLeadTypeC against value "<iclLeadTypeC>",  iclSubType against value "<iclSubType>",  leadSubSource against value "<leadSubSource>"
    And User enter Site Details project name
    Then User click next button on Address section page for contact us form
    And User clicks on the Next button on the Share Few Details section
    Then User selects the painting requirement type "<requirementType>"
    And User clicks on the project details next button
    Then User select home configuration type "<bhkType>"
    Then User enters the carpet area "<carpetArea>"
    And User clicks on the project details next button
    And User selects a schedule visit date and timeslot "<time>"
    And User clicks on the Schedule button
    Then contact us form confirmation message should be displayed successfully "Thank you for sharing your details & scheduling a survey with us!"

    Examples: 
      | Option               | Option1                       | pincode | Concern_Query | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource | requirementType | carpetArea | bhkType | address | time         |
      | Painter / Contractor | Become a painter / Contractor |  500001 | no            | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Contact Us    | Exteriors       |       1200 | 2 BHK   | pune    | 12 PM - 3 PM |
      | Painter / Contractor | General Enquiries / Others    |  500001 | no            | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Contact Us    | Exteriors       |       1200 | 2 BHK   | pune    | 12 PM - 3 PM |

  #---------------------------------- Scenario 3 ----------------------------------#
  Scenario Outline: To verify successful submission of contact us form for a logged-in user( Select-option 1.Designer / AID and option 2.Partner with Birla Opus)
    When User selects Who Are You as "<Option>"
    Then User selects How can we help you ? as "<Option1>"
    And User enters a valid serviceable pincode in the Contact Us form "<pincode>"
  
    Then User clicks on the Submit button in the Contact Us form
    And User captures and validates API request and response for "lead/shortForm"
    And User should see the acknowledgment message after successful submission "Thank You For Reaching out to us !"
 
    Examples: 
      | Option         | Option1                 | pincode | Concern_Query | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource | requirementType | carpetArea | bhkType | address | time         |
      | Designer / AID | Partner with Birla Opus |  500001 | no            | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Contact Us    | Exteriors       |       1200 | 2 BHK   | pune    | 12 PM - 3 PM |
@test
  #---------------------------------- Scenario 4 ----------------------------------#
  Scenario Outline: To verify successful submission of contact us form for a logged-in user( Select-option 1.Dealer / Retailer and option 2. General Enquiries / Others)
    When User selects Who Are You as "<Option>"
    Then User selects How can we help you ? as "<Option1>"
    Then User clicks on the Submit button in the Contact Us form
    And User captures and validates API request and response for "lead/shortForm"
    And User should see the acknowledgment message after successful submission "Thank You For Reaching out to us !"

    Examples: 
      | Option            | Option1                    | pincode | Concern_Query | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource | requirementType | carpetArea | bhkType | address | time         |
      | Dealer / Retailer | General Enquiries / Others |  500001 | no            | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Contact Us    | Exteriors       |       1200 | 2 BHK   | pune    | 12 PM - 3 PM |

  #---------------------------------- Scenario 5 ----------------------------------#
  Scenario Outline: To verify pop-up is displayed when an unserviceable PIN code is entered
    When User selects Who Are You as "<Option>"
    Then User selects How can we help you ? as "<Option1>"
    And User enters a unserviceable pincode in the Contact Us form "<pincode>"
    Then User enters a valid email ID in the Contact Us form
    And User enter valid Concern and Queries as "<Concern_Query>"
    Then User clicks on the Submit button in the Contact Us form
    And User captures and validates API request and response for "lead/shortForm"
    Then the unserviceable pin code message should be displayed "Coming soon Painting services are currently not available in your area"

    Examples: 
      | pincode | Option          | Option1                  | Concern_Query |
      |  111222 | I am a customer | Painting Service Enquiry | no            |

  #---------------------------------- Scenario 6----------------------------------#
  Scenario Outline: To verify that an error message is displayed when pincode filed empty.
    When User selects Who Are You as "<Option>"
    Then User selects How can we help you ? as "<Option1>"
    #And User enters a valid serviceable pincode in the Contact Us form "<pincode>"
    Then User enters a valid email ID in the Contact Us form
    And User enter valid Concern and Queries as "<Concern_Query>"
    Then User clicks on the Submit button in the Contact Us form
    And validation message for Pincode input fields should get displayed "This field is required"

    Examples: 
      | Option               | Option1                       | pincode | Concern_Query | iclLeadContextC    | iclLeadTypeC             | iclSubType         | leadSubSource | requirementType | carpetArea | bhkType | address | time         |
      | Painter / Contractor | Become a painter / Contractor |  500001 | no            | Paintcraft Service | Painting Service Enquiry | Paintcraft Service | Contact Us    | Exteriors       |       1200 | 2 BHK   | pune    | 12 PM - 3 PM |
