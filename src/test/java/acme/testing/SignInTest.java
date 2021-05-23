package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SignInTest extends AcmePlannerTest {
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		//Prueba de loguearse en la app con su usuario y contraseña correctamente 
		@ParameterizedTest
		@CsvFileSource(resources = "/sign-in/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveSignIn(final String username, final String password) {
			super.signIn(username, password);
			super.signOut();
		}
		//Como resultado obtenemos el logueo como el usuario que nos hayamos logueado en la app.
		
		//----------------------------------------------------------------------------------------------------------------
		
		//Prueba de loguearse en la app con un usuario que no 
		//ha sido registrado en la app (antes hay que registrarse con dicho usuario).
		@ParameterizedTest
		@CsvFileSource(resources = "/sign-in/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void negativeSignIn(final String username, final String password) {
			super.signInNegative(username, password);
		}
		//Como resultado de la prueba de logueo obtenemos un error de inicio de 
		//sesión ya que dicho usuario no está registrado en la app.
		
		//-------------------------------------------------------------------------------------------------------------

		// Ancillary methods ------------------------------------------------------

}
