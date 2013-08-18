$.extend($.jgrid.defaults, {
	datatype : 'json',
	ajaxGridOptions : {
		contentType : "application/json"
	},
	ajaxRowOptions : {
		contentType : "application/json",

	},
	serializeGridData : function(data) {
		delete data.oper;
		return JSON.stringify(data);
	}
});
$.extend($.jgrid.edit, {
	ajaxEditOptions : {
		contentType : "application/json",
		type : "PUT"
	},
	serializeEditData : function(data) {
		delete data.oper;
		return JSON.stringify(data);
	}
});

$.extend($.jgrid.del, {
	ajaxDelOptions : {
		contentType : "application/json"
	},
	mtype : "DELETE",
	serializeDelData : function(data) {
		delete data.oper;
		return JSON.stringify(data);
	}
});

var House = function() {

	this.getHouseList = function() {
		var optionalPageList = $("#optionalPageSize").attr("value").split(",");
		var table = jQuery("#jqGrid-house");
		table.jqGrid({
			url : '/house/house.json',
			datatype : "json",
			mtype : "post",
			// postData : postDataParam,
			prmNames : {
				search : null,
				nd : null
			},
			shrinkToFit : true,
			autowidth : true,
			colNames : [ '序号', 'id', '户主姓名', '坐落地址','使用性质', '建筑结构', '层数', '是否合法', '地类', '占地面积', '建筑面积',
					'建筑年代', '产权', '房产证号', '土地证号', '批文号', '宗地号', '上次修改时间' ],
			colModel : [ {
				name : 'no',
				index : '',
				width : 50,
				sortable : true
			}, {
				name : 'id',
				index : 'id',
				//width : 60,
				sortable : false,
				hidden : true
			}, {
				name : 'name',
				index : 'pid',
				width : 100,
				editable : true
			}, {
				name : 'address',
				index : 'address',
				width : 300,
				editable : true,
				sortable : false,
			},{
				name : 'useType',
				index : 'usetype',
				width : 60,
				align : "right",
				editable : true
			}, {
				name : 'buildStruct',
				index : 'buildstruct',
				width : 60,
				align : "right",
				editable : true
			},{
				name : 'floorCount',
				index : 'floorcount',
				width : 40,
				align : "right",
				editable : true
			},{
				name : 'isLegal',
				index : 'islegal',
				width : 40,
				align : "right",
				editable : true,
				formatter: function( cellvalue, options, rowObject ){
					if(cellvalue == "true"){
						return "合法";
					}else{
						return "非法";
					}
				}
			},{
				name : 'landClass',
				index : 'landclass',
				width : 60,
				align : "right",
				editable : true
			}, {
				name : 'landArea',
				index : 'landarea',
				width : 60,
				align : "right",
				editable : true
			}, {
				name : 'buildingArea',
				index : 'buildingarea',
				width : 60,
				editable : true
			}, {
				name : 'buildingAge',
				index : 'buildingage',
				width : 60,
				editable : true
			}, {
				name : 'property',
				index : 'property',
				width : 60,
				sortable : false,
				editable : true,
			}, {
				name : 'propertyNo',
				index : 'propertyno',
				width : 60,
				sortable : false,
				editable : true
			}, {
				name : 'landNo',
				index : 'landno',
				width : 60,
				sortable : false,
				editable : true
			}, {
				name : 'approvalsNo',
				index : 'approvalsno',
				width : 60,
				sortable : false,
				editable : true
			}, {
				name : 'parcelNo',
				index : 'parcelno',
				width : 60,
				sortable : false,
				editable : true
			}, {
				name : 'lastUpdateTime',
				index : 'lastupdatetime',
				editable : true,
				hidden : true
			} ],
			rowNum : $("#initPageSize").attr("value"),
			rowList : optionalPageList,
			pager : '#pager-house',
			sortname : 'id',
			height : 'auto',
			viewrecords : true,
			sortorder : "desc",
			gridComplete : function() {
				var ids = table.jqGrid('getDataIDs');
				for ( var i = 0; i < ids.length; i++) {
					table.jqGrid('setRowData', ids[i], {
						no : (table.getGridParam('page') - 1)
								* table.getGridParam('rowNum') + i + 1
					});
				}
			},
			jsonReader : {
				repeatitems : false
			},
			caption : "户籍信息表"
		});
		table.jqGrid('navGrid', "#pager-house", {edit : false, add : false, del : false, search:false})
			 .jqGrid('navButtonAdd', "#pager-house", {
				 	caption:"新增", 
				 	buttonicon:"ui-icon-add", 
				 	onClickButton: function(){ 
				 		$("#new-house-dialog").dialog("open");
				 		
				 		
				 	}, 
				 	position:"last"
			 })
			 .jqGrid('navButtonAdd', "#pager-house", {
				 	caption:"修改", 
				 	buttonicon:"ui-icon-edit", 
				 	onClickButton: function(){ 
				 		var selectedRowId = table.jqGrid('getGridParam', 'selrow');
				 		if(selectedRowId != null ){
				 			window.open("/house/editHouse/" + table.jqGrid ('getCell', selectedRowId, 'id'));
				 		}
				 	}, 
				 	position:"last"
			 })
			 .jqGrid('navButtonAdd', "#pager-house", {
				 	caption:"删除", 
				 	buttonicon:"ui-icon-del", 
				 	onClickButton: function(){ 
				 		var selectedRowId = table.jqGrid('getGridParam', 'selrow');
				 		if(selectedRowId != null ){
				 			$( "#delete-validation-dialog" ).dialog("open");
				 		}
				 	}, 
				 	position:"last"
			 });
	};
	
	this.refreshGrid = function() {
		$("#jqGrid-house").jqGrid('setGridParam', {
			ajaxGridOptions: {
				cache: false
			},
			page: 1
		}).trigger("reloadGrid");
	};
	
};

