package Shaders;

import java.util.Random;

import world.TerrainChunk;

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
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class TreeShader implements Shader {
	ShaderProgram program;
	Camera camera;
	RenderContext context;
	
	int u_projTrans;
	int u_worldTrans;
	int u_color;
	Random rand = new Random();
	Texture water = new Texture("terrain/water.png");
	Texture noise = new Texture("terrain/tex10.png");
	Texture texAttribute;
	TiledDrawable draw = new TiledDrawable();
	private final Matrix3 normalMatrix = new Matrix3();
	private static final float[] lightPosition = { 205, 135, 5 };
	private Matrix4 modelView = new Matrix4();
	int currentChunkX;
	int currentChunkY;
	TerrainChunk current;
	float waterHeight;
	float random;
	float waveHeight = 0.003f;
	
	@Override
	public void init() {
        String vert = Gdx.files.internal("shaders/vertex/tree_vertex.vsh").readString();
        String frag = Gdx.files.internal("shaders/fragment/tree_fragment.fsh").readString();
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
		
		lightPosition[0] = 0;
		lightPosition[1] = 0;
		lightPosition[2] = 0;
		//bind correct textures		
	    modelView.set(renderable.worldTransform);
		program.setUniformi("u_texture0", context.textureBinder.bind(((TextureAttribute)(renderable.material.get(TextureAttribute.Diffuse))).textureDescription));
		program.setUniformMatrix("u_normalMatrix", normalMatrix.set(modelView).inv().transpose());
		program.setUniform3fv("u_lightPosition", lightPosition , 0, 3);
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
	public void nextRandom(){
		random = rand.nextFloat();
		System.out.println("NEXT RANDOM");
	}
}