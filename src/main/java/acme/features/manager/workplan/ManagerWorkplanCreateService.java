
package acme.features.manager.workplan;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workplans.Workplan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerWorkplanCreateService implements AbstractCreateService<Manager, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository repository;

	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"publico", "periodoEjecucionInicio", "periodoEjecucionFinal");
	}

	@Override
	public Workplan instantiate(final Request<Workplan> request) {
		assert request != null;

		Workplan result;
		Date inicio;
		Date fin;

		inicio = new Date(System.currentTimeMillis() - 1);
		fin = new Date(System.currentTimeMillis() - 1);

		result = new Workplan();
		result.setPeriodoEjecucionInicio(inicio);
		result.setPeriodoEjecucionFinal(fin);

		Principal principal;
		principal = request.getPrincipal();
		
		result.setManager(this.repository.findOneManagerById(principal.getActiveRoleId()));

		return result;
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("periodoEjecucionFinal")) {
			errors.state(request, entity.getPeriodoEjecucionInicio().before(entity.getPeriodoEjecucionFinal()), "periodoEjecucionFinal", "anonymous.workplan.form.error.invalid-final");
		}
		
		if (!errors.hasErrors("periodoEjecucionInicio")) {
			final Date d = new Date(System.currentTimeMillis());
			errors.state(request, entity.getPeriodoEjecucionInicio().after(d), "periodoEjecucionInicio", "anonymous.workplan.form.error.past");
		}

	}
	
	@Override
	public void create(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.save(entity);
	}
}

