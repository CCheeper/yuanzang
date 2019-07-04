package com.example.yuanz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.AdministratorEntity;
import com.example.yuanz.entity.RoadEntity;
import com.example.yuanz.server.Administratorlpml;
import com.example.yuanz.server.Roadlpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RoadController {


    @Autowired
    Roadlpml roadlpml;


    @ResponseBody
    @RequestMapping("/road/list")
    public JSONObject roadList(@RequestParam("page") String page, @RequestParam("limit") String limit, @RequestParam("title") String titel) {

        int pagenum = Integer.valueOf(page);
        int limitnum = Integer.valueOf(limit);
        int total = roadlpml.findCountByRoad();

        List<RoadEntity> list = new ArrayList<RoadEntity>();

        //如果搜索框不存在字段则获取全部数据，如果搜索框存在，则转到第一步，通过搜索框内容查询对应内容

        //  if (String.valueOf(roadlpml.findRoadEntitiesBySchoolname(titel)).isEmpty())
        if (String.valueOf(titel).isEmpty()) {
            int startnum = (pagenum - 1) * limitnum;
            if (total > pagenum * limitnum) {
                list = roadlpml.findByRoadRange(startnum, limitnum);
            } else {
                list = roadlpml.findByRoadRange(startnum, limitnum);
            }


        } else {
            list = roadlpml.findRoadEntitiesBySchoolname(titel);
        }

        //用于存储list
        JSONArray array = new JSONArray();


        for (int i = 0; i < list.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("createTime", list.get(i).getTime());
            object.put("id", list.get(i).getId());
            object.put("schoolname", list.get(i).getSchoolname());
            object.put("Editor", list.get(i).getEditor());
            object.put("route", list.get(i).getRoute());
            object.put("comment_disabled", true);//
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

    //删除
    @ResponseBody
    @RequestMapping("/road/delect")
    public void isSuccess(@RequestBody JSONObject json) {
        String id = (String) json.get("id");
        roadlpml.deleteById(id);
    }


    @ResponseBody
    @RequestMapping(value = {
            "/road/create",
            "/road/update"
    })

    public JSONObject create(@RequestBody JSONObject json) {
        //时间格式化
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);

        //将json中值取出并且储存到对象中
        RoadEntity roadEntity = new RoadEntity();
        if (!roadlpml.findById((String) json.get("id")).isPresent() ) {
            roadEntity.setId(String.valueOf(roadlpml.findCountByRoad()+1));
        } else {
            roadEntity.setId((String) json.get("id"));
        }
        roadEntity.setEditor((String) json.get("Editor"));
        roadEntity.setRoute((String) json.get("route"));
        roadEntity.setSchoolname((String) json.get("schoolname"));
        roadEntity.setTime(date);

        roadlpml.save(roadEntity);

        //返回正确表示
        JSONObject object = new JSONObject();
        object.put("code", 20000);
        object.put("data", "success");
        //object.put("editor", json.get("editorId"));

        return object;
    }
    @ResponseBody
    @RequestMapping("/road/getthings")
    public JSONObject getthings(HttpServletRequest request) {

        JSONObject object = new JSONObject();
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);

        int idcount = roadlpml.findCountByRoad() +1;
        while (roadlpml.findById(String.valueOf(idcount)).isPresent()){
            idcount = idcount+1;
        }

        object.put("id",String.valueOf(idcount) );
        object.put("date", date);


        return object;
    }
}


