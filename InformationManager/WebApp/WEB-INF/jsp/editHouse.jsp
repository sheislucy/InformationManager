<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<div class="grey">
		<div class="detail-information">
			<label for="usetype">使用性质</label><input type="text" name="usetype" id="usertype" value="${house.useType}" />
			<label for="address">坐落地址</label><input type="text" name="address" id="address" value="${house.address}" />
			<label for="buildstruct">建筑结构</label><input type="text" name="buildstruct" id="buildstruct" value="${house.buildStruct}" />
			<label for="floorcount">层数</label><input type="text" name="floorcount" id="floorcount" value="${house.floorCount}" />
			<br />
			<label for="landclass">地类</label><input type="text" name="landclass" id="landclass" value="${house.landClass}" />
			<label for="landarea">占地面积</label><input type="text" name="landarea" id="landarea" value="${house.landArea}" />
			<label for="buildingarea">建筑面积</label><input type="text" name="buildingarea" id="buildingarea" value="${house.buildingArea}" />
			<label for="buildingage">建筑年代</label><input type="text" name="buildingage" id="buildingage" value="${house.buildingAge}" />
			<br />
			<label for="property">产权</label><input type="text" name="property" id="property" value="${house.property}" />
			<label for="propertyno">房产证号</label><input type="text" name="propertyno" id="propertyno" value="${house.propertyNo}" />
			<label for="landno">土地证号</label><input type="text" name="landno" id="landno" value="${house.landNo}" />
			<label for="approvalsno">批文号</label><input type="text" name="approvalsno" id="approvalsno" value="${house.approvalsNo}" />
			<br />
			<label for="parcelno">宗地号</label><input type="text" name="parcelno" id="parcelno" value="${house.parcelNo}" />
			<label for="faceto">朝向</label><input type="text" name="faceto" id="faceto" value="${house.faceTo}" />
			<label for="haswall">有无围墙</label>
				<select name="haswall" id="haswall" >
					<option <c:if test="${house.hasWall}">selected="selected"</c:if> value="true" >有</option>
					<option <c:if test="${!house.hasWall}">selected="selected"</c:if> value="false">无</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
			<label for="isdangerous">是否危房</label>
				<select name="isdangerous" id="isdangerous" >
					<option <c:if test="${house.isDangerous}">selected="selected"</c:if> value="true" >是</option>
					<option <c:if test="${!house.isDangerous}">selected="selected"</c:if> value="false">否</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
			<label for="islegal">是否合法</label>
				<select name="islegal" id="islegal" >
					<option <c:if test="${house.isLegal}">selected="selected"</c:if> value="true" >是</option>
					<option <c:if test="${!house.isLegal}">selected="selected"</c:if> value="false">否</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="save-detail" value="保存本栏" />
			<div class="clear"></div>
		</div>
	</div>
	<div class="grey">
		<div class="member-information">
			<div class="left-column">
				<input type="text" name="memberName" id="memberName" placeholder="查找无户籍关联的人员" />
				<input type="button" id="search-button" value="查询" />
				<input type="button" name="add-button" id="addToHouse" value="添加" />
				<br />
				<div class="result-table">
					<table id="jqGrid-member"></table>
					<div id="pager-member"></div>
				</div>
			</div>
			<div class="right-column">
				<div class="save-member"><input type="button" value="保存本栏" /></div>
				<ul id="member-list">
					<c:forEach var="member" items="${house.members}">
						<li>
							<input type="hidden" name="pid" value="${member.pid}" />
							<span id="member-name" class="fl">[${member.name}]</span>
							<span id="text-info" class="fl"> <fmt:formatDate value="${member.birthday}" type="date" pattern="yyyy-MM-dd" />&nbsp;&nbsp;&nbsp;&nbsp;与户主关系</span>
							<select id="relation-select" class="fl">
								<option value="1" <c:if test="${member.relation eq 1}">selected="selected"</c:if> >本人</option> <option value="2" <c:if test="${member.relation eq 2}">selected="selected"</c:if> >配偶</option> <option value="3" <c:if test="${member.relation eq 3}">selected="selected"</c:if> >子女</option> 
								<option value="4" <c:if test="${member.relation eq 4}">selected="selected"</c:if> >父母</option> <option value="5" <c:if test="${member.relation eq 5}">selected="selected"</c:if> >儿媳</option> <option value="6" <c:if test="${member.relation eq 6}">selected="selected"</c:if> >孙子/孙女</option> 
								<option value="7" <c:if test="${member.relation eq 7}">selected="selected"</c:if> >女婿</option> <option value="8" <c:if test="${member.relation eq 8}">selected="selected"</c:if> >兄弟姐妹</option> <option value="9" <c:if test="${member.relation eq 9}">selected="selected"</c:if> >妹夫/姐夫</option> 
								<option value="10" <c:if test="${member.relation eq 10}">selected="selected"</c:if> >侄子/侄女</option> <option value="11" <c:if test="${member.relation eq 11}">selected="selected"</c:if> >侄媳/侄婿</option> <option value="12" <c:if test="${member.relation eq 12}">selected="selected"</c:if> >叔/伯</option>
								<option value="13" <c:if test="${member.relation eq 13}">selected="selected"</c:if> >外孙/外孙女</option> <option value="14" <c:if test="${member.relation eq 14}">selected="selected"</c:if> >外甥/外甥女</option> <option value="15" <c:if test="${member.relation eq 15}">selected="selected"</c:if> >内兄</option> 
								<option value="16" <c:if test="${member.relation eq 16}">selected="selected"</c:if> >姨夫/姨</option> <option value="17" <c:if test="${member.relation eq 17}">selected="selected"</c:if> >舅舅/舅妈</option> <option value="18" <c:if test="${member.relation eq 18}">selected="selected"</c:if> >弟媳</option> 
								<option value="19" <c:if test="${member.relation eq 19}">selected="selected"</c:if> >堂兄弟/堂姐妹</option> <option value="20" <c:if test="${member.relation eq 20}">selected="selected"</c:if> >表兄弟/表姐妹</option> 
							</select>
							<a id="delete-a" class="ui-state-error fl ui-icon ui-icon-closethick"></a><div class="clear"></div>					
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="grey">
		<div class="piture-information">
			<form>
				<div class="upload-button">
					<span id="spanButtonPlaceholder"></span>
				</div>
			</form>
		
			<div id="divFileProgressContainer"></div>
			<div id="thumbnails">
				<c:forEach var="picture" items="${house.pictures}">
					<div class="thumbnail-single">
						<img src="../thumbnail?file=${picture.localFileName}" ><br />
						<textarea rows="3" cols="40">${picture.description}</textarea>
						<input type="hidden" name="pictureId" id="pictureId" value="${picture.id}" />
						<input type="button" name="save-desc" id="save-desc" value="保存注释" />
						<input type="button" name="delete-picture" id="delete-picture" value="删除这张图片" />
					</div>
				</c:forEach>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</div>
