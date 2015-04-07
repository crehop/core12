package control;

import server.Location;
import Zomtasia.Zomtasia;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class ControlsInput extends InputAdapter implements InputProcessor, ApplicationListener {
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
	//private int selected = -1, selecting = -1;
	//private Material selectionMaterial;
	//private Material originalMaterial;
	
	//========================
	public ControlsInput(Zomtasia game){
		this.game = game;
		this.resX = Gdx.graphics.getWidth();
		this.resY = Gdx.graphics.getHeight();
	}
	
	@Override
	public void create() {
		//System.out.println("CONFIRM CREATION");
	    //selectionMaterial = new Material();
	   // selectionMaterial.set(ColorAttribute.createDiffuse(Color.ORANGE));
	   // originalMaterial = new Material();
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public  void checkInput() {
		if(exitKey){
			game.dispose();
			return;
		}
		if(menu){
		}
		if(forward){
			Zomtasia.player.walkForward((float)(movementSpeed));
		}
		if(back){
			Zomtasia.player.walkBackward((float)(movementSpeed));
		}
		if(strafeLeft){
			Zomtasia.player.strafeLeft((float)(movementSpeed));
		}
		if(strafeRight){	
			Zomtasia.player.strafeRight((float)(movementSpeed));
		}
		if(jump){
			Zomtasia.player.moveUp((float)(movementSpeed));
		}
		if(crouch){
			Zomtasia.player.moveDown((float)(movementSpeed));
		}
		if(speedUpCam){
			movementSpeed = defaultMovementSpeed * 15;
		}
		else if(speedDownCam){
			movementSpeed = defaultMovementSpeed / 100;
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
				strafeLeft = true;
				return false;
			case Input.Keys.S:
				back = true;
				return false;
			case Input.Keys.D:
				strafeRight = true;
				return false;
			case Input.Keys.W:
				forward = true;
				return false;
			case Input.Keys.TAB:
				speedUpCam = true;
				return false;
			case Input.Keys.CONTROL_LEFT:
				speedDownCam = true;
				return false;
			case Input.Keys.SHIFT_LEFT:
				crouch = true;
				return false;
			case Input.Keys.SPACE:
				jump = true;
				return false;
			case Input.Keys.ESCAPE:
				exitKey = true;
				return false;
			case Input.Keys.UP:
				testForward = true;
				return false;
			case Input.Keys.DOWN:
				testBack = true;
				return false;
			case Input.Keys.LEFT:
				testLeft = true;
				return false;
			case Input.Keys.RIGHT:
				testRight = true;
				return false;
			case Input.Keys.CONTROL_RIGHT:
				testDown = true;
				return false;
			case Input.Keys.SHIFT_RIGHT:
				testUp = true;
				return false;
			case Input.Keys.T:
				menu = true;
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
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
}