<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.planOfJob.list.label.publica" path="publico" width="20%"/>
	<acme:list-column code="anonymous.planOfJob.list.label.periodoEjecucionInicio" path="periodoEjecucionInicio" width="10%"/>
	<acme:list-column code="anonymous.planOfJob.list.label.periodoEjecucionFinal" path="periodoEjecucionFinal" width="10%"/>
	<acme:list-column code="anonymous.planOfJob.list.label.cargaTrabajo" path="cargaTrabajo" width="20%"/>
</acme:list>


