import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
# java RSA 加密解密

```
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Base64Utils;

/**
 * 签名法工具类
 */

public class SignUtil {
	
    public static RSAPublicKey getPublicKey(String modulus, String publicExponent)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		BigInteger mod = new BigInteger(modulus);
		BigInteger exp = new BigInteger(publicExponent);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(mod, exp);
		return (RSAPublicKey) keyFactory.generatePublic(keySpec);

	}
    
    public static RSAPrivateKey getPrivateKey(String modulus, String privateExponent)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		BigInteger mod = new BigInteger(modulus);
		BigInteger exp = new BigInteger(privateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(mod, exp);
		return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

	}

    public static byte[] encrypt(String modulus, String publicExponent, byte[] plainTextData) {  
    	Cipher cipher = null;
    	RSAPublicKey publicKey;
    	try {  
			publicKey = getPublicKey(modulus, publicExponent);
    		// 使用默认RSA  
    		cipher = Cipher.getInstance("RSA");  
    		cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
    		byte[] output = cipher.doFinal(plainTextData);  
    		return Base64.encodeBase64(output);  
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	}
    	return null;
    }  
    
	public static byte[] decrypt(String modulus, String privateExponent, byte[] srcBytes) {
		// Cipher负责完成加密或解密工作，基于RSA
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA");
			// 根据公钥，对Cipher对象进行初始化
			RSAPrivateKey privateKey = getPrivateKey(modulus, privateExponent);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] resultBytes = cipher.doFinal(Base64.decodeBase64(srcBytes));
			return resultBytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
```