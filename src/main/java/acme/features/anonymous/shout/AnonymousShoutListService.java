/*
 * AnonymousShoutListService.java
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

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.informationsheets.Informationsheet;
import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListService implements AbstractListService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousShoutRepository repository;


	// AbstractListService<Administrator, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		

		request.unbind(entity, model, "author", "text", "moment");

		final Informationsheet informationsheet = entity.getInformationsheet();
		if (informationsheet != null) {
			model.setAttribute("informationsheet.date", informationsheet.getDate());
			model.setAttribute("informationsheet.moment", informationsheet.getMoment());
			model.setAttribute("informationsheet.money", informationsheet.getMoney().toString() + " " + 
				informationsheet.getCurrency());
			model.setAttribute("informationsheet.flag", informationsheet.getFlag());
		}
	}

	@Override
	public Collection<Shout> findMany(final Request<Shout> request) {
		assert request != null;

		Collection<Shout> result;
		final Calendar cal= Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		final java.util.Date limitetiempo= cal.getTime();
		result = this.repository.findRecentShouts(limitetiempo);

		return result;
	}

}
