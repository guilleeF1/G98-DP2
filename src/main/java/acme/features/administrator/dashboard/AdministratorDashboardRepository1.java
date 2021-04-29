package acme.features.administrator.dashboard;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository1 extends AbstractRepository {

	//DashBoard WorkPlan
	
	@Query("select count(w) from Workplan w where w.publico = TRUE")
	Integer countWorkPlanPublic();
		
	@Query("select count(w.id) from Workplan w where w.publico = FALSE")
	Integer countWorkPlanPrivate();
		
	@Query("select count(w.id) from Workplan w where ?1 > w.periodoEjecucionFinal")
	Integer countWorkPlanFinished(Date today);
		
	@Query("select count(w.id) from Workplan w where ?1 < w.periodoEjecucionFinal")
	Integer countWorkPlanNotFinished(Date today);
		
	@Query("select avg(w.tasks.cargaTrabajo) from Workplan w")
	Double workloadAverage1();
		
	@Query("select max(w.tasks.cargaTrabajo) from Workplan w")
	Integer workloadMax1();
		
	@Query("select min(w.tasks.cargaTrabajo) from Workplan w")
	Integer workloadMin1();
		
	@Query("select w.tasks.cargaTrabajo from Workplan w")
	Collection<Integer> getWorkload1();
	
	@Query("select w.periodoEjecucionInicio from Workplan w")
	Collection<Date> getStartPeriod1();

	@Query("select w.periodoEjecucionFinal from Workplan w")
	Collection<Date> getFinalPeriod1();
		
	@Query("select avg(w.periodoEjecucionInicio) from Workplan w")
	Double getStartPeriodAverage1();
		
	@Query("select avg(w.periodoEjecucionFinal) from Workplan w")
	Double getFinalPeriodAverage1();
		
	@Query("select min(w.periodoEjecucionInicio) from Workplan w")
	Date startPeriodMin1();
		
	@Query("select min(w.periodoEjecucionFinal) from Workplan w")
	Date finalPeriodMin1();
		
	@Query("select max(w.periodoEjecucionInicio) from Workplan w")
	Date startPeriodMax1();
		
	@Query("select max(w.periodoEjecucionFinal) from Workplan w")
	Date finalPeriodMax1();
}
