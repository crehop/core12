package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Title extends Actor{
	Sprite sprite = new Sprite(new Texture("screens/start.png"));
	public Title() {
		this.setX(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2);
		this.setY(Gdx.graphics.getHeight()/2 + Gdx.graphics.getHeight()/6 - sprite.getHeight()/2);
		setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		setTouchable(Touchable.disabled);
		sprite.scale(1.5f);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch);
	}
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	@Override
	protected void positionChanged() {
		sprite.setPosition(getX(), getY());
		super.positionChanged();
	}
}
