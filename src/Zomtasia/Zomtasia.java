package Zomtasia;

import java.util.ArrayList;

import screens.Console;
import world.Flora;
//import world.Lighting;
import world.Skybox;
import world.Terrain;
import world.TerrainChunk;
import world.Time;
import Shaders.SkyShader;
import Shaders.TerrainShader;
import Shaders.TreeShader;
import Shaders.WaterShader;

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
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import control.Controls;
import entities.AssetHandler;
import entities.Player;
import entities.PoliceCar;

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
	public static AssetHandler assets;
	private static ArrayList<ModelInstance> models = new ArrayList<ModelInstance>();
	public static ModelBuilder modelBuilder;
	public static float lasttime = 0;
	public String debug = "";
	int count;
	public static TerrainShader terrainShader;
	public static WaterShader waterShader;
	public static TreeShader treeShader;
	public SkyShader skyShader;
	public static boolean cameraCreated = false;
	public boolean once = true;
	public static PoliceCar testPolice;
	public static Flora testFlora;
	Texture grass;
	float progress;
	Vector3 xAxis = new Vector3(1,0,0);
	Vector3 yAxis = new Vector3(0,1,0);
	Vector3 zAxis = new Vector3(0,0,1);
	ModelBatch shadowBatch;
	public static TerrainChunk current = null;
	
	//WORLD CLASSES
	public static Terrain terrain = new Terrain();
	
	@Override
	public void create() {
		@SuppressWarnings("unused")
		Thread thread = new Thread(){
			Time time = new Time();
		};
		assets = new AssetHandler();
		terrainShader = new TerrainShader();
		terrainShader.init();
		waterShader = new WaterShader();
		waterShader.init();
		skyShader = new SkyShader();
		skyShader.init();
		treeShader = new TreeShader();
		treeShader.init();
		grass = new Texture("terrain/terrain.png");
		setGame(this);
		controls = new Controls(this);
        Gdx.input.setInputProcessor(controls);
        env = new Environment();
        env.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, .011f));
        env.add(new DirectionalLight().set(1f, 1f, 1f, -18f, -11.8f, -22.2f));
		player = new Player(0,0,10,this);
		assets.getAssetManager().finishLoading();
		modelBatch = new ModelBatch();
		//modelBuilder = new ModelBuilder();
		//model = modelBuilder.createBox(5f, 5f, 5f, 
        //    new Material(ColorAttribute.createDiffuse(Color.BLUE)),
        //    Usage.Position | Usage.Normal);
		//newModelInstance(new ModelInstance(model));
		
		
		Gdx.graphics.setContinuousRendering(true);
		Gdx.graphics.setVSync(true);
		Gdx.input.setCursorCatched(true);
		setScreen(player);
		testPolice = new PoliceCar(0,0,0);		Skybox.render();
		terrain.create();
		testFlora = new Flora();
	}

	@Override
	public void render() {
		Console.setLine1("FPS:"+ Gdx.graphics.getFramesPerSecond());
		if(assets.getAssetManager().update()) {
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
			//for(ModelInstance instance:models){
			//	modelBatch.render(instance,env);
			//
			//}
			modelBatch.render(testPolice.render(),env);
			Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(Gdx.gl.GL_ONE_MINUS_SRC_ALPHA, Gdx.gl.GL_ALPHA);
			count = 0;
	        for(int x = 0; x < terrain.getTerrainChunkLength(); x++){
	        	for(int y = 0; y < terrain.getTerrainChunkWidth(); y++){
	        		this.current = terrain.getTerrainChunk(x, y);
	        		modelBatch.render(terrain.getTerrainChunk(x, y).getTerrain(), terrainShader);
	        		count++;
	        	}
	        }
	        modelBatch.end();
			Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glDisable(GL20.GL_BLEND);
	  	    //=========================================
			//Water==================================
			modelBatch.begin(cam);
			modelBatch.render(testPolice.render(),env);
			Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(Gdx.gl.GL_ONE_MINUS_SRC_ALPHA, Gdx.gl.GL_ALPHA);
			count = 0;
	        for(int x = 0; x < terrain.getTerrainChunkLength(); x++){
	        	for(int y = 0; y < terrain.getTerrainChunkWidth(); y++){
	        		this.current = terrain.getTerrainChunk(x, y);
	        		modelBatch.render(terrain.getTerrainChunk(x, y).getWater(), waterShader);
	        		count++;
	        	}
	        }
	        modelBatch.end();
			Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glDisable(GL20.GL_BLEND);
	  	    //=========================================
			//Sky==================================
			modelBatch.begin(cam);
			modelBatch.render(testPolice.render(),env);
			Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(Gdx.gl.GL_ONE_MINUS_SRC_ALPHA, Gdx.gl.GL_ALPHA);
			count = 0;
	        for(int x = 0; x < terrain.getTerrainChunkLength(); x++){
	        	for(int y = 0; y < terrain.getTerrainChunkWidth(); y++){
	        		this.current = terrain.getTerrainChunk(x, y);
	        		modelBatch.render(terrain.getTerrainChunk(x, y).getSky(), skyShader);
	        		count++;
	        	}
	        }
	        modelBatch.end();
			Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glDisable(GL20.GL_BLEND);
	  	    //=========================================

			//TREES====================================
			
			modelBatch.begin(cam);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(Gdx.gl.GL_ONE_MINUS_SRC_ALPHA, Gdx.gl.GL_ALPHA);
			for(ModelInstance renderTree:testFlora.getTrees()){
				modelBatch.render(renderTree,treeShader);
			}
			modelBatch.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
			//=========================================
	        Console.render();
	        debug = "";
	        Console.setLine2("Chunks being rendered:" + count);
			Zomtasia.cam.update();	       
	      }else{
	       progress = assets.getAssetManager().getProgress();
	  	 }
	}
	
	@Override
	public void dispose() {
		super.dispose();
		modelBatch.dispose();
		model.dispose();
		skyShader.dispose();
		terrainShader.dispose();
		waterShader.dispose();
        for(int x = 0; x < terrain.getTerrainChunkLength(); x++){
        	for(int y = 0; y < terrain.getTerrainChunkWidth(); y++){
        		terrain.getTerrainChunk(x, y).getSky().model.dispose();
        		terrain.getTerrainChunk(x, y).getWater().model.dispose();
        		terrain.getTerrainChunk(x, y).getTerrain().model.dispose();
        	}
        }
        for(ModelInstance model:models){
        	model.model.dispose();
        }
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
		return assets.getAssetManager();
	}

	public static Terrain getTerrain() {
		return terrain;
	}
	public static void newModelInstance(ModelInstance modelInstance) {
		models.add(modelInstance);
	}
	public TerrainShader getShader(){
		return terrainShader;
	}
}
