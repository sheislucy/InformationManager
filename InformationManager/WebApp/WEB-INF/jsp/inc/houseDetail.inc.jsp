<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="ui-jqgrid house-detail">
	<input type="button" id="closeButton" value="关闭" />
	<c:if test="${not empty response.peopleList}">
		<c:forEach var="member" items="${response.peopleList}">
			<table class="w1000">
				<tr>
					<td colspan="10" class="ui-widget-header center">${member.relation}</td>
				</tr>
				<tr class="ui-row-ltr jqgrow">
					<td class="ui-state-default w50">姓名</td>
					<td class="w100">${member.lname}</td>
					<td class="ui-state-default w50">身份证</td>
					<td class="w100">${member.idcard};</td>
					<td class="ui-state-default w50">绰号</td>
					<td class="w150">${member.nname}</td>
					<td class="ui-state-default w50">性别</td>
					<td class="w100">${member.gender}
					 <%-- <c:when test="${member.gender}">
					男</c:when> <c:otherwise>女</c:otherwise> --%></td>
					<td colspan="2" rowspan="4" class="w200"><img height="130"
						width="100" height src="../images/FJL.jpg" /></td>
				</tr>
				<tr class="ui-row-ltr jqgrow">
					<td class="ui-state-default">教育程度</td>
					<td>${member.education}</td>
					<td class="ui-state-default">工作</td>
					<td>${member.job}</td>
					<td class="ui-state-default">工作地点</td>
					<td>${member.workPlace}</td>
					<td class="ui-state-default">技能</td>
					<td>${member.skill}</td>
				</tr>
				<tr class="ui-row-ltr jqgrow">
					<td class="ui-state-default">政治面貌</td>
					<td>${member.socialText}</td>
					<td class="ui-state-default">联系电话</td>
					<td>${member.cellphone}</td>
					<td class="ui-state-default">收入来源</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr class="ui-row-ltr jqgrow">
					<td class="ui-state-default">所属村</td>
					<td>煤山底村</td>
					<td class="ui-state-default">所属乡</td>
					<td>上方镇</td>
					<td class="ui-state-default">最后修改时间</td>
					<td>${member.updateDate}</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</c:forEach>
	</c:if>
	<p style="text-align: center;">
		<img height="200" width="300" src="../images/Car.jpg" />&nbsp;&nbsp;<img
			height="200" width="300" src="../images/FSW.JPG" />&nbsp;&nbsp;<img
			height="200" width="300" src="../images/JLW.jpg" />
	</p>
	<p style="padding-left: 50px;">
		<span class="img-description">舒炎标的车</span><span
			class="img-description">舒炎标家内饰</span><span class="img-description">舒炎标家外景</span>
	</p>
	<p style="text-align: center;">
		<img height="200" width="300" src="../images/FWShome8.JPG" />&nbsp;&nbsp;<img
			height="200" width="300" src="../images/FWShome4.JPG" />&nbsp;&nbsp;<img
			height="200" width="300" src="../images/FWShome6.JPG" />
	</p>
	<p style="padding-left: 50px;">
		<span class="img-description">舒炎标的仓库</span><span
			class="img-description">舒炎标的鸡舍</span><span class="img-description">舒炎标的猪舍</span>
	</p>
</div>
