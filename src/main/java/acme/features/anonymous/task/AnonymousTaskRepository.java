package acme.features.anonymous.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

public interface AnonymousTaskRepository extends AbstractRepository {

	@Query("select t from Task t")
	Collection<Task> findMany();

}
