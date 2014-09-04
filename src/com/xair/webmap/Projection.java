package com.xair.webmap;

import android.graphics.Point;
import android.util.Log;
import android.webkit.WebView;

import com.xair.webmap.WebMap.ProjectionCallback;
import com.xair.webmap.WebMap.Request;
import com.xair.webmap.WebMap.Response;
import com.xair.webmap.model.LatLng;

public class Projection {
	private LatLng projectingLatLng = new LatLng(0, 0);
	private Point projectingPoint = new Point();
	boolean flag;
//	WebView webview;
	WebMap map;
//	int projectionSessionId = 0;

//	public Projection(WebView webview) {
//		this.webview = webview;
//	}

	public Projection(WebMap map) {
		this.map = map;
	}

	Object o = new Object();

	public LatLng fromScreenLocation(Point paramPoint) {

//		projectingLatLng = null;

//		Log.d("latlngFun", "" + projectionSessionId);

		Request req = new Request("screenToLatlng", paramPoint.x + ","
				+ paramPoint.y, Request.Type_NeedResponse);

//		Response res = map.addRequest(req);
//		LatLng latlng = (LatLng) res.getAnswer();
//		Log.d("screenToLatlng", "lat:"+latlng.latitude+";lng:"+latlng.longitude);
//		return latlng;
		return projectingLatLng;
	}

	public Point toScreenLocation(LatLng paramLatLng) {
		return new Point(0, 0);
		// webview.loadUrl("javascript:latlngToScreen(" + paramLatLng.latitude
		// + "," + paramLatLng.longitude + ")");
		// synchronized (o) {
		// try {
		// o.wait(2);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// return new Point(projectingPoint.x, projectingPoint.y);
	}

	protected ProjectionCallback callback = new ProjectionCallback() {

		@Override
		public void latlngCallback(double latitude, double longitude,
				int sessionId) {
			Log.d("projection", "latlngCallback");

		}

		@Override
		public void screenpointCallback(double x, double y, int sessionId) {
			synchronized (o) {
				o.notify();
			}
			// if (sessionId == projectionSessionId) {
			Log.d("screenpointCallback", "x:" + x + ";y:" + y);
			// projectingPoint.x = (int) x;
			// projectingPoint.y = (int) y;
			projectingPoint = new Point((int) x, (int) y);
			// }
		}
	};

}
