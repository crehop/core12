package control;

import server.Location;
import Zomtasia.Zomtasia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Controls implements InputProcessor{
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
	
	public Controls(Zomtasia game){
		this.game = game;
		this.resX = Gdx.graphics.getWidth();
		this.resY = Gdx.graphics.getHeight();
	}

	public  void checkInput() {
		if(exitKey){
			game.dispose();
			return;
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
			Gdx.app.log(Zomtasia.LOG, "CAMSNAP");
		}
		if(testForward){
			Location current = Zomtasia.testPolice.getLocation();
			Zomtasia.testPolice.setLocation(current.getX(), current.getY(), current.getZ() + movementSpeed);
		}
		if(testBack){
			current = Zomtasia.testPolice.getLocation();
			Zomtasia.testPolice.setLocation(current.getX(), current.getY(), current.getZ() - movementSpeed);
		}
		if(testLeft){
			current = Zomtasia.testPolice.getLocation();
			Zomtasia.testPolice.setLocation(current.getX() + movementSpeed, current.getY(), current.getZ());
		}
		if(testRight){
			current = Zomtasia.testPolice.getLocation();
			Zomtasia.testPolice.setLocation(current.getX() - movementSpeed, current.getY(), current.getZ());
		}
		if(testUp){
			current = Zomtasia.testPolice.getLocation();
			Zomtasia.testPolice.setLocation(current.getX(), current.getY() + movementSpeed, current.getZ());
		}
		if(testDown){
			current = Zomtasia.testPolice.getLocation();
			Zomtasia.testPolice.setLocation(current.getX(), current.getY() - movementSpeed, current.getZ());
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
				Zomtasia.testFlora.createTree(Zomtasia.player.getLocation().getX(), Zomtasia.player.getLocation().getY(), Zomtasia.player.getLocation().getZ());
				menu = false;
				return false;
		}
		return false;
		}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
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
