/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.controllers;

import com.ara.web.constant.CommentDef;
import com.ara.web.model.Account;
import com.ara.web.model.Comment;
import com.ara.web.model.User;
import com.ara.web.model.mvc.UserComment;
import com.ara.web.service.AccountService;
import com.ara.web.service.CommentService;
import com.ara.web.utils.RequestTypeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:iiiandrea57@gmail.com">i云涯</a>
 * @createTime 14-3-28 下午4:50
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private AccountService accountService;

    @RequestMapping()
    public ModelAndView home(HttpServletRequest request, User host) {
        ModelAndView mv = new ModelAndView("home/home");

        List<Comment> comments = commentService.getUserCommentList(host.getId());
        List<UserComment> userComments = convertUserComments(comments);

        mv.addObject("comments", userComments);
        return mv;
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ModelAndView comment(HttpServletRequest request, User host,
            @RequestParam(value = "comment", required = false, defaultValue = "") String content) {
        ModelAndView mv = new ModelAndView("redirect:/home");
        if (StringUtils.isNotBlank(content)) {
            Comment comment = new Comment();
            comment.setUserId(host.getId());
            comment.setTopicId(CommentDef.DEFAULT_TOPIC_ID);
            comment.setComment(content);
            comment.setAddTime(new Date());
            commentService.save(comment);
        }
        return mv;
    }


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView info(HttpServletRequest request, User host,
            @RequestParam(value = "account", required = false, defaultValue = "") String account)
            throws Exception {
        ModelAndView mv = new ModelAndView("home/info");
        Account accountInfo = accountService.get(host.getId());
        mv.addObject("account", accountInfo);
        mv.addObject("host", host);
        return mv;
    }

    /**
     * 请求Json返回
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET, params = RequestTypeUtils.JSON_REQUEST_PARAM)
    public ModelAndView infoJson(HttpServletRequest request, User host,
            @RequestParam(value = "account", required = false, defaultValue = "") String account)
            throws Exception {
        ModelAndView mv = new ModelAndView("jsonView");
        Account accountInfo = accountService.get(host.getId());
        mv.addObject("account", accountInfo);
        mv.addObject("host", host);
        return mv;
    }



    private List<UserComment> convertUserComments(List<Comment> comments) {
        List<UserComment> result = new ArrayList<UserComment>();
        if (CollectionUtils.isNotEmpty(comments)) {
            for (Comment comment : comments) {
                UserComment userComment = new UserComment();
                userComment.setUserId(comment.getUserId());
                userComment.setUserName(accountService.get(comment.getUserId()).getAccount());
                userComment.setTopicId(comment.getTopicId());
                userComment.setComment(comment.getComment());
                userComment.setAddTime(comment.getAddTime());
                result.add(userComment);
            }
        }
        return result;
    }



}
