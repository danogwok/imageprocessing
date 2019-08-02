import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.RenderingHints;

public class ImageProcessor{
	
	public static void main(String[] args){
		String path = args[0];
		ImageIcon  newImage = new ImageIcon(path);
		//Image theImage = newImage.getImage().getScaledInstance(origImage.getWidth(), origImage.getHeight(), Image.SCALE_SMOOTH);
		BufferedImage newBuffImage1 = new BufferedImage(newImage.getIconWidth(), newImage.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = newBuffImage1.createGraphics();
		newImage.paintIcon(null, g, 0, 0);
		g.dispose();
		BufferedImage newColors = new BufferedImage(newBuffImage1.getWidth(), newBuffImage1.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < newColors.getWidth(); i++){
			for(int j = 0; j < newColors.getHeight(); j++){
				//System.out.println(newBuffImage1.getRGB(i, j));
				int color = newBuffImage1.getRGB(i, j);
				Color black = new Color(0,0,0);
				Color white  = new Color(255,255,255);
				if(color == black.getRGB()){
					newColors.setRGB(i, j, black.getRGB());
				}else{
					newColors.setRGB(i, j, white.getRGB());
				}
			}
		}
		
		BufferedImage newSize = resizeImage(newColors);
		try{
			ImageIO.write(newSize, "png", new File("new.png"));
		}catch(Exception e){
			System.out.println("Cannot write file");
		}
		
		//Sigmoid Values in range
		sigmoidValues(55,68);
	}
	
	private static BufferedImage resizeImage(BufferedImage image){
		int intSize = 25;
		Image tempImage = image.getScaledInstance(intSize, intSize, Image.SCALE_SMOOTH);
		BufferedImage newImage = new BufferedImage(intSize, intSize, BufferedImage.TYPE_INT_RGB);	//TYPE_INT_ARGB , ARGB because of support for transparent images
		Graphics2D g = newImage.createGraphics();
		//AffineTransform newForm = AffineTransform.getScaleInstance(scale, scale);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		//g.drawImage(image, 0, 0, intSize, intSize, null);
		//g.drawImage(image, newForm, null);
		g.drawImage(tempImage, 0, 0, null);
		g.dispose();
		return newImage;
	}
	
	
	private static void sigmoidValues(int intStart, int intEnd){
		for(int i = intStart; i < intEnd; i++){
			//System.out.println((double)(1/(1+Math.pow(Math.E, -i))));	//Cast to double
			System.out.println(1/(1+Math.pow(Math.E, -i)));	
		}
	}
	
}
