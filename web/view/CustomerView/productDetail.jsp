<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.*"  %>
<%@page import="util.*" %>
<%@page import="Model.*" %>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">

        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">

        <meta charset="utf-8" />
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"
            />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="CSS/style.css" />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"
            />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap"
            rel="stylesheet"
            />

        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://www.w3schools.com/w3css/4/w3.css" rel="stylesheet"/>
        <link
            href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet"
            />

        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <title>Product Details Page</title>
        <link rel="stylesheet" href="style.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
            />
        <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.4.2/uicons-solid-rounded/css/uicons-solid-rounded.css'>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.4.2/uicons-regular-straight/css/uicons-regular-straight.css'>
        <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.4.2/uicons-regular-rounded/css/uicons-regular-rounded.css'>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".wish-icon i").click(function () {
                    $(this).toggleClass("fa-heart fa-heart-o");
                });
            });
        </script>
        <style type="text/css">
            @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap");

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: "Poppins", sans-serif;
            }
            nav {
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 20px 8%;
            }
            nav .logo {
                font-size: 32px;
                font-weight: 500;
                cursor: pointer;
            }
            nav ul li {
                list-style: none;
                display: inline-block;
            }
            nav ul li a {
                display: block;
                margin: 0 10px;
                color: #000;
                font-weight: 500;
                text-decoration: none;
                font-size: 17px;
                position: relative;
            }
            nav ul li a::before {
                content: "";
                position: absolute;
                bottom: 0;
                left: 0;
                background-color: #000;
                width: 0;
                height: 2.5px;
                transition: all 0.3s ease;
            }
            nav ul li a:hover::before {
                width: 100%;
            }
            nav ul i {
                font-size: 20px;
                margin-left: 10px;
                cursor: pointer;
            }
            .flex-box {
                display: flex;
                width: 1000px;
                margin: 20px auto;
            }
            .left {
                width: 40%;
            }
            .big-img {
                width: 250px;
            }
            .big-img img {
                width: inherit;
            }
            .images {
                display: flex;
                justify-content: space-between;
                width: 60%;
                margin-top: 15px;
            }
            .small-img {
                width: 50px;
                overflow: hidden;
                border: 1.5px solid black;
            }
            .small-img img {
                width: inherit;
                cursor: pointer;
                transition: all 0.3s ease;
            }
            .small-img:hover img {
                transform: scale(1.2);
            }
            .url {
                font-size: 16px;
                font-weight: 400;
                color: rgb(0, 102, 255);
            }
            .pname {
                font-size: 22px;
                font-weight: 600;
                margin-top: 50px;
            }
            .ratings i {
                color: rgb(255, 136, 0);
            }
            .price {
                font-size: 20px;
                font-weight: 500;
                margin: 20px 0;
            }
            .size {
                display: flex;
                align-items: center;
                margin: 20px 0;
            }
            .size p {
                font-size: 18px;
                font-weight: 500;
            }
            .psize {
                width: 60px;
                height: 30px;
                border: 1px solid #ff5e00;
                color: #000;
                text-align: center;
                margin: 0 10px;
                cursor: pointer;
            }
            .psize.active {
                border-width: 1.5px;
                color: #ff5e00;
                font-weight: 500;
            }
            .quantity {
                display: flex;
            }
            .quantity p {
                font-size: 18px;
                font-weight: 500;
            }
            .quantity input {
                width: 40px;
                font-size: 17px;
                font-weight: 500;
                text-align: center;
                margin-left: 15px;
            }
            .btn-box {
                display: flex;
                margin-top: 40px;
            }
            .btn-box button {
                font-size: 18px;
                padding: 8px 25px;
                border: none;
                outline: none;
                border-radius: 6px;
                cursor: pointer;
                color: white;
            }
            .cart-btn {
                background-color: #ff5e00;
                margin-right: 20px;
            }
            .buy-btn {
                background-color: #00aeff;
                margin-right: 20px;
            }
            .wishlist-btn {
                background-color: #cccccc;
            }
            .cart-btn:hover {
                background-color: #ff3c00;
            }
            .buy-btn:hover {
                background-color: #0066ff;
            }
            .wishlist-btn:hover {
                background-color: #cccccc; /* Màu nền khi hover */

            }


            h2 {
                color: #000;
                font-size: 26px;
                font-weight: 300;
                text-align: center;
                text-transform: uppercase;
                position: relative;
                margin: 30px 0 60px;
            }
            h2::after {
                content: "";
                width: 100px;
                position: absolute;
                margin: 0 auto;
                height: 4px;
                border-radius: 1px;
                background: #7ac400;
                left: 0;
                right: 0;
                bottom: -20px;
            }
            .carousel {
                margin: 50px auto;
                padding: 0 70px;
            }
            .carousel .item {
                color: #747d89;
                min-height: 325px;
                text-align: center;
                overflow: hidden;
            }
            .carousel .thumb-wrapper {
                padding: 25px 15px;
                background: #fff;
                border-radius: 6px;
                text-align: center;
                position: relative;
                box-shadow: 0 2px 3px rgba(0,0,0,0.2);
            }
            .carousel .item .img-box {
                height: 120px;
                margin-bottom: 20px;
                width: 100%;
                position: relative;
            }
            .carousel .item img {
                max-width: 100%;
                max-height: 100%;
                display: inline-block;
                position: absolute;
                bottom: 0;
                margin: 0 auto;
                left: 0;
                right: 0;
            }
            .carousel .item h4 {
                font-size: 18px;
            }
            .carousel .item h4, .carousel .item p, .carousel .item ul {
                margin-bottom: 5px;
            }
            .carousel .thumb-content .btn {
                color: #7ac400;
                font-size: 11px;
                text-transform: uppercase;
                font-weight: bold;
                background: none;
                border: 1px solid #7ac400;
                padding: 6px 14px;
                margin-top: 5px;
                line-height: 16px;
                border-radius: 20px;
            }
            .carousel .thumb-content .btn:hover, .carousel .thumb-content .btn:focus {
                color: #fff;
                background: #7ac400;
                box-shadow: none;
            }
            .carousel .thumb-content .btn i {
                font-size: 14px;
                font-weight: bold;
                margin-left: 5px;
            }
            .carousel .item-price {
                font-size: 13px;
                padding: 2px 0;
            }
            .carousel .item-price strike {
                opacity: 0.7;
                margin-right: 5px;
            }
            .carousel-control-prev, .carousel-control-next {
                height: 44px;
                width: 40px;
                background: #7ac400;
                margin: auto 0;
                border-radius: 4px;
                opacity: 0.8;
            }
            .carousel-control-prev:hover, .carousel-control-next:hover {
                background: #78bf00;
                opacity: 1;
            }
            .carousel-control-prev i, .carousel-control-next i {
                font-size: 36px;
                position: absolute;
                top: 50%;
                display: inline-block;
                margin: -19px 0 0 0;
                z-index: 5;
                left: 0;
                right: 0;
                color: #fff;
                text-shadow: none;
                font-weight: bold;
            }
            .carousel-control-prev i {
                margin-left: -2px;
            }
            .carousel-control-next i {
                margin-right: -4px;
            }
            .carousel-indicators {
                bottom: -50px;
            }
            .carousel-indicators li, .carousel-indicators li.active {
                width: 10px;
                height: 10px;
                margin: 4px;
                border-radius: 50%;
                border: none;
            }
            .carousel-indicators li {
                background: rgba(0, 0, 0, 0.2);
            }
            .carousel-indicators li.active {
                background: rgba(0, 0, 0, 0.6);
            }
            .carousel .wish-icon {
                position: absolute;
                right: 10px;
                top: 10px;
                z-index: 99;
                cursor: pointer;
                font-size: 16px;
                color: #abb0b8;
            }
            .carousel .wish-icon .fa-heart {
                color: #ff6161;
            }
            .star-rating li {
                padding: 0;
            }
            .star-rating i {
                font-size: 14px;
                color: #ffc000;
            }

        </style>
        <%@include file="../../Template/libheader.jsp" %>
        <c:url value="/JS/jquery-3.7.1.min.js" var="libJquery"/>
    </head>
    <body>
        <%@include file="../../Template/navbar.jsp" %>
        <div class="flex-box">
            <div class="left">
                <div class="big-img">
                    <img src="${product.listImage.get(0).imageProduct_url}" alt="?" onclick="openFullscreen(this)" />
                </div>
                <div class="images">
                    <div class="small-img">
                        <img
                            src="${product.listImage.get(0).imageProduct_url}"
                            onclick="showImg(this.src)"
                            />
                    </div>
                    <div class="small-img">
                        <img
                            src="${product.listImage.get(1).imageProduct_url}"
                            onclick="showImg(this.src)"
                            />
                    </div>
                    <div class="small-img">
                        <img
                            src="${product.listImage.get(2).imageProduct_url}"
                            onclick="showImg(this.src)"
                            />
                    </div>
                    <div class="small-img">
                        <img
                            src="${product.listImage.get(3).imageProduct_url}"
                            onclick="showImg(this.src)"
                            />
                    </div>
                </div>
            </div>

            <div class="right">

                <div class="pname">${product.product_name}</div>

                <div class="star-rating">
                    <ul class="list-inline">

                        <c:forEach begin="1" end="${db.avgStarRate(i.product_id)}">
                            <li class="list-inline-item"><i class="fa fa-star"></i></li>  
                            </c:forEach>
                    </ul>
                </div>

                <i class="d-flex justify-content-between">
                    <h4>
                        <p class="small text-dark">
                            <s>${Feature.format(product.product_originPrice)}VND</s>
                        </p>
                    </h4>
                </i>

                <i class="d-flex justify-content-between mb-3">
                    <h4 class="text-danger mb-0">
                        ${Feature.format(Feature.calculateSalePrice(product.product_originPrice,product.product_percenSale))}VND
                    </h4>
                </i>
                <div class="size">
                    <p>Type :</p>
                    <c:forEach items="${product.listType}" var="type">
                        <a class="btn btn-outline-dark fs-4 ms-3 <c:if test="${pType != null && type.type_id == pType.type_id}">active</c:if>" href="product?product_id=${product.product_id}&type=${type.type_id}<c:if test="${param.color != null}">&color=${param.color}</c:if>">${type.type_describes}</a>
                    </c:forEach>
                </div>
                <div class="size">
                    <p>Color :</p>
                    <c:if test="${pType != null}">
                        <c:forEach items="${pType.listColor}" var="color">
                            <a class="btn btn-outline-dark fs-4 ms-3 <c:if test="${color.color_id == param.color}">active</c:if>" href="product?product_id=${product.product_id}&type=${requestScope.pType.type_id}&color=${color.color_id}">${color.color_name}</a>
                        </c:forEach>    
                    </c:if>
                    <c:if test="${pType == null}">
                        <c:forEach items="${product.listColor}" var="color">
                            <a class="btn btn-outline-dark fs-4 ms-3 <c:if test="${color.color_id == param.color}">active</c:if>" href="product?product_id=${product.product_id}&color=${color.color_id}">${color.color_name}</a>
                        </c:forEach>   
                    </c:if>

                </div>
                <script>
                    // Lấy tất cả các phần tử có class 'psize'
                    const sizes = document.querySelectorAll('.psize');

                    sizes.forEach(size => {
                        // Thêm sự kiện click cho từng phần tử
                        size.addEventListener('click', function () {
                            // Xóa class 'active' từ tất cả các phần tử
                            sizes.forEach(s => s.classList.remove('active'));
                            // Thêm class 'active' vào phần tử được click
                            this.classList.add('active');
                        });
                    });
                    function getUrlParameter(name) {
                        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
                        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
                        var results = regex.exec(location.search);
                        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
                    }


                    var type_id = getUrlParameter('type');
                    var color_id = getUrlParameter('color');

                    console.log("Type ID:", type_id);
                    console.log("Color ID:", color_id);
                    window.onload = function () {
                        var quantityInput = document.getElementById("quantity");
                        var addToCartButton = document.querySelector(".cart-btn");
                        var buyButton = document.querySelector(".buy-btn");
                        var availableStock = parseInt(quantityInput.max);

                        function showMessage(message) {
                            var messageElement = document.getElementById("message");
                            messageElement.textContent = message;
                            messageElement.style.color = "red";
                            messageElement.style.marginTop = "10px"; // thêm khoảng cách trên để tách biệt thông báo
                        }
                        // Disable buttons if type_id or color_id is missing
                        if (!type_id || !color_id) {
                            addToCartButton.disabled = true;
                            buyButton.disabled = true;
                            showMessage("Please select a type and color.");
                        } else if (availableStock <= 0) {
                            addToCartButton.disabled = true;
                            buyButton.disabled = true;
                            showMessage("Sold Out");
                        }
                    }
                </script>
                <div class="quantity">
                    <p>Quantity : </p>  
                    <span class="btn btn1" onclick="productAction(-1)"><i class="fa fa-minus"></i></span>
                    <input
                        id="quantity"
                        type="number"
                        min="1"
                        max="${requestScope.db.getStockByProductCharacterics(requestScope.product.product_id, requestScope.pType.type_id, requestScope.pColor.color_id)}"
                        value="1"
                        name="numberOfProduct"
                        class="input-quantity"
                        readonly
                        />
                    <span class="btn btn1" onclick="productAction(1)"><i class="fa fa-plus"></i></span>
                    <p>Available : <c:if test="${param.type != null and param.color != null}">
                            ${requestScope.db.getStockByProductCharacterics(requestScope.product.product_id, requestScope.pType.type_id, requestScope.pColor.color_id)}  
                        </c:if>
                        <c:if test="${param.type == null or param.color == null}">
                            ${requestScope.db.quantityOfProductById(product.product_id)}
                        </c:if></p>
                </div>
                <p id="message" style="margin-top: 10px;"></p> 
                <div class="btn-box">
                    <input type="hidden" name="idProduct" value="${product.product_id}" />
                    <input
                        type="hidden"
                        name="priceOfProduct"
                        value=" ${Feature.format(Feature.calculateSalePrice(product.product_originPrice,product.product_percenSale))}"
                        />
                    <button class="cart-btn" id="cart-btn-id">
                        Add to Cart
                    </button>

                    <button id="btn-buy" class="buy-btn">Buy Now</button>
                    <a  href="AddWishList?pid=${i.product_id}">
                        <button onclick="userActionWishList(this)" class="wishlist-btn">Wish List</button>
                    </a>            



                </div>
            </div>
        </div>


        <div class="flex-box">
            <div class="row">
                <div class="col-md-12 mt-3">
                    <div class="card rounded-4">
                        <div class="card-header rounded-top-4 bg-white">
                            <h4>Description</h4>
                        </div>
                        <div class="card-body rounded-bottom-4">
                            <p>${product.product_Describes}</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="flex-box">
            <div class="container p-0">
                <div class="card shadow-sm rounded-4 ps-3 pe-3 pt-1">
                    <a href="viewstore?store_id=${infoStore.user_id}">
                        <a href="viewstore?store_id=${infoStore.user_id}">
                            <div class="card-body">
                                <div class="row g-2">
                                    <div class="col-2">
                                        <div class="ratio ratio-1x1 w-50">
                                            <img class="w-100 border rounded-4" src="${infoStore.store_image}" alt="?"/>
                                        </div>
                                    </div>

                                    <div class="col-10 ps-4 pe-4">
                                        <div class="row g-3">
                                            <div class="col-4">
                                                <h5 class="fw-bold text-primary">${infoStore.store_name}</h5>
                                                <h5>${infoStore.store_phone}</h5>
                                            </div>
                                            <div class="col-4">
                                                <h5><i class="fi fi-rs-marker"></i>${infoStore.store_address}</h5>
                                                <h5>Rate: ${store.avgStarRate(i.user_id)}</h5>
                                            </div>
                                            <div class="col-4">
                                                <h5><i class="fi fi-sr-flame"></i>1000  Followers</h5>
                                                <h5>Chat</h5>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </a>
                    </a>
                </div>  
                </a>
            </div>

        </div>

        <div class="flex-box">
            <div class="card rounded-4 w-100">
                <div class="card-header rounded-top-4">
                    <h4>Product Review</h4>
                </div>
                <div class="card-body rounded-bottom-4">
                    <div class="row p-4 gy-4">
                        <c:choose>
                            <c:when test="${feedback.isEmpty()}">
                                <p class="text-center">No results.</p>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${feedback}" var="i">
                                    <div class="col-12">
                                        <div class="row">
                                            <div class="col-2">
                                                <div class="ratio-1x1 w-100">
                                                    <img style="border-radius: 50% !important; width: 50% !important;" class="w-100 border" src="image\image_avatar_user\avataruser(1).jpg" alt=""/>
                                                </div>
                                            </div>
                                            <div class="col-4">
                                                <h5>Phạm Hoàng Anh <span class="text-warning"><c:forEach begin="1" end="${i.feedback_rateStars}"><i class="fa fa-star pe-2"></i></c:forEach></span> </h5> 
                                                <p>${i.feedback_comment}</p>
                                            </div>
                                            <div>
                                                <img style=""/>
                                            </div>
                                        </div>
                                        <hr>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-xl">
            <div class="row">
                <div class="col-md-12">
                    <h2>Products <b>Related</b></h2>
                    <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="0">


                        <!-- Wrapper for carousel items -->
                        <div class="carousel-inner">
                            <div class="item carousel-item active">
                                <div class="row">

                                    <c:forEach items="${requestScope.productF}" var="i">
                                        <div class="col-sm-3">

                                            <div class="thumb-wrapper">
                                                <span class="wish-icon"><i class="fa fa-heart-o"></i></span>
                                                <div class="img-box">
                                                    <img src="${i.listImage.get(0).imageProduct_url}" class="img-fluid" alt="">									
                                                </div>
                                                <a href="product?product_id=${i.product_id}">
                                                    <div class="thumb-content">
                                                        <h4>${i.product_name}</h4>									
                                                        <div class="star-rating">
                                                            <ul class="list-inline">
                                                                <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                                <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                                <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                                <li class="list-inline-item"><i class="fa fa-star"></i></li>

                                                            </ul>
                                                        </div>
                                                        <p class="item-price"><strike>${Feature.format(i.product_originPrice)}VND</strike> <b>${Feature.format(Feature.calculateSalePrice(i.product_originPrice,product.product_percenSale))}</b></p>
                                                        <a href="#" class="btn btn-primary">Add to WishList</a>
                                                    </div>	
                                                </a>
                                            </div>

                                        </div>
                                    </c:forEach>

                                </div>
                            </div>

                        </div>


                    </div>
                </div>
            </div>
        </div>

        <div id="modal" class="w3-modal w3-black" style="padding-top:0" onclick="this.style.display = 'none'">
            <span class="w3-button w3-black w3-xlarge w3-display-topright">&times;</span>
            <div class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-64">
                <img id="img" class="w3-image w-50">
                <p id="caption"></p>
            </div>
        </div>
        <script src="${libJquery}"></script>                                             
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script>
            let bigImg = document.querySelector(".big-img img");
            function showImg(pic) {
                bigImg.src = pic;
            }
        </script>

        <script>
            function productAction(val) {
                var x = Number(document.getElementById("quantity").value);
                var sum = x + val;
                var max = Number(
                        document.getElementById("quantity").getAttribute("max")
                        );
                var min = Number(
                        document.getElementById("quantity").getAttribute("min")
                        );
                if (sum <= max && sum >= min) {
                    document.getElementById("quantity").value = x + val;
                }
            }
            function userActionBuy(btn) {
                document.order.action = "product";
                document.order.submit();
            }
            function userActionWishList(btn) {
                document.order.action = "wishlist";
                document.order.submit();
            }

            // Modal Image Gallery
            function openFullscreen(element) {
                document.getElementById("img").src = element.src;
                document.getElementById("modal").style.display = "block";
                const caption = document.getElementById('caption');
                captionText.innerHTML = element.alt;
                console.log('HI');
            }
        </script>
        <script>
            // Lấy tất cả các phần tử có class 'psize'
            const sizes = document.querySelectorAll('.psize');

            sizes.forEach(size => {
                // Thêm sự kiện click cho từng phần tử
                size.addEventListener('click', function () {
                    // Xóa class 'active' từ tất cả các phần tử
                    sizes.forEach(s => s.classList.remove('active'));
                    // Thêm class 'active' vào phần tử được click
                    this.classList.add('active');
                });
            });
            function getUrlParameter(name) {
                name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
                var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
                var results = regex.exec(location.search);
                return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
            }


            var type_id = getUrlParameter('type');
            var color_id = getUrlParameter('color');

            console.log("Type ID:", type_id);
            console.log("Color ID:", color_id);
            window.onload = function () {
                var quantityInput = document.getElementById("quantity");
                var addToCartButton = document.querySelector(".cart-btn");
                var buyButton = document.querySelector(".buy-btn");
                var availableStock = parseInt(quantityInput.max);

                if (type_id === null || !color_id === null) {
                    addToCartButton.disabled = true;
                    buyButton.disabled = true;


                    var missingSelectionMessage = document.createElement("p");
                    missingSelectionMessage.textContent = "Please select a type and color.";
                    missingSelectionMessage.style.color = "red";
                    var quantityDiv = document.querySelector(".quantity");
                    quantityDiv.appendChild(missingSelectionMessage);
                } else if (availableStock === 0) {
                    addToCartButton.disabled = true;
                    buyButton.disabled = true;
                    var soldOutMessage = document.createElement("p");
                    soldOutMessage.textContent = "Sold Out";
                    soldOutMessage.style.color = "red";
                    var quantityDiv = document.querySelector(".quantity");
                    quantityDiv.appendChild(soldOutMessage);
                }
            }
        </script>
        <script>
            function getUrlParameter(name) {
                name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
                var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
                var results = regex.exec(location.search);
                return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
            }
            const btn_cart = document.getElementById('cart-btn-id');
            btn_cart.addEventListener('click', () => {
                const type_id = Number(getUrlParameter('type'));
                const product_id = Number(getUrlParameter("product_id"));
                const color_id = Number(getUrlParameter("color"));
                const quantity = Number(document.getElementById('quantity').value);
                $.ajax({
                    url: "/tikilazapee/cart",
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        'product_id': product_id,
                        'type_id': type_id,
                        'color_id': color_id,
                        'quantity': quantity
                    }),
                    success: function () {
                        location.href = "cart";
                    }
                });
            });
            const btn_buy = document.getElementById('btn-buy');
            btn_buy.addEventListener('click', () => {
                const type_id = Number(getUrlParameter('type'));
                const product_id = Number(getUrlParameter("product_id"));
                const color_id = Number(getUrlParameter("color"));
                const quantity = Number(document.getElementById('quantity').value);
                $.ajax({
                    url: "/tikilazapee/product",
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        'product_id': product_id,
                        'type_id': type_id,
                        'color_id': color_id,
                        'quantity': quantity
                    }),
                    success: function (response) {
                        const ID = response;
                        location.href = "payment?item_id=" + ID;
                    }
                });
            });
        </script>
    </body>
</html>
