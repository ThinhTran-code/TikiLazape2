<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.*"  %>
<%@page import="util.*" %>

<html>
    <head>
        <title>Tikilazapee</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <title>Store</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet"
            />

        <c:url value="/CSS/listproductCSS.css" var="listproduct"/>


        <link rel="stylesheet" href="${listproduct}"/>
        <%@include file="../../Template/libheader.jsp" %>
        <!-- script
        ================================================== -->
        <script src="JS/modernizr.js"></script>
        <style>


            .categories-container {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                padding-bottom: 40px;

            }
            .category-item {
                border: 1px solid #ccc;
                border-radius: 8px;
                overflow: hidden;
                width: 100px;
                text-align: center;
            }
            .category-item img {
                width: 100%;
                height: auto;
            }
            .category-item h3 {
                margin: 0;
                padding: 10px;
                background-color: #f8f8f8;
            }
            .store-content {
                margin: 20px;
                overflow: hidden;
            }
            .store-content .store-info-right{
                display: flex;
                margin-bottom: 20px;
            }
            .store-details .store-info-left{
                display: flex;
                background-color: ghostwhite;
                padding: 20px;
                margin-bottom: 20px;
                border-radius: 5px;
                box-shadow: 0 5px 7px rgba(0, 0, 0, 0.4);
                margin-left: 200px;
                margin-right: 60px;
            }
            .store-info {
                display: flex;
                align-items: center;
                gap: 20px;
            }
            .store-info button{

                border-top-width: 1px;
                padding-right: 40px;
                padding-left: 40px;
                padding-bottom: 3px;
                padding-top: 3px;
                background-color: transparent;

            }
            .store-info img {
                border-radius: 50%;
                width: 100px;
                height: 100px;
            }
            .store-info div {
                flex: 1;
            }
            .store-info p{
                display:  block;
                color: black;
            }
            .store-info-left-left{
                display: flex;
                align-items: center;
                h5{
                    margin-bottom: 40px;
                }
            }
            .follow-btn{
                background-color: transparent;
                color: black;
                border-top-width: 1px;
                padding-right: 40px;
                padding-left: 40px;
                padding-bottom: 3px;
                padding-top: 3px;
                border-radius: 5px;
                cursor: pointer;
            }

            .material-symbols-outlined {
                font-variation-settings: 'FILL' 0,'wght' 200,'GRAD' 0,'opsz' 24;
                font-size: 14.7px
            }

        </style>
    </head>
    <body>
        <%@include file="../../Template/navbar.jsp"%>
        <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <svg xmlns="http://www.w3.org/2000/svg" id="chevron-left" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
        </svg>
    <symbol xmlns="http://www.w3.org/2000/svg" id="chevron-right" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="cart-outline" viewBox="0 0 16 16">
        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="quality" viewBox="0 0 16 16">
        <path d="M9.669.864 8 0 6.331.864l-1.858.282-.842 1.68-1.337 1.32L2.6 6l-.306 1.854 1.337 1.32.842 1.68 1.858.282L8 12l1.669-.864 1.858-.282.842-1.68 1.337-1.32L13.4 6l.306-1.854-1.337-1.32-.842-1.68L9.669.864zm1.196 1.193.684 1.365 1.086 1.072L12.387 6l.248 1.506-1.086 1.072-.684 1.365-1.51.229L8 10.874l-1.355-.702-1.51-.229-.684-1.365-1.086-1.072L3.614 6l-.25-1.506 1.087-1.072.684-1.365 1.51-.229L8 1.126l1.356.702 1.509.229z" />
        <path d="M4 11.794V16l4-1 4 1v-4.206l-2.018.306L8 13.126 6.018 12.1 4 11.794z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="price-tag" viewBox="0 0 16 16">
        <path d="M6 4.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm-1 0a.5.5 0 1 0-1 0 .5.5 0 0 0 1 0z" />
        <path d="M2 1h4.586a1 1 0 0 1 .707.293l7 7a1 1 0 0 1 0 1.414l-4.586 4.586a1 1 0 0 1-1.414 0l-7-7A1 1 0 0 1 1 6.586V2a1 1 0 0 1 1-1zm0 5.586 7 7L13.586 9l-7-7H2v4.586z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="shield-plus" viewBox="0 0 16 16">
        <path d="M5.338 1.59a61.44 61.44 0 0 0-2.837.856.481.481 0 0 0-.328.39c-.554 4.157.726 7.19 2.253 9.188a10.725 10.725 0 0 0 2.287 2.233c.346.244.652.42.893.533.12.057.218.095.293.118a.55.55 0 0 0 .101.025.615.615 0 0 0 .1-.025c.076-.023.174-.061.294-.118.24-.113.547-.29.893-.533a10.726 10.726 0 0 0 2.287-2.233c1.527-1.997 2.807-5.031 2.253-9.188a.48.48 0 0 0-.328-.39c-.651-.213-1.75-.56-2.837-.855C9.552 1.29 8.531 1.067 8 1.067c-.53 0-1.552.223-2.662.524zM5.072.56C6.157.265 7.31 0 8 0s1.843.265 2.928.56c1.11.3 2.229.655 2.887.87a1.54 1.54 0 0 1 1.044 1.262c.596 4.477-.787 7.795-2.465 9.99a11.775 11.775 0 0 1-2.517 2.453 7.159 7.159 0 0 1-1.048.625c-.28.132-.581.24-.829.24s-.548-.108-.829-.24a7.158 7.158 0 0 1-1.048-.625 11.777 11.777 0 0 1-2.517-2.453C1.928 10.487.545 7.169 1.141 2.692A1.54 1.54 0 0 1 2.185 1.43 62.456 62.456 0 0 1 5.072.56z" />
        <path d="M8 4.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V9a.5.5 0 0 1-1 0V7.5H6a.5.5 0 0 1 0-1h1.5V5a.5.5 0 0 1 .5-.5z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-fill" viewBox="0 0 16 16">
        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-empty" viewBox="0 0 16 16">
        <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-half" viewBox="0 0 16 16">
        <path d="M5.354 5.119 7.538.792A.516.516 0 0 1 8 .5c.183 0 .366.097.465.292l2.184 4.327 4.898.696A.537.537 0 0 1 16 6.32a.548.548 0 0 1-.17.445l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256a.52.52 0 0 1-.146.05c-.342.06-.668-.254-.6-.642l.83-4.73L.173 6.765a.55.55 0 0 1-.172-.403.58.58 0 0 1 .085-.302.513.513 0 0 1 .37-.245l4.898-.696zM8 12.027a.5.5 0 0 1 .232.056l3.686 1.894-.694-3.957a.565.565 0 0 1 .162-.505l2.907-2.77-4.052-.576a.525.525 0 0 1-.393-.288L8.001 2.223 8 2.226v9.8z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="quote" viewBox="0 0 24 24">
        <path fill="currentColor" d="m15 17l2-4h-4V6h7v7l-2 4h-3Zm-9 0l2-4H4V6h7v7l-2 4H6Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="facebook" viewBox="0 0 24 24">
        <path fill="currentColor" d="M9.198 21.5h4v-8.01h3.604l.396-3.98h-4V7.5a1 1 0 0 1 1-1h3v-4h-3a5 5 0 0 0-5 5v2.01h-2l-.396 3.98h2.396v8.01Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="youtube" viewBox="0 0 32 32">
        <path fill="currentColor" d="M29.41 9.26a3.5 3.5 0 0 0-2.47-2.47C24.76 6.2 16 6.2 16 6.2s-8.76 0-10.94.59a3.5 3.5 0 0 0-2.47 2.47A36.13 36.13 0 0 0 2 16a36.13 36.13 0 0 0 .59 6.74a3.5 3.5 0 0 0 2.47 2.47c2.18.59 10.94.59 10.94.59s8.76 0 10.94-.59a3.5 3.5 0 0 0 2.47-2.47A36.13 36.13 0 0 0 30 16a36.13 36.13 0 0 0-.59-6.74ZM13.2 20.2v-8.4l7.27 4.2Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="twitter" viewBox="0 0 256 256">
        <path fill="currentColor" d="m245.66 77.66l-29.9 29.9C209.72 177.58 150.67 232 80 232c-14.52 0-26.49-2.3-35.58-6.84c-7.33-3.67-10.33-7.6-11.08-8.72a8 8 0 0 1 3.85-11.93c.26-.1 24.24-9.31 39.47-26.84a110.93 110.93 0 0 1-21.88-24.2c-12.4-18.41-26.28-50.39-22-98.18a8 8 0 0 1 13.65-4.92c.35.35 33.28 33.1 73.54 43.72V88a47.87 47.87 0 0 1 14.36-34.3A46.87 46.87 0 0 1 168.1 40a48.66 48.66 0 0 1 41.47 24H240a8 8 0 0 1 5.66 13.66Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="instagram" viewBox="0 0 256 256">
        <path fill="currentColor" d="M128 80a48 48 0 1 0 48 48a48.05 48.05 0 0 0-48-48Zm0 80a32 32 0 1 1 32-32a32 32 0 0 1-32 32Zm48-136H80a56.06 56.06 0 0 0-56 56v96a56.06 56.06 0 0 0 56 56h96a56.06 56.06 0 0 0 56-56V80a56.06 56.06 0 0 0-56-56Zm40 152a40 40 0 0 1-40 40H80a40 40 0 0 1-40-40V80a40 40 0 0 1 40-40h96a40 40 0 0 1 40 40ZM192 76a12 12 0 1 1-12-12a12 12 0 0 1 12 12Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="linkedin" viewBox="0 0 24 24">
        <path fill="currentColor" d="M6.94 5a2 2 0 1 1-4-.002a2 2 0 0 1 4 .002zM7 8.48H3V21h4V8.48zm6.32 0H9.34V21h3.94v-6.57c0-3.66 4.77-4 4.77 0V21H22v-7.93c0-6.17-7.06-5.94-8.72-2.91l.04-1.68z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="nav-icon" viewBox="0 0 16 16">
        <path d="M14 10.5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0 0 1h3a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-7a.5.5 0 0 0 0 1h7a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0 0 1h11a.5.5 0 0 0 .5-.5z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="close" viewBox="0 0 16 16">
        <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z" />
    </symbol>

    </svg> 
    <div>
        <div class="store-content">
            <div class="store-details">
                <div class="store-info">
                    <div class="store-info-left"> 
                        <img src="${store.store_image}" alt="Store Photo">
                        <div class="store-info-left-left" >
                            <div>
                                <h5> ${store.store_name}</h5>
                                <button class="follow-btn">Yêu Thích</button>
                            </div>
                            <div>
                                <p><span class="material-symbols-outlined">call</span>Phone: ${store.store_phone}</p>
                                <p><span class="material-symbols-outlined">home</span>Address: ${store.store_address}</p>
                            </div>
                        </div>
                    </div>
                    <div class="store-info-right" >  
                        <div>
                            <p><span class="material-symbols-outlined">storefront</span>Product number: ${requestScope.totalOfProduct}</p>
                            <p><span class="material-symbols-outlined">group</span> Number of Followers: 1,000</p>
                        </div>
                        <div>
                            <p><span class="material-symbols-outlined"> star</span> Average rating: ${requestScope.avgRateStar}/5</p>
                            <p><span class="material-symbols-outlined">person_add</span>Join date: 01/01/2020</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <section id="company-services" class="padding-large">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 pb-3">
                    <div class="icon-box d-flex">
                        <div class="icon-box-icon pe-3 pb-3">
                            <svg class="cart-outline">
                            <use xlink:href="#cart-outline" />
                            </svg>
                        </div>
                        <div class="icon-box-content">
                            <h3 class="card-title text-uppercase text-dark">Free delivery</h3>
                            <p>Consectetur adipi elit lorem ipsum dolor sit amet.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 pb-3">
                    <div class="icon-box d-flex">
                        <div class="icon-box-icon pe-3 pb-3">
                            <svg class="quality">
                            <use xlink:href="#quality" />
                            </svg>
                        </div>
                        <div class="icon-box-content">
                            <h3 class="card-title text-uppercase text-dark">Quality guarantee</h3>
                            <p>Dolor sit amet orem ipsu mcons ectetur adipi elit.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 pb-3">
                    <div class="icon-box d-flex">
                        <div class="icon-box-icon pe-3 pb-3">
                            <svg class="price-tag">
                            <use xlink:href="#price-tag" />
                            </svg>
                        </div>
                        <div class="icon-box-content">
                            <h3 class="card-title text-uppercase text-dark">Daily offers</h3>
                            <p>Amet consectetur adipi elit loreme ipsum dolor sit.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 pb-3">
                    <div class="icon-box d-flex">
                        <div class="icon-box-icon pe-3 pb-3">
                            <svg class="shield-plus">
                            <use xlink:href="#shield-plus" />
                            </svg>
                        </div>
                        <div class="icon-box-content">
                            <h3 class="card-title text-uppercase text-dark">100% secure payment</h3>
                            <p>Rem Lopsum dolor sit amet, consectetur adipi elit.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section id="mobile-products" class="product-store position-relative padding-large no-padding-top">
        <div class="container">
            <div class="row">
                <div class="display-header d-flex justify-content-between pb-3">
                    <h2 class="display-7 text-dark text-uppercase">Suggestions for you</h2>
                    <div class="btn-right">
                        <a id="viewAllButton" class="btn btn-medium btn-normal text-uppercase" style="display: inline-flex;">All Product <span class="material-symbols-outlined">arrow_forward_ios</span></a>
                    </div>
                </div>
                <div class="swiper product-swiper">
                    <div class="swiper-wrapper">

                        <c:forEach items="${listProduct}" begin="0" end="7" var="i">
                            <div class="swiper-slide">
                                <div class="row" style="
                                     height: 100%;">
                                    <div class="card">
                                        <div class="d-flex justify-content-between p-3">
                                            <a href="AddWishList?pid=${i.product_id}" >
                                                <p class="lead mb-0">Add to WishList<span class="material-symbols-outlined">favorite</span></p>
                                            </a>

                                        </div>
                                        <a href="product?product_id=${i.product_id}">
                                            <div class="bg-image hover-zoom ripple" data-mdb-ripple-color="light">
                                                <img src="${i.listImage.get(0).imageProduct_url}"
                                                     class="w-100"   alt="Laptop" />
                                                <div class="mask">
                                                    <div class="d-flex justify-content-start align-items-end h-100">
                                                        <h5><span class="badge bg-danger ms-2">-${i.product_percenSale}%</span></h5>
                                                    </div>
                                                </div>
                                                <div class="hover-overlay">
                                                    <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                                                </div>
                                            </div>
                                        </a>
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <p class="small" style="height: 51.4px;"><a href="#!" class="text-muted">${db.getCategoryById(i.category_id).category_name}</a></p>
                                                <p class="small text-dark"><s>${Feature.format(i.product_originPrice)}VND</s></p>
                                            </div>

                                            <div class="d-flex justify-content-between mb-3">
                                                <h5 style="
                                                    width: 130px;
                                                    height: 50px;
                                                    " class="mb-0">${i.product_name}</h5>
                                                <h5 class="text-danger mb-0">${Feature.format(Feature.calculateSalePrice(i.product_originPrice,i.product_percenSale))}VND</h5>
                                            </div>

                                            <div class="d-flex justify-content-between mb-2">
                                                <p class="text-muted mb-0">Available: <span class="fw-bold">${td.quantityOfProductById(i.product_id)}</span></p>
                                                <div class="ms-auto text-warning">
                                                    <c:forEach begin="1" end="${pd.avgStarRate(i.product_id)}">
                                                        <i class="fa fa-star"></i>  
                                                    </c:forEach>
                                                    <span class="badge bg-primary ms-2">${pd.avgStarRate(i.product_id)}</span>


                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="swiper-pagination position-absolute text-center"></div>
    </section>
    <section id="smart-watches" class="product-store padding-large position-relative">
        <div class="container">
            <div class="row">
                <div class="display-header d-flex justify-content-between pb-3">
                    <h2 class="display-7 text-dark text-uppercase">Top Sales Product</h2>
                    <div class="btn-right">
                        <a  id="topSales" class="btn btn-medium btn-normal text-uppercase" style="display: inline-flex;">Sales Product <span class="material-symbols-outlined">arrow_forward_ios</span></a>
                    </div>
                </div>
                <div class="swiper product-watch-swiper">
                    <div class="swiper-wrapper">
                        <c:forEach items="${listSalesProduct}" begin="0" end="7" var="i">
                            <div class="swiper-slide">
                                <div class="row" style="
                                     height: 100%;">
                                    <div class="card">
                                        <div class="d-flex justify-content-between p-3">
                                            <a href="AddWishList?pid=${i.product_id}" >
                                                <p class="lead mb-0">Add to WishList<span class="material-symbols-outlined">favorite</span></p>
                                            </a>

                                        </div>
                                        <a href="product?product_id=${i.product_id}">
                                            <div class="bg-image hover-zoom ripple" data-mdb-ripple-color="light">
                                                <img src="${i.listImage.get(0).imageProduct_url}"
                                                     class="w-100"   alt="Laptop" />
                                                <div class="mask">
                                                    <div class="d-flex justify-content-start align-items-end h-100">
                                                        <h5><span class="badge bg-danger ms-2">-${i.product_percenSale}%</span></h5>
                                                    </div>
                                                </div>
                                                <div class="hover-overlay">
                                                    <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                                                </div>
                                            </div>
                                        </a>
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <p class="small" style="height: 51.4px;"><a href="#!" class="text-muted">${db.getCategoryById(i.category_id).category_name}</a></p>
                                                <p class="small text-dark"><s>${Feature.format(i.product_originPrice)}VND</s></p>
                                            </div>

                                            <div class="d-flex justify-content-between mb-3">
                                                <h5 style="
                                                    width: 130px;
                                                    height: 50px;
                                                    " class="mb-0">${i.product_name}</h5>
                                                <h5 class="text-danger mb-0">${Feature.format(Feature.calculateSalePrice(i.product_originPrice,i.product_percenSale))}VND</h5>
                                            </div>

                                            <div class="d-flex justify-content-between mb-2">
                                                <p class="text-muted mb-0">Available: <span class="fw-bold">${td.quantityOfProductById(i.product_id)}</span></p>
                                                <div class="ms-auto text-warning">
                                                    <c:forEach begin="1" end="${pd.avgStarRate(i.product_id)}">
                                                        <i class="fa fa-star"></i>  
                                                    </c:forEach>
                                                    <span class="badge bg-primary ms-2">${pd.avgStarRate(i.product_id)}</span>


                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="swiper-pagination position-absolute text-center"></div>
    </section>
    <section id="billboard" class="position-relative overflow-hidden bg-light-blue">
        <div class="swiper main-swiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="container">
                        <div class="row d-flex align-items-center">
                            <div class="col-md-6">
                                <div class="banner-content">
                                    <h1 class="display-2 text-uppercase text-dark pb-5">Your Products Are Great.</h1>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="image-holder">
                                    <img src="image/banner-image.png" alt="banner">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="container">
                        <div class="row d-flex flex-wrap align-items-center">
                            <div class="col-md-6">
                                <div class="banner-content">
                                    <h1 class="display-2 text-uppercase text-dark pb-5">Choose everything you want !!!</h1>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="image-holder">
                                    <img src="image/Banner(2).jpg" alt="banner">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="swiper-icon swiper-arrow swiper-arrow-prev">
            <svg class="chevron-left">
            <use xlink:href="#chevron-left" />
            </svg>
        </div>
        <div class="swiper-icon swiper-arrow swiper-arrow-next">
            <svg class="chevron-right">
            <use xlink:href="#chevron-right" />
            </svg>
        </div>
    </section>
    <section id="latest-blog" class="padding-large">
        <div class="container">
            <div class="row">
                <div class="display-header d-flex justify-content-between pb-3">
                    <h2 class="display-7 text-dark text-uppercase">Latest Blog</h2>
                    <div class="btn-right">
                        <a href="http://localhost:9999/tikilazapee/listblog" class="btn btn-medium btn-normal text-uppercase">Read Blog</a>
                    </div>
                </div>
                <div class="post-grid d-flex flex-wrap justify-content-between">
                                                        <c:forEach items="${listLatestBlog}" var="b">  
                    <div class="col-lg-4 col-sm-12">
                        <div class="card border-none me-3">
                            <a href="blogdetail?blog_id=${b.blog_id}">
                            <div class="card-image">
                                <img src="${b.blog_image}" alt="" class="img-fluid" style="
    width: 413px;
    height: 300px;
