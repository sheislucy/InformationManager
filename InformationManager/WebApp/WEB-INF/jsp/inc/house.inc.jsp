<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="css/people.css" />
<script type="text/javascript" src="js/grid.locale-cn.js"></script>
<script type="text/javascript" src="js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/house.js"></script>
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
<script type="text/javascript">
	$(function() {
		house.getHouseList();
		$(":button").button();
		$("#search-criteria").tabs();
		window.setTimeout(adjustCenterSize(),200);
	});
</script>