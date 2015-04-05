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
	int count = 0;
	ArrayList<GameObject> trees = new ArrayList<GameObject>();;
	public Flora(){
		tree = Zomtasia.assets.getModel("tree1"); 
		seperateIntoTrees();
	}
	public void seperateIntoTrees(){
		//DO NOT USE (USELESS SHADOW)
		Mesh mesh = tree.meshes.get(0);
		Material material = tree.materials.get(2);
		tree1 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK medium leafy light tree
		material = tree.materials.get(3);
		mesh = tree.meshes.get(1);
		tree2 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//DONT USE, BAD TREE
		material = tree.materials.get(3);
		mesh = tree.meshes.get(2);
		tree3 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK smallest leafy light tree
		material = tree.materials.get(4);
		mesh = tree.meshes.get(3);
		tree4 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK medium leafy light tree
		material = tree.materials.get(4);
		mesh = tree.meshes.get(4);
		tree5 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK biggest leafy light tree
		material = tree.materials.get(5);
		mesh = tree.meshes.get(5);
		tree6 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK big leafy light tree
		material = tree.materials.get(6);
		mesh = tree.meshes.get(6);
		tree7 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//DO NOT USE USELESS Shadow 2;
		material = tree.materials.get(2);
		mesh = tree.meshes.get(7);
		tree8 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK (Medium leafy tree)
		material = tree.materials.get(3);
		mesh = tree.meshes.get(8);
		tree9 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK (PINE3 SMALL)
		material = tree.materials.get(0);
		mesh = tree.meshes.get(9);
		tree10 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK (PINE2 TALL)
		material = tree.materials.get(0);
		mesh = tree.meshes.get(10);
		tree11 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK (PINE1 MEDIUM)
		material = tree.materials.get(0);
		mesh = tree.meshes.get(11);
		tree12 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK (LEAFY TREEN MEDIUM)
		material = tree.materials.get(3);
		mesh = tree.meshes.get(12);
		tree13 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
		//OK (SHADOW)
		material = tree.materials.get(1);
		mesh = tree.meshes.get(13);
		tree14 = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
		createTree(0,10,0);
	}
	public ArrayList<GameObject> getTrees(){
		return trees;
	}
	public void createTree(float x, float y, float z) {
		GameObject newTree;
		switch(count){
		case 0:
			newTree = new GameObject(tree1,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 1:
			newTree = new GameObject(tree2,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 2:
			newTree = new GameObject(tree3,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 3:
			newTree = new GameObject(tree4,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			return;
		case 4:
			newTree = new GameObject(tree5,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 5:
			newTree = new GameObject(tree6,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 6:
			newTree = new GameObject(tree7,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 7:
			newTree = new GameObject(tree8,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 8:
			newTree = new GameObject(tree9,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 9:
			newTree = new GameObject(tree10,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 10:
			newTree = new GameObject(tree11,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 11:
			newTree = new GameObject(tree12,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 12:
			newTree = new GameObject(tree13,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		case 13:
			newTree = new GameObject(tree14,x,y,z);
			newTree.transform.translate(3.6f,0,4.3f);
			trees.add(newTree);
			count++;
			if(count>13) {
				count = 0;
			}
			lastMoved = newTree;
			return;
		default:
			return;
		}
	}
}
