/**
 * created by 2010-6-28
 */
package software.dygzt.service.share;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import software.dygzt.util.Base64Util;
import software.dygzt.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;


/**
 * MD5签名工具的实现
 *
 * @author zym
 */
public class MD5Signature implements Signature {

    Log logger = LogFactory.getLog(MD5Signature.class);

    /*
     * (non-Javadoc)
     *
     * @see nju.edu.software.cooperate.sign.Signature#sign(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public String sign(String content, String privateKey, String charset)
            throws SignatureException {
        if (StringUtil.isBlank(privateKey)) {
            throw new SignatureException("privateKey is null!");
        }
        String tosign;
        if (content == null)
            tosign = privateKey;
        else
            tosign = content + privateKey;
        try {
            String sign = DigestUtils.md5Hex(tosign.getBytes(charset));

            logger.info("MD5签名[content = " + tosign + "; charset = " + charset
                    + "]结果：" + sign);

            return sign;
        } catch (UnsupportedEncodingException e) {
            throw new SignatureException("Unsupported Encoding" + e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see nju.edu.software.cooperate.sign.Signature#check(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean check(String content, String signature, String publicKey,
                         String charset) throws SignatureException {
        /*
		 * if (StringUtil.isBlank(content) || StringUtil.isBlank(publicKey) ||
		 * StringUtil.isBlank(signature)) {
		 *
		 * throw new
		 * SignatureException("content or publicKey or signature is null!"); }
		 */

        String tosign = content + publicKey;

        try {
            String mySign = DigestUtils.md5Hex(tosign.getBytes(charset));

            boolean verify = false;
            if (mySign.equals(signature)) {
                verify = true;
            }

            logger.info("MD5 SIGNATURE: [content = " + content + " ; charset = "
                    + charset + " ; signature = " + signature + "]"
                    + (verify ? " CONFIRM SUCCESS" : " FAIL! "));

            return verify;
        } catch (UnsupportedEncodingException e) {
            throw new SignatureException("Unsupported Encoding", e);
        }
    }

    @SuppressWarnings("static-access")
    public static void main(String args[]) throws SignatureException,
            UnsupportedEncodingException {
        String parm = "(1989)中经上字第00222号&jylb=正卷;副卷";
//		for(int i=1;i<100;i++){
//			parm=parm+""+i+",";
//		}
//		parm=parm.substring(0,parm.length()-1);
        System.out.println(parm);
        Base64 base = new Base64();
        String base64 = new String(base.encodeBase64(parm.getBytes("UTF-8")));
        base64 = Base64Util.getBASE64String(parm.getBytes("UTF-8"));
        System.out.println(base64);
//		base64="KDE5ODkp5Lit57uP5LiK5a2X56ysMDAyMjLlj7cmanlsYj3mraPljbc6MSwyLDMsNCw1LDYsNyw4LDk=";
//		String base64=Base64Util.getBASE64(parm.getBytes("UTF-8"));
//		System.out.println(base64);
        String key = "211bfa7efbcbe28431ceb328969cb15e";
        MD5Signature s = new MD5Signature();
        String content = s.sign(base64, key,
                "utf-8");
        System.out.println(content);
        System.out.println(content.toUpperCase());
//		String test = "YWg9KDIwMDMp5rSl6auY5rCR5LiA57uI5a2X56ysMDAwM+WPtyZqeWxiPeato+WNtw==";
//		String content = s.sign(test, "L1Sz2EaK8ArtEDoOevKUGh4i86H5bTfX",
//				"utf-8");
//		test = "KDIwMTMp5Lit57uP57uI5a2X56ysMDAxNjHlj7cmanlsYj3mraPljbc=211bfa7efbcbe28431ceb328969cb15e";
//		 System.out.println(DigestUtils.md5Hex(test.getBytes("UTF-8")));
//		System.out.println(content);
    }

}
