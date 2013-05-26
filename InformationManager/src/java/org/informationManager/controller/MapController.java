package org.informationManager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.informationManager.dto.JsonResponseDTO;
import org.informationManager.dto.MapImageDomain;
import org.informationManager.dto.PointDomain;
import org.informationManager.dto.PolygonDomain;
import org.informationManager.dto.ZoneDomain;
import org.informationManager.utils.JsonStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MapController extends BaseController {

	@RequestMapping(value = "/map/{mapId}", method = RequestMethod.GET)
	public @ResponseBody
	JsonResponseDTO initMapWithHotSpot(@PathVariable("mapId") String mapId) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("init".equalsIgnoreCase(mapId)) {
			mapId = "map03";
		}

		jsonMap.put("mapMeta", prepareImage(mapId));
		jsonMap.put("points", prepatePoints(mapId));
		jsonMap.put("polygons", prepareZones(mapId));
		JsonResponseDTO ar = new JsonResponseDTO();
		ar.setStatus(JsonStatus.SUCCESS);
		ar.setResultData(jsonMap);
		return ar;
	}

	private MapImageDomain prepareImage(String mapId) {
		MapImageDomain mim = new MapImageDomain();

		if ("map03".equalsIgnoreCase(mapId)) {
			mim.setMapImageUrl("/images/Map03.jpg");
			mim.setMapName("土地利用总图");
		} else if ("map05".equalsIgnoreCase(mapId)) {
			mim.setMapImageUrl("/images/Map05.jpg");
			mim.setMapName("规划平面图");
		} else if ("map06".equalsIgnoreCase(mapId)) {
			mim.setMapImageUrl("/images/Map06.jpg");
			mim.setMapName("结构分析");
		} else {
			return null;
		}
		mim.setMapId(mapId);
		mim.setWidth(2480d);
		mim.setHeight(3508d);

		return mim;
	}

	private List<PointDomain> prepatePoints(String mapId) {
		List<PointDomain> points = new ArrayList<PointDomain>();
		if ("map03".equalsIgnoreCase(mapId)) {
			PointDomain pb = new PointDomain(324.5d, 923.065d, "feature01");
			PointDomain pb2 = new PointDomain(433.5625d, 673.875d, "feature02");
			PointDomain pb3 = new PointDomain(553.25d, 422.96875d, "feature03");
			points.add(pb);
			points.add(pb2);
			points.add(pb3);
		}
		return points;
	}

	private List<PolygonDomain> prepareZones(String mapId) {
		List<PolygonDomain> polys = new ArrayList<PolygonDomain>();
		if ("map03".equalsIgnoreCase(mapId)) {
			PointDomain pb = new PointDomain(311.84375d, 420.40625d);
			PointDomain pb2 = new PointDomain(337.46875d, 368.28125d);
			PointDomain pb3 = new PointDomain(345.71875d, 367.15625d);
			PointDomain pb4 = new PointDomain(458.59375d, 405.90625d);
			PointDomain pb5 = new PointDomain(449.34375d, 451.40625d);
			PointDomain pb6 = new PointDomain(443.96875d, 455.53125d);
			PointDomain pb7 = new PointDomain(428.84375d, 459.88125d);
			PointDomain pb8 = new PointDomain(408.96875d, 479.53125d);
			PointDomain pb9 = new PointDomain(313.34375d, 423.78125d);

			ZoneDomain zone = new ZoneDomain();
			zone.getZones().add(pb);
			zone.getZones().add(pb2);
			zone.getZones().add(pb3);
			zone.getZones().add(pb4);
			zone.getZones().add(pb5);
			zone.getZones().add(pb6);
			zone.getZones().add(pb7);
			zone.getZones().add(pb8);
			zone.getZones().add(pb9);

			PolygonDomain poly = new PolygonDomain();
			poly.getPolygon().add(zone);
			poly.setFeatureId("poly01");
			polys.add(poly);
		}
		return polys;
	}
}
