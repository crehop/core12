//actual final recieved from openGl;
attribute vec4 a_color;
attribute vec2 a_texCoord0;
attribute vec3 a_position;
attribute vec3 a_normal;
 
//varying passed to fragment shader, must be same variable type and name in fragment shader
varying vec4 v_color;
varying vec2 v_texCoord0;
varying vec2 v_texCoordActual;
varying float intensity;
varying vec3 w_position;
varying int v_random;



//can set uniforms from code
uniform mat4 u_projTrans;
uniform mat4 u_worldTrans;
uniform mat3 u_normalMatrix;
uniform vec3 u_lightPosition;
uniform vec2 u_resolution;
uniform float waveTime;
uniform float waveWidth;
uniform float waveHeight;
uniform int u_random;
 
void main(void){
	vec4 v = vec4(gl_Vertex);
	v.z = sin(waveWidth * v.x + waveTime) * cos(waveWidth * v.y + waveTime) * waveHeight;
  	vec3 normalDirection = normalize(-a_normal);
  	vec3 lightDirection = normalize(vec3(-u_lightPosition));
  	vec4 diffuse = vec4(1.0, 1.0, 1.0, 0.0);
	vec4 materialDiffuse = vec4(0.0, 0.0, 0.1, 1.0);
  	vec3 diffuseReflection
    	= vec3(diffuse) * vec3(materialDiffuse)
    	* max(0.0, dot(normalDirection, lightDirection));
  	v_color = vec4(diffuseReflection * v, 1.0);
	v_texCoord0 = a_texCoord0;
	v_random = u_random;	
	vec3 normal = normalize(u_lightPosition * a_normal  * u_normalMatrix);
	vec3 light = normalize(u_lightPosition);
	intensity = max(dot(normal, light),0.0);
	//gl_position is a built in variable to set the position of the vertecies
	//projTrans = currentprojection? worldTrans = where to put it? position = actual vertex?
    gl_Position = u_projTrans * u_worldTrans * vec4(a_position, 1.0);
}
