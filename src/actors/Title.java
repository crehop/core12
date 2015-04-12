package actors;

import Zomtasia.Zomtasia;

import com.badlogic.gdx.Gdx;
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
		this.setX(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2);
		this.setY(Gdx.graphics.getHeight()/2 + Gdx.graphics.getHeight()/6 - sprite.getHeight()/2);
		setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		setTouchable(Touchable.disabled);
		sprite.scale(1.5f);
		setTouchable(Touchable.disabled);
		this.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {				
				if(keycode == Input.Keys.RIGHT) {					
					MoveByAction mba = new MoveByAction();
					mba.setAmount(100f, 0f);
					mba.setDuration(5f);
					Title.this.addAction(mba);
					return true;
				}
				else if(keycode == Input.Keys.ENTER){
					Zomtasia.getGame().setScreen(Zomtasia.getGame().player);
					Gdx.input.setInputProcessor(Zomtasia.controls);
					Gdx.input.setCursorCatched(true);
					return true;
				}
				return false;
			}
		});
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
