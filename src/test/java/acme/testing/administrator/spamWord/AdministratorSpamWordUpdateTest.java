package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordUpdateTest extends AcmePlannerTest {

	//Prueba que verifica que las palabras de spam se pueden actualizar dada una entrada de texto
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/updateSpamWord/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
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
	//Se espera que las inputs que hemos puesto en el csv sean admitidos y verificamos que los cambios se han aplicado correctamente
	
	//Prueba que verifica que si introducimos datos erróneos, no nos permite actualizar la palabra de spam
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/updateSpamWord/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
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
	//Se espera que las inputs que hemos puesto en el csv no sean admitidos
}
