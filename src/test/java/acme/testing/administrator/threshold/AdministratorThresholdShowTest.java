package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdShowTest extends AcmePlannerTest {

	//Prueba que verifica que los umbrales son visibles para los administradores
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listShowTreshold(final int recordIndex, final String umbral) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam threshold");
		
		super.checkInputBoxHasValue("umbral", umbral);		
		this.signOut();
	}
	//Se espera que lo que se detecte en la prueba es lo que hemos listado en el csv al acceder al umbral

	//Prueba que verifica que sólo los administradores pueden ver el umbral 	
	@Test
	@Order(10)
	public void negativeShowThreeshold() {
		
		super.signIn("manager1", "manager1");	
		super.navigate("/administrator/threshold/show","");
        super.checkPanicExists();
		
		this.signOut();
	}
	//Se espera que encontremos una ventana de panic al acceder de forma ilegal a la vista
}
