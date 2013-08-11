var MemberGrid = function(){
	$("#jqGrid-member").jqGrid({
		shrinkToFit : true,
		autowidth : true,
		datatype: "json",
//		data: [{"name": "李武", "cardid":"330103198801010516", "gender": "true", "pid": "001"}],
		colNames : [ '姓名', '身份证号', '性别', 'pid' ],
		colModel : [ {
			name : 'name',
			index : 'name',
			width : 80,
			sortable : false
		}, {
			name : 'cardid',
			index : 'cardid',
			//width : 120,
			sortable : false
		}, {
			name : 'gender',
			index : 'gender',
			width : 40,
			sortable : false,
			formatter: function(cellvalue, options, rowObject){
				return cellvalue == "true" ? "男" : "女";
			}
		},{
			name : 'pid',
			index : 'pid',
			sortable : false,
			hidden : true
		}],
//		viewrecords : true,
		pager : '#pager-member',
		rowNum : 10
	});
};

var FileUpload = function(){
	this.swfu = new SWFUpload({
		// Backend Settings
		upload_url: "",
		post_params: {},

		// File Upload Settings
		file_size_limit : "10 MB",
		file_types : "*.jpg;*.png;*.bmp;*.jpeg;*.gif",
		file_types_description : "JPG/JPEG Images; PNG Images; BMP Images; GIF Images",
		file_upload_limit : 0,

		// Event Handler Settings - these functions as defined in Handlers.js
		//  The handlers are not part of SWFUpload but are part of my website and control how
		//  my website reacts to the SWFUpload events.
		swfupload_preload_handler : preLoad,
		swfupload_load_failed_handler : loadFailed,
		file_queue_error_handler : fileQueueError,
		file_dialog_complete_handler : fileDialogComplete,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		upload_success_handler : uploadSuccess,
		upload_complete_handler : uploadComplete,

		// Button Settings
//		button_image_url : "images/SmallSpyGlassWithTransperancy_17x18.png",
		button_placeholder_id : "spanButtonPlaceholder",
		button_width: 180,
		button_height: 18,
		button_text : '<span class="button">选择图片 <span class="buttonSmall">(最大2 MB)</span></span>',
		button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 13px; } .buttonSmall { font-size: 12px; }',
		button_text_top_padding: 0,
		button_text_left_padding: 18,
		button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
		button_cursor: SWFUpload.CURSOR.HAND,
		
		// Flash Settings
		flash_url : "G:/workspace_info/lucy/git/InformationManager/InformationManager/WebApp/js/swfupload.swf",

		custom_settings : {
			upload_target : "divFileProgressContainer"
		},
		
		// Debug Settings
		debug: true
	});
};