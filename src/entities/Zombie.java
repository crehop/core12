package entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Zombie extends GameObject implements MovingEntity{
	ModelInstance zombie;
	public Zombie(Model model, float x, float y, float z, float yaw,float pitch, float roll, float scaleX, float scaleY, float scaleZ) {
		super(model, x, y, z, yaw, pitch, roll, scaleX, scaleY, scaleZ);
		zombie = new ModelInstance(model);
		zombie.animations.get(0).duration = 4f;
		// TODO Auto-generated constructor stub
	}
	public Zombie(Model model, float x, float y, float z) {
		super(model, x, y, z);
		// TODO Auto-generated constructor stub
	}
}
