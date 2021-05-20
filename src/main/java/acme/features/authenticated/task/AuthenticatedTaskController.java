package acme.features.authenticated.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;
@Controller
@RequestMapping("/authenticated/task/")
public class AuthenticatedTaskController extends AbstractController<Authenticated,Task>{

	// Internal state ---------------------------------------------------------

	@Autowired  
	protected AuthenticatedTaskListFinishedService	listFinishedService;

	@Autowired
	protected AuthenticatedTaskShowService	showService;



	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
    
		
		super.addCustomCommand(CustomCommand.LIST_FINISHED, BasicCommand.LIST, this.listFinishedService);
		
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}
}
