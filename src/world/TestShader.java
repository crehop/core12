package world;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class TestShader implements Shader {
	ShaderProgram program;
	Camera camera;
	RenderContext context;
	
	int u_projTrans;
	int u_worldTrans;
	int u_color;
	Texture grass = new Texture("terrain/grass.png");
	Texture rock  = new Texture("terrain/rock.png");
	Texture dirt  = new Texture("terrain/dirt.png");
	Texture splat = new Texture("terrain/terrain.png");
	Texture texAttribute;
	TiledDrawable draw = new TiledDrawable();
	
	@Override
	public void init() {
        String vert = Gdx.files.internal("shaders/vertex/terrain_vertex.vsh").readString();
        String frag = Gdx.files.internal("shaders/fragment/terrain_fragment.fsh").readString();
        program = new ShaderProgram(vert, frag);
        if (!program.isCompiled()){
        	throw new GdxRuntimeException(program.getLog());
        }else{
        	System.out.println("COMPILED SHADER PROGRAM");
        }

        u_projTrans = program.getUniformLocation("u_projTrans");
        u_worldTrans = program.getUniformLocation("u_worldTrans");
        u_color = program.getUniformLocation("u_color");

	}

	@Override
	public void dispose() {
		program.dispose();
	}

	@Override
	public void begin(Camera camera, RenderContext context) {
		this.camera = camera;
		this.context = context;
		program.begin();
		program.setUniformMatrix(u_projTrans, camera.combined);
		context.setDepthTest(GL20.GL_LEQUAL);
		context.setCullFace(GL20.GL_BACK);
	}

	@Override
	public void render(Renderable renderable){
		//bind correct textures		
		program.setUniformf("offsetU", ((TextureAttribute)(renderable.material.get(TextureAttribute.Diffuse))).offsetU);
		program.setUniformf("offsetV", ((TextureAttribute)(renderable.material.get(TextureAttribute.Diffuse))).offsetV);
		program.setUniformf("scaleU", ((TextureAttribute)(renderable.material.get(TextureAttribute.Diffuse))).scaleU);
		program.setUniformf("scaleV", ((TextureAttribute)(renderable.material.get(TextureAttribute.Diffuse))).scaleV);

		program.setUniformi("u_texture3", context.textureBinder.bind(dirt));
		program.setUniformi("u_texture2", context.textureBinder.bind(rock));
		program.setUniformi("u_texture1", context.textureBinder.bind(grass));
		program.setUniformi("u_texture0", context.textureBinder.bind(((TextureAttribute)(renderable.material.get(TextureAttribute.Diffuse))).textureDescription));
		
		program.setUniformMatrix(u_worldTrans, renderable.worldTransform);
		renderable.environment = Zomtasia.Zomtasia.env;
		if(renderable.material.get(ColorAttribute.Diffuse) != null){
			Color color = ((ColorAttribute)renderable.material.get(ColorAttribute.Diffuse)).color;
			program.setUniformf(u_color, color.r, color.g, color.b);
			renderable.mesh.render(program,
					renderable.primitiveType,
					renderable.meshPartOffset,
					renderable.meshPartSize);
		
		}
	}

	@Override
	public void end() {
		program.end();
	}

	@Override
	public int compareTo(Shader other) {
		return 0;
	}
	@Override
	public boolean canRender(Renderable instance) {
		return true;
	}
	public ShaderProgram getProgram(){
		return program;
	}
}