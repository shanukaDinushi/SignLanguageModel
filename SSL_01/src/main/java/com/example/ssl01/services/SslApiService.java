package com.example.ssl01.services;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

@Service
public class SslApiService {
    private JPanel mainContainer;
    private JPanel leftHandImageContainer;
    private JPanel rightLetterImageContainer;
    private JPanel buttonContainer;
    private JButton openCamera;
    private JButton takePhoto;
    private JButton close;
    private JLabel handImage;
    private JLabel letterImage;
    CanvasFrame canvas;


    public SslApiService() {
        //Create canvas frame for displaying webcam.

        openCamera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Create canvas frame for displaying webcam.
                canvas = new CanvasFrame("Webcam");
                //Set Canvas frame to close on exit
                canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                //Declare FrameGrabber to import output from webcam
                FrameGrabber grabber = new OpenCVFrameGrabber(0);
                try {
                    //Start grabber to capture video
                    grabber.start();
                    //Declare img as IplImage
                    Frame img;
                    while (true) {
                        //insert grabed video fram to IplImage img
                        OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
                        img = grabber.grab();
                        //Set canvas size as per dimentions of video frame.
                        canvas.setCanvasSize(grabber.getImageWidth(), grabber.getImageHeight());

                        if (img != null) {
                            //Flip image horizontally
                            //cvFlip(img, img, 1);
                            //Show video frame in canvas
                            canvas.showImage(img);
                            JButton jButton = new JButton("OK");
                            canvas.add(jButton);
                        }
                    }
                }
                catch (Exception e1) {
                }
            }
        });

        takePhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.dispose();
                OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0); // Camera Device ID (0 for built in , 1 for external etc)
		        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
		opencv_core.IplImage img;
		int i = 0;
		System.out.println("aaaaaaaaaa");
		try {
			System.out.println("bbbbbbbbbbbbb");
				grabber.start();
				Frame frame = grabber.grab();
				img = converter.convert(frame);
				//the grabbed frame will be flipped, re-flip to make it right
				//cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
				System.out.println("cccccccc");
				//checks if the webcam has taken the picture and if the picture if mot empty
				//if (img != null) {
					//determines where to save the picture
					cvSaveImage("C:\\Users\\ASUS\\Desktop\\" + i++ + ".jpg", img);
					System.out.println("dddddddddddd");
				//}
				//stops the webcam
				grabber.stop();
		} catch (FrameGrabber.Exception ex) {
			ex.printStackTrace();
		}
            }
        });
    }

    public void runGui() {
        JFrame frame = new JFrame("SSL Application");
        frame.setContentPane(new SslApiService().mainContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
