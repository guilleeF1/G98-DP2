<%--
- list.jsp
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

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="60%"/>
	<acme:list-column code="anonymous.shout.form.label.infosheet.date" path="infosheet.date" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.infosheet.moment" path="infosheet.moment" width="20%"/>
	<acme:list-column code="anonymous.shout.form.label.infosheet.money" path="infosheet.money" width="10%"/>
	<acme:list-column code="anonymous.shout.form.label.infosheet.flag" path="infosheet.flag" width="10%"/>
</acme:list>


