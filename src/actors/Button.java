package actors;

import Zomtasia.Zomtasia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Button extends Actor{
	String text;
	Sprite buttonUp;
	Sprite buttonDown;
	boolean down;

	public Button(int scale, int x, int y, String upTextureLocation, String downTextureLocation) {
		buttonUp = new Sprite(new Texture(upTextureLocation));
		buttonDown = new Sprite(new Texture(downTextureLocation));
		this.setX(x - buttonUp.getWidth()/2);
		this.setY(y - buttonUp.getHeight()/2);
		buttonUp.scale(scale);
		buttonDown.scale(scale);
		setBounds(buttonUp.getX(), buttonUp.getY(), buttonUp.getWidth() * scale, buttonUp.getHeight() * scale);
		this.setTouchable(Touchable.enabled);
		this.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				down = true;
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				super.touchDragged(event, x, y, pointer);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				down = false;
				Zomtasia.getGame();
				Zomtasia.getGame().setScreen(Zomtasia.player);
				Gdx.input.setInputProcessor(Zomtasia.controls);
				Gdx.input.setCursorCatched(true);
				// TODO Auto-generated method stub
				super.touchUp(event, x, y, pointer, button);
			}

		});
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(down) {
			buttonDown.draw(batch);
		}else {
			buttonUp.draw(batch);
		}
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
