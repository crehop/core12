//actual final recieved from openGl;
attribute vec4 a_color;
attribute vec2 a_texCoord0;
attribute vec3 a_position;
attribute vec3 a_normal;

//varying passed to fragment shader, must be same variable type and name in fragment shader
varying vec4 v_color;
varying vec2 v_texCoord0;
varying float intensity;

//can set uniforms from code
uniform mat4 u_projTrans;
uniform mat4 u_worldTrans;
uniform mat3 u_normalMatrix;
uniform vec3 u_lightPosition;

void main(void) {
	//set the varying to the actuals
	v_color = a_color;
	v_texCoord0 = a_texCoord0;
	vec3 normal = normalize(u_normalMatrix * a_normal);
	vec3 light = normalize(u_lightPosition);
	intensity = max(dot(normal, light),0.0);
	//gl_position is a built in variable to set the position of the vertecies
	//projTrans = currentprojection? worldTrans = where to put it? position = actual vertex?
    gl_Position = u_projTrans * u_worldTrans * vec4(a_position, 1.0);
}
