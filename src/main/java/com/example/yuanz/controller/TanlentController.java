package com.example.yuanz.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.TanlentintrductionEntity;
import com.example.yuanz.entity.WorkdynamicEntity;
import com.example.yuanz.server.Administratorlpml;
import com.example.yuanz.server.Talentlmpl;
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
public class TanlentController {

    @Autowired
    Talentlmpl talentlmpl;
    @Autowired
    Administratorlpml administratorlpml;



    //显示数据
    @ResponseBody
    @RequestMapping("/talentintrduction/list")
    public JSONObject worklist(@RequestParam("page") String page, @RequestParam("limit")String limit, @RequestParam("title")String title){
        int pagenum=Integer.valueOf(page);
        int limitnum=Integer.valueOf(limit);
        int total=talentlmpl.findCountByTanlent();

        List<TanlentintrductionEntity> list=new ArrayList<TanlentintrductionEntity>();
        JSONArray jsonArray=new JSONArray();

        int startnum=(pagenum-1) * limitnum;

        if (!String.valueOf(title).isEmpty())
        {
            //判断成功则输出搜索框中找到的内容
            TanlentintrductionEntity tanlentEntityByTitle=talentlmpl.findTanlentEntityByTitle(title);
            list.add(tanlentEntityByTitle);
        }else
        {
            //判断失败则直接输出整个列表
            if (total>pagenum*limitnum){
                list=talentlmpl.findByTalenRange(startnum,limitnum);
            }else
            {
                list=talentlmpl.findByTalenRange(startnum,total-limitnum);
            }
        }

        //将数据库中数据打包成json对象
        for (int i=0;i<list.size();i++){
            JSONObject jsonObject=new JSONObject();

            jsonObject.put("id",list.get(i).getId());
            jsonObject.put("title",list.get(i).getTitle());
            jsonObject.put("create_time",list.get(i).getCreateTime());
            jsonObject.put("school_name",list.get(i).getSchoolName());
            jsonObject.put("editor",list.get(i).getEditor());
            jsonArray.add(jsonObject);
        }

        JSONObject data=new JSONObject();
        data.put("total",total);
        data.put("items",jsonArray);

        JSONObject talentIntrduction = new JSONObject();
        talentIntrduction.put("code",20000);
        talentIntrduction.put("data",data);

        return talentIntrduction;
    }



    //增加，修改
    @ResponseBody
    @RequestMapping(value = {"/talentintrduction/create","/talentintrduction/update"})
    public JSONObject talentUpdate(@RequestBody JSONObject json, HttpServletRequest request){
        //时间格式化
        Date talentTime=new Date();
        SimpleDateFormat ft=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String date=ft.format(talentTime);
        TanlentintrductionEntity tanlentintrductionEntity=new TanlentintrductionEntity();

        //获取cookie中的username
        String name = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username"))
                    name = cookie.getValue();
            }
        }

        //判断增，改的json数据中是否有ID，如果有则获取，没有则赋值
        if(!String.valueOf(json.get("id")).isEmpty())
        {
            tanlentintrductionEntity.setId((String) json.get("id"));
        }else {
            tanlentintrductionEntity.setId(String.valueOf(talentlmpl.findCountByTanlent()+1));
        }
        tanlentintrductionEntity.setCreateTime(date);
        tanlentintrductionEntity.setTitle((String)json.get("title"));
        tanlentintrductionEntity.setSchoolName((String) json.get("school_name"));


        //获取管理员的名字并存入Entity中
        tanlentintrductionEntity.setEditor(administratorlpml.findAdministratorEntityByUsername(name).getUsername());
        talentlmpl.save(tanlentintrductionEntity);

        //返回正确表示
        JSONObject object = new JSONObject();
        object.put("code", 20000);
        object.put("data", "success");

        return object;
    }



    //删除
    @ResponseBody
    @RequestMapping("/talenIntrduction/delete")
    public void delete(@RequestBody JSONObject jsonObject){
        talentlmpl.deleteById((String) jsonObject.get("id"));
    }


    //获取id，编辑者，时间传输给前端，解决前端编辑，创建后不显示前面三者的问题
    @ResponseBody
    @RequestMapping("/talenIntrduction/getthings")
    public JSONObject getthings() {
        int total = talentlmpl.findCountByTanlent() + 1;
        String id = String.valueOf(total);
        JSONObject object = new JSONObject();
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);

        int idcount = talentlmpl.findCountByTanlent() +1;
        while (talentlmpl.findById(String.valueOf(idcount)).isPresent()){
            idcount = idcount+1;
        }


        object.put("id", String.valueOf(idcount));
        object.put("date", date);
        //       object.put("editor", "admin");
        return object;
    }
}



