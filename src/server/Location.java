package server;

import com.badlogic.gdx.math.Vector3;

public class Location {
	private Vector3 position;
	
	public Location(){
		this.position.x = 0;
		this.position.y = 0;
		this.position.z = 0;
	}
	public Location(float x, float y, float z){
		this.position = new Vector3();
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
	}
	public Location(Location location) {
		this.position = new Vector3();
		this.position.x = location.getX();
		this.position.y = location.getY();
		this.position.z = location.getZ();
	}
	public float getX() {
		return this.position.x;
	}
	public void setX(float x) {
		this.position.x = x;
	}
	public float getY() {
		return this.position.y;
	}
	public void setY(float y) {
		this.position.y = y;
	}
	public float getZ() {
		return this.position.z;
	}
	public void setZ(float z) {
		this.position.z = z;
	}
	public Vector3 getPosition(){
		return this.position;
	}
	public void update(Vector3 position) {
		this.position = position;
	}
	public void set(Vector3 lightPos) {
		this.position = lightPos;
	}
}
