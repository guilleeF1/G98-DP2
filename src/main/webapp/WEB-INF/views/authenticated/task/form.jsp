
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form readonly="true">
	<acme:form-checkbox code="authenticated.task.form.label.publica" path="publica"/>
	<acme:form-textbox code="authenticated.task.form.label.titulo" path="titulo"/>
	<acme:form-moment code="authenticated.task.form.label.periodoEjecucionInicio" path="periodoEjecucionInicio"/>
	<acme:form-moment code="authenticated.task.form.label.periodoEjecucionFinal" path="periodoEjecucionFinal"/>
	<acme:form-integer code="authenticated.task.form.label.cargaTrabajo" path="cargaTrabajo"/>
	<acme:form-integer code="authenticated.task.form.label.cargaTrabajoMinutos" path="cargaTrabajoMinutos"/>
	<acme:form-textarea code="authenticated.task.form.label.descripcion" path="descripcion"/>
	<acme:form-url code="authenticated.task.form.label.enlace" path="enlace"/>
	
	
  	<acme:form-return code="authenticated.task.form.button.return"/>
</acme:form>

