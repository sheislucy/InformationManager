<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="css/people.css" />
<script type="text/javascript" src="js/grid.locale-cn.js"></script>
<script type="text/javascript" src="js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/people.js"></script>
<div id="search-criteria">
	<ul>
		<li><a href="#search-tab-1">查询条件</a></li>
	</ul>
	<div id="search-tab-1">
		<div id="name" class="fl ui-state-active ui-corner-all">
			<span class="fl">姓名: </span> <input id="searchName" type="text"></input>
		</div>
		<div id="gender" class="fl ui-state-active ui-corner-all">
			<span>性别: </span> <input type="radio" id="male" name="gender" /><label
				for="male">男</label> <input type="radio" id="female" name="gender" /><label
				for="female">女</label> <input type="radio" id="none" name="gender"
				checked /><label for="none">不限</label>
		</div>
		<div id="ageRuleDropDown"
			class="fl ui-state-active ui-corner-all">
			<span>年龄 </span> <select id="ageRuleSelect">
				<option id="lt">&lt;=</option>
				<option id="gt">&gt;=</option>
			</select> <input id="searchAge" type="text" class="w50"></input>
		</div>
		<div id="button" class="fr">
			<input id="search"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
				type="button" value="搜索" />
		</div>
		<div class="clearFix"></div>
	</div>
</div>
<div id="search-result">
	<table id="jqGrid-people"></table>
	<div id="pager-people"></div>
</div>
<script type="text/javascript">
	$(function() {
		people.getPeopleList();
		$("#search").click(
				function() {
					var postDataParam = {
						searchName : $("#searchName").attr("value"),
						searchGender : $("input[name='gender'][checked]").attr(
								"id"),
						searchAgeRule : $("#ageRuleSelect").find(
								"option:selected").attr("id"),
						searchAge : $("#searchAge").attr("value")
					};
					people.refreshGrid(postDataParam);
				});
		$(":button").button();
		$("#search-criteria").tabs();
		window.setTimeout(adjustCenterSize(),200);
	});
</script>