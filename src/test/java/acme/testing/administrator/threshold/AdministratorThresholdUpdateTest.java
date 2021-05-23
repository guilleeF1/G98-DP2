package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdUpdateTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdateThreeshold(final int recordIndex, final String umbral) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam threshold");
		
		super.fillInputBoxIn("umbral", umbral);
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Spam threshold");
		super.checkInputBoxHasValue("umbral", umbral);
		
		this.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeUpdateThreeshold(final int recordIndex, final String umbral) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam threshold");
			
		super.fillInputBoxIn("umbral", umbral);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		this.signOut();
	}
//	
//	@Test
//	@CsvFileSource( encoding = "utf-8", numLinesToSkip = 1)
//	@Order(10)
//	public void negativeListThreeshold(final int recordIndex, final String umbral) {
//		
//		super.signIn("manager1", "manager1");
//		super.clickOnMenu("Administrator", "Spam threshold");		
//		super.navigate("/administrator/threshold/update","");
//        super.checkPanicExists();
//		
//		this.signOut();
//	}
}
