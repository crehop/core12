package world;

import java.util.Random;

import server.Location;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.math.Vector3;
public class TerrainChunk {
	public final float[] heightMap;
	public final short width;
	public final short height;
	public final float[] vertices;
	public final short[] indices;
	public final float[] waterVertices;
	public final short[] waterIndices;
	public final float[] skyVertices;
	public final short[] skyIndices;
	public final int vertexSize;
    private final int positionSize = 3;
	public Location location;
	private ModelInstance terrain;
	private ModelInstance water;
	private ModelInstance sky;
	private int xLoc = 0;
	private int yLoc = 0;
	private float waterHeight = 0.23529412f;
	private float skyHeight = 0.29529412f;
	Material material;
	private Mesh mesh;
	Random rand = new Random();
	
	public TerrainChunk (int width, int height, int vertexSize, Pixmap map, int xLoc, int yLoc) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.heightMap = new float[(width + 1) * (height + 1)];
		this.width = (short)width;
		this.height = (short)height;
		this.vertices = new float[heightMap.length * vertexSize];
		this.indices = new short[width * height * 6];
		this.waterVertices = new float[heightMap.length * vertexSize];
		this.waterIndices = new short[width * height * 6];
		this.skyVertices = new float[heightMap.length * vertexSize];
		this.skyIndices = new short[width * height * 6];
		this.vertexSize = vertexSize;
		this.location = new Location( 0,0,0);
		
