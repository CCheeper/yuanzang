package com.example.yuanz.controller;


//援藏人员功能


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.HelpmanEntity;
import com.example.yuanz.server.Helplmpl;
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


@Controller
public class HelpmanController {


    @Autowired
    Helplmpl helplmpl;

    @Autowired
    Schoolmpl schoolmpl;


    //显示援藏人员数据
    @ResponseBody
    @RequestMapping("/help/list")
    public JSONObject helplist(@RequestParam("page") String page,@RequestParam("limit") String limit,@RequestParam("username") String username){

        int pagenum=Integer.valueOf(page);
        int limtnum=Integer.valueOf(limit);
        int total= helplmpl.findCountByhelpman();

        List<HelpmanEntity> list=new ArrayList<HelpmanEntity>();
        JSONArray array=new JSONArray();

        //每页起始数据
        int startnum=(pagenum-1)*limtnum;

        //判断每页的第一条数据
        if (helplmpl.findUserEntitiesByUsername(username)!=null){
            HelpmanEntity helpmanEntity = helplmpl.findUserEntitiesByUsername(username);
            list.add(helpmanEntity);
        }else
        {
            if (total>pagenum * limtnum)
            {
                list=helplmpl.findByhelpmanRang(startnum,limtnum);
            }else {
                list=helplmpl.findByhelpmanRang(startnum,limtnum - total);
            }
        }

        //打包成json对象
        for (int i=0;i<list.size();i++)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",list.get(i).getId());
            jsonObject.put("username",list.get(i).getUsername());
            jsonObject.put("telephone",list.get(i).getTelephone());
            jsonObject.put("qq",list.get(i).getQq());
            jsonObject.put("email",list.get(i).getEmail());
            jsonObject.put("schoolId",list.get(i).getSchoolId());
            jsonObject.put("schoolName",list.get(i).getSchoolName());
            jsonObject.put("address",list.get(i).getAddress());
            jsonObject.put("gender",list.get(i).getGender());
            jsonObject.put("recruitId",list.get(i).getRecruitId());
            jsonObject.put("createTime",list.get(i).getCreateTime());
            array.add(jsonObject);
        }

        JSONObject data=new JSONObject();
        data.put("total",total);
        data.put("items",array);

        JSONObject helpman = new JSONObject();
        helpman.put("code",20000);
        helpman.put("data",data);

        return helpman;
    }


//删除援藏人员
    @ResponseBody
    @RequestMapping("/help/delete")
    public void delect(@RequestBody JSONObject jsonObject){
        helplmpl.deleteById((String) jsonObject.get("id"));

    }



}
