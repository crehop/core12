package entities;

import java.io.FileNotFoundException;
import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;

public class AssetHandler {
	public AssetManager assets;
	public HashMap<String,Model> loadedModels = new HashMap<String,Model>();
	public AssetHandler(){
		assets = new AssetManager();
		assets.load("skybox/skybox.g3dj", Model.class);
		assets.load("mobs/military/US_Marine.g3dj", Model.class);
		assets.load("cars/police/City_Police_Car_LOD1.g3dj", Model.class);
		assets.finishLoading();
		storeModel(assets.get("cars/police/City_Police_Car_LOD1.g3dj", Model.class), "policeCar");
	}
	public AssetManager getAssetManager(){
		return assets;
	}
	public void storeModel(Model model, String key){
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
}
