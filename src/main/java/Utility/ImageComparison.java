package Utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageComparison {
	
	public static void main(String args[]) {
		String p1 = "/Users/karankumaragarwal/Downloads/karan2022_03_02_09_23_11.jpg";
		String p2 = "/Users/karankumaragarwal/Desktop/sim.png";
		System.out.println(ImageComparison.comapareImages(p1,p2,false));
	}

	public static double comapareImages(String image1, String image2, Boolean iscomapreheightwidth) {
		BufferedImage imgA = null;
		BufferedImage imgB = null;
		try {
			imgA = ImageIO.read(new File(image1));
			imgB = ImageIO.read(new File(image2));
		}
		catch (IOException e) {
			System.out.println(e);
			return 100;
		}
		int width1 = imgA.getWidth();
		int width2 = imgB.getWidth();
		int height1 = imgA.getHeight();
		int height2 = imgB.getHeight();

		// Checking images size
		if(iscomapreheightwidth) {
			if ((width1 != width2) || (height1 != height2)) {
				System.out.println("Error: Images dimensions mismatch.");
				return -1;
			}
		}

		long difference = 0;
		for (int y = 0; y < height1; y++) {
			for (int x = 0; x < width1; x++) {

				int rgbA = imgA.getRGB(x, y);
				int rgbB = imgB.getRGB(x, y);
				int redA = (rgbA >> 16) & 0xff;
				int greenA = (rgbA >> 8) & 0xff;
				int blueA = (rgbA)&0xff;
				int redB = (rgbB >> 16) & 0xff;
				int greenB = (rgbB >> 8) & 0xff;
				int blueB = (rgbB)&0xff;

				difference += Math.abs(redA - redB);
				difference += Math.abs(greenA - greenB);
				difference += Math.abs(blueA - blueB);
			}
		}
		// So total number of pixels = width * height * 3
		double total_pixels = width1 * height1 * 3;

		// Normalizing the value of different pixels for accuracy

		// Note: Average pixels per color component
		double avg_different_pixels = difference / total_pixels;

		// There are 255 values of pixels in total
		double percentage = (avg_different_pixels / 255) * 100;
		System.out.println("Difference Percentage in images:" + percentage +"%");
		return percentage;

	}

}
