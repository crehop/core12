varying vec2 v_texCoord0;
uniform vec2 u_resolution;
uniform sampler2D u_sampler2D;


void main(void) {
	vec2 onePixel = vec2(1.0 / u_resolution.x, 1.0 / u_resolution.y);
	vec2 texCoord = v_texCoord0;

	vec3 color;
	color = vec3(0.5);

	color += texture2D(u_sampler2D, texCoord - onePixel) *5;
	color -= texture2D(u_sampler2D, texCoord + onePixel) *5;

	color = color.rgb / 3;

	gl_Position =  vec4(color, 1.0);
}
