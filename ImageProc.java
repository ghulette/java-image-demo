/* 
Demonstrates using ImageIO to load and save an image, as well as various image
routines to process and modify the image in memory.
*/

import java.io.File;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class ImageProc {
  
  // just put everything in main
	public static void main(String[] args) {
	  
		try {
			String format = "jpeg";
			File inputFile = new File("turtle.jpg");
			File outputFile = new File("output.jpg");

			// read the file
			BufferedImage image = ImageIO.read(inputFile);
			
			// store width and height
			int w = image.getWidth();
			int h = image.getHeight();
			
			// convert to greyscale
			BufferedImage greyImage;
			greyImage = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
			greyImage.getGraphics().drawImage(image, 0, 0, null);
			
			// mess with the raw pixels
			double[] pixels;
			pixels = greyImage.getData().getPixels(0, 0, w, h, (double [])null);
			
			// fill in a black rectangle in the upper left corner
			for(int x=0; x < 50; x++) {
				for(int y=0; y < 50; y++) {
				  int offset = y * greyImage.getWidth() + x;
					pixels[offset] = 0;
				}
			}
			greyImage.getRaster().setPixels(0, 0, w, h, pixels);
			
			// write the file
			ImageIO.write(greyImage, format, outputFile);
		}
		
		catch(Exception ex) {
      // bad!
			ex.printStackTrace();
		}
	}
}
