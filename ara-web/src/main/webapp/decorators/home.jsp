<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<html>
<head>
    <page:applyDecorator page="/decorators/empty.jsp" name="header"/>
    <%--从被装饰页面获取title标签内容, 并设置默认值--%>
    <title><decorator:title default="默认title"/></title>
    <%--从被装饰页面获取head标签内容--%>
    <decorator:head/>
</head>
<body>
<aside>
    <nav>
        <ul>
            <li class="cur"><a href="#">APP管理</a></li>
            <li class="t"><a href="/pageconsole/list?sid=${sid}">App修改</a></li>
            <li class="t"><a href="/pageconsole/list?sid=${sid}">APP流量控制</a></li>
            <li class="t"><a href="/pageconsole/list?sid=${sid}">APP权限控制</a></li>
        </ul>
        <ul>
            <li class="cur"><a href="#">推荐管理</a></li>
        </ul>
    </nav>
</aside>
<%--从被装饰页面获取body标签内容--%>
<decorator:body/>
<page:applyDecorator page="/decorators/empty.jsp" name="footer"/>
</body>
</html>