package org.xtazxz.base.web.component;

import cn.hutool.core.io.IoUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.util.AntPathMatcher;
import org.xtazxz.base.common.component.BaseComponent;
import org.xtazxz.base.common.utils.json.JsonUtils;
import org.xtazxz.base.entity.ErrorCode;
import org.xtazxz.base.entity.Response;

public abstract class BaseFilter extends BaseComponent implements Filter {

  private static final AntPathMatcher matcher = new AntPathMatcher();

  private String[] antPatterns;

  private String[] excludeAntPatterns;

  /**
   * 设置需要处理的路径模板，不设置，则全部路径都需要处理，优先级低于ignoringAntMatchers()中的配置.
   *
   * @param antPatterns 路径模板
   */
  public void antMatchers(String... antPatterns) {
    this.antPatterns = antPatterns;
  }

  /**
   * 忽略不需要处理的路径模板，不设置，则不忽略任何路径，优先级高于antMatchers()中的配置.
   *
   * @param excludeAntPatterns 路径模板
   */
  public void ignoringAntMatchers(String... excludeAntPatterns) {
    this.excludeAntPatterns = excludeAntPatterns;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    String baseUrl = httpRequest.getRequestURL().substring(0,
        httpRequest.getRequestURL().indexOf(httpRequest.getContextPath())
            + httpRequest.getContextPath().length());
    String method = httpRequest.getMethod();
    String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
    if (match(method, path)) {
      doFilter(httpRequest, httpResponse, chain, baseUrl, path);
    } else {
      chain.doFilter(request, response);
    }
  }

  public abstract void doFilter(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, String baseUrl, String path) throws IOException, ServletException;

  public void responseString(HttpServletResponse response, String responseString)
      throws IOException {
    response.setHeader("Content-Type", "plain/text;charset=utf-8");
    IoUtil.write(response.getOutputStream(), false,
        responseString.getBytes(StandardCharsets.UTF_8));
  }

  public void responseObject(HttpServletResponse response, Object responseObject)
      throws IOException {
    response.setHeader("Content-Type", "application/json;charset=utf-8");
    String responseString = JsonUtils.toString(responseObject);
    IoUtil.write(response.getOutputStream(), false,
        responseString.getBytes(StandardCharsets.UTF_8));
  }

  public void responseOk(HttpServletResponse response)
      throws IOException {
    responseOk(response, null);
  }

  public void responseOk(HttpServletResponse response, Object data)
      throws IOException {
    Response<Object> responseObject = Response.ok(data);
    responseObject(response, responseObject);
  }

  public void responseError(HttpServletResponse response, ErrorCode errorCode)
      throws IOException {
    responseError(response, errorCode, null);
  }

  public void responseError(HttpServletResponse response, ErrorCode errorCode, Object data)
      throws IOException {
    Response<Object> responseObject = Response.error(errorCode, data);
    responseObject(response, responseObject);
  }

  /**
   * 检查请求路径是否需要处理.
   *
   * @param method 请求方法
   * @param path   请求路径
   */
  private boolean match(String method, String path) {
    if (matchExcludeAntPatterns(method, path)) {
      // 如果需要排除，则返回false
      return false;
    } else {
      // 不需要排除时，在检查是否需要处理.
      return matchAntPatterns(method, path);
    }
  }

  /**
   * 匹配路径，是否需要处理.
   *
   * @param method 请求方法
   * @param path   请求路径
   */
  private boolean matchAntPatterns(String method, String path) {
    // 没有设置antPatterns时，全部URL都需要处理.
    if (antPatterns == null || antPatterns.length == 0) {
      return true;
    }
    for (String antPattern : antPatterns) {
      String matchMethod;
      String matchPath = antPattern;
      // 如果antPattern中包含空格，则表示，需要限制请求方法，如果请求方法不一样，则跳过处理.
      if (antPattern.contains(" ")) {
        String[] antPatternSplits = antPattern.split(" ");
        matchMethod = antPatternSplits[0];
        matchPath = antPatternSplits[1];
        if (!matchMethod.equalsIgnoreCase(method)) {
          continue;
        }
      }
      if (matcher.match(matchPath, path)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 匹配路径，是否需要排除.
   *
   * @param method 请求方法
   * @param path   请求路径
   */
  private boolean matchExcludeAntPatterns(String method, String path) {
    // 没有设置excludeAntPatterns时，全部URL都不需要排除.
    if (excludeAntPatterns == null) {
      return false;
    }
    for (String excludeAntPattern : excludeAntPatterns) {
      // 如果excludeAntPattern中包含空格，则表示，需要限制请求方法，如果请求方法不一样，则跳过处理.
      if (excludeAntPattern.contains(" ") && !excludeAntPattern.startsWith(method)) {
        continue;
      }
      if (matcher.match(excludeAntPattern, path)) {
        return true;
      }
    }
    return false;
  }

}
