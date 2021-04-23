package acme.features.authenticated.task;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedTaskListService implements AbstractListService<Authenticated, Task>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AuthenticatedTaskRepository repository;


		// AbstractListService<Administrator, Shout> interface --------------

		@Override
		public boolean authorise(final Request<Task> request) {
			assert request != null;

			return true;
		}

		@Override
		public void unbind(final Request<Task> request, final Task entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "publica", "titulo", "periodoEjecucionInicio", "periodoEjecucionFinal","cargaTrabajo","descripcion","enlace");
		}

		@Override
		public Collection<Task> findMany(final Request<Task> request) {
			assert request != null;

			Collection<Task> result;
			final Calendar cal= Calendar.getInstance();
			final java.util.Date hoy= cal.getTime();
			result = this.repository.findOpenTasks(hoy);

			result = this.repository.findMany();

			return result;
		}
}
