package io.github.xtazxz.base.common.utils.digest;

import cn.hutool.core.util.HexUtil;
import io.github.xtazxz.base.common.ex.ErrorCodeException;
import java.security.MessageDigest;

public class DigestUtils {

  private DigestUtils() {
  }

  /**
   * 使用SHA-256算法生成摘要.
   *
   * @param input 输入字符串
   */
  public static String encodeSha256(String input) {
    return encode(input, "SHA-256");
  }

  /**
   * 生成数据摘要.
   *
   * @param input     输入字符串
   * @param algorithm 算法
   */
  public static String encode(String input, String algorithm) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
      messageDigest.update(input.getBytes());
      return HexUtil.encodeHexStr(messageDigest.digest());
    } catch (Exception e) {
      throw new ErrorCodeException(e);
    }
  }

}
