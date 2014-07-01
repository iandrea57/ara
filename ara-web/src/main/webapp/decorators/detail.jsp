<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<html>
<head>
    <page:applyDecorator page="/decorators/empty.jsp" name="header"/>
    <title><decorator:title default="装饰器页面..."/></title>
    <decorator:head/>
</head>
<aside>
    <nav>
        <ul>
            <li class="cur"><a href="/appgroup/list?sid=${sid}">应用类别管理</a></li>
            <li class="t"><a href="/appgroup/add?sid=${sid}">添加类别</a></li>
            <li class="t"><a href="/appgroup/list?sid=${sid}">修改类别</a></li>
        </ul>
        <ul>
            <li class="cur"><a href="#">应用管理</a></li>
            <li class="t"><a href="/appgroup/addapp?sid=${sid}">添加App应用</a></li>
            <li class="t"><a href="/appgroup/delapp?sid=${sid}">删除App应用</a></li>
            <li class="t"><a href="/appgroup/additem?sid=${sid}">添加网站类应用</a>
            </li>
            <li class="t"><a href="/appgroup/delitem?sid=${sid}">删除网站类应用</a>
            </li>

        </ul>
    </nav>
</aside>
<decorator:body/>

<page:applyDecorator page="/decorators/empty.jsp" name="footer"/>
</body>