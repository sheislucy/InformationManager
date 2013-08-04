<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/OpenLayers.js"></script>
<script type="text/javascript" src="js/myLayerSwitcher.js"></script>
<script type="text/javascript" src="js/myMarker.js"></script>
<script type="text/javascript" src="js/myPanZoomBar.js"></script>
<script type="text/javascript" src="js/myMap.js"></script>
<link type="text/css" rel="stylesheet" href="css/myMap.css" />

<div id="map-view" class="map-wrapper">
	<div id="explore-map" style="width: 100%; min-height: 300px">
	</div>
</div>
<input type="hidden" id="mapId" value="${mapid}" />
<input type="hidden" id="dataParent" value="${dataParent}" />
<script type="text/javascript">
	getMapAndHotSpot();
</script>