package com.xair.webmap.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xair.webmap.WebMap;
import com.xair.webmap.WebMap.PolylineCallback;
import com.xair.webmap.WebMap.Request;

import android.webkit.WebView;

public class Polyline {

	int color;
	float width;
	float zIndex;
	boolean visible;
	ArrayList<LatLng> points;

	final int id;

//	WebView webview;
	WebMap map;
	PolylineCallback mCallback;

	public Polyline(int polylineIndex, WebMap map,
			PolylineOptions options, PolylineCallback polylinecallback) {
		this.id = polylineIndex;
		this.color = options.color;
		this.points = options.linepoints;
		this.visible = options.visible;
		this.width = options.width;
		this.zIndex = options.zIndex;

		this.map = map;
		this.mCallback = polylinecallback;

		JSONObject polylineJSON = new JSONObject();
		try {
			polylineJSON.put("id", id);
			polylineJSON.put("visible", visible);
			polylineJSON.put("width", width);
			polylineJSON.put("zIndex", zIndex);
			polylineJSON.put("count", points.size());

			JSONArray array = new JSONArray();

			for (LatLng latlng : points) {
				JSONObject node = new JSONObject();
				node.put("latitude", latlng.latitude);

				node.put("longitude", latlng.longitude);
				array.put(node);
			}

			polylineJSON.put("latlngs", array);
			String str = polylineJSON.toString();
//			webview.loadUrl("javascript:createPolyline(" + str + ")");
//			String requestStr = "javascript:createPolyline(" + str + ")";	
//			Request req = new Request(requestStr, Request.Type_NotResponse);
			Request req = new Request("createPolyline", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public int getColor() {
		return color;
	}

	public List<LatLng> getPoints() {
		return points;
	}


	public float getWidth() {
		return width;
	}

	public float getZIndex() {
		return zIndex;
	}

	public boolean isVisible() {
		return visible;
	}

	public void remove() {
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("id", id);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		String str = markerJSON.toString();
//		webview.loadUrl("javascript:removePolyline(" + str + ")");
//		String requestStr = "javascript:removePolyline(" + str + ")";	
//		Request req = new Request(requestStr, Request.Type_NotResponse);
		Request req = new Request("removePolyline", str,
				Request.Type_NotResponse);
		map.addRequest(req);
	}

	public void setColor(int color) {
		this.color = color;
		JSONObject markerJSON = new JSONObject();
		try {;
			markerJSON.put("id", id);
			markerJSON.put("property", "color");
			markerJSON.put("color", color);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		String str = markerJSON.toString();
//		webview.loadUrl("javascript:setPolylineProperty(" + str + ")");
//		String requestStr = "javascript:setPolylineProperty(" + str + ")";	
//		Request req = new Request(requestStr, Request.Type_NotResponse);
		Request req = new Request("setPolylineProperty", str,
				Request.Type_NotResponse);
		map.addRequest(req);
	}

	public void setPoints(java.util.List<LatLng> points) {
		this.points = (ArrayList<LatLng>) points;
		JSONObject markerJSON = new JSONObject();
		
		JSONArray array = new JSONArray();
		try {
			markerJSON.put("id", id);
			markerJSON.put("property", "latlngs");
			for (LatLng latlng : points) {
				JSONObject node = new JSONObject();
				node.put("latitude", latlng.latitude);
				node.put("longitude", latlng.longitude);
				array.put(node);
			}
			markerJSON.put("latlngs", array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String str = markerJSON.toString();
//		webview.loadUrl("javascript:setPolylineProperty(" + str + ")");
//		String requestStr = "javascript:setPolylineProperty(" + str + ")";	
//		Request req = new Request(requestStr, Request.Type_NotResponse);
		Request req = new Request("setPolylineProperty", str,
				Request.Type_NotResponse);
		map.addRequest(req);
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("id", id);
			markerJSON.put("property", "visible");
			markerJSON.put("visible", visible);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		String str = markerJSON.toString();
//		webview.loadUrl("javascript:setPolylineProperty(" + str + ")");
//		String requestStr = "javascript:setPolylineProperty(" + str + ")";	
//		Request req = new Request(requestStr, Request.Type_NotResponse);
		Request req = new Request("setPolylineProperty", str,
				Request.Type_NotResponse);
		map.addRequest(req);
	}

	public void setWidth(float width) {
		this.width = width;
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("id", id);
			markerJSON.put("property", "width");
			markerJSON.put("width", width);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		String str = markerJSON.toString();
//		webview.loadUrl("javascript:setPolylineProperty(" + str + ")");
//		String requestStr = "javascript:setPolylineProperty(" + str + ")";	
//		Request req = new Request(requestStr, Request.Type_NotResponse);
		Request req = new Request("setPolylineProperty", str,
				Request.Type_NotResponse);
		map.addRequest(req);
	}

	public void setZIndex(float zIndex) {
		this.zIndex = zIndex;
	}

}
