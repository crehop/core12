package Zomtasia;

import interfaces.UI;

import java.util.ArrayList;

import screens.Console;
import screens.Player;
import screens.SplashScreen;
import server.Time;
import world.Flora;
import world.Skybox;
import world.Terrain;
import world.TerrainChunk;
import Shaders.SkyShader;
import Shaders.TerrainShader;
import Shaders.TreeShader;
import Shaders.WaterShader;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import control.Controls;
import control.MenuControls;
import entities.AssetHandler;
import entities.GameObject;
import entities.Zombie;

public class Zomtasia extends Game implements ApplicationListener {
	public static String VERSION = "0.01 Pre-Alpha";
	public static String LOG = "";
	
	public static PerspectiveCamera cam;
	public ModelBatch modelBatch;
	
	public static SplashScreen splash;
	public static Player player;
	public static UI ui;
	
	public static Environment env;
	private static Zomtasia game;
	public static AssetHandler assets;
	public static ModelBuilder modelBuilder;
	public static InputMultiplexer multiplexer;
	
	public static Controls controls;
	public static MenuControls controlsMenu;
	
	private static ArrayList<ModelInstance> models = new ArrayList<ModelInstance>();
	private static int count;
	
	public static TerrainShader terrainShader;
	public static WaterShader waterShader;
	public static TreeShader treeShader;
	public SkyShader skyShader;
	
	public static Flora testFlora;
	float progress;
	
	public static Vector3 xAxis = new Vector3(1,0,0);
	public static Vector3 yAxis = new Vector3(0,1,0);
	public static Vector3 zAxis = new Vector3(0,0,1);
	
	private Vector3 position = new Vector3();
	
	private Zombie zombie;
	
	//WORLD CLASSES
	
	public static Terrain terrain = new Terrain();
    public static Array<GameObject> instances = new Array<GameObject>();	
    
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
		ui = new UI();
		Zomtasia.game = this;
		controls = new Controls(this);
		controlsMenu = new MenuControls(this);
        env = new Environment();
        env.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, .011f));
        env.add(new DirectionalLight().set(1f, 1f, 1f, -18f, -11.8f, -22.2f));
		player = new Player(0,0,10,this);
		splash = new SplashScreen(this);
		assets.getAssetManager().finishLoading();
		modelBatch = new ModelBatch();
		modelBuilder = new ModelBuilder();
		
		//ABSOLUTE 0,0,0 BOX=========================================
		Model model = modelBuilder.createBox(.02f, .02f, .02f, 
           new Material(ColorAttribute.createDiffuse(Color.RED)),
           Usage.Position | Usage.Normal);
		newModelInstance(new ModelInstance(model));
		//===========================================================
		
		Gdx.graphics.setContinuousRendering(true);
		Gdx.graphics.setVSync(true);
		Gdx.input.setCursorCatched(false);
		
		setScreen(splash);
		Skybox.render();
		terrain.create();
		testFlora = new Flora();
		multiplexer = new InputMultiplexer(ui.getStage(),controlsMenu);
		Gdx.input.setInputProcessor(multiplexer);
		zombie = new Zombie(assets.getModel("zombie@walk06"),0,0,0);
	}

	@Override
	public void render() {	
		Console.setLine1("FPS:" + Gdx.graphics.getFramesPerSecond());
		//MAIN MENU LOOP============================================================================================================
		if(assets.getAssetManager().update() && this.screen.equals(splash)) {
			ui.render(Gdx.graphics.getDeltaTime());
		}
		
		//GAME LOOP=================================================================================================================
		else if(assets.getAssetManager().update() && this.screen.equals(player)) {
			controls.checkInput();
			super.render();
	  	    Console.setLine9("POSITION: X:" + (double) Math.round(player.getLocation().getX() * 100) / 100
	  	    		+ " Y:" + (double) Math.round(player.getLocation().getY() * 100) / 100
	  	    		+ " Z:" + (double) Math.round(player.getLocation().getZ() * 100) / 100);
	  	    Console.setLine10("TIME: " + (double) Math.round(Time.getTime() * 100) / 100);
	  	    
	  	    //skybox===================================
	  	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
			modelBatch.begin(cam);
			modelBatch.render(Skybox.render());
	        modelBatch.end();
			Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);
	  	    //=========================================
			
			//TEST=====================================
			modelBatch.begin(cam);
			for(ModelInstance instance:models){
				modelBatch.render(instance,env);
			}
	        modelBatch.end();
			//=========================================
			
			//Terrain==================================
			modelBatch.begin(cam);
			Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ALPHA);
			count = 0;
	        for(int x = 0; x < terrain.getTerrainChunkLength(); x++){
	        	for(int y = 0; y < terrain.getTerrainChunkWidth(); y++){
	        		modelBatch.render(terrain.getTerrainChunk(x, y).getTerrain(), terrainShader);
	        	}
	        }
	        modelBatch.end();
			Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glDisable(GL20.GL_BLEND);
	  	    //=========================================
			//Water==================================
			modelBatch.begin(cam);
			Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ALPHA);
			count = 0;
	        for(int x = 0; x < terrain.getTerrainChunkLength(); x++){
	        	for(int y = 0; y < terrain.getTerrainChunkWidth(); y++){
	        		modelBatch.render(terrain.getTerrainChunk(x, y).getWater(), waterShader);
	        	}
	        }
	        modelBatch.end();
			Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glDisable(GL20.GL_BLEND);
	  	    //=========================================
			//Sky==================================
			modelBatch.begin(cam);
			Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ALPHA);
			count = 0;
	        for(int x = 0; x < terrain.getTerrainChunkLength(); x++){
	        	for(int y = 0; y < terrain.getTerrainChunkWidth(); y++){
	        		modelBatch.render(terrain.getTerrainChunk(x, y).getSky(), skyShader);
	        	}
	        }
	        modelBatch.end();
			Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
			Gdx.gl.glDisable(GL20.GL_BLEND);
	  	    //=========================================

			//TREES====================================
			
			modelBatch.begin(cam);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ALPHA);
			for(GameObject renderTree:testFlora.getTrees()){
				if(isVisible(renderTree)){
					renderTree.render();
					if(Gdx.input.isCursorCatched()){
						modelBatch.render(renderTree,treeShader);
					}else{
						modelBatch.render(renderTree,env);
					}
					count++;
				}
				modelBatch.render(zombie,env);
			}
			modelBatch.end();
			//=========================================
	        Console.render();
	        Console.setLine2("Models being rendered:" + count);
			Zomtasia.cam.update();	       
		//LOADING LOOP =========================================================================================================
	      }else{
	       progress = assets.getAssetManager().getProgress();
	  	 }
	}
	
	@Override
	public void dispose() {
		super.dispose();
		modelBatch.dispose();
		skyShader.dispose();
		terrainShader.dispose();
		waterShader.dispose();
		assets.assets.dispose();
        ui.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		ui.resize(width, height);
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
	protected boolean isVisible(final GameObject instance) {
	    instance.transform.getTranslation(position);
	    position.add(instance.center);
	    return cam.frustum.sphereInFrustum(position, instance.radius);
	}
}
