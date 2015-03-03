package com.example.gallery.flickr;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class QueryResponse {

	@SerializedName("query")
	private Query query;

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public List<FlickrImage> getImages() {
		return this.query.getResult().getImages();
	}

	public int getCount() {
		return this.query.getCount();
	}
}

class Query {

	@SerializedName("count")
	private int count;

	@SerializedName("results")
	private Result result;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}

class Result {

	@SerializedName("photo")
	private List<FlickrImage> images;

	public List<FlickrImage> getImages() {
		return images;
	}

	public void setImages(List<FlickrImage> images) {
		this.images = images;
	}

}
