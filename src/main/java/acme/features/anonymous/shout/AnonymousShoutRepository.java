/*
 * AnonymousShoutRepository.java
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

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.informationsheets.Informationsheet;
import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {

	@Query("select s from Shout s")
	Collection<Shout> findMany();

	@Query("select s from Shout s where s.moment >= ?1 ORDER BY s.moment")
	Collection<Shout> findRecentShouts(Date limitetiempo);
	
	@Query("select s from Informationsheet s")
	Collection<Informationsheet> findAllInformationsheets();
	
	@Query("select s from Informationsheet s where s.flag = TRUE")
	Collection<Informationsheet> findFlaggedInformationsheets();
	
	@Query("select s from Informationsheet s where s.date >= ?1")
	Collection<Informationsheet> findOutdatedInformationsheets(Date moment);
	
	@Query("select s.money from Informationsheet s where s.currency = ?1")
	Collection<Double> getMoneyFromInformationsheetByCurrency(String currency);

}
