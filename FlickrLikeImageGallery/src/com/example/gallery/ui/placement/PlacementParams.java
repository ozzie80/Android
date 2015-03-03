package com.example.gallery.ui.placement;

import java.util.List;

import com.example.gallery.model.GenericImage;

public class PlacementParams {

	private int screenWidth;
	private int screenHeight;
	private int minHeight;
	private int maxHeight;

	private List<GenericImage> images;

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public List<GenericImage> getImages() {
		return images;
	}

	public void setImages(List<GenericImage> images) {
		this.images = images;
	}

}
