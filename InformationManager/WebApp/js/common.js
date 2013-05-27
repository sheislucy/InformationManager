function adjustRightWidth(){
	var width = $(document).width() - $(".left").width() - 36;
	$(".right").css({
		'width' : width + 'px'
	});
}