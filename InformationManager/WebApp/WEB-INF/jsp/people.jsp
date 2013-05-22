<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/ui.jqgrid.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"
	type="text/css" />
<link type="text/css" rel="stylesheet" href="../css/people.css" />
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/grid.locale-cn.js"></script>
<script type="text/javascript" src="../js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/people.js"></script>
<title>人员信息</title>
<script type="text/javascript">
	$(function() {
		people.getPeopleList();
		$("#search").click(function(){
			var postDataParam={
					searchName: $("#searchName").attr("value"),
					searchGender: $("input[name='gender'][checked]").attr("id"),
					searchAgeRule: $("#ageRuleSelect").find("option:selected").attr("id"),
					searchAge: $("#searchAge").attr("value")
			};
			people.refreshGrid(postDataParam);
		});
	});
</script>
</head>
<body>
	<div>
		<div id="searchPeople">
			<div id="name" class="fl ui-widget-header h40 ui-corner-all">
				<span>姓名: </span> <input id="searchName" type="text"></input>
			</div>
			<div id="gender" class="fl ui-widget-header h40 ui-corner-all">
				<span>性别: </span> <input type="radio" id="male" name="gender" /><label
					for="male">男</label> <input type="radio" id="female" name="gender" /><label
					for="female">女</label> <input type="radio" id="none" name="gender" checked /><label
					for="none">不限</label>
			</div>
			<div id="ageRuleDropDown"
				class="fl ui-widget-header h40 ui-corner-all">
				<span>年龄 </span> <select id="ageRuleSelect">
					<option id="lt">&lt;=</option>
					<option id="gt">&gt;=</option>
				</select> <input id="searchAge" type="text" class="w50"></input>
			</div>
			<div id="button" class="fl h40">
				<input id="search"
					class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
					type="submit" value="搜索" />
			</div>
		</div>

		<input id="initPageSize" type="hidden"
			value="<c:out value="${defaultPageSize}" />" /> <input
			id="optionalPageSize" type="hidden"
			value="<c:out value="${optionalPageSize}" />" />

		<table id="jqGrid-people"></table>
		<div id="pager-people"></div>
	</div>
</body>
</html>