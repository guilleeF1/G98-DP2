package acme.features.manager.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
@Controller
@RequestMapping("/manager/task/")
public class ManagerTaskController extends AbstractController<Manager,Task>{

	// Internal state ---------------------------------------------------------


	@Autowired
	protected ManagerTaskCreateService	createService;
	@Autowired
	protected ManagerTaskListService	listService;
	@Autowired
	protected ManagerTaskShowService	showService;
	@Autowired
	protected ManagerTaskUpdateService		updateService;
	@Autowired
	protected ManagerTaskDeleteService		deleteService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
