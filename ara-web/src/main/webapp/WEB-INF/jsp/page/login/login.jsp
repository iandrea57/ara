<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: i云涯
  Date: 14-6-3
  Time: 下午2:49
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ara-web登录</title>
    <style type="text/css">
        span.itemTitle {text-align: right}
        div.error {color: #FF0000}
    </style>
</head>
<body>

    <form method="post">
        <c:if test="${!empty error}">
            <div class="error">${error}</div>
        </c:if>
        <div>
            <div>
                <span class="itemTitle">用户名: </span>
                <span><input type="text" name="account" value="${account}" /></span>
            </div>
            <div>
                <span class="itemTitle">密码: </span>
                <span><input type="password" name="password" value="" /></span>
            </div>
        </div>
        <div>
            <input type="submit" value="登录" />
        </div>
    </form>

</body>
</html>