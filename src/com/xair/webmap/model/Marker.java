package com.xair.webmap.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.xair.webmap.WebMap;
import com.xair.webmap.WebMap.MarkerCallback;
import com.xair.webmap.WebMap.Request;

import android.util.Log;

public class Marker {
	// WebView webview;
	WebMap map;
	float anchorU, anchorV;
	protected LatLng position;
	boolean draggable;
	boolean visible;
	float angle;
	int zIndex;
	BitmapDescriptor icon;

	// TODO
	MarkerCallback callback;
	protected final int index;

	public Marker(int index, WebMap map, MarkerOptions options,
			MarkerCallback callback) {
		this.index = index;
		this.map = map;
		this.angle = options.rotation;
		this.draggable = options.draggable;
		this.icon = options.icon;
		this.position = options.position;
		this.visible = options.visible;
		this.anchorU = options.anchorU;
		this.anchorV = options.anchorV;
		this.zIndex = options.zIndex;

		this.callback = callback;

		// Log.d("mapview", "" + webview.hashCode());

		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("id", index);
			markerJSON.put("rotation", angle);
			markerJSON.put("draggable", draggable);
			if (icon != null) {
				markerJSON.put("icon", icon.toString());
				markerJSON.put("height", icon.getHeight());
				markerJSON.put("width", icon.getWidth());
			} else {
				markerJSON.put("icon", null);
				markerJSON.put("height", 0);
				markerJSON.put("width", 0);
			}
			markerJSON.put("latitude", position.latitude);
			markerJSON.put("longitude", position.longitude);
			markerJSON.put("visible", visible);
			markerJSON.put("anchorU", anchorU);
			markerJSON.put("anchorV", anchorV);
			markerJSON.put("zIndex", zIndex);

			String str = markerJSON.toString();
			// webview.loadUrl("javascript:createMarker(" + str + ")");
			// String requestStr = "javascript:createMarker(" + str + ")";
			// Request req = new Request(requestStr, Request.Type_NotResponse);
			Request req = new Request("createMarker", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("id", index);
			String str = markerJSON.toString();
			// webview.loadUrl("javascript:removeMarker(" + str + ")");
			// String requestStr = "javascript:removeMarker(" + str + ")";
			// Request req = new Request(requestStr, Request.Type_NotResponse);
			Request req = new Request("removeMarker", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		callback.OnRemove(index);
	}

	public LatLng getPosition() {
		// JSONObject markerJSON = new JSONObject();
		// try {
		// markerJSON.put("id", index);
		// markerJSON.put("property", "position");
		//
		// String str = markerJSON.toString();
		// webview.loadUrl("javascript:getMarkerProperty(" + str + ")");
		//
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return position;
	}

	public boolean isDraggable() {
		return draggable;
	}

	public boolean isVisible() {
		return visible;
	}

	public void remove() {
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("id", index);
			String str = markerJSON.toString();
			// webview.loadUrl("javascript:removeMarker(" + str + ")");
			// String requestStr = "javascript:removeMarker(" + str + ")";
			// Request req = new Request(requestStr, Request.Type_NotResponse);
			Request req = new Request("removeMarker", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void setAnchor(float anchorU, float anchorV) {
		this.anchorU = anchorU;
		this.anchorV = anchorV;
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("property", "anchor");
			markerJSON.put("id", index);
			markerJSON.put("anchorU", anchorU);
			markerJSON.put("anchorV", anchorV);
			String str = markerJSON.toString();
			// webview.loadUrl("javascript:setMarkerProperty(" + str + ")");
			// String requestStr = "javascript:setMarkerProperty(" + str + ")";
			// Request req = new Request(requestStr, Request.Type_NotResponse);
			Request req = new Request("setMarkerProperty", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void setDraggable(boolean paramBoolean) {
		this.draggable = paramBoolean;
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("property", "draggable");
			markerJSON.put("id", index);
			markerJSON.put("draggable", draggable);
			String str = markerJSON.toString();
			// webview.loadUrl("javascript:setMarkerProperty(" + str + ")");
			// String requestStr = "javascript:setMarkerProperty(" + str + ")";
			// Request req = new Request(requestStr, Request.Type_NotResponse);
			Request req = new Request("setMarkerProperty", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void setIcon(BitmapDescriptor icon) {
		this.icon = icon;
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("property", "icon");
			markerJSON.put("id", index);
			markerJSON.put("icon", icon.toString());
			markerJSON.put("height", icon.getHeight());
			markerJSON.put("width", icon.getWidth());
			String str = markerJSON.toString();
			// webview.loadUrl("javascript:setMarkerProperty(" + str + ")");
			// String requestStr = "javascript:setMarkerProperty(" + str + ")";
			// Request req = new Request(requestStr, Request.Type_NotResponse);
			Request req = new Request("setMarkerProperty", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void setPosition(LatLng latlng) {
		this.position = latlng;
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("property", "position");
			markerJSON.put("id", index);
			markerJSON.put("latitude", position.latitude);
			markerJSON.put("longitude", position.longitude);
			String str = markerJSON.toString();
			// webview.loadUrl("javascript:setMarkerProperty(" + str + ")");
			// String requestStr = "javascript:setMarkerProperty(" + str + ")";
			// Request req = new Request(requestStr, Request.Type_NotResponse);
			Request req = new Request("setMarkerProperty", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void setPosition(double lat, double lng) {
		this.position.latitude = lat;
		this.position.latitude = lng;
	}

	public void setRotateAngle(float rotate) {
		this.angle = rotate;
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("property", "rotation");
			markerJSON.put("id", index);
			markerJSON.put("rotation", angle);
			String str = markerJSON.toString();
			// webview.loadUrl("javascript:setMarkerProperty(" + str + ")");
			// String requestStr = "javascript:setMarkerProperty(" + str + ")";
			// Request req = new Request(requestStr, Request.Type_NotResponse);
			Request req = new Request("setMarkerProperty", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("property", "visible");
			markerJSON.put("id", index);
			markerJSON.put("visible", visible);
			String str = markerJSON.toString();
			// webview.loadUrl("javascript:setMarkerProperty(" + str + ")");
			// String requestStr = "javascript:(" + str + ")";
			Request req = new Request("setMarkerProperty", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
		JSONObject markerJSON = new JSONObject();
		try {
			markerJSON.put("property", "zIndex");
			markerJSON.put("id", index);
			markerJSON.put("zIndex", zIndex);
			String str = markerJSON.toString();
			// webview.loadUrl("javascript:setMarkerProperty(" + str + ")");
			// String requestStr = "javascript:(" + str + ")";
			Request req = new Request("setMarkerProperty", str,
					Request.Type_NotResponse);
			map.addRequest(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public int getZIndex() {
		return this.zIndex;
	}

}
