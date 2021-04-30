
package acme.features.manager.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workplans.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkplanShowService implements AbstractShowService<Manager, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository repository;

	// AbstractShowService<Administrator, Workplan> interface --------------

	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;
		
		boolean result;
		boolean isPublic;
		int id;
		Workplan workplan;
		Manager manager;
		Principal principal;
		
		id = request.getModel().getInteger("id");
		workplan = this.repository.findOneWorkplanById(id);
		isPublic = workplan.getPublico();
		manager = workplan.getManager();
		principal = request.getPrincipal();
		

		result = (manager.getUserAccount().getId() == principal.getAccountId());
		
		return result;
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"publico", "periodoEjecucionInicio", "periodoEjecucionFinal");


		model.setAttribute("cargaTrabajo", entity.getCargaTrabajo());
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;
		
		Workplan result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkplanById(id);
		
		return result;
	}
}

