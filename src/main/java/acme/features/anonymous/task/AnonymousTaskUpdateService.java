package acme.features.anonymous.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractUpdateService;

@Service
public class AnonymousTaskUpdateService implements AbstractUpdateService<Anonymous, Task> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnonymousTaskRepository repository;

		// AbstractListService<Employer, Job> -------------------------------------


		@Override
		public boolean authorise(final Request<Task> request) {
			assert request != null;

			boolean result;
			int taskId;
			Task task;

			taskId = request.getModel().getInteger("id");
			task = this.repository.findOneTaskById(taskId);
			result = !task.isFinalMode();

			return result;
		}

		@Override
		public void validate(final Request<Task> request, final Task entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			if (!errors.hasErrors("titulo")) {
				final String descripcion = entity.getDescripcion();
				
				errors.state(request, descripcion.trim().isEmpty() || descripcion.length()<=499, "descripcion", "anonymous.task.form.error.length");
			}

			if (!errors.hasErrors("titulo")) {
				final String titulo = entity.getTitulo();
				
				errors.state(request, titulo.trim().isEmpty() || titulo.length()<=79, "titulo", "anonymous.task.form.error.length");
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

}
