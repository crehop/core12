package interfaces;

import Zomtasia.Zomtasia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button {
	int x;
	int y;
	int width;
	int height;
	Texture backround;
	String text;
	boolean visible;
	
	public Button(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public Button(int x, int y, int width, int height, Texture overlay) {
	}
	public boolean isClicked(int x, int y){
		if(x > x && x < x + width) {
			if(y > y && y < y + height) {
				return true;
			}
		}
		return false;
	}
	public void render() {
		Zomtasia.ui.spriteBatch.draw(backround, x, y, width, height);
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
