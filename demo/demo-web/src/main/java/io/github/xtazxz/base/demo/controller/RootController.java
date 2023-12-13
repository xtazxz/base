package io.github.xtazxz.base.demo.controller;

import io.github.xtazxz.base.entity.Response;
import io.github.xtazxz.base.web.component.BaseController;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController extends BaseController {

  @GetMapping("/")
  public String root() {
    return "index";
  }

  @GetMapping("/{page}.html")
  public String html(@PathVariable String page) {
    return page;
  }

  @ResponseBody
  @GetMapping("/health_check")
  public Response<Date> healthCheck() {
    return Response.ok(new Date());
  }

}
