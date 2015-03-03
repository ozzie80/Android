package com.example.gallery.ui.placement;

import java.util.List;

import com.example.gallery.model.GenericImage;

public interface ImageGridPlacement {

	List<List<GenericImage>> execute(PlacementParams params);

}
