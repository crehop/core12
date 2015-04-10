package interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class UI extends InputAdapter implements InputProcessor {
	private Stage stage;
	private Texture start;
	private Actor actor = new Actor();
	SpriteBatch spriteBatch;
	public UI(){
		create();
	}
	
	public void create () {
		spriteBatch = new SpriteBatch();
	    stage = new Stage(new ExtendViewport(640, 480, 1080, 720));
	    Gdx.input.setInputProcessor(stage);
	    start = new Texture("screens/start.png");
	    stage.addActor(actor);
	    actor.setBounds(0, 0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
	}

	public void resize (int width, int height) {
	    // See below for what true means.
	    stage.getViewport().update(width, height, false);
	}

	public void render (float delta) {
		// Set the viewport to the whole screen.
		// Draw anywhere on the screen.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		spriteBatch.draw(start, 0 + Gdx.graphics.getWidth()/2 - start.getWidth()/2, 0 + Gdx.graphics.getHeight()/2 - start.getHeight()/2);
		spriteBatch.end();
		// Restore the stage's viewport.
	}
	public void dispose() {
	    stage.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
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

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	public Stage getStage(){
		return stage;
	}
	
}
