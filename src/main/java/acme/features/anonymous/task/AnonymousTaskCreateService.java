
package acme.features.anonymous.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousTaskCreateService implements AbstractCreateService<Anonymous, Task> {

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

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("cargaTrabajoMinutos") && entity.getCargaTrabajoMinutos()!=null) {
			errors.state(request, entity.getCargaTrabajoMinutos() > 0 && entity.getCargaTrabajoMinutos() < 60, "cargaTrabajoMinutos", "anonymous.task.form.error.within-range");
		}
		
		if (!errors.hasErrors("cargaTrabajo")) {
			final Date date1 = entity.getPeriodoEjecucionInicio();
			final Date date2 = entity.getPeriodoEjecucionFinal();
			final long diffInMilliseconds = date2.getTime() - date1.getTime();
			final long timeElapsed = TimeUnit.MINUTES.convert(diffInMilliseconds,TimeUnit.MILLISECONDS);
			final long workloadTime;
			if (entity.getCargaTrabajoMinutos()!=null) {
				workloadTime = (entity.getCargaTrabajo() * 60) + entity.getCargaTrabajoMinutos();
			}
			else {
				workloadTime = (entity.getCargaTrabajo() * 60);
			}
			System.out.println(timeElapsed);
			System.out.println(workloadTime);
			errors.state(request, workloadTime <= timeElapsed, "cargaTrabajo", "anonymous.task.form.error.must-fit");
		}
	}
	
	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}
}

