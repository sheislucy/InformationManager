<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	$(function() {
		$("#treeMenu").accordion({
			active : <%=request.getParameter("accodionIndex")%>,
			heightStyle : "fill"
		});
		$(":button").button();
		$(":button").click(function() {
			$.ajax({
				url : this.attributes.data.value,
				success : function(data) {
					$(".ui-layout-center").html(data);
				}
			});
		});
	});
</script>
<div id="treeMenu">
	<h3>人员信息</h3>
	<div>
		<p>
			<input class="peopleButton" type="button" value="人员统计"
				data="/people/inc" />
		</p>
		<p>
			<input class="mapButton" type="button" value="地图浏览" data="/map/inc" />
		</p>
	</div>
	<h3>统计数据</h3>
	<div>
		<p>
			<input class="peopleButton" type="button" value="地区人口增长统计(3D饼图)"
				data="/chart/2" />
		</p>
		<p>
			<input class="peopleButton" type="button" value="销售统计(3D柱状图)"
				data="/chart/1" />
		</p>
		<p>
			<input class="peopleButton" type="button" value="同期乡村就业率交叉比较(折线图)"
				data="/chart/3" />
		</p>
	</div>
</div>