package acme.testing;

import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.Order;

public class LanguageChangeTest extends AcmePlannerTest {
	
	// Prueba de comprobación de que el idioma de la página cambia correctamente
		@Test
		@Order(10)	
		public void changeLanguage() {		
			
			super.clickOnLink("Spanish");
			super.checkLinkExists("Entrar");
			
			super.clickOnLink("Inglés");
			super.checkLinkExists("Sign in");
			
		}

	// Se espera que "Entrar" se haya traducido a "Sign in"
}
