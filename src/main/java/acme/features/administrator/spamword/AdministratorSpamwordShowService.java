
package acme.features.administrator.spamword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamword.Spamword;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamwordShowService implements AbstractShowService<Administrator, Spamword> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSpamwordRepository repository;

	// AbstractShowService<Administrator, Spamword> interface --------------

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

		request.unbind(entity, model, 
			"word");
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
}

