package com.mingcai.edu.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class PassUtil {

	
    private String algorithm = "MD5";
    private int iterations = 1;
	/**
     * Encodes the rawPass using a MessageDigest.
     * If a salt is specified it will be merged with the password before encoding.
     *
     * @param rawPass The plain text password
     * @param salt The salt to sprinkle
     * @return Hex string of password digest (or base64 encoded string if encodeHashAsBase64 is enabled.
     */
//    public String myEncodePassword(String rawPass, Object salt) {
//        String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
//
//        MessageDigest messageDigest = getMessageDigest();
//
//        byte[] digest;
//
//        try {
//            digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            throw new IllegalStateException("UTF-8 not supported!");
//        }
//
//        // "stretch" the encoded value if configured to do so
//        for (int i = 1; i < iterations; i++) {
//            digest = messageDigest.digest(digest);
//        }
//
//        if (false) {
//            return new String(Base64.encode(digest));
//        } else {
//        	return new String(Hex.encode(digest));
//        }
//       
//    }
    
    /**
     * Used by subclasses to generate a merged password and salt <code>String</code>.<P>The generated password
     * will be in the form of <code>password{salt}</code>.</p>
     *  <p>A <code>null</code> can be passed to either method, and will be handled correctly. If the
     * <code>salt</code> is <code>null</code> or empty, the resulting generated password will simply be the passed
     * <code>password</code>. The <code>toString</code> method of the <code>salt</code> will be used to represent the
     * salt.</p>
     *
     * @param password the password to be used (can be <code>null</code>)
     * @param salt the salt to be used (can be <code>null</code>)
     * @param strict ensures salt doesn't contain the delimiters
     *
     * @return a merged password and salt <code>String</code>
     *
     * @throws IllegalArgumentException if the salt contains '{' or '}' characters.
     */
    private String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }

        if (strict && (salt != null)) {
            if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1)) {
                throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
            }
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }
    
    /**
     * Get a MessageDigest instance for the given algorithm.
     * Throws an IllegalArgumentException if <i>algorithm</i> is unknown
     *
     * @return MessageDigest instance
     * @throws IllegalArgumentException if NoSuchAlgorithmException is thrown
     */
    protected final MessageDigest getMessageDigest() throws IllegalArgumentException {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }
    
    //MD5加密 
    public String md5ext(String plainText) {
    	plainText = (plainText==null ? ""  : plainText) ;
	        try {
	            MessageDigest md = MessageDigest.getInstance(algorithm);
	            md.update(plainText.getBytes());
	            byte b[] = md.digest();

	            int i;

	            StringBuffer buf = new StringBuffer("");
	            for (int offset = 0; offset < b.length; offset++) {
	                i = b[offset];
	                if (i < 0)
	                    i += 256;
	                if (i < 16)
	                    buf.append("0");
	                buf.append(Integer.toHexString(i));
	            }
	            //System.out.println("result: " + buf.toString());// 32位的加密
	            return buf.toString().toUpperCase();
	            // System.out.println("result: " + buf.toString().substring(8,
	            // 24));// 16位的加密
	        } catch (NoSuchAlgorithmException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return "error";
	        }
	    } 
    //MD5加密 
    public String md5(String plainText) {
    	plainText = (plainText==null ? ""  : plainText) ;
	        try {
	            MessageDigest md = MessageDigest.getInstance(algorithm);
	            md.update(plainText.getBytes());
	            byte b[] = md.digest();

	            int i;

	            StringBuffer buf = new StringBuffer("");
	            for (int offset = 0; offset < b.length; offset++) {
	                i = b[offset];
	                if (i < 0)
	                    i += 256;
	                if (i < 16)
	                    buf.append("0");
	                buf.append(Integer.toHexString(i));
	            }
	            //System.out.println("result: " + buf.toString());// 32位的加密
	            return buf.toString().toUpperCase();
	            // System.out.println("result: " + buf.toString().substring(8,
	            // 24));// 16位的加密
	        } catch (NoSuchAlgorithmException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return "error";
	        }
	    } 
    
    /**
	 * MD5加密（合并后的参数）
	 * @param
	 * @param
	 * @return
	 */
	public static String md5Encode(String content) {
		try {
			//String content = username + orderNo + targetInstitutionNo;
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] tempBytes = messageDigest.digest(content.getBytes("UTF-8"));
			StringBuffer stringBuffer = new StringBuffer();
			for (byte bytes : tempBytes) {
				String tempByte = Integer.toHexString(bytes & 0xFF);
				if (tempByte.length() == 1) {
					stringBuffer.append("0");
				}
				stringBuffer.append(tempByte);
			}
			return stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
}
