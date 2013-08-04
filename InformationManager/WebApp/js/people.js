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

var People = function() {

	this.getPeopleList = function() {
		var optionalPageList = $("#optionalPageSize").attr("value").split(",");
		var table = jQuery("#jqGrid-people");
		table.jqGrid({
			url : '/people/people.json',
			datatype : "json",
			mtype : "post",
			// postData : postDataParam,
			prmNames : {
				search : null,
				nd : null
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
				index : 'cardId',
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
				index : 'yearIncome',
				// width : 100,
				sortable : false,
				editable : true
			}, {
				name : 'incomeSource',
				index : 'incomeSource',
				// width : 100,
				sortable : false,
				editable : true
			}, {
				name : 'social',
				index : 'social',
				// width : 50,
				sortable : false,
				editable : true
			}, {
				name : 'relation',
				index : 'relation',
				// width : 30,
				sortable : false,
				editable : true
			}, {
				name : 'lastUpdateTime',
				index : 'lastUpdateTime',
				// width : 100,
				sortable : false,
				editable : true
			} ],
			rowNum : $("#initPageSize").attr("value"),
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
			caption : "人员信息表"
		});
		table.jqGrid('navGrid', "#pager-people", {
		// edit : false,
		// add : false,
		// del : false
		});
		table.jqGrid("setGridParam", {
			onSelectRow : function(rowid, status) {
				openHouseMembers(table.jqGrid('getRowData', rowid).pid);
			}
		});
	};

	this.refreshGrid = function(postDataParam) {
		$("#jqGrid-people").jqGrid('setGridParam', {
			postData : postDataParam
		}).trigger("reloadGrid");
	};

	function openHouseMembers(pid) {
//		window.open("/people/houseMembers/" + pid);
	}
};

var people = new People();
