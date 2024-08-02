<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %>
<%@page import="DAO.*" %>
<%@page import="util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
    <title>Search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
    <c:url value="/CSS/listproductCSS.css" var="listproduct"/>
    <link rel="stylesheet" href="${listproduct}"/>
    <%@include file="/Template/libheader.jsp" %>
    <script src="JS/modernizr.js"></script>
    <style>
        .dropdown {
            display: inline-flex;
            margin: 10px 0px;
        }
        .panel-body {
            padding: 0px;
        }
    </style>
</head>
<body>
    <div style="height: 150px">
        <%@include file="/Template/navbar.jsp" %>
    </div>
    <div class="container bootdey">
        <div class="col-md-3">
            <section class="panel">
                <header class="panel-heading">Filter</header>
                <form id="filterForm" action="searchbyfilter" method="get">
                    <input type="hidden" name="txt" value="${txt}">
                    <div class="panel-body">
                        <ul class="nav prod-cat">
                            <li>
                                <c:forEach items="${listc}" var="c">
                                    <label>${c.category_name}</label>
                                    <c:forEach items="${fd.getListFilterForSearch(txt, c.category_id)}" var="o">
                                        <div class="checkbox-container">
                                            <input type="checkbox" name="filter" value="${o.filter_id}" onchange="updateFilterIds()"/>
                                            ${o.nameFilter}
                                        </div>
                                    </c:forEach>
                                </c:forEach>
                            </li>
                        </ul>
                    </div>
                    <input type="hidden" name="filterIds" id="filterIds">
                    <button type="submit">Search</button>
                </form>
            </section>
        </div>
        <div class="col-md-9">
            <section class="panel">
                <div class="panel-body">
                    <div class="col-md-6">
                        <div class="pull-right">
                            <div class="dropdown">
                                <label style="margin-right: 10px">Sort</label>
                                <form action="searchpage?txt=${txt}&index=${index}" method="post" name="sortProductSearch">
                                    <input type="hidden" value="${txt}" name="sorttxt">
                                    <input type="hidden" value="${index}" name="sortindex">
                                    <select onchange="sort(this)" name="optionSort">
                                        <option value="0" ${requestScope.valueOption==0 ? 'selected' : ''}>Sort by Price (Low-High)</option>
                                        <option value="1" ${requestScope.valueOption==1 ? 'selected' : ''}>Sort by Price (High-Low)</option>
                                        <option value="2" ${requestScope.valueOption==2 ? 'selected' : ''}>Top Sales</option>
                                    </select>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <ul class="pagination pagination-sm pro-page-list" style="margin-left: 280px">
                            <c:forEach begin="1" end="${endPage}" var="i" step="1">
                                <li><a class="active" href="searchpage?txt=${txt}&index=${i}">${i}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </section>
            <div class="row product-list">
                <c:forEach items="${lists}" var="i">
                    <div class="col-md-4">
                        <section class="panel">
                            <div class="card">
                                <div class="d-flex justify-content-between p-3">
                                    <a href="product?product_id=${i.product_id}">
                                        <p class="lead mb-0">Add to Cart<span class="material-symbols-outlined">shopping_cart</span></p>
                                    </a>
                                    <a href="AddWishList?pid=${i.product_id}">
                                        <p class="lead mb-0">Add Wish List</p>
                                    </a>
                                </div>
                                <a href="product?product_id=${i.product_id}">
                                    <div class="bg-image hover-zoom ripple" data-mdb-ripple-color="light">
                                        <img src="${i.listImage.get(0).imageProduct_url}" class="w-100" alt="Laptop" />
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
                                        <h5 class="text-danger mb-0">${Feature.format(Feature.calculateSalePrice(i.product_originPrice, i.product_percenSale))} VND</h5>
                                    </div>
                                    <div class="d-flex justify-content-between mb-2">
                                        <p class="text-muted mb-0">Available: <span class="fw-bold">${pd.quantityOfProductById(i.product_id)}</span></p>
                                        <div class="ms-auto text-warning">
                                            <i class="fa fa-star"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript"></script>
    <script>
        let filterIds = [];

        function updateFilterIds() {
            filterIds = [];
            document.querySelectorAll('input[name="filter"]:checked').forEach(checkbox => {
                filterIds.push(checkbox.value);
            });
            document.getElementById('filterIds').value = filterIds.join(',');
        }

        function sort(ads) {
            document.sortProductSearch.submit();
            console.log("Sorting items");
        }
    </script>
</body>
</html>
