package control;

import screens.Console;
import server.Location;
import Zomtasia.Zomtasia;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

import entities.GameObject;

public class ControlsInput extends InputAdapter implements InputProcessor {
	private boolean exitKey;
	private boolean forward;
	private boolean back;
	private boolean strafeLeft;
	private boolean strafeRight;
    private boolean jump;
	private boolean crouch;
	private boolean speedUpCam;
	private boolean speedDownCam;
	private boolean menu;
	private boolean testForward;
	private boolean testBack;
	private boolean testLeft;
	private boolean testRight;
	private boolean testUp;
	private boolean testDown;
	private Location current;
	private Zomtasia game;
	
	//Mouse sensitivity
	private float mouseSensitivity = 0.04141519f;
	private final float defaultMovementSpeed = 0.50f;
	public float movementSpeed = defaultMovementSpeed;
	private int mouseX = 0;
	private int mouseY = 0;
	private int magY = 0;
	private int magX = 0;
	private int resX = 0;
	private int resY = 0;
	int count = 0;
	
	//3d OBJECT MANIPULATION
	private int selected = -1, selecting = -1;
	private Material selectionMaterial;
	private Material originalMaterial;
	private Vector3 position = new Vector3();
	
	//========================
	public ControlsInput(Zomtasia game){
		this.game = game;
		this.resX = Gdx.graphics.getWidth();
		this.resY = Gdx.graphics.getHeight();
	    selectionMaterial = new Material();
		selectionMaterial.set(ColorAttribute.createDiffuse(Color.ORANGE));
		originalMaterial = new Material();
	}

