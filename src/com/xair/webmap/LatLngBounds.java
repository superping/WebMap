package com.xair.webmap;

import java.util.ArrayList;

import com.xair.webmap.model.LatLng;

public class LatLngBounds {
	LatLng northeast = new LatLng(0, 0);
	LatLng southwest = new LatLng(0, 0);
	boolean firstUse = true;

	static Builder builder;

	public static LatLngBounds.Builder builder() {
		if (builder == null) {
			builder = new Builder();
		}
		return builder;
	}

	public boolean contains(LatLng point) {
		if (point.latitude <= this.northeast.latitude
				&& point.latitude >= this.southwest.latitude
				&& point.longitude <= this.northeast.longitude
				&& point.longitude >= this.southwest.longitude) {
			return true;
		}
		return false;
	}

	public LatLngBounds including(LatLng point) {
		if (firstUse) {
			this.northeast.latitude = point.latitude;
			this.northeast.longitude = point.longitude;
			this.southwest.latitude = point.latitude;
			this.southwest.longitude = point.longitude;
			firstUse = false;
		} else {
			if (point.latitude > this.northeast.latitude) {
				this.northeast.latitude = point.latitude;
			} else if (point.latitude < this.southwest.latitude) {
				this.southwest.latitude = point.latitude;
			}
			if (point.longitude > this.northeast.longitude) {
				this.northeast.longitude = point.longitude;
			} else if (point.longitude < this.southwest.longitude) {
				this.northeast.longitude = point.longitude;
			}
		}

		return this;
	}

	static public class Builder {
		ArrayList<LatLng> list = new ArrayList<LatLng>();

		public LatLngBounds build() {
			LatLngBounds mLatLngBounds = new LatLngBounds();
			for (LatLng latlng : list) {
				mLatLngBounds.including(latlng);
			}
			return mLatLngBounds;
		}

		public LatLngBounds.Builder including(LatLng point) {
			list.add(point);
			return this;
		}
	};
}
