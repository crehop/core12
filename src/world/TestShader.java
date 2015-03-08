package world;


/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * See: http://blog.xoppa.com/creating-a-shader-with-libgdx
 * @author Xoppa
 */
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
	public void render(Renderable renderable) {
		dirt.bind(3);
		program.setUniformi("u_texture3", 3);
		rock.bind(2);
		program.setUniformi("u_texture2", 2);
		grass.bind(1);
		program.setUniformi("u_texture1", 1);
		splat.bind(0);
		program.setUniformi("u_texture0", 0);
		program.setUniformMatrix(u_worldTrans, renderable.worldTransform);
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