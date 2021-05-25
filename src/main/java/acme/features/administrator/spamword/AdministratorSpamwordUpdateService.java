package acme.features.administrator.spamword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamword.Spamword;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSpamwordUpdateService implements AbstractUpdateService<Administrator, Spamword> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorSpamwordRepository repository;

		// AbstractCreateService<Administrator, SpamWord> interface --------------

		@Override
		public boolean authorise(final Request<Spamword> request) {
			assert request != null;

			return true;
		}

		@Override
		public void bind(final Request<Spamword> request, final Spamword entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors);
		}

		@Override
		public void unbind(final Request<Spamword> request, final Spamword entity, final Model model) {
			
		}

		@Override
		public void validate(final Request<Spamword> request, final Spamword entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			
		}
		
		@Override
		public Spamword findOne(final Request<Spamword> request) {
			assert request != null;

			Spamword result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findOneSpamwordById(id);

			return result;
		}

		@Override
		public void update(final Request<Spamword> request, final Spamword entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}

}
