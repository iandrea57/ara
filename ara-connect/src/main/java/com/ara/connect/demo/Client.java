/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.demo;

import com.ara.connect.model.api.AraRequest;
import com.ara.connect.model.api.AraResponse;
import com.ara.connect.model.constant.RequestParam;
import com.ara.connect.model.constant.RequestType;
import com.ara.connect.model.constant.ResponseKey;
import com.ara.connect.model.user.UserInfo;
import com.ara.connect.model.user.UserMsgInfo;
import com.ara.connect.service.IClient;
import org.apache.commons.lang.math.NumberUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 下午5:07
 */
public class Client implements IClient {

    private static final DateFormat df = new SimpleDateFormat(
            "yyyy年MM月dd日 HH时mm分ss秒");

    private static final int BUF_SIZE = 4096;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private volatile long since = System.currentTimeMillis();

    public AraResponse getResponse(AraRequest request) {
        try {
            String ip = "127.0.0.1";
            int port = 15757;
            Socket socket = new Socket(ip, port);
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(is, "utf-8");
            OutputStreamWriter writer = new OutputStreamWriter(os, "utf-8");
            writer.write(objectMapper.writeValueAsString(request));
            writer.flush();
            char [] cbuf = new char[BUF_SIZE];
            Arrays.fill(cbuf, '\0');
            int len = reader.read(cbuf, 0, BUF_SIZE);
            StringBuilder sb = new StringBuilder(BUF_SIZE);
            if (len > 0) {
                sb.append(cbuf, 0, len);
                String respStr = sb.toString();
                /*writer.close();
                reader.close();*/
                return objectMapper.readValue(respStr, AraResponse.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserInfo login(String userName, String password) {
        UserInfo userInfo = null;
        AraRequest request = new AraRequest();
        request.setType(RequestType.LOG_IN);
        Map<String, String> params = new HashMap<String, String>();
        params.put(RequestParam.USER_NAME, userName);
        params.put(RequestParam.PASSWORD, password);
        request.setParams(params);
        AraResponse response = getResponse(request);
        try {
            if (response != null && response.getResult() != null) {
                Map<String, String> result = response.getResult();
                if (result.get(ResponseKey.USER_INFO) != null) {
                    userInfo = objectMapper.readValue(result.get(ResponseKey.USER_INFO), UserInfo.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    @Override
    public void logout(long userId) {
        //TODO:
    }

    @Override
    public List<UserInfo> getFriends(long userId) {
        //TODO:
        return null;
    }

    @Override
    public List<UserInfo> getOnlineFriends(long userId) {
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        AraRequest request = new AraRequest();
        request.setType(RequestType.GET_ONLINE_FRIENDS);
        Map<String, String> params = new HashMap<String, String>();
        params.put(RequestParam.USER_ID, String.valueOf(userId));
        request.setParams(params);
        AraResponse response = getResponse(request);
        try {
            if (response != null && response.getResult() != null) {
                Map<String, String> result = response.getResult();
                if (result.get(ResponseKey.USER_INFO_LIST) != null) {
                    userInfoList = objectMapper.readValue(result.get(ResponseKey.USER_INFO_LIST),  new TypeReference<List<UserInfo>> () {});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfoList;
    }

    @Override
    public void sendMsg(long fromId, long toId, String msg) {
        AraRequest request = new AraRequest();
        request.setType(RequestType.SEND_MSG);
        Map<String, String> params = new HashMap<String, String>();
        params.put(RequestParam.FROM_ID, String.valueOf(fromId));
        params.put(RequestParam.TO_ID, String.valueOf(toId));
        params.put(RequestParam.MSG, String.valueOf(msg));
        request.setParams(params);
        AraResponse response = getResponse(request);
        try {
            if (response != null && response.getResult() != null) {
                Map<String, String> result = response.getResult();
                if (result.get(ResponseKey.STRING) != null) {
//                    System.out.println("info: " + result.get(ResponseKey.STRING));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserMsgInfo> aquireMsg(long userId) {
        List<UserMsgInfo> msgInfoList = new ArrayList<UserMsgInfo>();
        AraRequest request = new AraRequest();
        request.setType(RequestType.AQUIRE_MSG);
        Map<String, String> params = new HashMap<String, String>();
        params.put(RequestParam.USER_ID, String.valueOf(userId));
        request.setParams(params);
        AraResponse response = getResponse(request);
        try {
            if (response != null && response.getResult() != null) {
                Map<String, String> result = response.getResult();
                if (result.get(ResponseKey.USER_MSG_INFO_LIST) != null) {
                    msgInfoList = objectMapper.readValue(result.get(ResponseKey.USER_MSG_INFO_LIST), new TypeReference<List<UserMsgInfo>> () {});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgInfoList;
    }

    @Override
    public List<UserMsgInfo> aquireMsg(long userId, long since) {
        List<UserMsgInfo> msgInfoList = new ArrayList<UserMsgInfo>();
        AraRequest request = new AraRequest();
        request.setType(RequestType.AQUIRE_MSG_SINCE);
        Map<String, String> params = new HashMap<String, String>();
        params.put(RequestParam.USER_ID, String.valueOf(userId));
        params.put(RequestParam.SINCE, String.valueOf(since));
        request.setParams(params);
        AraResponse response = getResponse(request);
        try {
            if (response != null && response.getResult() != null) {
                Map<String, String> result = response.getResult();
                if (result.get(ResponseKey.USER_MSG_INFO_LIST) != null) {
                    msgInfoList = objectMapper.readValue(result.get(ResponseKey.USER_MSG_INFO_LIST), new TypeReference<List<UserMsgInfo>> () {});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgInfoList;
    }

    public void client() {
        printStatus("login");
        UserInfo userInfo = login("qiqi", "qiqi");
        if (userInfo != null) {
            System.out.println("login success : " + userInfo);
            final long userId = userInfo.getUserId();

            printStatus("getOnlineFriends");
            List<UserInfo> friendList = getOnlineFriends(userId);
            for (UserInfo friend : friendList) {
                System.out.println("friend --> " + friend.getUserId() + " : " + friend.getUserName());
            }

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    List<UserMsgInfo> msgList = aquireMsg(userId);
                    if (msgList != null) {
                        for (UserMsgInfo msg : msgList) {
                            System.out.println("MSG from " + msg.getFromName() + " : " + msg.getMsg() + "                      " + df.format(msg.getAddTime()));
                        }
                    }
                }
            }, 1000, 1000);

            printStatus("sendMsg");
            System.out.println("input \"[friendId]\"");
            Scanner scanner = new Scanner(System.in);
            long toId = NumberUtils.toLong(scanner.nextLine());
            if (toId > 0) {
                System.out.println("input \"[message]\"");
                boolean status = true;
                while (status) {
                    String input = scanner.nextLine();
                    if ("q".equals(input) || "quit".equals(input) || "exit".equals(input)) {
                        status = false;
                    } else {
                        sendMsg(userId, toId, input);
                    }
                }
            }
            System.out.println("exit !");
        } else {
            System.out.println("login failed ");
        }
    }

    public void group() {
        printStatus("login");
//        UserInfo userInfo = login("七七", "qiqi");
//        UserInfo userInfo = login("Ara", "ara");
        UserInfo userInfo = login("彭海龙", "penghailong");
        if (userInfo != null) {
            System.out.println("login success : " + userInfo);
            final long userId = userInfo.getUserId();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    long nowSince = since;
                    long nextSince = System.currentTimeMillis() / 1000 * 1000;
                    if (nextSince > nowSince) {
                        since = nextSince;
                        List<UserMsgInfo> msgList = aquireMsg(1, nowSince);
                        if (msgList != null) {
                            for (UserMsgInfo msg : msgList) {
                                System.out.println("MSG from " + msg.getFromName() + " : " + msg.getMsg() + "                      " + df.format(msg.getAddTime()));
                            }
                        }
                    }
                }
            }, 1000, 3000); // 每三秒获取一次消息

            printStatus("sendMsg");
            Scanner scanner = new Scanner(System.in);
            System.out.println("input \"[message]\"");
            boolean status = true;
            while (status) {
                String input = scanner.nextLine();
                if ("q".equals(input) || "quit".equals(input) || "exit".equals(input)) {
                    status = false;
                } else {
                    sendMsg(userId, 1, input);
                }
            }
            System.out.println("exit !");
        } else {
            System.out.println("login failed ");
        }
    }

    public static void main(String[] args) {

        final Client client = new Client();

        client.group();

        System.exit(0);
    }

    private static void printStatus(String value) {
        System.out.println("--------------------  " + value + "  ---------------------");
    }
}
