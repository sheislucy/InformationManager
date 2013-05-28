<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="../css/myChart.css" />
<script type="text/javascript" src="../js/Fusion/FusionCharts.js"></script>
<script type="text/javascript" src="../js/myChart.js"></script>
<script type="text/javascript">
	$(function() {
		$("#search-criteria").tabs();
		$("#search-criteria").css('min-height', '100px');
		$(".datepicker").datepicker();
	});
</script>
<div id="search-criteria">
	<ul>
		<li><a href="#search-tab-1">查询条件</a></li>
	</ul>
	<div id="search-tab-1">
		
			<div id="searchTime" class="fl ui-widget-header h40 ui-corner-all">
				<span class="fl">时间跨度: 从</span> <input type="text"
					class="datepicker" /> <span>至</span><input type="text"
					class="datepicker" />
			</div>
			<c:if test="${chartId eq 2 }">
				<div id="gender" class="fl ui-widget-header h40 ui-corner-all">
					<span>性别: </span> <input type="radio" id="male" name="gender" /><label
						for="male">男</label> <input type="radio" id="female" name="gender" /><label
						for="female">女</label> <input type="radio" id="none" name="gender"
						checked /><label for="none">不限</label>
				</div>
				<div id="ageRuleDropDown" class="fl ui-widget-header ui-corner-all">
					<span>年龄 </span> <select id="ageRuleSelect">
						<option id="lt">&lt;=</option>
						<option id="gt">&gt;=</option>
					</select> <input id="searchAge" type="text" class="w50"></input>
				</div>
			</c:if>
			<div id="button" class="fr">
				<input id="search"
					class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
					type="submit" value="搜索" />
			</div>
		<div class="clearFix"></div>
	</div>
</div>
<center style="margin-top: 100px;"><div id="dummyChart"></div></center>
<script>
	$(function() {
		cFactory.generate3DColumn(<%=request.getParameter("chartId")%>, $(".ui-layout-center").width()*0.7, $(".ui-layout-center").height()*0.5);
	});
</script>