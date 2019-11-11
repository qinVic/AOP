package com.vic.aop.controller;

import com.vic.aop.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinquan
 * @date 2019/9/10
 */
@RestController
@RequestMapping("/test")
public class JwtController {

    @Resource
    private JwtUtils jwtUtils;

    @GetMapping("")
    public String test() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("orgName", "deepexi");
        map.put("orgId", "009");
        map.put("code", "002");
        return jwtUtils.createToken("123", "vic", map);
    }

    @PostMapping("/test2")
    public void test2(@RequestParam("token") String token) {
        Claims claims = jwtUtils.parseToken(token);
        String code = claims.get("code", String.class);
        System.out.println(code);
    }

}
