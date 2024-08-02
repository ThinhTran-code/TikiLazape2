<%-- 
    Document   : authenticationPage
    Created on : May 26, 2024, 1:06:28 PM
    Author     : hbtth
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../../Template/libheader.jsp" %>
        <c:url value="/CSS/authenticationCSS.css" var="authentication"/>
        <c:url value="/JS/AuthenticationJS.js" var="js"/>
        <c:url value="/JS/jquery-3.7.1.min.js" var="jquery"/>
        <c:url value="/JS/AuthenticationOTP.js" var="OTP1"/>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${authentication}">

    </head>
    <body>
        <div class="container">
            <header>
                <i class="bx bxs-check-shield"></i>
            </header>
            <h4>Enter OTP Code to authentication account</h4>
            <form action="authentication" method="post">
                <div class="input-field">
                    <input class="code" type="text" maxlength="1" />
                    <input class="code" type="text" maxlength="1" disabled />
                    <input class="code" type="text" maxlength="1" disabled />
                    <input class="code" type="text" maxlength="1" disabled />
                    <input class="code" type="text" maxlength="1" disabled />
                    <input class="code" type="text" maxlength="1" disabled />                    
                </div>
                <p class="text-danger"></p>
                <div>
                    <input value="Cancel" type="button" class="cancel-btn"/>
                    <input type="button" value="Verify OTP" class="submit-btn" onclick="authenticate(${OTP})"/>
                </div>
                <br>
                <a class="btn-getcode" href="sendcaptcha">Don't get a code? click here to sent code again</a>
            </form>
        </div>
    </body>
    <script src="${jquery}"></script>
    <script src="${js}"></script>
    <script>
        const btn_cancel = document.querySelector('.cancel-btn'),
                btn_submit = document.querySelector('.submit-btn'),
                btn_getCode = document.querySelector('.btn-getcode');
        btn_cancel.addEventListener('click', () => {
            $.ajax({
                url: "/tikilazapee/sendcaptcha",
                type: 'DELETE',
                success: function (data) {
                    location.href = "login";
                }
            });
        });
        setTimeout(function () {
            btn_getCode.addEventListener('click', () => {
                $.ajax({
                    url: "/tikilazapee/sendcaptcha",
                    type: 'POST',
                    success: function () {

                    }
                });
            });
        }, 6000 * 10);
        let count = 0;
        let OTPcode="";
        function authenticate(OTP){
            const inputs = document.querySelectorAll('.code');
            let tempOTP ="";
            inputs.forEach((input,e)=>{
                tempOTP += input.value;
            });
            OTPcode = tempOTP;
            if(OTP != OTPcode){
                count++;
                inputs.forEach((input1,e)=>{
                input1.classList.add('danger');
            });
            
            
            document.querySelector('p').innerHTML = "<br> OTP is not correct!!!";
                
                if(count >=5){
                    $.ajax({
                url: "/tikilazapee/sendcaptcha",
                type: 'DELETE',
                success: function (data) {
                    location.href = "login";
                }
            });
                }
            }else{
                 $.ajax({
                url: "/tikilazapee/authentication",
                type: 'POST',
                success: function (data) {
                    location.href = "index";
                }
            });
            }
            console.log(OTPcode);
        }
    </script>
    <script src="${OTP1}"></script>
</html>
