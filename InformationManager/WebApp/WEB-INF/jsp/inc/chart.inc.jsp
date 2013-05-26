<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="../js/Fusion/FusionCharts.js"></script>
<script type="text/javascript" src="../js/myChart.js"></script>

<div id="dummyChart"></div>
<script>
$(function(){
	cFactory.generate3DColumn(<%=request.getParameter("chartId")%>);
});
</script>