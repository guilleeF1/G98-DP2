package acme.entities.informationsheets;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Informationsheet extends DomainEntity {
	
	protected static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(unique = true)
	protected String date;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date moment;
	
	@NotNull
	protected Double money;
	
	@NotBlank
	protected String currency;
	
	protected Boolean flag;
	
}