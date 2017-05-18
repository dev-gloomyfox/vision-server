package com.gloomyfox.vision.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gloomyfox.vision.util.ImageUtil;

@Service
public class OpenCVProcessingService implements ProcessingService {

	@Autowired
	private ImageUtil imageUtil;
	
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
}
