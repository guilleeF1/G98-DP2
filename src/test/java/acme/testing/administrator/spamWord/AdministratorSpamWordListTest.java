package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordListTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/updateSpamWord/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listSpamWord(final int recordIndex, final String word) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam words");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		this.signOut();
	}
}
