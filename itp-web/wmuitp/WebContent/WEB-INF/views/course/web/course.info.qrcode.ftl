<!DOCTYPE html>
<html>
<head>
</head>

<body>
	<#if courseInfos?exists>
		<#list courseInfos as courseInfo>
			<img src="${courseInfo.ciQrcode}" />
		</#list>
	</#if>
</body>
</html>
