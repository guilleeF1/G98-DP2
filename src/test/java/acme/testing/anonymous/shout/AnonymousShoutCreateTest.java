package acme.testing.anonymous.shout;

import java.util.Date;

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
		public void createShoutWithAdministratorPositive(final Date moment, final String author, final String text, final String info) {

			super.clickOnMenu("Anonymous", "Create a shout");

			super.fillInputBoxIn("moment", moment.toString());
			super.fillInputBoxIn("author", author);
			super.fillInputBoxIn("text", text);
			super.fillInputBoxIn("info", info);
			
			super.clickOnSubmitButton("Create");

			super.clickOnMenu("Administrator", "Shouts");
			
			//final ZoneId zona = ZoneId.systemDefault();
			//final Date fechaAhora = Date.from(LocalDate.now().atStartOfDay(zona).toInstant());
			
			//super.checkColumnHasValue(moment.toString(), moment.before(fechaAhora));

			super.checkInputBoxHasValue("moment", moment.toString());
			super.checkInputBoxHasValue("author", author);
			super.checkInputBoxHasValue("text", text);
			super.checkInputBoxHasValue("info", info);
			super.signOut();
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void createShoutWithAdministratorNegative(final Date moment, final String author, final String text, final String info) {

			super.clickOnMenu("Anonymous", "Create a shout");

			super.checkInputBoxHasValue("moment", moment.toString());
			super.checkInputBoxHasValue("author", author);
			super.checkInputBoxHasValue("text", text);
			super.checkInputBoxHasValue("info", info);
			super.clickOnSubmitButton("Create");

			super.checkErrorsExist();

			super.signOut();
		}

		// Ancillary methods ------------------------------------------------------

}
