package interfaces;

import actors.Button;
import actors.Title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UI{
	private Stage titleStage;
	SpriteBatch  batch;
	public UI(){
		create();
	}
	
	public void create () {
		Actor title = new Title();
		batch = new SpriteBatch();
	    titleStage = new Stage(new ScreenViewport());
	    titleStage.addActor(title);
	    titleStage.addActor(new Button(0.5f,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/4,"buttons/startButtonUp.png","buttons/startButtonDown.png", 1));
	    titleStage.addActor(new Button(0.5f,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/6,"buttons/button.png","buttons/buttonDown.png", 2));
	    titleStage.setKeyboardFocus(title);
	}

	public void resize (int width, int height) {
	    // See below for what true means.
	    titleStage.getViewport().update(width, height, false);
	}

	public void render (float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        titleStage.act(Gdx.graphics.getDeltaTime()); 
        titleStage.draw();
	}
	public void dispose() {
	    titleStage.dispose();
	    batch.dispose();
	}
	
	public Stage getStage(){
		return titleStage;
	}
	
}
