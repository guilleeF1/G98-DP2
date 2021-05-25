package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardTest extends AcmePlannerTest {
	//En esta prueba comprobamos que los valores visibles en el dashboard son los esperamos, para verificar que los administradores pueden acceder a esta vista
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/values.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void checkDashboard(final String recordIndex, final String value) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");

		By locator;
		
		locator = By.id(recordIndex);
		
		final String found = super.locateOne(locator).getText();
		
		assert value.equals(super.locateOne(locator).getText()) : "Expected value " + value + " does not match with found value " + found;
		
		this.signOut();
	}
	//Se espera que todos los valores del dashboard coincidan con los del csv
	
	//Verificamos que un usuario no administrador no pueda acceder al dashboard
	@Test
    @Order(10)
    public void dashboardNegative() {

        super.navigate("/administrator/dashboard/show","");

        super.checkPanicExists();

    }
	//Esperamos que haya un panic a raíz de esta acción
}
