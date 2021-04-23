package acme.features.administrator.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/task/")
public class AdministratorTaskController extends AbstractController<Administrator,Task>{

	// Internal state ---------------------------------------------------------


	@Autowired
	protected AdministratorTaskCreateService	createService;
	@Autowired
	protected AdministratorTaskListService	listService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}
}
