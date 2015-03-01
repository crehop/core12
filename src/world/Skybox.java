package world;


import screens.Console;
import server.Location;
import Zomtasia.Zomtasia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;

import control.Controls;

public class Skybox {
	private static boolean initiated = false;
    private static Texture noon = new Texture("skybox/1.png");
	private static Texture afternoon = new Texture("skybox/2.png");
	private static Texture dusk = new Texture("skybox/3.png");
	private static Texture evening = new Texture("skybox/4.png");
	private static Texture night = new Texture("skybox/5.png");
	private static Texture midnight = new Texture("skybox/6.png");
	private static Texture stars = new Texture("skybox/8.png");
	public static Material skyboxTexture;

	private static Model skydome;
	private static ModelInstance sky;
	//public static ModelInstance sky2;
	public static ModelInstance render(){
		if(initiated){
			if(Time.getTime() > 5.0f && Time.getTime() < 10.0f){
				skyboxTexture.set(TextureAttribute.createDiffuse(noon));
			}else if((Time.getTime() > 10 && Time.getTime() < 15.0f)){
				skyboxTexture.set(TextureAttribute.createDiffuse(noon));
			}else if((Time.getTime() > 15 && Time.getTime() < 20.0f)){
				skyboxTexture.set(TextureAttribute.createDiffuse(noon));
			}else if((Time.getTime() > 20 && Time.getTime() < 25.0f)){
				skyboxTexture.set(TextureAttribute.createDiffuse(noon));
			}else if((Time.getTime() > 25 && Time.getTime() < 30.0f)){
				skyboxTexture.set(TextureAttribute.createDiffuse(noon));
			}else if((Time.getTime() > 30 && Time.getTime() < 35.0f)){
				skyboxTexture.set(TextureAttribute.createDiffuse(noon));
			}else if((Time.getTime() > 35)){
				skyboxTexture.set(TextureAttribute.createDiffuse(noon));
			}
			sky.transform.setTranslation(
					Zomtasia.player.getLocation().getX() + 1, Zomtasia.player.getLocation().getY() - 400,
					Zomtasia.player.getLocation().getZ() );
			return sky;
		}else{
			create();
			return sky;
		}
	}
	public static void create(){
		skydome = Zomtasia.getGame().getAssets().get("skybox/skybox.g3dj", Model.class);
		sky = new ModelInstance(skydome,0,0,0);
		sky.transform.scl(1500);
		skyboxTexture = sky.materials.get(0);
		initiated = true;
		System.out.println("============================");
		System.out.println("1:" + sky.model.meshes.size);
		System.out.println("2:" + sky.model.nodes.size);
		System.out.println("3:" + sky.model.nodes.first().parts.size);
		System.out.println("4:" + sky.materials.size);
		System.out.println("5:" + sky.animations.size);
		System.out.println("6:" + sky.nodes.first().parts.size);
		System.out.println("7:" + sky.materials.size);
		System.out.println("============================");
	}
}
