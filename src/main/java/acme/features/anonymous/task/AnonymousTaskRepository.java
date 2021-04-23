package acme.features.anonymous.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Service
public interface AnonymousTaskRepository extends AbstractRepository{


	@Query("select s from Task s where s.periodoEjecucionFinal > ?1 and s.publica = TRUE")
	Collection<Task> findActiveTasks(Date hoy);
}
