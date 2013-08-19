$.extend($.jgrid.defaults, {
	datatype : 'json',
	ajaxGridOptions : {
		contentType: "application/json; charset=UTF-8",
		cache: false
	},
	ajaxOptions : {
		contentType: "application/json; charset=UTF-8",
		cache: false
	},
	ajaxSelectOptions: {
		contentType: "application/json; charset=UTF-8",
		cache: false
	},
	serializeGridData : function(data) {
		delete data.oper;
		return JSON.stringify(data);
	}
});
$.extend($.jgrid.edit, {
	ajaxEditOptions : {
		contentType: "application/json; charset=UTF-8",
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
		contentType: "application/json; charset=UTF-8",
		cache: false
	},
	mtype : "DELETE",
	serializeDelData : function(data) {
		delete data.oper;
		return JSON.stringify(data);
	}
});

var MemberGrid = function(){
	this.initMemberGrid = function(){
		
		$("#jqGrid-member").jqGrid({
//			url: "",
			shrinkToFit : true,
			autowidth : true,
			datatype: "json",
			ajaxOptions: {
				cache: false
			},
			colNames : [ '姓名', '生日', '性别', 'pid', "身份证号" ],
			colModel : [ {
				name : 'name',
				index : 'name',
				width : 80,
				sortable : false
			}, {
				name : 'birthday',
				index : 'birthday',
				//width : 120,
				sortable : false
			}, {
				name : 'gender',
				index : 'gender',
				width : 40,
				sortable : false
			},{
				name : 'pid',
				index : 'pid',
				sortable : false,
				hidden : true
			},{
				name : 'cardId',
				index : 'cardId',
				sortable : false,
				hidden : true
			}],
			jsonReader : {
				repeatitems : false
			},
			sortname : 'pid',
			sortorder : "desc",
			pager : '#pager-member',
			rowNum : 10
		});
	};
	
	this.refreshGrid = function(postDataParam) {
		$("#jqGrid-member").jqGrid('setGridParam', {
			url: "/people/searchPeopleForHouse",
			mtype : "post",
			postData : postDataParam,
			ajaxGridOptions: {
				cache: false
			},
			page: 1
		}).trigger("reloadGrid");
	};
};

var FileUpload = function(){
	this.swfu = new SWFUpload({
		// Backend Settings
		upload_url: "/house/uploadPicture",
		post_params: {
			"houseId": $("#house-id").val()
		},

		// File Upload Settings
		file_size_limit : "10 MB",
		file_types : "*.jpg;*.png;*.bmp;*.jpeg;*.gif",
		file_types_description : "JPG/JPEG Images; PNG Images; BMP Images; GIF Images",
		file_upload_limit : 0,

		// Event Handler Settings - these functions as defined in Handlers.js
		//  The handlers are not part of SWFUpload but are part of my website and control how
		//  my website reacts to the SWFUpload events.
		file_queue_error_handler : fileQueueError,
		file_dialog_complete_handler : fileDialogComplete,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		upload_success_handler : uploadSuccess,
		upload_complete_handler : uploadComplete,

		// Button Settings
		button_image_url : "/images/material/upload-button4.png",
		button_placeholder_id : "spanButtonPlaceholder",
		button_width: 160,
		button_height: 40,
		button_text : '<span class="button">选择图片 <span class="buttonSmall">(最大 10MB)</span></span>',
		button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 13px; } .buttonSmall { font-size: 12px; }',
		button_text_top_padding: 10,
		button_text_left_padding: 23,
		button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
		button_cursor: SWFUpload.CURSOR.HAND,
		
		// Flash Settings
		flash_url : "/js/swfupload.swf",

		custom_settings : {
			upload_target : "divFileProgressContainer"
		},
		
		// Debug Settings
		debug: false
	});
};

var member = new MemberGrid();
new FileUpload();

function validateRelation(data){
	var hostCount = 0;
	var spouseCount = 0;
	var parentCount = 0;
//	var data = [];
	$("#member-list > li").each(function(){
		var relation = $(this).find("select[id='relation-select'] > option:selected").val();
		if( relation == 1 ){
			hostCount++;
		} else if( relation ==  2 ){
			spouseCount++;
		} else if( relation ==  4 ){
			parentCount++;
		}
		
		data.push({
			"pid": $(this).find("input[name='pid']").val(),
			"relation": relation,
			"houseId": $("#house-id").val()
		});
	});
	var hasError = false;
	if( hostCount == 0 ){
		$("#save-validation-dialog").append("<p class='reason-item'>户主缺失</p>");
		hasError = true;
	} else if( hostCount > 1 ){
		$("#save-validation-dialog").append("<p class='reason-item'>户主多于一个</p>");
		hasError = true;
	}
	if( spouseCount > 1 ){
		$("#save-validation-dialog").append("<p class='reason-item'>配偶多于一个</p>");
		hasError = true;
	}
	if( parentCount > 4 ){
		$("#save-validation-dialog").append("<p class='reason-item'>父母总数多于四个</p>");
		hasError = true;
	}
	return !hasError;
}

