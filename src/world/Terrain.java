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
import com.badlogic.gdx.math.Matrix3;

public class Terrain {
		private TerrainChunk chunk;
		private PerspectiveCamera camera;
		private Texture terrain;
		private Texture mHeightMap;
		private TextureRegion tRegion;
		private TextureRegion mRegion;
		private int chunkSize = 51;
		private TextureRegion[][] chunkRegions;
		private TextureRegion[][] terrainRegions;
		public TerrainChunk[][] terrainChunks;	
	    int chunkNum = 0;
	    // Scale of the terrain
		float scale = 25;
		// position 3, normal 3, color 1, texture 2
		static int vertexSize = 3 + 3 + 1 + 2;
		public int indexOffset = 0;
		
		public void create () {
			terrain = new Texture("terrain/terrain.png");
			camera = Zomtasia.cam;
	        mHeightMap = new Texture("terrain/heightmap.png");
	        mRegion = new TextureRegion(mHeightMap, 0,0, mHeightMap.getWidth() ,mHeightMap.getHeight());
	        tRegion = new TextureRegion(terrain, 0,0, terrain.getWidth() ,terrain.getHeight());
	        chunkRegions = mRegion.split(chunkSize, chunkSize);
	        terrainRegions = tRegion.split(chunkSize, chunkSize);
	        terrainChunks = new TerrainChunk[chunkRegions.length][chunkRegions[0].length];
	        Pixmap map = new Pixmap(Gdx.files.internal("terrain/heightmap.png"));

	        for(int x = 0; x < chunkRegions.length; x++){
	        	for(int y = 0; y < chunkRegions[x].length; y++){
	        		Material material = new Material(ColorAttribute.createDiffuse(Color.WHITE), ColorAttribute.createSpecular(Color.WHITE),
	        		        FloatAttribute.createShininess(119f), TextureAttribute.createDiffuse(terrainRegions[x][y]));
	    			
	        		//Create Chunk
	        		chunk = new TerrainChunk(chunkSize, chunkSize, vertexSize, map, chunkRegions[x][y].getRegionX(),chunkRegions[x][y].getRegionY());
	        		chunk.setLocation(x * chunkSize, 0, y * chunkSize);
	        		terrainChunks[x][y] = chunk;
	        		
	        		//Create Mesh
	    			Mesh mesh = new Mesh(true, chunk.vertices.length / 9, chunk.indices.length,
	    			        new VertexAttribute(VertexAttributes.Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE), 
	    			        new VertexAttribute(VertexAttributes.Usage.Normal, 3, ShaderProgram.NORMAL_ATTRIBUTE),
	    			        new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, ShaderProgram.COLOR_ATTRIBUTE), 
	    			        new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2,  ShaderProgram.TEXCOORD_ATTRIBUTE + "0", 0));
	    			mesh.setVertices(chunk.vertices);
	    			mesh.setIndices(chunk.indices);
	    			
	    			Model result = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES, material);
	    			ModelInstance modelInstance = new ModelInstance(result, 0,-100,0);
	    			modelInstance.transform.scl(scale);

	    		    //Finish Chunk
	    		    chunk.setModelInstance(modelInstance,mesh);
	    		    chunkNum++;
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
}

