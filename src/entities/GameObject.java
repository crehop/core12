package entities;

import server.Location;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class GameObject extends ModelInstance{
	Location location;
	float roll = 0f;
	float pitch = 0f;
	float yaw = 0f;
	float scaleX = 1f;
	float scaleY = 1f;
	float scaleZ = 1f;
	boolean staticObject = false;
	String meta = "null";
	public GameObject(Model model, float x, float y, float z) {
		super(model,x,y,z);
		this.location = new Location(x,y,z);
	}
	public GameObject(Model model, float x, float y, float z,float  yaw,float  pitch,float  roll) {
		super(model,x,y,z);
		this.location = new Location(x,y,z);
		this.roll = roll;
		this.pitch = pitch;
		this.yaw = yaw;
		super.transform.setToRotation(Zomtasia.Zomtasia.yAxis, yaw);
		super.transform.setToRotation(Zomtasia.Zomtasia.xAxis, pitch);
		super.transform.setToRotation(Zomtasia.Zomtasia.zAxis, roll);
	}
	public GameObject(Model model, float x, float y, float z,float  yaw,float  pitch,float  roll, float scaleX, float scaleY, float scaleZ){
		super(model,x,y,z);
		this.location = new Location(x,y,z);
		this.roll = roll;
		this.pitch = pitch;
		this.yaw = yaw;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.scaleZ = scaleZ;
		super.transform.setToRotation(Zomtasia.Zomtasia.yAxis, yaw);
		super.transform.setToRotation(Zomtasia.Zomtasia.xAxis, pitch);
		super.transform.setToRotation(Zomtasia.Zomtasia.zAxis, roll);
		super.transform.scale(scaleX, scaleY, scaleZ);
	}
	public Location getLocation() {
		return this.location;
	}
	public float getRoll() {
		return roll;
	}
	public void setRoll(float roll) {
		this.roll = roll;
	}
	public float getPitch() {
		return pitch;
	}
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	public float getYaw() {
		return yaw;
	}
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	public float getScaleX() {
		return scaleX;
	}
	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}
	public float getScaleY() {
		return scaleY;
	}
	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}
	public float getScaleZ() {
		return scaleZ;
	}
	public void setScaleZ(float scaleZ) {
		this.scaleZ = scaleZ;
	}
	public boolean isStaticObject() {
		return staticObject;
	}
	public void setStaticObject(boolean staticObject) {
		this.staticObject = staticObject;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public void setLocation(float x, float y, float z){
		this.location.setX(x);
		this.location.setY(y);
		this.location.setZ(z);
	}
	public void setStatic(boolean isStatic){
		this.staticObject = isStatic;
	}
	public boolean isStatic(){
		return this.staticObject;
	}
	public boolean hasMeta(){
		if(meta.equalsIgnoreCase("null")){
			return false;
		}
		return true;
	}
	public String getMeta(){
		return meta;
	}
	public void setMeta(String meta){
		this.meta = meta;
	}
}
