/*
 * EmployerApplicationLIstTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.authenticated.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
		final String periodoEjecucionFinal, final String cargaTrabajo, final String cargaTrabajoMinutos, 
		final String descripcion, final String enlace) {		
		super.signIn("user1", "user1");
		
		super.clickOnMenu("Authenticated", "List finished tasks");		
		
		super.checkColumnHasValue(recordIndex, 0, titulo);
		super.checkColumnHasValue(recordIndex, 1, periodoEjecucionInicio);
		super.checkColumnHasValue(recordIndex, 2, periodoEjecucionFinal);
		super.checkColumnHasValue(recordIndex, 3, cargaTrabajo);
		super.checkColumnHasValue(recordIndex, 4, descripcion);
		super.checkColumnHasValue(recordIndex, 5, enlace);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("publica", publica);
		super.checkInputBoxHasValue("titulo", titulo);		
		super.checkInputBoxHasValue("periodoEjecucionInicio", periodoEjecucionInicio);
		super.checkInputBoxHasValue("periodoEjecucionFinal", periodoEjecucionFinal);
		super.checkInputBoxHasValue("cargaTrabajo", cargaTrabajo);
		super.checkInputBoxHasValue("cargaTrabajoMinutos", cargaTrabajoMinutos);
		super.checkInputBoxHasValue("descripcion", descripcion);
		super.checkInputBoxHasValue("enlace", enlace);
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------

}
