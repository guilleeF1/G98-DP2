package acme.features.administrator.treshold;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.treshold.Treshold;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorTresholdUpdateService implements AbstractUpdateService<Administrator, Treshold> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorTresholdRepository repository;

		// AbstractListService<Employer, Job> -------------------------------------


		@Override
		public boolean authorise(final Request<Treshold> request) {
			assert request != null;

			return true;
		}

		@Override
		public void validate(final Request<Treshold> request, final Treshold entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			if (!errors.hasErrors("umbral")) {
				final Double umbral = entity.getUmbral();
				
				errors.state(request, umbral <= 100 && umbral >= 0, "umbral", "administrator.spamWordTreshold.form.error.size");
			}		
		}

		@Override
		public void bind(final Request<Treshold> request, final Treshold entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors);
		}

		@Override
		public void unbind(final Request<Treshold> request, final Treshold entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "umbral");
		}

		@Override
		public Treshold findOne(final Request<Treshold> request) {
			assert request != null;

			Treshold result;
			final List<Treshold> s = new ArrayList<Treshold>(); 
			s.addAll(this.repository.findMany());
			
			result = s.get(0);

			return result;
		}

		@Override
		public void update(final Request<Treshold> request, final Treshold entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}

}
