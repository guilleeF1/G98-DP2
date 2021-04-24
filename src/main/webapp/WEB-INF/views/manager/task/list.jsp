<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="manager.task.list.label.publica" path="publica" width="10%"/>
	<acme:list-column code="manager.task.list.label.titulo" path="titulo" width="10%"/>
	<acme:list-column code="manager.task.list.label.periodoEjecucionInicio" path="periodoEjecucionInicio" width="10%"/>
	<acme:list-column code="manager.task.list.label.periodoEjecucionFinal" path="periodoEjecucionFinal" width="10%"/>
	<acme:list-column code="manager.task.list.label.cargaTrabajo" path="cargaTrabajo" width="10%"/>
	<acme:list-column code="manager.task.list.label.descripcion" path="descripcion" width="10%"/>
	<acme:list-column code="manager.task.list.label.enlace" path="enlace" width="10%"/>
</acme:list>


