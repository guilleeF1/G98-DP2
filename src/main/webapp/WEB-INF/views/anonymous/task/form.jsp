
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.task.form.label.publica" path="publica"/>
	<acme:form-textbox code="anonymous.task.form.label.titulo" path="titulo"/>
	<acme:form-moment code="anonymous.task.form.label.periodoEjecucionInicio" path="periodoEjecucionInicio"/>
	<acme:form-money code="anonymous.task.form.label.periodoEjecucionFinal" path="periodoEjecucionFinal"/>
	<acme:form-money code="anonymous.task.form.label.cargaTrabajo" path="cargaTrabajo"/>
	<acme:form-textarea code="anonymous.task.form.label.descripcion" path="descripcion"/>
	<acme:form-url code="anonymous.task.form.label.enlace" path="enlace"/>
	
	<acme:form-submit code="anonymous.task.form.button.create" action="/anonymous/task/create"/>
  	<acme:form-return code="anonymous.task.form.button.return"/>
</acme:form>

