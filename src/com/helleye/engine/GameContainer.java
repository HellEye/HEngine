package com.helleye.engine;

public class GameContainer implements Runnable {
	
	public Window getWindow() {
		return window;
	}
	private IGame game;
	private Renderer renderer;
	
	public Input getInput() {
		return input;
	}
	
	private Input input;
	private Window window;
	private final double UPDATE_CAP = 1.0 / 60.0;
	double frameTime = 0;
	int frames = 0;
	int fps = 0;
	private Thread thread;
	private boolean running = false;
	//resolution
	//change if lags
	//tile dimensions 12x8
	public final static int TILE_WIDTH = 12;
	public final static int TILE_HEIGHT = 8;
	public final static int P_WIDTH = 384;
	public final static int P_HEIGHT = 256;
	private float scale = 3.0F;
	private String title = "HEngine v0.1";
	
	public GameContainer(IGame game) {
	this.game=game;
	}
	
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void start() {
		window=new Window(this);
		renderer=new Renderer(this);
		input =new Input(this);
		thread = new Thread(this);
		game.initiateRender(this, renderer);
		thread.run();
	}
	
	public void stop() {
	
	}
	public int getFPS(){
		return fps;
	}
	
	public void run() {
		running = true;
		boolean render = false;
		double firstTime = 0;
		
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		
		while (running) {
			firstTime = System.nanoTime() / 1E9;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			render = true;
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while (unprocessedTime >= UPDATE_CAP) //updater
			{
				unprocessedTime -= UPDATE_CAP;
				render = true;
				//update methods
				game.update(this, (float) UPDATE_CAP);
				input.update();
				
				//update mthods end
				
				//fps counter
				if (frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					System.out.println("FPS: " + frames);
					frames = 0;
					
				}//fps counter end
			}
			if (render) //renderer
			{
				renderer.clear();
				game.render(this, renderer);
				// TODO renderer.drawText("fps:"+fps, 0, 0, 0xffffff00);
				window.update();
				frames++;
				
			}
			else {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		dispose();
	}
	
	public void dispose() {
	
	}
}