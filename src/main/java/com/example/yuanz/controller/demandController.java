package com.example.yuanz.controller;

//援藏需求


import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.DemandmanagerEntity;
import com.example.yuanz.entity.HelpmanEntity;
import com.example.yuanz.server.Demandlmpl;
import com.example.yuanz.server.Schoolmpl;
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
import java.util.SortedMap;


//援藏需求

@Controller
public class demandController {

    @Autowired
    Demandlmpl demandlmpl;
    @Autowired
    Schoolmpl schoolmpl;


    @ResponseBody
    @RequestMapping("/demandmanager/list")
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
            object.put("school_name", list.get(i).getSchoolName());
            object.put("message", list.get(i).getMessage());
            object.put("create_time", list.get(i).getCreateTime());
            object.put("editor", list.get(i).getEditor());
                //将json放入array数组中
            array.add(object);

        }

        JSONObject data = new JSONObject();
        data.put("total", total);
        data.put("items", array);

        JSONObject Demandmanager = new JSONObject();
        Demandmanager.put("code", 20000);
        Demandmanager.put("data", data);

        //返回显示对象
        return Demandmanager;
    }
    //增加援藏人员
    @ResponseBody
    @RequestMapping(value = {"demandmanager/create", "demandmanager/update"})
    public JSONObject addHelp (@RequestBody JSONObject json, HttpServletRequest request){

        //时间格式化
        Date creatTime=new Date();
        SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date=ft.format(creatTime);

        DemandmanagerEntity demandmanagerEntity=new DemandmanagerEntity();


        if(json.get("id")!=null){
            String name = null;
            Cookie[] cookies = request.getCookies();
            if (null != cookies)
            {
                for (Cookie cookie : cookies)
                {
                    if (cookie.getName().equals("username"))
                     name = cookie.getValue();
                }
            }
            demandmanagerEntity.setId((String) json.get("id"));
        }else {
            demandmanagerEntity.setId(String.valueOf(demandlmpl.findCountByDemand()+1));

        }
        demandmanagerEntity.setCreateTime(date);
        demandmanagerEntity.setTitle((String) json.get("title"));
        demandmanagerEntity.setEditor((String) json.get("Editor"));
        demandmanagerEntity.setUgent((String) json.get("ugent"));
        demandmanagerEntity.setSchoolName((String) json.get("school_name"));
        demandmanagerEntity.setMessage((String) json.get("message"));

        demandlmpl.save(demandmanagerEntity);

        //返回正确的形式
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",20000);
        jsonObject.put("data","success");

        return jsonObject;

    }


    //删除援藏需求
    @ResponseBody
    @RequestMapping("/demandmanager/delete")
    public void Delete(@RequestBody JSONObject deletejson) {
        demandlmpl.deleteById((String) deletejson.get("id"));
    }

}
