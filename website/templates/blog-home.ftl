<#include "header.ftl">
	
	<#include "menu.ftl">
	
	<div id="main">
        <#list published_posts as post>
		<#if (post??) >
			<#include "post/content-single.ftl">
		</#if>
		</#list>
		
	 </div>
	
	<#include "commons/sidebar.ftl">
<#include "footer.ftl">