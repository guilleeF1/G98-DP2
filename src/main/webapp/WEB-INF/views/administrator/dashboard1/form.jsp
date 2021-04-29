<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			Total number of public work plans
		</th>
		<td>
			<acme:print value="${numberOfWorkPlanPublic}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Total number of private work plans
		</th>
		<td>
			<acme:print value="${numberOfWorkPlanPrivate}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Total number of finished work plans
		</th>
		<td>
			<acme:print value="${numberOfWorkPlanFinished}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Total number of not finished work plans
		</th>
		<td>
			<acme:print value="${numberOfWorkPlanNotFinished}"/>
		</td>
	</tr>	
		<tr>
		<th scope="row">
			Workload Average work plans
		</th>
		<td>
			<acme:print value="${workloadAverage1}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			Workload Max work plans
		</th>
		<td>
			<acme:print value="${workloadMin1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Workload Min work plans
		</th>
		<td>
			<acme:print value="${workloadMax1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Workload Deviation work plans
		</th>
		<td>
			<acme:print value="${workloadDeviation1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Start Period Average work plans
		</th>
		<td>
			<acme:print value="${startPeriodAverage1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Final Period Average work plans
		</th>
		<td>
			<acme:print value="${finalPeriodAverage1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Start Period Min work plans
		</th>
		<td>
			<acme:print value="${startPeriodMin1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Final Period Min work plans
		</th>
		<td>
			<acme:print value="${finalPeriodMin1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Start Period Max work plans
		</th>
		<td>
			<acme:print value="${startPeriodMax1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Final Period Max work plans
		</th>
		<td>
			<acme:print value="${finalPeriodMax1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Start Period Deviation work plans
		</th>
		<td>
			<acme:print value="${startPeriodDeviation1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			Final Period Deviation work plans
		</th>
		<td>
			<acme:print value="${finalPeriodDeviation1}"/>
		</td>
	</tr>
	
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.application-statuses"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"PENDING", "ACCEPTED", "REJECTED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${ratioOfPendingApplications}"/>, 
						<jstl:out value="${ratioOfAcceptedApplications}"/>, 
						<jstl:out value="${ratioOfRejectedApplications}"/>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
