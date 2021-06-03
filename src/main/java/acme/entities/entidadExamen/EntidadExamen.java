package acme.entities.entidadExamen;


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
public class EntidadExamen extends DomainEntity {

	private static final long serialVersionUID = 1L;
	
	
	
	
	
	@NotNull
	@Column(unique = true)
	protected String timeAttribute;
	
	
	@Temporal(TemporalType.TIMESTAMP) //patrón de fecha, para moments
	@NotNull
	protected Date momentAttribute;
	
	@NotNull
	protected Money moneyAttribute; //tributo dinero
	
	@NotNull
	protected Boolean isFlag;
	
	
	
	
	
	

}
