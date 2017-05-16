package com.gloomyfox.vision.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MatUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(MatUtil.class);
	/**
	 * Convert BufferedImage Object to Mat Object
	 * @param image
	 * @return
	 */
	public Mat bufferedImageToMat(BufferedImage image) {
		int cvType = CvType.CV_8UC3;
		
		switch(image.getType()) {
		case BufferedImage.TYPE_3BYTE_BGR:
			cvType = CvType.CV_8UC3;
			break;
		case BufferedImage.TYPE_4BYTE_ABGR:
		case BufferedImage.TYPE_4BYTE_ABGR_PRE:
			cvType = CvType.CV_8UC4;
			break;
		case BufferedImage.TYPE_BYTE_BINARY:
		case BufferedImage.TYPE_BYTE_GRAY:
			cvType = CvType.CV_8UC1;
			break;
		case BufferedImage.TYPE_INT_ARGB:
		case BufferedImage.TYPE_INT_ARGB_PRE:
			cvType = CvType.CV_32SC4;
			break;
		case BufferedImage.TYPE_INT_BGR:
		case BufferedImage.TYPE_INT_RGB:
			cvType = CvType.CV_32SC3;
			break;
		case BufferedImage.TYPE_USHORT_GRAY:
			cvType = CvType.CV_16UC1;
			break;
		default:
			LOGGER.info("Unsupported format: {}.", image.getType());
			return null;
		}
		
		Mat mat = new Mat(image.getHeight(), image.getWidth(), cvType);
		byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		mat.put(0, 0, data);
		
		return mat;
	}
	
	/**
	 * Convert Mat Object to BufferedImage Object
	 * @param mat
	 * @return
	 */
	public BufferedImage matToBufferedImage(Mat mat) {
		int cols = mat.cols();
		int rows = mat.rows();
		int elemSize = (int) mat.elemSize();
		byte[] data = new byte[cols * rows * elemSize];
		int type;
		mat.get(0, 0, data);
		//TODO: Configuring switch statements for mat channels
		switch (mat.channels()) {
		case 1:
			type = BufferedImage.TYPE_BYTE_GRAY;
			break;
		case 3:
			type = BufferedImage.TYPE_3BYTE_BGR;
			byte b;
			for (int i = 0; i < data.length; i = i + 3) {
				b = data[i];
				data[i] = data[i + 2];
				data[i + 2] = b;
			}
			break;
		default:
			LOGGER.info("Unsupported channels: {}", mat.channels());
			return null;
		}

		BufferedImage image = new BufferedImage(cols, rows, type);
		image.getRaster().setDataElements(0, 0, cols, rows, data);
		return image;
	}
}
