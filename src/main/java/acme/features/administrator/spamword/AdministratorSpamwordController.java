package acme.features.administrator.spamword;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spamword.Spamword;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spamword/")
public class AdministratorSpamwordController extends AbstractController<Administrator, Spamword> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSpamwordListService	listService;
	
	@Autowired
	protected AdministratorSpamwordUpdateService	updateService;
	
	@Autowired
	protected AdministratorSpamwordShowService	showService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
