package acme.features.administrator.threshold;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.threshold.Threshold;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/threshold/")
public class AdministratorThresholdController extends AbstractController<Administrator,Threshold>{

	// Internal state ---------------------------------------------------------


	@Autowired
	protected AdministratorThresholdUpdateService	thresholdUpdateService;
	@Autowired
	protected AdministratorThresholdShowService	thresholdShowService;
	


	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.thresholdUpdateService);
		super.addBasicCommand(BasicCommand.SHOW, this.thresholdShowService);
	}
}
