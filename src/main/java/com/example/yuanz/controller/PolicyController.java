package com.example.yuanz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.PolicyEntity;
import com.example.yuanz.server.Administratorlpml;
import com.example.yuanz.server.Policylpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


//权限管理


@Controller
public class PolicyController {
    @Autowired
    Policylpml policylpml;
    @Autowired
    Administratorlpml administratorlpml;

    //删除管理功能
    @ResponseBody
    @RequestMapping("/policy/delect")
    public void delect(@RequestBody JSONObject json) {
        // String id = (String) json.get("id");
        policylpml.deleteById((String) json.get("id"));
    }

    //上传模块
    @ResponseBody
    @RequestMapping("/uploadfile")
    public JSONObject upload(@RequestParam("file") MultipartFile file,@RequestParam("id")String id,@RequestParam("editor")String editor ,@RequestParam("title")String title) {
        JSONObject object = new JSONObject();
        object.put("code", 20000);
        PolicyEntity policyEntity = new PolicyEntity();



        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);

        if (id.equals("")) {
            policyEntity.setId(String.valueOf(policylpml.findCountByPolicy() + 1));
        }else {
            policyEntity = policylpml.findById(id).get();
            policyEntity.setEditorid(editor);
            policyEntity.setTime(date);
            policyEntity.setFileurl(file.getOriginalFilename());
            policyEntity.setTitle(title);
        }
        policylpml.save(policyEntity);






        System.out.println(file.getClass());

        if (!file.isEmpty()) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(
                                file.getOriginalFilename())));
                System.out.println(file.getName());
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return object;
            } catch (IOException e) {
                e.printStackTrace();
                return object;
            }
            return object;
        } else {
            return object;
        }
    }

    @ResponseBody
    @RequestMapping("/upload")
    public JSONObject upload(@RequestBody JSONObject json) {

        System.out.println(json.get("name"));


        JSONObject object = new JSONObject();
        object.put("code", 20000);
        return object;
    }



    @ResponseBody
    @RequestMapping("/policy/list")
    public JSONObject postlist(@RequestParam("page") String page, @RequestParam("limit") String limit, @RequestParam("title") String title) {
        int pagenum = Integer.valueOf(page);
        int limitnum = Integer.valueOf(limit);
        int total = policylpml.findCountByPolicy();
        List<PolicyEntity> list = new ArrayList<PolicyEntity>();
        JSONArray array = new JSONArray();

        int startnum = (pagenum - 1) * limitnum;


        if (policylpml.findPolicyEntityByTitle(title) != null) {
            PolicyEntity policyEntity = policylpml.findPolicyEntityByTitle(title);
            list.add(policyEntity);
        } else {
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
            object.put("editor", list.get(i).getEditorid());
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
    @RequestMapping(value = {"/policy/create", "/policy/update"})

    public JSONObject create(@RequestBody JSONObject json, HttpServletRequest request) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);

        int total = policylpml.findCountByPolicy();


        PolicyEntity policyEntity = new PolicyEntity();
        policyEntity.setFileurl("模块未完成，等待上传模块完成传回url");
        policyEntity.setId(String.valueOf(total + 1));  //总行数加1
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

        JSONObject object = new JSONObject();
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);

        int idcount = policylpml.findCountByPolicy() +1;
        while (policylpml.findById(String.valueOf(idcount)).isPresent()){
            idcount = idcount+1;
        }
        object.put("id", String.valueOf(idcount));
        object.put("date", date);


        return object;
    }
}
