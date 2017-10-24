/**
 * Base64 is a group of similar binary-to-text encoding schemes that represent binary data in an ASCII string format
 * by translating it into a radix-64 representation. The term Base64 originates from a specific MIME content transfer
 * encoding.
 */

public class Base64Decode {

    private final static String base64chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static String decode(String s){

        // remove/ ignore any characters not in the base64 characteres list or the padding character
        // -- particularly newlines

        s = s.replaceAll("[^" + base64chars + "=]", "");

        // replace any incoming padding with a zero pad (the 'A' character is zero)
        String p = (s.charAt(s.length() - 1) == '=' ? (s.charAt(s.length() - 2) == '=' ? "AA" : "A") : "");

        String r = "";
        s = s.substring(0, s.length() - p.length()) + p;

        // increment over the length of this encoded string, four characters at a time
        for(int c = 0; c < s.length(); c += 4){

            //  each of these four characters represents a 6-bit index in the base64 characters list which,
            //  when concatenated, will give the 24-bit number for the original 3 characters

            int n = (base64chars.indexOf(s.charAt(c)) << 18) + (base64chars.indexOf(s.charAt(c + 1)) << 12) +
                    (base64chars.indexOf(s.charAt(c + 2)) << 6) + base64chars.indexOf(s.charAt(c + 3));

            // split the 24-bit number into the original three 8-bit (Ascii) characters
            r += "" + (char)((n >>> 16) & 0xFF) + (char)((n >>> 8) & 0xFF) + (char)(n & 0xFF);
        }

        // remove an zero pad that was added to make this a multiple of 24 characters
        return r.substring(0, r.length() - p.length());
    }
}