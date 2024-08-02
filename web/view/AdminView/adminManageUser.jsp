<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.*"  %>
<%@page import="util.*" %>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Management</title>
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
                text-align: center;
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
            .search{
                align-items: center;
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
    </head>
    <body>

        <h1>User Management</h1>

        <!-- Button to add a new user -->

        <div class="taskbar">
            <div>
                <button onclick="goHome()">
                    <a href="dashboard" class="add-user-button">Home</a>
                </button>
            </div>
            <div class="right-taskbar" >

                <form action="searchuser" method="get">
                    <a class ="search">
                        <span class="material-icons-outlined">search</span>
                        <input type="text" name="txt" placeholder="Search..." style="width:400px;">
                        <button type="submit" class="btn btn-secondary btn-number">search</button>
                    </a>

                </form>

                <div class="dropdown">
                    <form action="manageuser" method="post" name="sortUser">
                        <select onchange="sort(this)" name="optionSort">
                            <option ${requestScope.valueOption==0 ?'selected':'' } value="0">Sort by ID</option>
                            <option value="1" ${requestScope.valueOption==1 ?'selected':'' }>Sort by Name(A-Z)</option>
                            <option value="2" ${requestScope.valueOption==2 ?'selected':'' }>Sort by Name(Z-A)</option>
                        </select>
                    </form>


                </div>
            </div>
        </div>
        <!-- Table to display user data -->
        <table>
            <thead>
                <tr>
                    <th>Image</th>
                    <th>Fullname</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Join Date</th>
                     <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- Populate this section dynamically with user data -->
                <c:forEach items="${requestScope.listUser}" var="i">
                    <tr>

                         <td><img style="width: 100px;height: 100px" src="../${i.image}" alt="" /></td>
                        <td>${i.fullname}</td>
                        <td>${i.phoneNumber}</td>
                        <td>${i.email}</td>
                        <td style="width: 100px">${i.address}</td>
                         <td style="width: 10px">${i.getRole().role_name}</td>
                          <c:choose>
                            <c:when test="${i.status == 1}">
                                <c:set var="statusOfUser" value="Active"/>
                            </c:when>
                            <c:when test="${i.status == -1}">
                                <c:set var="statusOfUser" value="Banned"/>
                            </c:when>
                        </c:choose>
                          <td>${statusOfUser}</td>
                          <td>${i.startDate}</td>
                        <td>
                           <c:choose>
                            <c:when test="${i.status == 1}">
                            
                                <a href="banseller?idUser=${i.getUser_id()}" title="Ban" onclick="return confirm('Are you sure you want to BAN this User?')">
                                 <span class="material-symbols-outlined">block</span>
                                 </a>
                             </c:when>
                            <c:when test="${i.status == -1}">
                            
                                <a href="unbanuser?idUser=${i.getUser_id()}" title="Unban" onclick="return confirm('Are you sure you want to UNBAN this User?')">
                                 <span class="material-symbols-outlined">close</span>
                                 </a>
                             </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                <!-- More rows for additional users -->
            </tbody>
        </table>
        <script>
            function goHome() {
                // Thực hiện hành động khi nhấn vào nút Home
                console.log("Redirect to Home page");
            }

            function sort(ads) {
                document.sortUser.submit();
                // Thực hiện hành động khi nhấn vào nút Sort
                console.log("Sorting items");
            }

        </script>
    </body>
</html>
