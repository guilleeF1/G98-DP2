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
			<acme:form-textbox code="administrator.dashboard.label.public" path="numberOfTaskPublic" readonly="true"/>
		</th>
		<td>
			<acme:print value="${numberOfTaskPublic}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.private" path="numberOfTaskPrivate" readonly="true"/>
		</th>
		<td>
			<acme:print value="${numberOfTaskPrivate}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.finished" path="numberOfTaskFinished" readonly="true"/>
		</th>
		<td>
			<acme:print value="${numberOfTaskFinished}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.unfinished" path="numberOfTaskNotFinished" readonly="true"/>
		</th>
		<td>
			<acme:print value="${numberOfTaskNotFinished}"/>
		</td>
	</tr>	
		<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.average" path="workloadAverage" readonly="true"/>
		</th>
		<td>
			<acme:print value="${workloadAverage}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.max" path="workloadMax" readonly="true"/>
		</th>
		<td>
			<acme:print value="${workloadMax}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.min" path="workloadMin" readonly="true"/>
		</th>
		<td>
			<acme:print value="${workloadMin}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.deviation" path="workloadDeviation" readonly="true"/>
		</th>
		<td>
			<acme:print value="${workloadDeviation}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.startaverage" path="startPeriodAverage" readonly="true"/>
		</th>
		<td>
			<acme:print value="${startPeriodAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.finalaverage" path="finalPeriodAverage" readonly="true"/>
		</th>
		<td>
			<acme:print value="${finalPeriodAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.startmin" path="startPeriodMin" readonly="true"/>
		</th>
		<td>
			<acme:print value="${startPeriodMin}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.finalmin" path="finalPeriodMin" readonly="true"/>
		</th>
		<td>
			<acme:print value="${finalPeriodMin}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.startmax" path="startPeriodMax" readonly="true"/>
		</th>
		<td>
			<acme:print value="${startPeriodMax}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.finalmax" path="finalPeriodMax" readonly="true"/>
		</th>
		<td>
			<acme:print value="${finalPeriodMax}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.startdev" path="startPeriodDeviation" readonly="true"/>
		</th>
		<td>
			<acme:print value="${startPeriodDeviation}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:form-textbox code="administrator.dashboard.label.finaldev" path="finalPeriodDeviation" readonly="true"/>
		</th>
		<td>
			<acme:print value="${finalPeriodDeviation}"/>
		</td>
	</tr>
	
</table>