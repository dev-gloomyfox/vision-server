package com.gloomyfox.vision.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gloomyfox.vision.util.MatUtil;

@Service
public class OpenCVProcessingService implements ProcessingService {

	@Autowired
	private MatUtil matUtil;
	
	@Override
	public Map<String, Object> equalizeHist(BufferedImage image) throws IOException {
		Mat mat = matUtil.bufferedImageToMat(image);
		
		List<Mat> channels = new ArrayList<>();
		Core.split(mat, channels);
		
		for(Mat channel : channels) {
			Imgproc.equalizeHist(channel, channel);
		}
		
		Core.merge(channels, mat);
		
		BufferedImage bi = matUtil.matToBufferedImage(mat);
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", 0);
		result.put("hist", bufferedImageToBase64(bi));
		
		return result;
	}
	
	private String bufferedImageToBase64(BufferedImage bi) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(bi, "png", os);
		byte[] bytes = os.toByteArray();
		
		String base64 = Base64.getEncoder().encodeToString(bytes);
		return base64;
	}
}
