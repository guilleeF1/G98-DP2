/*
 * SignUpTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SignUpTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	//Prueba de registrarse en la app con todos los atributos rellenados correctamente
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSignUp(final String username, final String password, final String name, final String surname, final String email) {
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);
		super.signOut();
	}
	//Como resultado obtenemos la creación de una nueva cuenta correctamente para registrarnos en la app.
	
	//----------------------------------------------------------------------------------------------------------------
	
	//Prueba de registrarse en la app con varios errores en los atributos a rellenar en el formulario ya que name, 
	//surname y email no pueden estar vacios y el email debe de tener su formato correctamente.
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeSignUp(final String username, final String password, final String name, final String surname, final String email) {
		super.signUpNegative(username, password, name, surname, email);
	}
	//Como resultado obtenemos errores de validaciones puesto que no se han rellenado correctamente estos atributos citados anteriormente
	
	//-------------------------------------------------------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------
	
}
