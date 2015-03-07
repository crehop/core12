#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoord0;
varying vec2 v_texCoord1;

uniform sampler2D u_texture0;
uniform sampler2D u_texture1;

void main(void) {
	vec4 t1 = texture2D(u_texture0, v_texCoord0) * v_color;
	vec4 t2 = texture2D(u_texture1, v_texCoord1) * v_color;
	mix(t1,t2,v_color);
	gl_FragColor = mix(t1,t2,vec4(0.5f));
}
