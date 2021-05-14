package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
		// Test cases -------------------------------------------------------------
		
		@ParameterizedTest
		@CsvFileSource(resources = "/manager/task/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	
		public void create(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
			final String periodoEjecucionFinal, final String cargaTrabajo, final String cargaTrabajoMinutos, 
			final String descripcion, final String enlace) {		
			super.signIn("manager1", "manager1");
			
			super.clickOnMenu("Manager", "Create a task");		

//			super.checkColumnHasValue(recordIndex, 0, publica);

//			super.clickOnListingRecord(recordIndex);
			super.fillInputBoxIn("publica", publica);
			super.fillInputBoxIn("titulo", titulo);
			super.fillInputBoxIn("periodoEjecucionInicio", periodoEjecucionInicio);
			super.fillInputBoxIn("periodoEjecucionFinal", periodoEjecucionFinal);
			super.fillInputBoxIn("cargaTrabajo", cargaTrabajo);
			super.fillInputBoxIn("cargaTrabajoMinutos", cargaTrabajoMinutos);
			super.fillInputBoxIn("descripcion", descripcion);
			super.fillInputBoxIn("enlace", enlace);
			
			super.clickOnSubmitButton("Create");
			
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
		
		// Ancillary methods ------------------------------------------------------
}
