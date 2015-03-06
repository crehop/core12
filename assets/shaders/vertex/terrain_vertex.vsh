//actual final recieved from openGl;
attribute vec4 a_color;
attribute vec2 a_texCoord0;
attribute vec3 a_position;

//varying passed to fragment shader, must be same variable type and name in fragment shader
varying vec4 v_color;
varying vec2 v_texCoord0;

//can set uniforms from code
uniform mat4 u_projTrans;
uniform vec2 u_resolution;

void main(void) {
	//set the varying to the actuals
	v_color = a_color;
	v_texCoord0 = a_texCoord0;

	//gl_position is a built in variable to set the position of the vertecies
	gl_Position = u_projTrans * vec4(a_position, 1.0);


}
