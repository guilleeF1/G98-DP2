package acme.features.administrator.spamword;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamword.Spamword;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorSpamwordListService implements AbstractListService<Administrator, Spamword> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSpamwordRepository repository;


	// AbstractListService<Administrator, SpamWord> interface --------------

	@Override
	public boolean authorise(final Request<Spamword> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Spamword> request, final Spamword entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "word");
	}

	@Override
	public Collection<Spamword> findMany(final Request<Spamword> request) {
		assert request != null;

		Collection<Spamword> result;

		result = this.repository.findMany();

		return result;
	}


}
