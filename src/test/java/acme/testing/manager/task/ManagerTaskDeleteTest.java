package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;


public class ManagerTaskDeleteTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String publica, final String titulo, final String periodoEjecucionInicio,
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
		
		super.clickOnSubmitButton("Delete");
		
		super.clickOnMenu("Manager", "My tasks");		
		
		final Boolean b = super.checkColumnHasNoValue(recordIndex, 0, publica) ||
		super.checkColumnHasNoValue(recordIndex, 1, titulo) ||
		super.checkColumnHasNoValue(recordIndex, 2, periodoEjecucionInicio) ||
		super.checkColumnHasNoValue(recordIndex, 3, periodoEjecucionFinal) ||
		super.checkColumnHasNoValue(recordIndex, 4, cargaTrabajo) ||
		super.checkColumnHasNoValue(recordIndex, 5, descripcion) ||
		super.checkColumnHasNoValue(recordIndex, 6, enlace);
		
		assert b : String.format("All atributes are the same, task wasn't deleted");
		
		
		super.signOut();
	}
	
	//Se testea que un usuario sin permisos de manager intente borrar una tarea y que, a consecuencia, salte un panic error
	@Test
	@Order(11)
	public void deleteNegative() {		
		super.signIn("user1", "user1");
		
		super.navigate("Acme-Planner/manager/task/delete","id=273");
		
		super.checkPanicExists();
		
		super.signOut();
	} 

}