package acme.features.authenticated.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;
@Controller
@RequestMapping("/authenticated/task/")
public class AuthenticatedTaskController extends AbstractController<Anonymous,Task>{

	// Internal state ---------------------------------------------------------


	@Autowired
	protected AuthenticatedTaskCreateService	createService;
	@Autowired
	protected AuthenticatedTaskListFinishedService	listFinishedService;
	@Autowired
	protected AuthenticatedTaskListService	listAllService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listAllService);
		super.addCustomCommand(CustomCommand.LIST_FINISHED, BasicCommand.LIST, this.listFinishedService);
		
	}
}
