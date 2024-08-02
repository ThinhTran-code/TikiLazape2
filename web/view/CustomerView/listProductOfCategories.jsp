<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %>
<%@page import="DAO.*" %>
<%@page import="util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
    <title>ListProductOfCategory</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      rel="stylesheet"
    />
     <style>
            .carousel {
                margin-left: 0; /* Chỉnh lại lề trái cho phù hợp */
                margin-right: 10%; /* Chỉnh lại lề phải cho phù hợp */
                width: 100%; /* Đã tăng chiều rộng lên 1,3 lần */
            }

            ul.slides {
                display: block;
                position: relative;
                height: 300px; /* Đã chỉnh từ 600px xuống 300px */
                margin: 0;
                padding: 0;
                overflow: hidden;
                list-style: none;
            }

            .slides * {
                user-select: none;
                -ms-user-select: none;
                -moz-user-select: none;
                -khtml-user-select: none;
                -webkit-user-select: none;
                -webkit-touch-callout: none;
            }

            ul.slides input {
                display: none;
            }

            .slide-container {
                display: block;
            }

            .slide-image {
                display: block;
                position: absolute;
                width: 100%;
                height: 100%;
                top: 0;
                opacity: 0;
                transition: all .7s ease-in-out;
            }

            .slide-image img {
                width: auto;
                min-width: 100%;
                height: 100%;
            }

            .carousel-controls {
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                z-index: 999;
                font-size: 100px;
                line-height: 300px; /* Đã chỉnh từ 600px xuống 300px */
                color: #fff;
            }

            .carousel-controls label {
                display: none;
                position: absolute;
                padding: 0 20px;
                opacity: 0;
                transition: opacity .2s;
                cursor: pointer;
            }

            .slide-image:hover + .carousel-controls label{
                opacity: 0.5;
            }

            .carousel-controls label:hover {
                opacity: 1;
            }

            .carousel-controls .prev-slide {
                width: 49%;
                text-align: left;
                left: 0;
            }

            .carousel-controls .next-slide {
                width: 49%;
                text-align: right;
                right: 0;
            }

            .carousel-dots {
                position: absolute;
                left: 0;
                right: 0;
                bottom: 20px;
                z-index: 999;
                text-align: center;
            }

            .carousel-dots .carousel-dot {
                display: inline-block;
                width: 30px;
                height: 30px;
                border-radius: 50%;
                background-color: #fff;
                opacity: 0.5;
                margin: 10px;
            }

            input:checked + .slide-container .slide-image {
                opacity: 1;
                transform: scale(1);
                transition: opacity 1s ease-in-out;
            }

            input:checked + .slide-container .carousel-controls label {
                display: block;
            }

            input#img-1:checked ~ .carousel-dots label#img-dot-1,
            input#img-2:checked ~ .carousel-dots label#img-dot-2,
            input#img-3:checked ~ .carousel-dots label#img-dot-3,
            input#img-4:checked ~ .carousel-dots label#img-dot-4,
            input#img-5:checked ~ .carousel-dots label#img-dot-5,
            input#img-6:checked ~ .carousel-dots label#img-dot-6 {
                opacity: 1;
            }

            input:checked + .slide-container .nav label {
                display: block;
            }

        </style>
    
    <c:url value="/CSS/listproductCSS.css" var="listproduct"/>
    
    <link rel="stylesheet" href="${listproduct}"/>
    <%@include file="../../Template/libheader.jsp" %>
  </head>

       
        
       
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <title>ListProductOfCategory</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <c:url value="/CSS/listproductCSS.css" var="listproduct"/>
        <link rel="stylesheet" href="${listproduct}"/>
    
    <body>
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
      rel="stylesheet"
    />
    <%@include file="../../Template/navbar.jsp" %>
    <div class="container bootdey">
      <div class="col-md-3">
        <section class="panel">
             
        </section>
        <link
            href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
            rel="stylesheet"
            />
        <div class="container bootdey">
            <div class="col-md-3">
                <section class="panel">
                    <header class="panel-heading">Category</header>
                    <div class="panel-body">
                        <ul class="nav prod-cat">
                            <c:forEach items="${listC}" var="o">
                                <li class="w-100"><details <c:if test="${param.category_id == o.category_id}">open="true"</c:if>>
                                            <summary class="w-100 d-block">
                                                <a href="listProduct?category_id=${o.category_id}" class="active w-100"><i class="fa fa-angle-right "></i> ${o.category_name}</a>
                                        </summary>
                                        <ul class="nav">
                                            <c:forEach items="${df.getListFilter(o.category_id)}" var="f">
                                                <li class="nav-link w-100"><a href="listProduct?category_id=${o.category_id}&filter_id=${f.filter_id}">${f.nameFilter}</a></li>
                                                </c:forEach>
                                        </ul>
                                    </details></li>
                                </c:forEach>

                        </ul>
                    </div>
                </section>


                
            </div>
            <div class="col-md-9">
                <div>
                    <div class="carousel">
                        <ul class="slides">
                            <input type="radio" name="radio-buttons" id="img-1" checked />
                            <li class="slide-container">
                                <div class="slide-image">
                                    <img src="https://img3.thuthuatphanmem.vn/uploads/2019/10/14/banner-thoi-trang-dang-hien-dai-nhat_113856210.jpg">
                                </div>
                                <div class="carousel-controls">
                                    <label for="img-3" class="prev-slide">
                                        <span>&lsaquo;</span>
                                    </label>
                                    <label for="img-2" class="next-slide">
                                        <span>&rsaquo;</span>
                                    </label>
                                </div>
                            </li>
                            <input type="radio" name="radio-buttons" id="img-2" />
                            <li class="slide-container">
                                <div class="slide-image">
                                    <img src="https://cdn.shopify.com/s/files/1/0665/0627/files/Porter_1400x.png?v=1616271867">
                                </div>
                                <div class="carousel-controls">
                                    <label for="img-1" class="prev-slide">
                                        <span>&lsaquo;</span>
                                    </label>
                                    <label for="img-3" class="next-slide">
                                        <span>&rsaquo;</span>
                                    </label>
                                </div>
                            </li>
                            <input type="radio" name="radio-buttons" id="img-3" />
                            <li class="slide-container">
                                <div class="slide-image">
                                    <img src="https://www.harvieandhudson.com/images/articles/hh-homepage/Artboard%202.jpg">
                                </div>
                                <div class="carousel-controls">
                                    <label for="img-2" class="prev-slide">
                                        <span>&lsaquo;</span>
                                    </label>
                                    <label for="img-1" class="next-slide">
                                        <span>&rsaquo;</span>
                                    </label>
                                </div>
                            </li>
                            <div class="carousel-dots">
                                <label for="img-1" class="carousel-dot" id="img-dot-1"></label>
                                <label for="img-2" class="carousel-dot" id="img-dot-2"></label>
                                <label for="img-3" class="carousel-dot" id="img-dot-3"></label>
                            </div>
                        </ul>
                    </div>
                </div>
                <section class="panel">
                    <div class="panel-body">
                        <div class="pull-right">
                            <ul class="pagination pagination-sm pro-page-list">
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">»</a></li>
                            </ul>
                        </div>
                    </div>
                </section>
                <div class="row product-list">
                    <c:forEach items="${requestScope.listProduct}" var="i">
                        <div class="col-md-4">
                            <section class="panel">
                                <div class="card">
                                    <div class="d-flex justify-content-between p-3">
                                        <a href="AddWishList?pid=${i.product_id}">
                                            <p class="lead mb-0">Add to WishList <i class="fa-regular fa-heart"></i></p>
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
                                            <p class="text-muted mb-0">Available: <span class="fw-bold">${db.quantityOfProductById(i.product_id)}</span></p>
                                            <div class="ms-auto text-warning">
                                                <c:forEach begin="1" end="${db.avgStarRate(i.product_id)}">
                                                    <i class="fa fa-star"></i>  
                                                </c:forEach>
                                                <span class="badge bg-primary ms-2">${db.avgStarRate(i.product_id)}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                        </div>

                    </c:forEach>
                    <section class="panel">
                    <div class="panel-body">
                        <div class="pull-right">
                            <ul class="pagination pagination-sm pro-page-list">
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">»</a></li>
                            </ul>
                        </div>
                    </div>
                </section>

                </div>

            </div>



        </div>
        <script>
            // Lấy danh sách các phần tử Add Wish List
const addToWishlistElements = document.querySelectorAll('.add-to-wishlist');

// Xử lý sự kiện khi click vào Add Wish List
addToWishlistElements.forEach(element => {
    element.addEventListener('click', function(event) {
        event.preventDefault();
        const pid = element.getAttribute('data-product-id');
        
        // Gửi yêu cầu đến servlet AddWishList?pid=pid
        fetch(`AddWishList?pid=${pid}`)
            .then(response => {
                if (response.ok) {
                    // Thêm lớp 'added' vào phần tử đã click
                    element.classList.add('added');
                    // Chuyển đến trang home sau khi thêm vào wishlist
                    window.location.href = 'home';
                } else {
                    console.error('Failed to add to wishlist');
                }
            })
            .catch(error => console.error('Error:', error));
    });
});
        </script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript"></script>
    </body>
</html>

