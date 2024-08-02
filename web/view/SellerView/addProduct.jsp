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
                            <h2>Add <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="manageproduct" class="btn btn-danger"><i class="material-icons">&#xE5C4;</i> <span>Cancel</span></a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="addProductModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="addproduct" method="post" enctype="multipart/form-data">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Product Name</label>
                                    <input type="text" name="txtName" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input type="number" name="txtPrice" class="form-control" min="1" required>
                                </div>
                                <div class="form-group">
                                    <label>Describes</label>
                                    <textarea name="txtDescription" class="form-control" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Category Name</label>
                                    <select name="category" class="form-select" aria-label="Default select example" onchange="updateFilters(this.value)">
                                        <c:forEach items="${listc}" var="c">
                                            <option value="${c.category_id}">${c.category_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Filter</label>
                                    <select name="filter" id="filter" class="form-select">
                                        <c:forEach items="${listf}" var="f">
                                            <option value="${f.category_id}">${f.nameFilter}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Brand</label>
                                    <select name="brand" id="brand" class="form-select">
                                        <c:forEach items="${listb}" var="b">
                                            <option value="${b.brand_id}">${b.brand_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <!--<label>Store</label>-->
                                    <input type="hidden" name="store" class="form-control" value="${id}" required>
                                </div>
                                <div class="form-group">
                                    <label>Percent Sale</label>
                                    <input type="number" name="percentSale" class="form-control" min="0" required>
                                </div>
                                <div class="form-group">
                                    <label>Import Date</label>
                                    <input type="date" name="importDate" id="importDate" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Type, Color, Quantity</label>
                                    <div id="productTypeColorFields">
                                        <div class="productTypeColorField">
                                            <select name="typeIDs[]" class="form-select typeSelect">
                                                <c:forEach items="${listt}" var="t">
                                                    <option value="${t.type_id}">${t.type_describes}</option>
                                                </c:forEach>
                                            </select>
                                            <select name="colorIDs[]" class="form-select colorSelect">
                                                <c:forEach items="${listco}" var="l">
                                                    <option value="${l.color_id}">${l.color_name}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="number" name="quantities[]" required>
                                            <button type="button" class="btn btn-danger" onclick="removeProductTypeColorField(this)">Remove</button>
                                        </div>
                                    </div>
                                    <button type="button" class="btn btn-success" onclick="addProductTypeColorField()">Add</button>
                                </div>
                                <div class="form-group">
                                    <label>Images</label>
                                    <div id="imageFields">
                                        <img src="image/image_product/image_pants"/>
                                        <input type="file" name="txtImages[]" class="form-control-file" id="image" accept="image/*" required>
                                    </div>
                                    <button type="button" class="btn btn-success" onclick="addImageField()">Add image</button>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Add Product">
                            </div>
                            ${errorMessage}
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', (event) => {
                let today = new Date();
                let day = ('0' + today.getDate()).slice(-2);
                let month = ('0' + (today.getMonth() + 1)).slice(-2);
                let todayString = today.getFullYear() + '-' + month + '-' + day;
                document.getElementById('importDate').value = todayString;
            });
        </script>
        <script>
            function updateFilters(categoryId) {
                var filterSelect = document.getElementById('filter');
                filterSelect.innerHTML = ''; // Xóa các option cũ

            <%-- Thay đổi biến listf thành danh sách biến f --%>
            <c:forEach items="${listf}" var="f">
                if (${f.category_id} == categoryId) {
                    var option = document.createElement('option');
                    option.value = "${f.category_id}";
                    option.text = "${f.nameFilter}";
                    filterSelect.appendChild(option);
                }
            </c:forEach>
            }

            function addProductTypeColorField() {
                var container = document.getElementById("productTypeColorFields");
                var div = document.createElement("div");
                div.className = "productTypeColorField";

                var typeSelect = document.createElement("select");
                typeSelect.name = "typeIDs[]";
                typeSelect.className = "form-select typeSelect";
            <c:forEach items="${listt}" var="t">
                var option = document.createElement("option");
                option.value = "${t.type_id}";
                option.text = "${t.type_describes}";
                typeSelect.appendChild(option);
            </c:forEach>
                div.appendChild(typeSelect);

                var colorSelect = document.createElement("select");
                colorSelect.name = "colorIDs[]";
                colorSelect.className = "form-select colorSelect";
            <c:forEach items="${listco}" var="l">
                var option = document.createElement("option");
                option.value = "${l.color_id}";
                option.text = "${l.color_name}";
                colorSelect.appendChild(option);
            </c:forEach>
                div.appendChild(colorSelect);

                var quantityInput = document.createElement("input");
                quantityInput.type = "number";
                quantityInput.name = "quantities[]";
//                quantityInput.className = "form-control quantityInput";
                quantityInput.required = true;
                div.appendChild(quantityInput);

                var removeButton = document.createElement("button");
                removeButton.textContent = "Remove";
                removeButton.type = "button";
                removeButton.className = "btn btn-danger";
                removeButton.onclick = function () {
                    div.remove();
                };
                div.appendChild(removeButton);

                container.appendChild(div);
            }

            function addImageField() {
                var container = document.getElementById("imageFields");
                var input = document.createElement("input");
                input.type = "file";
                input.name = "txtImages[]";
                input.className = "form-control-file";
                input.required = true;

                container.appendChild(input);
                container.appendChild(document.createElement("br"));
            }
        </script>
    </body>
</html>
