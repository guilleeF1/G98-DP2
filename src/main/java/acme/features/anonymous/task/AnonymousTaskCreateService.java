
package acme.features.anonymous.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public abstract class AnonymousTaskCreateService implements AbstractCreateService<Anonymous, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousTaskRepository repository;

	// AbstractCreateService<Administrator, Task> interface --------------

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
			"titulo", "periodoEjecucionInicio", "periodoEjecucionFinal", "cargaTrabajo", "descripcion", "enlace");
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
		result.setTitulo("Titulo");
		result.setPeriodoEjecucionInicio(inicio);
		result.setPeriodoEjecucionFinal(fin);
		result.setCargaTrabajo(1);
		result.setDescripcion("Descripcion");
		result.setEnlace("https://clockify.me/tracker");

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}
	
	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

