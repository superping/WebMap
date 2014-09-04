package com.xair.webmap;

import android.graphics.Point;

import com.xair.webmap.model.LatLng;

public class CameraUpdateFactory {

	// Returns a CameraUpdate that moves the camera to a specified
	// CameraPosition.
	public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
		CameraUpdate cu = new CameraUpdate();
		cu.center = cameraPosition.target;
		cu.zoom = cameraPosition.zoom;
		cu.mode = CameraUpdate.MODE_CENTER;

		return cu;
	}

	// Returns a CameraUpdate that moves the center of the screen to a latitude
	// and longitude specified by a LatLng object.
	public static CameraUpdate newLatLng(LatLng latLng) {
		CameraUpdate cu = new CameraUpdate();
		cu.center = latLng;
		cu.mode = CameraUpdate.MODE_CENTER;

		return cu;
	}

	// Returns a CameraUpdate that transforms the camera such that the specified
	// latitude/longitude bounds are centered on screen at the greatest possible
	// zoom level.
	public static CameraUpdate newLatLngBounds(LatLngBounds bounds, int padding) {
		CameraUpdate cu = new CameraUpdate();
		cu.lb = bounds;
		cu.pad = padding;

		cu.mode = CameraUpdate.MODE_BOUND;
		return cu;
	}

	// Returns a CameraUpdate that transforms the camera such that the specified
	// latitude/longitude bounds are centered on screen within a bounding box of
	// specified dimensions at the greatest possible zoom level.
	public static CameraUpdate newLatLngBounds(LatLngBounds bounds, int width,
			int height, int padding) {
		CameraUpdate cu = new CameraUpdate();
		cu.lb = bounds;
		cu.pad = padding;

		cu.mode = CameraUpdate.MODE_BOUND;
		return cu;
	}

	// Returns a CameraUpdate that moves the center of the screen to a latitude
	// and longitude specified by a LatLng object, and moves to the given zoom
	// level.
	public static CameraUpdate newLatLngZoom(LatLng latlng, float zoom) {
		CameraUpdate cu = new CameraUpdate();
		cu.center = latlng;
		cu.zoom = zoom;
		cu.mode = CameraUpdate.MODE_CENTER;
		return cu;
	}

	// Returns a CameraUpdate that scrolls the camera over the map, shifting the
	// center of view by the specified number of pixels in the x and y
	// directions.
	public static CameraUpdate scrollBy(float xPixel, float yPixel) {
		return null;
	}

	// Returns a CameraUpdate that shifts the zoom level of the current camera
	// viewpoint.
	public static CameraUpdate zoomBy(float amount, Point focus) {
		return null;
	}

	// Returns a CameraUpdate that shifts the zoom level of the current camera
	// viewpoint.
	public static CameraUpdate zoomBy(float amount) {
		return null;
	}

	// Returns a CameraUpdate that zooms in on the map by moving the viewpoint's
	// height closer to the Earth's surface.
	public static CameraUpdate zoomIn() {
		CameraUpdate cu = new CameraUpdate();
		cu.center = WebMap.getInstance().getCameraPosition().target;
		cu.zoom = WebMap.getInstance().getCameraPosition().zoom + 1;
		cu.mode = CameraUpdate.MODE_CENTER;

		return cu;
	}

	// Returns a CameraUpdate that zooms out on the map by moving the
	// viewpoint's height farther away from the Earth's surface.
	public static CameraUpdate zoomOut() {
		CameraUpdate cu = new CameraUpdate();
		cu.center = WebMap.getInstance().getCameraPosition().target;
		cu.zoom = WebMap.getInstance().getCameraPosition().zoom - 1;
		cu.mode = CameraUpdate.MODE_CENTER;

		return cu;
	}

	// Returns a CameraUpdate that moves the camera viewpoint to a particular
	// zoom level.
	public static CameraUpdate zoomTo(float zoom) {
		CameraUpdate cu = new CameraUpdate();
		cu.center = WebMap.getInstance().getCameraPosition().target;
		cu.zoom = zoom;
		cu.mode = CameraUpdate.MODE_CENTER;

		return cu;
	}

}
