package com.swaggertest.demo.utils;

import com.swaggertest.demo.system.consts.AlgorithmConst;
import com.swaggertest.demo.system.consts.SystemConst;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.apache.tomcat.util.codec.binary.Base64;

public class SymmetricEncryptUtil {

  //加密
  public static String encrypt(Integer randomSalt,String str) throws Exception {
    PBEKeySpec pbeKeySpec = new PBEKeySpec((SystemConst.PROJECT_NAME+ AlgorithmConst.ALGORITHM_PASSWORD).toCharArray());
    SecretKeyFactory factory = SecretKeyFactory.getInstance(AlgorithmConst.ALGORITHM_PBEWITHMD5ANDDES);
    Key key = factory.generateSecret(pbeKeySpec);
    PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(randomSalt.toString().getBytes(SystemConst.ALGORITHM_CODE_UTF8), AlgorithmConst.ITERATION_TIME);
    Cipher cipher = Cipher.getInstance(AlgorithmConst.ALGORITHM_PBEWITHMD5ANDDES);
    cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
    byte[] result = cipher.doFinal(str.getBytes());
    String sign = Base64.encodeBase64String(result);
    return sign;
  }

  public static String decrypt(Integer randomSalt,String encryptStr) throws Exception{
    PBEKeySpec pbeKeySpec = new PBEKeySpec((SystemConst.PROJECT_NAME+AlgorithmConst.ALGORITHM_PASSWORD).toCharArray());
    SecretKeyFactory factory = SecretKeyFactory.getInstance(AlgorithmConst.ALGORITHM_PBEWITHMD5ANDDES);
    Key key = factory.generateSecret(pbeKeySpec);
    PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(randomSalt.toString().getBytes(SystemConst.ALGORITHM_CODE_UTF8), AlgorithmConst.ITERATION_TIME);
    Cipher cipher = Cipher.getInstance(AlgorithmConst.ALGORITHM_PBEWITHMD5ANDDES);
    cipher.init(Cipher.DECRYPT_MODE,key,pbeParameterSpec);
    byte[] result = cipher.doFinal(Base64.decodeBase64(encryptStr));
    String strTarget = new String(result,SystemConst.ALGORITHM_CODE_UTF8);
    return strTarget;
  }

}
