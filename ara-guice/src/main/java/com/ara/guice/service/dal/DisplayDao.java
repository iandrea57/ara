/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.service.dal;

import com.ara.guice.annotation.DAO;
import com.ara.guice.model.DisplayInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 展示信息数据服务
 *
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-6-27 下午3:26
 */
@DAO(entityClass = DisplayInfo.class)
public class DisplayDAO {

    private static final DisplayInfo info = initInfo();

    private static DisplayInfo initInfo() {
        DisplayInfo info = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    DisplayDAO.class.getResourceAsStream("/display.properties"),
                    "utf-8"));
            Map<String, String> dataMap = new HashMap<String, String>();
            String line;
            while((line = reader.readLine()) != null) {
                line = line.trim();
                String[] keyValue = line.split("=");
                if (keyValue.length == 2) {
                    dataMap.put(keyValue[0], keyValue[1]);
                }
            }
            info = new DisplayInfo();
            info.setTitle(dataMap.get("title"));
            info.setBody(dataMap.get("body"));
            info.setTail(dataMap.get("tail"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }

    public DisplayInfo getInfo() {
        return new DisplayInfo(info);
    }
}
