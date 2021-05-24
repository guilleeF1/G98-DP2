/*
 * EmployerApplicationUpdateTest.java
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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// Prueba de actualización de todos los atributos de una task correctamente 
	//entrando en la api logueándose como Manager.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
		final String periodoEjecucionFinal, final String cargaTrabajo, 
		final String descripcion, final String enlace) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My tasks");	

		super.checkColumnHasValue(recordIndex, 0, publica);

		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("titulo", titulo);
		super.fillInputBoxIn("periodoEjecucionInicio", periodoEjecucionInicio);
		super.fillInputBoxIn("periodoEjecucionFinal", periodoEjecucionFinal);
		super.fillInputBoxIn("cargaTrabajo", cargaTrabajo);
		
		super.clickOnSubmitButton("Update");
		
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
		super.checkInputBoxHasValue("descripcion", descripcion);
		super.checkInputBoxHasValue("enlace", enlace);
		
		super.signOut();
	}
	
	//Como resultado esta prueba muestra la task con sus atributos actualizados correctamente tal 
	//y como aparece en csv indicado en la url de resources actualizando sus atributos en la lista de todas los tasks.
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	// Prueba de la actualización de todos los atributos de una task entrando en la api logueándose como Manager con los atributos no actualizados correctamente 
	//puesto que el título debe de tener como máximo 79 carácteres, 
	//el periodo de ejecución inicial debe de ser anterior al periodo de ejecución final 
	//y la carga de trabajo no debe de ser negativa.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updateNegative(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
		final String periodoEjecucionFinal, final String cargaTrabajo, 
		final String descripcion, final String enlace) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My tasks");	

		super.checkColumnHasValue(recordIndex, 0, publica);

		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("titulo", titulo);
		super.fillInputBoxIn("periodoEjecucionInicio", periodoEjecucionInicio);
		super.fillInputBoxIn("periodoEjecucionFinal", periodoEjecucionFinal);
		super.fillInputBoxIn("cargaTrabajo", cargaTrabajo);
		super.fillInputBoxIn("descripcion", descripcion);
		super.fillInputBoxIn("enlace", enlace);
		
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();
		
		super.signOut();
	}
	
	//Como resultado esta prueba no muestra los atributos actualizados de la task correctamente tal y como aparece en csv indicado en la url 
	//de resources en la lista de todos las tasks a causa de este error de validación en estos atributos
			
	//------------------------------------------------------------------------------------------------------
	
	//Prueba para comprobar que un manager (manager2) no puede actualizar una tarea de otro manager (manager1)
	@Test
	@Order(11)
	public void updateNegative2() {		
		super.signIn("manager2", "manager2");
		
		super.navigate("Acme-Planner/manager/task/update","id=273");
		
		super.checkPanicExists();
		
		super.signOut();
	} 
	//Como resultado, como el manager ha accedido a las tareas de otro manager, se generará un panic error

}
