function adjustCenterSize(){
	$("#jqGrid-people").setGridWidth($(".ui-layout-center")[0].clientWidth - 15);
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