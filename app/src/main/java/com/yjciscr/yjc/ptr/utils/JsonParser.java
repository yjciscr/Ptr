package com.yjciscr.yjc.ptr.utils;

import com.yjciscr.yjc.ptr.bean.Cnm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJC on 2016/5/25.
 */
public class JsonParser {
    public static List<Cnm> getTravelsFromJson(String json) {
        List<Cnm> cnmList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String retmsg = jsonObject.getString(Constans.RETMSG);
            if (Constans.SUSS.equals(retmsg)) {
                JSONArray jsonArray = jsonObject.getJSONArray(Constans.DATA);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String type = jsonObject1.getString(Constans.TYPE);
                    String title = jsonObject1.getString(Constans.TITLE);
                    String summary = jsonObject1.getString(Constans.SUMMARY);
                    String groupthumbnail = jsonObject1.getString(Constans.GROUPTHUMBNAIL);
                    String surl = jsonObject1.getString(Constans.SURL);
                    Cnm cnm = new Cnm();
                    cnm.setType(type);
                    cnm.setTitle(title);
                    cnm.setSummary(summary);
                    cnm.setGroupthumbnail(groupthumbnail);
                    cnm.setSurl(surl);
                    cnmList.add(cnm);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cnmList;
    }
}
