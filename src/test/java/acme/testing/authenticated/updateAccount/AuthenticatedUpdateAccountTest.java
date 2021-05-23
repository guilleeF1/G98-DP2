package acme.testing.authenticated.updateAccount;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class AuthenticatedUpdateAccountTest extends AcmePlannerTest {
	
	//Prueba de la actualización de los atributos correctamente de la cuenta con la que nos hemos logueado
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/account/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositiveAccount(final String username, final String password, final String confirmation, 
		final String name, final String surname, final String email) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Account", "General data");	
		
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", confirmation);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("surname", surname);
		super.fillInputBoxIn("email", email);
		
		super.clickOnSubmitButton("Update");
		
		super.signOut();
	}
	//Como resultado obtenemos la actualización de los atributos que hayamos cambiado de nuestra cuenta
	
	//-----------------------------------------------------------------------------------------------------
	
	//En esta prueba podemos comprobar que no se hace correctamente la actualización de los atributos de nuestra cuenta 
	//ya que la contraseña no está bien confirmada y el name, el surname y el email no están escritos correctamente.
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/account/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updateNegativeAccount(final int recordIndex, final String username, final String password, final String confirmation, 
		final String name, final String surname, final String email) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Account", "General data");	
		
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", confirmation);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("surname", surname);
		super.fillInputBoxIn("email", email);
		
		super.clickOnSubmitButton("Update");
		
		super.signOut();
	}
	//Como resultado no podemos obtener la actualización de dichos atributos puesto que no están escritos correctamente.

}
