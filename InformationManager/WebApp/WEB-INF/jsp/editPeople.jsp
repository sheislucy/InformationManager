<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link type="text/css" rel="stylesheet" href="/css/jquery-ui-1.10.3.custom.min.css" />
<link type="text/css" rel="stylesheet" href="/css/ui.jqgrid.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<link type="text/css" rel="stylesheet" href="/css/peopleDetail.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改人员信息</title>
</head>
<body>

<div class="form-wrapper">
	<div class="grey">
		<div class="detail-information">
			<label for="name">姓名</label><input type="text" name="name" id="name" value="${people.name}" />
			<label for="cardid">身份证号</label><input type="text" name="cardid" id="cardid" value="${people.cardId}" />
			<label for="sname">绰号</label><input type="text" name="sname" id="sname" value="${people.sname}" />
			<label for="birthday">生日</label><input type="text" name="birthday" id="birthday" value="<fmt:formatDate value="${people.birthday}" type="date" pattern="yyyy-MM-dd" />" />
			<br />
			<label for="address">地址</label><input type="text" name="addr" id="address" value="${people.addr}" />
			<label for="job">职业</label><input type="text" name="job" id="job" value="${people.job}" />
			<label for="tel">联系电话</label><input type="text" name="tel" id="tel" value="${people.tel}" />
			<label for="phone">手机号码</label><input type="text" name="phone" id="phone" value="${people.phone}" />
			<br />
			<label for="wplace">工作地点</label><input type="text" name="wplace" id="wplace" value="${people.wplace}" />
			<label for="spec">专业特长</label><input type="text" name="spec" id="spec" value="${people.spec}" />
			<label for="incomesource">收入来源</label><input type="text" name="incomesource" id="incomesource" value="${people.incomeSource}" />
			<label for="ethnic">民族</label><input type="text" name="ethnic" id="ethnic" value="${people.ethnic}" />
			<br />
			<label for="army">兵役状况</label><input type="text" name="army" id="army" value="${people.army}" />
			<label for="health">身体状况</label><input type="text" name="health" id="health" value="${people.health}" />
			<label for="yearincome">年收入</label><input type="text" name="yearIncome" id="yearincome" value="${people.yearIncome}" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"  />
			<br />
			<label for="diffcond">困难情况</label><input type="text" name="diffCond" id="diffcond" value="${people.diffCond}" />
			<label for="companyname">工作单位</label><input type="text" name="companyName" id="companyname" value="${people.companyName}" />
			<label for="currentaddress">现在住址</label><input type="text" name="currentAddress" id="currentaddress" value="${people.currentAddress}" />
			<input type="button" class="save-detail" value="保存本栏" />
			<div class="clear"></div>
		</div>
	</div>
	
	<div class="grey">
		<div class="dropdown-information">
			<div class="left-column fl">
				<label for="gender">性别</label>
					<select name="genderId" id="gender" >
						<option <c:if test="${people.genderId eq 1}">selected="selected"</c:if> value="1" >男</option>
						<option <c:if test="${people.genderId eq 0}">selected="selected"</c:if> value="0">女</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<label for="education">教育程度</label>
					<select name="educationId" id="education" >
						<option value="">请选择</option>
						<option <c:if test="${people.educationId eq 1}">selected="selected"</c:if> value="1" >未受教育</option>
						<option <c:if test="${people.educationId eq 2}">selected="selected"</c:if> value="2">小学</option>
						<option <c:if test="${people.educationId eq 3}">selected="selected"</c:if> value="3">初中</option>
						<option <c:if test="${people.educationId eq 4}">selected="selected"</c:if> value="4">高中</option>
						<option <c:if test="${people.educationId eq 5}">selected="selected"</c:if> value="5">中专中技职校</option>
						<option <c:if test="${people.educationId eq 6}">selected="selected"</c:if> value="6">大专</option>
						<option <c:if test="${people.educationId eq 7}">selected="selected"</c:if> value="7">本科</option>
						<option <c:if test="${people.educationId eq 8}">selected="selected"</c:if> value="8">硕士</option>
						<option <c:if test="${people.educationId eq 9}">selected="selected"</c:if> value="9">博士</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<label for="political">政治面貌</label>
					<select name="politicalId" id="political" >
						<option value="">请选择</option>
						<option <c:if test="${people.politicalId eq 1}">selected="selected"</c:if> value="1" >群众</option>
						<option <c:if test="${people.politicalId eq 2}">selected="selected"</c:if> value="2">团员</option>
						<option <c:if test="${people.politicalId eq 3}">selected="selected"</c:if> value="3">党员</option>
						<option <c:if test="${people.politicalId eq 4}">selected="selected"</c:if> value="4">民主党派</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<label for="marriageid">婚姻状况</label>
					<select name="marriageId" id="marriageid" >
						<option value="">请选择</option>
						<option <c:if test="${people.marriageId eq 10}">selected="selected"</c:if> value="10" >未婚</option>
						<option <c:if test="${people.marriageId eq 11}">selected="selected"</c:if> value="11">已婚</option>
						<option <c:if test="${people.marriageId eq 12}">selected="selected"</c:if> value="12">离异</option>
						<option <c:if test="${people.marriageId eq 13}">selected="selected"</c:if> value="13">丧偶</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<br />
				<label for="position">职务</label>
					<select name="positionId" id="position" >
						<option value="">请选择</option>
						<option <c:if test="${people.positionId eq 1}">selected="selected"</c:if> value="1" >村党支部书记</option>
						<option <c:if test="${people.positionId eq 2}">selected="selected"</c:if> value="2">村党支部委员</option>
						<option <c:if test="${people.positionId eq 3}">selected="selected"</c:if> value="3">村委主任</option>
						<option <c:if test="${people.positionId eq 4}">selected="selected"</c:if> value="4">村委</option>
						<option <c:if test="${people.positionId eq 5}">selected="selected"</c:if> value="5">村经济合作社社长</option>
						<option <c:if test="${people.positionId eq 6}">selected="selected"</c:if> value="6">村社监会主人</option>
						<option <c:if test="${people.positionId eq 7}">selected="selected"</c:if> value="7">村社监会委员</option>
						<option <c:if test="${people.positionId eq 8}">selected="selected"</c:if> value="8">村务监督委员会主任</option>
						<option <c:if test="${people.positionId eq 9}">selected="selected"</c:if> value="9">村务监督委员会委员</option>
						<option <c:if test="${people.positionId eq 10}">selected="selected"</c:if> value="10">村治调主任</option>
						<option <c:if test="${people.positionId eq 11}">selected="selected"</c:if> value="11">村妇代会主任（计生联络员）</option>
						<option <c:if test="${people.positionId eq 12}">selected="selected"</c:if> value="12">信访员</option>
						<option <c:if test="${people.positionId eq 13}">selected="selected"</c:if> value="13">村团支部书记</option>
						<option <c:if test="${people.positionId eq 14}">selected="selected"</c:if> value="14">村民兵连长</option>
						<option <c:if test="${people.positionId eq 15}">selected="selected"</c:if> value="15">报账员</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<label for="residentid">户口性质</label>
					<select name="residentId" id="residentid" >
						<option value="">请选择</option>
						<option <c:if test="${people.residentId eq 14}">selected="selected"</c:if> value="14" >农业人口</option>
						<option <c:if test="${people.residentId eq 15}">selected="selected"</c:if> value="15">非农业人口</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<label for="islowsafe">是否低保</label>
					<select name="isLowSafe" id="islowsafe" >
						<option value="">请选择</option>
						<option <c:if test="${people.isLowSafe}">selected="selected"</c:if> value="true" >是</option>
						<option <c:if test="${!people.isLowSafe}">selected="selected"</c:if> value="false">否</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<br />
				<label for="isaddsafe">是否参保</label>
					<select name="isaddsafe" id="isaddsafe" >
						<option value="">请选择</option>
						<option <c:if test="${people.isaddsafe}">selected="selected"</c:if> value="true" >是</option>
						<option <c:if test="${!people.isaddsafe}">selected="selected"</c:if> value="false">否</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<label for="iscorps">是否军属</label>
					<select name="isCorps" id="iscorps" >
						<option value="">请选择</option>
						<option <c:if test="${people.isCorps}">selected="selected"</c:if> value="true" >是</option>
						<option <c:if test="${!people.isCorps}">selected="selected"</c:if> value="false">否</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<label for="isout">是否外出</label>
					<select name="isOut" id="isout" >
						<option value="">请选择</option>
						<option <c:if test="${people.isOut}">selected="selected"</c:if> value="true" >是</option>
						<option <c:if test="${!people.isOut}">selected="selected"</c:if> value="false">否</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<label for="isoversea">是否华侨</label>
					<select name="isOverSea" id="isoversea" >
						<option value="">请选择</option>
						<option <c:if test="${people.isOverSea}">selected="selected"</c:if> value="true" >是</option>
						<option <c:if test="${!people.isOverSea}">selected="selected"</c:if> value="false">否</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="save-dropdown" value="保存本栏" />
			</div>
			<div class="right-column fl" id="right-column">
			<c:choose>
				<c:when test="${!empty people.picture}">
					<div class="upload-pic-button" class="hide">
						<span id="spanButtonPlaceholder"></span>
					</div>
					<div id="divFileProgressContainer" class="hide"></div>
					<div id="thumbnails" class="hide">
						<img id="thumbnail-img" src="../thumbnail?file=${people.picture}" ><br />
						<input type="button" name="delete-picture" id="delete-picture" value="删除这张图片" />
					</div>
				</c:when>
				<c:otherwise>
					<div class="upload-pic-button">
						<span id="spanButtonPlaceholder"></span>
					</div>
					<div id="divFileProgressContainer"></div>
					<div id="thumbnails" class="hide">
						<img id="thumbnail-img" src="" ><br />
						<input type="button" name="delete-picture" id="delete-picture" value="删除这张图片" />
					</div>
				</c:otherwise>
			</c:choose>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>

<div class="left-bottom-corner">
	<div id="success-tip" class="ui-tooltip ui-widget-content hide">
		<div class="ui-tooltip-content">保存成功</div>
	</div>
	<div id="failure-tip" class="ui-tooltip ui-widget-content hide">
		<div class="ui-tooltip-content">保存失败</div>
	</div>
</div>
<input type="hidden" id="pid" value="${people.pid}" />

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="/js/swfupload.js"></script>
<script type="text/javascript" src="/js/people-handlers.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/editPeopleDetail.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='button']").button();
	init();
});
</script>

</body>
</html>