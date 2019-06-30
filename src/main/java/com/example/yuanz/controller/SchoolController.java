package com.example.yuanz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.SchoolEntity;
import com.example.yuanz.server.Administratorlpml;
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





//

//搜索功能 强化模糊查询 注解Rest问题
@Controller
public class SchoolController {
    @Autowired
    Schoolmpl schoolmpl;
    @Autowired
    Administratorlpml administratorlpml;


    @ResponseBody
    @RequestMapping("/article/list")
    public JSONObject articleList(@RequestParam("page") String page, @RequestParam("limit") String limit,@RequestParam("title") String titel) {

        int pagenum = Integer.valueOf(page);
        int limitnum = Integer.valueOf(limit);
        int total = schoolmpl.findCountBySchool();

        List<SchoolEntity> list = new ArrayList<SchoolEntity>();

        //如果搜索框不存在字段则获取全部数据，如果搜索框存在，则转到第一步，通过搜索框内容查询对应内容
        if( schoolmpl.findSchoolEntityBySchoolName(titel)!=null){
            SchoolEntity schoolEntity =  schoolmpl.findSchoolEntityBySchoolName(titel);
            list.add(schoolEntity);
        }else {
            int startnum = (pagenum - 1) * limitnum;
            if (total > pagenum * limitnum) {
                list = schoolmpl.findBySchoolRange(startnum, limitnum);
            } else {
                list = schoolmpl.findBySchoolRange(startnum, pagenum * limitnum - total);
            }
        }
        //用于存储list
        JSONArray array = new JSONArray();



        for (int i = 0; i < list.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("createTime", list.get(i).getCreateTime());
            object.put("id", list.get(i).getId());
            object.put("isgo", list.get(i).getIsGo());
            object.put("isneed", list.get(i).getIsNeed());
            object.put("name", list.get(i).getSchoolName());
            object.put("jianjie", list.get(i).getInfo());
            object.put("history", list.get(i).getHistory());
            object.put("Editor", list.get(i).getEditor());
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
    @RequestMapping("/message/isSuccess")
    public void isSuccess(@RequestBody JSONObject json) {
        String id = (String) json.get("id");
        schoolmpl.deleteById(id);
    }


    //添加
    @ResponseBody
    @RequestMapping(value = {"/article/create", "/article/update"})

    public JSONObject create(@RequestBody JSONObject json, HttpServletRequest request) {
        //时间格式化
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);

        //将json中值取出并且储存到对象中
        SchoolEntity schoolEntity = new SchoolEntity();

        schoolEntity.setCity("city");
        schoolEntity.setCreateTime(date);
        schoolEntity.setHistory((String) json.get("history"));
        schoolEntity.setId((String) json.get("id"));
        schoolEntity.setInfo((String) json.get("jianjie"));
        schoolEntity.setIsNeed((String) json.get("isneed"));
        schoolEntity.setIsGo((String) json.get("isgo"));
        schoolEntity.setSchoolName((String) json.get("name"));
        schoolEntity.setEditor((String) json.get("Editor"));

        //获取cookie
        String name = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    name = cookie.getValue();
                }
            }
        }

        //获取管理员的名字
        schoolEntity.setEditorId(administratorlpml.findAdministratorEntityByUsername(name).getUsername());
        schoolmpl.save(schoolEntity);

        //返回正确表示
        JSONObject object = new JSONObject();
        object.put("code", 20000);
        object.put("data", "success");

        //object.put("editor", json.get("editorId"));

        return object;
    }


    //获取id，编辑者，时间传输给前端，解决前端编辑，创建后不显示前面三者的问题
    @ResponseBody
    @RequestMapping("/message/getthings")
    public JSONObject getthings() {
        int total = schoolmpl.findCountBySchool() + 1;
        String id = String.valueOf(total);
        JSONObject object = new JSONObject();
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);
        object.put("id", id);
        object.put("date", date);
        object.put("editor", "admin");
        return object;
    }
}
