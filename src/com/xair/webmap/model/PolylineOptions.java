package com.xair.webmap.model;

import java.util.ArrayList;

public class PolylineOptions {
	int color;
	boolean visible;
	float width;
	float zIndex;

	ArrayList<LatLng> linepoints = new ArrayList<LatLng>();

	public PolylineOptions add(LatLng... points) {
		for (LatLng point : points) {
			linepoints.add(point);
		}
		return this;
	}

	public PolylineOptions add(LatLng point) {
		linepoints.add(point);
		return this;
	}

	public PolylineOptions addAll(java.lang.Iterable<LatLng> points) {
		for (LatLng point : points) {
			linepoints.add(point);
		}
		return this;
	}

	public PolylineOptions color(int color) {
		this.color = color;
		return this;
	}

	public int getColor() {
		return color;
	}

	public java.util.List<LatLng> getPoints() {
		return linepoints;
	}

	public float getWidth() {
		return width;
	}

	public float getZIndex() {
		return zIndex;
	}

	public boolean isGeodesic() {
		return false;
	}

	public boolean isVisible() {
		return false;
	}

	public PolylineOptions visible(boolean isVisible) {
		visible = isVisible;
		return this;
	}

	public PolylineOptions width(float width) {
		this.width = width;
		return this;
	}

	public PolylineOptions zIndex(float zIndex) {
		this.zIndex = zIndex;
		return this;
	}

}
