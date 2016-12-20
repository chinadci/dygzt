/**
 * created by 2010-6-28
 */
package software.dygzt.service.share;

import java.security.SignatureException;

/**
 * 签名接口
 * @author zym
 *
 */
public interface Signature {
    /**
     * 外部接口参数签名。<p>
     *
     * 使用privateKey对原始数据进行签名
     *
     * @param content 原始数据
     * @param privateKey 私钥
     * @param charset 编码集
     * @return 签名数据
     * @return
     * @throws SignatureException
     */
    public String sign(String content, String privateKey, String charSet) throws SignatureException;

    /**
     * * 验证签名
     *
     * @param content 原始数据
     * @param signature 签名数据
     * @param publicKey 公钥
     * @param charset 编码集
     * @return True 签名验证通过 False 签名验证失败
     *
     */
    public boolean check(String content, String signature, String publicKey, String charset) throws SignatureException;
}
