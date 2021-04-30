package acme.features.administrator.spamword;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spamword.Spamword;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamwordRepository extends AbstractRepository {


	@Query("select s from Spamword s")
	Collection<Spamword> findMany();
	
	@Query("select t from Spamword t where t.id = ?1")
	Spamword findOneSpamwordById(int id);

}
