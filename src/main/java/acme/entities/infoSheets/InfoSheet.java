package acme.entities.infoSheets;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InfoSheet extends DomainEntity{

	protected static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	//@Past
	@NotNull
	protected Date moment;
	
	@Temporal(TemporalType.TIMESTAMP)
	//@Past
	@NotNull
	protected Date shoutMoment;
	

	@NotNull
	protected Money money;
	
	
	@NotNull
	protected Boolean bool;
	
}
