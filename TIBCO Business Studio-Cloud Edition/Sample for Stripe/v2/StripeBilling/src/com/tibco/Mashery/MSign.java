package com.tibco.Mashery;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MSign {
	
		
	/**
	 * @param args
	 */
	
//	@SuppressWarnings("null")
	
	public static String getSign(String apikey, String secret) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		long epoch = System.currentTimeMillis()/1000;
		String timestamp = Long.toString(epoch);
		String sig = MD5(apikey + secret + timestamp); 
		System.out.println(sig);
		return(sig);
		
		
	      
	}
	private static String MD5(String text)
		    throws NoSuchAlgorithmException, UnsupportedEncodingException  {
		        MessageDigest md;
		        md = MessageDigest.getInstance("MD5");
		        byte[] md5hash = new byte[32];
		        md.update(text.getBytes("iso-8859-1"), 0, text.length());
		        md5hash = md.digest();
		        return convertToHex(md5hash);
		    }
	private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
           } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

}

