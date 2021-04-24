
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.task.form.label.publica" path="publica"/>
	<acme:form-textbox code="administrator.task.form.label.titulo" path="titulo"/>
	<acme:form-moment code="administrator.task.form.label.periodoEjecucionInicio" path="periodoEjecucionInicio"/>
	<acme:form-money code="administrator.task.form.label.periodoEjecucionFinal" path="periodoEjecucionFinal"/>
	<acme:form-money code="administrator.task.form.label.cargaTrabajo" path="cargaTrabajo"/>
	<acme:form-textarea code="administrator.task.form.label.descripcion" path="descripcion"/>
	<acme:form-url code="administrator.task.form.label.enlace" path="enlace"/>
	
	<acme:form-submit code="administrator.task.form.button.create" action="/administrator/task/create"/>
  	<acme:form-return code="administrator.task.form.button.return"/>
</acme:form>

