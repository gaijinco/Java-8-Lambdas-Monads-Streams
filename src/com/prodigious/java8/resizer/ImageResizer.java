package com.prodigious.java8.resizer;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import com.prodigious.java8.exceptions.ImageResizerException;
import com.prodigious.java8.image.Image;
import com.prodigious.java8.image.Images;

/**
 * This class represents the core functionality of an image resizer utility
 * program.
 *
 * The class need 4 arguments: the path of the source directory, the path of the
 * destination directory, and the height and width of the images after resized.
 * 
 * An object of the class can be created using the static class Builder in this
 * way:
 * 
 * ImageResizer resizer = new ImageResizer.Builder(<source>, <destination>)
 * 										  .withHeight(<height>) 
 * 										  .withWidth(<width>) 
 * 										  .build();
 * 
 * Note that while the source and destination are mandatory, width and height
 * are optional, its default values being 640 and 480.
 */
public class ImageResizer {

	private final Path source;
	private final Path destination;
	private final int width;
	private final int height;

	private ImageResizer(Builder builder) throws ImageResizerException {
		if (!Files.exists(builder.source)) {
			throw new ImageResizerException("source.do.not.exit");
		}
		this.source = builder.source;
		this.destination = builder.destination;
		this.width = builder.width;
		this.height = builder.height;
	}

	public void run() throws IOException {
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(source)) {
			for (Path path : directoryStream) {
				if (Images.isImage(path)) {
					Image image = new Image(path);
					if (image.isProportional(width, height)) {
						image = image.resize(width, height);
						image.write(destination);
					}
				}
			}
		}
	}
	
	public static class Builder {

		private Path source;
		private Path destination;
		private int width = 640;
		private int height = 480;

		/**
		 * Create a builder with the specified source directory and destination
		 * directory.
		 * 
		 * If you don't use any of the setters methods of the class the default
		 * values for width and height will be used: 600 and 480 respectively.
		 * 
		 * @param source
		 * @param destination
		 */
		public Builder(Path source, Path destination) {
			this.source = source;
			this.destination = destination;
		}

		/**
		 * Setter for height attribute.
		 * 
		 * @param height
		 * @return this object, so that method can be chained.
		 */
		public Builder withHeight(int height) {
			this.height = height;
			return this;
		}

		/**
		 * Setter for width attribute.
		 * 
		 * @param width
		 * @return this object, so that method can be chained.
		 */
		public Builder withWidth(int width) {
			this.width = width;
			return this;
		}

		/**
		 * Construct an ImageResizer with the parameters used in the constructor
		 * and the optional setters methods.
		 * 
		 * @return an ImageResizer as explained above.
		 */
		public ImageResizer build() throws ImageResizerException {
			return new ImageResizer(this);
		}
	}
}
