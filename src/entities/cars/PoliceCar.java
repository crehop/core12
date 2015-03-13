package entities.cars;

import server.Location;
import Zomtasia.Zomtasia;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class PoliceCar {
	private ModelInstance policeCar;
	private Location location;
	public PoliceCar(float x, float y, float z){
		policeCar = new ModelInstance(Zomtasia.assets.getModel("policeCar"), x, y, z);
		this.location = new Location(x, y, z);
	}
	public Location getLocation(){
		return location;
	}
	public void setLocation(float x, float y, float z){
		this.location.setX(x);
		this.location.setY(y);
		this.location.setZ(z);
	}
	public void setLocation(Location location){
		this.location = location;
	}
	public ModelInstance render(){
		this.policeCar.transform.setTranslation(location.getPosition());
		return this.policeCar;
	}
}
