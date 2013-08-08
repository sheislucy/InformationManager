<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="css/myMap.css" />

<div id="map-view" class="map-wrapper">
	<div id="explore-map" style="min-height: 300px">
	</div>
</div>
<input type="hidden" id="mapId" value="${mapid}" />
<input type="hidden" id="mapWidth" value="${map.width}" />
<input type="hidden" id="mapHeight" value="${map.height}" />
<input type="hidden" id="mapName" value="${map.mapName}" />
<input type="hidden" id="mapUrl" value="${map.url}" />

<script type="text/javascript" src="js/OpenLayers.js"></script>
<script type="text/javascript" src="js/myLayerSwitcher.js"></script>
<script type="text/javascript" src="js/myMarker.js"></script>
<script type="text/javascript" src="js/myPanZoomBar.js"></script>
<script type="text/javascript" src="js/myMap.js"></script>
<script type="text/javascript">
$(function(){
	//var map_height =$(window).innerHeight()-10;
	//$('#explore-map').height($("#treeMenu").innerHeight()-10);
	
	var map_height = $("#treeMenu").innerHeight();
	$('#explore-map').height(map_height);
	var map_width = map_height / $("#mapHeight").val() * $("#mapWidth").val();
	
	getMapAndHotSpot({
		"view_width": map_width, 
		"view_height": map_height, 
		"mapId": $("#mapId").val(),
		"mapName": $("#mapName").val(),
		"mapImageUrl": $("#mapUrl").val()
	});
});
</script>