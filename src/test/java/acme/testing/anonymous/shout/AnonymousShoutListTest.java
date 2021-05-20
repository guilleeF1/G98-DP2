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
		public void listShoutsWithAnonymous(final int recordIndex, final String moment, final String author, final String text) {
			
			super.clickOnMenu("Anonymous", "List shouts");		
			
			super.checkColumnHasValue(recordIndex, 0, moment);
			super.checkColumnHasValue(recordIndex, 1, author);
			super.checkColumnHasValue(recordIndex, 2, text);

		}
		
		//Como resultado esta prueba muestra la lista de todos los shouts tal y como aparece en el csv de la url indicada en resources incluyendo los shouts que se hayan creado correctamente
		
		//------------------------------------------------------------------------------------------------------

		
		// Ancillary methods ------------------------------------------------------


}