<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.*" %>
<%@page import="Model.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Ansonika">


        <!-- Favicons-->
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" type="image/x-icon" href="img/apple-touch-icon-57x57-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="img/apple-touch-icon-72x72-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="img/apple-touch-icon-114x114-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="img/apple-touch-icon-144x144-precomposed.png">

        <!-- GOOGLE WEB FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

        <!-- BASE CSS -->
        <c:url value="/CSS/bootstrap.min.css" var="bootstrap"/>
        <c:url value="CSS/style113.css" var="style"/>
        <link href="${bootstrap}" rel="stylesheet" type="text/css"/>
        <link href="CSS/style113.css" rel="stylesheet" type="text/css"/>
        <!-- SPECIFIC CSS -->
        <c:url value="/CSS/blogForSeller.css" var="blog"/>
        <link href="${blog}" rel="stylesheet" type="text/css"/>

        <!-- YOUR CUSTOM CSS -->
        <link href="CSS/custom.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">

        <title> Blog Detail</title>
        <style>.full-width-image {
                width: 100%;
                margin: 0;
                padding: 0;
            }

            .full-width-image img {
                width: 100%;
                height: auto;
                display: block;
            }
            .bg_gray1 ul li a {
                font-size: 150%;
                font-weight: bold;
                color: black;
            }
        </style>

        <%@include file="../../Template/libheader.jsp" %>
    </head>
    <body>
        />
        <%@include file="../../Template/navbar.jsp" %>

        <div id="page">

            <div class="bg_gray1">
                <div class="container margin_30">

                    <ul>

                        <li><a href="http://localhost:9999/tikilazapee/listblog"> Tikilazapee Blog &amp; News</a></li>

                    </ul>
                </div>

            </div>


            <main class="bg_gray">
                <div class="container margin_30">
                    <c:set var="blog" value="${requestScope.blog}"></c:set>
                        <!-- /page_header -->
                        <div class="row">
                            <div class="col-lg-9">
                                <div class="singlepost">
                                    <figure class="full-width-image"><img alt="" class="img-fluid" src="${blog.blog_image}"></figure>
                                <h1>${blog.blog_title}</h1>
                                <div class="postmeta">
                                    <ul>
                                        <li><a href="#"><i class="ti-folder"></i> ${category.getCategoryById(blog.getCategory_id()).getCategory_name()}</a></li>
                                        <li><i class="ti-calendar"></i> ${blog.blog_create_day}</li>
                                        <li><a href="#"><i class="ti-user"></i> ${user.getUserById(blog.user_id).getFullname()}</a></li>
                                        <li><a href="#"><i class="ti-comment"></i> 3</a></li>
                                    </ul>
                                </div>
                                <!-- /post meta -->
                                <div class="post-content">
                                    <div class="dropcaps">
                                        <p>${blog.blog_content} </p>
                                    </div>
                                </div>
                                <!-- /post -->
                            </div>
                            <!-- /single-post -->

                            <div id="comments">
                                <h5>Comments</h5>
                                <ul>
                                    <c:forEach items="${requestScope.interactionList}" var="i">
                                        <li>
                                            <div class="avatar">
                                                <a href="#"><img src="${i.user.image}" alt="">
                                                </a>
                                            </div>
                                            <div class="comment_right clearfix">
                                                <c:forEach items="${i.listComment}" var="j">
                                                    <div class="comment_info">
                                                        By <a href="#">${i.user.fullname}</a><span>|</span>
                                                        <span>${j.comment_create_day}</span><a href="#"><i class="icon-reply"></i></a>
                                                    </div>
                                                    <p>
                                                        ${j.comment}
                                                    </p>
                                                </c:forEach>
                                            </div>

                                        </li>
                                    </c:forEach>

                                </ul>
                            </div>

                            
                            <c:if test="${requestScope.currentUser != null}">
                                <h5>Enter comment</h5>
                                <p>${requestScope.currentUser.fullname}</p>
                                <form action="blogdetail" method="post">
                                    <input type="hidden" name="user" value="${currentUser.user_id}"/>
                                    <input type="hidden" name="blog" value="${param.blog_id}"/>
                                    <div class="form-group">
                                        <textarea class="form-control" name="comment" id="comments2" rows="6" placeholder="Comment"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" id="submit2" class="btn_1 add_bottom_15" disabled>Submit</button>
                                    </div>    
                                </form>

                                <script>
                                    document.addEventListener("DOMContentLoaded", function () {
                                        const commentField = document.getElementById("comments2");
                                        const submitButton = document.getElementById("submit2");

                                        commentField.addEventListener("input", function () {
                                            if (commentField.value.trim() !== "") {
                                                submitButton.disabled = false;
                                            } else {
                                                submitButton.disabled = true;
                                            }
                                        });
                                    });
                                </script>
                            </c:if>


                        </div>
                        <!-- /col -->

                        <aside class="col-lg-3">
                            <div class="widget search_blog">
                                <div class="form-group">
                                    <input type="text" name="search" id="search" class="form-control" placeholder="Search..">
                                    <button type="submit"><i class="ti-search"></i></button>
                                </div>
                            </div>
                            <!-- /widget -->
                            <div class="widget">
                                <div class="widget-title">
                                    <h4>Latest Post</h4>
                                </div>

                                <ul class="comments-list">
                                    <c:forEach items="${listLatestBlog}" var="b">
                                        <li>
                                            <div class="alignleft">
                                                <a href="blogdetail?blog_id=${b.blog_id}"><img src="${b.blog_image}" alt=""></a>
                                            </div>
                                            <small>${category.getCategoryById(b.getCategory_id()).getCategory_name()} - ${b.blog_create_day}</small>
                                            <h3><a href="blogdetail?blog_id=${b.blog_id}" title="">${b.blog_title}.</a></h3>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <!-- /widget -->
                            <!-- <div class="widget">
                                    <div class="widget-title">
                                            <h4>Categories</h4>
                                    </div>
                                    <ul class="cats">
                                            <li><a href="#">Food <span>(12)</span></a></li>
                                            <li><a href="#">Places to visit <span>(21)</span></a></li>
                                            <li><a href="#">New Places <span>(44)</span></a></li>
                                            <li><a href="#">Suggestions and guides <span>(31)</span></a></li>
                                    </ul>
                            </div> -->
                            <!-- /widget -->
                            <div class="widget">
                                <div class="widget-title">
                                    <h4>Popular Tags</h4>
                                </div>
                                <div class="tags">
                                    <a href="#">Balo</a>
                                    <a href="#">Shoes</a>
                                    <a href="#">T-Shirt</a>
                                    <a href="#">Laptop</a>
                                    <a href="#">Electronic Device</a>
                                    <a href="#">Phone</a>
                                    <a href="#">Cosmetic</a>
                                </div>
                            </div>
                            <!-- /widget -->
                        </aside>
                        <!-- /aside -->
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </main>
            <!--/main-->


        </div>
        <!-- page -->

        <div id="toTop"></div><!-- Back to top button -->

        <!-- COMMON SCRIPTS -->
        <script src="js/common_scripts.min.js"></script>
        <script src="js/main.js"></script>


    </body>
</html>
