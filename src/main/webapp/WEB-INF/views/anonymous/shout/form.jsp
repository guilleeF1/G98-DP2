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
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info"/>
	
	<acme:form-textbox code="anonymous.shout.form.label.infosheet.date" path="date" placeholder="dd/mm/yyyy"/>
	<acme:form-double code="anonymous.shout.form.label.infosheet.money" path="amount"/>
	<acme:form-select code="anonymous.shout.form.label.infosheet.currency" path="currency">
		<acme:form-option code="anonymous.shout.form.label.infosheet.euro" value="euro"/>
		<acme:form-option code="anonymous.shout.form.label.infosheet.dollar" value="dollar"/>
	</acme:form-select>
	<acme:form-checkbox code="anonymous.shout.form.label.infosheet.flag" path="flag"/>
	
	
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
  	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
