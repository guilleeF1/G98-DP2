package acme.entities.workplans;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Workplan extends DomainEntity {

	// Serialisation identifier -------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes ----------------------
	
	@NotNull
	protected Boolean publico;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@NotNull
	protected Date periodoEjecucionInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@NotNull
	protected Date periodoEjecucionFinal;
	
	// Realtionships -----------------
	
	@NotNull
	@Valid
	@ManyToMany
	Collection<Task> tasks;
	
}
