package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardTest extends AcmePlannerTest {
	
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
	
	@Test
    @Order(10)
    public void dashboardNegative() {

        super.navigate("/administrator/dashboard/show","");

        super.checkPanicExists();

    }
	
}