	public  void checkInput() {
		if(exitKey){
			game.dispose();
			return;
		}
		if(menu){
		}
		if(forward){
			if(Gdx.input.isCursorCatched()){
				Zomtasia.player.walkForward((float)(movementSpeed));
			}
		}
		if(back){
			Console.setLine5("CONFIRM BACK");
			if(Gdx.input.isCursorCatched()){
				Zomtasia.player.walkBackward((float)(movementSpeed));
			}
		}
		if(strafeLeft){
			if(Gdx.input.isCursorCatched()){
				Zomtasia.player.strafeLeft((float)(movementSpeed));
			}
		}
		if(strafeRight){	
			if(Gdx.input.isCursorCatched()){
				Zomtasia.player.strafeRight((float)(movementSpeed));
			}
		}
		if(jump){
			if(Gdx.input.isCursorCatched()){
				Zomtasia.player.moveUp((float)(movementSpeed));
			}
		}
		if(crouch){
			if(Gdx.input.isCursorCatched()){
				Zomtasia.player.moveDown((float)(movementSpeed));
			}
		}
		if(speedUpCam){
			if(Gdx.input.isCursorCatched()){
				movementSpeed = defaultMovementSpeed * 15;
			}
		}
		else if(speedDownCam){
			if(Gdx.input.isCursorCatched()){
				movementSpeed = defaultMovementSpeed / 100;
			}
		}
		else{
			movementSpeed = defaultMovementSpeed;
		}
		if(menu){
			Zomtasia.testFlora.createTree(count,Zomtasia.player.getLocation().getX(), Zomtasia.player.getLocation().getY(), Zomtasia.player.getLocation().getZ());
			count++;
			if(count > 13){
				count = 0;
			}
		}
		if(testForward){
			current = Zomtasia.testFlora.lastMoved.getLocation();
			Zomtasia.testFlora.lastMoved.setLocation(current.getX(), current.getY(), current.getZ() + movementSpeed);
		}
		if(testBack){
			current = Zomtasia.testFlora.lastMoved.getLocation();
			Zomtasia.testFlora.lastMoved.setLocation(current.getX(), current.getY(), current.getZ() - movementSpeed);
		}
		if(testLeft){
			current = Zomtasia.testFlora.lastMoved.getLocation();
			Zomtasia.testFlora.lastMoved.setLocation(current.getX() + movementSpeed, current.getY(), current.getZ());
		}
		if(testRight){
			current = Zomtasia.testFlora.lastMoved.getLocation();
			Zomtasia.testFlora.lastMoved.setLocation(current.getX() - movementSpeed, current.getY(), current.getZ());
		}
		if(testUp){
			current = Zomtasia.testFlora.lastMoved.getLocation();
			Zomtasia.testFlora.lastMoved.setLocation(current.getX(), current.getY() + movementSpeed, current.getZ());
		}
		if(testDown){
			current = Zomtasia.testFlora.lastMoved.getLocation();
			Zomtasia.testFlora.lastMoved.setLocation(current.getX(), current.getY() - movementSpeed, current.getZ());
		}
	}
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
			case Input.Keys.A:
				if(Gdx.input.isCursorCatched()){
					strafeLeft = true;
				}
				return false;
			case Input.Keys.S:
				if(Gdx.input.isCursorCatched()){
					back = true;
				}
				return false;
			case Input.Keys.D:
				if(Gdx.input.isCursorCatched()){
					strafeRight = true;
				}
				return false;
			case Input.Keys.W:
				if(Gdx.input.isCursorCatched()){
					forward = true;
				}
				return false;
			case Input.Keys.TAB:
				if(Gdx.input.isCursorCatched()){
					speedUpCam = true;
				}
				return false;
			case Input.Keys.CONTROL_LEFT:
				if(Gdx.input.isCursorCatched()){
					speedDownCam = true;
				}
				return false;
			case Input.Keys.SHIFT_LEFT:
				if(Gdx.input.isCursorCatched()){
					crouch = true;
				}
				return false;
			case Input.Keys.SPACE:
				if(Gdx.input.isCursorCatched()){
					jump = true;
				}
				return false;
			case Input.Keys.ESCAPE:
				if(Gdx.input.isCursorCatched()){
					exitKey = true;
				}
				return false;
			case Input.Keys.UP:
				if(Gdx.input.isCursorCatched()){
					testForward = true;
				}
				return false;
			case Input.Keys.DOWN:
				if(Gdx.input.isCursorCatched()){
					testBack = true;
				}
				return false;
			case Input.Keys.LEFT:
				if(Gdx.input.isCursorCatched()){
					testLeft = true;
				}
				return false;
			case Input.Keys.RIGHT:
				if(Gdx.input.isCursorCatched()){
					testRight = true;
				}
				return false;
			case Input.Keys.CONTROL_RIGHT:
				if(Gdx.input.isCursorCatched()){
					testDown = true;
				}
				return false;
			case Input.Keys.SHIFT_RIGHT:
				if(Gdx.input.isCursorCatched()){
					testUp = true;
				}
				return false;
			case Input.Keys.T:
				if(Gdx.input.isCursorCatched()){
					menu = true;
				}
				return false;
			default:
				return true;
		}
	}
	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
			case Input.Keys.A:
				strafeLeft = false;
				return false;
			case Input.Keys.S:
				back = false;
				return false;
			case Input.Keys.D:
				strafeRight = false;
				return false;
			case Input.Keys.E:
				if(Gdx.input.isCursorCatched()){
					Gdx.input.setCursorCatched(false);
				}else{
					Gdx.input.setCursorCatched(true);
				}
				return false;
			case Input.Keys.W:
				forward = false;
				return false;
			case Input.Keys.TAB:
				speedUpCam = false;
				return false;
			case Input.Keys.CONTROL_LEFT:
				speedDownCam = false;
				return false;
			case Input.Keys.SHIFT_LEFT:
				crouch = false;
				return false;
			case Input.Keys.SPACE:
				jump = false;
				return false;
			case Input.Keys.ESCAPE:
				exitKey = false;
				return false;
			case Input.Keys.UP:
				testForward = false;
				return false;
			case Input.Keys.DOWN:
				testBack = false;
				return false;
			case Input.Keys.LEFT:
				testLeft = false;
				return false;
			case Input.Keys.RIGHT:
				testRight = false;
				return false;
			case Input.Keys.CONTROL_RIGHT:
				testDown = false;
				return false;
			case Input.Keys.SHIFT_RIGHT:
				testUp = false;
				return false;
			case Input.Keys.T:
				menu = false;
				count++;
				if(count>13) {
					count = 0;
				}
				return false;
		}
		return false;
		}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method 
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
	    magX = Math.abs(mouseX - screenX);
	    magY = Math.abs(mouseY - screenY);
	    if (mouseX > screenX) {
		   resX += magX;
	    }

	    if (mouseX < screenX) {
		    resX -= magX;
	    }

	    if (mouseY < screenY) {
	    	resY -= magY;
	    }

	    if (mouseY > screenY) {
	    	resY += magY;
	    }
	    
		//CAMERA PITCH YAW CODE =====================
		Zomtasia.player.pitch((float)((float)resY * (float)mouseSensitivity));
		Zomtasia.player.yaw((float)((float)resX * (float)mouseSensitivity));
		resX = 0;
		resY = 0;
	    mouseX = screenX;
	    mouseY = screenY;
		//===========================================


	    return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		  selecting = getObject(screenX, screenY);
		  return selecting >= 0;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	    if (selecting >= 0) {
	        if (selecting == getObject(screenX, screenY))
	            setSelected(selecting);
	        selecting = -1;
	        return true;
	    }
	    return false;   
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
	    if (selecting < 0) 
	        return false;
	    if (selected == selecting) {
	        Ray ray = Zomtasia.cam.getPickRay(screenX, screenY);
	        final float distance = -ray.origin.y / ray.direction.y;
	        position.set(ray.direction).scl(distance).add(ray.origin);
	        Zomtasia.instances.get(selected).transform.setTranslation(position);
	    }
	    return true;
	}
	public int getObject (int screenX, int screenY) {
	    Ray ray = Zomtasia.cam.getPickRay(screenX, screenY);
	 
	    int result = -1;
	    float distance = -1;
	 
	    for (int i = 0; i < Zomtasia.instances.size; ++i) {
	        final GameObject instance = Zomtasia.instances.get(i);
	 
	        instance.transform.getTranslation(position);
	        position.add(instance.center);
	 
	        float dist2 = ray.origin.dst2(position);
	        if (distance >= 0f && dist2 > distance)
	            continue;
	 
	        if (Intersector.intersectRaySphere(ray, position, instance.radius, null)) {
	            result = i;
	            distance = dist2;
	        }
	    }
	 
	    return result;
	}

	public void setSelected (int value) {
	    if (selected == value) return;
	    if (selected >= 0) {
	        Material mat = Zomtasia.instances.get(selected).materials.get(0);
	        mat.clear();
	        mat.set(originalMaterial);
	    }
	    selected = value;
	    if (selected >= 0) {
	        Material mat = Zomtasia.instances.get(selected).materials.get(0);
	        originalMaterial.clear();
	        originalMaterial.set(mat);
	        mat.clear();
	        mat.set(selectionMaterial);
	    }
	}
}
