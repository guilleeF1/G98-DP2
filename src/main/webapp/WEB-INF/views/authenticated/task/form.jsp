
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:form-textbox code="authenticated.task.form.label.publica" path="publica"/>
	<acme:form-textbox code="authenticated.task.form.label.titulo" path="titulo"/>
	<acme:form-moment code="authenticated.task.form.label.periodoEjecucionInicio" path="periodoEjecucionInicio"/>
	<acme:form-money code="authenticated.task.form.label.periodoEjecucionFinal" path="periodoEjecucionFinal"/>
	<acme:form-money code="authenticated.task.form.label.cargaTrabajo" path="cargaTrabajo"/>
	<acme:form-textarea code="authenticated.task.form.label.descripcion" path="descripcion"/>
	<acme:form-url code="authenticated.task.form.label.enlace" path="enlace"/>
	
	
	<jstl:if test="${!readonly}">
		<acme:form-submit code="authenticated.task.form.button.create" action="/authenticated/task/create"/>
	</jstl:if>
  	<acme:form-return code="authenticated.task.form.button.return"/>
</acme:form>

