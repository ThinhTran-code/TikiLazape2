<%-- 
    Document   : navbar
    Created on : May 21, 2024, 11:14:46 AM
    Author     : hbtth
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="height: 110px">
    <div class="navbar-box">
        <div class="row" >
            <div class="col-md-10 navbar-top">
                <div class="top-left">
                    <ul>
                        <c:if test="${sessionScope.s_u_tikilazapee!= null}">
                            <c:if test="${sessionScope.s_u_tikilazapee.role.role_id == 1}">
                                <li><a href="admin/dashboard">Back to Admin Center</a></li>
                                </c:if>
                                <c:if test="${sessionScope.s_u_tikilazapee.role.role_id == 2}">
                                <li><a href="seller/dashboard">Back to seller Center</a></li>
                                </c:if>
                                <c:if test="${sessionScope.s_u_tikilazapee.role.role_id == 3}">
                                <li><a href="#">Become to seller Center</a></li>
                                </c:if>
                            </c:if>

                        <li><a href="#">
                                Follow us on 
                                <i class="fa-brands fa-instagram"></i> 
                                <i class="fa-brands fa-facebook"></i>
                            </a></li>

                    </ul>
                </div>
                <div class="top-right">
                    <ul>
                        <li><a href="#"><i class="fa-solid fa-bell"></i> Notifications</a></li>
                        <li><a href="ViewWishList"><i class="fa-regular fa-heart"></i> Wish List</a></li>
                        <li><a href="http://localhost:9999/tikilazapee/listblog"><i class="fa-solid fa-blog"></i> Blog</a></li>
                        <li><a href="http://localhost:9999/tikilazapee/allproduct"><i class="fa-solid fa-list"></i> List products</a></li>
                            <c:if test="${sessionScope.s_u_tikilazapee!= null}">                   
                            <li class="dropdown">
                                <div class="dropdown_select">
                                    <span class="dropdown_selected" style="color: white;">
                                        <a style="font-size: 12px"> <img style="height: 30px;width: 30px;border-radius: 50px;" src="${s_u_tikilazapee.image}"/>
                                            ${sessionScope.s_u_tikilazapee.fullname}
                                        </a>
                                    </span>
                                    <ul class="dropdown_list">
                                        <li class="dropdown-item">
                                            <a href="myprofile" class="dropdown-text" style="color: black; font-weight: bold;">My profile</a>
                                            <i class="fa-regular fa-user"></i>
                                        </li>
                                        <li class="dropdown-item">
                                            <a href="myorder" class="dropdown-text" style="color: black; font-weight: bold;">My Order</a>
                                            <i class="fa-solid fa-file-invoice"></i>
                                        </li>
                                        <li class="dropdown-item">
                                            <a href="logout" class="dropdown-text" style="color: black; font-weight: bold;">Logout</a>
                                            <i class="fa-solid fa-right-from-bracket"></i>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.s_u_tikilazapee== null}">
                            <li id="sign-in"><a href="login"><i class="fa-solid fa-user"></i> Sign in</a></li>
                            <li><a href="register">Sign up</a></li>
                            </c:if>

                    </ul>
                </div>
            </div>
            <div class="col-md-10 navbar-bottom" >
                <a class="logo-box" href="home">
                    <img src="image/image_logo/logowebsite.png" alt="">
                    <h1>Tikilazapee</h1>
                </a>
                <div class="search-box">
                    <form class="search-bar" method="get" action="searchpage?index=1">
                        <input name="index" value="1" type="hidden">
                        <input type="search" placeholder="Tikilazapee for the best choice" name="txt" value="${txt}" style="color: black">
                        <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </form>

                    <div class="example-box" style="background: #a36b6b">
                        <ul>
                            <li><a href="searchpage?index=1&txt=Bag">Bag</a></li>
                            <li><a href="searchpage?index=1&txt=Shoes">Shoes</a></li>
                            <li><a href="searchpage?index=1&txt=Shirt">Shirt</a></li>
                            <li><a href="searchpage?index=1&txt=Iphone">Iphone</a></li>
                            <li><a href="searchpage?index=1&txt=Air pod">Air pod</a> </li>
                            <li><a href="searchpage?index=1&txt=Laptop">Laptop</a></li>
                            <li><a href="searchpage?index=1&txt=Teddy">Teddy</a></li>
                            <li><a href="searchpage?index=1&txt=Sweeter">Sweeter</a></li>
                            <li><a href="searchpage?index=1&txt=Laptop">Laptop</a></li>
                            <li><a href="searchpage?index=1&txt=Mouse">Mouse</a></li>
                            <li><a href="searchpage?index=1&txt=PC">PC</a></li>
                            <li><a href="searchpage?index=1&txt=Jean">Jean</a></li>
                            <li><a href="searchpage?index=1&txt=Cart">Cart</a></li>
                        </ul>
                    </div>
                </div>

                <a class="shopping-cart-box" href="cart">
                    <i class="fa-solid fa-cart-shopping"></i>
                </a>
            </div>
        </div>
    </div>






</div>