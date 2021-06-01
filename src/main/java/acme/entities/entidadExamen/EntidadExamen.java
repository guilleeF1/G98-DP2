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
	
	
	@Temporal(TemporalType.TIMESTAMP) //patrón de fecha ¿con fecha o no?
	@NotNull
	@Column(unique = true)
	protected Date timeAttribute;
	
	//@MatchesPattern(value = "")
	//To match a date in mm/dd/yyyy format, rearrange the regular expression to ^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d$. 
	//For dd-mm-yyyy format, use ^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d$
	
	@Temporal(TemporalType.TIMESTAMP) //patrón de fecha, para moments
	@NotNull
	protected Date momentAttribute;
	
	@NotNull
	protected Money moneyAttribute; //tributo dinero
	
	@NotNull
	protected Boolean isFlag;
	
	
	
	
	
	

}
