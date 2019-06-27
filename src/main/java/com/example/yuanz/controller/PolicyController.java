package com.example.yuanz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PolicyController {



    @ResponseBody
    @RequestMapping("/policy/delect")
    public void delect(@RequestBody JSONObject json) {
        String id = (String) json.get("id");


    }


    @ResponseBody
        @RequestMapping("/policy/list")
        public JSONObject postlist(@RequestParam("page") String page, @RequestParam("limit") String limit) {
            int pagenum = Integer.valueOf(page);
            int limitnum = Integer.valueOf(limit);
            int total = needlpml.findCountByNeed();
            List<NeedEntity> list;
            JSONArray array = new JSONArray();

            int startnum = (pagenum - 1) * limitnum;
            if (total > pagenum * limitnum) {
                list = needlpml.findByNeedRange(startnum, limitnum);
            } else {
                list = needlpml.findByNeedRange(startnum, pagenum * limitnum - total);
            }

            for (int i = 0; i < list.size(); i++) {
                JSONObject object = new JSONObject();
                object.put("createTime", list.get(i).getTime());
                object.put("id", list.get(i).getId());
                object.put("title", list.get(i).getTitle());
                object.put("fileurl", list.get(i).getFileurl());
                object.put("editorid", list.get(i).getEditorid());
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
}