<input id="house-id" value="${houseId}" type="hidden"/>
<div class="hide thumbnail-single" id="thumbnail-example">
	<img ><br />
	<textarea rows="3" cols="40"></textarea>
	<input type="hidden" name="pictureId" id="pictureId" value="" />
	<input type="button" name="save-button" id="save-picture" value="保存注释" />
	<input type="button" name="delete-picture" id="delete-picture" value="删除这张图片" />
</div>

<div class="hide member-single">
	<input type="hidden" name="pid" id="pid" value="" />
	<span id="member-name" class="fl"></span>
	<span id="text-info" class="fl"></span>
	<select id="relation-select" class="fl">
		<option value="1">本人</option> <option value="2">配偶</option> <option value="3">子女</option> 
		<option value="4">父母</option> <option value="5">儿媳</option> <option value="6">孙子/孙女</option> 
		<option value="7">女婿</option> <option value="8">兄弟姐妹</option> <option value="9">妹夫/姐夫</option> 
		<option value="10">侄子/侄女</option> <option value="11">侄媳/侄婿</option> <option value="12">叔/伯</option>
		<option value="13">外孙/外孙女</option> <option value="14">外甥/外甥女</option> <option value="15">内兄</option> 
		<option value="16">姨夫/姨</option> <option value="17">舅舅/舅妈</option> <option value="18">弟媳</option> 
		<option value="19">堂兄弟/堂姐妹</option> <option value="20">表兄弟/表姐妹</option> 
	</select>
	<a id="delete-a" class="ui-state-error fl ui-icon ui-icon-closethick"></a><div class="clear"></div>
</div>

<div class="left-bottom-corner">
	<div id="success-tip" class="ui-tooltip ui-widget-content hide">
		<div class="ui-tooltip-content">保存成功</div>
	</div>
	<div id="failure-tip" class="ui-tooltip ui-widget-content hide">
		<div class="ui-tooltip-content">保存失败</div>
	</div>
</div>

<div id="save-validation-dialog" title="成员关系验证失败" class="hide">
	<p><span class="bold">原因</span></p>
</div>

<div id="duplicate-validation-dialog" title="请不要重复添加成员" class="hide">
</div>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="/js/grid.locale-cn.js"></script>
<script type="text/javascript" src="/js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/swfupload.js"></script>
<script type="text/javascript" src="/js/house-handlers.js"></script>
<script type="text/javascript" src="/js/editHouseDetail.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='button']").button();
	init();
});
</script>

</body>
</html>