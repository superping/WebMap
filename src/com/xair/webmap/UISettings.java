package com.xair.webmap;

public class UISettings {

	boolean CompassEnabled;

	// ��ȡָ����״̬��
	public boolean isCompassEnabled() {
		return CompassEnabled;
	}

	// ָ������ò����á�
	public void setCompassEnabled(boolean enabled) {
		this.CompassEnabled = enabled;
	}

	boolean RotateGesturesEnabled;

	// ���ص�ǰ��ͼ�Ƿ�����ͨ��������ת��ͼ��
	public boolean isRotateGesturesEnabled() {
		return RotateGesturesEnabled;
	}

	// ������������˵�ͼ�Ƿ�����ͨ����������ת��
	public void setRotateGesturesEnabled(boolean enabled) {
		RotateGesturesEnabled = enabled;
	}

	boolean ScaleControlsEnabled;

	// ���ر����߹����Ƿ���á�
	public boolean isScaleControlsEnabled() {
		return ScaleControlsEnabled;
	}

	// ���ñ����߹����Ƿ����
	public void setScaleControlsEnabled(boolean enabled) {
		ScaleControlsEnabled = enabled;
	}

	boolean ScrollGesturesEnabled;

	// ���ص�ǰ��ͼ�Ƿ�����ͨ�������ƶ���ͼ��
	public boolean isScrollGesturesEnabled() {
		return ScrollGesturesEnabled;
	}

	// ������������˵�ͼ�Ƿ�����ͨ���������ƶ���
	public void setScrollGesturesEnabled(boolean enabled) {
		ScrollGesturesEnabled = enabled;
	}

	boolean TiltGesturesEnabled;

	// ���ص�ǰ��ͼ�Ƿ�����ͨ��������б��ͼ��
	public boolean isTiltGesturesEnabled() {
		return TiltGesturesEnabled;
	}

	// ������������˵�ͼ�Ƿ�����ͨ����������б��
	public void setTiltGesturesEnabled(boolean enabled) {
		TiltGesturesEnabled = enabled;
	}

	boolean ZoomControlsEnabled;

	// ���ص�ǰ��ͼ�Ƿ���ʾ�����Ű�ť��
	public boolean isZoomControlsEnabled() {
		return ZoomControlsEnabled;
	}

	// ������������˵�ͼ�Ƿ�������ʾ���Ű�ť��
	public void setZoomControlsEnabled(boolean enabled) {
		ZoomControlsEnabled = enabled;
	}

	boolean ZoomGesturesEnabled;

	// ���ص�ǰ��ͼ�Ƿ�����ͨ���������ŵ�ͼ��
	public boolean isZoomGesturesEnabled() {
		return ZoomGesturesEnabled;
	}

	// ������������˵�ͼ�Ƿ�����ͨ�����������š�
	public void setZoomGesturesEnabled(boolean enabled) {
		ZoomGesturesEnabled = enabled;
	}

}
