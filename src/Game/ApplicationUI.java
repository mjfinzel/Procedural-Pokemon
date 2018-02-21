package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ApplicationUI extends JFrame{
	public BufferStrategy myStrategy;
	
	private static final long serialVersionUID = 1L;
	public Controller ctrl;
	public static int windowWidth = 800;//800
	public static int windowHeight = 650;//650
	private int gameFPS = 100;
	private long beginTime;
	private long updatePeriod = 1000000000L/gameFPS;
	
	public GamePanel drawPanel;
	
	long lastFPSCheckTime = System.currentTimeMillis();
	int framesSinceLastSecond = 0;
	static int currentFPS = 0;
	
	
	public ApplicationUI(){
		setIgnoreRepaint(true);
		setTitle("Pokemon Safari");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(windowWidth,windowHeight);
		//setResizable(false);
		setVisible(true);
		setFocusable(true);
		
		this.createBufferStrategy(2);
		myStrategy = getBufferStrategy();
		
		
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		drawPanel = new GamePanel();
		
		drawPanel.setIgnoreRepaint(true);
		
		ctrl = new Controller();
		ctrl.setGamePanel(drawPanel);
		this.addKeyListener(ctrl);
		
		pane.add(drawPanel);
		
		//setFullScreen(false);
		

		
		Thread gameThread = new Thread(){
			public void run(){
				gameLoop();
			}
		};
		gameThread.start();
		
	}
	/*
	 * Used to set whether the game should be displayed in full screen or windowed mode
	 * 
	 * @param fullscreen	a boolean used to decide if the game is full screen
	 */
	public void setFullScreen(boolean fullscreen){
		if(fullscreen==true){
			this.setExtendedState(Frame.MAXIMIZED_BOTH);  
			this.setUndecorated(true);  
		}
		else{
			this.setExtendedState(Frame.NORMAL);  
			this.setUndecorated(false);  
		}
	}
	public static void main(String[] args) {
		ApplicationUI f = new ApplicationUI();
		
	}
	/*
	 * The main loop of the game. This will call all of the update methods and repaint the game at a fixed rate
	 */
	public void gameLoop(){
		
		while(true){
			
			if(System.currentTimeMillis()-lastFPSCheckTime>=1000){
				lastFPSCheckTime=System.currentTimeMillis();
				currentFPS=framesSinceLastSecond;
				framesSinceLastSecond=0;
			}
			framesSinceLastSecond++;
			windowWidth = getWidth();
			windowHeight = getHeight();
			beginTime=System.nanoTime();
			//check keys
			Controller.checkKeys();
			
			//repaint the graphics of the game
			//look into buffer strategy and active rendering
			//repaint();
			Graphics2D g = (Graphics2D) myStrategy.getDrawGraphics();
			drawPanel.Draw(g);
			myStrategy.show();
			g.setBackground(Color.black);
			g.clearRect(0,0,getWidth(),getHeight());
			g.dispose();
			
			//update
			
			//the time taken to do everything with the frame in nanoseconds
			long timeTaken = System.nanoTime()-beginTime;
			long timeLeft = (updatePeriod - timeTaken) / 1000000L;; // In milliseconds
			if(timeTaken<updatePeriod){
				// If the time is less than 10 milliseconds, then we will put thread to sleep for 10 millisecond so that some other thread can do some work.
				if (timeLeft < 10){ 
					timeLeft = 10; //set a minimum
				}
				try {
					//Provides the necessary delay and also yields control so that other thread can do work.
					Thread.sleep(timeLeft);
				} catch (InterruptedException ex) { }
			}
		}
	}

}
