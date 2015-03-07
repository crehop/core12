package Zomtasia;

import java.util.ArrayList;

import screens.Console;
//import world.Lighting;
import world.Skybox;
import world.Terrain;
import world.TestShader;
import world.Time;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import control.Controls;
import entities.Player;

public class Zomtasia extends Game implements ApplicationListener {
	public static String VERSION = "0.01 Pre-Alpha";
	public static String LOG = "";
	public static PerspectiveCamera cam;
	public ModelBatch modelBatch;
	public Model model;
	public ModelInstance instance;
	public static Player player;
	public static Environment env;
	private static Zomtasia game;
	public static Controls controls;
	private static AssetManager assets;
	private static ArrayList<ModelInstance> models = new ArrayList<ModelInstance>();
	public static ModelBuilder modelBuilder;
	public static float lasttime = 0;
	public String debug = "";
	int count;
	TestShader test;
	public static boolean cameraCreated = false;
	public boolean once = true;
	Texture grass;
	float progress;
	Vector3 xAxis = new Vector3(1,0,0);
	Vector3 yAxis = new Vector3(0,1,0);
	Vector3 zAxis = new Vector3(0,0,1);
	
	//WORLD CLASSES
	public static Terrain terrain = new Terrain();
	
	@Override
	public void create() {
		@SuppressWarnings("unused")
		Thread thread = new Thread(){
			Time time = new Time();
		};
		test = new TestShader();
		test.init();
		grass = new Texture("terrain/terrain.png");
		setGame(this);
		controls = new Controls(this);
        Gdx.input.setInputProcessor(controls);
       
        env = new Environment();
        env.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, .011f));
        env.add(new DirectionalLight().set(1f, 1f, 1f, -18f, -11.8f, -22.2f));
		player = new Player(0,0,10,this);
        
		modelBatch = new ModelBatch();

		modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(5f, 5f, 5f, 
            new Material(ColorAttribute.createDiffuse(Color.BLUE)),
            Usage.Position | Usage.Normal);
		newModelInstance(new ModelInstance(model));
		
		
		Gdx.graphics.setContinuousRendering(true);
		Gdx.graphics.setVSync(true);
		Gdx.input.setCursorCatched(true);
		setScreen(player);
		
		assets = new AssetManager();
		assets.load("skybox/skybox.g3dj", Model.class);
		assets.finishLoading();
		Skybox.render();
		terrain.create();
	}

	@Override
	public void render() {
		Console.setLine1("FPS:"+ Gdx.graphics.getFramesPerSecond());
		if(assets.update()) {
			super.render();
			if(cameraCreated == false){
				cameraCreated = true;
			}
	  	    Console.setLine9("POSITION: X:" + (double) Math.round(player.getLocation().getX() * 100) / 100
	  	    		+ " Y:" + (double) Math.round(player.getLocation().getY() * 100) / 100
	  	    		+ " Z:" + (double) Math.round(player.getLocation().getZ() * 100) / 100);
	  	    Console.setLine10("TIME: " + (double) Math.round(Time.getTime() * 100) / 100);
	  	    
	  	    //skybox===================================
	  	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
			controls.checkInput();
			modelBatch.begin(cam);
			modelBatch.render(Skybox.render());
	        modelBatch.end();
			Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);
	  	    //=========================================
			
			//Terrain==================================
			modelBatch.begin(cam);
			for(ModelInstance instance:models){
				modelBatch.render(instance,env);
			}
			Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
			count = 0;
	        for(int x = 0; x < terrain.getTerrainChunkLength(); x++){
	        	for(int y = 0; y < terrain.getTerrainChunkWidth(); y++){
	        		modelBatch.render(terrain.getTerrainChunk(x, y).getModelInstance(), test);
	        		count++;
	        	}
	        }
	        modelBatch.end();
	        //Lighting.endShader();
			Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
	  	    //=========================================

	        Console.render();
	        debug = "";
	        Console.setLine2("Chunks being rendered:" + count);
			Zomtasia.cam.update();	       
	      }else{
	       progress = assets.getProgress();
	  	 }
	}
	
	@Override
	public void dispose() {
		super.dispose();
		modelBatch.dispose();
		model.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	public static Zomtasia getGame() {
		return game;
	}

	public static void setGame(Zomtasia game) {
		Zomtasia.game = game;
	}
	public AssetManager getAssets(){
		return assets;
	}

	public static Terrain getTerrain() {
		return terrain;
	}
	public static void newModelInstance(ModelInstance modelInstance) {
		models.add(modelInstance);
	}
}
