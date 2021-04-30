
package acme.features.manager.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"publica", "titulo", "periodoEjecucionInicio", "periodoEjecucionFinal", "cargaTrabajo", "descripcion", "enlace");
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		Task result;
		Date inicio;
		Date fin;

		inicio = new Date(System.currentTimeMillis() - 1);
		fin = new Date(System.currentTimeMillis() - 1);

		result = new Task();
		result.setPublica(true);
		result.setTitulo("Titulo");
		result.setPeriodoEjecucionInicio(inicio);
		result.setPeriodoEjecucionFinal(fin);
		result.setCargaTrabajo(1);
		result.setDescripcion("Descripcion");
		result.setEnlace("https://clockify.me/tracker");

		Principal principal;
		principal = request.getPrincipal();
		
		result.setManager(this.repository.findOneManagerById(principal.getActiveRoleId()));

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("periodoEjecucionFinal")) {
			errors.state(request, entity.getPeriodoEjecucionInicio().before(entity.getPeriodoEjecucionFinal()), "periodoEjecucionFinal", "anonymous.task.form.error.invalid-final");
		}
		
		if (!errors.hasErrors("periodoEjecucionInicio")) {
			final Date d = new Date(System.currentTimeMillis());
			errors.state(request, entity.getPeriodoEjecucionInicio().after(d), "periodoEjecucionInicio", "anonymous.task.form.error.past");
		}

	}
	
	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.save(entity);
	}
}

