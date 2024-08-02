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
        <title>Order detail</title>    
        <%@include file="../../Template/libheader.jsp" %>
        <c:url value="/CSS/OrderDetail.css" var="OrderDetail"/>
        <c:url value="/JS/OrderDetail.js" var="OrderDetailjs"/>
        <c:url value="/JS/jquery-3.7.1.min.js" var="libJquery"/>
        <link rel="stylesheet" href="${OrderDetail}" />
    </head>
    <body>
        <%@include file="../../Template/navbar.jsp" %>
        <div class="container">
            <div class="order-top" style="margin-top: 30px;">
                <a id="link-back" href="#"><i class="fa-solid fa-angle-left"></i> Back</a>
                <hr>
            </div>
            <div class="order-detail-box" style="border: 3px solid black;">
                <h2><i class="fa-solid fa-location-dot"></i> Delivery address</h2>
                <div class="order-address">
                    <div class="order-information-user">                    
                        <div class="information-user">
                            <h3>${s_u_tikilazapee.fullname} (84+)${s_u_tikilazapee.phoneNumber}</h3> <h5>${s_u_tikilazapee.address} </h5>
                        </div>
                    </div>
                    <div class="order-information-store">                    
                        <div class="information-store">
                            <img src="${order.store.store_image}" alt="A">
                            <h4>${order.store.store_name}</h4>
                            <a href="viewstore?store_id=${order.store_id}"><i class="fa-solid fa-store"></i> &nbsp;View shop</a>
                        </div>
                        <div class="address-store">
                            <h3>Phone number store: <span>(84+)${order.store.store_phone}</span></h3> <h5>Address store: ${order.store.store_address}<span></span></h5>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="order-content">
                    <div class="row Title-box">
                        <div class="col-md-4">Name product</div>
                        <div class="col-md-3"></div>
                        <div class="col-md-2">Unit price</div>
                        <div class="col-md-2">Total price</div>
                        <div class="col-md-1"></div>
                    </div>
                    <c:forEach items="${order.listOrderDetail}" var="p">
                        <div class="row product-box">
                            <div class="col-md-4 product-name">
                                <img src="${p.product.listImage.get(0).imageProduct_url}"
                                     alt="" style="height: 100px;width: 100px;">
                                <p>${p.product.product_name}.</p>
                                <p style="color: gray;"> &nbsp;&nbsp;x${p.quantityProduct}</p>
                            </div>
                            <div class="col-md-3 product-type">
                                <div class="menu-type-color-option" onclick="toggle_menu_type_color_f(this)">
                                    <div>Variations: <label class="arrow-down"></label></div>
                                    <p>Type: ${p.type.type_describes}, Color: ${p.color.color_name}</p> 
                                </div>                           
                            </div>
                            <div class="col-md-2 product-unit-price">${Feature.format(p.unitPrice)}đ</div>
                            <div class="col-md-2 product-total-price">${Feature.format(p.intoPrice)}đ</div>
                            <div class="col-md-1 product-status" style="font-size: 14px">
                                <c:choose>
                                    <c:when test="${order.order_status == -2}"><p style="color: red;font-size: 18px" id="${order.order_id}">Cancel</p></c:when>
                                    <c:when test="${order.order_status == -1}"><p style="color: red;font-size: 18px">Declined</p></c:when>
                                    <c:when test="${order.order_status == 0}"><p style="color: #919115;font-size: 18px">Awaiting for confirm</p></c:when>
                                    <c:when test="${order.order_status == 1}"><p style="color: #6126b7;font-size: 18px">Awaiting prepare product</p></c:when>
                                    <c:when test="${order.order_status == 2}"><p style="color: yellow;font-size: 18px">On shipping</p></c:when>
                                    <c:when test="${order.order_status == 3}"><p style="color: green; font-size: 18px">Successfully</p></c:when>
                                </c:choose>
                            </div>
                        </div>
                        <div class="button-box">
                            <c:if test="${order.order_status == 3}">
                                <c:if test="${p.isFeedback == false}">
                                    <button class="btn-feedback" orderDetail_id="${p.orderDetail_id}">Feedback</button>
                                </c:if>
                                <button class="btn-buy-again" order_id="${order.order_id}" orderDetail_id="${p.orderDetail_id}">Buy again</button>
                            </c:if>
                            <c:if test="${order.order_status == -2}">
                                <button class="btn-buy-again" order_id="${order.order_id}" orderDetail_id="${p.orderDetail_id}">Buy again</button>
                            </c:if>
                        </div>
                        <hr>
                    </c:forEach>
                </div>

                <div class="order-money" style="text-align: center;">
                    <h3 style="font-size: 28px;">Total Order</h3>
                    <h5 style="font-size: 28px;">${Feature.format(order.order_totalPrice)}đ</h5>
                </div>
            </div>
        </div>
        <script src="${libJquery}"></script>
        <script src="${OrderDetailjs}"></script>
    </body>
</html>