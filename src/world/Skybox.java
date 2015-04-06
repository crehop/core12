package world;


import Zomtasia.Zomtasia;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;

public class Skybox {
	private static boolean initiated = false;
	public static Texture noon = new Texture("skybox/1.png");
	private static Texture afternoon = new Texture("skybox/2.png");
	private static Texture dusk = new Texture("skybox/3.png");
	private static Texture evening = new Texture("skybox/4.png");
	private static Texture night = new Texture("skybox/5.png");
	private static Texture midnight = new Texture("skybox/6.png");
	public static Texture stars = new Texture("skybox/8.png");
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
				skyboxTexture.set(TextureAttribute.createDiffuse(stars));
			}
			sky.transform.setTranslation(
					Zomtasia.player.getLocation().getX() , Zomtasia.player.getLocation().getY(),
					Zomtasia.player.getLocation().getZ() );
			return sky;
		}else{
			create();
			return sky;
		}
	}
	public static void create(){
		skydome = Zomtasia.getGame().getAssets().get("skybox/skybox.g3dj", Model.class);
		Zomtasia.assets.addDisposable(skydome);
		sky = new ModelInstance(skydome,0,0,0);
		sky.transform.scl(100);
		skyboxTexture = sky.materials.get(0);
		initiated = true;
	}
}
