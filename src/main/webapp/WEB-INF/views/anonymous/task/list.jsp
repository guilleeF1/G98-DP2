<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.task.list.label.publica" path="publica" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.titulo" path="titulo" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.periodoEjecucionInicio" path="periodoEjecucionInicio" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.periodoEjecucionFinal" path="periodoEjecucionFinal" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.cargaTrabajo" path="cargaTrabajo" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.descripcion" path="descripcion" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.enlace" path="enlace" width="20%"/>
</acme:list>


