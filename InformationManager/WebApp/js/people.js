var People = function() {

	this.getPeopleList = function() {
		var optionalPageList = $("#optionalPageSize").attr("value").split(",");
		$("#jqGrid-people")
				.jqGrid(
						{
							url : '/people/getList.json',
							datatype : "json",
							mtype : "post",
							// postData : postDataParam,
							prmNames : {
								search : null,
								nd : null
							},
							colNames : [ '序号', 'id', '姓名', '绰号', '流动性', '身份证号',
									'教育程度', '地址', '从事职业', '工作地点', '收入来源',
									'政治面貌', '体貌特征', '户主', '和户主的关系', '上次修改时间' ],
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
								editable : true
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
							} ],
							rowNum : $("#initPageSize").attr("value"),
							rowList : optionalPageList,
							pager : '#pager-people',
							sortname : 'pid',
							height : 'auto',
							viewrecords : true,
							sortorder : "desc",
							gridComplete : function() {
								var ids = jQuery("#jqGrid-people").jqGrid(
										'getDataIDs');
								for ( var i = 0; i < ids.length; i++) {
									jQuery("#jqGrid-people")
											.jqGrid(
													'setRowData',
													ids[i],
													{
														no : ($(
																'#jqGrid-people')
																.getGridParam(
																		'page') - 1)
																* $(
																		'#jqGrid-people')
																		.getGridParam(
																				'rowNum')
																+ i + 1
													});
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
		jQuery("#jqGrid-people").jqGrid('navGrid', "#pager-people", {
//			edit : false,
//			add : false,
//			del : false
		});
	};

	this.refreshGrid = function(postDataParam) {
		$("#jqGrid-people").jqGrid('setGridParam', {
			postData : postDataParam
		}).trigger("reloadGrid");
	};
};

var people = new People();
