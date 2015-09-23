<div class="pageFormContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "formDatasAdd('messageSenderAddForm');return false;"><span>发送</span></a></li>
		</ul>
	</div>
	<form id="messageSenderAddForm" method="POST" class="pageForm required-validate" action="${ctx}/messageSender/messageSenderAdd" style="height:350px;float:left">
		
		<dl class="nowrap">
			<dt>收件人：</dt>
			<dd>
				<input name="senderName" type="text" class="required" readonly="readonly"/>
				<a class="btnLook" href="${ctx}/messageSender/showMessageSenderLookup" lookupGroup="" width="950">查找带回</a>
			</dd>
		</dl>
		<dl class="nowrap">
			<dt>收件人编号：</dt>
			<dd>
				<input class="readonly" name="senderIds"  readonly="readonly" type="text"  class="required"/>
			</dd>
		</dl>
		<p style="width:340px;float:left">
			<label style="width:70px">信标题：</label>
			<input type="text" size="30"   name = "msTopic" class="required"/>
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">信内容：</label>
			<textarea name="msContent"  rows="5" cols="50" class="required"/>
		</p>
		

	</form>
</div>
