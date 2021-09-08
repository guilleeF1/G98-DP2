package acme.testing.authenticated.becomeamanager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AdministratorBecomeAManagerTest extends AcmePlannerTest {
	
	//Prueba de convertirse en un manager logueandose en la app como administrador
	@Test
	@Order(10)
	public void administratorBecomeAManager() {
		
		super.signIn("user1", "user1");
		super.clickOnMenu("Account", "Become a manager");
		
		super.clickOnSubmitButton("Become manager");
		
		this.signOut();
	}
	//Como resultado de la prueba obtenemos el menú disponible en la app al loguearse como manager

}
