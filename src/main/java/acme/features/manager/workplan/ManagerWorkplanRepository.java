package acme.features.manager.workplan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workplans.Workplan;
import acme.framework.entities.Manager;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkplanRepository extends AbstractRepository{


	@Query("select t from Workplan t")
	Collection<Workplan> findMany();
	
	@Query("select t from Workplan t where t.manager.userAccount.id = ?1")
	Collection<Workplan> findManyByManagerId(int managerId);
	
	@Query("select t from Workplan t where t.id = ?1")
	Workplan findOneWorkplanById(int id);

	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);
	
}
