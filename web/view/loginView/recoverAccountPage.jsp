<%-- 
    Document   : recoverAccountPage
    Created on : Feb 1, 2024, 4:26:12 PM
    Author     : hbtth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../../Template/libheader.jsp" %>
        <title>Forgot password</title>
        <c:url value="CSS/recoverAccountCSS.css" var="style"/>
        <c:url value="/JS/jquery-3.7.1.min.js" var="jquery"/>
        <c:url value="JS/recoverAccountJS.js" var="script"/>
        <link rel="stylesheet" href="${style}"/>   
    </head>

    <body>
        <div class="forgot-password">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="main-left">
                            <img src="image/image_logo/logo.png" alt="">
                            <h1>Choose your account</h1>
                        </div>

                    </div>
                    <div class="col-md-6">
                        <form class="main-right" action="setnewpassword" method="post">
                            <c:forEach items="${listAccount}" var="account">
                                <label class="account-box" for="${account.user_id}">
                                    <img src="${account.image}"
                                         alt="">
                                    <div class="user-infor">
                                        <p>${account.fullname}</p>
                                        <p>username: ${account.username}</p>
                                    </div>
                                    <input type="radio" value="${account.username}" id="${account.user_id}" onchange="Onselected(this)" name="username"/>
                                    <input type="hidden" value="${account.email}" name="email"/>
                                </label>
                                
                            </c:forEach>
                            
                        </form>
                    </div>
                    <div class="col-md-12">
                        <div class="button-box">
                            <button onclick="cancel()">Cancel</button>
                            <button onclick="onContinue()" type="submit">Continue</button>                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="${jquery}"></script>
    <script src="${script}"></script>
</html>