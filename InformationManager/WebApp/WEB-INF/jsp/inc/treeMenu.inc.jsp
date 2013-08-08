<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="treeMenu">
	<h3>村民信息</h3>
	<div>
		<p>
			<input class="peopleButton" type="button" value="村民信息浏览"
				dataUrl="/people/inc" />
		</p>
		<p>
			<input class="mapButton" type="button" value="户籍信息浏览" dataUrl="/map/inc/03" dataParent="p"/>
		</p>
	</div>
	<h3>统计数据</h3>
	<div>
		<p>
			<input class="peopleButton" type="button" value="地区人口增长统计(3D饼图)"
				dataUrl="/chart/2" />
		</p>
		<p>
			<input class="peopleButton" type="button" value="销售统计(3D柱状图)"
				dataUrl="/chart/1" />
		</p>
		<p>
			<input class="peopleButton" type="button" value="同期乡村就业率交叉比较(折线图)"
				dataUrl="/chart/3" />
		</p>
	</div>
	<h3>农村规划</h3>
	<div>
		<c:forEach var="map" items="${mapList}">
			<p class="clear">
				<input class="fl" type="button" value="${map.mapName}" dataUrl="/map/inc/${map.id}"/>
				<input class="fr mr10" type="button" value="编辑" forwardType="edit" dataUrl="/map/edit/${map.id}"/>
			</p>
		</c:forEach>
	</div>
</div>
<script>
	$(function() {
		$("#treeMenu").accordion({
			heightStyle : "fill"
		});
		$(":button").button();
		$(":button").click(function() {
			if(this.attributes.forwardType && this.attributes.forwardType.value == "edit"){
				window.open(this.attributes.dataUrl.value, "_blank");
			}else{
				$.ajax({
					url : this.attributes.dataUrl.value,
					success : function(data) {
						$(".ui-layout-center").html(data);
					}
				});
			}
		});
	});
</script>