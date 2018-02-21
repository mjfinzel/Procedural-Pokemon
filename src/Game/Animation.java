package Game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.function.Function;

public class Animation {
	Point position;
	BufferedImage[][] frames;
	int currentFrame=0;
	int updatesPerFrame;
	int totalUpdates = 0;
	int animationY = 0;
	boolean done = false;
	String functionArgs = "";
	public Animation(int x, int y, int animY, BufferedImage[][] frames, int updatesPerFrame){
		position = new Point(x,y);
		animationY = animY;
		this.frames = frames;
		this.updatesPerFrame = updatesPerFrame;
	}
	public void update(){

		if(totalUpdates%updatesPerFrame==0){
			if(currentFrame<frames.length-1){
				currentFrame++;
			}
			else{
				done = true;
			}
		}
		totalUpdates++;
	}
	public void addFunction(Function<String, String> func, String arguments){
		this.functionArgs = arguments;
	}
	public void Draw(Graphics2D g){
		update();
		//System.out.println("current frame: "+currentFrame+", animationY: "+animationY);
		if(frames[currentFrame][animationY]!=null){
			g.drawImage(frames[currentFrame][animationY],position.x,position.y,null);
		}
	}
}
