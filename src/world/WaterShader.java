package world;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class WaterShader implements Shader {
	ShaderProgram program;
	Camera camera;
	RenderContext context;
	
	int u_projTrans;
	int u_worldTrans;
	int u_color;
	Random rand = new Random();
	Texture water = new Texture("terrain/water.png");
	Texture texAttribute;
	TiledDrawable draw = new TiledDrawable();
	private final Matrix3 normalMatrix = new Matrix3();
	private static final float[] lightPosition = { 205, 135, 5 };
	private Matrix4 modelView = new Matrix4();
	int currentChunkX;
	int currentChunkY;
	TerrainChunk current;
	
	@Override
	public void init() {
        String vert = Gdx.files.internal("shaders/vertex/water_vertex.vsh").readString();
        String frag = Gdx.files.internal("shaders/fragment/water_fragment.fsh").readString();
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
		lightPosition[0] = Zomtasia.Zomtasia.testPolice.getLocation().getX();
		lightPosition[1] = Zomtasia.Zomtasia.testPolice.getLocation().getY();
		lightPosition[2] = Zomtasia.Zomtasia.testPolice.getLocation().getZ();
		//bind correct textures		
	    modelView.set(renderable.worldTransform);
		program.setUniformMatrix("u_normalMatrix", normalMatrix.set(modelView).inv().transpose());
		program.setUniform3fv("u_lightPosition", lightPosition , 0, 3);
		//program.setUniform4fv("u_ambientColor", ambientColor, 0, 4);
		//program.setUniform4fv("u_diffuseColor", diffuseColor, 0, 4);
		//program.setUniform4fv("u_specularColor", specularColor, 0, 4);
		program.setUniformi("u_texture0", context.textureBinder.bind(water));
		water.unsafeSetWrap(TextureWrap.Repeat,TextureWrap.Repeat);
		water.unsafeSetFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
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