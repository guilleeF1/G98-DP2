package acme.features.administrator.spamWord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spamWord.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamWordRepository extends AbstractRepository {


	@Query("select s from SpamWord s")
	Collection<SpamWord> findMany();

}
