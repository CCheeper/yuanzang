package com.example.yuanz.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.server.Administratorlpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

@Controller
@RequestMapping("/")
public class UserLoginController {
    @Autowired
    Administratorlpml administratorlpml;


    @ResponseBody
    @RequestMapping("/user/login")
    public JSONObject login(@RequestBody JSONObject json, HttpServletResponse response, HttpServletRequest request) {
        String password = (String) json.get("password");
        String username = (String) json.get("username");

        if (password.equals(administratorlpml.findAdministratorEntityByUsername(username).getPassword())) {
            JSONObject object = new JSONObject();
            object.put("code", 20000);
            JSONObject data = new JSONObject();
            data.put("token", UUID.randomUUID().toString());
            object.put("data", data);            //添加cookie

            return object;
        } else {
            return null;
        }

    }

    @ResponseBody
    @RequestMapping("/user/info")
    public JSONObject info(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        String name = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    name = cookie.getValue();
                }
            }
        }
        object.put("code", 20000);
        JSONObject data = new JSONObject();
        data.put("roles", "admin");
        data.put("introduction", "I am a super administrator");
        data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("name", name);
     //   System.out.println(name);
        object.put("data", data);


        return object;
    }

    @ResponseBody
    @RequestMapping("/user/logout")
    public JSONObject logout(HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        object.put("code", 20000);

        object.put("data", "success");


        return object;
    }


}
