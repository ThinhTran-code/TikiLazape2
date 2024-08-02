<%-- 
    Document   : ShoppingCartPage
    Created on : Jun 8, 2024, 2:55:49 PM
    Author     : hbtth
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.*" %>
<%@page import="Model.*" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Shopping cart</title>    
        <%@include file="../../Template/libheader.jsp" %>
        <c:url value="/CSS/ShoppingCartCSS.css" var="shoppingcart"/>
        <c:url value="/JS/ShoppingCart.js" var="shoppingcartjs"/>
        <c:url value="/JS/jquery-3.7.1.min.js" var="libJquery"/>
        <link rel="stylesheet" href="${shoppingcart}" />
    </head>
    <body>
        <div class="shopping-cart">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 cart-top">
                        <i class="fa-solid fa-arrow-left" onclick="back()"></i>
                        <p>My Shopping Cart</p>
                        <i class="fa-solid fa-house" onclick="home()"></i>
                    </div>
                    <div class="row cart-main">
                        <div class="select-all-box row">
                            <div class="checkbox col-md-1">
                                <input type="checkbox" id="myCheckBox" name="myCheckBox" />
                                <label for="myCheckBox" style="font-weight: 700;">All</label>
                            </div>
                            <div class="col-md-3">Name product</div>
                            <div class="col-md-2">Type</div>
                            <div class="col-md-1">Unit price</div>
                            <div class="col-md-2">Quantity</div>
                            <div class="col-md-2">Total price</div>
                            <div class="col-md-1">Action</div>
                        </div>


                        <c:forEach items="${cart.listItem}" var="item">
                            <div class="product-box row" >
                                <div class="checkbox col-md-1">
                                    <input type="checkbox" item_id="${item.cartItem_id}" id="${item.product_id}" onclick="onChecked(this)" value="${item.intoPrice}"
                                           ${item.product.getProduct_quantity(item.product_id, item.color_id,item.type_id) < item.quantityProduct ? 'disabled="true"':''}
                                           color_id ="${item.color_id}" type_id="${item.type_id}" quantity="${item.quantityProduct}"
                                           />
                                </div>
                                <div class="col-md-3 product-name">
                                    <img src="${item.product.listImage.get(0).imageProduct_url}"
                                         alt="">
                                    <p>${item.product.product_name}
                                        <label style="font-size: 12px;color: gray">Store name: ${db.getNameStoreByProduct_ID(item.product_id)}</label>
                                    </p>
                                </div>
                                <div class="col-md-2 product-type">
                                    <div class="menu-type-color-option" onclick="toggle_menu_type_color_f(this)">
                                        <div>Variations: <label class="arrow-down"></label></div>
                                        <p>Type: ${item.type.type_describes}, Color: ${item.color.color_name}</p> 
                                    </div>
                                    <div class="menu-type-color-option-toggle close">                                
                                        <div class="color-box">
                                            <h5>Color:</h5>                          
                                            <c:forEach items="${item.product.listColor}" var="color">
                                                <input class="${item.cartItem_id}" type="radio" name="color${item.cartItem_id}"
                                                       id="${color.color_id};color${item.cartItem_id}" value="${color.color_id}" 
                                                       ${color.color_id == item.color_id ?'checked':''}
                                                       onchange="getQuantity(${item.product_id},${item.cartItem_id})"> 
                                                <label for="${color.color_id};color${item.cartItem_id}">
                                                    ${color.color_name}</label>
                                                </c:forEach>
                                        </div>
                                        <hr>
                                        <div class="type-box">
                                            <h5>Type:</h5>
                                            <c:forEach items="${item.product.listType}" var="type">
                                                <input class="${item.cartItem_id}" type="radio" name="type${item.cartItem_id}"
                                                       id="${type.type_id};type${item.cartItem_id}" value="${type.type_id}"
                                                       ${type.type_id == item.type_id ?'checked':''}
                                                       onchange="getQuantity(${item.product_id},${item.cartItem_id})"
                                                       >
                                                <label for="${type.type_id};type${item.cartItem_id}"

                                                       >${type.type_describes}</label>
                                            </c:forEach>
                                        </div>
                                        <div class="quantity-box" style="margin-top: 20px;" id="${item.cartItem_id}available">
                                            Available: ${item.product.getProduct_quantity(item.product_id, item.color_id,item.type_id)}
                                        </div>
                                        <div class="button-box">
                                            <button class="cancel" onclick="cancel_button(this)">
                                                Cancel
                                            </button>
                                            <button class="Confirm" onclick="confirm_button(${item.product_id},${item.cartItem_id},${cart.shoppingCart_id})">
                                                Confirm
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-1 product-unit-price">${Feature.format(Feature.calculateSalePrice(item.product.product_originPrice,item.product.product_percenSale))}đ</div>
                                <div class="col-md-2 product-quantity">
                                    <button class="btn-quantity" onclick="changeQuantityRemove(this, -1,${item.unitPrice},${item.product_id},${item.cartItem_id})"><i class="fa-solid fa-minus"></i></button>
                                    <input id="${item.cartItem_id}quantity" type="number" name="quantity" max="${item.product.getProduct_quantity(item.product_id, item.color_id,item.type_id)}" value="${item.quantityProduct}" readonly/>
                                    <button class="btn-quantity" onclick="changeQuantityAdd(this, 1,${item.unitPrice},${item.product_id},${item.cartItem_id})"><i class="fa-solid fa-plus"></i></button>
                                </div>
                                <div class="col-md-2 product-total-price">${Feature.format(item.intoPrice)} đ 
                                    <c:if test="${item.product.getProduct_quantity(item.product_id, item.color_id,item.type_id) < item.quantityProduct}">
                                        <span style="color: red">&nbsp; &nbsp; &nbsp;Sold out</span>

                                    </c:if>
                                </div>
                                <div class="col-md-1 product-action"><p onclick="doDelete(${item.cartItem_id}, this)"><i class="fa-solid fa-trash"></i></p></div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="row cart-checkout">
                        <div class="action-box col-md-6">
                            <input type="checkbox" name="" id="cart-checkout-checkbox">
                            <label for="cart-checkout-checkbox">Select All <span>(2)</span></label>
                            <a href="#" onclick="deleteAll(${cart.shoppingCart_id})">Delete All</a>
                        </div>
                        <div class="checkout-box col-md-6">
                            <div class="checkout">
                                <p>Total (0 item): <span>${Feature.format(0)}đ</span></p>

                                <input type="hidden" name="item_id" value="">
                                <button id="btn_checkout">Checkout</button>                          
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="${libJquery}"></script>
        <script src="${shoppingcartjs}"></script>
    </body>
</html>