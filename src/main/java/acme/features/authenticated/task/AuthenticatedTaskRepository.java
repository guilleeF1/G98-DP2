package acme.features.authenticated.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Service
public interface AuthenticatedTaskRepository extends AbstractRepository{


	@Query("select s from Task s where s.periodoEjecucionFinal < ?1 and s.publica = TRUE ORDER BY s.cargaTrabajo")
	Collection<Task> findClosedTasks(Date hoy);
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
}
