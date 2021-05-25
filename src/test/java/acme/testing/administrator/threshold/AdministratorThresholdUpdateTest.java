package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdUpdateTest extends AcmePlannerTest {
	//Prueba que verifica que el umbral se pueden actualizar dada una entrada de texto
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
	//Se espera que entrando de nuevo en el menú tras editar el valor, salga el valor que le hemos asignado
	
	//Dada una entrada inapropiada de texto, esta prueba verifica que esta funcionalidad sólo admite ciertos parámetros
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
	//Se espera que dando valores inapropiados en el campo de texto de esta función, obtengamos errores
}
