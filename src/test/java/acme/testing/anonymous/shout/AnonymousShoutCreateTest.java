package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void createShoutWithAnonymousPositive(final int recordIndex, final String author, final String text) {
			super.clickOnMenu("Anonymous", "Shout!");

			super.fillInputBoxIn("author", author);
			super.fillInputBoxIn("text", text);
			super.clickOnSubmitButton("Shout!");

			super.clickOnMenu("Anonymous", "List shouts");		
			
			super.checkColumnHasValue(recordIndex, 1, author);
			super.checkColumnHasValue(recordIndex, 2, text);
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void createShoutWithAnonymousNegative(final int recordIndex, final String author, final String text) {
			super.clickOnMenu("Anonymous", "Shout!");

			super.fillInputBoxIn("author", author);
			super.fillInputBoxIn("text", text);
			super.clickOnSubmitButton("Shout!");

			super.checkErrorsExist();
		}

		// Ancillary methods ------------------------------------------------------

}
