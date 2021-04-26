package acme.features.manager.workplan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workplans.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class ManagerWorkplanListService implements AbstractListService<Manager, Workplan>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerWorkplanRepository repository;


		// AbstractListService<Administrator, Shout> interface --------------

		@Override
		public boolean authorise(final Request<Workplan> request) {
			assert request != null;

			return true;
		}

		@Override
		public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "publico", "periodoEjecucionInicio", "periodoEjecucionFinal");

			model.setAttribute("cargaTrabajo", entity.getCargaTrabajo());
		}

		@Override
		public Collection<Workplan> findMany(final Request<Workplan> request) {
			assert request != null;

			Collection<Workplan> result;
			Principal principal;

			principal = request.getPrincipal();
			result = this.repository.findManyByManagerId(principal.getAccountId());

			return result;
		}
}
