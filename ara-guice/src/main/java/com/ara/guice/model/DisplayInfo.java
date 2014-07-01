/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.model;

/**
 * 展示信息
 *
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-6-27 下午3:18
 */
public class DisplayInfo {

    /** 标题 **/
    private String title;

    /** 主体 **/
    private String body;

    /** 尾部提示 **/
    private String tail;

    public DisplayInfo() {}

    public DisplayInfo(DisplayInfo info) {
        if (info != null) {
            this.title = info.getTitle();
            this.body = info.getBody();
            this.tail = info.getTail();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "DisplayInfo{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", tail='" + tail + '\'' +
                "} " + super.toString();
    }
}
