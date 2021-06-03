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

import acme.features.anonymous.shout.AnonymousShoutRepository;
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
	
	@Autowired
	protected AnonymousShoutRepository shoutRepository;

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
			"finalPeriodMin", "startPeriodMax", "finalPeriodMax", "startPeriodDeviation", "finalPeriodDeviation",
			"flaggedRatio", "outdatedInformationsheetsRatio", "moneyEuroAverage", "moneyDollarAverage",
			"moneyEuroDeviation", "moneyDollarDeviation");
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
		Double outdatedInformationsheets;
		Double moneyEuroAverage;
		Double moneyDollarAverage;
		Double moneyEuroDeviation;
		Double moneyDollarDeviation;

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
		flaggedRatio = ((double) this.shoutRepository.findFlaggedInformationsheets().size()) /
			((double) this.shoutRepository.findAllInformationsheets().size());
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		
		outdatedInformationsheets = ((double) this.shoutRepository.findOutdatedInformationsheets(moment).size()) /
			((double) this.shoutRepository.findAllInformationsheets().size());

		moneyEuroAverage = this.repository.getMoneyAverage("euro");
		moneyDollarAverage = this.repository.getMoneyAverage("dollar");
		
		moneyEuroDeviation = AdministratorDashboardShowService.calculateSDDouble(this.shoutRepository.getMoneyFromInformationsheetByCurrency("euro"));
		
		moneyDollarDeviation = AdministratorDashboardShowService.calculateSDDouble(this.shoutRepository.getMoneyFromInformationsheetByCurrency("dollar"));

		result = new Dashboard();
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
		result.setFlaggedRatio(flaggedRatio);
		result.setOutdatedInformationsheetsRatio(outdatedInformationsheets);
		result.setMoneyEuroAverage(moneyEuroAverage);
		result.setMoneyDollarAverage(moneyDollarAverage);
		result.setMoneyEuroDeviation(moneyEuroDeviation);
		result.setMoneyDollarDeviation(moneyDollarDeviation);

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

	private static double calculateSDDouble(final Collection<Double> ls) {
		double sum = 0.0, standardDeviation = 0.0;
		final int length = ls.size();

		for (final Double num : ls) {
			sum += num;
		}

		final double mean = sum / length;

		for (final Double num : ls) {
			standardDeviation += Math.pow(num - mean, 2);
		}

		return Math.sqrt(standardDeviation / length);
	}

}
