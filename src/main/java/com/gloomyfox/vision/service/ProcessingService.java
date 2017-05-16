package com.gloomyfox.vision.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

public interface ProcessingService {
	Map<String, Object> equalizeHist(BufferedImage image) throws IOException;
}
