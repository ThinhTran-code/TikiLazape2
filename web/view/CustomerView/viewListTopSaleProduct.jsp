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
        <title>Sales Product</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <c:url value="/CSS/listproductCSS.css" var="listproduct"/>
        <link rel="stylesheet" href="${listproduct}"/>
        <%@include file="../../Template/libheader.jsp" %>
    </head>
    <style>
        .w-100{
            font-size: medium;
        } 
    </style>
    <body>
        <link
            href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
            rel="stylesheet"
            />
        <%@include file="../../Template/navbar.jsp" %>
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
                    <c:forEach items="${listAll}" var="i">
                        <div class="col-md-4">
                            <section class="panel">
                                <div class="card">
                                    <div class="d-flex justify-content-between p-3">
                                        <a href="AddWishList?pid=${i.product_id}" >
                                            <p class="lead mb-0">Add Wish List <i class="fa-regular fa-heart"></i></p>

                                        </a>
                                    </div>
                                    <a href="product?product_id=${i.product_id}">
                                        <div class="bg-image hover-zoom ripple" data-mdb-ripple-color="light">
                                            <img src="${i.listImage.get(0).imageProduct_url}"
                                                 style="width: 303.05px;
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
                </section>
            </div>
            <!--          <section class="panel">
                      <div class="panel-body">
                        <div class="pull-right">
                          <ul class="pagination pagination-sm pro-page-list">
            <c:forEach begin="1" end="${numPage}" var="i">
                <li class="${index==i?"active":""}" ><a href="topsaleproduct?index=${i}">${i}</a></li>
            </c:forEach>
            </section>
        </div>-->
            <section class="panel">
                <div class="panel-body">
                    <div class="pull-right">
                        <ul class="pagination pagination-sm pro-page-list">
                            <c:forEach begin="1" end="${numPage}" var="i">
                                <li class="${index==i?"active":""}" ><a href="topsaleproduct?index=${i}">${i}</a></li>
                                </c:forEach>
                            <li ><a href="topsaleproduct?index=${numPage}">»</a></li>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
        <!--          </div>
                </section>
                    </div>-->
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript"></script>
    </body>
</html>
