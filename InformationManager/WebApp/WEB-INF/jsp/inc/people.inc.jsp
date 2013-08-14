<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="css/people.css" />
<script type="text/javascript" src="js/grid.locale-cn.js"></script>
<script type="text/javascript" src="js/jquery.jqGrid.4.5.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/people.js"></script>
<div id="search-criteria">
	<ul>
		<li><a href="#composite-search">查询条件</a></li>
	</ul>
	<div id="composite-search">
		<div id="name" class="fl ui-state-active ui-corner-all">
			<label for="search-name">姓名</label><input id="search-name" type="text"></input>
		</div>
		<div id="gender" class="fl ui-state-active ui-corner-all">
			<span>性别</span>
			<input type="radio" id="male" name="gender" value="true" /><label for="male">男</label>
			<input type="radio" id="female" name="gender" value="false" /><label for="female">女</label>
			<input type="radio" id="none" name="gender" value="" checked="checked" /><label for="none">不限</label>
		</div>
		<div id="age-limit" class="fl ui-state-active ui-corner-all">
			<span>年龄范围</span>
			<label for="age-low-limit">下限</label><input name="ageLowLimit" type="text" id="age-low-limit" value="" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
			<label for="age-upper-limit">上限</label><input name="ageUpperLimit" type="text" id="age-upper-limit" value="" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
		</div>
		<div class="clear"></div>
		<div id="income-limit" class="fl ui-state-active ui-corner-all">
			<span>收入范围</span>
			<label for="incoming-low-limit">下限</label><input name="incomingLowLimit" type="text" id="incoming-low-limit" value="" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
			<label for="incoming-upper-limit">上限</label><input name="incomingUpperLimit" type="text" id="incoming-upper-limit" value="" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
		</div>
		<div id="political" class="fl ui-state-active ui-corner-all">
			<span>政治面貌</span>
			<input name="political" type="checkbox" id="common" value="1" /><label for="common">群众</label>
			<input name="political" type="checkbox" id="youth-league" value="2" /><label for="youth-league">团员</label>
			<input name="political" type="checkbox" id="party" value="3" /><label for="party">党员</label>
			<input name="political" type="checkbox" id="other" value="4" /><label for="other">民主党派</label>
		</div>
		<div class="clear"></div>
		<div id="education" class="fl ui-state-active ui-corner-all">
			<span>教育程度</span>
			<input name="education" type="checkbox" id="edu-none" value="1" /><label for="edu-none">未受教育</label>
			<input name="education" type="checkbox" id="primary" value="2" /><label for="primary">小学</label>
			<input name="education" type="checkbox" id="middle" value="3" /><label for="middle">初中</label>
			<input name="education" type="checkbox" id="high" value="4" /><label for="high">高中</label>
			<input name="education" type="checkbox" id="tech" value="5" /><label for="tech">中专中技职校</label>
			<input name="education" type="checkbox" id="college" value="6" /><label for="college">大专</label>
			<input name="education" type="checkbox" id="bachelor" value="7" /><label for="bachelor">本科</label>
			<input name="education" type="checkbox" id="master" value="8" /><label for="master">硕士</label>
			<input name="education" type="checkbox" id="doctor" value="9" /><label for="doctor">博士</label>
		</div>
		<div id="submit" class="fr ui-state-default submit">
			<a>查询</a>
		</div>
		<div class="clear"></div>
	</div>
</div>
<div id="search-result">
	<table id="jqGrid-people"></table>
	<div id="pager-people"></div>
</div>
<script type="text/javascript">
	$(function() {
		people.getPeopleList();
		$("#submit").click(
				function() {
					var political = [];
					var education = [];
					$("input=[name='political']:checked").each(function(){
						political.push($(this).val());
					});
					$("input=[name='education']:checked").each(function(){
						education.push($(this).val());
					});
					var postDataParam = {
						"name" : $("#search-name").val(),
						"gender" : $("input[name='gender']:checked").val(),
						"ageLow" : $("#age-low-limit").val(),
						"ageUp" : $("#age-upper-limit").val(),
						"incomeLow": $("#incoming-low-limit").val(),
						"incomeUp": $("#incoming-upper-limit").val(),
						"political": political,
						"education": education
					};
					people.refreshGrid(postDataParam);
				});
		$(":button").button();
		$("#search-criteria").tabs();
		window.setTimeout(adjustCenterSize(),200);
	});
</script>