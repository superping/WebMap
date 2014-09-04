package com.xair.webmap;

public class UISettings {

	boolean CompassEnabled;

	// 获取指南针状态。
	public boolean isCompassEnabled() {
		return CompassEnabled;
	}

	// 指南针可用不可用。
	public void setCompassEnabled(boolean enabled) {
		this.CompassEnabled = enabled;
	}

	boolean RotateGesturesEnabled;

	// 返回当前地图是否允许通过手势旋转地图。
	public boolean isRotateGesturesEnabled() {
		return RotateGesturesEnabled;
	}

	// 这个方法设置了地图是否允许通过手势来旋转。
	public void setRotateGesturesEnabled(boolean enabled) {
		RotateGesturesEnabled = enabled;
	}

	boolean ScaleControlsEnabled;

	// 返回比例尺功能是否可用。
	public boolean isScaleControlsEnabled() {
		return ScaleControlsEnabled;
	}

	// 设置比例尺功能是否可用
	public void setScaleControlsEnabled(boolean enabled) {
		ScaleControlsEnabled = enabled;
	}

	boolean ScrollGesturesEnabled;

	// 返回当前地图是否允许通过手势移动地图。
	public boolean isScrollGesturesEnabled() {
		return ScrollGesturesEnabled;
	}

	// 这个方法设置了地图是否允许通过手势来移动。
	public void setScrollGesturesEnabled(boolean enabled) {
		ScrollGesturesEnabled = enabled;
	}

	boolean TiltGesturesEnabled;

	// 返回当前地图是否允许通过手势倾斜地图。
	public boolean isTiltGesturesEnabled() {
		return TiltGesturesEnabled;
	}

	// 这个方法设置了地图是否允许通过手势来倾斜。
	public void setTiltGesturesEnabled(boolean enabled) {
		TiltGesturesEnabled = enabled;
	}

	boolean ZoomControlsEnabled;

	// 返回当前地图是否显示了缩放按钮。
	public boolean isZoomControlsEnabled() {
		return ZoomControlsEnabled;
	}

	// 这个方法设置了地图是否允许显示缩放按钮。
	public void setZoomControlsEnabled(boolean enabled) {
		ZoomControlsEnabled = enabled;
	}

	boolean ZoomGesturesEnabled;

	// 返回当前地图是否允许通过手势缩放地图。
	public boolean isZoomGesturesEnabled() {
		return ZoomGesturesEnabled;
	}

	// 这个方法设置了地图是否允许通过手势来缩放。
	public void setZoomGesturesEnabled(boolean enabled) {
		ZoomGesturesEnabled = enabled;
	}

}
