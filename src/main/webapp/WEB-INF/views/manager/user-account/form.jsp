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
	<acme:form-textbox code="manager.user-account.label.username" path="username"/>
	<acme:form-password code="manager.user-account.label.password" path="password"/>
	<acme:form-password code="manager.user-account.label.confirmation" path="confirmation"/>
	
	<acme:form-textbox code="manager.user-account.label.name" path="identity.name"/>
	<acme:form-textbox code="manager.user-account.label.surname" path="identity.surname"/>
	<acme:form-textbox code="manager.user-account.label.email" path="identity.email"/>
	<acme:form-textbox code="manager.user-account.label.phone" path="identity.phone"/>
	 
	<acme:form-checkbox code="manager.user-account.label.accept" path="accept"/>
	
	<acme:form-submit code="manager.user-account.button.create" action="/manager/user-account/create"/>
  	<acme:form-return code="manager.user-account.button.return"/>
</acme:form>
