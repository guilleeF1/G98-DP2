
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-checkbox code="manager.workplan.form.label.publico" path="publico"/>
	<acme:form-moment code="manager.workplan.form.label.periodoEjecucionInicio" path="periodoEjecucionInicio"/>
	<acme:form-moment code="manager.workplan.form.label.periodoEjecucionFinal" path="periodoEjecucionFinal"/>
	<acme:form-integer readonly="true" code="manager.workplan.form.label.cargaTrabajo" path="cargaTrabajo"/>
	
	<acme:form-submit test="${command == 'show'}" code="manager.workplan.form.button.update" action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'show'}" code="manager.workplan.form.button.delete" action="/manager/workplan/delete"/>
	<acme:form-submit test="${command == 'create'}" code="manager.workplan.form.button.create" action="/manager/workplan/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.workplan.form.button.update" action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.workplan.form.button.delete" action="/manager/workplan/delete"/>
  	<acme:form-return code="manager.task.form.button.return"/>
</acme:form>

