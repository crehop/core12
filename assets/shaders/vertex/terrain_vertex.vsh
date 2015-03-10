//actual final recieved from openGl;
attribute vec4 a_color;
attribute vec2 a_texCoord0;
attribute vec3 a_position;

//varying passed to fragment shader, must be same variable type and name in fragment shader
varying vec4 v_color;
varying vec2 v_texCoord0;
varying vec2 v_texCoordActual;

//can set uniforms from code
uniform mat4 u_projTrans;
uniform mat4 u_worldTrans;
uniform vec2 u_resolution;

uniform float offsetU;
uniform float offsetV;
uniform float scaleU;
uniform float scaleV;

void main(void) {
	//set the varying to the actuals
	v_color = a_color;
	v_texCoordActual = a_texCoord0;
	v_texCoord0 = vec2(((a_texCoord0.x  * scaleU) + offsetU), ((a_texCoord0.y* scaleV) + offsetV));

	//gl_position is a built in variable to set the position of the vertecies
	//projTrans = currentprojection? worldTrans = where to put it? position = where to put the vertex?
    gl_Position = u_projTrans * u_worldTrans * vec4(a_position, 1.0);
}
