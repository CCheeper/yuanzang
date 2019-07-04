package com.example.yuanz.controller;

import ch.qos.logback.classic.db.DBHelper;
import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.PowerEditEntity;
import com.example.yuanz.server.Powerlmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;

@Controller
public class PowerController {
    @Autowired
    Powerlmpl powerlmpl;

    @ResponseBody
    @RequestMapping("/power/sreach")
    public JSONObject search(@RequestBody JSONObject json) {
        JSONObject data = new JSONObject();

        if (powerlmpl.findById((String) json.get("title")).isPresent()) {
            ArrayList<String> listQuery = new ArrayList<String>();

            PowerEditEntity powerEditEntity = powerlmpl.findById((String) json.get("title")).get();
            if (powerEditEntity.getRoleEdit().equals(1)) {
                listQuery.add("role_edit");
            }
            if (powerEditEntity.getPersonalEdit().equals(1)) {
                listQuery.add("personal_edit");
            }
            if (powerEditEntity.getNeedEdit().equals(1)) {
                listQuery.add("need_edit");
            }
            if (powerEditEntity.getHelppeopleEdit().equals(1)) {
                listQuery.add("helppeople_edit");
            }
            if (powerEditEntity.getHelpZEdit().equals(1)) {
                listQuery.add("helpZ_edit");
            }
            if (powerEditEntity.getWorkdataEdit().equals(1)) {
                listQuery.add("workdata_edit");
            }
            if (powerEditEntity.getRoadEdit().equals(1)) {
                listQuery.add("road_edit");
            }
            if (powerEditEntity.getSchoolEdit().equals(1)) {
                listQuery.add("school_edit");
            }
            if (powerEditEntity.getPowerEdit().equals(1)) {
                listQuery.add("power_edit");
            }
            data.put("list", listQuery);
            data.put("status", true);
        }

        JSONObject message = new JSONObject();
        message.put("code", 20000);
        message.put("data", data);

        return message;
    }


    @ResponseBody
    @RequestMapping("/power/edit")
    public JSONObject searcha(@RequestBody JSONObject json) {
        JSONObject jsonObject = new JSONObject();
        ArrayList<String> checkList = (ArrayList<String>) json.get("checkList");
        String name = (String) json.get("title");
        PowerEditEntity powerEditEntity = powerlmpl.findById(name).get();
        boolean flag[] = new boolean[9];
        for (int i = 0; i < flag.length; i++) {
            flag[i] = true;
        }
        for (int i = 0; i < checkList.size(); i++) {
            if (flag[0])
                if (checkList.get(i).equals("role_edit")) {
                    powerEditEntity.setRoleEdit(1);
                    flag[0] = false;
                } else {
                    powerEditEntity.setRoleEdit(0);

                }
            if (flag[1])
                if (checkList.get(i).equals("personal_edit")) {
                    powerEditEntity.setPersonalEdit(1);
                    flag[1] = false;
                } else {
                    powerEditEntity.setPersonalEdit(0);

                }
            if (flag[2])
                if (checkList.get(i).equals("need_edit")) {
                    powerEditEntity.setNeedEdit(1);
                    flag[2] = false;
                } else {
                    powerEditEntity.setNeedEdit(0);

                }
            if (flag[3])
                if (checkList.get(i).equals("helpZ_edit")) {
                    powerEditEntity.setHelpZEdit(1);
                    flag[3] = false;
                } else {
                    powerEditEntity.setHelpZEdit(0);

                }
            if (flag[4])
                if (checkList.get(i).equals("workdata_edit")) {
                    powerEditEntity.setWorkdataEdit(1);
                    flag[4] = false;
                } else {
                    powerEditEntity.setWorkdataEdit(0);

                }
            if (flag[5])
                if (checkList.get(i).equals("power_edit")) {
                    powerEditEntity.setPowerEdit(1);
                    flag[5] = false;
                } else {
                    powerEditEntity.setPowerEdit(0);

                }
            if (flag[6])
                if (checkList.get(i).equals("road_edit")) {
                    powerEditEntity.setRoadEdit(1);
                    flag[6] = false;
                } else {
                    powerEditEntity.setRoadEdit(0);

                }
            if (flag[7])
                if (checkList.get(i).equals("school_edit")) {
                    powerEditEntity.setSchoolEdit(1);
                    flag[7] = false;
                } else {
                    powerEditEntity.setSchoolEdit(0);

                }
            if (flag[8])
                if (checkList.get(i).equals("helppeople_edit")) {
                    powerEditEntity.setHelppeopleEdit(1);
                    flag[8] = false;
                } else {
                    powerEditEntity.setHelppeopleEdit(0);

                }
        }
        powerlmpl.save(powerEditEntity);


        jsonObject.put("code", 20000);
        return jsonObject;
    }
}
