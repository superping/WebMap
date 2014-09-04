package com.xair.webmap;

import com.xair.webmap.model.BitmapDescriptorFactory;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapFragment extends Fragment {

	static MapFragment instance;
	private static final String MAP_URL = "file:///android_asset/src/example.html";
	WebView webview;
	WebMap map;

	public WebMap getMap() {
		return map;
	}

	static public MapFragment newInstance() {
		instance = new MapFragment();
		return instance;
	}

	static public MapFragment newInstance(WebMapOptions options) {
		instance = new MapFragment();
		// TODO init map
		return instance;
	}

	public void onAttach(Activity paramActivity) {
		super.onAttach(paramActivity);
		BitmapDescriptorFactory.init(paramActivity);

	}

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
	}

	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		// View rootview = paramLayoutInflater.inflate(R.layout.layout_map,
		// paramViewGroup, false);

		WebView view = new WebView(getActivity());
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		// webview = (WebView) rootview.findViewById(R.id.map_webview);
		webview = view;
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setUseWideViewPort(false);
		webview.getSettings().setSupportZoom(false);
		webview.getSettings().setBuiltInZoomControls(false);
		webview.getSettings().setDisplayZoomControls(false);
		webview.getSettings().setDomStorageEnabled(true);
		webview.setWebViewClient(new WebViewClient());
		map = WebMap.getInstance();
		map.init(getActivity(), webview);

		Log.d("mapview", "" + webview.hashCode());
//		webview.addJavascriptInterface(new JavaScriptInterface(), "markers");
		return view;
	}

	public class JavaScriptInterface {

		@JavascriptInterface
		public void position(final float latitude, final float longitude) {
			Log.d("setposition F", "" + latitude + "," + longitude);
		}

		@JavascriptInterface
		public void onMarker() {
			Log.d("onMarker F", "onMarker");
		}

	}

}
