<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<c:url value="/js/OpenLayers.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/myLayerSwitcher.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/myMarker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/myPanZoomBar.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/myMap.js" />"></script>
<link type="text/css" rel="stylesheet" href="../css/myMap.css" />

<div id="map-view" class="map-wrapper">
	<div id="explore-map" style="width: 100%; min-height: 300px">
		<script type="text/javascript">
			getMapAndHotSpot("init");
		</script>
	</div>
</div>