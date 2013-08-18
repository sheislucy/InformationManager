<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="/css/token-input-facebook.css" />
<link type="text/css" rel="stylesheet" href="css/house.css" />

<div id="search-criteria">
	<ul>
		<li><a href="#search-tab-1">查询条件</a></li>
	</ul>
	<div id="search-tab-1">
	</div>
</div>
<div id="search-result">
	<table id="jqGrid-house"></table>
	<div id="pager-house"></div>
</div>

<div id="new-house-dialog" title="选择户主" class="hide">
	<label for="host-input">户主姓名</label>
	<input type="text" name="hostId" id="host-input" class="text ui-widget-content" />
</div>

<div id="delete-validation-dialog" title="确认解散该家庭？" class="hide"></div>
<div class="right-bottom-corner">
	<div id="success-tip" class="ui-tooltip ui-widget-content hide">
		<div class="ui-tooltip-content">保存成功</div>
	</div>
	<div id="failure-tip" class="ui-tooltip ui-widget-content hide">
		<div class="ui-tooltip-content">保存失败</div>
	</div>
</div>

<a class="hide" href="" id="edit-house-link" target="_blank"></a>

<script type="text/javascript" src="js/grid.locale-cn.js"></script>
<script type="text/javascript" src="js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="js/jquery.tokeninput.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/house.js"></script>
<script type="text/javascript">
	$(function() {
		init();
		$(":button").button();
		$("#search-criteria").tabs();
	});
</script>