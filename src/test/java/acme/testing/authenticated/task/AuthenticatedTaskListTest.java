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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// Prueba de comprobación de que se pueda obtener la lista de todas las tasks finalizadas correctamente entrando en la api logueándose como cualquier usuario
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
		super.checkInputBoxHasValue("descripcion", descripcion);
		super.checkInputBoxHasValue("enlace", enlace);
		
		super.signOut();
	}
	
	//Como resultado esta prueba muestra la lista de todas las tasks finalizadas tal y como aparece en el csv de la url indicada en resources

	// Aquí comprobamos que las tareas se listan por su workload
	@Test
	@Order(30)	
	public void order() {
		super.signIn("user1", "user1");
		
		super.clickOnMenu("Authenticated", "List finished tasks");			
		
		super.checkTaskOrder();
		
		super.signOut();
	}
	
	// El resultado es un boolean que nos indica si al recorrer las entradas, sus workloads estaban ordenados correctamente
	
	// Ancillary methods ------------------------------------------------------
	
	//Este test comprueba que un usuario con rol de administrador no puede acceder a la lista de tareas que tienen los usuarios registrados (authenticated)
	@Test
	@Order(10)
	public void listAuthenticatedTaskNegative() {		
		super.signIn("administrator", "administrator");	
		
		super.navigate("/authenticated/task/list-finished","");
		
		super.checkPanicExists();
		
		super.signOut();
	}
	//Como resultado, se generará un error de tipo Panic, ya que estamos accediendo a una funcionalidad para la que no tenemos los permisos adecuados
	
	//Este test comprueba que los usuarios sin registrar no pueden acceder a la lista de tareas que tienen los usuarios registrados(authenticated)
		@Test
		@Order(10)
		public void listAuthenticatedTaskNegative2() {		
			
			super.navigate("/authenticated/task/list-finished","");
			
			super.checkPanicExists();
			
		}
		//Como resultado, se generará un error de tipo Panic, ya que estamos accediendo a una funcionalidad para la que no tenemos los permisos adecuados
		
		
		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void listAuthenticatedTaskPublicAndFinished(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
			final String periodoEjecucionFinal, final String cargaTrabajo, final String cargaTrabajoMinutos, 
			final String descripcion, final String enlace) throws ParseException {		
			super.signIn("user1", "user1");	
			super.clickOnMenu("Authenticated", "List finished tasks");		
			
			List<WebElement> row;
			row = super.getListingRecord(recordIndex);
			final DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			final Date date = format.parse(row.get(2).getText());  // Fecha de finalización de la tarea en cuestión
			final Date fecha = new Date(); // Fecha actual
			assert fecha.after(date);
			
			super.clickOnListingRecord(recordIndex);
			super.checkInputBoxHasValue("publica", "true");
			super.signOut();
		}
}
