package actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Button extends Actor{
	String text;
	Sprite buttonUp = new Sprite(new Texture("screens/startButtonUp.png"));
	Sprite buttonDown = new Sprite(new Texture("screens/startButtonDown.png"));

	public Button(int scale, int x, int y) {
		this.setX(x);
		this.setY(y);
		buttonUp.scale(scale);
		setBounds(buttonUp.getX(), buttonUp.getY(), buttonUp.getWidth(), buttonUp.getHeight());
		setTouchable(Touchable.disabled);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(buttonUp, getX(), getY());
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
