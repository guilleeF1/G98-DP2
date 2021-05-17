
package acme.features.administrator.treshold;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.treshold.Treshold;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorTresholdShowService implements AbstractShowService<Administrator, Treshold> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorTresholdRepository repository;

	// AbstractShowService<Administrator, SpamWordTreshold> interface --------------


	@Override
	public boolean authorise(final Request<Treshold> request) {
		assert request != null;
		return true;
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
}
