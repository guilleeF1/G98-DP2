package acme.features.administrator.treshold;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.treshold.SpamWordTreshold;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/treshold/")
public class AdministratorTresholdController extends AbstractController<Administrator,SpamWordTreshold>{

	// Internal state ---------------------------------------------------------


	@Autowired
	protected AdministratorTresholdService	tresholdService;
	


	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.tresholdService);
	}
}
