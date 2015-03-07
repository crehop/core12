//actual final recieved from openGl;
attribute vec4 a_color;
attribute vec2 a_texCoord0;
attribute vec2 a_texCoord1;
attribute vec3 a_position;

//varying passed to fragment shader, must be same variable type and name in fragment shader
varying vec4 v_color;
varying vec2 v_texCoord0;
varying vec2 v_texCoord1;

//can set uniforms from code
uniform mat4 u_projTrans;
uniform mat4 u_worldTrans;
uniform vec2 u_resolution;

void main(void) {
	//set the varying to the actuals
	v_color = a_color;
	v_texCoord0 = a_texCoord0;
	v_texCoord1 = a_texCoord1;

	//gl_position is a built in variable to set the position of the vertecies
	//projTrans = currentprojection? worldTrans = where to put it? position = where to put the vertex?
    gl_Position = u_projTrans * u_worldTrans * vec4(a_position, 1.0);
}
