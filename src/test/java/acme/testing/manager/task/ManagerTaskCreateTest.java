package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
		// Test cases -------------------------------------------------------------
	
		// Prueba de creación de task con sus atributos añadidos correctamente 
		//entrando en la api logueándose como Manager.
  
		@ParameterizedTest
		@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	
		public void create(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
			final String periodoEjecucionFinal, final String cargaTrabajo, 
			final String descripcion, final String enlace) {		
			super.signIn("manager1", "manager1");
			
			super.clickOnMenu("Manager", "Create a task");		

//			super.checkColumnHasValue(recordIndex, 0, publica);

//			super.clickOnListingRecord(recordIndex);
			if (publica.equals("true")) {
				super.fillInputBoxIn("publica", "true");
			}
			else {
				super.fillInputBoxIn("publica", "false");
			}
			super.fillInputBoxIn("titulo", titulo);
			super.fillInputBoxIn("periodoEjecucionInicio", periodoEjecucionInicio);
			super.fillInputBoxIn("periodoEjecucionFinal", periodoEjecucionFinal);
			super.fillInputBoxIn("cargaTrabajo", cargaTrabajo);
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
			super.checkInputBoxHasValue("descripcion", descripcion);
			super.checkInputBoxHasValue("enlace", enlace);
			
			super.signOut();
		}
		
		//Como resultado esta prueba muestra la task creada correctamente tal 
		//y como aparece en csv indicado en la url de resources añadiendola en la lista de todas los tasks.
		
		//-------------------------------------------------------------------------------------------------------------------------------------
				
		// Prueba de creación de task entrando en la api logueándose como Manager con los atributos no añadidos correctamente 
		//puesto que el título debe de tener como máximo 79 carácteres, 
		//el periodo de ejecución inicial debe de ser anterior al periodo de ejecución final 
		//y la carga de trabajo no debe de ser negativa.
		
		@ParameterizedTest
		@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)	
		public void createNegative(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
			final String periodoEjecucionFinal, final String cargaTrabajo, 
			final String descripcion, final String enlace) {		
			super.signIn("manager1", "manager1");
			
			super.clickOnMenu("Manager", "Create a task");	

//			super.checkColumnHasValue(recordIndex, 0, publica);

//			super.clickOnListingRecord(recordIndex);
			
			super.fillInputBoxIn("titulo", titulo);
			super.fillInputBoxIn("periodoEjecucionInicio", periodoEjecucionInicio);
			super.fillInputBoxIn("periodoEjecucionFinal", periodoEjecucionFinal);
			super.fillInputBoxIn("cargaTrabajo", cargaTrabajo);
			super.fillInputBoxIn("descripcion", descripcion);
			super.fillInputBoxIn("enlace", enlace);
			
			super.clickOnSubmitButton("Create");

			super.checkErrorsExist();
			
			super.signOut();
		}
		
		//Como resultado esta prueba no muestra la task creada correctamente tal y como aparece en csv indicado en la url 
		//de resources en la lista de todos las tasks a causa de este error de validación en estos atributos
		
		//------------------------------------------------------------------------------------------------------
		
		//En este test comprobamos que, un usuario sin permisos de manager, no puede crear tareas
		@Test
		@Order(11)
		public void createNegative2() {		
			super.signIn("user1", "user1");
			
			super.navigate("Acme-Planner/manager/task/update","");
			
			super.checkPanicExists();
			
			super.signOut();
		} 
		//al acceder el usuario a una funcionalidad para la que no tiene permisos, se espera un panic.
}
