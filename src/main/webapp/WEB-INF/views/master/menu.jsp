<%--
- menu.jsp
-
- Copyright (c) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.arrans-favourite-link" action="https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab"/>			
			<acme:menu-suboption code="master.menu.anonymous.bejarano-favourite-link" action="https://www.estadiodeportivo.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.beltran-favourite-link" action="https://www.huelvainformacion.es/huelva/Colegio-Ferroviarios-Adios-decadencia_0_1467453460.html"/>
			<acme:menu-suboption code="master.menu.anonymous.bwye-favourite-link" action="http://observatorio-us.appspot.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.colmenero-favourite-link" action="https://myanimelist.net/manga/91941/Made_in_Abyss?q=made&cat=manga"/>
			<acme:menu-suboption code="master.menu.anonymous.lopez-favourite-link" action="https://www.amazon.es/Cola-Cao-Original-1600-gr/dp/B00OHSPWUY/ref=sr_1_1_sspa?adgrpid=111547091755&dchild=1&gclid=Cj0KCQjwi7yCBhDJARIsAMWFScPGwfuFshDdsypHMf31qa4I4pQpZnCbMy5TQQxNwyLVC-j-wlo987AaArJAEALw_wcB&hvadid=470896206949&hvdev=c&hvlocphy=9047036&hvnetw=g&hvqmt=b&hvrand=5533190105916645360&hvtargid=kwd-979551371260&hydadcr=11431_1828588&keywords=colacao+oferta&qid=1615830227&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUFIRFZaVjYxVkVZM0YmZW5jcnlwdGVkSWQ9QTAzOTc1MTFZTE9FQUtIVzhGVUcmZW5jcnlwdGVkQWRJZD1BMDcyNzEwODNDTjlYSjcxVFpNSFUmd2lkZ2V0TmFtZT1zcF9hdGYmYWN0aW9uPWNsaWNrUmVkaXJlY3QmZG9Ob3RMb2dDbGljaz10cnVl
"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/master/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/master/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

