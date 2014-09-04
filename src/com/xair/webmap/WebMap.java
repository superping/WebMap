package com.xair.webmap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xair.webmap.WebMap.Request;
import com.xair.webmap.model.*;

public class WebMap implements Callback {
	private WebView webview;
	Handler handler;
	Projection projection;
	CameraPosition mCaremaPosition;
	MapjavascriptInterface jsinterface;

	private static WebMap instance;
	private Handler threadHandler;

	protected static WebMap getInstance() {
		if (instance == null) {
			instance = new WebMap();
		}
		return instance;
	}

	protected WebMap() {
		handler = new Handler(this);
		threadHandler = new Handler(this);
		mCaremaPosition = new CameraPosition();
	}

	protected void init(Context context, WebView webview) {
		this.webview = webview;
		BitmapDescriptorFactory.init(context);
		jsinterface = new MapjavascriptInterface();
		webview.addJavascriptInterface(jsinterface, "mapjs");
		InputStream is = context.getResources().openRawResource(R.raw.src);
		Unzip(is, "/mnt/sdcard/android/data/com.xair.webmap/htmlsrc/");
		String path = "file:///mnt/sdcard/android/data/com.xair.webmap/htmlsrc/src/example.html";

		webview.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url) {
				Log.d("webview", "page finished");
				jsinterface.OnLoaded();
			}
		});
		webview.loadUrl(path);
		projection = new Projection(this);

	}

	private class MapjavascriptInterface {
		@JavascriptInterface
		public void markerPositionReturn(int id, double latitude,
				double longitude) {
			Marker marker = markerMap.get(id);
			marker.setPosition(latitude, longitude);
			Log.d("marker", "id:" + id + "lat:" + latitude + "lng:" + longitude);
		}

		@JavascriptInterface
		public void OnMarkerDragStart(int id) {
			Marker marker = markerMap.get(id);
			handler.obtainMessage(MARKER_DRAG_START, marker);
			Log.d("marker", "id:" + id + "dragstart");
		}

		@JavascriptInterface
		public void OnMarkerDrag(int id, double latitude, double longitude) {
			Marker marker = markerMap.get(id);
			Message msg = new Message();
			Bundle bundle = msg.getData();
			bundle.putDouble("latitude", latitude);
			bundle.putDouble("longitude", longitude);
			msg.setData(bundle);
			msg.obj = marker;
			msg.what = MARKER_DRAG;
			handler.sendMessage(msg);
			// handler.obtainMessage(MARKER_DRAG, marker);
			Log.d("marker", "id:" + id + "drag");
		}

		@JavascriptInterface
		public void OnMarkerDragEnd(int id) {
			Marker marker = markerMap.get(id);
			Message msg = handler.obtainMessage(MARKER_DRAG_END, marker);
			handler.sendMessage(msg);
			Log.d("marker", "id:" + id + "dragend");
		}

		@JavascriptInterface
		public void OnMarkerClick(int id) {
			Marker marker = markerMap.get(id);
			Message msg = handler.obtainMessage(MARKER_CLICK, marker);
			handler.sendMessage(msg);
			Log.d("marker", "id:" + id + "click");
		}

		@JavascriptInterface
		public void OnMapClick(double latitude, double longitude) {
			LatLng point = new LatLng(latitude, longitude);
			Message msg = handler.obtainMessage(MAP_CLICK, point);
			handler.sendMessage(msg);
		}

		@JavascriptInterface
		public void OnMapDragStart() {
		}

		@JavascriptInterface
		public void OnMapDrag() {
		}

		@JavascriptInterface
		public void OnMapDragEnd() {
		}

		@JavascriptInterface
		public void OnLoaded() {
			handler.sendEmptyMessage(MAP_LOAD);
		}

		@JavascriptInterface
		public void OnCameraChange(double latitude, double longitude,
				float zoom, float tilt, float bearing) {
			Message msg = new Message();
			Bundle bundle = msg.getData();
			bundle.putDouble("latitude", latitude);
			bundle.putDouble("longitude", longitude);
			bundle.putFloat("zoom", zoom);
			bundle.putFloat("tilt", tilt);
			bundle.putFloat("bearing", bearing);
			msg.setData(bundle);
			msg.what = MAP_CAMERA_CHANGE;
			handler.sendMessage(msg);
		}

		@JavascriptInterface
		public void LatLngCallback(double lat, double lng, int SessionId) {
			Response res = findResponse(SessionId);
			if (res != null) {
				synchronized (res) {
					res.ans = new LatLng(lat, lng);
					waitsponseList.remove(res);
					// res.mSemaphore.release();
					res.req.mSemaphore.release();
					res.notify();

				}
			}
		}

		@JavascriptInterface
		public void PointCallback(double x, double y, int SessionId) {
			if (waitingReq != null) {
				if (waitingReq.sessionId == SessionId) {
					waitingReq.arrived = true;
					waitingReq = null;
					projection.callback.screenpointCallback(x, y, SessionId);
				}
			}
		}

	}

	private static void Unzip(InputStream inputstream, String targetDir) {
		int BUFFER = 4096;
		String strEntry;
		try {
			BufferedOutputStream dest = null;
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(
					inputstream));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				try {

					Log.i("Unzip: ", "=" + entry);
					int count;
					byte data[] = new byte[BUFFER];
					strEntry = entry.getName();

					File entryFile = new File(targetDir + strEntry);
					if (entry.isDirectory()) {
						boolean flag = entryFile.mkdirs();
						Log.d("mkdir", entryFile.getPath() + ":" + flag);
						continue;
					}
					File entryDir = new File(entryFile.getParent());
					if (!entryDir.exists()) {
						boolean flag = entryDir.mkdirs();
						Log.d("mkdir", entryDir.getPath() + ":" + flag);
					}
					FileOutputStream fos = new FileOutputStream(entryFile);
					dest = new BufferedOutputStream(fos, BUFFER);
					while ((count = zis.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
					dest.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			zis.close();
		} catch (Exception cwj) {
			cwj.printStackTrace();
		}
	}

	static int markerIndex = 0;
	MarkerCallback callback = new MarkerCallback();
	Map<Integer, Marker> markerMap = new HashMap<Integer, Marker>();

	public final Marker addMarker(MarkerOptions options) {
		markerIndex++;
		Marker marker = new Marker(markerIndex, this, options, callback);
		markerMap.put(markerIndex, marker);
		return marker;
	}

	public class MarkerCallback {
		public void OnRemove(int id) {
			markerMap.remove(id);
		}
	}

	static int polylineIndex = 0;
	Map<Integer, Polyline> polylineMap = new HashMap<Integer, Polyline>();
	PolylineCallback polylinecallback = new PolylineCallback();

	public final Polyline addPolyline(PolylineOptions options) {
		polylineIndex++;
		Polyline polyline = new Polyline(polylineIndex, this, options,
				polylinecallback);
		polylineMap.put(polylineIndex, polyline);
		return polyline;
	}

	public class PolylineCallback {
		public void OnRemove(int id) {
			markerMap.remove(id);
		}
	}

	public void clear() {

	}

	public CameraPosition getCameraPosition() {
		return mCaremaPosition;
	}

	public int getMapType() {
		return 0;
	}

	public Projection getProjection() {
		return projection;
	}

	public interface ProjectionCallback {
		void latlngCallback(double latitude, double longitude, int sessionId);

		void screenpointCallback(double x, double y, int sessionId);

	}

	UISettings uisetting = new UISettings();

	public UISettings getUiSettings() {

		return uisetting;
	}

	public void moveCamera(CameraUpdate update) {
		if (update.mode == CameraUpdate.MODE_BOUND) {
			JSONObject polylineJSON = new JSONObject();
			try {
				polylineJSON.put("pad", update.pad);
				polylineJSON.put("northeastLat", update.lb.northeast.latitude);
				polylineJSON.put("northeastLng", update.lb.northeast.longitude);
				polylineJSON.put("southwestLat", update.lb.southwest.latitude);
				polylineJSON.put("southwestLng", update.lb.southwest.longitude);

				String str = polylineJSON.toString();
				webview.loadUrl("javascript:moveCamByBound(" + str + ")");

			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if (update.mode == CameraUpdate.MODE_CENTER) {
			try {
				JSONObject polylineJSON = new JSONObject();
				polylineJSON.put("zoom", update.zoom);
				polylineJSON.put("centerLat", update.center.latitude);
				polylineJSON.put("centerLng", update.center.longitude);

				String str = polylineJSON.toString();
				webview.loadUrl("javascript:moveCamByCenter(" + str + ")");

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public interface OnCameraChangeListener {
		abstract void onCameraChange(CameraPosition position);
	}

	public interface OnMapClickListener {
		abstract void onMapClick(LatLng point);
	}

	public interface OnMapLoadedCallback {
		abstract void onMapLoaded();
	}

	public interface OnMarkerClickListener {
		abstract boolean onMarkerClick(Marker marker);
	}

	public interface OnMarkerDragListener {

		// Called repeatedly while a marker is being dragged.
		abstract void onMarkerDrag(Marker marker);

		// Called when a marker has finished being dragged.
		abstract void onMarkerDragEnd(Marker marker);

		// Called when a marker starts being dragged.
		abstract void onMarkerDragStart(Marker marker);

	}

	public interface InfoWindowAdapter {
		// Provides custom contents for the default info window frame of a
		// marker.
		abstract View getInfoContents(Marker marker);

		// Provides a custom info window for a marker.
		abstract View getInfoWindow(Marker marker);

	}

	OnCameraChangeListener mOnCameraChangeListener;

	// Sets a callback that's invoked when the camera changes.
	public final void setOnCameraChangeListener(OnCameraChangeListener listener) {
		this.mOnCameraChangeListener = listener;
	}

	OnMapClickListener mOnMapClickListener;

	// Sets a callback that's invoked when the map is tapped.
	public final void setOnMapClickListener(OnMapClickListener listener) {
		mOnMapClickListener = listener;
	}

	OnMapLoadedCallback mOnMapLoadedCallback;

	// Sets a callback that is invoked when this map has finished rendering.
	public void setOnMapLoadedCallback(OnMapLoadedCallback callback) {
		mOnMapLoadedCallback = callback;
	}

	OnMarkerClickListener mOnMarkerClickListener;

	// Sets a callback that's invoked when a marker is clicked.
	public final void setOnMarkerClickListener(OnMarkerClickListener listener) {
		mOnMarkerClickListener = listener;
	}

	OnMarkerDragListener mOnMarkerDragListener;

	// Sets a callback that's invoked when a marker is dragged.
	public final void setOnMarkerDragListener(OnMarkerDragListener listener) {
		mOnMarkerDragListener = listener;
	}

	InfoWindowAdapter mInfoWindowAdapter;

	public void setInfoWindowAdapter(InfoWindowAdapter adapter) {
		mInfoWindowAdapter = adapter;
	}

	final int MARKER_DRAG_START = 1;
	final int MARKER_DRAG = 2;
	final int MARKER_DRAG_END = 3;
	final int MARKER_CLICK = 4;
	final int MAP_DRAG_START = 5;
	final int MAP_DRAG = 6;
	final int MAP_DRAG_END = 7;
	final int MAP_CLICK = 8;

	final int MAP_ZOOM_CHANGE = 9;

	final int MAP_LOAD = 10;

	final int MAP_CAMERA_CHANGE = 11;

	final int REQUEST_NORES = 4000;
	final int REQUEST_RES = 4101;

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case MARKER_DRAG_START:
			if (mOnMarkerDragListener != null) {
				mOnMarkerDragListener.onMarkerDragStart((Marker) msg.obj);
			}
			break;
		case MARKER_DRAG:
			Marker marker = (Marker) msg.obj;
			Bundle data = msg.getData();
			marker.getPosition().latitude = data.getDouble("latitude");
			marker.getPosition().longitude = data.getDouble("longitude");
			if (mOnMarkerDragListener != null) {
				mOnMarkerDragListener.onMarkerDrag(marker);
			}
			break;
		case MARKER_DRAG_END:
			if (mOnMarkerDragListener != null) {
				mOnMarkerDragListener.onMarkerDragEnd((Marker) msg.obj);
			}
			break;
		case MARKER_CLICK:
			if (mOnMarkerClickListener != null) {
				mOnMarkerClickListener.onMarkerClick((Marker) msg.obj);
			}
			break;
		case MAP_CLICK:
			if (mOnMapClickListener != null) {
				mOnMapClickListener.onMapClick((LatLng) msg.obj);
			}
			break;
		case MAP_DRAG_START:
		case MAP_DRAG:
		case MAP_DRAG_END:
			break;
		case MAP_CAMERA_CHANGE:
			Bundle bundle = msg.getData();

			double latitude = bundle.getDouble("latitude");
			double longitude = bundle.getDouble("longitude");
			float zoom = bundle.getFloat("zoom");
			float tilt = bundle.getFloat("tilt");
			float bearing = bundle.getFloat("bearing");

			mCaremaPosition.target.latitude = latitude;
			mCaremaPosition.target.longitude = longitude;
			mCaremaPosition.zoom = zoom;
			mCaremaPosition.tilt = tilt;
			mCaremaPosition.bearing = bearing;

			break;
		case MAP_LOAD:
			if (mOnMapLoadedCallback != null) {
				mOnMapLoadedCallback.onMapLoaded();
			}
			break;
		case REQUEST_RES:

			Request reqRes = (Request) msg.obj;
			String reqStr = "javascript:" + reqRes.funcString + "("
					+ reqRes.paramString + "," + reqRes.sessionId + ")";
			Log.d("request", reqStr);
			webview.loadUrl(reqStr);

			break;
		case REQUEST_NORES:

			Request req = (Request) msg.obj;
			String noreqStr = "javascript:" + req.funcString + "("
					+ req.paramString + ")";
			Log.d("request", noreqStr);
			webview.loadUrl(noreqStr);
			break;

		}
		return false;
	}

	public static class Request {
		String funcString;
		String paramString;
		int sessionId;
		int requestType;
		boolean arrived = false;
		private final Semaphore mSemaphore = new Semaphore(0, false);
		public final static int Type_NeedResponse = 1;
		public final static int Type_NotResponse = 2;
		static int count = 0;

		public Request(String jsonString, String param, int type) {
			this.sessionId = count++;
			this.funcString = jsonString;
			this.paramString = param;
			this.requestType = type;
		}
	}

	public class Response {
		int sessionId;
		Object ans;
		Request req;

		public Object getAnswer() {
			return ans;
		}
	}

	LinkedList<Request> responseList = new LinkedList<Request>();
	LinkedList<Request> noresponseList = new LinkedList<Request>();

	LinkedList<Response> waitsponseList = new LinkedList<Response>();

	Request waitingReq;
	Response waitingRes;
	boolean isAlive;
//	Runnable run = new Runnable() {
//
//		@Override
//		public void run() {
//			isAlive = true;
//			while (isAlive) {
//				if (noresponseList.size() > 0) {
//					if (responseList.size() == 0) {
//						synchronized (noresponseList) {
//							Request req = noresponseList.pop();
//							Message msg = threadHandler.obtainMessage(
//									REQUEST_NORES, req);
//							threadHandler.sendMessage(msg);
//						}
//					}
//
//				}
//				if (noresponseList.size() == 0 || responseList.size() > 0) {
//					isAlive = false;
//				} else
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//
//			}
//		}
//	};
	Thread thread;

	public Response addRequest(Request req) {
		Response res = new Response();
		if (req.requestType == Request.Type_NeedResponse) {
//			synchronized (responseList) {
//				responseList.add(req);
//			}
//			res.sessionId = req.sessionId;
//			res.req = req;
//			waitsponseList.add(res);
//
//			String reqStr = "javascript:" + req.funcString + "("
//					+ req.paramString + "," + req.sessionId + ")";
//			Log.d("request", reqStr);
//			isAlive = false;
//			if (thread != null) {
//				while (thread.isAlive())
//					;
//			}
//			
//			while (res.ans == null) {
//				webview.loadUrl(reqStr);
//				synchronized (req) {
//					try {
//						boolean flag = req.mSemaphore.tryAcquire(20,
//								TimeUnit.MILLISECONDS);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			synchronized (responseList) {
//				responseList.pop();
//			}
//			startThread();
		} else if (req.requestType == Request.Type_NotResponse) {
//			synchronized (noresponseList) {
//				noresponseList.add(req);
//			}
//			startThread();
//			Request req = (Request) msg.obj;
			String noreqStr = "javascript:" + req.funcString + "("
					+ req.paramString + ")";
			Log.d("request", noreqStr);
			webview.loadUrl(noreqStr);
		}
		return res;
	}

//	void startThread() {
//		if (thread == null) {
//			thread = new Thread(run);
//			thread.start();
//		} else if (!thread.isAlive()) {
//			thread = new Thread(run);
//			thread.start();
//		}
//	}

	Response findResponse(int sessionId) {
		for (Response res : waitsponseList) {
			if (res.sessionId == sessionId) {
				return res;
			}
		}
		return null;
	}

}
