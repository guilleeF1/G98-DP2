package acme.entities.treshold;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Treshold extends DomainEntity {

	protected static final long	serialVersionUID = 1L;

	@NotNull
	protected Double umbral;

}