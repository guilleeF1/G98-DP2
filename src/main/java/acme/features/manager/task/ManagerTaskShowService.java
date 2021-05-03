
package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerTaskShowService implements AbstractShowService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;

	// AbstractShowService<Administrator, Task> interface --------------

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		boolean result;
		boolean isPublic;
		int id;
		Task task;
		Manager manager;
		Principal principal;
		
		id = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(id);
		isPublic = task.getPublica();
		manager = task.getManager();
		principal = request.getPrincipal();
		

		result = (manager.getUserAccount().getId() == principal.getAccountId()) || isPublic;
		
		return result;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"publica", "titulo", "periodoEjecucionInicio", "periodoEjecucionFinal", "cargaTrabajo", "descripcion", "enlace");


		Manager manager;
		Principal principal;
		
		manager = entity.getManager();
		principal = request.getPrincipal();
		
		if (manager.getUserAccount().getId() != principal.getAccountId()) {
			model.setAttribute("readonly", true);
		}else {
			model.setAttribute("readonly", false);
		}
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		
		Task result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);
		
		return result;
	}
}

