package com.gloomyfox.vision.controller;

import java.awt.image.BufferedImage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gloomyfox.vision.domain.MatchingResult;

@RestController
@RequestMapping("/vision")
public class VisionRestController {
	
	@RequestMapping(path="/colorcv")
	@ResponseBody
	public BufferedImage convertColor(@RequestParam(value="image", required=true) MultipartFile image, int code) {
		return null;
	}
	
	@RequestMapping(path="/histeq")
	@ResponseBody
	public BufferedImage equalizeHist(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/gammacr")
	@ResponseBody
	public BufferedImage correctGamma(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/scale")
	@ResponseBody
	public BufferedImage scale(@RequestParam(value="image", required=true) MultipartFile image, double scale) {
		return null;
	}
	
	@RequestMapping(path="/basicbl")
	@ResponseBody
	public BufferedImage blurBasic(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/medianbl")
	@ResponseBody
	public BufferedImage blurMedian(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/gaussianbl")
	@ResponseBody
	public BufferedImage blurGaussian(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/morph")
	@ResponseBody
	public BufferedImage doMorphology(@RequestParam(value="image", required=true) MultipartFile image, int type) {
		return null;
	}
	
	@RequestMapping(path="/canny")
	@ResponseBody
	public BufferedImage detectCannyEdge(@RequestParam(value="image", required=true) MultipartFile image) {
		return null;
	}
	
	@RequestMapping(path="/basicbin")
	@ResponseBody
	public BufferedImage doBasicBinarization(@RequestParam(value="image", required=true) MultipartFile image, int type) {
		return null;
	}
	
	@RequestMapping(path="/adaptivebin")
	@ResponseBody
	public BufferedImage doAdaptiveBinarization(@RequestParam(value="image", required=true) MultipartFile image, int type) {
		return null;
	}
	
	@RequestMapping(path="/template")
	@ResponseBody
	public MatchingResult matchTempalte(@RequestParam(value="image", required=true) MultipartFile src, 
			@RequestParam(value="image", required=true) MultipartFile template) {
		return null;
	}
	
	@RequestMapping(path="/sift")
	@ResponseBody
	public MatchingResult matchSIFT(@RequestParam(value="image", required=true) MultipartFile src, 
			@RequestParam(value="image", required=true) MultipartFile target) {
		return null;
	}
	
	@RequestMapping(path="/surf")
	@ResponseBody
	public MatchingResult matchSURF(@RequestParam(value="image", required=true) MultipartFile src, 
			@RequestParam(value="image", required=true) MultipartFile target) {
		return null;
	}
	
	@RequestMapping(path="/ocr", method=RequestMethod.POST)
	@ResponseBody
	public String doOCR(@RequestParam(value="image", required=true) MultipartFile image, 
			@RequestParam(value="whitelist", required=false) String whitelist) {
		return null;
	}
}
