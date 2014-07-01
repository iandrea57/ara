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
import com.ara.connect.service.IServer;
import com.ara.connect.service.impl.MsgService;
import com.ara.connect.service.impl.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-2-28 下午3:14
 */
@Component
public class Server implements IServer {

    private static final DateFormat df = new SimpleDateFormat(
            "yyyy年MM月dd日 HH时mm分ss秒");

    private static final int BUF_SIZE = 4096;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserService userService;

    @Autowired
    private MsgService msgService;

    @Override
    public UserInfo login(String userName, String password) {
        UserInfo user = userService.login(userName, password);
        return user;
    }

    @Override
    public void logout(long userId) {
        userService.logout(userId);
    }

    @Override
    public List<UserInfo> getFriends(long userId) {
        return userService.getFriends(userId);
    }

    @Override
    public List<UserInfo> getOnlineFriends(long userId) {
        return userService.getOnlineFriends(userId);
    }

    @Override
    public void sendMsg(long fromId, long toId, String msg) {
        msgService.sendMsg(fromId, toId, msg, new Date());
    }

    @Override
    public List<UserMsgInfo> aquireMsg(long userId) {
        List<UserMsgInfo> infoList = msgService.aquireMsg(userId);
        return infoList;
    }

    @Override
    public List<UserMsgInfo> aquireMsg(long userId, long since) {
        List<UserMsgInfo> infoList = msgService.aquireMsg(userId, new Date(since));
        return infoList;
    }

    public void startServer() {
        try {
            String ip = "127.0.0.1";
            int port = 15757;
            System.out.println(
                    "Starting server with port : " + port);
            ServerSocket serverSocket = new ServerSocket(15757);
            while (true) {
                Socket socket = serverSocket.accept();
                OutputStream os = socket.getOutputStream();
                InputStream is = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(is, "utf-8");
                OutputStreamWriter writer = new OutputStreamWriter(os, "utf-8");
                char [] cbuf = new char[BUF_SIZE];
                Arrays.fill(cbuf, '\0');
                int len = reader.read(cbuf, 0, BUF_SIZE);
                StringBuilder sb = new StringBuilder(BUF_SIZE);
                sb.append(cbuf, 0, len);
                String reqStr = sb.toString();
                System.out.println("get request --> " + reqStr);
                AraRequest request = objectMapper.readValue(reqStr,
                        AraRequest.class);
                AraResponse response = new AraResponse();
                if (request != null && request.getParams() != null) {
                    Map<String, String> params = request.getParams();
                    response.setType(request.getType());
                    Map<String, String> result = new HashMap<String, String>();
                    response.setResult(result);
                    long userId;
                    switch (request.getType()) {
                        case RequestType.LOG_IN:
                            String userName = params.get(RequestParam.USER_NAME);
                            String password = params.get(RequestParam.PASSWORD);
                            if (StringUtils.isNotBlank(userName)
                                    && StringUtils.isNotBlank(password)) {
                                UserInfo user = login(userName, password);
                                String value = objectMapper.writeValueAsString(user);
                                result.put(ResponseKey.USER_INFO, value);
                            } else {
                                result.put(ResponseKey.STRING, "参数错误");
                            }
                            break;
                        case RequestType.GET_ONLINE_FRIENDS:
                            userId = NumberUtils.toLong(params.get(RequestParam.USER_ID));
                            if (userId > 0) {
                                List<UserInfo> userList = getOnlineFriends(userId);
                                String value = objectMapper.writeValueAsString(userList);
                                result.put(ResponseKey.USER_INFO_LIST, value);
                            } else {
                                result.put(ResponseKey.STRING, "参数错误");
                            }
                            break;
                        case RequestType.SEND_MSG:
                            long fromId = NumberUtils.toLong(params.get(RequestParam.FROM_ID));
                            long toId = NumberUtils.toLong(params.get(RequestParam.TO_ID));
                            String msg = params.get(RequestParam.MSG);
                            if (fromId > 0 && toId > 0 && StringUtils.isNotBlank(msg)) {
                                sendMsg(fromId, toId, msg);
                                result.put(ResponseKey.STRING, "消息发送成功");
                            } else {
                                result.put(ResponseKey.STRING, "参数错误");
                            }
                            break;
                        case RequestType.AQUIRE_MSG:
                            userId = NumberUtils.toLong(params.get(RequestParam.USER_ID));
                            if (userId > 0) {
                                List<UserMsgInfo> msgList = aquireMsg(userId);
                                String value = objectMapper.writeValueAsString(msgList);
                                result.put(ResponseKey.USER_MSG_INFO_LIST, value);
                            } else {
                                result.put(ResponseKey.STRING, "无好友信息");
                            }
                            break;
                        case RequestType.AQUIRE_MSG_SINCE:
                            userId = NumberUtils.toLong(params.get(RequestParam.USER_ID));
                            long since = NumberUtils.toLong(params.get(RequestParam.SINCE));
                            if (userId > 0) {
                                List<UserMsgInfo> msgList = aquireMsg(userId, since);
                                String value = objectMapper.writeValueAsString(msgList);
                                result.put(ResponseKey.USER_MSG_INFO_LIST, value);
                            } else {
                                result.put(ResponseKey.STRING, "无好友信息");
                            }
                            break;
                        default:
                            result.put(ResponseKey.STRING, "不支持此操作");
                            break;
                    }
                }
                writer.write(objectMapper.writeValueAsString(response));
                writer.flush();
                /*reader.close();
                writer.close();*/
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void testServer() {
        String userName = "ara";
        String password = "ara";
        UserInfo user = login(userName, password);
        System.out.println(" if login --> " + (user != null));
        if (user != null) {
            System.out.println(
                    "----------- message info ---------------------");
            List<UserMsgInfo> msgList = aquireMsg(user.getUserId());
            for (UserMsgInfo msg : msgList) {
                System.out.println(
                        msg.getFromName() + ": " + msg.getMsg() + "      " + df.format(
                                msg.getAddTime()));
            }
            System.out.println("----------- friend info ---------------------");
            List<UserInfo> friendList = getOnlineFriends(user.getUserId());
            for (UserInfo friend : friendList) {
                System.out.println(
                        " online friend --> " + friend.getUserName());
                sendMsg(user.getUserId(), friend.getUserId(), "吃饭去了!");
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        Server server = context.getBean(Server.class);
        System.out.println("----------- start ------------------");

        long start = System.currentTimeMillis();

//        server.testServer();
        server.startServer();

        System.out.println("----------- end ---------------------");
        System.out.println(
                " total used " + (System.currentTimeMillis() - start) + " ms");
        System.exit(0);
    }
}
