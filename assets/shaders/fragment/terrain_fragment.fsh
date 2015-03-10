#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoord0;
varying vec2 v_texCoordActual;

uniform sampler2D u_texture0;
uniform sampler2D u_texture1;
uniform sampler2D u_texture2;
uniform sampler2D u_texture3;

void main(void) {
	vec4 splat = texture2D(u_texture0, v_texCoord0);// * v_color;
	vec4 grass = texture2D(u_texture1,  v_texCoordActual);  //* v_color;
	vec4 rock = texture2D(u_texture2, v_texCoordActual);   //* v_color;
	vec4 dirt = texture2D(u_texture3, v_texCoordActual); //* v_color;
	vec4 premix0 = mix(dirt,rock,splat.r);
	vec4 premix1 = mix(premix0,dirt,splat.b);
	vec4 finalMix = mix(premix1,grass,splat.g);

	gl_FragColor = finalMix;
}
