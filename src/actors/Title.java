package actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Title extends Actor{
	Sprite sprite = new Sprite(new Texture("screens/start.png"));
	public Title() {
		setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		System.out.println("" + sprite.getX() + "," + sprite.getY() + "," + sprite.getWidth() + "," + sprite.getHeight());
		setTouchable(Touchable.enabled);
		this.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {				
				if(keycode == Input.Keys.RIGHT) {					
					MoveByAction mba = new MoveByAction();
					mba.setAmount(100f, 0f);
					mba.setDuration(5f);
					
					Title.this.addAction(mba);
				}
				return true;
			}
		});
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(sprite, 0, 0);
	}
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	@Override
	protected void positionChanged() {
		System.out.println("triggered," + getX() + "," + getY());
		sprite.setPosition(getX(), getY());
		super.positionChanged();
	}
}
