<%-- 
    Document   : ManageOrder
    Created on : Jul 15, 2024, 5:12:23 AM
    Author     : hbtth
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>My Order</title>    
        <%@include file="../../Template/libheader.jsp" %>
        <c:url value="/CSS/ManageOrder.css" var="manage"/>
        <c:url value="/JS/ManageOrder.js" var="ManageOrderjs"/>
        <c:url value="/JS/jquery-3.7.1.min.js" var="libJquery"/>
        <link rel="stylesheet" href="${manage}" />

    </head>
    <body>
        <div class="container my-order-box" style="height: 100vh; width: 100vw; margin-top: 40px;">
            <h1 class="text-align">Manage order</h1>
            <div class="col-md-12 search-box">
                <i class="fa-solid fa-magnifying-glass"></i><input type="text" name="search" placeholder="Seach">
            </div>
            <div class="row my-order-box-content">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link ${status==-3?'active':''}" aria-current="page" href="manageorder">All</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${status==-2?'active':''}" href="manageorder?status=-2">Cancel</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${status==-1?'active':''}" href="manageorder?status=-1">Declined</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${status==0?'active':''}" href="manageorder?status=0">Awaiting to confirm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${status==1?'active':''}" href="manageorder?status=1">Confirm to ship</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${status==2?'active':''}" href="manageorder?status=2">On Shipping</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${status==3?'active':''}" href="manageorder?status=3">Success</a>
                    </li>

                </ul>

                <c:forEach items="${list}" var="i">
                    <div class="col-md-11 order" >
                        <div class="top-order">
                            <div class="status-order">
                                <c:choose>
                                    <c:when test="${i.order_payment_status == 0}"><p style="color: green" id="${i.order_id}">Pay in cash  | &nbsp; </p></c:when>
                                    <c:when test="${i.order_payment_status == 1}"><p style="color: #6126b7" id="${i.order_id}">Paid out  | &nbsp;</p></c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${i.order_status == -2}"><p style="color: red" id="${i.order_id}">Cancel</p></c:when>
                                    <c:when test="${i.order_status == -1}"><p style="color: red" id="${i.order_id}">Declined</p></c:when>
                                    <c:when test="${i.order_status == 0}"><p style="color: #919115" id="${i.order_id}">Awaiting for confirm</p></c:when>
                                    <c:when test="${i.order_status == 1}"><p style="color: #919115" id="${i.order_id}">Approved</p></c:when>
                                    <c:when test="${i.order_status == 2}"><p style="color: #6126b7" id="${i.order_id}">On shipping</p></c:when>
                                    <c:when test="${i.order_status == 3}"><p id="${i.order_id}">Successfully</p></c:when>
                                </c:choose>
                            </div>
                        </div>
                        <hr>
                        <div class="main-order">
                            <c:forEach items="${i.listOrderDetail}" var="p">
                                <div class="row product-box">
                                    <div class="col-md-5 product-name">
                                        <img src="../${p.product.listImage.get(0).imageProduct_url}"
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
                                    <h3>Total price</h3>
                                    <h5>${Feature.format(i.order_totalPrice)}đ</h5>
                                </div>
                                <div class="button-box">
                                    <button class="btn-view-detail" order_id="${i.order_id}">View detail</button>
                                    <c:choose>
                                        <c:when test="${i.order_status == 0}">
                                            <form action="manageorder" method="post">
                                                <input type="hidden" value="${i.order_id}" name="order_id"/>
                                                <button type="submit">Approved</button>
                                            </form>

                                            <button class="btn-declined" status="-1" order_id="${i.order_id}">Declined</button>                                          
                                        </c:when>
                                        <c:when test="${i.order_status == 1}">
                                            <button class="btn-confirm" status="2" order_id="${i.order_id}"> Confirm prepare for ship</button>
                                        </c:when>
                                    </c:choose>

                                </div>
                                <c:if test="${order_id == i.order_id}">
                                    <p style="margin-right: 40px;color: red">${notice}</p>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </c:forEach>
                <hr style="height: 3px;color: #000; background-color: #000;">
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
        <script src="${ManageOrderjs}"></script>
    </body>
</html>
