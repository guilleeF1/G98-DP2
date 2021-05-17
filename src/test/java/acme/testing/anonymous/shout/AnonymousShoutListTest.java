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

import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listShoutsWithAdministrator(final Date moment, final String author, final String text, final String info) {		

		super.clickOnMenu("Anonymous", "Shouts");
		
		//final ZoneId zona = ZoneId.systemDefault();
		//final Date fechaAhora = Date.from(LocalDate.now().atStartOfDay(zona).toInstant());
		
		
		//super.checkColumnHasValue(moment, moment.before(fechaAhora));
		
		super.checkInputBoxHasValue("author", author);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("info", info);
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
