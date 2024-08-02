<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.*" %>
<%@page import="Model.*" %>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Ansonika">
        <title>Seller Manage Blog</title>

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
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <!-- BASE CSS -->
        <c:url var="bootstrap" value="/CSS/bootstrap.min.css"/>
        <c:url var="style" value="/CSS/styleBlogForSeller.css"/>
        <c:url var="style1" value="/CSS/blogForSeller.css"/>
        <link href="${bootstrap}" rel="stylesheet">
        <link href="${style}" rel="stylesheet">

        <!-- SPECIFIC CSS -->
        <link href="${style1}" rel="stylesheet">

        <!-- YOUR CUSTOM CSS -->

    </head>

    <body>

        <div id="page">



            <main class="bg_gray">
                <div class="container margin_30">
                    <a href="manageblog">
                    <div class="page_header">
                        <h1>Blog &amp; News</h1>
                    </div>
                        </a>
                    <!-- /page_header -->
                    <div class="row">
                        <div class="col-lg-9">

              
                            <!-- /widget -->
                            <div class="row">

                                <div class="col-md-6">
                                    <c:forEach items="${listLeft}" var="b">
                                        <article class="blog">
                                            <input type="hidden" value="${b.blog_id}" name="blog_id">
                                            <figure>
                                                <a href="#"><img style="width: 430px;
                                                                                                     height: auto" src="../${b.blog_image}" alt="">
                                                    
                                                </a>
                                            </figure>
                                            <div class="post_info">
                                                <small>${category.getCategoryById(b.getCategory_id()).getCategory_name()} - ${b.blog_create_day}</small>
                                                <h2><a href="blog-post.html">${b.blog_title}</a></h2>
                                                <p>${Feature.formatBlogContext(b.getBlog_content())}...</p>
                                                <ul>
                                                    <li>
                                                        <div class="thumb"><img src="${ud.getUserById(b.user_id).getImage()}" alt=""></div> ${user.getUserById(b.user_id).getFullname()}
                                                    </li>
                                                    <li><i class="ti-comment"></i>20</li>
                                                </ul>
                                                <div class="action-button">
                                                    <a href="editblog?blog_id=${b.blog_id}">
                                                        <button class="btn btn-success btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Edit"><span class="material-symbols-outlined">edit_note</span></button>
                                                    </a>
                                                    <a href="deleteblog?blog_id=${b.blog_id}">
                                                        <button class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Delete"><span class="material-symbols-outlined">delete</span></button>
                                                    </a>
                                                </div>
                                            </div>
                                        </article>
                                    </c:forEach>
                                    <!-- /article -->
                                </div>
                                <!-- /col -->
                                <div class="col-md-6">
                                    <c:forEach items="${listRight}" var="b">
                                        <article class="blog">
                                            <input type="hidden" value="${b.blog_id}" name="blog_id">
                                            <figure>
                                                <a href="#"><img style="width: 430px;
                                                                        height: auto" src="../${b.blog_image}" alt="">
                                                </a>
                                            </figure>
                                            <div class="post_info">
                                                <small>${category.getCategoryById(b.getCategory_id()).getCategory_name()} - ${b.blog_create_day}</small>
                                                <h2><a href="blog-post.html">${b.blog_title}</a></h2>
                                                <p>${Feature.formatBlogContext(b.getBlog_content())}...</p>
                                                <ul>
                                                    <li>
                                                        <div class="thumb"><img src="${ud.getUserById(b.user_id).getImage()}" alt=""></div> ${user.getUserById(b.user_id).getFullname()}
                                                    </li>
                                                    <li><i class="ti-comment"></i>20</li>
                                                </ul>
                                                <div class="action-button">
                                                    <a href="editblog?blog_id=${b.blog_id}">
                                                        <button class="btn btn-success btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Edit"><span class="material-symbols-outlined">edit_note</span></button>
                                                    </a>
                                                    <a href="deleteblog?blog_id=${b.blog_id}" onclick="return confirm('Are you want to Delete this Blog ?')">
                                                        <button class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Delete"><span class="material-symbols-outlined">delete</span></button>
                                                    </a>
                                                </div>
                                            </div>
                                        </article>
                                    </c:forEach>
                                    <!-- /article -->
                                </div>
                                <a href="addblog" style="justify-content: center;display: flex;font-size: -webkit-xxx-large;"><p>Add Blog</p></a>
                            </div>
                            <!-- /row -->

                            <div class="pagination__wrapper no_border add_bottom_30">
                                <ul class="pagination">
                                    <li><a href="#0" class="prev" title="previous page">&#10094;</a></li>
                                    <li>
                                        <a href="#0" class="active">1</a>
                                    </li>
                                    <li>
                                        <a href="#0">2</a>
                                    </li>
                                    <li>
                                        <a href="#0">3</a>
                                    </li>
                                    <li>
                                        <a href="#0">4</a>
                                    </li>
                                    <li><a href="#0" class="next" title="next page">&#10095;</a></li>
                                </ul>
                            </div>
                            <!-- /pagination -->

                        </div>
                        <!-- /col -->

                        <aside class="col-lg-3">
                            <form action="searchblog" method="get">
                            <div class="widget search_blog d-none d-sm-none d-md-none d-lg-block">
                                <div class="form-group">
                                    <input type="text" name="search" id="search_blog" class="form-control" placeholder="Search..">
                                    <button type="submit"><i class="ti-search"></i></button>
                                </div>
                            </div>
                                </form>
                            <!-- /widget -->
                            <div class="widget">
                                <div class="widget-title">
                                    <h4>Latest Post</h4>
                                </div>
                                <ul class="comments-list">
                                    <c:forEach items="${listLatestBlog}" var="b">
                                        <li>
                                            <div class="alignleft">
                                                <a href="#0"><img src="../${b.blog_image}" alt=""></a>
                                            </div>
                                            <small>${category.getCategoryById(b.getCategory_id()).getCategory_name()} - ${b.blog_create_day}</small>
                                            <h3><a href="#" title="">${b.blog_title}.</a></h3>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </aside>
                        <!-- /aside -->
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </main>
            <!--/main-->
            <!--/footer-->
        </div>
        <!-- page -->

        <div id="toTop"></div><!-- Back to top button -->

        <!-- COMMON SCRIPTS -->
        <script src="JS/common_scripts.min.js"></script>
        <script src="JS/mainBlogSeller.js"></script>


    </body>
</html>