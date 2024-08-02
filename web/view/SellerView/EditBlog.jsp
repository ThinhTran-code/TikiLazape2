<%-- 
    Document   : addProduct
    Created on : Jul 13, 2024, 10:31:31 PM
    Author     : sktnb
--%>
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
        <c:url value="/JS/jquery-3.7.1.min.js" var="libJquery"/>
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
                            <h2>Edit <b>Blog</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="manageblog" class="btn btn-danger"><i class="material-icons">&#xE5C4;</i> <span>Cancel</span></a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="addProductModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="editblog" method="post" enctype="multipart/form-data">
                            <input type="hidden" value="${blog.blog_id}" name="blog_id">
                            <input type="hidden" value="${blog.blog_image}" name="blog_image_1">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Blog</h4>
                            </div>
                            
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Blog Title</label>
                                    <input type="text" name="txtName" class="form-control" value="${blog.blog_title}" required>
                                </div>
                                <div class="form-group">
                                    <label>Blog Content</label>
                                    <textarea style="height: 280px;" name="txtContent" class="form-control"  required>${blog.blog_content}</textarea>
                                </div>
                                <div class="form-group">
                                    <label>Category Name</label>
                                    <select name="category" class="form-select" aria-label="Default select example"  onchange="updateFilters(this.value)">
                                        <c:forEach items="${listc}" var="c">
                                            
                                            <option value="${c.category_id}" ${c.category_id==blog.category_id?'selected':''}>${c.category_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Images</label>
                                    <div id="imageFields">
                                        <img src="../${blog.blog_image}" id="imageBlog"/>
                                        <label for="imageInput">choose Image</label>
                                        <input type="file" name="txtImages" style="display: none;" class="form-control-file" id="imageInput" accept="image/*" >
                                    </div>
                                   
                                </div>
                            </div>
                           
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Edit Blog">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="${libJquery}"></script>
    <script>
        const image = document.querySelector('#imageBlog');
        const input = document.querySelector('#imageInput');
        input.addEventListener('change', () => {
            const fileReader = new FileReader();
            fileReader.readAsDataURL(input.files[0]);
            fileReader.onload = (fileReaderEvent) => {
                image.src = fileReaderEvent.target.result;
            };
        });



    </script>
    </body>
</html>