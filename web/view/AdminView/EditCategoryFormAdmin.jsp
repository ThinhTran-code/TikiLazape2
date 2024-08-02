<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="CSS/manage.css" rel="stylesheet" type="text/css"/>
    <style>
        img {
            width: 100%;
        }
        .container {
            width: 100%;
        }
        .item {
            float: left;
            width: 25%;
            box-sizing: border-box;
            padding: 5px;
            border: 1px solid #000;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Edit <b>Category</b></h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="managecategory" class="btn btn-danger"><i class="material-icons">&#xE5C4;</i> <span>Cancel</span></a>
                    </div>
                </div>
            </div>
        </div>
        <div id="addCategoryModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="editcategory" method="post" enctype="multipart/form-data">
                 
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Category</h4>
                        </div>
                        <div class="modal-body">	
                            <div class="form-group">
                                <label>Category Name</label>
                                <input type="text" name="categoryName" class="form-control" required>
                                <input type="hidden" value="${category_id}" name="category_id"/>
                            </div>
                            <div class="form-group">
                                <label>Images</label>
                                <div id="imageFields">
                                    <img src="image/image_category/image_category_01"/>
                                    <input type="file" name="txtImages" class="form-control-file" id="image" accept="image/*" required>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-success" value="Edit Category">
                        </div>                  
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        const input = document.querySelector('input[type="file"]');
        input.addEventListener('change', (event) => {
            const [file] = event.target.files;
            if (file) {
                const img = document.querySelector('img');
                img.src = URL.createObjectURL(file);
            }
        });
    </script>
</body>
</html>
