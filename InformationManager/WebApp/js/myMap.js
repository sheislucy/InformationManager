var Feature = OpenLayers.Feature.Vector;
var Geometry = OpenLayers.Geometry;
var Map = OpenLayers.Map;
var Image = OpenLayers.Layer.Image;
var Rule = OpenLayers.Rule;
var Filter = OpenLayers.Filter;
var web_context = "";

// map generator-----------start--------------
var MapManager = function() {
};

/**
 * mapMeta:{ view_width:"", view_height:"", mapName:"", mapImageUrl:"", mapId:"" }
 * 
 * hotspotMeta:{ points: [], polygons: [], lines: [] } 
 * 
 * All coordinations are following WKT standards.
 * 
 */
MapManager.prototype.genMap = function(mapMeta, hotspotMeta) {
	if (this.map) {
		this.map.destroy();
	}
	this.map = new Map('explore-map', {
		projection : "EPSG:3857",
		controls : [],
		fractionalZoom : true
	});
	this.dbMapId = mapMeta.mapId;
	this.map.layers = new Array();

	var options = {
		numZoomLevels : 6,
	};
	
	//bounds use the original height/width, THIS SHOULD BE A STATIC AND FIXED VALUE!!
	var graphic1 = new Image(mapMeta.mapName,
			web_context + mapMeta.mapImageUrl, new OpenLayers.Bounds(0, 0,
					$("#mapWidth").val(), $("#mapHeight").val()),
			new OpenLayers.Size(mapMeta.view_width, mapMeta.view_height),
			options);
	
	var pointFeatures = new Array();
	if (hotspotMeta && hotspotMeta.points && hotspotMeta.points.length > 0) {
		for ( var i = 0; i < hotspotMeta.points.length; i++) {
			var feature = new Feature(new Geometry.fromWKT(hotspotMeta.points[i].coordination), {
				styleClass : "pointDefault",
				dbFeatureId : hotspotMeta.points[i].id,
				hostName: hotspotMeta.points[i].hostName + " - 户主id: " + hotspotMeta.points[i].hostId,
				description: hotspotMeta.points[i].description
			});
			pointFeatures.push(feature);
		}
	}
	
	var lineFeatures = new Array();
	if (hotspotMeta && hotspotMeta.lines && hotspotMeta.lines.length > 0) {
		for ( var i = 0; i < hotspotMeta.lines.length; i++) {
			var feature = new Feature(new Geometry.fromWKT(hotspotMeta.lines[i].coordination), {
				styleClass : "lineDefault",
				dbFeatureId : hotspotMeta.lines[i].id,
				description: hotspotMeta.lines[i].description
			});
			lineFeatures.push(feature);
		}
	}
	
	var polygonFeatures = new Array();
	if (hotspotMeta && hotspotMeta.polygons && hotspotMeta.polygons.length > 0) {
		for ( var i = 0; i < hotspotMeta.polygons.length; i++) {
			var feature = new Feature(new Geometry.fromWKT(hotspotMeta.polygons[i].coordination), {
				styleClass : "zoneDefault",
				dbFeatureId : hotspotMeta.polygons[i].id,
				description: hotspotMeta.polygons[i].description
			});
			polygonFeatures.push(feature);
		}
	}
	
	var defaultStyle = new OpenLayers.Style({
		pointRadius : 10,
		strokeWidth : 3,
		strokeOpacity : 0.6,
		strokeColor : "navy",
		fillColor : "#ffcc66",
		fillOpacity : 1
	}, {
		rules : [ new Rule({
			filter : new Filter.Comparison({
				type : "==",
				property : "styleClass",
				value : "pointDefault"
			}),
			symbolizer : {
				pointRadius : 15,
				strokeWidth : 2,
				srokeColor : '#9C9C9C',
				externalGraphic : web_context + '/images/material/a20.png',
				graphicXOffset : -15,
				graphicYOffset : -30,
				cursor : "pointer"
			}
		}), new Rule({
			filter : new Filter.Comparison({
				type : "==",
				property : "styleClass",
				value : "zoneDefault"
			}),
			symbolizer : {
				strokeWidth : 2,
				strokeOpacity : 0.6,
				strokeColor : "#00ffa5",
				fillColor : "#defaf9",
				fillOpacity : 0.4,
				cursor : "pointer"
			}
		}), new Rule({
			filter : new Filter.Comparison({
				type : "==",
				property : "styleClass",
				value : "lineDefault"
			}),
			symbolizer : {
				strokeWidth : 5,
				strokeOpacity : 0.6,
				strokeColor : "#d21838",
				cursor : "pointer"
			}
		}) ]
	});

	var selectStyle = new OpenLayers.Style({
		fillColor : "red",
		pointRadius : 13,
		strokeColor : "yellow",
		strokeWidth : 3
	}, {
		rules : [ new Rule({
			filter : new Filter.Comparison({
				type : "==",
				property : "styleClass",
				value : "pointDefault"
			}),
			symbolizer : {
				pointRadius : 15,
				strokeWidth : 2,
				srokeColor : '#9C9C9C',
				externalGraphic : web_context + '/images/material/a23.png',
				graphicXOffset : -15,
				graphicYOffset : -30,
				cursor : "pointer"
			}
		}), new Rule({
			filter : new Filter.Comparison({
				type : "==",
				property : "styleClass",
				value : "zoneDefault"
			}),
			symbolizer : {
				strokeWidth : 2,
				strokeOpacity : 0.7,
				strokeColor : "#4be900",
				fillColor : "#defaf9",
				fillOpacity : 0.5,
				cursor : "pointer"
			}
		}), new Rule({
			filter : new Filter.Comparison({
				type : "==",
				property : "styleClass",
				value : "lineDefault"
			}),
			symbolizer : {
				strokeWidth : 5,
				strokeOpacity : 0.7,
				strokeColor : "#fd0392",
				cursor : "pointer"
			}
		}) ]
	});

	var vectorLayer = new OpenLayers.Layer.Vector("热点", {
		styleMap : new OpenLayers.StyleMap({
			"default" : defaultStyle,
			"select" : selectStyle
		}),
	});

	vectorLayer.addFeatures(pointFeatures);
	vectorLayer.addFeatures(lineFeatures);
	vectorLayer.addFeatures(polygonFeatures);

	var highlightCtrlr = new OpenLayers.Control.SelectFeature(vectorLayer, {
		hover : true,
		highlightOnly : true,
		renderIntent : "select",
		geometryTypes : ["OpenLayers.Geometry.Polygon", "OpenLayers.Geometry.LineString"]
	});
	// select points
	var selectCtrlr = new OpenLayers.Control.SelectFeature(vectorLayer, {
		clickout : true
	});
	vectorLayer.events.on({
		'featureselected' : showMarker,
		'featureunselected' : hideMarker
	});

	var switcher = new MyLayerSwitcher({
		roundedCorner : false
	});

	this.map.addLayers([ graphic1, vectorLayer ]);
	this.map.addControls([ switcher, highlightCtrlr, selectCtrlr,
			new OpenLayers.Control.MousePosition(),
			new OpenLayers.Control.Navigation(), new MyPanZoomBar() ]);
	this.map.zoomToMaxExtent();
	this.map.events.register("mousemove", this.map, function(e) {
		mouseLonlat = e.object.getLonLatFromPixel(e.xy);
	});
	highlightCtrlr.activate();
	selectCtrlr.activate();
};

