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

import java.math.BigDecimal;
import java.time.LocalDate;
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
		
		result.getInformationsheet().setMoment(moment);

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
		if(!errors.hasErrors("date")) {
			errors.state(request, entity.getInformationsheet().getDate().matches
				("^\\d{4}\\/(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])$"), 
				"date", "anonymous.shout.form.error.dateExpresion");
			errors.state(request, entity.getInformationsheet().getDate().contains
				(this.todayYear() + "/" + this.todayMonth() + "/" + this.todayDay()), 
				"date", "anonymous.shout.form.error.notToday");
			errors.state(request, this.isUnique(entity.getInformationsheet().getDate()), "date",
				"anonymous.shout.form.error.unique");
		}
		
		if(!errors.hasErrors("currency")) {
			errors.state(request, entity.getInformationsheet().getCurrency().equals("euro") || 
				entity.getInformationsheet().getCurrency().equals("dollar"), 
				"currency", "anonymous.shout.form.error.currency");
		}
		
		if (entity.getInformationsheet().getMoney() == null) {
			errors.state(request, false, "money", "anonymous.shout.form.error.null");
		}
		
		if(entity.getInformationsheet().getMoney() != null && !errors.hasErrors("money")) {
			errors.state(request, entity.getInformationsheet().getMoney() >= 0, 
				"money", "anonymous.shout.form.error.min");
			errors.state(request, BigDecimal.valueOf(entity.getInformationsheet().getMoney()).scale() <= 2, 
				"money", "anonymous.shout.form.error.twoDecimals");
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
	
	private Boolean isUnique(final String unique) {
		final List<Informationsheet> ls = new ArrayList<Informationsheet>();
		ls.addAll(this.repository.findAllInformationsheets());
		Boolean b = false;
		for (final Informationsheet i : ls) {
			b = unique.equals(i.getDate());
			if (b) {
				break;
			}
		}
		return !b;
	}
	
	private String todayDay() {
		final int i = LocalDate.now().getDayOfMonth();
		if (i < 10) {
			return "0" + i;
		}
		else {
			return String.valueOf(i);
		}
	}
	
	private String todayMonth() {
		final int i = LocalDate.now().getMonthValue();
		if (i < 10) {
			return "0" + i;
		}
		else {
			return String.valueOf(i);
		}
	}
	
	private String todayYear() {
		final int i = LocalDate.now().getYear();
		return String.valueOf(i);
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
