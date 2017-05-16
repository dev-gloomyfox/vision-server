package com.gloomyfox.vision.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gloomyfox.vision.service.ProcessingService;

@RestController
@RequestMapping("/vision")
public class VisionRestController {
	
	@Autowired
	private ProcessingService processingService;
	
	@RequestMapping(path="/colorcv", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> convertColor(@RequestParam(value="image", required=true) MultipartFile image, int code) {
		return null;
	}
	
	@RequestMapping(path="/histeq", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> equalizeHist(@RequestParam(value="image", required=true) MultipartFile image) throws IOException {
		BufferedImage bi = ImageIO.read(image.getInputStream());
		return processingService.equalizeHist(bi);
	}
	
	@RequestMapping(path="/gammacr", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> correctGamma(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/scale", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> scale(@RequestParam(value="image", required=true) MultipartFile image, double scale) {
		return null;
	}
	
	@RequestMapping(path="/basicbl", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> blurBasic(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/medianbl", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> blurMedian(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/gaussianbl", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> blurGaussian(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/morph", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doMorphology(@RequestParam(value="image", required=true) MultipartFile image, int type) {
		return null;
	}
	
	@RequestMapping(path="/canny", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> detectCannyEdge(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/basicbin", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doBasicBinarization(@RequestParam(value="image", required=true) MultipartFile image, int type) {
		return null;
	}
	
	@RequestMapping(path="/adaptivebin", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdaptiveBinarization(@RequestParam(value="image", required=true) MultipartFile image, int type) {
		return null;
	}
	
	@RequestMapping(path="/template", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> matchTempalte(@RequestParam(value="image", required=true) MultipartFile src, 
			@RequestParam(value="image", required=true) MultipartFile template) {
		return null;
	}
	
	@RequestMapping(path="/sift", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> matchSIFT(@RequestParam(value="image", required=true) MultipartFile src, 
			@RequestParam(value="image", required=true) MultipartFile target) {
		return null;
	}
	
	@RequestMapping(path="/surf", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> matchSURF(@RequestParam(value="image", required=true) MultipartFile src, 
			@RequestParam(value="image", required=true) MultipartFile target) {
		return null;
	}
	
	@RequestMapping(path="/ocr", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doOCR(@RequestParam(value="image", required=true) MultipartFile image, 
			@RequestParam(value="whitelist", required=false) String whitelist) {
		return null;
	}
}
