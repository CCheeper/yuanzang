package com.example.yuanz.controller;

// 工作动态功能

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.WorkdynamicEntity;
import com.example.yuanz.server.Administratorlpml;
import com.example.yuanz.server.Workdynamiclmpl;
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
import java.util.SimpleTimeZone;


@Controller
public class WorkdynamicController {


@Autowired
    Workdynamiclmpl workdynamiclmpl;
@Autowired
    Administratorlpml administratorlpml;



    //显示列表
    @ResponseBody
    @RequestMapping("/workdynamic/list")
    public JSONObject worklist(@RequestParam("page") String page,@RequestParam("limit")String limit,@RequestParam("title")String title){
        int pagenum=Integer.valueOf(page);
        int limitnum=Integer.valueOf(limit);
        int total=workdynamiclmpl.findCountByWorkdynamic();

        List<WorkdynamicEntity> list=new ArrayList<WorkdynamicEntity>();
        JSONArray jsonArray=new JSONArray();

        int startnum=(pagenum-1) * limitnum;
      //  System.out.println(startnum);

        if (workdynamiclmpl.findWorkdynamicEntityByTitle(title)!=null)
        {
            //判断成功则输出搜索框中找到的内容
            WorkdynamicEntity workdynamicEntity=workdynamiclmpl.findWorkdynamicEntityByTitle(title);
            list.add(workdynamicEntity);
        }else
            {
            //判断失败则直接输出整个列表
            if (total>pagenum*limitnum){
                list=workdynamiclmpl.findByWorkdynamicRange(0,limitnum);
            }else
            {
                list=workdynamiclmpl.findByWorkdynamicRange(0,total-limitnum);
            }
            }

        //将数据库中数据打包成json对象
        for (int i=0;i<list.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",list.get(i).getId());
            jsonObject.put("title",list.get(i).getTitle());
            jsonObject.put("message",list.get(i).getMessage());
            jsonObject.put("lasttime",list.get(i).getLasttime());
            jsonObject.put("editorid",list.get(i).getEditorid());
            jsonArray.add(jsonObject);
        }

        JSONObject data=new JSONObject();
        data.put("total",total);
        data.put("item",jsonArray);

        JSONObject workdynamic = new JSONObject();
        workdynamic.put("coke",20000);
        workdynamic.put("data",data);

        return workdynamic;
    }


    @ResponseBody
    @RequestMapping(value = {"/workdynamic/create","/workdynamic/update"})
    public JSONObject workUpdate(@RequestBody JSONObject json, HttpServletRequest request){
        //时间格式化
        Date workTime=new Date();
        SimpleDateFormat ft=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String date=ft.format(workTime);

            WorkdynamicEntity workdynamicEntity=new WorkdynamicEntity();
            workdynamicEntity.setLasttime(date);
            workdynamicEntity.setId((String)json.get("id"));
            workdynamicEntity.setTitle((String)json.get("title"));
            workdynamicEntity.setMessage((String)json.get("message"));
            workdynamicEntity.setEditorid((String)json.get("editorid"));

        //获取cookie中的username
        String name = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    name = cookie.getValue();
                }
            }
        }

        //获取管理员的名字并存入Entity中
        workdynamicEntity.setEditorid(administratorlpml.findAdministratorEntityByUsername(name).getUsername());
        workdynamiclmpl.save(workdynamicEntity);

        //返回正确表示
        JSONObject object = new JSONObject();
        object.put("code", 20000);
        object.put("data", "success");

        return object;
    }



    //删除工作动态
    @ResponseBody
    @RequestMapping("/workdynamic/delete")
    public void delete(@RequestBody JSONObject jsonObject){
        workdynamiclmpl.deleteById((String) jsonObject.get("id"));
    }

}
