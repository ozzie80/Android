package com.example.gallery.ui.placement;

import java.util.ArrayList;
import java.util.List;

import com.example.gallery.model.GenericImage;

public class ImageGridPlacementImpl implements ImageGridPlacement {

	private PlacementParams params;

	public List<List<GenericImage>> execute(PlacementParams params) {
		this.params = params;
		return placeImagesToGrid(assignImagesToRows());
	}

	private List<List<GenericImage>> assignImagesToRows() {
		List<List<GenericImage>> imageGrid = new ArrayList<List<GenericImage>>();

		int totalWidth = 0;
		int index = 0;
		GenericImage image = null;
		List<GenericImage> images = params.getImages();
		List<GenericImage> row = new ArrayList<GenericImage>();

		while (index < images.size()) {
			image = images.get(index);

			if (image.getWidth() + totalWidth > params.getScreenWidth()) {
				// the width of the first image may be larger than that of the
				// screen, hence the list might be empty.
				if (row.size() > 0) {
					imageGrid.add(row);
				}
				totalWidth = 0;
				row = new ArrayList<GenericImage>();
			}

			row.add(image);
			totalWidth += image.getWidth();
			index++;
		}

		return imageGrid;
	}

	private List<List<GenericImage>> placeImagesToGrid(
			List<List<GenericImage>> imageGrid) {
		for (List<GenericImage> row : imageGrid) {
			int minHeight = getMinHeight(row);
			scaleToMinHeight(row, minHeight);
		}

		return scaleToScreenWidth(imageGrid);
	}

	private int getMinHeight(List<GenericImage> row) {
		int minHeight = params.getScreenHeight();
		for (GenericImage image : row) {
			minHeight = Math.min(image.getHeight(), minHeight);
		}

		return Math.min(Math.max(minHeight, params.getMinHeight()),
				params.getMaxHeight());
	}

	private void scaleToMinHeight(List<GenericImage> row, int newHeight) {
		for (GenericImage image : row) {
			float scaleFactor = (float) newHeight / image.getHeight();
			int newWidth = (int) (image.getWidth() * scaleFactor);
			image.setHeight(newHeight);
			image.setWidth(newWidth);
		}
	}

	private List<List<GenericImage>> scaleToScreenWidth(
			List<List<GenericImage>> imageGrid) {
		for (List<GenericImage> row : imageGrid) {
			int total = getTotalWidth(row);
			float scaleFactor = (float) params.getScreenWidth() / total;
			scaleImages(row, scaleFactor);
		}

		return imageGrid;
	}

	private void scaleImages(List<GenericImage> row, float scaleFactor) {
		for (GenericImage image : row) {
			image.setHeight((int) (scaleFactor * image.getHeight()));
			image.setWidth((int) (scaleFactor * image.getWidth()));
		}
	}

	private int getTotalWidth(List<GenericImage> row) {
		int totalWidth = 0;
		for (GenericImage image : row) {
			totalWidth += image.getWidth();
		}

		return totalWidth;
	}

}