">
                            </div>
                            </a>
                        </div>
                        <div class="card-body text-uppercase">
                            <div class="card-meta text-muted">
                                <span class="meta-date">${b.blog_create_day}</span>
                                <span class="meta-category">- ${db.getCategoryById(b.getCategory_id()).getCategory_name()}</span>
                            </div>
                            <h3 class="card-title">
                                <a href="blogdetail?blog_id=${b.blog_id}">${b.blog_title}.</a>
                            </h3>
                        </div>
                    </div>
                                         </c:forEach>
                </div>
            </div>
        </div>
    </section>
    <section id="testimonials" class="position-relative" style="
             padding-bottom: 50px;">
        <div class="container">
            <div class="row">
                <div class="review-content position-relative">
                    <div class="swiper-icon swiper-arrow swiper-arrow-prev position-absolute d-flex align-items-center">
                        <svg class="chevron-left">
                        <use xlink:href="#chevron-left" />
                        </svg>
                    </div>
                    <div class="swiper testimonial-swiper">
                        <div class="quotation text-center">
                            <svg class="quote">
                            <use xlink:href="#quote" />
                            </svg>
                        </div>
                        <div class="swiper-wrapper">
                            <c:forEach items="${listFeedback}" var="f">
                                <div class="swiper-slide text-center d-flex justify-content-center">
                                    <div class="review-item col-md-10" >
                                        <a href="product?product_id=${f.product_id}" style="text-decoration: none;">
                                            <i class="icon icon-review"></i>
                                            <blockquote>“${f.feedback_comment}”</blockquote>
                                            <div class="rating">
                                                <c:forEach begin="1" end="${f.feedback_rateStars}">
                                                    <svg class="star star-fill">
                                                    <use xlink:href="#star-fill"></use>
                                                    </svg>
                                                </c:forEach>
                                            </div>
                                            <div class="author-detail">
                                                <div class="name text-dark text-uppercase pt-2">${ud.getUserById(f.customer_id).fullname}</div>

                                            </div>
                                            <div class="name text-dark text-uppercase pt-2">${pb.getProductByIdProduct(f.product_id).getProduct_name()}</div>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="swiper-icon swiper-arrow swiper-arrow-next position-absolute d-flex align-items-center">
                        <svg class="chevron-right">
                        <use xlink:href="#chevron-right" />
                        </svg>
                    </div>
                </div>
            </div>
        </div>
        <div class="swiper-pagination"></div>
    </section>
    <div class="container bootdey">
        <section class="slide">
            <hr style="height: 1px;
                background-color: black;
                margin-bottom: 20px;">
            <div class="container bootdey">
                <div class="col-md-3">
                    <section class="panel">
                        <header class="panel-heading">Category</header>
                        <div class="panel-body">
                            <ul class="nav prod-cat">
                                <c:forEach items="${listCategory}" var="o">
                                    <li>
                                        <a href="listProduct?category_id=${o.category_id}" class="active"
                                           ><i class="fa fa-angle-right"></i> ${o.category_name}</a>
                                        <c:forEach items="${df.getListFilter(o.category_id)}" var="f">
                                            <ul class="nav">
                                                <li "><a href="listProduct?filter_id=${f.filter_id}">${f.nameFilter}</a></li>
                                            </ul>
                                        </c:forEach>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                    </section>
                </div>
                <div class="col-md-9">
                    <div class="carousel">
                        <div class="carousel-inner">
                            <input class="carousel-open" type="radio" id="carousel-1" name="carousel" aria-hidden="true" hidden="" checked="checked">
                            <div class="carousel-item">
                                <img src="https://img3.thuthuatphanmem.vn/uploads/2019/10/14/banner-thoi-trang-dang-hien-dai-nhat_113856210.jpg" alt=""/>
                            </div>
                            <input class="carousel-open" type="radio" id="carousel-2" name="carousel" aria-hidden="true" hidden="">
                            <div class="carousel-item">
                                <img src="https://img3.thuthuatphanmem.vn/uploads/2019/10/14/banner-quang-cao-thoi-trang-nu_113855491.jpg" alt=""/>
                            </div>
                            <input class="carousel-open" type="radio" id="carousel-3" name="carousel" aria-hidden="true" hidden="">
                            <div class="carousel-item">
                                <img src="https://intphcm.com/data/upload/banner-thoi-trang4.jpg" alt=""/>
                            </div>
                            <label for="carousel-3" class="carousel-control prev control-1">‹</label>
                            <label for="carousel-2" class="carousel-control next control-1">›</label>
                            <label for="carousel-1" class="carousel-control prev control-2">‹</label>
                            <label for="carousel-3" class="carousel-control next control-2">›</label>
                            <label for="carousel-2" class="carousel-control prev control-3">‹</label>
                            <label for="carousel-1" class="carousel-control next control-3">›</label>
                            <ol class="carousel-indicators">
                                <li>
                                    <label for="carousel-1" class="carousel-bullet">•</label>
                                </li>
                                <li>
                                    <label for="carousel-2" class="carousel-bullet">•</label>
                                </li>
                                <li>
                                    <label for="carousel-3" class="carousel-bullet">•</label>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <div class="row product-list">
                        <c:forEach items="${listProduct}" var="i">
                            <div class="col-md-4">
                                <section class="panel">
                                    <div class="card">
                                        <div class="d-flex justify-content-between p-3">
                                            <a href="AddWishList?pid=${i.product_id}" >
                                                <p class="lead mb-0">Add to WishList<span class="material-symbols-outlined">favorite</span></p>
                                            </a>
                                        </div>
                                        <a href="product?product_id=${i.product_id}">
                                            <div class="bg-image hover-zoom ripple" data-mdb-ripple-color="light">
                                                <img src="${i.listImage.get(0).imageProduct_url}"
                                                     style="width: 301.05px;
                                                     height: 303.05px;"   alt="Laptop" />
                                                <div class="mask">
                                                    <div class="d-flex justify-content-start align-items-end h-100">
                                                        <h5><span class="badge bg-danger ms-2">-${i.product_percenSale}%</span></h5>
                                                    </div>
                                                </div>
                                                <div class="hover-overlay">
                                                    <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                                                </div>
                                            </div>
                                        </a>
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">

                                                <p class="small text-dark"><s>${Feature.format(i.product_originPrice)}VND</s></p>
                                            </div>

                                            <div class="d-flex justify-content-between mb-3">
                                                <h5 class="mb-0">${i.product_name}</h5>
                                                <h5 class="text-danger mb-0">${Feature.format(Feature.calculateSalePrice(i.product_originPrice,i.product_percenSale))}VND</h5>
                                            </div>

                                            <div class="d-flex justify-content-between mb-2">
                                                <p class="text-muted mb-0">Available: <span class="fw-bold">${td.quantityOfProductById(i.product_id)}</span></p>
                                                <div class="ms-auto text-warning">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>        
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
                <section class="panel">
                    <div class="panel-body">
                        <div class="pull-right">
                            <ul class="pagination pagination-sm pro-page-list">
                                <c:forEach begin="1" end="${numPage}" var="i">
                                    <li class="${index==i?"active":""}" ><a href="viewstore?store_id=${id}&index=${i}">${i}</a></li>
                                    </c:forEach>
                                <li ><a href="viewstore?index=${numPage}">»</a></li>
                            </ul>
                        </div>
                    </div>
                </section>

        </section>
    </div>
    <footer id="footer" class="overflow-hidden" style="
            margin-top: 40px;">
        <div class="container">
            <div class="row">
                <div class="footer-top-area">
                    <div class="row d-flex flex-wrap justify-content-between">
                        <div class="col-lg-3 col-sm-6 pb-3">
                            <div class="footer-menu">
                                <h4>Tikilazapee</h4>
                                <p>Trust us, we will bring you the best experience and whatever you want.</p>
                                <div class="social-links">
                                    <ul class="d-flex list-unstyled">
                                        <li>
                                            <a href="#">
                                                <svg class="facebook">
                                                <use xlink:href="#facebook" />
                                                </svg>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <svg class="instagram">
                                                <use xlink:href="#instagram" />
                                                </svg>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <svg class="twitter">
                                                <use xlink:href="#twitter" />
                                                </svg>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <svg class="linkedin">
                                                <use xlink:href="#linkedin" />
                                                </svg>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <svg class="youtube">
                                                <use xlink:href="#youtube" />
                                                </svg>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 pb-3">
                            <div class="footer-menu text-uppercase">
                                <h5 class="widget-title pb-2">Quick Links</h5>
                                <ul class="menu-list list-unstyled text-uppercase">
                                    <li class="menu-item pb-2">
                                        <a href="#">Home</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">About</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Shop</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Blogs</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Contact</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 pb-3">
                            <div class="footer-menu text-uppercase">
                                <h5 class="widget-title pb-2">Help & Info Help</h5>
                                <ul class="menu-list list-unstyled">
                                    <li class="menu-item pb-2">
                                        <a href="#">Track Your Order</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Returns Policies</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Shipping + Delivery</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Contact Us</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Faqs</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 pb-3">
                            <div class="footer-menu contact-item">
                                <h5 class="widget-title text-uppercase pb-2">Contact Us</h5>
                                <p>Do you have any queries or suggestions? <a href="mailto:">pham94729@gmail.com</a>
                                </p>
                                <p>If you need support? Just give us a call. <a href="">+84 853082922</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
    </footer>
    <script src="JS/jquery-1.11.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
    <script type="text/javascript" src="JS/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="JS/plugins.js"></script>
    <script type="text/javascript" src="JS/script.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript"></script>
    <script>
        $(document).ready(function () {
            $('#viewAllButton').click(function () {
                $('html, body').animate({
                    scrollTop: $('.slide').offset().top
                }, 100); // The number 1000 represents the duration of the scroll in milliseconds
            });
        });

        $(document).ready(function () {
            $('#topSales').click(function () {
                $('html, body').animate({
                    scrollTop: $('.slide').offset().top
                }, 100); // The number 1000 represents the duration of the scroll in milliseconds
            });
        });
    </script>
</body>
</html>