package com.xair.webmap;

public class WebMapOptions {

	boolean compassEnabled;
	CameraPosition camera;
	int maptype;


	public WebMapOptions camera(CameraPosition camera) {
		this.camera = camera;
		return this;
	}

	public WebMapOptions compassEnabled(boolean enabled) {
		return this;
	}

	public CameraPosition getCamera() {
		return camera;
	}

	public boolean getCompassEnabled() {
		return compassEnabled;
	}

	public int getMapType() {
		return maptype;
	}

	boolean RotateGesturesEnabled;
	
	public boolean getRotateGesturesEnabled() {
		return RotateGesturesEnabled;
	}

	boolean ScaleControlsEnabled;


	public boolean getScaleControlsEnabled() {
		return ScaleControlsEnabled;
	}

	boolean ScrollGesturesEnabled;


	public boolean getScrollGesturesEnabled() {
		return this.getScrollGesturesEnabled();
	}

	boolean TiltGesturesEnabled;

	public boolean getTiltGesturesEnabled() {
		return TiltGesturesEnabled;
	}

	boolean ZoomControlsEnabled;

	public boolean getZoomControlsEnabled() {
		return ZoomControlsEnabled;
	}

	boolean ZoomGesturesEnabled;

	public boolean getZoomGesturesEnabled() {
		return ZoomGesturesEnabled;
	}

	public WebMapOptions mapType(int mapType) {
		this.maptype = mapType;
		return this;
	}

	public WebMapOptions rotateGesturesEnabled(boolean enabled) {
		this.RotateGesturesEnabled = enabled;
		return this;
	}

	public WebMapOptions scaleControlsEnabled(boolean enabled) {
		this.ScaleControlsEnabled = enabled;
		return this;
	}

	public WebMapOptions scrollGesturesEnabled(boolean enabled) {
		this.ScrollGesturesEnabled = enabled;
		return this;
	}

	public WebMapOptions tiltGesturesEnabled(boolean enabled) {
		this.TiltGesturesEnabled = enabled;
		return this;
	}

	public WebMapOptions zoomControlsEnabled(boolean enabled) {
		this.ZoomControlsEnabled = enabled;
		return this;
	}

	public WebMapOptions zoomGesturesEnabled(boolean enabled) {
		this.ZoomGesturesEnabled = enabled;
		return this;
	}

}
