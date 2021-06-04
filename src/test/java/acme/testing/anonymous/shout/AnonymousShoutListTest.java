/*
 * EmployerJobListAllTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
		// Test cases -------------------------------------------------------------
		
		// Prueba de comprobación de que se pueda obtener la lista de todos los shouts correctamente entrando en la api sin loguearse (Anónimo)
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	
		public void listShoutsWithAnonymous(final int recordIndex, final String moment, final String author, final String text, final String timeAttribute, final String isFlag, final String amount, final String currency) {
			
			super.clickOnMenu("Anonymous", "List shouts");
			
			super.checkColumnHasValue(recordIndex, 1, author);
			super.checkColumnHasValue(recordIndex, 2, text);
			super.checkColumnHasValue(recordIndex, 4, timeAttribute);
			super.checkColumnHasValue(recordIndex, 5, isFlag);
			super.checkColumnHasValue(recordIndex, 6, amount);
			super.checkColumnHasValue(recordIndex, 7, currency);

		}
		
		//Como resultado esta prueba muestra la lista de todos los shouts tal y como aparece en el csv de la url indicada en resources incluyendo los shouts que se hayan creado correctamente
		
		//------------------------------------------------------------------------------------------------------

		//Este test comprueba que un usuario registrado no puede acceder a shout, ya que es una funcionalidad de anónimo.
		@Test
		@Order(20)
		public void listShoutNegative() {		
			super.signIn("user1", "user1");	
			
			super.navigate("/anonymous/shout/list","");
			
			super.checkPanicExists();
			
			super.signOut();
		}
		//Como resultado, se generará un error de tipo Panic, ya que estamos accediendo a una funcionalidad para la que no tenemos los permisos adecuados
		
		//------------------------------------------------------------------------------------------------------
		
		//Este test comprueba que tampoco los admin pueden acceder a la funcionalidad shout.
		@Test
		@Order(30)
		public void listShoutNegative2() {		
			super.signIn("administrator", "administrator");	
			
			super.navigate("/anonymous/shout/list","");
			
			super.checkPanicExists();
			
			super.signOut();
		}
				//Como resultado, se generará un error de tipo Panic, ya que estamos accediendo a una funcionalidad para la que no tenemos los permisos adecuados

		
		//Este test verifica que todos los shouts que haya en esta lista no sean más antiguos que un mes. Se usará el record index del archivo csv para recorrer la lista

		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(40)
		public void listShoutNotOlderThanOneMonth(final int recordIndex) {
			super.clickOnMenu("Anonymous", "List shouts");
			
			super.checkMoment(recordIndex);
		}
		// Debe devolver que todas las fechas recorridas son de posteriores a hace un mes
}