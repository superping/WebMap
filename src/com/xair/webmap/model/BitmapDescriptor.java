package com.xair.webmap.model;

public class BitmapDescriptor {

	private int height;
	private int width;
	private String path;

	protected BitmapDescriptor(String path, int height, int width) {
		this.path = path;
		this.height = height;
		this.width = width;
	}

	@Override
	public String toString() {
		return path;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
