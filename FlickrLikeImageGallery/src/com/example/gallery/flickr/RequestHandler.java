package com.example.gallery.flickr;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.avg.gallery.R;
import com.example.gallery.model.GenericImage;
import com.example.gallery.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class RequestHandler {

	public List<GenericImage> getImages(Context context) {
		// The following should be replaced with an API call from an async. task
		String json = FileUtils.readRawTextFile(context, R.raw.flickr);

		QueryResponse response = getObjectFromJson(json,
				QueryResponse.class);

		List<GenericImage> images = new ArrayList<GenericImage>();
		for (FlickrImage flickrImage : response.getImages()) {
			GenericImage genericImage = new GenericImage();
			genericImage.setHeight(flickrImage.getHeight());
			genericImage.setWidth(flickrImage.getWidth());
			genericImage.setUrl(flickrImage.getImageURL());
			images.add(genericImage);
		}

		return images;
	}

	public <T> T getObjectFromJson(String response, Class<T> clazzOfT) {
		T object = null;
		Gson gson = new Gson();

		try {
			object = (T) gson.fromJson(response, clazzOfT);
		} catch (JsonSyntaxException e) {
			// Logger.getLogger(ServiceUtils.class.getName()).log(Level.SEVERE,
			// "Json cannot be parsed.", e);
		}

		return object;
	}

}
