package acme.features.anonymous.task;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousTaskListService implements AbstractListService<Anonymous, Task>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnonymousTaskRepository repository;


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
			result = this.repository.findActiveTasks(hoy);

			return result;
		}
		
		
		

		
		
		
}
