<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="../css/myChart.css" />
<script type="text/javascript" src="../js/Fusion/FusionCharts.js"></script>
<script type="text/javascript" src="../js/myChart.js"></script>
<script type="text/javascript">
	$(function() {
		$("#search-criteria").tabs();
		$( ".datepicker" ).datepicker();
	});
</script>
<div id="search-criteria">
	<ul>
		<li><a href="#search-tab-1">查询条件</a></li>
	</ul>
	<div id="search-tab-1">
		<div id="searchTime" class="fl ui-widget-header h40 ui-corner-all">
			<span class="fl">时间跨度: 从</span> <input type="text" class="datepicker" />
			<span>至</span><input type="text" class="datepicker" />
		</div>
		
		<div id="button" class="fl h40">
			<input id="search"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
				type="submit" value="搜索" />
		</div>
	</div>
</div>
<div id="dummyChart"></div>
<script>
	$(function() {
		cFactory.generate3DColumn(
<%=request.getParameter("chartId")%>
	);
	});
</script>