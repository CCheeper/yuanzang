package com.example.yuanz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.PolicyEntity;
import com.example.yuanz.server.Administratorlpml;
import com.example.yuanz.server.Policylpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PolicyController {
    @Autowired
    Policylpml policylpml;
    @Autowired
    Administratorlpml administratorlpml;


    @ResponseBody
    @RequestMapping("/policy/delect")
    public void delect(@RequestBody JSONObject json) {
       // String id = (String) json.get("id");
        policylpml.deleteById((String) json.get("id"));
    }


    @ResponseBody
        @RequestMapping("/policy/list")
        public JSONObject postlist(@RequestParam("page") String page, @RequestParam("limit") String limit,@RequestParam("title") String titel) {
            int pagenum = Integer.valueOf(page);
            int limitnum = Integer.valueOf(limit);
            int total = policylpml.findCountByPolicy();
            List<PolicyEntity> list= new ArrayList<PolicyEntity>();;
            JSONArray array = new JSONArray();

            int startnum = (pagenum - 1) * limitnum;


        if( policylpml.findPolicyEntityByTitle(titel)!=null){
            PolicyEntity policyEntity =  policylpml.findPolicyEntityByTitle(titel);
            list.add(policyEntity);
        }else {
            if (total > pagenum * limitnum) {
                list = policylpml.findByPolicyRange(startnum, limitnum);
            } else {
                list = policylpml.findByPolicyRange(startnum, pagenum * limitnum - total);
            }
        }
            for (int i = 0; i < list.size(); i++) {
                JSONObject object = new JSONObject();
                object.put("createTime", list.get(i).getTime());
                object.put("id", list.get(i).getId());
                object.put("title", list.get(i).getTitle());
                object.put("fileurl", list.get(i).getFileurl());
                object.put("editorid", list.get(i).getEditorid());
                object.put("comment_disabled", true);
                array.add(object);
            }

        JSONObject data = new JSONObject();
        data.put("total", total);
        data.put("items", array);
        JSONObject lll = new JSONObject();
        lll.put("code", 20000);
        lll.put("data", data);
        return lll;
    }

    @ResponseBody
    @RequestMapping(value = {
            "/policy/create",
            "/policy/update"
    })

    public JSONObject create(@RequestBody JSONObject json, HttpServletRequest request) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);

        int total = policylpml.findCountByPolicy();


        PolicyEntity policyEntity = new PolicyEntity();
        policyEntity.setFileurl("模块未完成，等待上传模块完成传回url");
        policyEntity.setId(String.valueOf(total+1));  //总行数加1
        policyEntity.setTime(date);
        policyEntity.setTitle((String) json.get("title"));


        String name = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    name = cookie.getValue();
                }
            }
        }


        policyEntity.setEditorid(administratorlpml.findAdministratorEntityByUsername(name).getUsername());


        policylpml.save(policyEntity);


        JSONObject object = new JSONObject();
        object.put("code", 20000);
        object.put("data", "success");
        //object.put("editor", json.get("editorId"));

        return object;
    }

    @ResponseBody
    @RequestMapping("/policy/getthings")
    public JSONObject getthings(HttpServletRequest request) {
        int total = policylpml.findCountByPolicy() + 1;
        String id = String.valueOf(total);
        JSONObject object = new JSONObject();
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);
        object.put("id", id);
        object.put("date", date);


        return object;
    }
}