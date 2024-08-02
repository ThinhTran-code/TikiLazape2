<%-- 
    Document   : setNewPassword
    Created on : Feb 25, 2024, 9:37:21 PM
    Author     : hbtth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../../Template/libheader.jsp" %>
        <c:url value="/JS/jquery-3.7.1.min.js" var="libJquery"/>
        <c:url var="setpassword" value="/CSS/setnewpassword.css"/>
        <c:url var="script" value="JS/setnewpasswordJS.js"/>
        <link rel="stylesheet" href="${setpassword}">
        <title>Change your password</title>
    </head>
    <body>
    <div class="reset-password">
        <div class="container">
            <form class="row" method="POST">
                <div class="col-md-6">
                    <div class="main-left">
                        <img src="image/image_logo/logo.png" alt="">
                        <h1>Enter new password</h1>
                    </div>

                </div>
                <div class="col-md-6 right">
                    <div class="main-right">
                        <div class="input-box">
                            <input type="password" placeholder="Enter new password" name="password">
                        </div>
                        <div class="input-box">
                            <input type="password" placeholder="Re-Enter new password" name="re_password">
                        </div>
                        <div class="OTP-box">
                            <input type="password" placeholder="Enter OTP" name="OTP">
                            <input type="hidden" id="username" value="${sessionScope.username}"/>
                            <input type="hidden" id="email" value="${sessionScope.email}"/>
                            <a>Send Again</a>
                        </div>
                            <p style="color: red; font-size: 18px">${notice}</p>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="button-box">
                        <button onclick="resetPassword()" type="submit">Reset password</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
        <script src="${libJquery}"></script>
        <script src="${script}"></script>
</body>

</html>
