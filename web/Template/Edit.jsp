<%-- 
    Document   : Edit
    Created on : May 26, 2024, 11:14:37 PM
    Author     : sktnb
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            img{
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
            .quantity {
                display: flex;
            }
            .quantity p {
                font-size: 18px;
                font-weight: 500;
            }
            .quantity input {
                width: 100px;
                height: 20px;
                font-size: 17px;
                font-weight: 500;
                text-align: center;
                margin-left: 15px;
            }
            .size {
                display: flex;
                align-items: center;
                margin: 20px 0;
            }
            .size p {
                font-size: 18px;
                font-weight: 500;
            }
            .psize {
                width: 60px;
                height: 30px;
                border: 1px solid #ff5e00;
                color: #000;
                text-align: center;
                margin: 0 10px;
                cursor: pointer;
            }
            .psize.active {
                border-width: 1.5px;
                color: #ff5e00;
                font-weight: 500;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Edit <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="edit" method="post" enctype="multipart/form-data">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Product</h4>
                                <a href="manageproduct"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></a>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Product ID</label>
                                    <input value="${detail.product_id}" name="id" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Product Name</label>
                                    <input value="${detail.product_name}" name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <input value="${cadb.getCategory(detail.product_id)}" name="category" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <c:forEach items="${listi}" var="i" varStatus="status">
                                        <div class="container mb-3">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <img src="../${i.imageProduct_url}" class="img-fluid">
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="hidden" name="OldtxtImages[]" value="${i.imageProduct_url}">
                                                    <input type="file" name="txtImages[${status.index}]" class="form-control-file" accept="image/*">
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <div class="form-group">
                                        <label>Origin Price</label>
                                        <input value="${detail.product_originPrice}" name="price" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Percent Sale</label>
                                        <input value="${detail.product_percenSale}" name="sale" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">

                                        <div id="productTypeColorFields">
                                            <c:forEach items="${detail.listType}" var="t">
                                                <c:forEach items="${requestScope.colorList}" var="l">
                                                    <div class="form-row align-items-center mb-3">
                                                        <div class="col-md-4">
                                                            <label>Type: ${t.type_describes}</label>
                                                            <input type="hidden" name="typeIDs[]" value="${t.type_id}">
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label>Color: ${l.color_name}</label>
                                                            <input type="hidden" name="colorIDs[]" value="${l.color_id}">
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label>Quantity: </label><input type="number" name="quantities[]" value="${requestScope.db.getStockByProductCharacterics(detail.product_id,t.type_id,l.color_id)}" class="form-control" min="1" required>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:forEach>
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea name="description" class="form-control" required>${detail.product_Describes}</textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="submit" class="btn btn-success" value="Edit">
                                    </div>
                                </div>
                        </form>

                        ${errorMessage}

                    </div>
                </div>
            </div>
            <script src="JS/manage.js" type="text/javascript"></script>
    </body>
</html>
