package com.xair.webmap;

import com.xair.webmap.model.LatLng;

public class CameraPosition {
	public float bearing;
	public LatLng target = new LatLng(0, 0);
	public float tilt;
	public float zoom;

	public final static CameraPosition fromLatLngZoom(LatLng target, float zoom) {
		CameraPosition cp = new CameraPosition();
		cp.bearing = 0;
		cp.target = target;
		cp.tilt = 0;
		cp.zoom = zoom;
		return cp;
	}

	static Builder builder;

	public static CameraPosition.Builder builder(CameraPosition camera) {
		if (builder  == null) {
			builder = new Builder();
		}
		builder.bearing = camera.bearing;
		builder.target = camera.target;
		builder.tilt = camera.tilt;
		builder.zoom = camera.zoom;
		return builder;
	}

	public static CameraPosition.Builder builder() {
		if (builder == null) {
			builder = new Builder();
		}
		return builder;
	}

	static public class Builder {
		private float bearing;
		private LatLng target;
		private float tilt;
		private float zoom;

		public Builder bearing(float bearing) {
			this.bearing = bearing;
			return this;
		}

		public Builder target(LatLng location) {
			target = location;
			return this;
		}

		public Builder tilt(float tilt) {
			this.tilt = tilt;
			return this;
		}

		public Builder zoom(float zoom) {
			this.zoom = zoom;
			return this;
		}

		public CameraPosition build() {
			CameraPosition cp = new CameraPosition();
			cp.bearing = bearing;
			cp.target = target;
			cp.tilt = tilt;
			cp.zoom = zoom;
			return cp;
		}

	};
}
