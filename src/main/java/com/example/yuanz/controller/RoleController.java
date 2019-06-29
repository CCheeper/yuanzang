package com.example.yuanz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.AdministratorEntity;
import com.example.yuanz.server.Administratorlpml;
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
public class RoleController {
    @Autowired
    Administratorlpml administratorlpml;


    @ResponseBody
    @RequestMapping("/role/list")
    public JSONObject articleList(@RequestParam("page") String page, @RequestParam("limit") String limit, @RequestParam("title") String titel) {

        int pagenum = Integer.valueOf(page);
        int limitnum = Integer.valueOf(limit);
        int total = administratorlpml.findCountByAdministrator();

        List<AdministratorEntity> list = new ArrayList<AdministratorEntity>();

        //如果搜索框不存在字段则获取全部数据，如果搜索框存在，则转到第一步，通过搜索框内容查询对应内容

        if( administratorlpml.findAdministratorEntityByUsername(titel)!=null){
            AdministratorEntity administratorEntity =  administratorlpml.findAdministratorEntityByUsername(titel);
            list.add(administratorEntity);
        }else {
            int startnum = (pagenum - 1) * limitnum;
            if (total > pagenum * limitnum) {
                list = administratorlpml.findByAdministratorRange(startnum, limitnum);
            } else {
                list = administratorlpml.findByAdministratorRange(startnum, pagenum * limitnum - total);
            }
        }
        //用于存储list
        JSONArray array = new JSONArray();



        for (int i = 0; i < list.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("createTime", list.get(i).getCreatetime());
            object.put("id", list.get(i).getId());
            object.put("qq", list.get(i).getQq());
            object.put("level", list.get(i).getLevel());
            object.put("email", list.get(i).getEmail());
            object.put("username", list.get(i).getUsername());
            object.put("telephone", list.get(i).getTelephone());
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
    @RequestMapping("/role/delete")
    public void isSuccess(@RequestBody JSONObject json) {
        String id = (String) json.get("id");
        administratorlpml.deleteById(id);
    }


    @ResponseBody
    @RequestMapping(value = {
            "/role/create",
            "/role/update"
    })

    public JSONObject create(@RequestBody JSONObject json, HttpServletRequest request) {
        //时间格式化
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);


        //将json中值取出并且储存到对象中
        AdministratorEntity administratorEntity = new AdministratorEntity();
        if(administratorlpml.findAdministratorEntityByUsername((String) json.get("username"))==null){
            administratorEntity.setId(String.valueOf(administratorlpml.findCountByAdministrator()));
        }else {
            administratorEntity.setId((String) json.get("id"));
        }

        administratorEntity.setCreatetime(date);
        administratorEntity.setEmail((String) json.get("email"));

        administratorEntity.setUsername((String) json.get("username"));
        administratorEntity.setLevel(5);
        administratorEntity.setPassword((String) json.get("password"));
        administratorEntity.setQq((String) json.get("qq"));
        administratorEntity.setTelephone((String) json.get("telephone"));


        administratorlpml.save(administratorEntity);

        //返回正确表示
        JSONObject object = new JSONObject();
        object.put("code", 20000);
        object.put("data", "success");
        //object.put("editor", json.get("editorId"));

        return object;
    }


    //获取id，编辑者，时间传输给前端，解决前端编辑，创建后不显示前面三者的问题
    @ResponseBody
    @RequestMapping("/role/getthings")
    public JSONObject getthings() {
        int total = administratorlpml.findCountByAdministrator() + 1;
        String id = String.valueOf(total);
        JSONObject object = new JSONObject();
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);
        object.put("id", id);
        object.put("date", date);
 //       object.put("editor", "admin");
        return object;
    }
}
