package com.example.yuanz.controller;

//援藏需求


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class demandController {


    @ResponseBody
    @RequestMapping("/demand/list")
    public JSONObject demand(@RequestParam ("page") String page,@RequestParam("limit") String limit){





        return null;
    }


}
