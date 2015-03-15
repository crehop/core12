package world;

import server.Location;
import Zomtasia.Zomtasia;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Flora {
	ModelInstance tree;
	Location location;
	public Flora(float x, float y, float z){
		tree = new ModelInstance(Zomtasia.assets.getModel("trees"), x, y, z);
		this.location = new Location(x, y, z);
	}
	public ModelInstance getModelInstance(){
		return tree;
	}
}
