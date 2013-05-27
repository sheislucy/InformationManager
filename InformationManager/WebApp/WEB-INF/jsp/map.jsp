<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/ui.jqgrid.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="../css/common.css" />
<link href="../css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script>
	$(function() {
		adjustRightWidth();
	});

	$(window).resize(function() {
		var width = $(document).width() - $(".left").width() - 36;
		$(".right").css({
			'width' : width + 'px'
		});
	});
</script>
<title>地图信息</title>
</head>
<body>
	<div class="header"></div>
	<div class="wrapper">
		<div class="left">
			<jsp:include page="/WEB-INF/jsp/inc/treeMenu.inc.jsp">
				<jsp:param value="0" name="accodionIndex" />
			</jsp:include>
		</div>
		<div class="right">
			<jsp:include page="/WEB-INF/jsp/inc/map.inc.jsp" />
		</div>
	</div>
</body>
</html>