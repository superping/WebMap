package com.xair.webmap.model;

public class MarkerOptions {
	float anchorU = 0.5f;
	float anchorV = 0.5f;
	int zIndex = 0;

	public MarkerOptions anchor(float u, float v) {
		anchorU = u;
		anchorV = v;
		return this;
	}

	public MarkerOptions draggable(boolean draggable) {
		this.draggable = draggable;
		return this;
	}

	public float getAnchorU() {
		return anchorU;
	}

	public float getAnchorV() {
		return anchorV;
	}

	BitmapDescriptor icon;

	public BitmapDescriptor getIcon() {
		return icon;
	}

	LatLng position;

	public LatLng getPosition() {
		return position;
	}

	public MarkerOptions icon(BitmapDescriptor icon) {
		this.icon = icon;
		return this;
	}

	boolean draggable;

	public boolean isDraggable() {
		return draggable;
	}

	boolean visible;

	public boolean isVisible() {
		return visible;
	}

	public MarkerOptions position(LatLng position) {
		this.position = position;
		return this;
	}


	public MarkerOptions visible(boolean visible) {
		this.visible = visible;
		return this;
	}

	float rotation;

	// Sets the rotation of the marker in degrees clockwise about the marker's
	// anchor point.
	public MarkerOptions rotation(float rotation) {
		this.rotation = rotation;
		return this;
	}

	// Gets the rotation set for this MarkerOptions object.
	public float getRotation() {
		return this.rotation;
	}
	
	public MarkerOptions zIndex(int zIndex){
		this.zIndex = zIndex;
		return this;
	}
	
	public int getZIndex(){
		return this.zIndex;
	}

}
