<%-- 
    Document   : MyOrder
    Created on : Jul 14, 2024, 8:37:36 AM
    Author     : hbtth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>My Order</title>    
        <%@include file="../../Template/libheader.jsp" %>
        <c:url value="/CSS/MyOrder.css" var="MyOrder"/>
        <c:url value="/JS/MyOrder.js" var="MyOrderjs"/>
        <c:url value="/JS/jquery-3.7.1.min.js" var="libJquery"/>
        <link rel="stylesheet" href="${MyOrder}" />
    </head>
    <body>
        <%@include file="../../Template/navbar.jsp" %>
        <div class="container my-order-box" style="height: 100vh; width: 100vw; margin-top: 40px;">
            <h1 class="text-align">List order</h1>
            <div class="col-md-12 search-box">
                <i class="fa-solid fa-magnifying-glass"></i><input type="text" name="search" placeholder="Seach">
            </div>
            <div class="row my-order-box-content">

                <c:forEach items="${list}" var="i">
                    <div class="col-md-11 order" >
                        <div class="top-order">
                            <div class="information-store">
                                <img src="${i.store.store_image}" alt="A">
                                <h4>${i.store.store_name}</h4>
                                <a href="viewstore?store_id=${i.store_id}"><i class="fa-solid fa-store"></i> &nbsp;View shop</a>
                            </div>
                            <div class="status-order">
                                 <c:choose>
                                     <c:when test="${i.order_payment_status == 0}"><p style="color: green" id="${i.order_id}">Pay in cash  | &nbsp; </p></c:when>
                                    <c:when test="${i.order_payment_status == 1}"><p style="color: #6126b7" id="${i.order_id}">Paid out  | &nbsp;</p></c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${i.order_status == -2}"><p style="color: red" id="${i.order_id}">Cancel</p></c:when>
                                    <c:when test="${i.order_status == -1}"><p style="color: red" id="${i.order_id}">Declined</p></c:when>
                                    <c:when test="${i.order_status == 0}"><p style="color: #919115" id="${i.order_id}">Awaiting for confirm</p></c:when>
                                    <c:when test="${i.order_status == 1}"><p style="color: #6126b7" id="${i.order_id}">On prepare product</p></c:when>
                                    <c:when test="${i.order_status == 2}"><p style="color: #6126b7" id="${i.order_id}">On shipping to you</p></c:when>
                                    <c:when test="${i.order_status == 3}"><p id="${i.order_id}">Successfully</p></c:when>
                                </c:choose>

                            </div>
                        </div>
                        <hr>
                        <div class="main-order">
                            <c:forEach items="${i.listOrderDetail}" var="p">
                                <div class="row product-box">
                                    <div class="col-md-5 product-name">
                                        <img src="${p.product.listImage.get(0).imageProduct_url}"
                                             alt="">
                                        <p>${p.product.product_name}.</p>
                                    </div>
                                    <div class="col-md-3 product-type">
                                        <div class="menu-type-color-option">
                                            <div>Variations: <label class="arrow-down"></label></div>
                                            <p>Type: ${p.type.type_describes}, Color: ${p.color.color_name}</p> 
                                        </div>                           
                                    </div>
                                    <div class="col-md-2 product-total-price">${Feature.format(Feature.calculateSalePrice(p.product.product_originPrice,p.product.product_percenSale))}đ</div>
                                </div>
                            </c:forEach>
                            <div class="total-price">
                                <div class="price-box">
                                    <h3>Total Order</h3>
                                    <h5>${Feature.format(i.order_totalPrice)}đ</h5>
                                </div>
                                <div class="button-box">
                                    <button class="btn-view-detail" order_id="${i.order_id}">View detail</button>
                                    <c:choose>
                                        <c:when test="${i.order_status == -2}">                                            
                                            <button class="btn-buy-again" order_id="${i.order_id}">Buy Again</button>
                                        </c:when>
                                        <c:when test="${i.order_status == 0}">
                                            <button class="btn-cancel" order_id="${i.order_id}" status="-2">Cancel</button>                                           
                                        </c:when>
                                        <c:when test="${i.order_status == 2}">
                                            <button class="btn-confirm" order_id="${i.order_id}" status="3">Confirm received</button>
                                        </c:when>
                                        <c:when test="${i.order_status == 3}">
                                            <button class="btn-buy-again" order_id="${i.order_id}">Buy Again</button>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr style="height: 3px;color: #000; background-color: #000;">
                </c:forEach>
                <div class="pagination">
                    <nav aria-label="..." style="margin-bottom: 20px;">
                        <ul class="pagination">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1">Previous</a>
                            </li>
                            <li class="page-item active"><a class="page-link" href="#">1<span class="sr-only">(current)</span></a></li>
                            <li class="page-item ">
                                <a class="page-link" href="#">2 </a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#">Next</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>

        </div>
        <script src="${libJquery}"></script>
        <script src="${MyOrderjs}"></script>
    </body>
</html>