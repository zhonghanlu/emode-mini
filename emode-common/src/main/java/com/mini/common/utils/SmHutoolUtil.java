package com.mini.common.utils;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.mini.common.constant.ErrorCodeConstant;
import com.mini.common.exception.service.EModeServiceException;

/**
 * @author zhl
 * @Date 2024年10月14日15:14:21
 * <p>
 * 采用hutool包下的sm加密
 */
public class SmHutoolUtil {

    private static final String PUBLIC_KEY = "04298364ec840088475eae92a591e01284d1abefcda348b47eb324bb521bb03b0b2a5bc393f6b71dabb8f15c99a0050818b56b23f31743b93df9cf8948f15ddb54";

    private static final String PRIVATE_KEY = "3037723d47292171677ec8bd7dc9af696c7472bc5f251b2cec07e65fdef22e25";

    private static final SM2 sm2 = SmUtil.sm2(PRIVATE_KEY, PUBLIC_KEY);

    private static final String EXTRA_STR = "04";

    /**
     * sm2 加密
     *
     * @param cipherText 待加密文本
     * @return 加密之后密文 需要去掉头04
     */
    public static String sm2EncryptBase64(String cipherText) {
        String encryptedHex = sm2.encryptHex(cipherText, KeyType.PublicKey);
        encryptedHex = encryptedHex.replaceFirst(EXTRA_STR, "");
        return encryptedHex;
    }

    /**
     * sm2 解密
     * 解析前端sm-crypto 这个库的sm2密文需要加上04
     *
     * @param cipherText 加密文本
     * @return 明文
     */
    public static String sm2DecryptStr(String cipherText) {
        cipherText = EXTRA_STR + cipherText;
        String decryptStr = "";
        try {
            decryptStr = sm2.decryptStr(cipherText, KeyType.PrivateKey);
        } catch (Exception e) {
            throw new EModeServiceException(ErrorCodeConstant.PARAM_ERROR, "解密异常，请传入正确密文");
        }
        return decryptStr;
    }


    public static void main(String[] args) {
//        KeyPair pair = SecureUtil.generateKeyPair("SM2");
//        //私钥
//        String privateKeyStr = Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());
//        //公钥
//        String publicKeyStr = Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());
//        System.out.println("私钥 " + privateKeyStr);
//        System.out.println("公钥 " + publicKeyStr);

        // 加密
//        SM2 sm2 = SmUtil.sm2(PRIVATE_KEY, PUBLIC_KEY);
//        String encryptedHex = sm2.encryptHex("123456", KeyType.PublicKey);
//        System.out.println(encryptedHex);
//
//        String decryptStr = sm2.decryptStr("04c3e5df33440666c166f316f1572cf485b790072a0d41aede8b1ac8436bc46105e531ea491a97232432950dcb03980d4f5aade6582dfe9a3d14b95c35a6a82ee0ae39b3046c13871b4e572b7dc94b132635b91700da14b0c33af2d092c770407b8b2a0ab0f577", KeyType.PrivateKey);
//        System.out.println(decryptStr);
        System.out.println(sm2EncryptBase64("1234567"));

        // 解密
//        SM2 sm2pr = SmUtil.sm2(privateKeyStr, null);
//        String decryptStr = sm2pr.decryptStr(encryptBase64, KeyType.PrivateKey);
//        System.out.println(decryptStr);

//        System.out.println(sm2DecryptStr("e9dd505f20ec72269386a9150f53b8fff1c6075d6bc23c3bf51313a20767fdbcabb5ed22420db2e48f3a7ac10da9392a4984daf7422fdb35e090672ec3e0f3814b5a291a04190bd3fe3f49089507e4da50bedf77a35d4c044ba32c3a97f7332fd6f7b1f4aa47"));

    }

}
