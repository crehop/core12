package entities;

import server.Location;
import Zomtasia.Zomtasia;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class PoliceCar {
	private ModelInstance policeCar;
	private Location location;
	private float rotX = 100f;
	private float rotY = 0f;
	private float rotZ = 0f;
	Vector3 xAxis = new Vector3(1,0,0);
	Vector3 yAxis = new Vector3(0,1,0);
	Vector3 zAxis = new Vector3(0,0,1);
	
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
		this.policeCar.transform.setToRotation(xAxis, rotX);
		//this.policeCar.transform.setToRotation(yAxis, rotY);
		//this.policeCar.transform.setToRotation(zAxis, rotZ);
		this.policeCar.transform.setTranslation(location.getPosition());

		return this.policeCar;
	}
}
