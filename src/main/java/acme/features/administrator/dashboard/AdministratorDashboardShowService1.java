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
public class AdministratorDashboardShowService1 implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorDashboardRepository1 repository;

		// AbstractShowService<Administrator, Dashboard1> interface ----------------


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
				"numberOfWorkPlanPublic", "numberOfWorkPlanPrivate",
				"numberOfWorkPlanFinished","numberOfWorkPlanNotFinished", "workloadAverage1","workloadMin1", "workloadMax1",
				"workloadDeviation1", "startPeriodAverage1", "finalPeriodAverage1", "startPeriodMin1", "finalPeriodMin1",
				"startPeriodMax1", "finalPeriodMax1", "startPeriodDeviation1","finalPeriodDeviation1");
		}

		@SuppressWarnings("deprecation")
		@Override
		public Dashboard findOne(final Request<Dashboard> request) {
			assert request != null;
			final Date today = Calendar.getInstance().getTime();

			Dashboard result;
			Integer numberOfWorkPlanPublic;
			Integer numberOfWorkPlanPrivate;
			Integer numberOfWorkPlanFinished;
			Integer numberOfWorkPlanNotFinished;
			Double workloadAverage1;
			Integer workloadMin1;
			Integer workloadMax1;
			Double workloadDeviation1;
			Double startPeriodAverage1;
			Double finalPeriodAverage1;
			Date startPeriodMin1;
			Date finalPeriodMin1;
			Date startPeriodMax1;
			Date finalPeriodMax1;
			Double startPeriodDeviation1;
			Double finalPeriodDeviation1;
			
			numberOfWorkPlanPublic = this.repository.countWorkPlanPublic();
			numberOfWorkPlanPrivate = this.repository.countWorkPlanPrivate();
			numberOfWorkPlanFinished = this.repository.countWorkPlanFinished(today);
			numberOfWorkPlanNotFinished = this.repository.countWorkPlanNotFinished(today);
			workloadAverage1 = this.repository.workloadAverage1();
			workloadMin1 = this.repository.workloadMin1();
			workloadMax1 = this.repository.workloadMax1();
			workloadDeviation1 = AdministratorDashboardShowService1.calculateSD1(this.repository.getWorkload1());
			startPeriodAverage1 = this.repository.getStartPeriodAverage1();
			finalPeriodAverage1 = this.repository.getFinalPeriodAverage1();
			startPeriodMin1 = this.repository.startPeriodMin1();
			finalPeriodMin1 = this.repository.finalPeriodMin1();
			startPeriodMax1 = this.repository.startPeriodMax1();
			finalPeriodMax1 = this.repository.finalPeriodMax1();
			final Collection<Date> periodosI = this.repository.getStartPeriod1();
			final List<Integer> pI = new ArrayList<>();
			for(final Date d: periodosI) {
				pI.add(d.getHours());
			}
			final Collection<Date> periodosF = this.repository.getFinalPeriod1();
			final List<Integer> pF = new ArrayList<>();
			for(final Date d: periodosF) {
				pF.add(d.getHours());
			}
			startPeriodDeviation1 = AdministratorDashboardShowService1.calculateSD1(pI);
			finalPeriodDeviation1 = AdministratorDashboardShowService1.calculateSD1(pF);

			result = new Dashboard();
			result.setNumberOfWorkPlanPublic(numberOfWorkPlanPublic);
			result.setNumberOfWorkPlanPrivate(numberOfWorkPlanPrivate);
			result.setNumberOfWorkPlanFinished(numberOfWorkPlanFinished);
			result.setNumberOfWorkPlanNotFinished(numberOfWorkPlanNotFinished);
			result.setWorkloadAverage(workloadAverage1);
			result.setWorkloadMax(workloadMax1);
			result.setWorkloadMin(workloadMin1);
			result.setWorkloadDeviation(workloadDeviation1);
			result.setStartPeriodAverage(startPeriodAverage1/120000);
			result.setFinalPeriodAverage(finalPeriodAverage1/120000);
			result.setFinalPeriodMax(finalPeriodMax1);
			result.setFinalPeriodMin(finalPeriodMin1);
			result.setStartPeriodMax(startPeriodMax1);
			result.setStartPeriodMin(startPeriodMin1);
			result.setFinalPeriodDeviation(finalPeriodDeviation1);
			result.setStartPeriodDeviation(startPeriodDeviation1);

			return result;
		}
		
		 private static double calculateSD1(final Collection<Integer> integer)
		    {
		        double sum = 0.0, standardDeviation = 0.0;
		        final int length = integer.size();

		        for(final Integer num : integer) {
		            sum += num;
		        }

		        final double mean = sum/length;

		        for(final Integer num: integer) {
		            standardDeviation += Math.pow(num - mean, 2);
		        }

		        return Math.sqrt(standardDeviation/length);
		    }
		 
		

}
