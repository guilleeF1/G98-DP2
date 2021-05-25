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
			final String periodoEjecucionFinal, final String cargaTrabajo, 
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
			super.checkInputBoxHasValue("descripcion", descripcion);
			super.checkInputBoxHasValue("enlace", enlace);
			
			
			super.signOut();
		}
		
		//Comprueba que un anónimo no puede acceder a la vista de detalles de una tarea
				@Test
				@Order(11)
				public void showNegative() {		
					
					super.navigate("Acme-Planner/manager/task/show","id=273");
					
					super.checkPanicExists();
					
				} 
		//A consecuencia, saltará un error Panic, ya que no se tienen los permisos necesarios para mostrar los detalles de la tarea
		
		
		
		//Comprueba que un manager no puede, desde la vista de detalles de una tarea, ver los botones para editar o borrar una tarea de otro manager. 
		//la tarea 273 fue creada por manager1
		@Test
		@Order(11)
		public void showNegative2() {		
			super.signIn("manager2", "manager2");
			
			super.navigate("Acme-Planner/manager/task/show","id=273");
			
			super.checkButtonNotExists("Delete");
			
			super.checkButtonNotExists("Update");
			
			super.signOut();
			
		} 
		//

}
