<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %>
<%@page import="DAO.*" %>
<%@page import="util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <!--<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />-->
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <title>WishList</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <c:url value="/CSS/listproductCSS.css" var="listproduct"/>
        <link rel="stylesheet" href="${listproduct}"/>
        <%@include file="/Template/libheader.jsp" %>
        <script src="JS/modernizr.js"></script>
        <style>
            .dropdown{
                display : inline-flex;
                margin: 10px 0px;
            }
            .panel-body {
                padding: 0px;
            }
              .mb-20 {
        margin-bottom: 20px;
    }
        </style>
    </head>
    <body>
        <div style="height: 150px"> <%@include file="/Template/navbar.jsp" %> </div>
        <div class="container bootdey">
            <div class="col-md-12">
                <section class="panel">

                </section>
                <div class="row product-list">
                    <c:forEach items="${lists}" var="i">
                        <div class="col-md-4">
                            <section class="panel">
                                <div class="card">
                                    <div class="d-flex justify-content-between p-3">
                                        <a href="product?product_id=${i.product_id}">
                                            <p class="lead mb-0">Add to Cart<span class="material-symbols-outlined">                                           
                                                </span></p>
                                        </a>

                                    </div>
                                    <a href="#!">
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

                                            <p class="small text-dark"><s>${Feature.format(i.product_originPrice)} VND</s></p>
                                        </div>

                                        <div class="d-flex justify-content-between mb-3">
                                            <h5 class="mb-0">${i.product_name}</h5>
                                            <h5 class="text-danger mb-0">${Feature.format(Feature.calculateSalePrice(i.product_originPrice,i.product_percenSale))} VND</h5>
                                        </div>

                                        <div class="d-flex justify-content-between mb-2">
                                            <p class="text-muted mb-0">Available: <span class="fw-bold">${pd.quantityOfProductById(i.product_id)}</span></p>
                                            <div class="ms-auto text-warning">
                                                <i class="fa fa-star"></i>
                                            </div>
                                        </div>
                                    </div>
                                   <div class="d-flex justify-content-center mt-3 mb-20">
                                    <form action="DeleteWishList" method="get">
                                        <input type="hidden" name="pid" value="${i.product_id}">
                                        <button type="submit" class="btn btn-danger">Delete Wish List</button>
                                    </form>
                                </div>
                                </div>
                        </div>

                    </c:forEach>
                    </section>
                </div>
            </div>



        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript"></script>

        <script>
            function handleCheckboxChange(checkbox) {
                var isChecked = checkbox.checked;
                var value = checkbox.value;

                if (isChecked) {
                    // Tạo một request để gửi giá trị của checkbox đến server
                    var xhr = new XMLHttpRequest();
                    var url = "searchpage?filter_id=" + encodeURIComponent(value);
                    xhr.open("GET", url, true);
                    xhr.send();
                }
            }
        </script>
        <script>
            function sort(ads) {
                document.sortProductSearch.submit();

                // Thực hiện hành động khi nhấn vào nút Sort
                console.log("Sorting items");
            }
        </script>
    </body>
</html>
