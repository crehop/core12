package world;

import Zomtasia.Zomtasia;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;


public class Lighting {
	private static final float[] lightPosition = { 5, 35, 5 };
	private static final float[] ambientColor = { 0.2f, 0.2f, 0.2f, 1.0f };
	private static final float[] diffuseColor = { 0.5f, 0.5f, 0.5f, 1.0f };
	private static final float[] specularColor = { 0.7f, 0.7f, 0.7f, 1.0f };
	private static final float[] fogColor = { 0.2f, 0.1f, 0.6f, 1.0f };
	private static Matrix4 model = new Matrix4();
	private static Matrix4 modelView = new Matrix4();
	private static final Matrix3 normalMatrix = new Matrix3();
	static boolean once = true;
	public static final String vertexShader =
	        "attribute vec4 a_position; \n" +
	        "attribute vec3 a_normal; \n" +
	        "attribute vec2 a_texCoord; \n" +
	        "attribute vec4 a_color; \n" +

	        "uniform mat4 u_MVPMatrix; \n" +
	        "uniform mat3 u_normalMatrix; \n" +

	        "uniform vec3 u_lightPosition; \n" +

	        "varying float intensity; \n" +
	        "varying vec2 texCoords; \n" +
	        "varying vec4 v_color; \n" +

	        "void main() { \n" +
	        "    vec3 normal = normalize(u_normalMatrix * a_normal); \n" +
	        "    vec3 light = normalize(u_lightPosition); \n" +
	        "    intensity = max( dot(normal, light) , 0.0); \n" +

	        "    v_color = a_color; \n" +
	        "    texCoords = a_texCoord; \n" +

	        "    gl_Position = u_MVPMatrix * a_position; \n" +
	        "}";

	public static final String fragmentShader =
	        "#ifdef GL_ES \n" +
	        "precision mediump float; \n" +
	        "#endif \n" +

	        "uniform vec4 u_ambientColor; \n" +
	        "uniform vec4 u_diffuseColor; \n" +
	        "uniform vec4 u_specularColor; \n" +

	        "uniform sampler2D u_texture; \n" +
	        "varying vec2 texCoords; \n" +
	        "varying vec4 v_color; \n" +

	        "varying float intensity; \n" +

	        "void main() { \n" +
	        "    gl_FragColor = v_color * intensity * texture2D(u_texture, texCoords); \n" +
	        "}";
	static ShaderProgram shader = new ShaderProgram(vertexShader,fragmentShader);
	public static void beginShader(){
		if(once){
			shader = new ShaderProgram(vertexShader,fragmentShader);
			once = false;
		}
	    model.setToRotation(new Vector3(0, 1, 0), 45f);
	    modelView.set(Zomtasia.cam.view).mul(model);
	    shader.begin();

	    shader.setUniformMatrix("u_MVPMatrix", Zomtasia.cam.combined);
	    shader.setUniformMatrix("u_normalMatrix", normalMatrix.set(modelView).inv().transpose());

	    shader.setUniform3fv("u_lightPosition", lightPosition, 0, 3);
	    //shader.setUniform4fv("u_ambientColor", ambientColor, 0, 4);
	    //shader.setUniform4fv("u_diffuseColor", diffuseColor, 0, 4);
	    //shader.setUniform4fv("u_specularColor", specularColor, 0, 4);
	}
	public static void endShader(){
		shader.end();
	}
	public static ShaderProgram getShader(){
		return shader;
	}
}