var mouseLonlat = {};

var hideMarker = function(evt) {
	var feature = evt.feature;
	this.map.removePopup(feature.popup);
	feature.popup.destroy();
	feature.popup = null;
};

var showMarker = function(evt) {
	var feature = evt.feature;
	var lonlat;
	var mouseLonlatOnClick = mouseLonlat;
	var text = "";
	if (feature.geometry instanceof Geometry.Point) {
		lonlat = OpenLayers.LonLat.fromString(feature.geometry.toShortString());
		text = "<div style='min-width: 220px; min-height: 50px'>户主：<a href='' style='margin-left: 5px;' target='_blank'>" + feature.data.hostName +"</a><br /><span style='position: absolute;'>备注：</span><div id='marker-description' style='margin-left: 45px;width: 160px;'>"
		+ feature.data.description + "</div><div class='clear'></div></div>";
	} else {
		lonlat = new OpenLayers.LonLat(mouseLonlatOnClick.lon,
				mouseLonlatOnClick.lat);
		text = "<span style='top: 5px;position: absolute;'>备注：</span><div id='marker-description' style='margin-left: 45px;width: 160px;'>" 
			+ feature.data.description + "</div><div class='clear'></div>";
	}
	
	var popup = new MyMarker("myPopup", lonlat,
			new OpenLayers.Size(70, 60), text,
			null, false, null, null);
	popup.minSize = new OpenLayers.Size(80, 60);
	popup.autoSize = true;
	feature.popup = popup;
	this.map.addPopup(popup);
	
};

// map generator-----------end--------------
var getMapAndHotSpot = function(mapMeta) {
	var mapManager = new MapManager();
	$.getJSON(web_context + '/map/hotspots/' + $("#mapId").val(), function(data) {
		if (data && data.status == 'SUCCESS') {
			$('#explore-map').html("");
			mapManager.genMap(mapMeta, {"points": data.points, "lines": data.lines, "polygons": data.polygons});
		}
	});
};

var flyZoomBarAndSwitcher = function() {
	var zoomDiv = $('div[id^=MyPanZoomBar]');
	if (zoomDiv && zoomDiv.length > 0) {
		zoomDiv[0].style.position = "fixed";
		zoomDiv[0].style.top = 20 + "%";
		zoomDiv[0].style.left = 5 + "%";
	}
	var switcherDiv = $('div[id^=OpenLayers\\.Control\\.LayerSwitcher]');
	if (switcherDiv && switcherDiv.length > 0) {
		switcherDiv[0].style.position = "fixed";
		switcherDiv[0].style.top = 20 + "%";
		switcherDiv[0].style.right = 8 + "%";
	}
};
