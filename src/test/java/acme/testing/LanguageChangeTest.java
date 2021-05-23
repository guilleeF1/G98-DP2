package acme.testing;

import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.Order;

public class LanguageChangeTest extends AcmePlannerTest {
	
	// Prueba de comprobación de que se pueda obtener la lista de todas las tasks finalizadas correctamente entrando en la api logueándose como cualquier usuario
		@Test
		@Order(10)	
		public void changeLanguage() {		
			
			super.clickOnLink("Spanish");
			super.checkLinkExists("Entrar");
			
			super.clickOnLink("Inglés");
			super.checkLinkExists("Sign in");
			
		}

}
