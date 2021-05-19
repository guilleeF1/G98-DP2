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
			<acme:message code="administrator.dashboard.label.public"/>
		</th>
		<td>
			<acme:print value="${numberOfTaskPublic}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.private"/>
		</th>
		<td>
			<acme:print value="${numberOfTaskPrivate}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.finished"/>
		</th>
		<td>
			<acme:print value="${numberOfTaskFinished}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.unfinished"/>
		</th>
		<td>
			<acme:print value="${numberOfTaskNotFinished}"/>
		</td>
	</tr>	
		<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.average"/>
		</th>
		<td>
			<acme:print value="${workloadAverage}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.max"/>
		</th>
		<td>
			<acme:print value="${workloadMax}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.min"/>
		</th>
		<td>
			<acme:print value="${workloadMin}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.deviation"/>
		</th>
		<td>
			<acme:print value="${workloadDeviation}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.startaverage"/>
		</th>
		<td>
			<acme:print value="${startPeriodAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.finalaverage"/>
		</th>
		<td>
			<acme:print value="${finalPeriodAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.startmin"/>
		</th>
		<td>
			<acme:print value="${startPeriodMin}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.finalmin"/>
		</th>
		<td>
			<acme:print value="${finalPeriodMin}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.startmax"/>
		</th>
		<td>
			<acme:print value="${startPeriodMax}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.finalmax"/>
		</th>
		<td>
			<acme:print value="${finalPeriodMax}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.startdev"/>
		</th>
		<td>
			<acme:print value="${startPeriodDeviation}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.label.finaldev"/>
		</th>
		<td>
			<acme:print value="${finalPeriodDeviation}"/>
		</td>
	</tr>
	
</table>