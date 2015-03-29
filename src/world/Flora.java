package world;

import java.util.ArrayList;
import server.Location;
import Zomtasia.Zomtasia;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;

public class Flora {
	Model tree;
	Model tree2;
	Location location;
	Renderable render;
	ArrayList<ModelInstance> trees = new ArrayList<ModelInstance>();;
	public Flora(){
		tree = Zomtasia.assets.getModel("tree1");
		tree2 = Zomtasia.assets.getModel("tree2");
		seperateIntoTrees();
	}
	//public ModelInstance getModelInstance(){
	//	return tree;
	//}
	public void seperateIntoTrees(){
		tree.meshes.get(0).scale(0, 0, 0);
		tree.meshes.get(1).scale(0, 0, 0);
		tree.meshes.get(2).scale(0, 0, 0);
		tree.meshes.get(3).scale(0, 0, 0);
		tree.meshes.get(4).scale(0, 0, 0);
		tree.meshes.get(5).scale(0, 0, 0);
		tree.meshes.get(6).scale(0, 0, 0);
		tree.meshes.get(7).scale(0, 0, 0);
		tree.meshes.get(8).scale(0, 0, 0);
		tree.meshes.get(9).scale(0, 0, 0);
		tree.meshes.get(10).scale(1, 1, 1);
		tree.meshes.get(11).scale(0, 0, 0);
		tree.meshes.get(12).scale(0, 0, 0);
		tree.meshes.get(13).scale(0, 0, 0);
		ModelInstance treeInstance = new ModelInstance(tree,0,0,0);
		treeInstance.transform.scale(1, 1, 1);
		trees.add(treeInstance);
		
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
}
