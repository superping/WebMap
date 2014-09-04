package com.xair.webmap;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapView extends View {
	WebMap map;
	WebView webview;

	public MapView(Context context, WebMapOptions options) {
		super(context);
		WebView view = new WebView(context);
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
		map.init(context, webview, options);

		Log.d("mapview", "" + webview.hashCode());

	}

	public MapView(Context context) {
		super(context);
	}

	public WebMap getMap() {
		return map;
	}

}
