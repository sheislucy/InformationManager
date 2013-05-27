<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	$(function() {
		$("#treeMenu").accordion({
			active : <%=request.getParameter("accodionIndex")%>,
			heightStyle : "fill"
		});
	});
</script>
<div id="treeMenu">
	<h3>人员信息</h3>
	<div>
		<p>
			<a class="menuLink" href="/">村1</a>&nbsp;&nbsp;&nbsp;<a
				class="menuLink maplink" href="/map" target="_self">地图浏览</a>
		</p>
		<p>
			<a class="menuLink" href="">村2</a>&nbsp;&nbsp;&nbsp;<a
				class="menuLink" href="">地图浏览</a>
		</p>
		<p>
			<a class="menuLink" href="">村3</a>&nbsp;&nbsp;&nbsp;<a
				class="menuLink" href="">地图浏览</a>
		</p>
		<p>
			<a class="menuLink" href="">村4</a>&nbsp;&nbsp;&nbsp;<a
				class="menuLink" href="">地图浏览</a>
		</p>
	</div>
	<h3>统计数据</h3>
	<div>
		<p>
			<a class="menuLink" href="/statistics/1">销售统计(3D柱状图)</a>
		</p>
		<p>
			<a class="menuLink" href="/statistics/2">地区人口增长统计(3D饼图)</a>
		</p>
		<p>
			<a class="menuLink" href="/statistics/3">同期乡村就业率交叉比较(折线图)</a>
		</p>
	</div>
</div>