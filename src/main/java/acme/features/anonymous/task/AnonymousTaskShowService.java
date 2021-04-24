
package acme.features.anonymous.task;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousTaskShowService implements AbstractShowService<Anonymous, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousTaskRepository repository;

	// AbstractShowService<Administrator, Task> interface --------------

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		boolean result;
		boolean isPublic;
		boolean isFinished;
		int id;
		Task task;
		
		id = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(id);
		isPublic = task.getPublica();
		isFinished = LocalDate.now().isAfter(task.getPeriodoEjecucionFinal().toInstant()
		      .atZone(ZoneId.systemDefault())
		      .toLocalDate());
		

		result = isPublic && !isFinished;
		
		return result;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"publica", "titulo", "periodoEjecucionInicio", "periodoEjecucionFinal", "cargaTrabajo", "descripcion", "enlace");

//		model.setAttribute("readonly", true);
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

