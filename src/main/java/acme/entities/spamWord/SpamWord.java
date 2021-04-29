package acme.entities.spamWord;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpamWord extends DomainEntity {
	
	protected static final long serialVersionUID = 1L;
	

	@NotNull
	protected String word;

}