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

<table class="table table-sm" id="values">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.public"/>
		</th>
		<td id="0">
			<acme:print value="${numberOfTaskPublic}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.private"/>
		</th>
		<td id="1">
			<acme:print value="${numberOfTaskPrivate}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.finished"/>
		</th>
		<td id="2">
			<acme:print value="${numberOfTaskFinished}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.unfinished"/>
		</th>
		<td id="3">
			<acme:print value="${numberOfTaskNotFinished}"/>
		</td>
	</tr>	
		<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.average"/>
		</th>
		<td id="4">
			<acme:print value="${workloadAverage}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.max"/>
		</th>
		<td id="5">
			<acme:print value="${workloadMax}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.min"/>
		</th>
		<td id="6">
			<acme:print value="${workloadMin}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.deviation"/>
		</th>
		<td id="7">
			<acme:print value="${workloadDeviation}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.startaverage"/>
		</th>
		<td id="8">
			<acme:print value="${startPeriodAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.finalaverage"/>
		</th>
		<td id="9">
			<acme:print value="${finalPeriodAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.startmin"/>
		</th>
		<td id="10">
			<acme:print value="${startPeriodMin}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.finalmin"/>
		</th>
		<td id="11">
			<acme:print value="${finalPeriodMin}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.startmax"/>
		</th>
		<td id="12">
			<acme:print value="${startPeriodMax}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.finalmax"/>
		</th>
		<td id="13">
			<acme:print value="${finalPeriodMax}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.startdev"/>
		</th>
		<td id="14">
			<acme:print value="${startPeriodDeviation}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.finaldev"/>
		</th>
		<td id="15">
			<acme:print value="${finalPeriodDeviation}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.flaggedRatio"/>
		</th>
		<td id="16">
			<acme:print value="${flaggedRatio}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.outdatedInformationsheetsRatio"/>
		</th>
		<td id="17">
			<acme:print value="${outdatedInformationsheetsRatio}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.moneyEuroAverage"/>
		</th>
		<td id="18">
			<acme:print value="${moneyEuroAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.moneyDollarAverage"/>
		</th>
		<td id="19">
			<acme:print value="${moneyDollarAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.moneyEuroDeviation"/>
		</th>
		<td id="20">
			<acme:print value="${moneyEuroDeviation}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.moneyDollarDeviation"/>
		</th>
		<td id="21">
			<acme:print value="${moneyDollarDeviation}"/>
		</td>
	</tr>
	
</table>