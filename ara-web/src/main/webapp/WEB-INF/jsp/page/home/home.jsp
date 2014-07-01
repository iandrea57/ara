<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: i云涯
  Date: 14-5-26
  Time: 下午4:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主页</title>
    <style type="text/css">
        span.itemTitle {text-align: right}
        div.error {color: #FF0000}
    </style>
</head>
<body>
    <%--用户基本信息--%>
    <div>
        <div>
            <span class="itemTitle">用户id: </span>
            <span>${host.id}</span>
        </div>
        <div>
            <span class="itemTitle">用户名: </span>
            <span>${host.name}</span>
        </div>
    </div>

    <%--评论列表--%>
    <div>
        <c:if test="${!empty comments}">
            <ul>
                <c:forEach var="comment" items="${comments}">
                    <li>
                        <span class="itemTitle">${comment.userName} <fmt:formatDate value="${comment.addTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/> 说: </span>
                        <span>${comment.comment}</span>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>

    <%--评论框--%>
    <div>
        <form method="post" action="/home/comment">
            <div><textarea name="comment"></textarea></div>
            <div><input type="submit" value="评论" /></div>
        </form>
    </div>
</body>
</html>