package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordListTest extends AcmePlannerTest {
	
	//Este test prueba que un administrador logueado pueda listar correctamente la lista de palabras que se consideran spam
	//Para ello, se comprueba la coincidencia spamword con la primera palabra.
	@ParameterizedTest
	@CsvFileSource(resources = "/updateSpamWord/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listSpamWord(final int recordIndex, final String word) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam words");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		this.signOut();
	}
	//Como resultado, se muestra correctamente la lista de palabras spams
	
	
	//Este test comprueba que un usuario sin permisos de administrador (en este caso, solo authenticated), no pueda listar las palabras consideradas spam
	@Test
	@Order(10)
	public void listSpamWordNegativo() {		
		super.signIn("user1", "user1");	
		
		super.navigate("/administrator/spamword/list","");
		
		super.checkPanicExists();
		
		super.signOut();
	}
	//Como resultado, al haber accedido a la url sin los permisos adecuados, se generará un error de tipo Panic
	
	//Este test comprueba que un usuario sin permisos de administrador (en este caso, un anónimo), no pueda listar las palabras consideradas spam. Similar al anterior.
	@Test
	@Order(10)
	public void listSpamWordNegativo2() {			
		
		super.navigate("/administrator/spamword/list","");
		
		super.checkPanicExists();
		
	}
	//Como resultado, al haber accedido a la url sin los permisos adecuados, se generará un error de tipo Panic
	
}
