<#macro pagination>

<#assign current =  page.getPageNo()>
<#assign begin = page.getBegin()>
<#assign end = page.getEnd()>
<#assign max=current+4>
<#assign min=current-4>

<!-- auto_include无法引入-->
<#include "/travel/common/global.ftl">
<ul>
		<li class="disabled"><a>共${page.totalPages}页</a></li>
		 <#if ((page.isHasNext() && current != 1) || (current == end && current != 1))>
               	<li><a href="?p=1&ps=${page.getPageSize()}" value="" >&lt;&lt;</a></li>
                <li><a href="#" value="${current-1}" class="ajax_pg" >上一页</a></li>
                <#if (min>=0)>
                	
                </#if>
         <#else>
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
         </#if>
		<#list begin..end as i>
			<#if i==current>
				<li class="active"><a href="#" class="ajax_pg" value="${i}">${i}</a></li>
			<#else>
				<#if (i<=max) >
				<li><a href="#" class="ajax_pg" value="${i}">${i}</a></li>
				<#else>
					...
				<#break>
				</#if>
			</#if>
		</#list>

		<#if (page.isHasNext())>
               	<li><a href="#" value="${current+1}" class="ajax_pg" ><span>下一页</span></a></li>
                <li><a href="?p=${page.totalPages}&ps=${page.getPageSize()}">&gt;&gt;</a></li>
         <#else>
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
         </#if>
		到第<input type="text" id="" style="width:20px;" value="${current}" />页<button type="button" class="">确定</button>
		<input type="hidden" id="page_u" value="${request.requestUri}" />
	</ul>
<script src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/pagination/pagination.js"></script>
</#macro>