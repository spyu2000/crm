<#if (actionMessages?exists && actionMessages?size > 0)>
	<div class="msg3">
		<#list Session.actionMessages as message>
			<span class="actionMessage">${message}</span><br/>
		</#list>
	</div>
<#elseif (Request.actionMessages?exists && Request.actionMessages?size > 0)>
	<div class="msg3">
		<#list Request.actionMessages as message>
			<span class="actionMessage">${message}</span><br/>
		</#list>
	</div>
<#elseif (Session.actionMessages?exists && Session.actionMessages?size > 0)>
	<div class="msg3">
		<#list Session.actionMessages as message>
			<span class="actionMessage">${message}</span><br/>
		</#list>
	</div>
<#elseif (actionErrors?exists && actionErrors?size > 0)>
	<div class="errormsg">
		<#list actionErrors as errors>
			<span class="errorMessage">${errors}</span>
		</#list>
	</div>
<#elseif (Request.actionErrors?exists && Request.actionErrors?size > 0)>
	<div class="errormsg">
		<#list Request.actionErrors as errors>
			<span class="errorMessage">${errors}</span>
		</#list>
	</div>
</#if>