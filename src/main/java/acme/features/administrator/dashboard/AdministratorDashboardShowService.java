/*
 * AdministratorDashboardShowService.java
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"numberOfTaskPublic", "numberOfTaskPrivate", "numberOfTaskFinished", "numberOfTaskNotFinished", "workloadAverage", "workloadMin", "workloadMax", "workloadDeviation", "startPeriodAverage", "finalPeriodAverage", "startPeriodMin",
			"finalPeriodMin", "startPeriodMax", "finalPeriodMax", "startPeriodDeviation", "finalPeriodDeviation","flaggedRatio","donationRatio","currencyDeviationEur","currencyDeviationUsd");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		final Date today = Calendar.getInstance().getTime();

		Dashboard result;
		Integer numberOfTaskPublic;
		Integer numberOfTaskPrivate;
		Integer numberOfTaskFinished;
		Integer numberOfTaskNotFinished;
		Double workloadAverage;
		Integer workloadMin;
		Integer workloadMax;
		Double workloadDeviation;
		Double startPeriodAverage;
		Double finalPeriodAverage;
		Date startPeriodMin;
		Date finalPeriodMin;
		Date startPeriodMax;
		Date finalPeriodMax;
		Double startPeriodDeviation;
		Double finalPeriodDeviation;
		Double flaggedRatio;
		Double xxxRatio;
		Double currencyDeviationEur;
		Double currencyDeviationUsd;

		flaggedRatio= this.repository.countShoutFlagged() /(double)this.repository.countShout();
		xxxRatio= this.repository.countShoutWith10() /(double)this.repository.countShout();
		final Collection<Double> currencyEu= this.repository.getCurrencyEur("EUR");
		currencyDeviationEur = AdministratorDashboardShowService.calculateSDDoubles(currencyEu);
		final Collection<Double> currencyUs= this.repository.getCurrencyUsd("PST");
		currencyDeviationUsd = AdministratorDashboardShowService.calculateSDDoubles(currencyUs);
		
		
		
		numberOfTaskPublic = this.repository.countTaskPublic();
		numberOfTaskPrivate = this.repository.countTaskPrivate();
		numberOfTaskFinished = this.repository.countTaskFinished(today);
		numberOfTaskNotFinished = this.repository.countTaskNotFinished(today);
		workloadAverage = this.repository.workloadAverage();
		workloadMin = this.repository.workloadMin();
		workloadMax = this.repository.workloadMax();
		workloadDeviation = AdministratorDashboardShowService.calculateSD(this.repository.getWorkload());
		startPeriodAverage = this.repository.getStartPeriodAverage();
		finalPeriodAverage = this.repository.getFinalPeriodAverage();
		startPeriodMin = this.repository.startPeriodMin();
		finalPeriodMin = this.repository.finalPeriodMin();
		startPeriodMax = this.repository.startPeriodMax();
		finalPeriodMax = this.repository.finalPeriodMax();
		final Collection<Date> periodosI = this.repository.getStartPeriod();
		final List<Integer> pI = new ArrayList<>();
		for (final Date d : periodosI) {
			final Calendar c = Calendar.getInstance();
			c.setTime(d);
			pI.add(c.get(Calendar.HOUR));
		}
		final Collection<Date> periodosF = this.repository.getFinalPeriod();
		final List<Integer> pF = new ArrayList<>();
		for (final Date d : periodosF) {
			final Calendar c = Calendar.getInstance();
			c.setTime(d);
			pF.add(c.get(Calendar.HOUR));
		}
		startPeriodDeviation = AdministratorDashboardShowService.calculateSD(pI);
		finalPeriodDeviation = AdministratorDashboardShowService.calculateSD(pF);

		result = new Dashboard();
		
		result.setFlaggedRatio(flaggedRatio);
		result.setDonationRatio(xxxRatio);
		result.setCurrencyDeviationEur(currencyDeviationEur);
		result.setCurrencyDeviationUsd(currencyDeviationUsd);
		
		
		result.setNumberOfTaskPublic(numberOfTaskPublic);
		result.setNumberOfTaskPrivate(numberOfTaskPrivate);
		result.setNumberOfTaskFinished(numberOfTaskFinished);
		result.setNumberOfTaskNotFinished(numberOfTaskNotFinished);
		result.setWorkloadAverage(workloadAverage);
		result.setWorkloadMax(workloadMax);
		result.setWorkloadMin(workloadMin);
		result.setWorkloadDeviation(workloadDeviation);
		result.setStartPeriodAverage(startPeriodAverage / 120000);
		result.setFinalPeriodAverage(finalPeriodAverage / 120000);
		result.setFinalPeriodMax(finalPeriodMax);
		result.setFinalPeriodMin(finalPeriodMin);
		result.setStartPeriodMax(startPeriodMax);
		result.setStartPeriodMin(startPeriodMin);
		result.setFinalPeriodDeviation(finalPeriodDeviation);
		result.setStartPeriodDeviation(startPeriodDeviation);
		

		return result;
	}

	private static double calculateSD(final Collection<Integer> integer) {
		double sum = 0.0, standardDeviation = 0.0;
		final int length = integer.size();

		for (final Integer num : integer) {
			sum += num;
		}

		final double mean = sum / length;

		for (final Integer num : integer) {
			standardDeviation += Math.pow(num - mean, 2);
		}

		return Math.sqrt(standardDeviation / length);
	}
	
	private static double calculateSDDoubles(final Collection<Double> lista) {
		double sum = 0.0, standardDeviation = 0.0;
		final int tam = lista.size();

		for (final Double num : lista) {
			sum += num;
		}

		final double mean = sum / tam;

		for (final Double num : lista) {
			standardDeviation += Math.pow(num - mean, 2);
		}

		return Math.sqrt(standardDeviation / tam);
	}

}
