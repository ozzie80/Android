package com.example.gallery.flickr;

import com.google.gson.annotations.SerializedName;

public class FlickrImage {

	@SerializedName("title")
	private String title;

	@SerializedName("url_n")
	private String imageURL;

	@SerializedName("height_n")
	private int height;

	@SerializedName("width_n")
	private int width;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
