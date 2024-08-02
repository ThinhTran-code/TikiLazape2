<%-- 
    Document   : newjsp
    Created on : May 20, 2024, 9:45:20 PM
    Author     : hbtth
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../../Template/libheader.jsp" %>
        <c:url var="login" value="/CSS/loginCSS.css"/>
        <link rel="stylesheet" href="${login}">
        <title>login page</title>
    </head>
    <body>
        <div class="container">
            <section class="vh-100">
                <div class="container py-5 h-100">
                    <h1 class="text-center title-Login">Login to Tikilazapee</h1>
                    <div class="row d-flex align-items-center justify-content-center h-100">
                        <div class="col-md-8 col-lg-7 col-xl-6">
                            <img src="image/image_logo/logo.png"
                                 class="img-fluid" alt="Phone image">
                        </div>
                        <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
                            <form method="POST" action="login" name="loginform">
                                <!-- Email input -->
                                <div data-mdb-input-init class="form-outline mb-4">
                                    <input type="text" id="form1Example13" class="form-control form-control-lg" 
                                           placeholder="Username" name="username"
                                           value="${username != null ? username : ''}"
                                           />                                    
                                </div>

                                <!-- Password input -->
                                <div data-mdb-input-init class="form-outline mb-4">
                                    <input type="password" id="form1Example23" class="form-control form-control-lg"
                                           placeholder="Password" name="password"
                                           value="${password != null ? password : ''}"
                                           />                                    
                                </div>

                                <div class="d-flex justify-content-around align-items-center mb-4">
                                    <!-- Checkbox -->
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="rem" 
                                               id="form1Example3" name="rem"
                                               ${rem != null ? 'checked' : ''}
                                               />
                                        <label class="form-check-label" for="form1Example3"> Remember me </label>
                                    </div>
                                    <a href="forgotpassword">Forgot password?</a>
                                </div>
                                               <h6 style="color: red">${noitce}</h6>
                                <!-- Submit button -->
                                <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block btn-sign-in">Sign in</button>

                                <!-- Register link -->
                                <div class="d-flex align-items-center mb-4">
                                    Don't have an account &nbsp; &nbsp;<a href="register">Register now?</a>
                                </div>

                                <div class="divider d-flex align-items-center my-4">
                                    <p class="text-center fw-bold mx-3 mb-0 text-muted">OR</p>
                                </div>
                                <!--login by facebook or google -->
                                <a data-mdb-ripple-init class="btn btn-primary btn-lg btn-block" style="background-color: #3b5998" href="${Oauth_Config.FACEBOOK_LINK_GET_CODE}"
                                   role="button">
                                    <i class="fab fa-facebook-f me-2"></i>Continue with Facebook
                                </a>
                                <a data-mdb-ripple-init class="btn btn-primary btn-lg btn-block btnGoogle" href="${Oauth_Config.GOOGLE_LINK_GET_CODE}"
                                   role="button">
                                    <i class="fa-brands fa-google-plus-g">&nbsp;</i>Continue with Google</a>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>
