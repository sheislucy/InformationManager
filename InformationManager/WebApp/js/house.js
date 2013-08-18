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
				// width : 100,
				editable : true
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
					// if (table.jqGrid('getRowData', ids[i]).hostId == table
					// .jqGrid('getRowData', ids[i]).pid) {
					// $('#' + $.jgrid.jqID(ids[i])).addClass("hostRow");
					// }
				}
			},
//			multiselect : true,
			jsonReader : {
//				root : "peopleList",
				repeatitems : false
			},
			// editurl : "server.php",
			caption : "户籍信息表"
		});
		table.jqGrid('navGrid', "#pager-house", {edit : false, add : false, del : false})
			 .jqGrid('navButtonAdd', "#pager-house", {
				 	caption:"新增", 
				 	buttonicon:"ui-icon-add", 
				 	onClickButton: function(){ 
				 		window.open(""); //TODO
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
			 });
	};

};

var house = new House();