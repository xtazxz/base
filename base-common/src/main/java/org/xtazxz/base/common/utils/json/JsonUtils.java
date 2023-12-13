package org.xtazxz.base.common.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.xtazxz.base.common.ex.Assert;
import org.xtazxz.base.common.ex.ErrorCodeException;
import org.xtazxz.base.entity.ErrorCode;

public class JsonUtils {

  private JsonUtils() {
  }

  /**
   * 获取单个参数的值
   *
   * @param jsonStr json字符串
   * @param name    参数名字，例如: a.b.c
   */
  public static String readValue(String jsonStr, String name) {
    String[] nodes = name.split("\\.");
    try {
      JsonNode root = new ObjectMapper().readTree(jsonStr);
      for (String node : nodes) {
        root = root.get(node);
        Assert.notNull(root, ErrorCode.system("{}中没有节点{}", jsonStr, name));
      }
      return root.asText();
    } catch (JsonProcessingException e) {
      throw new ErrorCodeException(e);
    }
  }

  /**
   * 写入单个参数的值（没有则新增）
   *
   * @param jsonStr json字符串
   * @param name    参数名字，例如: a.b.c
   * @param value   参数值
   */
  public static String writeValue(String jsonStr, String name, String value) {
    String[] nodes = name.split("\\.");
    try {
      JsonNode root = new ObjectMapper().readTree(jsonStr);
      JsonNode currNode = root;
      for (int i = 0; i < nodes.length - 1; i++) {
        String propertyName = nodes[i];
        JsonNode subNode = currNode.get(propertyName);
        if (subNode == null) {
          currNode = ((ObjectNode) currNode).putObject(propertyName);
        } else {
          currNode = subNode;
        }
      }
      ((ObjectNode) currNode).put(nodes[nodes.length - 1], value);
      return root.toString();
    } catch (JsonProcessingException e) {
      throw new ErrorCodeException(e);
    }
  }

  /**
   * 对象转字符串
   *
   * @param obj 对象
   */
  public static String toString(Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new ErrorCodeException(e);
    }
  }

  /**
   * json字符串转对象
   *
   * @param jsonStr json字符串
   * @param cls     对象类型
   */
  public static <T> T toObject(String jsonStr, Class<T> cls) {
    try {
      return new ObjectMapper().readValue(jsonStr, cls);
    } catch (JsonProcessingException e) {
      throw new ErrorCodeException(e);
    }
  }

  /**
   * json字符串转list
   *
   * @param jsonStr json字符串
   * @param cls     对象类型
   */
  public static <T> List<T> toList(String jsonStr, Class<T> cls) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      CollectionType type = objectMapper.getTypeFactory().constructCollectionType(
          ArrayList.class, cls);
      return objectMapper.readValue(jsonStr, type);
    } catch (JsonProcessingException e) {
      throw new ErrorCodeException(e);
    }
  }

  /**
   * json字符串转Map
   *
   * @param jsonStr  json字符串
   * @param keyCls   KEY对象类型
   * @param valueCls VALUE对象类型
   */
  public static <K, V> Map<K, V> toList(String jsonStr, Class<K> keyCls, Class<V> valueCls) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      MapType type = objectMapper.getTypeFactory().constructMapType(
          LinkedHashMap.class, keyCls, valueCls);
      return objectMapper.readValue(jsonStr, type);
    } catch (JsonProcessingException e) {
      throw new ErrorCodeException(e);
    }
  }

}