		buildHeightmap(map);
		buildIndices();
		buildVertices();
		buildWaterIndices();
		buildWaterVertices();
		buildSkyIndices();
		buildSkyVertices();
        calcNormals(skyIndices, skyVertices);
        calcNormals(waterIndices, waterVertices);
        calcNormals(indices, vertices);
	}
	
	public void buildHeightmap (Pixmap map) {
		Pixmap heightmapImage = map;
		Color color = new Color();
		int idh = 0;
		for (int x = 0; x <= this.width; x++) {
			for (int y = 0; y <= this.height; y++) {
				Color.rgba8888ToColor(color, heightmapImage.getPixel((xLoc + x), (yLoc + y)));
				this.heightMap[idh++] = (color.r + color.b + color.g)/3;
			}
		}
	}

	public void buildVertices() {
	    int heightPitch = height + 1;
	    int widthPitch = width + 1;
	    int multiplier = 100; //heightmap multiplier
	    int index = 0;
	    int heightmapIndex = 0;
	    for (int z = 0; z < heightPitch; z++) {
	        for (int x = 0; x < widthPitch; x++) {

	            //POSITION
	            vertices[index++] = x;
	            vertices[index++] = heightMap[heightmapIndex++] * multiplier;
	            vertices[index++] = z;

	            //SKIP NORMALS
	            index += 3;

	            //COLOR
	            vertices[index++] = Color.WHITE.toFloatBits();

	            //TEXTURE
	            vertices[index++] = ((float)z / height);
	            vertices[index++] = ((float)x / width);
	        }
	    }
	}
	
	private void buildIndices () {
		int idx = 0;
		short pitch = (short)(width + 1);
		short i1 = 0;
		short i2 = 1;
		short i3 = (short)(1 + pitch);
		short i4 = pitch;
		
		short row = 0;

		for (int z = 0; z < height; z++) {
			for (int x = 0; x < width; x++) {
				indices[idx++] = i1;
				indices[idx++] = i3; //i3 is exchanged
				indices[idx++] = i2; //with i2

				indices[idx++] = i3;
				indices[idx++] = i1; //i1 is exchanged
				indices[idx++] = i4; //with i4

				i1++;
				i2++;
				i3++;
				i4++;
			}
			
			row += pitch;
			i1 = row;
			i2 = (short)(row + 1);
			i3 = (short)(i2 + pitch);
			i4 = (short)(row + pitch);
		}
	}
	
	
	public void buildWaterVertices() {
	    int heightPitch = height + 1;
	    int widthPitch = width + 1;
	    int strength = 100; //heightmap multiplier
	    int idx = 0;
	    for (int z = 0; z < heightPitch; z++) {
	        for (int x = 0; x < widthPitch; x++) {

	            //POSITION
	            waterVertices[idx++] = x;
	            waterVertices[idx++] = waterHeight * strength;
	            waterVertices[idx++] = z;

	            //SKIP NORMALS
	            idx += 3;

	            //COLOR
	            waterVertices[idx++] = Color.BLUE.toFloatBits();

	            //TEXTURE
	            waterVertices[idx++] = ((float)z / height);
	            waterVertices[idx++] = ((float)x / width);
	        }
	    }
	}
	
	private void buildWaterIndices () {
		int idx = 0;
		short pitch = (short)(width + 1);
		short i1 = 0;
		short i2 = 1;
		short i3 = (short)(1 + pitch);
		short i4 = pitch;
		
		short row = 0;

		for (int z = 0; z < height; z++) {
			for (int x = 0; x < width; x++) {
				waterIndices[idx++] = i1;
				waterIndices[idx++] = i3; //i3 is exchanged
				waterIndices[idx++] = i2; //with i2

				waterIndices[idx++] = i3;
				waterIndices[idx++] = i1; //i1 is exchanged
				waterIndices[idx++] = i4; //with i4

				i1++;
				i2++;
				i3++;
				i4++;
			}
			
			row += pitch;
			i1 = row;
			i2 = (short)(row + 1);
			i3 = (short)(i2 + pitch);
			i4 = (short)(row + pitch);
		}
	}
	
	public void buildSkyVertices() {
	    int heightPitch = height + 1;
	    int widthPitch = width + 1;
	    int strength = 100; //heightmap multiplier
	    int idx = 0;
	    for (int z = 0; z < heightPitch; z++) {
	        for (int x = 0; x < widthPitch; x++) {

	            //POSITION
	            skyVertices[idx++] = x;
	            skyVertices[idx++] = skyHeight * strength;
	            skyVertices[idx++] = z;

	            //SKIP NORMALS
	            idx += 3;

	            //COLOR
	            skyVertices[idx++] = Color.WHITE.toFloatBits();

	            //TEXTURE
	            skyVertices[idx++] = -((float)z / height);
	            skyVertices[idx++] = -((float)x / width);
	        }
	    }
	}
	
	private void buildSkyIndices () {
		int idx = 0;
		short pitch = (short)(width + 1);
		short i1 = 0;
		short i2 = 1;
		short i3 = (short)(1 + pitch);
		short i4 = pitch;
		
		short row = 0;

		for (int z = 0; z < height; z++) {
			for (int x = 0; x < width; x++) {
				skyIndices[idx++] = i1;
				skyIndices[idx++] = i3; //i3 is exchanged
				skyIndices[idx++] = i2; //with i2

				skyIndices[idx++] = i3;
				skyIndices[idx++] = i1; //i1 is exchanged
				skyIndices[idx++] = i4; //with i4

				i1++;
				i2++;
				i3++;
				i4++;
			}
			
			row += pitch;
			i1 = row;
			i2 = (short)(row + 1);
			i3 = (short)(i2 + pitch);
			i4 = (short)(row + pitch);
		}
	}
	

	public boolean needsGL20 () {
		return false;
	}
	public Location getLocation(){
		return location;
	}
	public void setLocation(float x, float y, float z){
		this.location.setX(x);
		this.location.setY(y-100);
		this.location.setZ(z);
	}
	public void setLocation(Location location){
		this.location = location;
	}
	public void setModelInstance(ModelInstance instance){
		this.terrain = instance;
		this.terrain.transform.translate(location.getPosition());
	}
	public void setWaterModelInstance(ModelInstance instance2){
		this.water = instance2;
		this.water.transform.translate(location.getPosition());
	}
	public void setSkyModelInstance(ModelInstance instance3){
		this.sky = instance3;
		this.sky.transform.rotate(1,0,0,180);
		this.sky.transform.rotate(0,1,0,90);
		this.sky.transform.translate(location.getPosition().x, 0, location.getPosition().z);
	}
	public ModelInstance getTerrain(){
		return this.terrain;
	}
	public ModelInstance getWater(){
		return this.water;
	}

    // Gets the index of the first float of a normal for a specific vertex
    private int getNormalStart(int vertIndex) {
        return vertIndex * vertexSize + positionSize;
    }

    // Gets the index of the first float of a specific vertex
    private int getPositionStart(int vertIndex) {
        return vertIndex * vertexSize;
    }

    // Adds the provided value to the normal
    private void addNormal(int vertIndex, float[] verts, float x, float y, float z) {

        int i = getNormalStart(vertIndex);

        verts[i] += x;
        verts[i + 1] += y;
        verts[i + 2] += z;
    }
    private void normalizeNormal(int vertIndex, float[] verts) {

        int i = getNormalStart(vertIndex);

        float x = verts[i];
        float y = verts[i + 1];
        float z = verts[i + 2];

        float num2 = ((x * x) + (y * y)) + (z * z);
        float num = 1f / (float) Math.sqrt(num2);
        x *= num;
        y *= num;
        z *= num;

        verts[i] = x;
        verts[i + 1] = y;
        verts[i + 2] = z;
    }
    private void calcNormals(short[] indices, float[] verts) {

        for (int i = 0; i < indices.length; i += 3) {
            int i1 = getPositionStart(indices[i]);
            int i2 = getPositionStart(indices[i + 1]);
            int i3 = getPositionStart(indices[i + 2]);

            // p1
            float x1 = verts[i1];
            float y1 = verts[i1 + 1];
            float z1 = verts[i1 + 2];

            // p2
            float x2 = verts[i2];
            float y2 = verts[i2 + 1];
            float z2 = verts[i2 + 2];

            // p3
            float x3 = verts[i3];
            float y3 = verts[i3 + 1];
            float z3 = verts[i3 + 2];

            // u = p3 - p1
            float ux = x3 - x1;
            float uy = y3 - y1;
            float uz = z3 - z1;

            // v = p2 - p1
            float vx = x2 - x1;
            float vy = y2 - y1;
            float vz = z2 - z1;

            // n = cross(v, u)
            float nx = (vy * uz) - (vz * uy);
            float ny = (vz * ux) - (vx * uz);
            float nz = (vx * uy) - (vy * ux);

            // normalize(n)
            float num2 = ((nx * nx) + (ny * ny)) + (nz * nz);
            float num = 1f / (float) Math.sqrt(num2);
            nx *= num;
            ny *= num;
            nz *= num;

            addNormal(indices[i], verts, nx, ny, nz);
            addNormal(indices[i + 1], verts, nx, ny, nz);
            addNormal(indices[i + 2], verts, nx, ny, nz);
        }

        for (int i = 0; i < (verts.length / vertexSize); i++) {
            normalizeNormal(i, verts);
        }
    }
    public Mesh getMesh(){
    	return mesh;
    }

	public ModelInstance getSky() {
		return sky;
	}
}