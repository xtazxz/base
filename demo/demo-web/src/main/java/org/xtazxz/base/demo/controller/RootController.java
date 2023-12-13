package org.xtazxz.base.demo.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xtazxz.base.entity.Response;
import org.xtazxz.base.web.component.BaseController;

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
