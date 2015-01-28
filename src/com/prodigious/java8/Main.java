package com.prodigious.java8;

import java.io.IOException;

import com.prodigious.java8.config.ConfigReader;
import com.prodigious.java8.exceptions.ImageResizerException;
import com.prodigious.java8.resizer.ImageResizer;


public class Main {

	private static void doRun() throws ImageResizerException, IOException {
		ConfigReader reader = new ConfigReader();
		ImageResizer resizer = new ImageResizer.Builder(reader.getSource(), reader.getDestination())
											   .withWidth(reader.getWidth())
											   .withHeight(reader.getHeight())
											   .build();
		resizer.run();
	}

	public static void main(String[] args) throws IOException {
		try {
			doRun();
		} catch (ImageResizerException e) {
			
		} catch (IOException e) {
			
		}
	}

}
