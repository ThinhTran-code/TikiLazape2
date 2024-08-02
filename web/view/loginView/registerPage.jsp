<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="util.*" %>
<!DOCTYPE html>

<html lang="en" dir="ltr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <%@include file="../../Template/libheader.jsp" %>
        <c:url value="/CSS/registerCSS.css" var="registerPage"/>
        <c:url value="/JS/jquery-3.7.1.min.js" var="libJquery"/>
        <c:url value="/JS/registerJS.js" var="register"/>
        <link rel="stylesheet" href="${registerPage}"/>
        <title>Register</title>

    </head>

    <body>
        <div class="container">
            <div class="title">Registration</div>
            <div class="content">
                <form action="register" method="post" autocomplete="off">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Full Name</span>
                            <input type="text" name="fullname" placeholder="Full name" class="name" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Address</span>
                            <input type="text" name="address" placeholder="Address" class="adress" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Username</span>
                            <input type="text" name="username" placeholder="Username" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Email</span>
                            <input class="email" type="text" name="email" placeholder="Email" required autocomplete="off">
                        </div>
                        <div class="input-box">
                            <span class="details">Password</span>
                            <input class="password" type="password" name="password" placeholder="New password" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Confirm Password</span>
                            <input class="confirm-password" type="password" name="confirm-password" placeholder="Confirm password" required>
                        </div>
                    </div>
                    <div class="">
                        <label style="font-weight: 530" >Day Of Birth</label>
                        <br>
                        <select name="day" >
                            <c:forEach items="${listDay}" var="i">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                        </select>
                        <select name="month" >
                            <c:forEach items="${listMonth}" var="i">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                        </select>

                        <select name="year" >
                            <c:forEach items="${listYears}" var="i">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <p class="notice-p">${notice}</p>
                    <div class="gender-details">
                        <input type="radio" name="gender" value="1" id="dot-1" checked>
                        <input type="radio" name="gender" value="0" id="dot-2">
                        <input type="radio" name="gender" value="2" id="dot-3">
                        <span class="gender-title">Gender</span>
                        <div class="category">
                            <label for="dot-1">
                                <span class="dot one"></span>
                                <span class="gender">Male</span>
                            </label>
                            <label for="dot-2">
                                <span class="dot two"></span>
                                <span class="gender">Female</span>
                            </label>
                            <label for="dot-3">
                                <span class="dot three"></span>
                                <span class="gender">Prefer not to say</span>
                            </label>
                        </div>
                    </div>
                    <div class="gender-details">
                        <input type="radio" name="role" value="3" id="buyer" checked>
                        <input type="radio" name="role" value="2" id="seller">
                        <span class="gender-title">Role</span>
                        <div class="category">
                            <label for="buyer">
                                <span class="dot one"></span>
                                <span class="gender">Customer</span>
                            </label>
                            <label for="seller">
                                <span class="dot two"></span>
                                <span class="gender">Seller</span>
                            </label>
                        </div>
                    </div>
                    <small style="font-style: italic; font-size: 10px">People who use our service may have uploaded your contact information to Tikialazapee. <br>
                        By clicking Register, you agree to our Terms, Privacy Policy and Cookies Policy. You may receive Email notifications from us and can opt out at any time</small>
                    <div class="button">
                        <input type="button" value="Register" id="register-button">
                        <a href="login" style="color: #495057;
                           text-decoration: underline;">
                            Already have a account?
                        </a>
                        <input type="hidden" name="listMonths" value="${requestScope.listMonth}" />
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script src="${libJquery}"></script>
    <script src="${register}"></script>
</html>
