package acme.entities.workplans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.Manager;
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
	@NotNull
	protected Date periodoEjecucionInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date periodoEjecucionFinal;

	// Derived attributes -----------------------------------------------------

	public int getCargaTrabajo() {
		int w = 0;
		int m = 0;
		final List<Task> l = new ArrayList<>();
		l.addAll(this.tasks);
		for(final Task t : l) {
			w += t.getCargaTrabajo();
			if (t.getCargaTrabajoMinutos() != null) {
				m += t.getCargaTrabajoMinutos();
			}
			if (m >= 60) {
				w += 1;
				m -= 60;
			}
		}
		return w;
	}
	
	// Realtionships -----------------
	
	@NotNull
	@Valid
	@ManyToMany(fetch=FetchType.EAGER)
	protected Collection<Task> tasks;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Manager manager;
}
