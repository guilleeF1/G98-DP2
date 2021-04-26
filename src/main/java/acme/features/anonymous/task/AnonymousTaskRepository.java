package acme.features.anonymous.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.entities.Anonymous;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTaskRepository extends AbstractRepository{


	@Query("select s from Task s where s.periodoEjecucionFinal > ?1 and s.publica = TRUE")
	Collection<Task> findActiveTasks(Date hoy);
  
	@Query("select t from Task t")
	Collection<Task> findMany();
  
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
}
