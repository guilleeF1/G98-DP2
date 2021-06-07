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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shoutSheets.ShoutSheet;
import acme.entities.shouts.Shout;
import acme.entities.spamword.Spamword;
import acme.entities.threshold.Threshold;
import acme.features.administrator.spamword.AdministratorSpamwordRepository;
import acme.features.administrator.threshold.AdministratorThresholdRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
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
	protected AdministratorThresholdRepository	thresholdRepository;

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
		request.bind(entity.getShoutSheet(), errors);
		request.bind(entity.getShoutSheet().getDonation(), errors);

	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");
		request.unbind(entity.getShoutSheet(), model, "yesterday","name","finished");
		request.unbind(entity.getShoutSheet().getDonation(), model, "amount","currency");

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
		result.setShoutSheet(new ShoutSheet());
		result.getShoutSheet().setFinished(true);
		result.getShoutSheet().setDonation(new Money());

		

		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//me falta parte de la validaciónS
		if (!errors.hasErrors("author")) {
			errors.state(request, !this.isSpam(entity.getAuthor()), "author", "anonymous.shout.form.error.spam");
		}

		if (!errors.hasErrors("text")) {
			errors.state(request, !this.isSpam(entity.getText()), "text", "anonymous.shout.form.error.spam");
		}
		if (!errors.hasErrors("currency")) {
			final String currency = entity.getShoutSheet().getDonation().getCurrency().toLowerCase();
			errors.state(request, !currency.trim().isEmpty() && (currency.equals("eur") || currency.equals("pst")) && currency!=null , "currency", "anonymous.shout.form.error.currency");
		}
		if (!errors.hasErrors("amount")) {
			final Double amount = entity.getShoutSheet().getDonation().getAmount();
			errors.state(request, amount!=null && amount>0   , "amount", "anonymous.shout.form.error.positive");
		}
		if (!errors.hasErrors("name")) {
			final String name = entity.getShoutSheet().getName(); 
			final List<String> timeNames = this.repository.findtimeAttribute();
			final LocalDate hoy= LocalDate.now();
			final int diaInt= hoy.getDayOfMonth();
			String dia= String.valueOf(diaInt);
			if (diaInt<10) dia= "0" + dia;
			final int mesINt= hoy.getMonthValue();
			final String mes= String.valueOf(mesINt);
			if (mesINt<10) dia= "0" + mes;
			final String year= String.valueOf(hoy.getYear());
			final String nameReceived= dia+"-"+mes+"-"+year+"-"+name;
			errors.state(request, !(timeNames.contains(nameReceived)) && timeNames!=null , "name", "anonymous.shout.form.error.duplicatedtime");
			
		}
		
		if (!errors.hasErrors("finished")) {
			final Boolean isFlag = entity.getShoutSheet().getFinished();
			errors.state(request, isFlag!=null , "currency", "anonymous.shout.form.error.null");
		}
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		final LocalDate hoy= LocalDate.now();
		final int diaInt= hoy.getDayOfMonth();
		String dia= String.valueOf(diaInt);
		if (diaInt<10) dia= "0" + dia;
		
		final int mesINt= hoy.getMonthValue();
		final String mes= String.valueOf(mesINt);
		if (mesINt<10) dia= "0" + mes;
		
		final String year= String.valueOf(hoy.getYear());
		final String name= dia+"-"+mes+"-"+year+"-"+entity.getShoutSheet().getName();
		entity.getShoutSheet().setName(name);
		
		final Calendar calMoment= Calendar.getInstance();
		calMoment.add(Calendar.DAY_OF_MONTH, -1);
		final Date momentAyer = calMoment.getTime();
		entity.getShoutSheet().setYesterday(momentAyer);
		
		
		entity.setMoment(moment);
		

		this.repository.save(entity.getShoutSheet());
		this.repository.save(entity);
	}
	
	
	
	
	
	private Boolean isSpam(final String texto) {
		
		final Collection<Spamword> cs = this.spamRepository.findMany();
		final List<Spamword> cs2 = new ArrayList<>();
		cs2.addAll(cs);
		
		final Collection<Threshold> ct = this.thresholdRepository.findMany();
		final List<Threshold> l = new ArrayList<>();
		l.addAll(ct);
		final Threshold t = l.get(0);

		return Spamword.isSpam(texto.toLowerCase(), cs2, t);
	}

}
