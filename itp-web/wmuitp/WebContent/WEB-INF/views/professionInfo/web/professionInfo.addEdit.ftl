<div class="pageFormContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "formDatasAdd('professionInfoAddForm');return false;"><span>保存</span></a></li>
		</ul>
	</div>
	<form id="professionInfoAddForm" method="POST" class="pageForm required-validate" action="${ctx}/professionInfo/professionInfoAdd" style="width:350px;float:left">
		
		<p style="width:340px;float:left">
			<label style="width:70px">专业名称：</label>
			<input type="text" size="30" value = "${(professionInfo.piProfession)?default("")}"  name = "piProfession" class="required"/>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">所属学院：</label>
			<input type="text" size="30" value = "${(professionInfo.piCollege)?default("")}"  name = "piCollege" class="required"/>
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">详情备注：</label>
			<input type="text" size="30" value = "${(professionInfo.piSign)?default("")}"  name = "piSign" />
		</p>
		
		<p style="width:340px;float:left">
			<input type="hidden" size="30" value = "${(professionInfo.piId)?default("")}"  name = "piId" />
		</p>
		
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick = "formDatasAdd('professionInfoAddForm');return false;">保存</button>
						</div>	
					</div>
				</li>
				
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
