package acme.features.administrator.threshold;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threshold.Threshold;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorThresholdUpdateService implements AbstractUpdateService<Administrator, Threshold> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorThresholdRepository repository;

		// AbstractListService<Employer, Job> -------------------------------------


		@Override
		public boolean authorise(final Request<Threshold> request) {
			assert request != null;

			return true;
		}

		@Override
		public void validate(final Request<Threshold> request, final Threshold entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			if (!errors.hasErrors("umbral")) {
				final Double umbral = entity.getUmbral();
				
				errors.state(request, umbral <= 100 && umbral >= 0, "umbral", "administrator.spamWordThreshold.form.error.size");
			}		
		}

		@Override
		public void bind(final Request<Threshold> request, final Threshold entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors);
		}

		@Override
		public void unbind(final Request<Threshold> request, final Threshold entity, final Model model) {
			
			//There is nothing to unbind in this service 
		}

		@Override
		public Threshold findOne(final Request<Threshold> request) {
			assert request != null;

			Threshold result;
			final List<Threshold> s = new ArrayList<Threshold>(); 
			s.addAll(this.repository.findMany());
			
			result = s.get(0);

			return result;
		}

		@Override
		public void update(final Request<Threshold> request, final Threshold entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}

}
