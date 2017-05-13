package com.gloomyfox.vision.configuration;

import javax.annotation.PostConstruct;

import org.opencv.core.Core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenCVConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenCVConfiguration.class);
	
	@PostConstruct
	private void init() {
		loadOpenCv();
	}
	
	private void loadOpenCv() {
		LOGGER.debug("Load OpenCV libarary {}.", Core.NATIVE_LIBRARY_NAME);
		try {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			LOGGER.info("OpenCV has been successfully loaded.");
		} catch (Exception e) {
			LOGGER.error("OpenCV failed to load.");
			LOGGER.error("Check the OpenCV library setting.");
			throw e;
		}
	}
}
