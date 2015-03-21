#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoord0;
varying vec2 v_texCoordActual;
varying float intensity;

uniform sampler2D u_texture0;
uniform sampler2D u_texture1;
uniform sampler2D u_texture2;
uniform sampler2D u_texture3;
uniform sampler2D u_texture4;

uniform vec4 u_ambientColor;
uniform vec4 u_diffuseColor;
uniform vec4 u_specularColor;


const vec4 fog_colour = vec4(0.21, 0.22, 0.20, 1.);
vec4 add_fog(vec4 fragColour){
 	float perspective_far = 100000.0;
 	float fog_coord = (gl_FragCoord.z / gl_FragCoord.w) / perspective_far;
  	float fog_density = 9.0;
  	float fog = fog_coord * fog_density;
  	return mix(fog_colour, fragColour, clamp(1.0 - fog, 0., 1.) );
}

void main(void) {
	vec4 splat = texture2D(u_texture0, v_texCoord0);// * v_color;
	vec4 grass = texture2D(u_texture1,  v_texCoordActual);  //* v_color;
	vec4 rock = texture2D(u_texture2, v_texCoordActual);   //* v_color;
	vec4 dirt = texture2D(u_texture3, v_texCoordActual); //* v_color;

	vec4 premix0 = mix(dirt,rock,splat.r);
	vec4 premix1 = mix(premix0,dirt,splat.b);
	vec4 finalMix = mix(premix1,grass,splat.g);

	gl_FragColor = add_fog((finalMix + mix(finalMix, vec4(-intensity,-intensity,-intensity,1), intensity)));
}
