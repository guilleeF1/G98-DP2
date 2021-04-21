package acme.features.anonymous.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.entities.Anonymous;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTaskRepository extends AbstractRepository{


	@Query("select t from Task t")
	Collection<Task> findMany();
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);

//	@Query("select t from Task t where t.reference = ?1")
//	Task findOneTaskByReference(String reference);

	@Query("select a from Anonymous a where a.id = ?1")
	Anonymous findOneAnonymousById(int id);

	@Query("select t from Task t where t.anonymous.id = ?1")
	Collection<Task> findManyByAnonymousId(int anonymousId);

//	@Query("select t from Task t where t.finalMode = true and t.deadline > current_timestamp()")
//	Collection<Task> findManyTasksByAvailability();
	
}
