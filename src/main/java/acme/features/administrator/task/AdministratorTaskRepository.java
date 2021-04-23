package acme.features.administrator.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Service
public interface AdministratorTaskRepository extends AbstractRepository{


	@Query("select s from Task s")
	Collection<Task> findMany();
}
