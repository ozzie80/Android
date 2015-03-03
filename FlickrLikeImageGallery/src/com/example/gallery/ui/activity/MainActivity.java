package com.example.gallery.ui.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.avg.gallery.R;
import com.example.gallery.flickr.RequestHandler;
import com.example.gallery.model.GenericImage;
import com.example.gallery.ui.placement.ImageGridPlacement;
import com.example.gallery.ui.placement.ImageGridPlacementImpl;
import com.example.gallery.ui.placement.PlacementParams;
import com.example.gallery.ui.task.DownloadImageTask;

public class MainActivity extends Activity {

	private TableLayout tableLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tableLayout = (TableLayout) findViewById(R.id.tableView);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		List<GenericImage> images = new RequestHandler().getImages(this);
		resizeImages(images, metrics.density);
		displayImages(images, metrics);
	}

	private void displayImages(List<GenericImage> images, DisplayMetrics metrics) {

		PlacementParams params = new PlacementParams();
		params.setImages(images);
		params.setScreenHeight(metrics.heightPixels);
		params.setScreenWidth(metrics.widthPixels);
		params.setMaxHeight(metrics.heightPixels);
		params.setMinHeight(50);

		ImageGridPlacement placementAlg = new ImageGridPlacementImpl();
		List<List<GenericImage>> imageGrid = placementAlg.execute(params);

		for (List<GenericImage> row : imageGrid) {
			addRow(row, metrics);
		}

		tableLayout.requestLayout();
		tableLayout.refreshDrawableState();
	}

	private void addRow(List<GenericImage> images, DisplayMetrics metrics) {

		TableRow outerRow = (TableRow) LayoutInflater.from(this).inflate(
				R.layout.row_item1, null);
		TableLayout innerTable = (TableLayout) outerRow
				.findViewById(R.id.innerTableView);

		TableRow row = new TableRow(this);
		for (int i = 0; i < images.size(); i++) {
			ImageView imgView = new ImageView(this);
			GenericImage image = images.get(i);
			imgView.setScaleType(ScaleType.FIT_XY);
			imgView.setPadding(0, 0, 2, 2);
			new DownloadImageTask(imgView, image.getWidth(), image.getHeight())
					.execute(image.getUrl());

			row.addView(imgView);
		}

		row.setMinimumWidth(metrics.widthPixels);
		row.refreshDrawableState();
		innerTable.addView(row);
		tableLayout.addView(outerRow);

	}

	public void resizeImages(List<GenericImage> images, float density) {
		int newWidth, newHeight;
		for (GenericImage genericImage : images) {
			newWidth = (int) (genericImage.getWidth() / density);
			newHeight = (int) (genericImage.getHeight() / density);
			genericImage.setWidth(newWidth);
			genericImage.setHeight(newHeight);
		}
	}

}
