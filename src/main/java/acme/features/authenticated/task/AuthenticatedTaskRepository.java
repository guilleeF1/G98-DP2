package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Service
public interface AuthenticatedTaskRepository extends AbstractRepository{


	@Query("select s from Task s")
	Collection<Task> findMany();
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
}
