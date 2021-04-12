package acme.entities.plansOfJob;

import java.util.Collection;
import java.util.Date;

import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;

public class PlanOfJob extends DomainEntity {

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
	
	@NotNull
	protected Integer cargaTrabajo;
	
	// Realtionships -----------------
	
	@NotNull
	@Valid
	@ManyToMany(mappedBy = "planOfJob")
	Collection<Task> task;
	
}
