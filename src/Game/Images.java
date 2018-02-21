package Game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;






public class Images implements Serializable{
	public static BufferedImage load(String imageName)
	{
		BufferedImage image;
		try
		{
			image = ImageIO.read(Images.class.getResourceAsStream(imageName));
			BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = img.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			image = img;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
		return image;
	}
	public static boolean pixelsAreSameColor(Color p1, Color p2, float thresh){
		double p1Total = p1.getRed()+p1.getGreen()+p1.getBlue();
		double p2Total = p2.getRed()+p2.getGreen()+p2.getBlue();
		if(p1Total==0 || p2Total==0){
			return true;
		}
		if(Math.abs(((double)p1.getRed()/p1Total) - ((double)p2.getRed()/p2Total)) <= thresh){
			//System.out.println("SADF "+Math.abs(((double)p1.getRed()/p1Total) - ((double)p2.getRed()/p2Total)));
			if(Math.abs(((double)p1.getGreen()/p1Total) - ((double)p2.getGreen()/p2Total)) <= thresh){
				if(Math.abs(((double)p1.getBlue()/p1Total) - ((double)p2.getBlue()/p2Total)) <= thresh){
					return true;
				}
			}
		}
		//		//red
		//		if(Math.abs(p1.getRed()-p2.getRed())<=thresh){
		//			//green
		//			if(Math.abs(p1.getGreen()-p2.getGreen())<=thresh){
		//				//blue
		//				if(Math.abs(p1.getBlue()-p2.getBlue())<=thresh){
		//					return true;
		//				}
		//			}
		//		}
		return false;
	}
	public static ArrayList<Integer> getInvalidColors(BufferedImage normal, BufferedImage shiny){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i<normal.getWidth(); i++){
			for(int j = 0; j<normal.getHeight(); j++){
				if(normal.getRGB(i, j)==shiny.getRGB(i, j))
					result.add(normal.getRGB(i, j));
			}
		}

		//remove duplicates
		Set<Integer> res = new HashSet<Integer>();
		res.addAll(result);
		result.clear();
		result.addAll(res);

		//return the result
		return result;
	}
	public static boolean colorIsInvalid(Color color, ArrayList<Integer> invalidColors){
		for(int i = 0; i<invalidColors.size(); i++){
			if(pixelsAreSameColor(color, new Color(invalidColors.get(i)), .20f)){
				return true;
			}
		}
		return false;
	}
	public static Color randomColor(ArrayList<Integer> invalidColors){
		int min = 55;
		Color randColor = new Color(GamePanel.randomNumber(min, 255, false), GamePanel.randomNumber(min, 255, false), GamePanel.randomNumber(min, 255, false));
		int tries = 0;
		while(colorIsInvalid(randColor, invalidColors)){
			randColor = new Color(GamePanel.randomNumber(min, 255, false), GamePanel.randomNumber(min, 255, false), GamePanel.randomNumber(min, 255, false));
			tries++;
			if(tries>=50){
				break;
			}
		}
		return randColor;
	}
	//determine which areas in the image are the same color, generate a shiny color for each of those areas
	public static BufferedImage generateShiny(BufferedImage normal, BufferedImage shiny){
		BufferedImage result = new BufferedImage(normal.getWidth(), normal.getHeight(), BufferedImage.TYPE_INT_ARGB);
		ArrayList<Integer> invalidColors = getInvalidColors(normal, shiny);

		ArrayList<Point> modify = new ArrayList<Point>();
		for(int i = 0; i<normal.getWidth(); i++){
			for(int j = 0; j<normal.getHeight(); j++){
				Color normalColor = new Color(normal.getRGB(i, j), true);
				Color shinyColor = new Color(shiny.getRGB(i, j), true);
				//If this color can be changed
				if(!pixelsAreSameColor(normalColor, shinyColor, .15f)){
					modify.add(new Point(i, j));
				}
				else{
					result.setRGB(i, j, shiny.getRGB(i, j));
				}
			}
		}
		//ArrayList<Color> colorsUsed = new ArrayList<Color>();

		while(modify.size()>0){
			Color randColor = randomColor(invalidColors);
			if(randColor==null){
				//return shiny;
				randColor = new Color(220, 0, 0);
			}
			double randShade = getColorShade(randColor);

			Color currentColor = new Color(shiny.getRGB(modify.get(0).x, modify.get(0).y), true);

			//colorsUsed.add(new Color(normal.getRGB(modify.get(0).x, modify.get(0).y), true));

			for(int i = 0; i<modify.size(); i++){

				Color normalColor = new Color(shiny.getRGB(modify.get(i).x, modify.get(i).y), true);
				//System.out.println(normalColor + " got here " + colorsUsed.get(colorsUsed.size()-1) + "      " + pixelsAreSameColor(normalColor, colorsUsed.get(colorsUsed.size()-1), 10));
				if(pixelsAreSameColor(normalColor, currentColor, .01f)){
					double originalShade = getColorShade(normalColor);

					double m = originalShade/randShade;
					int red = (int)((double)(randColor.getRed() * m));
					int green = (int)((double)(randColor.getGreen() * m));
					int blue = (int)((double)(randColor.getBlue() * m));
					result.setRGB(modify.get(i).x, modify.get(i).y, new Color(red, green, blue, normalColor.getAlpha()).getRGB());
					modify.remove(i);
					if(i>0){
						i--;
					}
				}
			}
		}
		return result;
	}
	public static BufferedImage[][] duplicateSlices(BufferedImage[][] slices){
		BufferedImage[][] result = new BufferedImage[slices.length][slices[0].length];
		for(int i = 0; i<slices.length; i++){
			for(int j = 0; j<slices[0].length; j++){
				BufferedImage img = new BufferedImage(slices[i][j].getWidth(), slices[i][j].getHeight(), BufferedImage.TYPE_INT_ARGB);
				img.getGraphics().drawImage(slices[i][j], 0, 0, null);
				result[i][j] = img;
			}
		}

		return result;
	}
	public static int getColorShade(Color color){
		if(color.getRed()>=color.getGreen() && color.getRed()>=color.getBlue()){
			return color.getRed();
		}
		else if(color.getGreen()>=color.getRed() && color.getGreen()>=color.getBlue()){
			return color.getGreen();
		}
		else{
			return color.getBlue();
		}
	}
	public static void save(BufferedImage img, String pathToImage){
		System.out.println("Saving to: "+pathToImage);
		String path = GamePanel.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path.replaceFirst("/", "");

		pathToImage = pathToImage.replaceAll(path, "/");
		File outputfile = new File(pathToImage);
		outputfile.mkdirs();
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static BufferedImage[][] cut(String imageName, int sliceWidth, int sliceHeight)
	{
		//System.out.println("Starting image cut for "+imageName);
		long temp = System.currentTimeMillis();
		BufferedImage sheet;
		try
		{
			sheet = ImageIO.read(Images.class.getResourceAsStream(imageName));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}

		int w = sheet.getWidth();
		int h = sheet.getHeight();

		int xSlices = w/sliceWidth;
		int ySlices = h/sliceHeight;

		BufferedImage[][] images = new BufferedImage[xSlices][ySlices];
		for (int x=0; x<xSlices; x++)
			for (int y=0; y<ySlices; y++)
			{
				BufferedImage img = new BufferedImage(sliceWidth, sliceHeight, BufferedImage.TYPE_INT_ARGB);
				Graphics g = img.getGraphics();
				g.drawImage(sheet, -x*sliceWidth, -y*sliceHeight, null);
				g.dispose();
				images[x][y] = img;
			}

		//System.out.println("Done cutting image for "+imageName+". Total time spent on cut = "+(System.currentTimeMillis()-temp));
		return images;
	}
	public static BufferedImage[][] cut(BufferedImage sheet, int sliceWidth, int sliceHeight)
	{

		int w = sheet.getWidth();
		int h = sheet.getHeight();

		int xSlices = w/sliceWidth;
		int ySlices = h/sliceHeight;

		BufferedImage[][] images = new BufferedImage[xSlices][ySlices];
		for (int x=0; x<xSlices; x++)
			for (int y=0; y<ySlices; y++)
			{
				BufferedImage img = new BufferedImage(sliceWidth, sliceHeight, BufferedImage.TYPE_INT_ARGB);
				Graphics g = img.getGraphics();
				g.drawImage(sheet, -x*sliceWidth, -y*sliceHeight, null);
				g.dispose();
				images[x][y] = img;
			}

		//System.out.println("Done cutting image for "+imageName+". Total time spent on cut = "+(System.currentTimeMillis()-temp));
		return images;
	}
	public static BufferedImage combine(BufferedImage[][] slices){
		BufferedImage result = new BufferedImage(slices[0][0].getWidth()*slices.length, slices[0][0].getHeight()*slices[0].length, BufferedImage.TYPE_INT_ARGB);
		Graphics g = result.getGraphics();
		for(int i = 0; i<slices.length;i++){
			for(int j = 0; j<slices[0].length; j++){
				g.drawImage(slices[i][j], i*slices[0][0].getWidth(), j*slices[0][0].getHeight(), null);
			}
		}
		g.dispose();
		return result;
	}
}
