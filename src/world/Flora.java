package world;

import java.util.ArrayList;

import screens.Console;
import server.Location;
import Zomtasia.Zomtasia;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class Flora {
	Model tree;
	Location location;
	Renderable render;
	ArrayList<ModelInstance> trees = new ArrayList<ModelInstance>();;
	public Flora(){
		tree = Zomtasia.assets.getModel("trees");
		seperateIntoTrees(tree);
	}
	//public ModelInstance getModelInstance(){
	//	return tree;
	//}
	public void seperateIntoTrees(Model model){
		model.meshes.get(0).scale(0, 0, 0);
		model.meshes.get(1).scale(0, 0, 0);
		model.meshes.get(2).scale(0, 0, 0);
		model.meshes.get(3).scale(20, 20, 20);
		model.meshes.get(4).scale(0, 0, 0);
		model.meshes.get(5).scale(0, 0, 0);
		model.meshes.get(6).scale(0, 0, 0);
		model.meshes.get(7).scale(0, 0, 0);
		model.meshes.get(8).scale(0, 0, 0);
		model.meshes.get(9).scale(0, 0, 0);
		model.meshes.get(10).scale(0, 0, 0);
		model.meshes.get(11).scale(0, 0, 0);
		model.meshes.get(12).scale(0, 0, 0);
		model.meshes.get(13).scale(0, 0, 0);
		trees.add(new ModelInstance(model,0,10,0));
	}
	public ArrayList<ModelInstance> getTrees(){
		return trees;
	}
}
