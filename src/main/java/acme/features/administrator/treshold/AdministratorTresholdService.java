package acme.features.administrator.treshold;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.treshold.SpamWordTreshold;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

public class AdministratorTresholdService implements AbstractCreateService<Administrator, SpamWordTreshold> {

	@Autowired
	protected AdministratorTresholdRepository repository;
	
	@Override
	public boolean authorise(final Request<SpamWordTreshold> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<SpamWordTreshold> request, final SpamWordTreshold entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<SpamWordTreshold> request, final SpamWordTreshold entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "umbral");

	}

	@Override
	public SpamWordTreshold instantiate(final Request<SpamWordTreshold> request) {
		assert request != null;
		SpamWordTreshold result;
		
		result = new SpamWordTreshold();
		result.setUmbral(45.0);		
		
		return result;
	}

	@Override
	public void validate(final Request<SpamWordTreshold> request, final SpamWordTreshold entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<SpamWordTreshold> request, final SpamWordTreshold entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
