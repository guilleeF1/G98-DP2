
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-checkbox code="manager.task.form.label.publica" path="publica"/>
	<acme:form-textbox code="manager.task.form.label.titulo" path="titulo"/>
	<acme:form-moment code="manager.task.form.label.periodoEjecucionInicio" path="periodoEjecucionInicio"/>
	<acme:form-moment code="manager.task.form.label.periodoEjecucionFinal" path="periodoEjecucionFinal"/>
	<acme:form-integer code="manager.task.form.label.cargaTrabajo" path="cargaTrabajo"/>
	<acme:form-integer code="authenticated.task.form.label.cargaTrabajoMinutos" path="cargaTrabajoMinutos"/>
	<acme:form-textarea code="manager.task.form.label.descripcion" path="descripcion"/>
	<acme:form-url code="manager.task.form.label.enlace" path="enlace"/>
	
	<jstl:if test="${readonly == 'false'}">
	<acme:form-submit test="${command == 'show'}" code="manager.task.form.button.update" action="/Acme-Planner/manager/task/update"/>
	<acme:form-submit test="${command == 'show'}" code="manager.task.form.button.delete" action="/Acme-Planner/manager/task/delete"/>
	</jstl:if>
	<acme:form-submit test="${command == 'create'}" code="manager.task.form.button.create" action="/Acme-Planner/manager/task/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.task.form.button.update" action="/Acme-Planner/manager/task/update"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.task.form.button.delete" action="/Acme-Planner/manager/task/delete"/>
  	<acme:form-return code="manager.task.form.button.return"/>
</acme:form>

