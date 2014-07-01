/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.service;

import com.ara.web.dao.CommentDAO;
import com.ara.web.entity.CommentEntity;
import com.ara.web.model.Comment;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-5-6 下午4:06
 */
@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO;

    public List<Comment> getUserCommentList(long userId) {
        List<Comment> result;

        String hql = "from CommentEntity where userId = " + userId;
        List<CommentEntity> entityList = commentDAO.getListByHQL(hql);

        result = commentFromEntity(entityList);

        return result;
    }

    public void save(Comment comment) {
        CommentEntity entity = entityFromComment(comment);
        commentDAO.add(entity);
    }

    private CommentEntity entityFromComment(Comment comment) {
        CommentEntity entity = null;
        if (comment != null) {
            entity = new CommentEntity();
            entity.setId(comment.getId());
            entity.setUserId(comment.getUserId());
            entity.setTopicId(comment.getTopicId());
            entity.setComment(comment.getComment());
            entity.setAddTime(new Timestamp(comment.getAddTime().getTime()));
        }
        return entity;
    }


    private List<Comment> commentFromEntity(List<CommentEntity> entityList) {
        List<Comment> result = new ArrayList<Comment>();
        if (CollectionUtils.isNotEmpty(entityList)) {
            for (CommentEntity entity : entityList) {
                Comment comment = commentFromEntity(entity);
                if (comment != null) {
                    result.add(comment);
                }
            }
        }
        return result;
    }

    private Comment commentFromEntity(CommentEntity entity) {
        Comment comment = null;
        if (entity != null) {
            comment = new Comment();
            comment.setId(entity.getId());
            comment.setUserId(entity.getUserId());
            comment.setTopicId(entity.getTopicId());
            comment.setComment(entity.getComment());
            comment.setAddTime(entity.getAddTime());
        }
        return comment;
    }


}
