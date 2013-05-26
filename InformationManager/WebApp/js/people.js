var People = function() {

	this.getPeopleList = function() {
		var optionalPageList = $("#optionalPageSize").attr("value").split(",");
		var table = jQuery("#jqGrid-people");
		table.jqGrid({
			url : '/people/hostList.json',
			datatype : "json",
			mtype : "post",
			// postData : postDataParam,
			prmNames : {
				search : null,
				nd : null
			},
			colNames : [ '序号', 'id', '姓名', '绰号', '流动性', '身份证号', '教育程度', '地址',
					'从事职业', '工作地点', '收入来源', '政治面貌', '体貌特征', '户主', '和户主的关系',
					'上次修改时间', '户主id' ],
			colModel : [ {
				name : 'no',
				index : '',
				width : 30,
				sortable : true
			}, {
				name : 'pid',
				index : 'pid',
				width : 30,
				sortable : false,
				hidden : true
			}, {
				name : 'lname',
				index : 'lname',
				width : 60,
				editable : true
			}, {
				name : 'nName',
				index : 'nName',
				width : 60,
				editable : true,
				hidden : true
			}, {
				name : 'sortText',
				index : 'sortText',
				width : 60,
				align : "right",
				editable : true
			}, {
				name : 'idcard',
				index : 'idcard',
				width : 150,
				align : "right",
				editable : true
			}, {
				name : 'education',
				index : 'education',
				width : 60,
				align : "right",
				editable : true
			}, {
				name : 'address',
				index : 'address',
				width : 150,
				sortable : false,
				editable : true
			}, {
				name : 'job',
				index : 'job',
				width : 100,
				sortable : false,
				editable : true
			}, {
				name : 'workPlace',
				index : 'workPlace',
				width : 150,
				sortable : false,
				editable : true
			}, {
				name : 'incomeSource',
				index : 'incomeSource',
				width : 100,
				sortable : false,
				editable : true
			}, {
				name : 'socialText',
				index : 'socialText',
				width : 60,
				sortable : false,
				editable : true
			}, {
				name : 'physicalCharact',
				index : 'physicalCharact',
				width : 150,
				sortable : false,
				editable : true
			}, {
				name : 'hostName',
				index : 'hostName',
				width : 60,
				sortable : false,
				editable : true
			}, {
				name : 'relation',
				index : 'relation',
				width : 100,
				sortable : false,
				editable : true
			}, {
				name : 'updateDate',
				index : 'updateDate',
				width : 150,
				sortable : false,
				editable : true
			}, {
				name : 'hostId',
				index : 'hostId',
				width : 30,
				sortable : false,
				hidden : true
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
			multiselect : true,
			jsonReader : {
				root : "peopleList",
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
				getHouseMembers(table.jqGrid('getRowData', rowid).pid);
			}
		});
	};

	this.refreshGrid = function(postDataParam) {
		$("#jqGrid-people").jqGrid('setGridParam', {
			postData : postDataParam
		}).trigger("reloadGrid");
	};

	function getHouseMembers (pid) {
		var $overlay = $('<div class="ui-overlay"><div class="ui-widget-overlay"></div></div>').hide().appendTo('body');
        $overlay.fadeIn();
		
		$.ajax({
			url: "/people/houseMembers.json",
			dataType :"html",
			type: "POST",
			contentType : "application/json",
			data: JSON.stringify({"pid": pid}),
//			beforeSend: function ( xhr ) {
//			    xhr.overrideMimeType("text/html; charset=UTF-8");
//			},
			success: function(data, status){
				var $overlayContent = $("<div class='ui-widget ui-widget-content'></div>");
				$overlayContent.append(data);
				$overlay.append($overlayContent);
			},
			error: function(){
				$overlay.remove();
			},
			complete: function(){
				$("#closeButton").click(function() {
//					$(".ui-overlay").fadeOut("fast");
					$overlay.remove();
				});
			}
		});

	};
};

var people = new People();
