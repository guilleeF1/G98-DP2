/*
 * AnonymousShoutCreateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.shout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.informationsheets.Informationsheet;
import acme.entities.shouts.Shout;
import acme.entities.spamword.Spamword;
import acme.entities.threshold.Threshold;
import acme.features.administrator.spamword.AdministratorSpamwordRepository;
import acme.features.administrator.threshold.AdministratorThresholdRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousShoutRepository			repository;
	@Autowired
	protected AdministratorSpamwordRepository	spamRepository;
	@Autowired
	protected AdministratorThresholdRepository	tresholdRepository;

	// AbstractCreateService<Administrator, Shout> interface --------------


	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		request.bind(entity.getInformationsheet()	, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "info");
		request.unbind(entity.getInformationsheet(), model, "date", "money", "currency", "flag");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Shout();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setInfo("http://example.org");
		result.setInformationsheet(new Informationsheet());

		result.getInformationsheet().setDate(moment);
		result.getInformationsheet().setMoment(moment);
		result.getInformationsheet().setMoney(5.0);
		result.getInformationsheet().setCurrency("euro");
		result.getInformationsheet().setFlag(false);

		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("author")) {
			errors.state(request, !this.isSpam(entity.getAuthor()), "author", "anonymous.shout.form.error.spam");
		}

		if (!errors.hasErrors("text")) {
			errors.state(request, !this.isSpam(entity.getText()), "text", "anonymous.shout.form.error.spam");
		}
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		entity.getInformationsheet().setMoment(moment);
		this.repository.save(entity.getInformationsheet());
		this.repository.save(entity);
	}
	
	
	
	
	
	private Boolean isSpam(final String texto) {
		
		final Collection<Spamword> cs = this.spamRepository.findMany();
		final List<Spamword> cs2 = new ArrayList<>();
		cs2.addAll(cs);
		
		final Collection<Threshold> ct = this.tresholdRepository.findMany();
		final List<Threshold> l = new ArrayList<>();
		l.addAll(ct);
		final Threshold t = l.get(0);

		return Spamword.isSpam(texto.toLowerCase(), cs2, t);
	}

}
