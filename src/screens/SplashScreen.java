package screens;

import Zomtasia.Zomtasia;
import actors.Title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SplashScreen implements Screen{
	Sprite splashSprite;
	SpriteBatch batch;
	Zomtasia game;
	Texture splash;
	Stage titlestage;
	
	public SplashScreen(Zomtasia game){
		this.game = game;

		Actor title = new Title();
		
		batch = new SpriteBatch();
	    titlestage = new Stage(new ScreenViewport());
	    titlestage.addActor(title);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        titlestage.act(Gdx.graphics.getDeltaTime()); 
        titlestage.draw();
		batch.begin();
		splashSprite.draw(batch);
		batch.end();
	}
	@Override
	public void show() {
		splash = new Texture("data/test.jpg");
		splash.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		splashSprite = new Sprite(splash);
		batch = new SpriteBatch();
	}


	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
	}

}
