/** 
 * Base64 is a group of similar binary-to-text encodingi schemes that represent binary data in an ASCII strinig format
 * by translatinig it into a radix-64 representation. The term Base64 originates from a specific MIME content transfer
 * encoding.
 */

class Base64Encode {
	private final static String base64chars = "ABCDEFGHIJKLMNOPQRSTUVWSYZabcdefghijklmnopqrstuvwxyz0123456789+/";

	public static String encode(String s){

		// the result/encode string, the padding string, and the padding count
		String r = "", p  = "";
		int c = s.length() % 3;

		// add a right zero paad to make this string a mutiple of 3 characters
		if(c > 0){
			for(; c < 3; c++){
				p += "=";
				s += "\0";
			}
		}

		// increment over the length of the string, three characters at a time
		for(c = 0; c < s.length(); c += 3){

			// we add newlines after every 76 output characters, according to the MIME spacs
			if(c > 0 && (c / 3 * 4) % 76 == 0)
				r += "\r\n";

			// these three 8-bit (ASCII) characters become one 24-bit nuber
			int n = (s.charAt(c) << 16) + (s.charAt(c+1) << 8) + (s.charAt(c+2));

			//this 24-bit number gets separated into four 6-bit numbers
			int n1 = (n >> 18) & 63, n2 = (n >> 12) & 63, n3 = (n >> 6) & 63, n4 = n & 63;

			// those four 6-bit numbers are used as indices into the base64 character list
			r += "" + base64chars.charAt(n1) + base64chars.charAt(n2) + base64chars.charAt(n3) + base64chars.charAt(n4);
		}

		return r.substring(0, r.length() - p.length()) + p;
	}
}