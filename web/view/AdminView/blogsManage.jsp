<%-- 
    Document   : productManage
    Created on : Mar 5, 2024, 1:36:33 AM
    Author     : Asus
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="Model.*" %>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manage List BLog</title>
        <style>
            /* Basic styling */
            body {
                font-family: Arial, sans-serif;
            }
            h1 {
                text-align: center;
                margin-top: 50px;
            }
            table {
                width: 80%;
                margin: 0 auto;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            .add-user-button {
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            .add-user-button:hover {
                background-color: #45a049;
            }

        </style>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }
            .taskbar {
                background-color: #333;
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
            .search{
                text-decoration: none;
                align-content: center;
            }
            .material-icons-outlined {
                vertical-align: middle;
                line-height: 1px;
                font-size: 35px;
                color: #f8f9fa;



            }
            .right-taskbar{
                width: 65%;
                display: flex;
                justify-content: space-between;
            }

        </style>
        <style>
            .add html, body {
                min-height: 100%;
            }
            body, div, form, input, select, textarea, label, p {
                padding: 0;
                margin: 0;
                outline: none;
                font-family: Roboto, Arial, sans-serif;
                font-size: 14px;
                color: #666;
                line-height: 22px;
            }
            .add h1 {
                position: absolute;
                margin: 0;
                font-size: 40px;
                color: #fff;
                z-index: 2;
                line-height: 83px;
            }
            .add textarea {
                width: calc(100% - 12px);
                padding: 5px;
            }
            .add .testbox {
                display: flex;
                justify-content: center;
                align-items: center;
                height: inherit;
                padding: 20px;
            }
            .add form {
                width: 100%;
                padding: 20px;
                border-radius: 6px;
                background: #fff;
                box-shadow: 0 0 8px  #669999;
            }
            .add .banner {
                position: relative;
                height: 300px;
                background-image: url("/uploads/media/default/0001/02/2d2ed84e0a2678357d5885b627ccd03a35d3c54c.jpeg");
                background-size: cover;
                display: flex;
                justify-content: center;
                align-items: center;
                text-align: center;
            }
            .add .banner::after {
                content: "";
                background-color: rgba(0, 0, 0, 0.2);
                position: absolute;
                width: 100%;
                height: 100%;
            }
            .add input, select, textarea {
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }
            .add input {
                width: calc(100% - 10px);
                padding: 5px;
            }
            .add input[type="date"] {
                padding: 4px 5px;
            }
            .add textarea {
                width: calc(100% - 12px);
                padding: 5px;
            }
            .add .item:hover p, .item:hover i, .question:hover p, .question label:hover, input:hover::placeholder {
                color:  #669999;
            }
            .add .item input:hover, .item select:hover, .item textarea:hover {
                border: 1px solid transparent;
                box-shadow: 0 0 3px 0  #669999;
                color: #669999;
            }
            .add .item {
                position: relative;
                margin: 10px 0;
            }
            .add .item span {
                color: red;
            }
            .add .week {
                display:flex;
                justfiy-content:space-between;
            }
            .add .colums {
                display:flex;
                justify-content:space-between;
                flex-direction:row;
                flex-wrap:wrap;
            }
            .add .colums div {
                width:48%;
            }
            .add input[type="date"]::-webkit-inner-spin-button {
                display: none;
            }
            .add .item i, input[type="date"]::-webkit-calendar-picker-indicator {
                position: absolute;
                font-size: 20px;
                color:  #a3c2c2;
            }
            .add .item i {
                right: 1%;
                top: 30px;
                z-index: 1;
            }
            .add input[type=radio], input[type=checkbox]  {
                display: none;
            }
            .add label.radio {
                position: relative;
                display: inline-block;
                margin: 5px 20px 15px 0;
                cursor: pointer;
            }
            .add .question span {
                margin-left: 30px;
            }
            .add .question-answer label {
                display: block;
            }
            .add label.radio:before {
                content: "";
                position: absolute;
                left: 0;
                width: 17px;
                height: 17px;
                border-radius: 50%;
                border: 2px solid #ccc;
            }
            .add input[type=radio]:checked + label:before, label.radio:hover:before {
                border: 2px solid  #669999;
            }
            .add label.radio:after {
                content: "";
                position: absolute;
                top: 6px;
                left: 5px;
                width: 8px;
                height: 4px;
                border: 3px solid  #669999;
                border-top: none;
                border-right: none;
                transform: rotate(-45deg);
                opacity: 0;
            }
            .add input[type=radio]:checked + label:after {
                opacity: 1;
            }
            .add .flax {
                display:flex;
                justify-content:space-around;
            }
            .add .btn-block {
                margin-top: 10px;
                text-align: center;
            }
            .add button {
                width: 150px;
                padding: 10px;
                border: none;
                border-radius: 5px;
                background:  #669999;
                font-size: 16px;
                color: #fff;
                cursor: pointer;
            }
            .add button:hover {
                background:  #a3c2c2;
            }
            @media (min-width: 568px) {
                .add .name-item, .city-item {
                    display: flex;
                    flex-wrap: wrap;
                    justify-content: space-between;
                }
                .add .name-item input, .name-item div {
                    width: calc(50% - 20px);
                }
                .add .name-item div input {
                    width:97%;
                }
                .add .name-item div label {
                    display:block;
                    padding-bottom:5px;
                }
            }
        </style>
    </head>
    <body>

        <h1 style="
            margin-bottom: 15px;
            margin-top: 30px;
            " >
            Manage Blog
        </h1>

        <!-- Button to add a new product -->

        <div class="taskbar">
            <div>
                <button onclick="goHome()">
                    <a href="addUser.jsp" class="add-user-button">Home</a>
                </button>
                <button onclick="">
                    <a href="addBlog?blog_id=${i.blog_id}" class="add-user-button">Add Blog From Admin</a>
                </button>
            </div>
            <div class="right-taskbar" >

                <form action="searchBlog" method="get">
                    <a class ="search">
                        <span class="material-icons-outlined">search</span>
                        <input type="text" name="txt" placeholder="Search..." style="width:400px;">
                        <button type="submit" class="btn btn-secondary btn-number">search</button>
                    </a>
                </form>


                <div class="dropdown">
                    <form action="BlogManage" method="post" name="sortBlog">
                        <select onchange="sort(this)" name="optionSort">
                            <option value="0" ${requestScope.valueOption==0 ?'selected':'' }>Sort by ID</option>
                            <option ${requestScope.valueOption==1 ?'selected':'' } value="1">Sort by Name(A-Z)</option>
                        </select>
                    </form>
                </div>
            </div>
        </div>




        <!-- Table to display product data -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Category</th>                    
                    <th>Image</th>
                    <th>Create Date</th>  
                    <th colSpan="2" style="text-align: center">Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- Populate this section dynamically with product data -->
                <c:forEach items="${requestScope.lists}" var="i">
                    <tr>
                        <td>${i.blog_id}</td>
                        <td>${i.blog_title}</td>
                        <td>${i.category.category_name}</td>
                        <td><img style="width: 100px;height: 100px" src="../${i.blog_image}"/></td>
                        <td>${i.blog_create_day}</td>
                        <td style="text-align: center;"> 
                            <form action="deleteBlog" method="post">
                                <input type="hidden" name="blog_id" value="${i.blog_id}"/>
                                <input type="hidden" name="category_id" value="${i.category_id}"/>
                                <button type="submit" style="background-color: red; color: white; border: none; padding: 10px 20px; cursor: pointer;">Delete</button>
                            </form>
                        </td>
                        <td>
                            <div>
                                <a href="editBlog?blog_id=${i.blog_id}">
                                    <button class="btn btn-success btn-sm rounded-0" type="button" style="background-color: gray; color: white; border: none; padding: 10px 20px; cursor: pointer;" data-toggle="tooltip" data-placement="top" title="Edit"><span class="material-symbols-outlined">Edit</span></button>
                                </a>
                                <!--<button type="submit" style="background-color: gray; color: white; border: none; padding: 10px 20px; cursor: pointer;">Edit</button>-->
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <!-- More rows for additional products -->
            </tbody>
        </table>


        <script>
            function goHome() {
                // Thực hiện hành động khi nhấn vào nút Home
                console.log("Redirect to Home page");
            }

            function sort(ads) {
                document.sortBlog.submit();
                // Thực hiện hành động khi nhấn vào nút Sort
                console.log("Sorting items");
            }
        </script>
    </body>
</html>
