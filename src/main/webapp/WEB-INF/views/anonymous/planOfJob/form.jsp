<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>


<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.planOfJob.form.label.publica" path="publico"/>
	<acme:form-moment code="anonymous.planOfJob.form.label.periodoEjecucionInicio" path="periodoEjecucionInicio"/>
	<acme:form-money code="anonymous.planOfJob.form.label.periodoEjecucionFinal" path="periodoEjecucionFinal"/>
	<acme:form-money code="anonymous.planOfJob.form.label.cargaTrabajo" path="cargaTrabajo"/>
	
	<acme:form-submit code="anonymous.planOfJob.form.button.create" action="/anonymous/planOfJob/create"/>
  	<acme:form-return code="anonymous.planOfJob.form.button.return"/>
</acme:form>

