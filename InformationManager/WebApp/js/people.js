$.extend($.jgrid.defaults, {
	datatype : 'json',
	ajaxGridOptions : {
		contentType : "application/json",
		cache: false
	},
	ajaxOptions : {
		contentType : "application/json",
		cache: false
	},
	ajaxSelectOptions: {
		contentType : "application/json",
		cache: false
	},
	serializeGridData : function(data) {
		delete data.oper;
		return JSON.stringify(data);
	}
});
$.extend($.jgrid.edit, {
	ajaxEditOptions : {
		contentType : "application/json",
		type : "PUT",
		cache: false
	},
	serializeEditData : function(data) {
		delete data.oper;
		return JSON.stringify(data);
	}
});

$.extend($.jgrid.del, {
	ajaxDelOptions : {
		contentType : "application/json",
		cache: false
	},
	mtype : "DELETE",
	serializeDelData : function(data) {
		delete data.oper;
		return JSON.stringify(data);
	}
});

var People = function() {

	this.getPeopleList = function() {
		var optionalPageList = $("#optionalPageSize").attr("value").split(",");
		var table = jQuery("#jqGrid-people");
		table.jqGrid({
			url : '/people/people.json?',
			datatype : "json",
			mtype : "post",
			ajaxOptions: {
				cache: false
			},
			prmNames : {
				search : null
			},
			shrinkToFit : true,
			autowidth : true,
			colNames : [ '序号', 'id', '姓名', '性别','流动性', '身份证号', '生日', '电话', '手机', '教育程度', '地址',
					'从事职业', '工作地点', '年收入', '收入来源', '政治面貌', '和户主的关系',
					'上次修改时间' ],
			colModel : [ {
				name : 'no',
				index : '',
				width : 50,
				sortable : true
			}, {
				name : 'pid',
				index : 'pid',
				//width : 60,
				sortable : false,
				hidden : true
			}, {
				name : 'name',
				index : 'name',
				width : 100,
				editable : true
			}, {
				name : 'gender',
				index : 'gender',
				width : 60,
				editable : true
			},{
				name : 'ptype',
				index : 'ptype',
				// width : 60,
				align : "right",
				editable : true
			}, {
				name : 'cardId',
				index : 'cardid',
				// width : 140,
				align : "right",
				editable : true
			},{
				name : 'birthday',
				index : 'birthday',
				// width : 140,
				align : "right",
				editable : true
			},{
				name : 'tel',
				index : 'tel',
				// width : 140,
				align : "right",
				editable : true
			},{
				name : 'phone',
				index : 'phone',
				// width : 140,
				align : "right",
				editable : true
			}, {
				name : 'education',
				index : 'education',
				// width : 40,
				align : "right",
				editable : true
			}, {
				name : 'addr',
				index : 'addr',
				// width : 130,
				sortable : false,
				editable : true
			}, {
				name : 'job',
				index : 'job',
				// width : 60,
				sortable : false,
				editable : true
			}, {
				name : 'wplace',
				index : 'wplace',
				// width : 150,
				sortable : false,
				editable : true,
			}, {
				name : 'yearIncome',
				index : 'yearincome',
				// width : 100,
				editable : true
			}, {
				name : 'incomeSource',
				index : 'incomesource',
				// width : 100,
				editable : true
			}, {
				name : 'political',
				index : 'political',
				// width : 50,
				sortable : false,
				editable : true
			}, {
				name : 'relation',
				index : 'relationid',
				// width : 30,
				sortable : false,
				editable : true
			}, {
				name : 'lastUpdateTime',
				index : 'lastupdatetime',
				// width : 100,
				editable : true,
				hidden : true
			} ],
			rowNum : $("#initPageSize").val(),
			rowList : optionalPageList,
			pager : '#pager-people',
			sortname : 'pid',
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
			caption : "人员信息表"
		});
		table.jqGrid('navGrid', "#pager-people", {edit : false, add : false, del : false, search:false})
			.jqGrid('navButtonAdd', "#pager-people", {
				 	caption:"新增", 
				 	buttonicon:"ui-icon-add", 
				 	onClickButton: function(){ 
				 		$("#new-house-dialog").dialog("open");
				 		
				 		
				 	}, 
				 	position:"last"
			 })
			 .jqGrid('navButtonAdd', "#pager-people", {
				 	caption:"修改", 
				 	buttonicon:"ui-icon-edit", 
				 	onClickButton: function(){ 
				 		var selectedRowId = table.jqGrid('getGridParam', 'selrow');
				 		if(selectedRowId != null ){
				 			window.open("/people/editPeople/" + table.jqGrid ('getCell', selectedRowId, 'pid'));
				 		}
				 	}, 
				 	position:"last"
			 })
			 .jqGrid('navButtonAdd', "#pager-people", {
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
		table.jqGrid("setGridParam", {
			onSelectRow : function(rowid, status) {
				openHouseMembers(table.jqGrid('getRowData', rowid).pid);
			}
		});
	};

	this.refreshGrid = function(postDataParam) {
		$("#jqGrid-people").jqGrid('setGridParam', {
			postData : postDataParam,
			ajaxGridOptions: {
				cache: false
			},
			page: 1
		}).trigger("reloadGrid");
	};

	function openHouseMembers(pid) {
//		window.open("/people/houseMembers/" + pid);
	}
};

var people = new People();
