
package acme.features.manager.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.spamword.Spamword;
import acme.entities.tasks.Task;
import acme.entities.treshold.Treshold;
import acme.features.administrator.spamword.AdministratorSpamwordRepository;
import acme.features.administrator.treshold.AdministratorTresholdRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository				repository;
	@Autowired
	protected AdministratorSpamwordRepository	spamRepository;
	@Autowired
	protected AdministratorTresholdRepository	tresholdRepository;

	// AbstractListService<Employer, Job> -------------------------------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		boolean result;
		int taskId;
		Task task;
		Manager manager;
		Principal principal;

		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(taskId);
		manager = task.getManager();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("titulo")) {
			final String descripcion = entity.getDescripcion();

			errors.state(request, !descripcion.trim().isEmpty() && descripcion.length() <= 499, "descripcion", "anonymous.task.form.error.length");
		}

		if (!errors.hasErrors("titulo")) {
			final String titulo = entity.getTitulo();

			errors.state(request, !titulo.trim().isEmpty() && titulo.length() <= 79, "titulo", "anonymous.task.form.error.length");
		}

		if (!errors.hasErrors("cargaTrabajo")) {
			errors.state(request, entity.getCargaTrabajo() > 0, "cargaTrabajo", "manager.task.form.error.negative");
		}

		if (!errors.hasErrors("cargaTrabajo")) {
			if (entity.getCargaTrabajoMinutos() != null) {
				errors.state(request, entity.getCargaTrabajo() * 60 + entity.getCargaTrabajoMinutos() <= (this.minutesBetween(entity.getPeriodoEjecucionInicio(), entity.getPeriodoEjecucionFinal())), "cargaTrabajo", "manager.task.form.error.equals");
			}
			else {
				errors.state(request, entity.getCargaTrabajo() * 60 <= (this.minutesBetween(entity.getPeriodoEjecucionInicio(), entity.getPeriodoEjecucionFinal())), "cargaTrabajo", "manager.task.form.error.equals");
			}
		}

		if (!errors.hasErrors("cargaTrabajoMinutos") && entity.getCargaTrabajoMinutos() != null) {
			errors.state(request, entity.getCargaTrabajoMinutos() >= 1 && entity.getCargaTrabajoMinutos() <= 59, "cargaTrabajoMinutos", "manager.task.form.error.minutes");
		}

		if (!errors.hasErrors("descripcion")) {
			errors.state(request, !this.isSpam(entity.getDescripcion()), "descripcion", "manager.task.form.error.description-spam");
		}

		if (!errors.hasErrors("titulo")) {
			errors.state(request, !this.isSpam(entity.getTitulo()), "titulo", "manager.task.form.error.title-spam");
		}
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

		request.unbind(entity, model, "publica", "titulo", "periodoEjecucionInicio", "periodoEjecucionFinal");
		request.unbind(entity, model, "cargaTrabajo", "descripcion", "enlace", "finalMode");
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

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	private Boolean isSpam(final String texto) {

		final Collection<Spamword> cs = this.spamRepository.findMany();
		final List<Spamword> cs2 = new ArrayList<>();
		cs2.addAll(cs);

		final Collection<Treshold> ct = this.tresholdRepository.findMany();
		final List<Treshold> l = new ArrayList<>();
		l.addAll(ct);
		final Treshold t = l.get(0);

		return Spamword.isSpam(texto.toLowerCase(), cs2, t);
	}

	private Integer minutesBetween(final Date i, final Date f) {
		final Long l = (f.getTime() - i.getTime()) / (60 * 1000);
		return l.intValue();
	}

}
