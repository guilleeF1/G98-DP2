
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.task.form.label.publica" path="publica"/>
	<acme:form-textbox code="anonymous.task.form.label.titulo" path="titulo"/>
	<acme:form-moment code="anonymous.task.form.label.periodoEjecucionInicio" path="periodoEjecucionInicio"/>
	<acme:form-moment code="anonymous.task.form.label.periodoEjecucionFinal" path="periodoEjecucionFinal"/>
	<acme:form-integer code="anonymous.task.form.label.cargaTrabajo" path="cargaTrabajo"/>
	<acme:form-integer code="anonymous.task.form.label.cargaTrabajoMinutos" path="cargaTrabajoMinutos"/>
	<acme:form-textarea code="anonymous.task.form.label.descripcion" path="descripcion"/>
	<acme:form-url code="anonymous.task.form.label.enlace" path="enlace"/>
	
	<acme:form-submit test="${command == 'show'}" code="anonymous.task.form.button.update" action="/anonymous/task/update"/>
	<acme:form-submit test="${command == 'show'}" code="anonymous.task.form.button.delete" action="/anonymous/task/delete"/>
	<acme:form-submit test="${command == 'create'}" code="anonymous.task.form.button.create" action="/anonymous/task/create"/>
	<acme:form-submit test="${command == 'update'}" code="anonymous.task.form.button.update" action="/anonymous/task/update"/>
	<acme:form-submit test="${command == 'delete'}" code="anonymous.task.form.button.delete" action="/anonymous/task/delete"/>
  	<acme:form-return code="anonymous.task.form.button.return"/>
</acme:form>

