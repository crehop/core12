package entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;

public class AssetHandler {
	public AssetManager assets;
	public HashMap<String,Model> loadedModels = new HashMap<String,Model>();
	public ArrayList<Model> dispose = new ArrayList<Model>();
	public AssetHandler(){
		assets = new AssetManager();
		assets.load("skybox/skybox.g3dj", Model.class);
		assets.load("mobs/military/US_Marine.g3dj", Model.class);
		assets.load("cars/police/City_Police_Car_LOD1.g3dj", Model.class);
		assets.load("terrain/trees/tree1.g3dj", Model.class);
		assets.load("terrain/trees/tree2.g3dj", Model.class);
		assets.load("terrain/trees/tree3.g3dj", Model.class);
		assets.load("terrain/trees/tree4.g3dj", Model.class);
		assets.load("terrain/trees/tree5.g3dj", Model.class);
		assets.load("terrain/trees/tree6.g3dj", Model.class);
		assets.load("terrain/trees/tree7.g3dj", Model.class);
		assets.load("terrain/trees/tree8.g3dj", Model.class);
		assets.load("terrain/trees/tree9.g3dj", Model.class);
		assets.load("terrain/trees/tree10.g3dj", Model.class);
		assets.load("terrain/trees/tree11.g3dj", Model.class);
		assets.load("terrain/trees/tree12.g3dj", Model.class);
		assets.load("terrain/trees/tree13.g3dj", Model.class);
		assets.finishLoading();
		storeModel(assets.get("cars/police/City_Police_Car_LOD1.g3dj", Model.class), "policeCar");
		storeModel(assets.get("terrain/trees/tree1.g3dj", Model.class), "tree1");
		storeModel(assets.get("terrain/trees/tree2.g3dj", Model.class), "tree2");
		storeModel(assets.get("terrain/trees/tree3.g3dj", Model.class), "tree3");
		storeModel(assets.get("terrain/trees/tree4.g3dj", Model.class), "tree4");
		storeModel(assets.get("terrain/trees/tree5.g3dj", Model.class), "tree5");
		storeModel(assets.get("terrain/trees/tree6.g3dj", Model.class), "tree6");
		storeModel(assets.get("terrain/trees/tree7.g3dj", Model.class), "tree7");
		storeModel(assets.get("terrain/trees/tree8.g3dj", Model.class), "tree8");
		storeModel(assets.get("terrain/trees/tree9.g3dj", Model.class), "tree9");
		storeModel(assets.get("terrain/trees/tree10.g3dj", Model.class), "tree10");
		storeModel(assets.get("terrain/trees/tree11.g3dj", Model.class), "tree11");
		storeModel(assets.get("terrain/trees/tree12.g3dj", Model.class), "tree12");
		storeModel(assets.get("terrain/trees/tree13.g3dj", Model.class), "tree13");
	}
	public AssetManager getAssetManager(){
		return assets;
	}
	public void storeModel(Model model, String key){
		dispose.add(model);
		loadedModels.put(key,model);
	}
	public Model getModel(String string) {
		if(loadedModels.containsKey(string)){
			return loadedModels.get(string);
		}
		else{
			try {
				throw new FileNotFoundException();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public void dispose() {
		for(Model model:dispose){
			model.dispose();
		}
	}
	public void addDisposable(Model model){
		dispose.add(model);
	}
}