function init(){
	var house = new House();
	house.getHouseList();
	window.setTimeout(adjustCenterSize(),200);
	$( "#delete-validation-dialog" ).dialog({
		autoOpen: false,
		height: 120,
		width: 200,
		modal: true,
		buttons: {
			"确定": function(){
				$( this ).dialog( "close" );
				var table = $("#jqGrid-house");
				var data = {
					"id": table.jqGrid ('getCell', table.jqGrid('getGridParam', 'selrow'), 'id')
				};
				jQuery.ajax({
					url: "/house/delete",
					type: "POST",
					contentType: "application/json; charset=UTF-8",
					data: JSON.stringify(data),
					dataType: "json",
					success: function(response){
						if(response.status == "SUCCESS"){
							$("#success-tip").fadeIn("slow").delay(2000);
							$("#success-tip").hide('explode');
							table.trigger("reloadGrid");
						}else{
							$("#failure-tip").fadeIn("slow").delay(2000);
							$("#failure-tip").hide('explode');
						}
					},
					error: function(){
						$("#failure-tip").fadeIn("slow").delay(2000);
						$("#failure-tip").hide('explode');
					}
				});
			},
			"取消": function(){
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$( this ).dialog( "close" );
		}
	});
	$("#new-house-dialog").dialog({
		autoOpen: false,
		height: 180,
		width: 250,
		modal: true,
		buttons: {
			"确定": function(){
				var data = {
					"pid": $("#host-input").val()
				};
				jQuery.ajax({
					url:"/house/create",
					type: "POST",
					contentType: "application/json; charset=UTF-8",
					data: JSON.stringify(data),
					dataType: "json",
					success: function(response){
						$("#new-house-dialog").dialog( "close" );
						if(response.status == "SUCCESS"){
							$("#jqGrid-house").trigger("reloadGrid");
							$("#success-tip").fadeIn("slow").delay(2000);
							$("#success-tip").hide('explode');
							$("#edit-house-link").attr("href", "/house/editHouse/" + response.id);
							$("#edit-house-link").click();
							window.open("/house/editHouse/" + response.id);
						}else{
							$("#failure-tip").fadeIn("slow").delay(2000);
							$("#failure-tip").hide('explode');
						}
					},
					error: function(){
						$("#new-house-dialog").dialog( "close" );
						$("#failure-tip").fadeIn("slow").delay(2000);
						$("#failure-tip").hide('explode');
					}
				});
			},
			"取消": function(){
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$( this ).dialog( "close" );
			$("#host-input").val( "" );
			$(".token-input-list-facebook").remove();
		},
		open: function(){
			jQuery.getJSON("/people/miniPeopleList/nohouse", function(data){
				$("#host-input").tokenInput(data, {
					theme: "facebook",
	                preventDuplicates: true,
	                zindex: 10099,
	                tokenLimit: 1,
	                hintText: "输入人员姓名来查找",
	                noResultsText: "找不到姓名中包含该字的无户人员",
	                searchingText: "查找中..."
	            });
			});
		}
	});
	
}
