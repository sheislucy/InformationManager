<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="/css/jquery-ui-1.10.3.custom.min.css" />
<link type="text/css" rel="stylesheet" href="/css/token-input-facebook.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<link type="text/css" rel="stylesheet" href="/css/myMap.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地图编辑</title>
</head>
<body>
	<div id="tool-bar" class="ui-widget-header">
		<div id="controlRadio">
			<p><input name="control" id="cursor" type="radio" checked/><label for="cursor" class="title">普通鼠标</label></p>
			<p><input name="control" id="drawer" type="radio" /><label for="drawer" class="title">选择画笔</label></p>
		</div>
		<div id="shapeRadio" class="hide">
			<p><input name="shape" id="point" type="radio" /><label for="point">画点</label></p>
			<p><input name="shape" id="line" type="radio" /><label for="line">画线段</label></p>
			<p><input name="shape" id="polygon" type="radio" /><label for="polygon">画多边形</label></p>
			<!-- <p><input name="shape" id="drag" type="radio" /><label for="drag">拖拽热点</label></p> -->
		</div>
		<div id="polygonRadio" class="hide">
			<p><input name="polygonType" id="freeform" type="radio" /><label for="freeform">任意多边形</label></p>
			<p><input name="polygonType" id="triangle" type="radio" /><label for="triangle">三角形</label><input name="polygonType" id="regTriangle" type="radio" /><label for="regTriangle">正三角形</label></p>
			<p><input name="polygonType" id="rectangle" type="radio" /><label for="rectangle">&nbsp;矩&nbsp;形&nbsp;</label><input name="polygonType" id="square" type="radio" /><label for="square">&nbsp;正方形&nbsp;&nbsp;</label></p>
			<p><input name="polygonType" id="pentagon" type="radio" /><label for="pentagon">五边形</label><input name="polygonType" id="regPentagon" type="radio" /><label for="regPentagon">正五边形</label></p>
			<p><input name="polygonType" id="hexagon" type="radio" /><label for="hexagon">六边形</label><input name="polygonType" id="regHexagon" type="radio" /><label for="regHexagon">正六边形</label></p>
			<p><input name="polygonType" id="oval" type="radio" /><label for="oval">&nbsp;椭&nbsp;圆&nbsp;</label><input name="polygonType" id="circle" type="radio" /><label for="circle">&nbsp;&nbsp;圆&nbsp;形&nbsp;&nbsp;&nbsp;</label></p>
		</div>
	</div>
	<div id="map-wrapper">
		<div id="explore-map" style="height: 600px">
		</div>
	</div>
	<div id="point-people-dialog" title="户/家庭信息" class="hide">
			<div class="validateTips hidden"></div>
			<form>
				<input type="hidden" id="mapId" name="mapId" value="${mapid}" />
				<p>
					<label for="host-input">户主姓名</label>
					<input type="text" name="hostId" id="host-input" class="text ui-widget-content" />
				</p>
				<br />
				<p>
					<label for="remark">备注</label>
				</p>
					<textarea id="remark" rows="6" cols="48" class="text ui-widget-content"></textarea>
			</form>
	</div>
	<div id="delete-confirm-dialog" title="确认删除该热点?" class="hide"></div>
	<div class="middle-window">
		<div id="success-tip" class="ui-tooltip ui-widget ui-widget-content hide">
			<div class="ui-tooltip-content">保存成功</div>
		</div>
		<div id="failure-tip" class="ui-tooltip ui-widget ui-widget-content hide">
			<div class="ui-tooltip-content">保存失败</div>
		</div>
	</div>
	<input type="hidden" id="mapWidth" value="${map.width}" />
	<input type="hidden" id="mapHeight" value="${map.height}" />
	<input type="hidden" id="mapName" value="${map.mapName}" />
	<input type="hidden" id="mapUrl" value="${map.url}" />

	<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script type="text/javascript" src="/js/OpenLayers.js"></script>
	<script type="text/javascript" src="/js/jquery.tokeninput.js"></script>
	
	<script type="text/javascript" src="/js/myLayerSwitcher.js"></script>
	<script type="text/javascript" src="/js/myMarker.js"></script>
	<script type="text/javascript" src="/js/myPanZoomBar.js"></script>
	<script type="text/javascript" src="/js/myEditMap.js"></script>
	<script type="text/javascript">
		$(function(){
			var map_height =$(window).innerHeight()-10;
			$('#explore-map').height(map_height);
			$('#tool-bar').height(map_height);
			var map_width = map_height / $("#mapHeight").val() * $("#mapWidth").val();
			
			initAll({
				"view_width": map_width, 
				"view_height": map_height, 
				"mapId": $("#mapId").val(),
				"mapName": $("#mapName").val(),
				"mapImageUrl": $("#mapUrl").val()
				});
			
		});
	</script>
</body>
</html>