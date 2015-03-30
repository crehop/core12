package world;

import java.util.ArrayList;

import server.Location;
import Zomtasia.Zomtasia;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class Flora {
	Model tree;
	Model tree2;
	Location location;
	Renderable render;
	ArrayList<ModelInstance> trees = new ArrayList<ModelInstance>();;
	public Flora(){
		tree2 = Zomtasia.assets.getModel("tree1");
		seperateIntoTrees();
	}
	//public ModelInstance getModelInstance(){
	//	return tree;
	//}
	public void seperateIntoTrees(){
		Mesh mesh = tree2.meshes.get(10);
		Material material = tree2.materials.first();
		tree = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);

		/*tree2.meshes.get(0).scale(0, 0, 0);
		tree2.meshes.get(1).scale(0, 0, 0);
		tree2.meshes.get(2).scale(0, 0, 0);
		//tree2.meshes.get(3).scale(0, 0, 0);
		tree2.meshes.get(4).scale(20, 20, 20);
		tree2.meshes.get(5).scale(0, 0, 0);
		tree2.meshes.get(6).scale(0, 0, 0);
		tree2.meshes.get(7).scale(0, 0, 0);
		tree2.meshes.get(8).scale(0, 0, 0);
		tree2.meshes.get(9).scale(0, 0, 0);
		tree2.meshes.get(10).scale(0, 0, 0);
		tree2.meshes.get(11).scale(0, 0, 0);
		tree2.meshes.get(12).scale(0, 0, 0);
		tree2.meshes.get(13).scale(0, 0, 0);
		trees.add(new ModelInstance(tree2,91,-84, 480));
		*/
	}
	public ArrayList<ModelInstance> getTrees(){
		return trees;
	}
	public void createTree(float x, float y, float z) {
		ModelInstance newTree = new ModelInstance(tree,x,y,z);
		newTree.transform.scale(1, 1, 1);
		newTree.transform.translate(3.6f,0,4.3f);
		trees.add(newTree);
	}
}
