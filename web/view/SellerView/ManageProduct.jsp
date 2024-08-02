<%-- 
    Document   : ProductWareHouse
    Created on : May 25, 2024, 9:31:32 PM
    Author     : sktnb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <c:url var="manage" value="/CSS/manage.css"/>
    <link rel="stylesheet" href="${manage}">
    <link href="CSS/" rel="stylesheet" type="text/css"/>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Montserrat', sans-serif;
        }
        .container{
            display: grid;
            /*            grid-template-columns: 260px 1fr 1fr 1fr;*/
            grid-template-rows: 0.2fr 3fr;
            grid-template-areas:
                'sidebar header header header'
                'sidebar main main main';
            height: 100vh;
        }
        .taskbar {
            color: #fff;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px;
        }
        .taskbar input[type="text"] {
            padding: 5px;
            border-radius: 5px;
            border: none;
            outline: none;
        }
        .taskbar button {
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: #555;
            color: #fff;
            margin-left: 10px;
        }
        .right-taskbar{
            color: black;
            width: 65%;
            display: flex;
            justify-content: space-between;
        }
        img{
            width: 50%;
        }
        .dropdown{
            color: black;
        }
        #sidebar {
            grid-area: sidebar;
            height: 100%;
            background-color: #263043;
            overflow-y: auto;
            transition: all 0.5s;
            -webkit-transition: all 0.5s;
        }

        .sidebar-title {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 30px 30px 30px 30px;
            margin-bottom: 30px;
        }

        .sidebar-title > span {
            display: none;
        }

        .sidebar-brand {
            margin-top: 15px;
            font-size: 20px;
            font-weight: 700;
        }

        .sidebar-list {
            padding: 0;
            margin-top: 15px;
            list-style-type: none;
        }

        .sidebar-list-item {
            padding: 20px 20px 20px 20px;
            font-size: 18px;
        }

        .sidebar-list-item:hover {
            background-color: rgba(255, 255, 255, 0.2);
            cursor: pointer;
        }

        .sidebar-list-item > a {
            text-decoration: none;
            color: #9e9ea4;
        }

        .sidebar-responsive {
            display: inline !important;
            position: absolute;
            /*
              the z-index of the ApexCharts is 11
              we want the z-index of the sidebar higher so that
              the charts are not showing over the sidebar 
              on small screens
            */
            z-index: 12 !important;
        }
        .table-wrapper {
            flex-grow: 1;
        }
        .table-title {
            margin-bottom: 20px;
        }
        @media (min-width: 1200px) {
            .container {
                width: 1485px;
            }
        }
    </style>
    <body>
        <div class="container" style="margin-left: 0px;margin-right: 0px;padding-left: 0px;">
            <%@include file="../../Template/navbarSeller.jsp" %>
            <div id="sidebar">
                <div class="sidebar-title">
                    <a href="../home">Back to homepage </a>

                </div>
                <ul class="sidebar-list">
                    <li class="sidebar-list-item">
                        <a href="dashboard" >
                            <span class="material-icons-outlined">dashboard</span> Dashboard
                        </a>
                    </li>
                    <li class="sidebar-list-item">
                        <a href="manageproduct" >
                            <span class="material-icons-outlined">inventory_2</span> Products Management
                        </a>
                    </li>
                    <li class="sidebar-list-item">
                        <a href="manageorder">
                            <span class="material-icons-outlined">fact_check</span>Order Management
                        </a>
                    </li>
                    <li class="sidebar-list-item">
                        <a href="manageblog" target="_blank">
                            <span class="material-icons-outlined">fact_check</span> Blog Management
                        </a>
                    </li>
                    <li class="sidebar-list-item">
                        <a href="InformationStore" >
                            <span class="material-icons-outlined">inventory_2</span> Information Store
                        </a>
                    </li>
                    <li class="sidebar-list-item">
                        <a href="../logout" >
                            <span class="material-icons-outlined">logout</span> Logout
                        </a>
                    </li>
                </ul>
            </div>
            <div class="table-wrapper" style="margin-top: 0px;">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="addproduct" class="btn btn-success"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                        </div>
                    </div>
                    <div class="taskbar">
                        <div class="right-taskbar">
                            <form action="searchproduct" method="get">
                                <input name="txt" type="search" id="search" style="width:400px" value="${txt}">
                                <button type="submit">search</button>
                            </form>
                            <div class="dropdown">
                                <form action="searchproduct" method="post" name="sortProductSeller">
                                    <select onchange="sort(this)" name="optionSort">
                                        <option value="0" ${requestScope.valueOption==0 ?'selected':''}>Sort by Price(Low-High)</option>
                                        <option value="1" ${requestScope.valueOption==1 ?'selected':''}>Sort by Price(High-Low)</option>
                                    </select>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Category Name</th>
                            <th>Image</th>
                            <th>Origin Price</th>
                            <th>Percent Sale</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listP}" var="o">
                            <tr>
                                <td>${o.product_id}</td>
                                <td>${o.product_name}</td>
                                <td>${CAdb.getCategory(o.product_id)}</td>
                                <td><img src="${pageContext.request.contextPath}/${o.listImage.get(0).imageProduct_url}" alt=""></td>
                                <td>${o.product_originPrice}</td>
                                <td>${o.product_percenSale}</td>
                                <c:choose>
                            <c:when test="${o.isActive == true}">
                                <c:set var="statusOfProduct" value="Accepted"/>
                            </c:when>
                            <c:when test="${o.isActive == false}">
                                <c:set var="statusOfProduct" value="Not Accept"/>
                            </c:when>
                        </c:choose>
                        <td>${statusOfProduct}</td>
                                <td>
                                    <a href="edit?pid=${o.product_id}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#">Previous</a></li>
                        <li class="page-item active"><a href="#" class="page-link">1</a></li>
                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                        <li class="page-item"><a href="#" class="page-link">3</a></li>
                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
        </div>



        <div id="deleteEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">
                            <h4 class="modal-title">Delete Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete these Records?</p>
                            <p class="text-warning"><small>This action cannot be undone.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="JS/manage.js" type="text/javascript"></script>
        <script>
                                        function sort(ads) {
                                            document.sortProductSeller.submit();

                                            // Thực hiện hành động khi nhấn vào nút Sort
                                            console.log("Sorting items");
                                        }
        </script>
    </body>
</html>
