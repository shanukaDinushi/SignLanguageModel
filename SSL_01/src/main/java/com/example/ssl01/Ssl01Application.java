package com.example.ssl01;

import com.example.ssl01.services.SslApiService;
import org.bytedeco.javacpp.opencv_videoio;
import org.bytedeco.javacpp.presets.opencv_highgui;
import org.bytedeco.javacv.Frame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.bytedeco.javacv.*;
import static org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.CanvasFrame;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

@SpringBootApplication
public class Ssl01Application implements CommandLineRunner{

	@Autowired
	public SslApiService sslApiService;

	public static void main(String[] args) {
		//SpringApplication.run(Ssl01Application.class, args);
//		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0); // Camera Device ID (0 for built in , 1 for external etc)
//		OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
//		IplImage img;
//		int i = 0;
//		System.out.println("aaaaaaaaaa");
//		try {
//			System.out.println("bbbbbbbbbbbbb");
//				grabber.start();
//				Frame frame = grabber.grab();
//				img = converter.convert(frame);
//				//the grabbed frame will be flipped, re-flip to make it right
//				//cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
//				System.out.println("cccccccc");
//				//checks if the webcam has taken the picture and if the picture if mot empty
//				//if (img != null) {
//					//determines where to save the picture
//					cvSaveImage("C:\\Users\\ASUS\\Desktop\\" + i++ + ".jpg", img);
//					System.out.println("dddddddddddd");
//				//}
//				//stops the webcam
//				grabber.stop();
//		} catch (FrameGrabber.Exception ex) {
//			ex.printStackTrace();
//		}

//		opencv_videoio.CvCapture capture = opencv_highgui.cvCreateCameraCapture(0);
//		opencv_videoio.cvSetCaptureProperty(capture, opencv_videoio.CV_CAP_PROP_FRAME_HEIGHT, 1000);
//		opencv_videoio.cvSetCaptureProperty(capture, opencv_videoio.CV_CAP_PROP_FRAME_WIDTH, 1500);
//		IplImage image = opencv_videoio.cvQueryFrame(capture);
//		CanvasFrame frame = new CanvasFrame("Webcam");
//
//		while(frame.isVisible() && image != null){
//			OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
//			frame.showImage(grabberConverter.convert(image));
//		}

		SpringApplicationBuilder builder = new SpringApplicationBuilder(Ssl01Application.class);
		builder.headless(false);
		builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		sslApiService.runGui();
	}
}
