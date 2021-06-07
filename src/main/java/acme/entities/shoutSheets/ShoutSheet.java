package acme.entities.shoutSheets;


import java.util.Date;

import javax.persistence.Column;
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
public class ShoutSheet extends DomainEntity {

	private static final long serialVersionUID = 1L;
	
	
	
	
	
	@NotNull
	@Column(unique = true)
	protected String name;
	
	
	@Temporal(TemporalType.TIMESTAMP) //patrón de fecha, para moments
	@NotNull
	protected Date yesterday;
	
	@NotNull
	protected Money donation; //tributo dinero
	
	@NotNull
	protected Boolean finished;
	
	
	
	
	
	

}
