package com.prodigious.java8.image;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Utility class for the Image class.
 *
 */
public final class Images {

	private Images() {
		throw new AssertionError("This class should never be instantiated");
	}

	/**
	 * Given a path, returns true if the path correspond to an image.
	 * 
	 * This implementation accepts jpg, gif and png as image formats.
	 * @param path
	 * @throws IllegalArgumentException if path is null
	 * @return
	 */
	public static boolean isImage(Path path) {
		Objects.requireNonNull(path);
		switch(getExtension(path)) {
		case "jpg":
		case "JPG":
		case "gif":
		case "GIF":
		case "png":
		case "PNG":
			return true;
		default:
			return false;
		}
	}

	/**
	 * Returns the extension of a path.
	 * 
	 * Specifically it would return the String after
	 * the last dot on the path, an empty String if there
	 * is no dot.
	 * @param path
	 * @return
	 */
	public static String getExtension(Path path) {
		Objects.requireNonNull(path);
		String filpath = path.toString();
		int indexOfLastDot = filpath.lastIndexOf('.');
		if (indexOfLastDot == -1) {
			return "";
		} else {
			String extension = filpath.substring(indexOfLastDot + 1);
			return extension;
		}
	}
}
