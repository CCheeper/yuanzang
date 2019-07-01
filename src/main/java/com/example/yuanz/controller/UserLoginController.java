package com.example.yuanz.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.PowerEditEntity;
import com.example.yuanz.server.Administratorlpml;
import com.example.yuanz.server.Powerlmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class UserLoginController {
    @Autowired
    Administratorlpml administratorlpml;
    @Autowired
    Powerlmpl powerlmpl;

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
        ArrayList<String > arrayList =new ArrayList<>();

        if (powerlmpl.findById(name).isPresent()) {


            PowerEditEntity powerEditEntity = powerlmpl.findById(name).get();
            if (powerEditEntity.getPowerEdit().equals(1)) {
                arrayList.add("power_edit");
            }
            if (powerEditEntity.getRoleEdit().equals(1)) {
                arrayList.add("role_edit");
            }
            if (powerEditEntity.getPersonalEdit().equals(1)) {
                arrayList.add("personal_edit");
            }
            if (powerEditEntity.getNeedEdit().equals(1)) {
                arrayList.add("need_edit");
            }
            if (powerEditEntity.getHelppeopleEdit().equals(1)) {
                arrayList.add("helppeople_edit");
            }
            if (powerEditEntity.getHelpZEdit().equals(1)) {
                arrayList.add("helpZ_edit");
            }
            if (powerEditEntity.getWorkdataEdit().equals(1)) {
                arrayList.add("workdata_edit");
            }
            if (powerEditEntity.getRoadEdit().equals(1)) {
                arrayList.add("road_edit");
            }
            if (powerEditEntity.getSchoolEdit().equals(1)) {
                arrayList.add("school_edit");
            }
        }
        arrayList.add("editor");



        data.put("roles", arrayList);
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


    @ResponseBody
    @RequestMapping("/userRouter")
    public ArrayList<String> router(HttpServletRequest request) {
        String name = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    name = cookie.getValue();
                }
            }
        }
        ArrayList<String> arrayList = new ArrayList<>();
        if (powerlmpl.findById(name).isPresent()) {
            PowerEditEntity powerEditEntity = new PowerEditEntity();
            powerEditEntity = powerlmpl.findById(name).get();

            if (powerEditEntity.getRoadEdit().equals(1)) {
                arrayList.add("road_edit");
            }
            if (powerEditEntity.getHelppeopleEdit().equals(1)) {
                arrayList.add("helppeople_edit");
            }
            if (powerEditEntity.getHelpZEdit().equals(1)) {
                arrayList.add("helpZ_edit");
            }
            if (powerEditEntity.getNeedEdit().equals(1)) {
                arrayList.add("need_edit");
            }
            if (powerEditEntity.getPersonalEdit().equals(1)) {
                arrayList.add("personal_edit");
            }
            if (powerEditEntity.getRoleEdit().equals(1)) {
                arrayList.add("role_edit");
            }
            if (powerEditEntity.getSchoolEdit().equals(1)) {
                arrayList.add("school_edit");
            }
            if (powerEditEntity.getWorkdataEdit().equals(1)) {
                arrayList.add("workdata_edit");
            }
            if (powerEditEntity.getPowerEdit().equals(1)) {
                arrayList.add("power_edit");
            }
        }


        return arrayList;
    }

}
