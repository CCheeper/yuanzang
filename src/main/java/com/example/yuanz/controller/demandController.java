package com.example.yuanz.controller;

//援藏需求


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.DemandmanagerEntity;
import com.example.yuanz.server.Demandlmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


//援藏需求

@Controller
public class demandController {

    @Autowired
    Demandlmpl demandlmpl;


    @ResponseBody
    @RequestMapping("/demand/list")
    public JSONObject demand(@RequestParam("page") String page, @RequestParam("limit") String limit) {
        int pagenum = Integer.valueOf(page);//页数
        int limitnum = Integer.valueOf(limit);//每页的数据属
        int total = demandlmpl.findCountByDemand();//所有的数据

        List<DemandmanagerEntity> list = new ArrayList<DemandmanagerEntity>();
        JSONArray array = new JSONArray();

        //每页的起始数据
        int startnum = (pagenum - 1) * limitnum;

        //输出每页的数据
        if (total > pagenum * limitnum) {
            list = demandlmpl.findByDemandRang(startnum, limitnum);
        } else {
            list = demandlmpl.findByDemandRang(startnum, limitnum - total);
        }

        //打包成json对象
        for (int i = 0; i < list.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("id", list.get(i).getId());
            object.put("title", list.get(i).getTitle());
            object.put("ugent", list.get(i).getUgent());
            object.put("schoolname", list.get(i).getSchoolName());
            object.put("message", list.get(i).getMessage());
            object.put("createTime", list.get(i).getCreateTime());
            object.put("editor", list.get(i).getEditor());
            array.add(object);      //将json放入array数组中
        }

        JSONObject data = new JSONObject();
        data.put("total", total);
        data.put("items", array);

        JSONObject Demandmanager = new JSONObject();
        Demandmanager.put("code", 20000);
        Demandmanager.put("data", data);

        return Demandmanager;//返回显示对象
    }


    //删除援藏需求
    @ResponseBody
    @RequestMapping("/demand/delete")
    public void Delete(@RequestBody JSONObject deletejson) {
        demandlmpl.deleteById((String) deletejson.get("id"));
    }

}
