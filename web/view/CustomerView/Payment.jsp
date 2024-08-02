
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Payment</title>    
        <%@include file="../../Template/libheader.jsp" %>
        <c:url value="/CSS/Payment.css" var="payment"/>
        <c:url value="/JS/PaymentJS.js" var="paymentjs"/>
        <c:url value="/JS/jquery-3.7.1.min.js" var="libJquery"/>
        <link rel="stylesheet" href="${payment}" />
    </head>
    <body>
        <%@include file="../../Template/navbar.jsp" %>
        <div class="container payment-box-main" style="height: 100vh; width: 100vw; margin-top: 80px;">
            <h1 class="text-align">Checkout</h1>
            <div class="delivery-address-box">
                <h2><i class="fa-solid fa-location-dot"></i> Delivery address</h2>
                <div class="information-user">
                    <h3>${s_u_tikilazapee.fullname} (84+)${s_u_tikilazapee.phoneNumber}</h3> <h5>${s_u_tikilazapee.address} &nbsp; &nbsp; &nbsp; &nbsp;<span><a href="myprofile">Change</a></span>
                        <c:if test="${s_u_tikilazapee.address == null || s_u_tikilazapee.phoneNumber == null}">
                            <span style="color: red; font-size: 16px"> &nbsp; &nbsp; &nbsp;You must update address or phone number before place order</span>
                        </c:if>
                    </h5>
                </div>
            </div>
            <div class="order-detail-box">
                <div class="row payment-product-box">
                    <div class="col-md-12 product-box-main">
                        <div class="row Title-box">
                            <div class="col-md-5">Name product</div>
                            <div class="col-md-3"></div>
                            <div class="col-md-1">Unit price</div>
                            <div class="col-md-1">Quantity</div>
                            <div class="col-md-2">Total price</div>
                        </div>
                        <c:forEach items="${items}" var="i">
                            <div class="product-box row" >
                                <div class="col-md-5 product-name">
                                    <img src="${i.product.listImage.get(0).imageProduct_url}"
                                         alt="">
                                    <p>${i.product.product_name}</p>
                                </div>
                                <div class="col-md-3 product-type">
                                    <div class="menu-type-color-option" onclick="toggle_menu_type_color_f(this)">
                                        <div>Variations: <label class="arrow-down"></label></div>
                                        <p>Type: ${i.type.type_describes}, Color: ${i.color.color_name}</p> 
                                    </div>                           
                                </div>
                                <div class="col-md-1 product-unit-price">${Feature.format(Feature.calculateSalePrice(i.product.product_originPrice,i.product.product_percenSale))}đ</div>
                                <div class="col-md-1 product-quantity">
                                    <input type="number" name="quantity" value="${i.quantityProduct}" readonly/>
                                </div>
                                <div class="col-md-2 product-total-price">${Feature.format(i.intoPrice)}đ</div>
                            </div>
                        </c:forEach>

                    </div> 
                </div>
            </div>
            <div class="row payment-method-box">
                <div class="col-md-8 payment-method-box-left">
                    <h4>Payment method</h4>
                    <input type="radio" name="method" id="1" value="1"/> <label for="1">VNPAY</label>
                    <input type="radio" name="method" id="2" value="2"/> <label for="2">Cash on delivery</label>
                    <input type="hidden" id="total" value="${total}"/> 
                 
                </div>
                <div class="col-md-4 payment-method-box-right">
                    <h2>Total Price</h2>
                    <h6>${Feature.format(total)}đ</h6>
                    <button id="btn_placeOrder">Place Order</button>
                </div>
            </div>
        </div>
    </body>
    <script src="${libJquery}"></script>
    <script>
        let item_id =[];
        <c:forEach items="${items}" var="i">
            item_id.push(${i.cartItem_id});
        </c:forEach>        

        function doPayment(){
            const radio_button = document.getElementsByName('method');
            let payment_method = null;
            radio_button.forEach(e =>{
                if(e.checked){
                    payment_method = e.value;
                }
            });
            if(payment_method !== null){
                if(payment_method == 1){
                    const list_item = JSON.stringify(item_id);
                    $.ajax({
                        url: "/tikilazapee/myorder",
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({
                            'payment_method':payment_method,
                            'item_id':list_item
                        }),
                        success: function (){
                            const total = document.getElementById('total');
                            $.ajax({
                                url: "/tikilazapee/payment",
                                type: 'POST',
                                contentType: 'application/json',
                                dataType: 'json',
                                data:JSON.stringify({
                                    'amount':total.value
                                }),
                                success: function(response){
                                    if(response.code === '00'){
                                        location.href = response.data;
                                    }
                                }
                            });
                        }                        
                    });
                }else{
                    const list_item = JSON.stringify(item_id);
                    $.ajax({
                        url: "/tikilazapee/myorder",
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({
                            'payment_method':payment_method,
                            'item_id':list_item
                        }),
                        success: function (){
                            location.href = "myorder";
                        }                        
                    });
                    
                }
            }
        }
        <c:if test="${s_u_tikilazapee.address != null && s_u_tikilazapee.phoneNumber != null}">
            const btn_placeOrder = document.getElementById('btn_placeOrder');
            btn_placeOrder.addEventListener("click",doPayment);
        </c:if>

    </script>
    
    <script src="${paymentjs}"></script>
    
</html>
