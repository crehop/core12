package world;

import Zomtasia.Zomtasia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;

public class TerrainLighting implements Shader {
	private static Game game;
		@Override
	    public void init () {}
	    @Override
	    public void dispose () {}
	    @Override
	    public void begin (Camera camera, RenderContext context) {  }
	    @Override
	    public void render (Renderable renderable) {    }
	    @Override
	    public void end () {    }
	    @Override
	    public int compareTo (Shader other) {
	        return 0;
	    }
	    @Override
	    public boolean canRender (Renderable instance) {
	        return true;
	    }
	}