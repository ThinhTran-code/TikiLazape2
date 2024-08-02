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
        <c:url value="/CSS/register-storeCSS.css" var="registerStore"/>
        <link rel="stylesheet" href="${registerPage}"/>
        <link rel="stylesheet" href="${registerStore}"/>
        <title>Register</title>

    </head>

    <body>
        <div class="container">
            <div class="title">Registration</div>
            <div class="content">
                <form action="register-store" method="post" autocomplete="off" enctype="multipart/form-data">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Store Name</span>
                            <input type="text" name="store_name" placeholder="Store name" class="name" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Store phone</span>
                            <input type="text" name="store_phone" placeholder="Store phone" class="adress" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Store address</span>
                            <input type="text" name="store_address" placeholder="Store address" required>
                        </div>
                        <div class="image-box">
                            <span class="details">Store image</span>
                            <img src="image/image_store/vector-shop-icon.jpg" alt="avatar of store"/>
                            <label for="image">Choose image</label>
                            <input type="file" accept="image/*" name="store_image" id="image"/>
                        </div>

                        <p class="notice-p">${notice}</p>
                        <div class="button input-box">
                            <input type="submit" value="Register" id="register-button">
                        </div>
                </form>
            </div>
        </div>
    </body>
    <script src="${libJquery}"></script>
    <script>
        const image = document.querySelector('.image-box img');
        const input = document.querySelector('.image-box input');
        input.addEventListener('change', () => {
            const fileReader = new FileReader();
            fileReader.readAsDataURL(input.files[0]);
            fileReader.onload = (fileReaderEvent) => {
                image.src = fileReaderEvent.target.result;
            };
        });



    </script>
</html>
