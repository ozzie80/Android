package com.example.gallery.ui.task;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	private ImageView imgView;
	private int width;
	private int height;

	// better if moved to application context
	private static LRUMap<String, Bitmap> cache = new LRUMap<String, Bitmap>(
			50, 100);

	public static void cleanCache() {
		cache.clear();
	}

	public DownloadImageTask(ImageView imgView, int width, int height) {
		this.imgView = imgView;
		this.width = width;
		this.height = height;
	}

	protected Bitmap doInBackground(String... urls) {
		String imageURL = urls[0];
		Bitmap bitmap = null;
		try {
			if (cache.containsKey(imageURL)) {
				bitmap = cache.get(imageURL);
			} else {
				InputStream in = new java.net.URL(imageURL).openStream();
				bitmap = BitmapFactory.decodeStream(in);
				cache.put(imageURL, bitmap);
			}
			bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
		} catch (Exception e) {
			// Logger.getLogger(DownloadImageTask.class.toString()).log(
			// Level.WARNING, e.toString());
		}
		return bitmap;
	}

	protected void onPostExecute(Bitmap result) {
		imgView.setImageBitmap(result);
		imgView.refreshDrawableState();
	}
}
