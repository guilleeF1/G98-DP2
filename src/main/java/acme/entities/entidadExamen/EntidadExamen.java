package acme.entities.entidadExamen;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EntidadExamen extends DomainEntity {

	private static final long serialVersionUID = 1L;
	
	
	@Temporal(TemporalType.TIMESTAMP) //patrón de fecha
	@NotNull
	protected Date timeAttribute;
	
//	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$") //valida dd/mm/yyyy
//	@NotNull
//	protected Date atributoTiempo2;
	
	@Temporal(TemporalType.TIMESTAMP) //patrón de fecha, para moments
	@NotNull
	protected Date momentAttribute;
	
	@NotNull
	protected Double moneyAttribute; //solo acepta dos tipos de currency, imagino que habrá otro atributo que lo indique?
	
	@NotNull
	protected Boolean isFlag;
	
	
	
	
	
	

}
