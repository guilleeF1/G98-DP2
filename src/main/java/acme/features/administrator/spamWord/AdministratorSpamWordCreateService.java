package acme.features.administrator.spamWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamWord.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorSpamWordCreateService implements AbstractCreateService<Administrator, SpamWord> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorSpamWordRepository repository;

		// AbstractCreateService<Administrator, SpamWord> interface --------------

		@Override
		public boolean authorise(final Request<SpamWord> request) {
			assert request != null;

			return true;
		}

		@Override
		public void bind(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors);
		}

		@Override
		public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, 
				"word");
		}

		@Override
		public SpamWord instantiate(final Request<SpamWord> request) {
			assert request != null;

			SpamWord result;

			result = new SpamWord();
			result.setWord("palabra");

			return result;
		}

		@Override
		public void validate(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			
		}
		
		@Override
		public void create(final Request<SpamWord> request, final SpamWord entity) {
			assert request != null;
			assert entity != null;
			
			this.repository.save(entity);
		}

}
