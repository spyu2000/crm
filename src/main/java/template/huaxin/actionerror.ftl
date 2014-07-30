<#if (actionErrors?exists && actionErrors?size > 0)>
	<div class="errormsg">
	<#list actionErrors as errors>
		<span class="errorMessage">${errors}</span>
	</#list>
	</div>
</#if>

