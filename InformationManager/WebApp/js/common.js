function adjustCenterSize(){
//	$("#jqGrid-people").setGridWidth($(".ui-layout-center")[0].clientWidth - 15);
	$("#jqGrid-people").setGridWidth($("#search-criteria").width());
	$("#search-result").height($(".ui-layout-center")[0].clientHeight - $("#search-criteria").height() - 60);
	$("#jqGrid-people").setGridHeight($(".ui-layout-center")[0].clientHeight - $("#search-criteria").height() - 113);
}

function adjustAccordionSize(){
	$("#treeMenu").accordion("refresh");
}

function adjustAll(){
	adjustCenterSize();
	adjustAccordionSize();
}

var OverlayManager = function() {

	var overlay = $('<div class="ui-widget-overlay"></div>');
	
	function showLoadingOverlay() {
		var loadingIcon = $('<div class="bubblingG"></div>');
		for ( var i = 1; i < 4; i++) {
			var bubbling = $('<span id="bubblingG_' + i + '"></span>');
			loadingIcon.append(bubbling);
		}
		overlay.append(loadingIcon);
		$(document.body).append(overlay);
	};
	
	function hideLoadingOverlay (){
		$(".ui-widget-overlay").hide();
//		$(document.body).remove(".ui-widget-overlay");
	};
	
	$(document.body).ajaxStart(function() { showLoadingOverlay(); });
    $(document.body).ajaxSuccess(function() { hideLoadingOverlay(); });
    $(document.body).ajaxError(function() { hideLoadingOverlay(); });

};

var om = new OverlayManager();