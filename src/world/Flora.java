package world;

import java.util.ArrayList;

import server.Location;
import Zomtasia.Zomtasia;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import entities.GameObject;

public class Flora {
	// Materials breakdown;
	// 0 pine
	// 1 light penetrating shadow
	// 2 dense shadow
	// 3 leafy light colored tree
	// 4 big leafy light colored tree
	// 5 small leafy darker tree
	// 6 big leafy light colored tree 2
	// 7 pine
	// 8 pine
	public GameObject lastMoved;
	Model tree;
	Model tree1;
	Model tree2;
	Model tree3;
	Model tree4;
	Model tree5;
	Model tree6;
	Model tree7;
	Model tree8;
	Model tree9;
	Model tree10;
	Model tree11;
	Model tree12;
	Model tree13;
	Model tree14;
	Location location;
	ArrayList<GameObject> trees = new ArrayList<GameObject>();;
	public Flora(){
		tree = Zomtasia.assets.getModel("tree1"); 
		seperateIntoTrees();
	}
	@SuppressWarnings("deprecation")
	public void seperateIntoTrees(){
		//DO NOT USE (USELESS SHADOW)
		Mesh mesh = tree.meshes.get(0);
		Material material = tree.materials.get(2);
		tree1 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK medium leafy light tree
		material = tree.materials.get(3);
		mesh = tree.meshes.get(1);
		tree2 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//DONT USE, BAD TREE
		material = tree.materials.get(3);
		mesh = tree.meshes.get(2);
		tree3 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK smallest leafy light tree
		material = tree.materials.get(4);
		mesh = tree.meshes.get(3);
		tree4 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK medium leafy light tree
		material = tree.materials.get(4);
		mesh = tree.meshes.get(4);
		tree5 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK biggest leafy light tree
		material = tree.materials.get(5);
		mesh = tree.meshes.get(5);
		tree6 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK big leafy light tree
		material = tree.materials.get(6);
		mesh = tree.meshes.get(6);
		tree7 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//DO NOT USE USELESS Shadow 2;
		material = tree.materials.get(2);
		mesh = tree.meshes.get(7);
		tree8 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK (Medium leafy tree)
		material = tree.materials.get(3);
		mesh = tree.meshes.get(8);
		tree9 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK (PINE3 SMALL)
		material = tree.materials.get(0);
		mesh = tree.meshes.get(9);
		tree10 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK (PINE2 TALL)
		material = tree.materials.get(0);
		mesh = tree.meshes.get(10);
		tree11 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK (PINE1 MEDIUM)
		material = tree.materials.get(0);
		mesh = tree.meshes.get(11);
		tree12 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK (LEAFY TREEN MEDIUM)
		material = tree.materials.get(3);
		mesh = tree.meshes.get(12);
		tree13 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		//OK (SHADOW)
		material = tree.materials.get(1);
		mesh = tree.meshes.get(13);
		tree14 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		makeDisposable();
	}
	private void makeDisposable(){
		Zomtasia.assets.addDisposable(tree);
		Zomtasia.assets.addDisposable(tree1);
		Zomtasia.assets.addDisposable(tree2);
		Zomtasia.assets.addDisposable(tree3);
		Zomtasia.assets.addDisposable(tree4);
		Zomtasia.assets.addDisposable(tree5);
		Zomtasia.assets.addDisposable(tree6);
		Zomtasia.assets.addDisposable(tree7);
		Zomtasia.assets.addDisposable(tree8);
		Zomtasia.assets.addDisposable(tree9);
		Zomtasia.assets.addDisposable(tree10);
		Zomtasia.assets.addDisposable(tree11);
		Zomtasia.assets.addDisposable(tree12);
		Zomtasia.assets.addDisposable(tree13);
		Zomtasia.assets.addDisposable(tree14);
	}
	public ArrayList<GameObject> getTrees(){
		return trees;
	}
	public void createTree(int count, float x, float y, float z) {
		GameObject newTree;
		switch(count){
		case 0:
			return;
		case 1:
			newTree = new GameObject(tree2,x + -8.25f,y + 0.02f,z + -10.8149f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 2:
			newTree = new GameObject(tree3,x + -8.25f,y + 0.01f,z + 3.12f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 3:
			newTree = new GameObject(tree4,x + 13.374f,y + 0.015f,z + 3.039f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 4:
			newTree = new GameObject(tree5,x + 13.37f,y + 0.01f,z + -3.34f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 5:
			newTree = new GameObject(tree6,x + 2.844f,y + 0.01f,z + 11.79f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 6:
			newTree = new GameObject(tree7,x + 3.299f,y + 0.0f,z + -13.48f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 7:
			return;
		case 8:
			newTree = new GameObject(tree9,x + -8.267f,y + 0.0f,z + -4.04f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 9:
			newTree = new GameObject(tree10,x + 3.62f,y + 0,z + -1.6f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 10:
			newTree = new GameObject(tree11,x + 3.6f,y + 0,z + 4.3f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 11:
			newTree = new GameObject(tree12,x + 3.67f,y + 0,z + -6.96f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 12:
			newTree = new GameObject(tree13,x + -8.24f,y + 0.0f,z + 9.88f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		case 13:
			newTree = new GameObject(tree14,x + -8.32f,y + -0.09f,z + 9.95f);
			newTree.transform.translate(-8.32f,-0.09f,9.95f);
			trees.add(newTree);
			lastMoved = newTree;
			return;
		default:
			return;
		}
	}
}
