<%-- 
    Document   : Profile
    Created on : May 25, 2024, 8:47:13 PM
    Author     : Thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        
        <%@include file="../../Template/libheader.jsp" %>
        <link rel="stylesheet" href="CSS/profileCss.css">
        <meta charset="utf-8" />
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />

        <title>Users / Profile </title>
        <meta content="" name="description" />
        <meta content="" name="keywords" />

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon" />
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon" />

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect" />
        <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet"
            />

    </head>

    <body>
        <%@include file="../../Template/navbar.jsp" %>
        <main id="main" class="main">
            <!-- End Page Title -->

            <section class="section profile">
                <div class="row">
                    <div class="col-xl-4">
                        <div class="card">
                            <div
                                class="card-body profile-card pt-4 d-flex flex-column align-items-center"
                                >
                                <img 
                                    src="${s_u_tikilazapee.image}"
                                    alt="Profile"
                                    class="rounded-circle"
                                    />
                                <h2>${s_u_tikilazapee.fullname}</h2>

                            </div>
                        </div>
                    </div>

                    <div class="col-xl-8">
                        <div class="card">
                            <div class="card-body pt-3">
                                <!-- Bordered Tabs -->
                                <ul class="nav nav-tabs nav-tabs-bordered">
                                    <li class="nav-item">
                                        <button
                                            class="nav-link active"
                                            data-bs-toggle="tab"
                                            data-bs-target="#profile-overview"
                                            >
                                            Overview
                                        </button>
                                    </li>

                                    <li class="nav-item">
                                        <button
                                            class="nav-link"
                                            data-bs-toggle="tab"
                                            data-bs-target="#profile-edit"
                                            >
                                            Edit Profile
                                        </button>
                                    </li>

                                    <li class="nav-item">
                                        <button
                                            class="nav-link"
                                            data-bs-toggle="tab"
                                            data-bs-target="#profile-change-password"
                                            >
                                            Change Password
                                        </button>
                                    </li>
                                </ul>
                                <div class="tab-content pt-2">
                                    <div
                                        class="tab-pane fade show active profile-overview"
                                        id="profile-overview"
                                        >
                                        <h5 class="card-title">
                                            Profile Details
                                        </h5>

                                        <div class="row">
                                            <div
                                                class="col-lg-3 col-md-4 label"
                                                >
                                                FullName
                                            </div>
                                            <div class="col-lg-9 col-md-8">
                                                ${s_u_tikilazapee.fullname}
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div
                                                class="col-lg-3 col-md-4 label"
                                                >
                                                PhoneNumber
                                            </div>
                                            <div class="col-lg-9 col-md-8">
                                                ${s_u_tikilazapee.phoneNumber}
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div
                                                class="col-lg-3 col-md-4 label"
                                                >
                                                Email
                                            </div>
                                            <div class="col-lg-9 col-md-8">
                                                ${s_u_tikilazapee.email}
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div
                                                class="col-lg-3 col-md-4 label"
                                                >
                                                Gender
                                            </div>
                                            <div class="col-lg-9 col-md-8">
                                                <c:if test="${s_u_tikilazapee.gender eq 1}">
                                                    Male
                                                </c:if>
                                                <c:if test="${s_u_tikilazapee.gender eq 0}">
                                                    Female
                                                </c:if>
                                                <c:if test="${s_u_tikilazapee.gender eq 2}">
                                                    Other
                                                </c:if>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div
                                                class="col-lg-3 col-md-4 label"
                                                >
                                                Address
                                            </div>
                                            <div class="col-lg-9 col-md-8">
                                                ${s_u_tikilazapee.address}
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div
                                                class="col-lg-3 col-md-4 label"
                                                >
                                                Date of Birth
                                            </div>
                                            <div class="col-lg-9 col-md-8">

                                                ${s_u_tikilazapee.DOB}
                                            </div>
                                        </div>
                                        <hr/>

                                    </div>

                                    <div
                                        class="tab-pane fade profile-edit pt-3"
                                        id="profile-edit"
                                        >
                                        <!-- Profile Edit Form -->
                                        <form action="myprofile" method="post" enctype="multipart/form-data">
                                            <div class="row mb-3">
                                                <label
                                                    for="profileImage"
                                                    class="col-md-4 col-lg-3 col-form-label"
                                                    >Profile Image</label
                                                >
                                                <div class="col-md-8 col-lg-9">
                                                    <div class="image-box">                                                     
                                                        <img src="${s_u_tikilazapee.image}" alt="Image"/>                                                       
                                                        <input type="file" accept="image/*" name="image" id="image"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Full Name</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input type="text" class="form-control" id="fullName" name="fullname" value="${s_u_tikilazapee.fullname}">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label for="Phone" class="col-md-4 col-lg-3 col-form-label">Phone Number</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${s_u_tikilazapee.phoneNumber}">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label for="Email" class="col-md-4 col-lg-3 col-form-label">Email</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input type="email" class="form-control" id="email" name="email" value="${s_u_tikilazapee.email}">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label for="Gender" class="col-md-4 col-lg-3 col-form-label">Gender</label>
                                                <div class="col-md-8 col-lg-9">

                                                    <input type="radio" id="Male" name="gender" value="1" ${s_u_tikilazapee.gender == 1 ? 'checked' : ''}> Male
                                                    <input type="radio" id="Female" name="gender" value="0" ${s_u_tikilazapee.gender == 0 ? 'checked' : ''}> Female
                                                    <input type="radio" id="Other" name="gender" value="2" ${s_u_tikilazapee.gender == 2 ? 'checked' : ''}> Other
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label for="Address" class="col-md-4 col-lg-3 col-form-label">Address</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input type="text" class="form-control" id="address" name="address" value="${s_u_tikilazapee.address}">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label for="dobir" class="col-md-4 col-lg-3 col-form-label">Date of Birth</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input type="date" id="dobir" name="dobir" value="${s_u_tikilazapee.DOB}" class="form-control"/>
                                                </div>
                                            </div>

                                            <div class="text-center">
                                                <button
                                                    type="submit"
                                                    class="btn btn-primary"
                                                    >
                                                    Save Changes
                                                </button>
                                            </div>
                                        </form>
                                        <!-- End Profile Edit Form -->
                                    </div>
                                    <div
                                        class="tab-pane fade pt-3"
                                        id="profile-settings"
                                        ></div>
                                    <div
                                        class="tab-pane fade pt-3"
                                        id="profile-change-password"
                                        >
                                        <!-- Change Password Form -->
                                        <form action="ChangePassword" method="post">   
                                            <input type="hidden" name="user_id" value="${s_u_tikilazapee.user_id}" />
                                            <div class="row mb-3">
                                                <label
                                                    for="currentPassword"
                                                    class="col-md-4 col-lg-3 col-form-label"
                                                    >Current Password</label
                                                >
                                                <div class="col-md-8 col-lg-9">
                                                    <input
                                                        name="current_password"
                                                        type="password"
                                                        class="form-control"
                                                        id="current_password"
                                                         /><i class="fa fa-eye eye-icon" onclick="togglePasswordVisibility('current_password')"></i>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label
                                                    for="newPassword"
                                                    class="col-md-4 col-lg-3 col-form-label"
                                                    >New Password</label
                                                >
                                                <div class="col-md-8 col-lg-9">
                                                    <input
                                                        name="new_password"
                                                        type="password"
                                                        class="form-control"
                                                        id="new_password"
                                                        />
                                                    <i class="fa fa-eye eye-icon" onclick="togglePasswordVisibility('new_password')"></i>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label
                                                    for="renewPassword"
                                                    class="col-md-4 col-lg-3 col-form-label"
                                                    >Re-enter New
                                                    Password</label
                                                >
                                                <div class="col-md-8 col-lg-9">
                                                    <input
                                                        name="re_new_password"
                                                        type="password"
                                                        class="form-control"
                                                        id="re_new_password"
                                                        />
                                                   <i class="fa fa-eye eye-icon" onclick="togglePasswordVisibility('re_new_password')"></i>
                                                </div>
                                            </div>
                                            <div class="text-center">
                                                <button
                                                    type="submit"
                                                    class="btn btn-primary"
                                                    >
                                                    Change Password
                                                </button>
                                            </div>
                                        </form>
                                        <!-- End Change Password Form -->
                                    </div>
                                </div>
                                <!-- End Bordered Tabs -->
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <!-- End #main -->

        <a
            href="#"
            class="back-to-top d-flex align-items-center justify-content-center"
            ><i class="bi bi-arrow-up-short"></i
            ></a>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var emailField = document.getElementById('email');
                emailField.readOnly = true;
            });
            const image = document.querySelector('.image-box img');
            const input = document.querySelector('.image-box input');
            input.addEventListener('change', () => {
                const fileReader = new FileReader();
                fileReader.readAsDataURL(input.files[0]);
                fileReader.onload = (fileReaderEvent) => {
                    image.src = fileReaderEvent.target.result;
                };
            });
             function togglePasswordVisibility(id) {
            const input = document.getElementById(id);
            const icon = input.nextElementSibling;
            if (input.type === "password") {
                input.type = "text";
                icon.classList.remove("fa-eye");
                icon.classList.add("fa-eye-slash");
            } else {
                input.type = "password";
                icon.classList.remove("fa-eye-slash");
                icon.classList.add("fa-eye");
            }
        }
        </script>
        <!-- Vendor JS Files -->
        <script src="JS/apexcharts.min.js"></script>
        <script src="JS/bootstrap.bundle.min.js"></script>
        <script src="JS/chart.umd.js"></script>
        <script src="JS/echarts.min.js"></script>
        <script src="JS/quill.js"></script>
        <script src="JS/simple-datatables.js"></script>
        <script src="JS/tinymce.min.js"></script>
        <script src="JS/validate.js"></script>

        <!-- Template Main JS File -->

    </body>
</html>
