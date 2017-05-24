package com.gloomyfox.vision.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gloomyfox.vision.util.ImageUtil;

@Service
public class OpenCVProcessingService implements ProcessingService {

	@Autowired
	private ImageUtil imageUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenCVProcessingService.class);
	
	@Override
	public Map<String, Object> convertColor(BufferedImage image, int code) throws IOException {
		Mat mat = imageUtil.bufferedImageToMat(image);

		Imgproc.cvtColor(mat, mat, code);

		BufferedImage bi = imageUtil.matToBufferedImage(mat);
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", 0);
		result.put("cvt", imageUtil.bufferedImageToBase64(bi));
		
		return result;
	}
	
	@Override
	public Map<String, Object> equalizeHist(BufferedImage image) throws IOException {
		Mat mat = imageUtil.bufferedImageToMat(image);
		
		List<Mat> channels = new ArrayList<>();
		Core.split(mat, channels);
		
		for(Mat channel : channels) {
			Imgproc.equalizeHist(channel, channel);
		}
		
		Core.merge(channels, mat);
		
		BufferedImage bi = imageUtil.matToBufferedImage(mat);
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", 0);
		result.put("hist", imageUtil.bufferedImageToBase64(bi));
		
		return result;
	}

	@Override
	public Map<String, Object> binarizeText(BufferedImage image) throws IOException {
		// TODO: Implement Paper that Font and Background Color Independent Text Binarization
		Mat mat = imageUtil.bufferedImageToMat(image);
		
		// 1. Convert Gray Color
		Mat gray = new Mat();
		Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);
		
		// 2. Gaussian Blur
		Mat blur = new Mat();
		Imgproc.GaussianBlur(gray, blur, new Size(5, 5), 0);
		
		// 3. Canny Edge Detection
		Mat edge = new Mat();
		Imgproc.Canny(blur, edge, 0.2, 0.3);
		
		// 4. 8 Connected Component Labeling
		Mat label = new Mat();
		Mat stat = new Mat();
		Mat centroid = new Mat();
		int numOfLabel = Imgproc.connectedComponentsWithStats(edge, label, stat, centroid);
		
		// 5. Edge Box Setting
		for(int i = 0; i < numOfLabel; i++) {
			int area = (int) stat.get(i, Imgproc.CC_STAT_AREA)[0];
			int height = (int) stat.get(i, Imgproc.CC_STAT_HEIGHT)[0];
			int left = (int) stat.get(i, Imgproc.CC_STAT_LEFT)[0];
			int top = (int) stat.get(i, Imgproc.CC_STAT_TOP)[0];
			int width = (int) stat.get(i, Imgproc.CC_STAT_WIDTH)[0];
			
			LOGGER.debug("Label {}'s Area: {}, Height: {}, Left: {}, Top: {}, Width: {}", 
					i, area, height, left, top, width);
			
			Imgproc.rectangle(mat, new Point(left, top), new Point(left+width, top+height), new Scalar(0, 0, 255), 1);
		}
		
		// 6. Edge Box Filtering
		// 7. Get Foreground Intensity(All Edge Pixel's Gray Level Intensity's Mean Value) per Edge Box
		// 8. Get Background Intensity(Box's Corner's 12 Point's Gray Level Intensity's Median Value) per Edge Box
		// 9. Estimate Threshold And Apply Threshold
		
		BufferedImage bi = imageUtil.matToBufferedImage(mat);
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", 0);
		result.put("text", imageUtil.bufferedImageToBase64(bi));
		
		return result;
	}
}
