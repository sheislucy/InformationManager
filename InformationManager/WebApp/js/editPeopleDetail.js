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
		debug: false
	});
};

new FileUpload();

function init(){
	$( "#birthday" ).datepicker({ 
		altFormat: "yy-mm-dd",
		altField: "#birthday"
	});
	
	$(".save-detail").click(function(){
		var postData = {
				"pid": $("#pid").val(),
				"cardId": $("#cardid").val(),
				"sname": $("#sname").val(),
				"birthday": $("#birthday").val(),
				"addr": $("#addr").val(),
				"job": $("#job").val(),
				"tel": $("#tel").val(),
				"phone": $("#phone").val(),
				"wplace": $("#wplace").val(),
				"spec": $("#spec").val(),
				"incomeSource": $("#incomesource").val(),
				"ethnic": $("#ethnic").val(),
				"army": $("#army").val(),
				"health": $("#health").val(),
				"yearIncome": $("#yearincome").val(),
				"diffCond": $("#diffcond").val(),
				"companyName": $("#companyname").val(),
				"currentAddress": $("#currentaddress").val()
			};
			jQuery.ajax({
				url: "/people/saveTextDetail",
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
	
	$(".save-dropdown").click(function(){
		var postData = {
				"pid": $("#pid").val(),
				"genderId":  $("select[name='genderId'] > option:selected").val()==""?null:$("select[name='genderId'] > option:selected").val(),
				"educationId":  $("select[name='educationId'] > option:selected").val()==""?null:$("select[name='educationId'] > option:selected").val(),
				"politicalId":  $("select[name='politicalId'] > option:selected").val()==""?null:$("select[name='politicalId'] > option:selected").val(),
				"marriageid":  $("select[name='marriageid'] > option:selected").val()==""?null:$("select[name='marriageid'] > option:selected").val(),
				"positionId":  $("select[name='positionId'] > option:selected").val()==""?null:$("select[name='positionId'] > option:selected").val(),
				"residentId":  $("select[name='residentId'] > option:selected").val()==""?null:$("select[name='residentId'] > option:selected").val(),
				"isLowSafe":  $("select[name='isLowSafe'] > option:selected").val()==""?null:$("select[name='isLowSafe'] > option:selected").val(),
				"isaddsafe":  $("select[name='isaddsafe'] > option:selected").val()==""?null:$("select[name='isaddsafe'] > option:selected").val(),
				"isCorps":  $("select[name='isCorps'] > option:selected").val()==""?null:$("select[name='isCorps'] > option:selected").val(),
				"isOut":  $("select[name='isOut'] > option:selected").val()==""?null:$("select[name='isOut'] > option:selected").val(),
				"isOverSea":  $("select[name='isOverSea'] > option:selected").val()==""?null:$("select[name='isOverSea'] > option:selected").val()
			};
			jQuery.ajax({
				url: "/people/saveSelectDetail",
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
	
	$("#delete-picture").click(function(){
		var data = {
			"pid": $("#pid").val()
		};
		$("#thumbnails").hide();
		jQuery.ajax({
			url:"/people/deletePicture",
			type: "POST",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify(data),
			dataType: "json",
			success: function(response){
				if(response && response.status == "SUCCESS"){
					$("#success-tip").fadeIn("slow").delay(2000);
					$("#success-tip").hide('explode');
					$(".upload-pic-button").show();
					$("#divFileProgressContainer").html("");
					$("#divFileProgressContainer").show();
					$("#thumbnails").hide();
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
}