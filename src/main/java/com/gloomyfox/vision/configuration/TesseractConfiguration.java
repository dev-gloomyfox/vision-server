package com.gloomyfox.vision.configuration;

import java.io.File;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TesseractConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(TesseractConfiguration.class);
	
	@Value("${tess.data}")
	private String tessDataDir;
	
	@PostConstruct
	private void init() {
		checkTesseractData();
	}
	
	private void checkTesseractData() {
		String tessDataFullPath = System.getProperty("user.dir") + tessDataDir;
		LOGGER.debug("Check a Tesseract data set {}.", tessDataFullPath);
		File tessDataFile = new File(tessDataFullPath);
		if(!tessDataFile.exists()) {
			throw new Error("There is no tesseract data at " + tessDataFullPath);
		} else {
			LOGGER.info("Tesseract Data exists at {}.", tessDataFullPath);
		}
	}
}
