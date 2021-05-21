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

package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskListTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// Prueba de comprobación de que se pueda obtener la lista de todas
	//las tasks correctamente entrando en la api logueándose como Manager
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
		final String periodoEjecucionFinal, final String cargaTrabajo, final String cargaTrabajoMinutos, 
		final String descripcion, final String enlace) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My tasks");		
		
		super.checkColumnHasValue(recordIndex, 0, publica);
		super.checkColumnHasValue(recordIndex, 1, titulo);
		super.checkColumnHasValue(recordIndex, 2, periodoEjecucionInicio);
		super.checkColumnHasValue(recordIndex, 3, periodoEjecucionFinal);
		super.checkColumnHasValue(recordIndex, 4, cargaTrabajo);
		super.checkColumnHasValue(recordIndex, 5, descripcion);
		super.checkColumnHasValue(recordIndex, 6, enlace);
		
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
	//Como resultado esta prueba muestra la lista de todas las tasks tal y como aparece en el csv de la url indicada en resources incluyendo las tasks que se hayan creado correctamente
	
	//-----------------------------------------------------------------------------------------------------------------
	
	//Este test comprueba que, estando registrado como un usuario que no es manager, no se puede acceder a la lista de tareas de uno
	@Test
	@Order(10)
	public void listNegative() {		
		super.signIn("user1", "user1");	
		
		super.navigate("Acme-Planner/manager/task/list","");
		
		super.checkPanicExists();
		
		super.signOut();
	}
	// Al haber accedido a una url de manager sin los permisos necesarios, se lanzará un panic.
	
	//Este test comprueba que, sin haberse registrado, no se puede acceder a la lista de tareas de manager. Similar al anterior, pero con anonymous
		@Test
		@Order(10)
		public void listNegative2() {		
			
			super.navigate("Acme-Planner/manager/task/list","");
			
			super.checkPanicExists();
			
		}
		// Al haber accedido a una url de manager sin los permisos necesarios, se lanzará un panic.
}
