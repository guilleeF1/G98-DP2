package acme.features.anonymous.workplan;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workplans.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkplanRepository extends AbstractRepository{


	@Query("select w from Workplan w where w.periodoEjecucionFinal > ?1 and w.publico = TRUE")
	Collection<Workplan> findActiveWorkplans(Date hoy);
  
//	@Query("select t from Workplan t where t.id = ?1")
//	Workplan findOneWorkplanById(int id);
}
