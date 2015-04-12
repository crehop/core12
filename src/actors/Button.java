package actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Button extends Actor{
	String text;
	Sprite buttonUp = new Sprite(new Texture("screens/startButtonUp.png"));
	Sprite buttonDown = new Sprite(new Texture("screens/startButtonDown.png"));

	public Button(int scale, int x, int y) {
		this.setX(x - buttonUp.getWidth()/2);
		this.setY(y - buttonUp.getHeight()/2);
		buttonUp.scale(scale);
		setBounds(buttonUp.getX(), buttonUp.getY(), buttonUp.getWidth(), buttonUp.getHeight());

	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		buttonUp.draw(batch);
	}
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	@Override
	protected void positionChanged() {
		buttonUp.setPosition(getX(), getY());
		buttonDown.setPosition(getX(), getY());
		super.positionChanged();
	}
}
