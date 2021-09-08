package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		// Prueba de creación de shouts con sus atributos añadidos correctamente entrando en la api sin loguearse (Anónimo)
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void createShoutWithAnonymousPositive(final int recordIndex, final String author, final String text, final String infoSheetDate, final String infoSheetMoney, final String currency, final String flag) {
			super.clickOnMenu("Anonymous", "Shout!");

			super.fillInputBoxIn("author", author);
			super.fillInputBoxIn("text", text);
			super.fillInputBoxIn("date", infoSheetDate);
			super.fillInputBoxIn("amount", infoSheetMoney);
			super.fillInputBoxIn("currency", currency);
			super.fillInputBoxIn("flag", flag);
			super.clickOnSubmitButton("Shout!");

			super.clickOnMenu("Anonymous", "List shouts");		
			
			super.checkColumnHasValue(recordIndex, 1, author);
			super.checkColumnHasValue(recordIndex, 2, text);
			super.checkColumnHasValue(recordIndex, 3, infoSheetDate);
			super.checkColumnHasValue(recordIndex, 5, infoSheetMoney + " " + currency);
		}
		
		//Como resultado esta prueba muestra el shout creado correctamente tal y como aparece en csv indicado en la url de resources añadiendolo en la lista de todos los shouts 
		
		//-------------------------------------------------------------------------------------------------------------------------------------
		
		// Prueba de creación de shouts entrando en la api sin loguearse (Anónimo) con el atributo autor no añadido correctamente ya que debe de tener como mínimo 5 carácteres
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void createShoutWithAnonymousNegative(final int recordIndex, final String author, final String text, final String infoSheetDate, final String infoSheetMoney) {
			super.clickOnMenu("Anonymous", "Shout!");

			super.fillInputBoxIn("author", author);
			super.fillInputBoxIn("text", text);
			super.fillInputBoxIn("date", infoSheetDate);
			super.fillInputBoxIn("amount", infoSheetMoney);
			super.clickOnSubmitButton("Shout!");

			super.checkErrorsExist();
		}
		
		//Como resultado esta prueba no muestra el shout creado correctamente tal y como aparece en csv indicado en la url de resources en la lista de todos los shouts a causa de este error de validación en el atributo autor
		
		//------------------------------------------------------------------------------------------------------

		// Ancillary methods ------------------------------------------------------

}