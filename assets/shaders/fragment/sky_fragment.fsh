#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoord0;
varying vec2 v_texCoordActual;
varying float intensity;

uniform sampler2D u_texture0;
uniform vec4 u_ambientColor;
uniform vec4 u_diffuseColor;
uniform vec4 u_specularColor;


const vec4 fog_colour = vec4(0.21, 0.22, 0.20, 1.);
vec4 add_fog(vec4 fragColour){
 	float perspective_far = 100000.0;
 	float fog_coord = (gl_FragCoord.z / gl_FragCoord.w) / perspective_far;
  	float fog_density = 6.0;
  	float fog = fog_coord * fog_density;
  	return mix(fog_colour, fragColour, clamp(1.0 - fog, 0., 1.) );
}

void main(void) {
	vec4 sky = texture2D(u_texture0, v_texCoord0);// * v_color;
	sky.a = -sky.a + (sky.r + sky.b + sky.g);
	vec4 finalMix = mix(sky,vec4(0.5,0.5,0.5,1.0), intensity);
	vec4 finalMix2 = mix(finalMix, vec4(1,1,1,1), sky.a);
	gl_FragColor = finalMix2;
}
