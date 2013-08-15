<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link type="text/css" rel="stylesheet" href="/css/jquery-ui-1.10.3.custom.min.css" />
<link type="text/css" rel="stylesheet" href="/css/ui.jqgrid.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<link type="text/css" rel="stylesheet" href="/css/houseDetail.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改户籍信息</title>
</head>
<body>

<div class="form-wrapper">
	<div class="detail-information">
		<label for="usertype">使用性质</label><input type="text" name="usertype" id="usertype" />
		<label for="address">坐落地址</label><input type="text" name="address" id="address" />
		<label for="buildstruct">建筑结构</label><input type="text" name="buildstruct" id="buildstruct" />
		<label for="floorcount">层数</label><input type="text" name="floorcount" id="floorcount" />
		<br />
		<label for="landclass">地类</label><input type="text" name="landclass" id="landclass" />
		<label for="landarea">占地面积</label><input type="text" name="landarea" id="landarea" />
		<label for="buildingarea">建筑面积</label><input type="text" name="buildingarea" id="buildingarea" />
		<label for="buildingage">建筑年代</label><input type="text" name="buildingage" id="buildingage" />
		<br />
		<label for="property">产权</label><input type="text" name="property" id="property" />
		<label for="propertyno">房产证号</label><input type="text" name="propertyno" id="propertyno" />
		<label for="landno">土地证号</label><input type="text" name="landno" id="landno" />
		<label for="approvalsno">批文号</label><input type="text" name="approvalsno" id="approvalsno" />
		<br />
		<label for="parcelno">宗地号</label><input type="text" name="parcelno" id="parcelno" />
		<label for="faceto">朝向</label><input type="text" name="faceto" id="faceto" />
		<label for="haswall">有无围墙</label><select name="haswall" id="haswall" ><option>有</option><option>无</option></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label for="isdangerous">是否危房</label><select name="isdangerous" id="isdangerous" ><option>是</option><option>否</option></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label for="islegal">是否合法</label><select name="islegal" id="islegal" ><option>是</option><option>否</option></select>
	</div>
	<div class="member-information">
		<div class="left-column">
			<input type="text" name="memberName" id="property" placeholder="请输入人员姓名" />
			<input type="button" id="search-button" value="查询" />
			<input type="button" name="add-button" id="property" value="添加" />
			<br />
			<div class="result-table">
				<table id="jqGrid-member"></table>
				<div id="pager-member"></div>
			</div>
		</div>
		<div class="right-column">
			<ul>
				<li><span class="fl">张三 330103198801010516</span><select class="fl"><option>兄弟/姐妹</option></select><a class="ui-state-error fl ui-icon ui-icon-closethick"></a><div class="clear"></div></li>
				<li><span class="fl">张三 330103198801010516</span><select class="fl"><option>本人</option></select><a class="ui-state-error fl ui-icon ui-icon-closethick"></a><div class="clear"></div></li>
				<li><span class="fl">张三 330103198801010516</span><select class="fl"><option>配偶</option></select><a class="ui-state-error fl ui-icon ui-icon-closethick"></a><div class="clear"></div></li>
				<li><span class="fl">张三 330103198801010516</span><select class="fl"><option>父母</option></select><a class="ui-state-error fl ui-icon ui-icon-closethick"></a><div class="clear"></div></li>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	<div class="piture-information">
		<form>
			<div class="upload-button">
				<span id="spanButtonPlaceholder"></span>
			</div>
		</form>
	
		<div id="divFileProgressContainer"></div>
		<div id="thumbnails"></div>
	</div>
</div>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="/js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/swfupload.js"></script>
<script type="text/javascript" src="/js/handlers.js"></script>
<script type="text/javascript" src="/js/editHouseDetail.js"></script>
<script type="text/javascript">
$(function(){
	new MemberGrid();
	$("input[type='button']").button();
	new FileUpload();
});
</script>

</body>
</html>