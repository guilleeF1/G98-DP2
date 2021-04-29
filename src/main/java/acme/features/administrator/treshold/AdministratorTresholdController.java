package acme.features.administrator.treshold;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.treshold.Treshold;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/treshold/")
public class AdministratorTresholdController extends AbstractController<Administrator,Treshold>{

	// Internal state ---------------------------------------------------------


	@Autowired
	protected AdministratorTresholdUpdateService	tresholdUpdateService;
	@Autowired
	protected AdministratorTresholdShowService	tresholdShowService;
	


	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.tresholdUpdateService);
		super.addBasicCommand(BasicCommand.SHOW, this.tresholdShowService);
	}
}
