package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdListTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listSpamWord(final int recordIndex, final String word) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam threshold");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		this.signOut();
	}
}
