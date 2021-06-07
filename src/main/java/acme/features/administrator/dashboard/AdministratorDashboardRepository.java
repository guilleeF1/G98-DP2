/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(s) from Shout s")//cantidad de shouts, para hacer los ratios
	Integer countShout();
	
	//a) the ratio of shouts whose XXX were flagged as XXX
	@Query("select count(e) from ShoutSheet e where e.finished = TRUE")//como es one to one, vale así xd
	Integer countShoutFlagged();
	
	//b) the ratio of shouts whose XXX have XXX
	@Query("select count(e) from ShoutSheet e where e.donation.amount > 10")//como es one to one, vale así xd
	Integer countShoutWith10();
	
	//c) the average and the standard deviation of the XXX grouped by currency
	@Query("select t.donation.amount from ShoutSheet t where t.donation.currency = ?1")
	Collection<Double> getCurrencyEur(String eur);
	
	@Query("select t.donation.amount from ShoutSheet t where t.donation.currency = ?1")
	Collection<Double> getCurrencyUsd(String usd);
	
	
	@Query("select count(t) from Task t where t.publica = TRUE")
	Integer countTaskPublic();
	
	@Query("select count(t.id) from Task t where t.publica = FALSE")
	Integer countTaskPrivate();
	
	@Query("select count(t.id) from Task t where ?1 > t.periodoEjecucionFinal")
	Integer countTaskFinished(Date today);
	
	@Query("select count(t.id) from Task t where ?1 < t.periodoEjecucionFinal")
	Integer countTaskNotFinished(Date today);
	
	@Query("select avg(t.cargaTrabajo) from Task t")
	Double workloadAverage();
	
	@Query("select max(t.cargaTrabajo) from Task t")
	Integer workloadMax();
	
	@Query("select min(t.cargaTrabajo) from Task t")
	Integer workloadMin();
	
	@Query("select t.cargaTrabajo from Task t")
	Collection<Integer> getWorkload();
	
	@Query("select t.periodoEjecucionInicio from Task t")
	Collection<Date> getStartPeriod();
	
	@Query("select t.periodoEjecucionFinal from Task t")
	Collection<Date> getFinalPeriod();
	
	@Query("select avg(t.periodoEjecucionInicio) from Task t")
	Double getStartPeriodAverage();
	
	@Query("select avg(t.periodoEjecucionFinal) from Task t")
	Double getFinalPeriodAverage();
	
	@Query("select min(t.periodoEjecucionInicio) from Task t")
	Date startPeriodMin();
	
	@Query("select min(t.periodoEjecucionFinal) from Task t")
	Date finalPeriodMin();
	
	@Query("select max(t.periodoEjecucionInicio) from Task t")
	Date startPeriodMax();
	
	@Query("select max(t.periodoEjecucionFinal) from Task t")
	Date finalPeriodMax();

}
