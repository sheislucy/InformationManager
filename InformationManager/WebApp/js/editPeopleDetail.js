var FileUpload = function(){
	this.swfu = new SWFUpload({
		// Backend Settings
		upload_url: "/people/uploadPicture",
		post_params: {
			"pid": $("#pid").val()
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
		debug: true
	});
};

new FileUpload();

function init(){
	$( "#birthday" ).datepicker({ 
		altFormat: "yy-mm-dd",
		altField: "#birthday"
	});
}