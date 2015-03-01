package world;
import screens.Console;
import Zomtasia.Zomtasia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Terrain {
		private TerrainChunk chunk;
		private PerspectiveCamera camera;
		private Texture grass;
		private Texture mHeightMap;
		private TextureRegion mRegion;
		private int chunkSize = 170;
		private TextureRegion[][] chunkRegions;
		public TerrainChunk[][] terrainChunks;	
	    int chunkNum = 0;
	    // Scale of the terrain
		float scale = 200;
		// position 3, normal 3, color 1, texture 2
		static int vertexSize = 3 + 3 + 1 + 2;
		public int indexOffset = 0;
		public boolean once = true;
		
		public void create () {
			grass = new Texture("terrain/grass.png");
			camera = Zomtasia.cam;
	        mHeightMap = new Texture("data/heightmap.png");
	        mRegion = new TextureRegion(mHeightMap, 0,0, mHeightMap.getWidth() ,mHeightMap.getHeight());
	        chunkRegions = mRegion.split(chunkSize, chunkSize);
	        terrainChunks = new TerrainChunk[chunkRegions.length][chunkRegions[0].length];
	        Pixmap map = new Pixmap(Gdx.files.internal("data/heightmap.png"));

	        for(int x = 0; x < chunkRegions.length; x++){
	        	for(int y = 0; y < chunkRegions[x].length; y++){
	        		Material material = new Material(ColorAttribute.createDiffuse(Color.WHITE), ColorAttribute.createSpecular(Color.WHITE),
	        		        FloatAttribute.createShininess(119f), TextureAttribute.createDiffuse(grass));
	    			
	        		//Create Chunk
	        		chunk = new TerrainChunk(chunkSize, chunkSize, vertexSize, map, chunkRegions[x][y].getRegionX(),chunkRegions[x][y].getRegionY());
	        		chunk.setLocation(x * chunkSize, 0, y * chunkSize);
	        		terrainChunks[x][y] = chunk;
	        		
	        		//Create Mesh
	    			Mesh mesh = new Mesh(true, chunk.vertices.length / 9, chunk.indices.length,
	    			        new VertexAttribute(VertexAttributes.Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE), 
	    			        new VertexAttribute(VertexAttributes.Usage.Normal, 3, ShaderProgram.NORMAL_ATTRIBUTE),
	    			        new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, ShaderProgram.COLOR_ATTRIBUTE), 
	    			        new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2,  ShaderProgram.TEXCOORD_ATTRIBUTE));
	    			mesh.setVertices(chunk.vertices);
	    			mesh.setIndices(chunk.indices);
	    			
	    			Model result = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
	    			ModelInstance modelInstance = new ModelInstance(result, 0,0,0);
	    			modelInstance.model.meshes.set(0,mesh);
	    		    modelInstance.transform.scl(scale);

	    		    //Finish Chunk
	    		    chunk.setModelInstance(modelInstance,mesh);
	    		    chunkNum++;
	    		    
	    		    if(once){
	    		    	Mesh mesh2 = modelInstance.model.nodes.get(0).parts.get(0).meshPart.mesh;
	    		    	float[] temp = new float[mesh.getNumVertices()];
	    		    	mesh2.getVertices(temp);
	    		    	int counter = 0;
	    		    	String tempLine = "";
		    			System.out.println("" + mesh.getNumVertices() + " :" + mesh.getNumIndices());
	    		    	for(float temp2:temp){
	    		    		tempLine += (temp2 + " , ");
	    		    		counter++;
	    		    		if(counter == 9){
		    		    		System.out.println("DEBUG VERTEX INFO:" + tempLine);
		    		    		tempLine = "";
		    		    		counter = 0;
	    		    		}
	    		    	}
	    		    	once = false;
	    		    }
	        	}
	        }
	        map.dispose();
		}
		public TerrainChunk[][] getChunks(){
			return this.terrainChunks;
		}
		public int getTerrainChunkLength(){
			return this.terrainChunks.length;
		}
		public int getTerrainChunkWidth(){
			return this.terrainChunks[0].length;
		}
		public TerrainChunk getTerrainChunk(int x,int y){
			return this.terrainChunks[x][y];
		}
		public void offset(boolean toggle){
			if(toggle == true){
				indexOffset++;
				create();
				Console.setLine7("INDEX OFFSET = " + indexOffset);
			}else{
				indexOffset--;
				create();
				Console.setLine7("INDEX OFFSET = " + indexOffset);
			}
		}
}

