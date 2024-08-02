<%-- 
    Document   : navbarSeller
    Created on : Jul 16, 2024, 10:40:55 PM
    Author     : hbtth
--%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
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
<div id="sidebar">
    <div class="sidebar-title">
        <a href="../home"> <span class="material-icons-outlined"></span>back to homepage</a>
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