function init(){
	member.initMemberGrid();
	$(".save-detail").click(function(){
		var postData = {
			"id": $("#house-id").val(),
			"useType": $("#usertype").val(),
			"address": $("#address").val(),
			"buildStruct": $("#buildstruct").val(),
			"floorCount": $("#floorcount").val(),
			"landClass": $("#landclass").val(),
			"landArea": $("#landarea").val(),
			"buildingArea": $("#buildingarea").val(),
			"buildingAge": $("#buildingage").val(),
			"property": $("#property").val(),
			"propertyNo": $("#propertyno").val(),
			"landNo": $("#landno").val(),
			"approvalsNo": $("#approvalsno").val(),
			"parcelNo": $("#parcelno").val(),
			"faceTo": $("#faceto").val(),
			"hasWall":  $("select[name='haswall'] > option:selected").val(),
			"isDangerous":  $("select[name='isdangerous'] > option:selected").val(),
			"isLegal":  $("select[name='islegal'] > option:selected").val()
		};
		jQuery.ajax({
			url: "/house/saveTextDetail",
			type: "POST",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify(postData),
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					$("#success-tip").fadeIn("slow").delay(2000);
					$("#success-tip").hide('explode');
				} else {
					$("#failure-tip").fadeIn("slow").delay(2000);
					$("#failure-tip").hide('explode');
				}
			},
			error: function(){
				$("#failure-tip").fadeIn("slow").delay(2000);
				$("#failure-tip").hide('explode');
			}
		});
	});
	$(".save-member").click(function(){
		var data = [];
		if(validateRelation(data)){
			jQuery.ajax({
				url: "/people/saveMemberDetail",
				type: "POST",
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify(data),
				dataType: "json",
				success: function(response){
					if(response && response.status == "SUCCESS"){
						$("#success-tip").fadeIn("slow").delay(2000);
						$("#success-tip").hide('explode');
					} else{
						$("#failure-tip").fadeIn("slow").delay(2000);
						$("#failure-tip").hide('explode');
						for( var i in response.errorList ){
							$("#save-validation-dialog").append("<p class='reason-item'>" +  response.errorList[i] + "</p>");
						}
						$("#save-validation-dialog").dialog("open");
					}
				},
				error: function(){
					$("#failure-tip").fadeIn("slow").delay(2000);
					$("#failure-tip").hide('explode');
				}
			});
		} else {
			$("#save-validation-dialog").dialog("open");
		}
	});
	$("#search-button").click(function(){
		var postDataParam = {
				"name": $("#memberName").val()
		};
		member.refreshGrid(postDataParam);
	});
	$("#addToHouse").click(function(){
		var table = $("#jqGrid-member");
		var selectedRowId = table.jqGrid('getGridParam', 'selrow');
 		if(selectedRowId != null ){
 			var duplicate = false;
 			$("#member-list > li").each(function(){
 				if(table.jqGrid ('getCell', selectedRowId, 'pid') == $(this).find("input[name='pid']").val()){
 					$("#duplicate-validation-dialog").dialog("open");//.delay(3000).dialog("close");
 					duplicate = true;
 				}
 			});
 			if(!duplicate){
 				var newMember = $("<li></li>");
 				var memberCloned = $(".member-single").clone(true, true);
 				var deleteAHref =  $(".member-single").find("a").clone(true, true);
 				deleteAHref.click(function(){
 					$(this).parent("li").remove();
 				});
 				memberCloned.find("#pid").val(table.jqGrid ('getCell', selectedRowId, 'pid'));
 				memberCloned.find("#member-name").html("[" + table.jqGrid ('getCell', selectedRowId, 'name') 
 						+ "] ");
 				memberCloned.find("#text-info").html(table.jqGrid ('getCell', selectedRowId, 'birthday') + "&nbsp;&nbsp;&nbsp;&nbsp;与户主关系");
 				newMember.append(memberCloned.find("#pid"))
 				.append(memberCloned.find("#member-name"))
 				.append(memberCloned.find("#text-info"))
 				.append(memberCloned.find("#relation-select"))
 				.append(deleteAHref)
 				.append(memberCloned.find("div"));
 				$("#member-list").append(newMember);
// 			table.jqGrid('delRowData', selectedRowId);
 			}
 		}
	});
	$("#member-list > li > #delete-a").click(function(){
		$(this).parent("li").remove();
	});
	$("#thumbnails > .thumbnail-single > #delete-picture").click(function(){
		var data = {
			"pictureId": $(this).siblings("#pictureId").val()
		};
		$(this).parent("div").remove();
		jQuery.ajax({
			url:"/house/deletePicture",
			type: "POST",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify(data),
			dataType: "json",
			success: function(response){
				if(response && response.status == "SUCCESS"){
					$("#success-tip").fadeIn("slow").delay(2000);
					$("#success-tip").hide('explode');
				} else{
					$("#failure-tip").fadeIn("slow").delay(2000);
					$("#failure-tip").hide('explode');
				}
			},
			error: function(){
				$("#failure-tip").fadeIn("slow").delay(2000);
				$("#failure-tip").hide('explode');
			}
		});
	});
	$( "#save-validation-dialog" ).dialog({
		autoOpen: false,
		height: 150,
		width: 200,
		modal: true,
		buttons: {
			"确定": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$( this ).dialog( "close" );
			$(".reason-item").remove();
		}
	});
	$("#duplicate-validation-dialog").dialog({
		autoOpen: false,
		height: 80,
		width: 200,
		modal: true,
		buttons: {
			"确定": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$( this ).dialog( "close" );
			$(".reason-item").remove();
		}
	});
}