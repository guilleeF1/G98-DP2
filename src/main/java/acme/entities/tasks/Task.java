

package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {
	
	protected static final long serialVersionUID = 1L;
	
	@NotNull
	protected Boolean publica;

	@Length(min = 1, max = 79)
	@NotBlank
	protected String titulo;
	
	@Temporal(TemporalType.TIMESTAMP)
//	@Future
	@NotNull
	protected Date periodoEjecucionInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
//	@Future
	@NotNull
	protected Date periodoEjecucionFinal;
	
	@NotNull
	protected Integer cargaTrabajo;
	
	protected Integer cargaTrabajoMinutos;
	
	@NotBlank
	@Length(min = 1, max = 499)
	protected String descripcion;
	
	@URL
	protected String enlace;


	// Relationships ----------------------------------------------------------


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Manager manager;

}