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

package acme.testing.anonymous.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// Prueba de comprobación de que se pueda obtener la lista de todas las tasks correctamente entrando en la api sin loguearse (Anónimo)
//	@ParameterizedTest
//	@CsvFileSource(resources = "/anonymous/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
//	@Order(10)	
	public void list(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
		final String periodoEjecucionFinal, final String cargaTrabajo, final String cargaTrabajoMinutos, 
		final String descripcion, final String enlace) {
		
		super.clickOnMenu("Anonymous", "List tasks");		
		
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
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAnonymousTaskPublicAndFinished(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
		final String periodoEjecucionFinal, final String cargaTrabajo, final String cargaTrabajoMinutos, 
		final String descripcion, final String enlace) throws ParseException {		
		
		super.clickOnMenu("Anonymous", "List tasks");		
		
		List<WebElement> row;
		row = super.getListingRecord(recordIndex);
		final DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		final Date date = format.parse(row.get(2).getText());  // Fecha de finalización de la tarea en cuestión
		final Date fecha = new Date(); // Fecha actual
		assert fecha.before(date);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("publica", "true");
	}
	//Como resultado esta prueba muestra la lista de todas las tasks tal y como aparece en el csv de la url indicada en resources incluyendo las tasks que se hayan creado correctamente
	
	//------------------------------------------------------------------------------------------------------
	
	// Ancillary methods ------------------------------------------------------

}
