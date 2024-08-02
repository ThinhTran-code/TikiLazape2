<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../../Template/libheader.jsp" %>
        <link rel="stylesheet" href="CSS/inforstore.css">
        <meta charset="utf-8" />
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />

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

        <style>
            body {
                font-family: 'Nunito', sans-serif;
                background: linear-gradient(to right, #f8cdda, #1c92d2);
                height: 100vh;
                margin: 0;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .card {
                width: 100%;
                max-width: 900px;
                border-radius: 0.5rem;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                display: flex;
                background: #fff;
            }

            .gradient-custom {
                background: linear-gradient(to bottom, #f6d365, #fda085);
                text-align: center;
                color: white;
                padding: 20px;
            }

            .card-body {
                padding: 40px;
            }

            .card-body h6 {
                margin-bottom: 20px;
                font-weight: bold;
                color: #333;
            }

            .form-control {
                border: 1px solid #ced4da;
                border-radius: 0.25rem;
                padding: 10px;
                margin-bottom: 15px;
                font-size: 14px;
                color: #495057;
            }

            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
                padding: 10px 20px;
                font-size: 14px;
                border-radius: 0.25rem;
                transition: background-color 0.3s ease, box-shadow 0.3s ease;
            }

            .btn-primary:hover {
                background-color: #0056b3;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .img-fluid {
                border-radius: 50%;
                width: 100px;
                height: 100px;
                object-fit: cover;
                margin-bottom: 15px;
            }
        </style>
    </head>

<<<<<<< HEAD
    <body>
=======
    <body style="margin-left: 0px;margin-right: 0px;padding-left: 0px;">
        
        <%@include file="../../Template/navbarSeller.jsp" %>

>>>>>>> origin/khanhhe173521
        <c:set value="${sessionScope.inforStore}" var="str"></c:set>
            <div class="card">

                <div class="card-body">
                    <form action="InformationStore" method="post" enctype="multipart/form-data">
                        <div class="row mb-3">                      
                            <div class="col-md-8 col-lg-9">
                                <div class="image-box">                                                     
                                    <img style="width: 135%;height: 200px" src="../${str.store_image}" alt="Image"/>                                                       
                                <input type="file" accept="image/*" name="store_image" id="store_image"/>
                            </div>
                        </div>
                    </div>
                    <h3>Information Store</h3>
                    <div class="row">
                        <div class="col-12 col-md-6 mb-3">
                            <label for="store_name">Store Name</label>
                            <input
                                name="store_name"
                                type="text"
                                class="form-control"
                                id="store_name"
                                value="${str.store_name}"
                                />
                        </div>
                        <div class="col-12 col-md-6 mb-3">
                            <label for="store_phone">Store Phone</label>
                            <input
                                name="store_phone"
                                type="text"
                                class="form-control"
                                id="store_phone"
                                value="${str.store_phone}"
                                />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 mb-3">
                            <label for="store_address">Store Address</label>
                            <input
                                name="store_address"
                                type="text"
                                class="form-control"
                                id="store_address"
                                value="${str.store_address}"
                                />
                        </div>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">
                            Edit
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <a
            href="#"
            class="back-to-top d-flex align-items-center justify-content-center"
            ><i class="bi bi-arrow-up-short"></i
            ></a>

        <!-- Vendor JS Files -->
        <script>
            const image = document.querySelector('.image-box img');
            const input = document.querySelector('.image-box input');
            input.addEventListener('change', () => {
                const fileReader = new FileReader();
                fileReader.readAsDataURL(input.files[0]);
                fileReader.onload = (fileReaderEvent) => {
                    image.src = fileReaderEvent.target.result;
                };
            });
        </script>
        <script src="JS/apexcharts.min.js"></script>
        <script src="JS/bootstrap.bundle.min.js"></script>
        <script src="JS/chart.umd.js"></script>
        <script src="JS/echarts.min.js"></script>
        <script src="JS/quill.js"></script>
        <script src="JS/simple-datatables.js"></script>
        <script src="JS/tinymce.min.js"></script>
        <script src="JS/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>
    </body>
</html>
