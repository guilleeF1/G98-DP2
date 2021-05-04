
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
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository				repository;
	@Autowired
	protected AdministratorSpamwordRepository	spamRepository;
	@Autowired
	protected AdministratorTresholdRepository	tresholdRepository;


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

		request.unbind(entity, model, "publica", "titulo", "periodoEjecucionInicio", "periodoEjecucionFinal", "cargaTrabajo", "descripcion", "enlace");
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

		if (!errors.hasErrors("descripcion")) {
			final String descripcion = entity.getDescripcion();
			errors.state(request, !descripcion.trim().isEmpty() && descripcion.length() <= 499, "descripcion", "anonymous.task.form.error.length");
		}

		if (!errors.hasErrors("titulo")) {
			final String titulo = entity.getTitulo();
			errors.state(request, !titulo.trim().isEmpty() && titulo.length() <= 79, "titulo", "anonymous.task.form.error.length");
		}

		if (!errors.hasErrors("periodoEjecucionFinal")) {
			errors.state(request, entity.getPeriodoEjecucionInicio().before(entity.getPeriodoEjecucionFinal()), "periodoEjecucionFinal", "anonymous.task.form.error.invalid-final");
		}

		if (!errors.hasErrors("periodoEjecucionInicio")) {
			final Date d = new Date(System.currentTimeMillis());
			errors.state(request, entity.getPeriodoEjecucionInicio().after(d), "periodoEjecucionInicio", "anonymous.task.form.error.past");
		}

		if (!errors.hasErrors("cargaTrabajo")) {
			errors.state(request, entity.getCargaTrabajo() < 0, "cargaTrabajo", "manager.task.form.error.negative");
		}

		if (!errors.hasErrors("cargaTrabajo")) {
			errors.state(request, entity.getCargaTrabajo() <= (this.hoursBetween(entity.getPeriodoEjecucionInicio(), entity.getPeriodoEjecucionFinal())), "cargaTrabajo", "manager.task.form.error.equals");
		}

		if (!errors.hasErrors("cargaTrabajoMinutos")) {
			errors.state(request, entity.getCargaTrabajoMinutos().equals(entity.getCargaTrabajo() * 60), "cargaTrabajoMinutos", "manager.task.form.error.minutes");
		}

		if (!errors.hasErrors("descripcion")) {
			errors.state(request, !this.isSpam(entity.getDescripcion()), "descripcion", "manager.task.form.error.description-spam");
		}

		if (!errors.hasErrors("titulo")) {
			errors.state(request, !this.isSpam(entity.getTitulo()), "titulo", "manager.task.form.error.title-spam");
		}
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
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

		return Spamword.isSpam(texto, cs2, t);
	}

	private Integer hoursBetween(final Date i, final Date f) {
		final Long l = (f.getTime() - i.getTime()) / (60 * 60 * 1000);
		return l.intValue();
	}
}
