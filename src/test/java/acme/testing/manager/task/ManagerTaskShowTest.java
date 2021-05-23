package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskShowTest extends AcmePlannerTest{
	
	    // Prueba de mostrar los detalles de una task 
		//entrando en la api logueándose como Manager.
		@ParameterizedTest
		@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void showPositive(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
			final String periodoEjecucionFinal, final String cargaTrabajo, final String cargaTrabajoMinutos, 
			final String descripcion, final String enlace) {		
			super.signIn("manager1", "manager1");
			
			super.clickOnMenu("Manager", "My tasks");	

			//Compruebo que esté la tarea
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
		
		//Comprueba que un manager no puede mostrar los detalles de una tarea que ha creado otro manager distinto
		//la tarea 273 fue creada por manager1
		@Test
		@Order(11)
		public void deleteNegative2() {		
			super.signIn("manager2", "manager2");
			
			super.navigate("Acme-Planner/manager/task/show","id=273");
			
			super.checkPanicExists();
			
			super.signOut();
		} 
		//A consecuencia, saltará un error Panic, ya que el manager 2 no tiene permisos sobre la task 273 

}
