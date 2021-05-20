package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordUpdateTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/updateSpamWord/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdateSpamWord(final int recordIndex, final String word) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam words");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("word", word);
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Spam words");
		super.checkColumnHasValue(recordIndex, 0, word);
		
		this.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/updateSpamWord/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeUpdateSpamWord(final int recordIndex, final String word) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam words");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("word", word);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		this.signOut();
	}
}
