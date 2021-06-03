/*
 * Dashboard.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						numberOfTaskPublic;
	Integer						numberOfTaskPrivate;
	Integer						numberOfTaskFinished;
	Integer						numberOfTaskNotFinished;
	Double                      workloadAverage;
	Integer						workloadMin;
	Integer						workloadMax;
	Double 						workloadDeviation;
	Double                      startPeriodAverage;
	Double                      startPeriodDeviation;
	Double                      finalPeriodDeviation;
	Double                      finalPeriodAverage;
	Date 						startPeriodMin;
	Date 						finalPeriodMin;
	Date 						startPeriodMax;
	Date 						finalPeriodMax;
	Double						flaggedRatio;
	Double						outdatedInformationsheetsRatio;
	Double						moneyEuroAverage;
	Double						moneyDollarAverage;
	Double						moneyEuroDeviation;
	Double						moneyDollarDeviation;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
