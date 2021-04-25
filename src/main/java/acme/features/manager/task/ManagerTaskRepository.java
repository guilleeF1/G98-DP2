package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository{


	@Query("select t from Task t")
	Collection<Task> findMany();
	
	@Query("select t from Task t where t.manager.userAccount.id = ?1")
	Collection<Task> findManyByManagerId(int managerId);
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
}
