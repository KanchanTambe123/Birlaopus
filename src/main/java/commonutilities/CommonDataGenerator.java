package commonutilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import com.github.javafaker.Faker;

public class CommonDataGenerator {

	private Faker faker;
	private Random random;

	// Constructor to initialise Faker and Random
	public CommonDataGenerator() {
		this.faker = new Faker();
		this.random = new Random();
	}
	

	// Method to generate a fake mobile number where the first digit is 8 or 9
	public String generateFakeMobileNumber() {
		StringBuilder phoneNumber = new StringBuilder();

		// First digit should be either 8 or 9
		phoneNumber.append(random.nextInt(2) + 8); // This will randomly append 8 or 9

		// Append remaining 9 digits randomly
		for (int i = 0; i < 9; i++) {
			phoneNumber.append(random.nextInt(10)); // Random digit between 0-9
		}

		return phoneNumber.toString();
	}

	// Method to generate a fake email address using Faker
	public String generateFakeEmail() {
		return faker.internet().emailAddress();
	}

	// Method to generate a fake first name using Faker
	public String generateFakeFirstName() {
		return faker.name().firstName();
	}

	// Method to generate a fake last name using Faker
	public String generateFakeLastName() {
		return faker.name().lastName();
	}
	
	
	
	public String generateFakeFullName() {
		return faker.name().fullName();
	}

	// Method to generate a fake date of birth (DOB) using LocalDate
	public String generateFakeDOB() {
		// Generate random year, month, and day
		int year = random.nextInt(30) + 1970; // Random year between 1970 and 1999
		int month = random.nextInt(12) + 1; // Random month between 1 and 12
		int day = random.nextInt(28) + 1; // Random day between 1 and 28 to avoid invalid dates

		// Create a LocalDate and format it into "dd-MM-yyyy"
		LocalDate dob = LocalDate.of(year, month, day);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return dob.format(formatter);
	}

	// Method to generate a fake anniversary date (similar to DOB)
	public String generateFakeAnniversaryDate() {
		// Generate random year, month, and day for anniversary date
		int year = random.nextInt(20) + 2000; // Random year between 2000 and 2019
		int month = random.nextInt(12) + 1; // Random month between 1 and 12
		int day = random.nextInt(28) + 1; // Random day between 1 and 28

		// Create a LocalDate and format it into "dd-MM-yyyy"
		LocalDate anniversary = LocalDate.of(year, month, day);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return anniversary.format(formatter);
	}
	
	 public String generateFakeAddress() {
	        return faker.address().fullAddress();
	    }
	 public String generateFakeJobRole() {
			// Faker has job titles built-in
			return faker.job().title();
		}

		// Generate a fake company name for "CurrentCompany"
		public String generateFakeCurrentCompany() {
			return faker.company().name();
		}

		// Generate a fake industry name
		public String generateFakeIndustry() {
			// Faker does not have a direct method for industry, so using job field or custom list
			String[] industries = {
				"Information Technology", "Finance", "Healthcare", "Retail", "Manufacturing", "Education",
				"Transportation", "Hospitality", "Telecommunications", "Construction", "Legal", "Marketing"
			};
			return industries[random.nextInt(industries.length)];
		}
		 public String generateFakeGSTINForMaharashtra() {
		        String stateCode = "27"; // Maharashtra
		        String pan = generateFakePAN();
		        String entityNumber = "1";
		        String zChar = "Z";
		        String checkDigit = getRandomAlphaNumericChar(); // Fake check digit
		        return stateCode + pan + entityNumber + zChar + checkDigit;
		    }

		    private String generateFakePAN() {
		        StringBuilder pan = new StringBuilder();
		        for (int i = 0; i < 5; i++) {
		            pan.append((char) (random.nextInt(26) + 'A'));
		        }
		        for (int i = 0; i < 4; i++) {
		            pan.append(random.nextInt(10));
		        }
		        pan.append((char) (random.nextInt(26) + 'A'));
		        return pan.toString();
		    }

		    private String getRandomAlphaNumericChar() {
		        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		        return String.valueOf(chars.charAt(random.nextInt(chars.length())));
		    }
}